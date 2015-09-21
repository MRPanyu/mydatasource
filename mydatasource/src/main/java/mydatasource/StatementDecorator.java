package mydatasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

/**
 * Decorator base class for {@link Statement}. Any utility decorators for
 * <code>Statement</code> should be subclass of this.
 * 
 * @author Panyu
 *
 */
public class StatementDecorator implements Statement {

	protected Statement delegateStatement;
	protected Connection connection;
	protected MyDataSource myDataSource;

	/**
	 * Delegated statement object, which is either the raw statement or another
	 * statement decorator in the chain.
	 */
	public Statement getDelegateStatement() {
		return delegateStatement;
	}

	/**
	 * Delegated statement object, which is either the raw statement or another
	 * statement decorator in the chain.
	 */
	public void setDelegateStatement(Statement delegateStatement) {
		this.delegateStatement = delegateStatement;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Related {@link MyDataSource} instance.
	 */
	public MyDataSource getMyDataSource() {
		return myDataSource;
	}

	/**
	 * Related {@link MyDataSource} instance.
	 */
	public void setMyDataSource(MyDataSource myDataSource) {
		this.myDataSource = myDataSource;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return delegateStatement.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return delegateStatement.isWrapperFor(iface);
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		return delegateStatement.executeQuery(sql);
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		return delegateStatement.executeUpdate(sql);
	}

	@Override
	public void close() throws SQLException {
		delegateStatement.close();
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		return delegateStatement.getMaxFieldSize();
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		delegateStatement.setMaxFieldSize(max);
	}

	@Override
	public int getMaxRows() throws SQLException {
		return delegateStatement.getMaxRows();
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		delegateStatement.setMaxRows(max);
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		delegateStatement.setEscapeProcessing(enable);
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		return delegateStatement.getQueryTimeout();
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		delegateStatement.setQueryTimeout(seconds);
	}

	@Override
	public void cancel() throws SQLException {
		delegateStatement.cancel();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return delegateStatement.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		delegateStatement.clearWarnings();
	}

	@Override
	public void setCursorName(String name) throws SQLException {
		delegateStatement.setCursorName(name);
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		return delegateStatement.execute(sql);
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		return delegateStatement.getResultSet();
	}

	@Override
	public int getUpdateCount() throws SQLException {
		return delegateStatement.getUpdateCount();
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		return delegateStatement.getMoreResults();
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		delegateStatement.setFetchDirection(direction);
	}

	@Override
	public int getFetchDirection() throws SQLException {
		return delegateStatement.getFetchDirection();
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		delegateStatement.setFetchSize(rows);
	}

	@Override
	public int getFetchSize() throws SQLException {
		return delegateStatement.getFetchSize();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		return delegateStatement.getResultSetConcurrency();
	}

	@Override
	public int getResultSetType() throws SQLException {
		return delegateStatement.getResultSetType();
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		delegateStatement.addBatch(sql);
	}

	@Override
	public void clearBatch() throws SQLException {
		delegateStatement.clearBatch();
	}

	@Override
	public int[] executeBatch() throws SQLException {
		return delegateStatement.executeBatch();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return connection;
	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		return delegateStatement.getMoreResults(current);
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		return delegateStatement.getGeneratedKeys();
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys)
			throws SQLException {
		return delegateStatement.executeUpdate(sql, autoGeneratedKeys);
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes)
			throws SQLException {
		return delegateStatement.executeUpdate(sql, columnIndexes);
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames)
			throws SQLException {
		return delegateStatement.executeUpdate(sql, columnNames);
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys)
			throws SQLException {
		return delegateStatement.execute(sql, autoGeneratedKeys);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		return delegateStatement.execute(sql, columnIndexes);
	}

	@Override
	public boolean execute(String sql, String[] columnNames)
			throws SQLException {
		return delegateStatement.execute(sql, columnNames);
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		return delegateStatement.getResultSetHoldability();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return delegateStatement.isClosed();
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		delegateStatement.setPoolable(poolable);
	}

	@Override
	public boolean isPoolable() throws SQLException {
		return delegateStatement.isPoolable();
	}

}
