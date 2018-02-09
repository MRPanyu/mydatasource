package mydatasource;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Decorator base class for {@link PreparedStatement}. Any utility decorators
 * for <code>PreparedStatement</code> should be subclass of this.
 * 
 * @author Panyu
 *
 */
public class PreparedStatementDecorator extends StatementDecorator implements
		PreparedStatement {

	protected PreparedStatement delegatePreparedStatement;
	protected String sql;
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * Delegated statement object, which is either the raw statement or another
	 * statement decorator in the chain.
	 */
	public PreparedStatement getDelegatePreparedStatement() {
		return delegatePreparedStatement;
	}

	/**
	 * Delegated statement object, which is either the raw statement or another
	 * statement decorator in the chain.
	 */
	public void setDelegatePreparedStatement(
			PreparedStatement delegatePreparedStatement) {
		this.delegatePreparedStatement = delegatePreparedStatement;
		this.delegateStatement = delegatePreparedStatement;
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		return delegatePreparedStatement.executeQuery();
	}

	@Override
	public int executeUpdate() throws SQLException {
		return delegatePreparedStatement.executeUpdate();
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		delegatePreparedStatement.setNull(parameterIndex, sqlType);
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		delegatePreparedStatement.setBoolean(parameterIndex, x);
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		delegatePreparedStatement.setByte(parameterIndex, x);
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		delegatePreparedStatement.setShort(parameterIndex, x);
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		delegatePreparedStatement.setInt(parameterIndex, x);
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		delegatePreparedStatement.setLong(parameterIndex, x);
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		delegatePreparedStatement.setFloat(parameterIndex, x);
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		delegatePreparedStatement.setDouble(parameterIndex, x);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x)
			throws SQLException {
		delegatePreparedStatement.setBigDecimal(parameterIndex, x);
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		delegatePreparedStatement.setString(parameterIndex, x);
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		delegatePreparedStatement.setBytes(parameterIndex, x);
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		delegatePreparedStatement.setDate(parameterIndex, x);
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		delegatePreparedStatement.setTime(parameterIndex, x);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x)
			throws SQLException {
		delegatePreparedStatement.setTimestamp(parameterIndex, x);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		delegatePreparedStatement.setAsciiStream(parameterIndex, x, length);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void setUnicodeStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		delegatePreparedStatement.setUnicodeStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		delegatePreparedStatement.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void clearParameters() throws SQLException {
		delegatePreparedStatement.clearParameters();
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType)
			throws SQLException {
		delegatePreparedStatement.setObject(parameterIndex, x, targetSqlType);
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		delegatePreparedStatement.setObject(parameterIndex, x);
	}

	@Override
	public boolean execute() throws SQLException {
		return delegatePreparedStatement.execute();
	}

	@Override
	public void addBatch() throws SQLException {
		delegatePreparedStatement.addBatch();
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length)
			throws SQLException {
		delegatePreparedStatement.setCharacterStream(parameterIndex, reader,
				length);
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		delegatePreparedStatement.setRef(parameterIndex, x);
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		delegatePreparedStatement.setBlob(parameterIndex, x);
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		delegatePreparedStatement.setClob(parameterIndex, x);
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		delegatePreparedStatement.setArray(parameterIndex, x);
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		return delegatePreparedStatement.getMetaData();
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal)
			throws SQLException {
		delegatePreparedStatement.setDate(parameterIndex, x, cal);
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal)
			throws SQLException {
		delegatePreparedStatement.setTime(parameterIndex, x, cal);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
			throws SQLException {
		delegatePreparedStatement.setTimestamp(parameterIndex, x, cal);
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName)
			throws SQLException {
		delegatePreparedStatement.setNull(parameterIndex, sqlType, typeName);
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		delegatePreparedStatement.setURL(parameterIndex, x);
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		return delegatePreparedStatement.getParameterMetaData();
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		delegatePreparedStatement.setRowId(parameterIndex, x);
	}

	@Override
	public void setNString(int parameterIndex, String value)
			throws SQLException {
		delegatePreparedStatement.setNString(parameterIndex, value);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value,
			long length) throws SQLException {
		delegatePreparedStatement.setNCharacterStream(parameterIndex, value,
				length);
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		delegatePreparedStatement.setNClob(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length)
			throws SQLException {
		delegatePreparedStatement.setClob(parameterIndex, reader, length);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length)
			throws SQLException {
		delegatePreparedStatement.setBlob(parameterIndex, inputStream, length);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length)
			throws SQLException {
		delegatePreparedStatement.setNClob(parameterIndex, reader, length);
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject)
			throws SQLException {
		delegatePreparedStatement.setSQLXML(parameterIndex, xmlObject);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType,
			int scaleOrLength) throws SQLException {
		delegatePreparedStatement.setObject(parameterIndex, x, targetSqlType,
				scaleOrLength);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length)
			throws SQLException {
		delegatePreparedStatement.setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length)
			throws SQLException {
		delegatePreparedStatement.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader,
			long length) throws SQLException {
		delegatePreparedStatement.setCharacterStream(parameterIndex, reader,
				length);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x)
			throws SQLException {
		delegatePreparedStatement.setAsciiStream(parameterIndex, x);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x)
			throws SQLException {
		delegatePreparedStatement.setBinaryStream(parameterIndex, x);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader)
			throws SQLException {
		delegatePreparedStatement.setCharacterStream(parameterIndex, reader);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value)
			throws SQLException {
		delegatePreparedStatement.setNCharacterStream(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		delegatePreparedStatement.setClob(parameterIndex, reader);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream)
			throws SQLException {
		delegatePreparedStatement.setBlob(parameterIndex, inputStream);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		delegatePreparedStatement.setNClob(parameterIndex, reader);
	}

}
