package mydatasource.utility.sqlcatch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mydatasource.PreparedStatementDecorator;

public class SqlCatchPreparedStatementDecorator extends PreparedStatementDecorator {

	protected Log logger = LogFactory.getLog(this.getClass());

	@Override
	public ResultSet executeQuery() throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.executeQuery();
	}

	@Override
	public int executeUpdate() throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.executeUpdate();
	}

	@Override
	public boolean execute() throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.execute();
	}

	@Override
	public void addBatch() throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		super.addBatch();
	}

	/* -- copied from SqlCatchStatementDecorator -- */
	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.executeQuery(sql);
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.executeUpdate(sql);
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.execute(sql);
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		super.addBatch(sql);
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.executeUpdate(sql, autoGeneratedKeys);
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.executeUpdate(sql, columnIndexes);
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.executeUpdate(sql, columnNames);
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.execute(sql, autoGeneratedKeys);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.execute(sql, columnIndexes);
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		SqlCatchUtils.matchSql(logger, myDataSource, sql);
		return super.execute(sql, columnNames);
	}
}
