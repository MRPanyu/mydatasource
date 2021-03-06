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
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Extended {@link PreparedStatementDecorator} that stores parameters.
 * 
 * @author Panyu
 *
 */
public class PreparedStatementDecoratorEx extends PreparedStatementDecorator {

	protected Map<Integer, Object> indexedParameters = new LinkedHashMap<Integer, Object>();
	protected int maxParameterIndex = 0;

	protected List<Map<Integer, Object>> batchedIndexedParameters = new ArrayList<Map<Integer, Object>>();
	protected List<Integer> batchedMaxParameterIndex = new ArrayList<Integer>();

	public Object getParameter(int parameterIndex) {
		return indexedParameters.get(parameterIndex);
	}

	public Map<Integer, Object> getIndexedParameters() {
		return indexedParameters;
	}

	public int getMaxParameterIndex() {
		return maxParameterIndex;
	}

	public List<Map<Integer, Object>> getBatchedIndexedParameters() {
		return batchedIndexedParameters;
	}

	public List<Integer> getBatchedMaxParameterIndex() {
		return batchedMaxParameterIndex;
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		super.setNull(parameterIndex, sqlType);
		indexedParameters.put(parameterIndex, null);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		super.setBoolean(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		super.setByte(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		super.setShort(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		super.setInt(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		super.setLong(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		super.setFloat(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		super.setDouble(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x)
			throws SQLException {
		super.setBigDecimal(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		super.setString(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		super.setBytes(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		super.setDate(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		super.setTime(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x)
			throws SQLException {
		super.setTimestamp(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		super.setAsciiStream(parameterIndex, x, length);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setUnicodeStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		super.setUnicodeStream(parameterIndex, x, length);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		super.setBinaryStream(parameterIndex, x, length);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void clearParameters() throws SQLException {
		super.clearParameters();
		indexedParameters.clear();
		maxParameterIndex = 0;
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType)
			throws SQLException {
		super.setObject(parameterIndex, x, targetSqlType);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		super.setObject(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length)
			throws SQLException {
		super.setCharacterStream(parameterIndex, reader, length);
		indexedParameters.put(parameterIndex, reader);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		super.setRef(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		super.setBlob(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		super.setClob(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		super.setArray(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal)
			throws SQLException {
		super.setDate(parameterIndex, x, cal);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal)
			throws SQLException {
		super.setTime(parameterIndex, x, cal);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
			throws SQLException {
		super.setTimestamp(parameterIndex, x, cal);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName)
			throws SQLException {
		super.setNull(parameterIndex, sqlType, typeName);
		indexedParameters.put(parameterIndex, null);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		super.setURL(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		super.setRowId(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setNString(int parameterIndex, String value)
			throws SQLException {
		super.setNString(parameterIndex, value);
		indexedParameters.put(parameterIndex, value);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value,
			long length) throws SQLException {
		super.setNCharacterStream(parameterIndex, value, length);
		indexedParameters.put(parameterIndex, value);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		super.setNClob(parameterIndex, value);
		indexedParameters.put(parameterIndex, value);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length)
			throws SQLException {
		super.setClob(parameterIndex, reader, length);
		indexedParameters.put(parameterIndex, reader);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length)
			throws SQLException {
		super.setBlob(parameterIndex, inputStream, length);
		indexedParameters.put(parameterIndex, inputStream);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length)
			throws SQLException {
		super.setNClob(parameterIndex, reader, length);
		indexedParameters.put(parameterIndex, reader);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject)
			throws SQLException {
		super.setSQLXML(parameterIndex, xmlObject);
		indexedParameters.put(parameterIndex, xmlObject);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType,
			int scaleOrLength) throws SQLException {
		super.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length)
			throws SQLException {
		super.setAsciiStream(parameterIndex, x, length);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length)
			throws SQLException {
		super.setBinaryStream(parameterIndex, x, length);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader,
			long length) throws SQLException {
		super.setCharacterStream(parameterIndex, reader, length);
		indexedParameters.put(parameterIndex, reader);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x)
			throws SQLException {
		super.setAsciiStream(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x)
			throws SQLException {
		super.setBinaryStream(parameterIndex, x);
		indexedParameters.put(parameterIndex, x);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader)
			throws SQLException {
		super.setCharacterStream(parameterIndex, reader);
		indexedParameters.put(parameterIndex, reader);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value)
			throws SQLException {
		super.setNCharacterStream(parameterIndex, value);
		indexedParameters.put(parameterIndex, value);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		super.setClob(parameterIndex, reader);
		indexedParameters.put(parameterIndex, reader);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream)
			throws SQLException {
		super.setBlob(parameterIndex, inputStream);
		indexedParameters.put(parameterIndex, inputStream);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		super.setNClob(parameterIndex, reader);
		indexedParameters.put(parameterIndex, reader);
		maxParameterIndex = Math.max(maxParameterIndex, parameterIndex);
	}

	@Override
	public void addBatch() throws SQLException {
		super.addBatch();
		// make a copy
		batchedIndexedParameters.add(new LinkedHashMap<Integer, Object>(
				indexedParameters));
		batchedMaxParameterIndex.add(maxParameterIndex);
	}

	@Override
	public void clearBatch() throws SQLException {
		super.clearBatch();
		batchedIndexedParameters.clear();
		batchedMaxParameterIndex.clear();
	}

}
