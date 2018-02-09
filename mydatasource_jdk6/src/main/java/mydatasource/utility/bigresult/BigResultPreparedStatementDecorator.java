package mydatasource.utility.bigresult;

import java.sql.ResultSet;
import java.sql.SQLException;

import mydatasource.PreparedStatementDecorator;

public class BigResultPreparedStatementDecorator extends
		PreparedStatementDecorator {

	protected String lastExecuteSql;

	@Override
	public ResultSet executeQuery() throws SQLException {
		ResultSet rs = super.executeQuery();
		lastExecuteSql = null;
		BigResultResultSetDecorator rsd = new BigResultResultSetDecorator();
		rsd.setSql(sql);
		rsd.setMyDataSource(myDataSource);
		rsd.setDelegateResultSet(rs);
		rsd.init();
		return rsd;
	}

	@Override
	public boolean execute() throws SQLException {
		boolean returnValue = super.execute();
		if (returnValue) {
			lastExecuteSql = null;
		}
		return returnValue;
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		ResultSet rs = super.getResultSet();
		BigResultResultSetDecorator rsd = new BigResultResultSetDecorator();
		if (lastExecuteSql == null) {
			rsd.setSql(sql);
		} else {
			rsd.setSql(lastExecuteSql);
		}
		rsd.setMyDataSource(myDataSource);
		rsd.setDelegateResultSet(rs);
		rsd.init();
		return rsd;
	}

	// ------ copied from BigResultStatementDecorator ------
	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		ResultSet rs = super.executeQuery(sql);
		BigResultResultSetDecorator rsd = new BigResultResultSetDecorator();
		rsd.setSql(sql);
		rsd.setDelegateResultSet(rs);
		return rsd;
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		boolean returnValue = super.execute(sql);
		if (returnValue) {
			lastExecuteSql = sql;
		}
		return returnValue;
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys)
			throws SQLException {
		boolean returnValue = super.execute(sql, autoGeneratedKeys);
		if (returnValue) {
			lastExecuteSql = sql;
		}
		return returnValue;
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		boolean returnValue = super.execute(sql, columnIndexes);
		if (returnValue) {
			lastExecuteSql = sql;
		}
		return returnValue;
	}

	@Override
	public boolean execute(String sql, String[] columnNames)
			throws SQLException {
		boolean returnValue = super.execute(sql, columnNames);
		if (returnValue) {
			lastExecuteSql = sql;
		}
		return returnValue;
	}

}