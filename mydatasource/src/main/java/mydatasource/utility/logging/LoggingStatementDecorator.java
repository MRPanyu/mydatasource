package mydatasource.utility.logging;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mydatasource.StatementDecorator;

public class LoggingStatementDecorator extends StatementDecorator {

	protected Log logger = LogFactory.getLog(this.getClass());

	protected List<String> batchedSqls = new ArrayList<String>();

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		long begin = System.currentTimeMillis();
		ResultSet rs = super.executeQuery(sql);
		long executeTime = System.currentTimeMillis() - begin;
		LoggingUtils.log(logger, myDataSource, sql, executeTime);
		return rs;
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		long begin = System.currentTimeMillis();
		int returnValue = super.executeUpdate(sql);
		long executeTime = System.currentTimeMillis() - begin;
		LoggingUtils.log(logger, myDataSource, sql, executeTime);
		return returnValue;
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		long begin = System.currentTimeMillis();
		boolean returnValue = super.execute(sql);
		long executeTime = System.currentTimeMillis() - begin;
		LoggingUtils.log(logger, myDataSource, sql, executeTime);
		return returnValue;
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		super.addBatch(sql);
		batchedSqls.add(sql);
	}

	@Override
	public int[] executeBatch() throws SQLException {
		long begin = System.currentTimeMillis();
		int[] returnValue = super.executeBatch();
		long executeTime = System.currentTimeMillis() - begin;
		LoggingUtils.logBatch(logger, myDataSource, batchedSqls, executeTime);
		return returnValue;
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys)
			throws SQLException {
		long begin = System.currentTimeMillis();
		int returnValue = super.executeUpdate(sql, autoGeneratedKeys);
		long executeTime = System.currentTimeMillis() - begin;
		LoggingUtils.log(logger, myDataSource, sql, executeTime);
		return returnValue;
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes)
			throws SQLException {
		long begin = System.currentTimeMillis();
		int returnValue = super.executeUpdate(sql, columnIndexes);
		long executeTime = System.currentTimeMillis() - begin;
		LoggingUtils.log(logger, myDataSource, sql, executeTime);
		return returnValue;
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames)
			throws SQLException {
		long begin = System.currentTimeMillis();
		int returnValue = super.executeUpdate(sql, columnNames);
		long executeTime = System.currentTimeMillis() - begin;
		LoggingUtils.log(logger, myDataSource, sql, executeTime);
		return returnValue;
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys)
			throws SQLException {
		long begin = System.currentTimeMillis();
		boolean returnValue = super.execute(sql, autoGeneratedKeys);
		long executeTime = System.currentTimeMillis() - begin;
		LoggingUtils.log(logger, myDataSource, sql, executeTime);
		return returnValue;
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		long begin = System.currentTimeMillis();
		boolean returnValue = super.execute(sql, columnIndexes);
		long executeTime = System.currentTimeMillis() - begin;
		LoggingUtils.log(logger, myDataSource, sql, executeTime);
		return returnValue;
	}

	@Override
	public boolean execute(String sql, String[] columnNames)
			throws SQLException {
		long begin = System.currentTimeMillis();
		boolean returnValue = super.execute(sql, columnNames);
		long executeTime = System.currentTimeMillis() - begin;
		LoggingUtils.log(logger, myDataSource, sql, executeTime);
		return returnValue;
	}

}
