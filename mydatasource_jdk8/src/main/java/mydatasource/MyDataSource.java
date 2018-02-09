package mydatasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Custom <code>DataSource</code> that wraps the real <code>DataSource</code>
 * and registers a list of {@link ConnectionDecorators} to provide custom jdbc
 * intercepter function.
 * 
 * @author Panyu
 *
 */
public class MyDataSource implements DataSource {

	protected Log logger = LogFactory.getLog(this.getClass());

	protected String name = "myDataSource";
	protected DataSource dataSource;
	protected List<String> connectionDecorators = new ArrayList<String>();
	protected List<Class<?>> connectionDecoratorClasses = new ArrayList<Class<?>>();
	protected Map<String, Map<String, Object>> dataStorage = new HashMap<String, Map<String, Object>>();
	protected Properties configs = new Properties();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
	 * List of {@link ConnectionDecorator} class names.
	 */
	public List<String> getConnectionDecorators() {
		return connectionDecorators;
	}

	/**
	 * List of {@link ConnectionDecorator} class names.
	 */
	public void setConnectionDecorators(List<String> connectionDecorators) {
		if (connectionDecorators == null) {
			throw new IllegalArgumentException(
					"connectionDecorators cannot be null");
		}
		this.connectionDecorators = connectionDecorators;
		this.connectionDecoratorClasses = new ArrayList<Class<?>>();
		for (String clsName : connectionDecorators) {
			try {
				Class<?> cls = Class.forName(clsName);
				if (!ConnectionDecorator.class.isAssignableFrom(cls)) {
					logger.error("Not a ConnectionDecorator class: " + clsName);
					throw new RuntimeException(
							"Not a ConnectionDecorator class: " + clsName);
				}
				this.connectionDecoratorClasses.add(cls);
			} catch (ClassNotFoundException e) {
				logger.error("Cannot find decorator class: " + clsName);
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * DataStorage can be used to store arbitrary data which is used by
	 * decorators.
	 * <p>
	 * This method is thread safe but the returned map is not.
	 */
	public Map<String, Object> getDataStorage(String className) {
		Map<String, Object> storage = dataStorage.get(className);
		if (storage == null) {
			synchronized (dataStorage) {
				storage = dataStorage.get(className);
				if (storage == null) {
					storage = new HashMap<String, Object>();
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
		List<ConnectionDecorator> decs = new ArrayList<ConnectionDecorator>();
		for (Class<?> decClass : connectionDecoratorClasses) {
			try {
				ConnectionDecorator dec = (ConnectionDecorator) decClass
						.newInstance();
				decs.add(dec);
				dec.setMyDataSource(this);
				dec.setDelegateConnection(curConn);
				curConn = dec;
			} catch (Exception e) {
				logger.error("Error creating decorator: " + decClass.getName(),
						e);
				throw new RuntimeException(e);
			}
		}
		// set raw connection and fully decorated connection for all decorators
		for (ConnectionDecorator dec : decs) {
			dec.setRawConnection(connection);
			dec.setFullyDecoratedConnection(curConn);
			dec.afterPropertiesSet();
		}
		return curConn;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return Logger.getGlobal();
	}

}
