package mydatasource;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;

/**
 * Decorator base class for {@link Connection}. Any utility decorators for
 * <code>Connection</code> should be subclass of this.
 * 
 * @author Panyu
 *
 */
public class ConnectionDecorator implements Connection {

	protected Connection delegateConnection;
	protected Connection rawConnection;
	protected Connection fullyDecoratedConnection;
	protected MyDataSource myDataSource;

	/**
	 * Delegated connection object, which is either the raw connection or
	 * another connection decorator in the chain.
	 */
	public Connection getDelegateConnection() {
		return delegateConnection;
	}

	/**
	 * Delegated connection object, which is either the raw connection or
	 * another connection decorator in the chain.
	 */
	public void setDelegateConnection(Connection delegateConnection) {
		this.delegateConnection = delegateConnection;
	}

	/**
	 * The undecorated raw connection.
	 */
	public Connection getRawConnection() {
		return rawConnection;
	}

	/**
	 * The undecorated raw connection.
	 */
	public void setRawConnection(Connection rawConnection) {
		this.rawConnection = rawConnection;
	}

	/**
	 * The fully decorated connection.
	 */
	public Connection getFullyDecoratedConnection() {
		return fullyDecoratedConnection;
	}

	/**
	 * The fully decorated connection.
	 */
	public void setFullyDecoratedConnection(Connection fullyDecoratedConnection) {
		this.fullyDecoratedConnection = fullyDecoratedConnection;
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

	/**
	 * Override this method to use custom {@link StatementDecorator} for
	 * <code>createStatement</code> methods.
	 * <p>
	 * Remember to <code>setConnection</code> on the decorator object using the
	 * <code>fullyDecoratedConnection</code> for consistent behavior.
	 */
	public Statement decorateStatement(Statement statement) {
		StatementDecorator dec = new StatementDecorator();
		dec.setDelegateStatement(statement);
		dec.setMyDataSource(myDataSource);
		dec.setConnection(fullyDecoratedConnection);
		return dec;
	}

	/**
	 * Override this method to use custom {@link PreparedStatementDecorator} for
	 * <code>prepareStatement</code> methods.
	 * <p>
	 * Remember to <code>setConnection</code> on the decorator object using the
	 * <code>fullyDecoratedConnection</code> for consistent behavior.
	 */
	public PreparedStatement decoratePreparedStatement(
			PreparedStatement preparedStatement) {
		PreparedStatementDecorator dec = new PreparedStatementDecorator();
		dec.setDelegatePreparedStatement(preparedStatement);
		dec.setMyDataSource(myDataSource);
		dec.setConnection(fullyDecoratedConnection);
		return dec;
	}

	/**
	 * Override this method to use custom {@link CallableStatementDecorator} for
	 * <code>prepareCall</code> methods.
	 * <p>
	 * Remember to <code>setConnection</code> on the decorator object using the
	 * <code>fullyDecoratedConnection</code> for consistent behavior.
	 */
	public CallableStatement decorateCallableStatement(
			CallableStatement callableStatement) {
		CallableStatementDecorator dec = new CallableStatementDecorator();
		dec.setDelegateCallableStatement(callableStatement);
		dec.setMyDataSource(myDataSource);
		dec.setConnection(fullyDecoratedConnection);
		return dec;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return delegateConnection.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return delegateConnection.isWrapperFor(iface);
	}

	@Override
	public Statement createStatement() throws SQLException {
		return decorateStatement(delegateConnection.createStatement());
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return decoratePreparedStatement(delegateConnection
				.prepareStatement(sql));
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return decorateCallableStatement(delegateConnection.prepareCall(sql));
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return delegateConnection.nativeSQL(sql);
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		delegateConnection.setAutoCommit(autoCommit);
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return delegateConnection.getAutoCommit();
	}

	@Override
	public void commit() throws SQLException {
		delegateConnection.commit();
	}

	@Override
	public void rollback() throws SQLException {
		delegateConnection.rollback();
	}

	@Override
	public void close() throws SQLException {
		delegateConnection.close();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return delegateConnection.isClosed();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return delegateConnection.getMetaData();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		delegateConnection.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return delegateConnection.isReadOnly();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		delegateConnection.setCatalog(catalog);
	}

	@Override
	public String getCatalog() throws SQLException {
		return delegateConnection.getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		delegateConnection.setTransactionIsolation(level);
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return delegateConnection.getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return delegateConnection.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		delegateConnection.clearWarnings();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return decorateStatement(delegateConnection.createStatement(
				resultSetType, resultSetConcurrency));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return decoratePreparedStatement(delegateConnection.prepareStatement(
				sql, resultSetType, resultSetConcurrency));
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return decorateCallableStatement(delegateConnection.prepareCall(sql,
				resultSetType, resultSetConcurrency));
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return delegateConnection.getTypeMap();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		delegateConnection.setTypeMap(map);
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		delegateConnection.setHoldability(holdability);
	}

	@Override
	public int getHoldability() throws SQLException {
		return delegateConnection.getHoldability();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return delegateConnection.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return delegateConnection.setSavepoint(name);
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		delegateConnection.rollback(savepoint);
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		delegateConnection.releaseSavepoint(savepoint);
	}

	@Override
	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return decorateStatement(delegateConnection.createStatement(
				resultSetType, resultSetConcurrency, resultSetHoldability));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return decoratePreparedStatement(delegateConnection.prepareStatement(
				sql, resultSetType, resultSetConcurrency, resultSetHoldability));
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return decorateCallableStatement(delegateConnection.prepareCall(sql,
				resultSetType, resultSetConcurrency, resultSetHoldability));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		return decoratePreparedStatement(delegateConnection.prepareStatement(
				sql, autoGeneratedKeys));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		return decoratePreparedStatement(delegateConnection.prepareStatement(
				sql, columnIndexes));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		return decoratePreparedStatement(delegateConnection.prepareStatement(
				sql, columnNames));
	}

	@Override
	public Clob createClob() throws SQLException {
		return delegateConnection.createClob();
	}

	@Override
	public Blob createBlob() throws SQLException {
		return delegateConnection.createBlob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		return delegateConnection.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return delegateConnection.createSQLXML();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return delegateConnection.isValid(timeout);
	}

	@Override
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		delegateConnection.setClientInfo(name, value);
	}

	@Override
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		delegateConnection.setClientInfo(properties);
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return delegateConnection.getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return delegateConnection.getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		return delegateConnection.createArrayOf(typeName, elements);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		return delegateConnection.createStruct(typeName, attributes);
	}

}
