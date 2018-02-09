package mydatasource;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 * Decorator base class for {@link CallableStatement}. Any utility decorators
 * for <code>CallableStatement</code> should be subclass of this.
 * 
 * @author Panyu
 *
 */
public class CallableStatementDecorator extends PreparedStatementDecorator
		implements CallableStatement {

	protected CallableStatement delegateCallableStatement;

	/**
	 * Delegated statement object, which is either the raw statement or another
	 * statement decorator in the chain.
	 */
	public CallableStatement getDelegateCallableStatement() {
		return delegateCallableStatement;
	}

	/**
	 * Delegated statement object, which is either the raw statement or another
	 * statement decorator in the chain.
	 */
	public void setDelegateCallableStatement(
			CallableStatement delegateCallableStatement) {
		this.delegateCallableStatement = delegateCallableStatement;
		this.delegatePreparedStatement = delegateCallableStatement;
		this.delegateStatement = delegateCallableStatement;
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType)
			throws SQLException {
		delegateCallableStatement.registerOutParameter(parameterIndex, sqlType);
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, int scale)
			throws SQLException {
		delegateCallableStatement.registerOutParameter(parameterIndex, sqlType,
				scale);
	}

	@Override
	public boolean wasNull() throws SQLException {
		return delegateCallableStatement.wasNull();
	}

	@Override
	public String getString(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getString(parameterIndex);
	}

	@Override
	public boolean getBoolean(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getBoolean(parameterIndex);
	}

	@Override
	public byte getByte(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getByte(parameterIndex);
	}

	@Override
	public short getShort(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getShort(parameterIndex);
	}

	@Override
	public int getInt(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getInt(parameterIndex);
	}

	@Override
	public long getLong(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getLong(parameterIndex);
	}

	@Override
	public float getFloat(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getFloat(parameterIndex);
	}

	@Override
	public double getDouble(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getDouble(parameterIndex);
	}

	@Override
	@SuppressWarnings("deprecation")
	public BigDecimal getBigDecimal(int parameterIndex, int scale)
			throws SQLException {
		return delegateCallableStatement.getBigDecimal(parameterIndex, scale);
	}

	@Override
	public byte[] getBytes(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getBytes(parameterIndex);
	}

	@Override
	public Date getDate(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getDate(parameterIndex);
	}

	@Override
	public Time getTime(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getTime(parameterIndex);
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getTimestamp(parameterIndex);
	}

	@Override
	public Object getObject(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getObject(parameterIndex);
	}

	@Override
	public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getBigDecimal(parameterIndex);
	}

	@Override
	public Object getObject(int parameterIndex, Map<String, Class<?>> map)
			throws SQLException {
		return delegateCallableStatement.getObject(parameterIndex, map);
	}

	@Override
	public Ref getRef(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getRef(parameterIndex);
	}

	@Override
	public Blob getBlob(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getBlob(parameterIndex);
	}

	@Override
	public Clob getClob(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getClob(parameterIndex);
	}

	@Override
	public Array getArray(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getArray(parameterIndex);
	}

	@Override
	public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
		return delegateCallableStatement.getDate(parameterIndex, cal);
	}

	@Override
	public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
		return delegateCallableStatement.getTime(parameterIndex, cal);
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex, Calendar cal)
			throws SQLException {
		return delegateCallableStatement.getTimestamp(parameterIndex, cal);
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType,
			String typeName) throws SQLException {
		delegateCallableStatement.registerOutParameter(parameterIndex, sqlType,
				typeName);
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType)
			throws SQLException {
		delegateCallableStatement.registerOutParameter(parameterName, sqlType);
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType,
			int scale) throws SQLException {
		delegateCallableStatement.registerOutParameter(parameterName, sqlType,
				scale);
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType,
			String typeName) throws SQLException {
		delegateCallableStatement.registerOutParameter(parameterName, sqlType,
				typeName);
	}

	@Override
	public URL getURL(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getURL(parameterIndex);
	}

	@Override
	public void setURL(String parameterName, URL val) throws SQLException {
		delegateCallableStatement.setURL(parameterName, val);
	}

	@Override
	public void setNull(String parameterName, int sqlType) throws SQLException {
		delegateCallableStatement.setNull(parameterName, sqlType);
	}

	@Override
	public void setBoolean(String parameterName, boolean x) throws SQLException {
		delegateCallableStatement.setBoolean(parameterName, x);
	}

	@Override
	public void setByte(String parameterName, byte x) throws SQLException {
		delegateCallableStatement.setByte(parameterName, x);
	}

	@Override
	public void setShort(String parameterName, short x) throws SQLException {
		delegateCallableStatement.setShort(parameterName, x);
	}

	@Override
	public void setInt(String parameterName, int x) throws SQLException {
		delegateCallableStatement.setInt(parameterName, x);
	}

	@Override
	public void setLong(String parameterName, long x) throws SQLException {
		delegateCallableStatement.setLong(parameterName, x);
	}

	@Override
	public void setFloat(String parameterName, float x) throws SQLException {
		delegateCallableStatement.setFloat(parameterName, x);
	}

	@Override
	public void setDouble(String parameterName, double x) throws SQLException {
		delegateCallableStatement.setDouble(parameterName, x);
	}

	@Override
	public void setBigDecimal(String parameterName, BigDecimal x)
			throws SQLException {
		delegateCallableStatement.setBigDecimal(parameterName, x);
	}

	@Override
	public void setString(String parameterName, String x) throws SQLException {
		delegateCallableStatement.setString(parameterName, x);
	}

	@Override
	public void setBytes(String parameterName, byte[] x) throws SQLException {
		delegateCallableStatement.setBytes(parameterName, x);
	}

	@Override
	public void setDate(String parameterName, Date x) throws SQLException {
		delegateCallableStatement.setDate(parameterName, x);
	}

	@Override
	public void setTime(String parameterName, Time x) throws SQLException {
		delegateCallableStatement.setTime(parameterName, x);
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x)
			throws SQLException {
		delegateCallableStatement.setTimestamp(parameterName, x);
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, int length)
			throws SQLException {
		delegateCallableStatement.setAsciiStream(parameterName, x, length);
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, int length)
			throws SQLException {
		delegateCallableStatement.setBinaryStream(parameterName, x, length);
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType,
			int scale) throws SQLException {
		delegateCallableStatement.setObject(parameterName, x, targetSqlType,
				scale);
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType)
			throws SQLException {
		delegateCallableStatement.setObject(parameterName, x, targetSqlType);
	}

	@Override
	public void setObject(String parameterName, Object x) throws SQLException {
		delegateCallableStatement.setObject(parameterName, x);
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader,
			int length) throws SQLException {
		delegateCallableStatement.setCharacterStream(parameterName, reader,
				length);
	}

	@Override
	public void setDate(String parameterName, Date x, Calendar cal)
			throws SQLException {
		delegateCallableStatement.setDate(parameterName, x, cal);
	}

	@Override
	public void setTime(String parameterName, Time x, Calendar cal)
			throws SQLException {
		delegateCallableStatement.setTime(parameterName, x, cal);
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x, Calendar cal)
			throws SQLException {
		delegateCallableStatement.setTimestamp(parameterName, x, cal);
	}

	@Override
	public void setNull(String parameterName, int sqlType, String typeName)
			throws SQLException {
		delegateCallableStatement.setNull(parameterName, sqlType, typeName);
	}

	@Override
	public String getString(String parameterName) throws SQLException {
		return delegateCallableStatement.getString(parameterName);
	}

	@Override
	public boolean getBoolean(String parameterName) throws SQLException {
		return delegateCallableStatement.getBoolean(parameterName);
	}

	@Override
	public byte getByte(String parameterName) throws SQLException {
		return delegateCallableStatement.getByte(parameterName);
	}

	@Override
	public short getShort(String parameterName) throws SQLException {
		return delegateCallableStatement.getShort(parameterName);
	}

	@Override
	public int getInt(String parameterName) throws SQLException {
		return delegateCallableStatement.getInt(parameterName);
	}

	@Override
	public long getLong(String parameterName) throws SQLException {
		return delegateCallableStatement.getLong(parameterName);
	}

	@Override
	public float getFloat(String parameterName) throws SQLException {
		return delegateCallableStatement.getFloat(parameterName);
	}

	@Override
	public double getDouble(String parameterName) throws SQLException {
		return delegateCallableStatement.getDouble(parameterName);
	}

	@Override
	public byte[] getBytes(String parameterName) throws SQLException {
		return delegateCallableStatement.getBytes(parameterName);
	}

	@Override
	public Date getDate(String parameterName) throws SQLException {
		return delegateCallableStatement.getDate(parameterName);
	}

	@Override
	public Time getTime(String parameterName) throws SQLException {
		return delegateCallableStatement.getTime(parameterName);
	}

	@Override
	public Timestamp getTimestamp(String parameterName) throws SQLException {
		return delegateCallableStatement.getTimestamp(parameterName);
	}

	@Override
	public Object getObject(String parameterName) throws SQLException {
		return delegateCallableStatement.getObject(parameterName);
	}

	@Override
	public BigDecimal getBigDecimal(String parameterName) throws SQLException {
		return delegateCallableStatement.getBigDecimal(parameterName);
	}

	@Override
	public Object getObject(String parameterName, Map<String, Class<?>> map)
			throws SQLException {
		return delegateCallableStatement.getObject(parameterName, map);
	}

	@Override
	public Ref getRef(String parameterName) throws SQLException {
		return delegateCallableStatement.getRef(parameterName);
	}

	@Override
	public Blob getBlob(String parameterName) throws SQLException {
		return delegateCallableStatement.getBlob(parameterName);
	}

	@Override
	public Clob getClob(String parameterName) throws SQLException {
		return delegateCallableStatement.getClob(parameterName);
	}

	@Override
	public Array getArray(String parameterName) throws SQLException {
		return delegateCallableStatement.getArray(parameterName);
	}

	@Override
	public Date getDate(String parameterName, Calendar cal) throws SQLException {
		return delegateCallableStatement.getDate(parameterName, cal);
	}

	@Override
	public Time getTime(String parameterName, Calendar cal) throws SQLException {
		return delegateCallableStatement.getTime(parameterName, cal);
	}

	@Override
	public Timestamp getTimestamp(String parameterName, Calendar cal)
			throws SQLException {
		return delegateCallableStatement.getTimestamp(parameterName, cal);
	}

	@Override
	public URL getURL(String parameterName) throws SQLException {
		return delegateCallableStatement.getURL(parameterName);
	}

	@Override
	public RowId getRowId(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getRowId(parameterIndex);
	}

	@Override
	public RowId getRowId(String parameterName) throws SQLException {
		return delegateCallableStatement.getRowId(parameterName);
	}

	@Override
	public void setRowId(String parameterName, RowId x) throws SQLException {
		delegateCallableStatement.setRowId(parameterName, x);
	}

	@Override
	public void setNString(String parameterName, String value)
			throws SQLException {
		delegateCallableStatement.setNString(parameterName, value);
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value,
			long length) throws SQLException {
		delegateCallableStatement.setNCharacterStream(parameterName, value,
				length);
	}

	@Override
	public void setNClob(String parameterName, NClob value) throws SQLException {
		delegateCallableStatement.setNClob(parameterName, value);
	}

	@Override
	public void setClob(String parameterName, Reader reader, long length)
			throws SQLException {
		delegateCallableStatement.setClob(parameterName, reader, length);
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream,
			long length) throws SQLException {
		delegateCallableStatement.setBlob(parameterName, inputStream, length);
	}

	@Override
	public void setNClob(String parameterName, Reader reader, long length)
			throws SQLException {
		delegateCallableStatement.setNClob(parameterName, reader, length);
	}

	@Override
	public NClob getNClob(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getNClob(parameterIndex);
	}

	@Override
	public NClob getNClob(String parameterName) throws SQLException {
		return delegateCallableStatement.getNClob(parameterName);
	}

	@Override
	public void setSQLXML(String parameterName, SQLXML xmlObject)
			throws SQLException {
		delegateCallableStatement.setSQLXML(parameterName, xmlObject);
	}

	@Override
	public SQLXML getSQLXML(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getSQLXML(parameterIndex);
	}

	@Override
	public SQLXML getSQLXML(String parameterName) throws SQLException {
		return delegateCallableStatement.getSQLXML(parameterName);
	}

	@Override
	public String getNString(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getNString(parameterIndex);
	}

	@Override
	public String getNString(String parameterName) throws SQLException {
		return delegateCallableStatement.getNString(parameterName);
	}

	@Override
	public Reader getNCharacterStream(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getNCharacterStream(parameterIndex);
	}

	@Override
	public Reader getNCharacterStream(String parameterName) throws SQLException {
		return delegateCallableStatement.getNCharacterStream(parameterName);
	}

	@Override
	public Reader getCharacterStream(int parameterIndex) throws SQLException {
		return delegateCallableStatement.getCharacterStream(parameterIndex);
	}

	@Override
	public Reader getCharacterStream(String parameterName) throws SQLException {
		return delegateCallableStatement.getCharacterStream(parameterName);
	}

	@Override
	public void setBlob(String parameterName, Blob x) throws SQLException {
		delegateCallableStatement.setBlob(parameterName, x);
	}

	@Override
	public void setClob(String parameterName, Clob x) throws SQLException {
		delegateCallableStatement.setClob(parameterName, x);
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, long length)
			throws SQLException {
		delegateCallableStatement.setAsciiStream(parameterName, x, length);
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, long length)
			throws SQLException {
		delegateCallableStatement.setBinaryStream(parameterName, x, length);
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader,
			long length) throws SQLException {
		delegateCallableStatement.setCharacterStream(parameterName, reader,
				length);
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x)
			throws SQLException {
		delegateCallableStatement.setAsciiStream(parameterName, x);
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x)
			throws SQLException {
		delegateCallableStatement.setBinaryStream(parameterName, x);
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader)
			throws SQLException {
		delegateCallableStatement.setCharacterStream(parameterName, reader);
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value)
			throws SQLException {
		delegateCallableStatement.setNCharacterStream(parameterName, value);
	}

	@Override
	public void setClob(String parameterName, Reader reader)
			throws SQLException {
		delegateCallableStatement.setClob(parameterName, reader);
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream)
			throws SQLException {
		delegateCallableStatement.setBlob(parameterName, inputStream);
	}

	@Override
	public void setNClob(String parameterName, Reader reader)
			throws SQLException {
		delegateCallableStatement.setNClob(parameterName, reader);
	}

	@Override
	public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
		return delegateCallableStatement.getObject(parameterIndex, type);
	}

	@Override
	public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
		return delegateCallableStatement.getObject(parameterName, type);
	}

}
