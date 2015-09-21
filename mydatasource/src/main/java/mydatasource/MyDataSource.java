package mydatasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Custom <code>DataSource</code> that wraps the real <code>DataSource</code>
 * and provides decoration functions for <code>Connection</code>s and
 * <code>Statement</code>s.
 * 
 * @author Panyu
 *
 */
public class MyDataSource implements DataSource {

	protected Log logger = LogFactory.getLog(this.getClass());

	protected DataSource dataSource;
	protected List<String> decorators;
	protected List<Class<?>> connectionDecorators;
	protected List<Class<?>> statementDecorators;
	protected List<Class<?>> preparedStatementDecorators;
	protected List<Class<?>> callableStatementDecorators;
	protected Map<String, Map<String, Object>> dataStorage = new HashMap<String, Map<String, Object>>();
	protected Properties configs = new Properties();

	/**
	 * The real <code>DataSource</code>.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * The real <code>DataSource</code>.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * A list of decorator class names. Any class in this list should be
	 * subclass of {@link ConnectionDecorator}, {@link StatementDecorator},
	 * {@link PreparedStatementDecorator} or {@link CallableStatementDecorator}.
	 * Order of this list is the order of decorators from inside to outside.
	 */
	public List<String> getDecorators() {
		return decorators;
	}

	/**
	 * A list of decorator class names. Any class in this list should be
	 * subclass of {@link ConnectionDecorator}, {@link StatementDecorator},
	 * {@link PreparedStatementDecorator} or {@link CallableStatementDecorator}.
	 * Order of this list is the order of decorators from inside to outside.
	 */
	public void setDecorators(List<String> decorators) {
		this.decorators = decorators;
		this.connectionDecorators = new ArrayList<Class<?>>();
		this.statementDecorators = new ArrayList<Class<?>>();
		this.preparedStatementDecorators = new ArrayList<Class<?>>();
		this.callableStatementDecorators = new ArrayList<Class<?>>();
		for (String decorator : decorators) {
			try {
				Class<?> decoratorClass = Class.forName(decorator);
				if (ConnectionDecorator.class.isAssignableFrom(decoratorClass)
						&& !MyConnectionDecorator.class.equals(decoratorClass)) {
					this.connectionDecorators.add(decoratorClass);
				} else if (CallableStatementDecorator.class
						.isAssignableFrom(decoratorClass)) {
					this.callableStatementDecorators.add(decoratorClass);
				} else if (PreparedStatementDecorator.class
						.isAssignableFrom(decoratorClass)) {
					this.preparedStatementDecorators.add(decoratorClass);
				} else if (StatementDecorator.class
						.isAssignableFrom(decoratorClass)) {
					this.statementDecorators.add(decoratorClass);
				} else {
					logger.error("Class is not of any decorator type: "
							+ decorator);
				}
			} catch (ClassNotFoundException e) {
				logger.error("Cannot find decorator class: " + decorator);
			}
		}
	}

	public List<Class<?>> getConnectionDecorators() {
		return connectionDecorators;
	}

	public void setConnectionDecorators(List<Class<?>> connectionDecorators) {
		this.connectionDecorators = connectionDecorators;
	}

	public List<Class<?>> getStatementDecorators() {
		return statementDecorators;
	}

	public void setStatementDecorators(List<Class<?>> statementDecorators) {
		this.statementDecorators = statementDecorators;
	}

	public List<Class<?>> getPreparedStatementDecorators() {
		return preparedStatementDecorators;
	}

	public void setPreparedStatementDecorators(
			List<Class<?>> preparedStatementDecorators) {
		this.preparedStatementDecorators = preparedStatementDecorators;
	}

	public List<Class<?>> getCallableStatementDecorators() {
		return callableStatementDecorators;
	}

	public void setCallableStatementDecorators(
			List<Class<?>> callableStatementDecorators) {
		this.callableStatementDecorators = callableStatementDecorators;
	}

	/**
	 * DataStorage can be used to store arbitrary data which is used by
	 * decorators.
	 * <p>
	 * This method and returned storage is thread safe.
	 */
	public Map<String, Object> getDataStorage(String className) {
		Map<String, Object> storage = dataStorage.get(className);
		if (storage == null) {
			synchronized (dataStorage) {
				storage = dataStorage.get(className);
				if (storage == null) {
					storage = Collections
							.synchronizedMap(new HashMap<String, Object>());
					dataStorage.put(className, storage);
				}
			}
		}
		return storage;
	}

	/**
	 * DataStorage can be used to store arbitrary data which is used by
	 * decorators.
	 * <p>
	 * This method and returned storage is thread safe.
	 */
	public Map<String, Object> getDataStorage(Class<?> cls) {
		return getDataStorage(cls.getName());
	}

	/**
	 * Configuration data which can be used by utility decorators.
	 */
	public Properties getConfigs() {
		return configs;
	}

	/**
	 * Configuration data which can be used by utility decorators.
	 */
	public void setConfigs(Properties configs) {
		this.configs = configs;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return dataSource.getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		dataSource.setLogWriter(out);
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		dataSource.setLoginTimeout(seconds);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return dataSource.getLoginTimeout();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return dataSource.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return dataSource.isWrapperFor(iface);
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		return decorateConnection(connection);
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		Connection connection = dataSource.getConnection(username, password);
		return decorateConnection(connection);
	}

	protected Connection decorateConnection(Connection connection) {
		Connection curConn = connection;
		// add other decorators
		for (Class<?> decClass : connectionDecorators) {
			try {
				ConnectionDecorator dec = (ConnectionDecorator) decClass
						.newInstance();
				dec.setMyDataSource(this);
				dec.setDelegateConnection(curConn);
				curConn = dec;
			} catch (Exception e) {
				logger.error("Error creating decorator: " + decClass.getName(),
						e);
				throw new RuntimeException(e);
			}
		}
		// add MyConnectionDecorator as last decorator.
		MyConnectionDecorator myDec = new MyConnectionDecorator();
		myDec.setMyDataSource(this);
		myDec.setDelegateConnection(curConn);
		curConn = myDec;
		return curConn;
	}

}
