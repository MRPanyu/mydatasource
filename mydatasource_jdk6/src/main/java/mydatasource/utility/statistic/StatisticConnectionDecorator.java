package mydatasource.utility.statistic;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mydatasource.ConnectionDecorator;

public class StatisticConnectionDecorator extends ConnectionDecorator {

	protected long openTime;
	protected StackTraceElement[] createStatementStackTrace;

	public long getOpenTime() {
		return openTime;
	}

	public StackTraceElement[] getCreateStatementStackTrace() {
		return createStatementStackTrace;
	}

	@Override
	public void afterPropertiesSet() {
		this.openTime = System.currentTimeMillis();
		StatisticManager manager = StatisticManager.getManager(myDataSource);
		manager.openConnection(this);
	}

	@Override
	public Statement decorateStatement(Statement statement) {
		saveStackTrace();
		return super.decorateStatement(statement);
	}

	@Override
	public PreparedStatement decoratePreparedStatement(
			PreparedStatement preparedStatement, String sql) {
		saveStackTrace();
		return super.decoratePreparedStatement(preparedStatement, sql);
	}

	@Override
	public CallableStatement decorateCallableStatement(
			CallableStatement callableStatement, String sql) {
		saveStackTrace();
		return super.decorateCallableStatement(callableStatement, sql);
	}

	@Override
	public void close() throws SQLException {
		StatisticManager manager = StatisticManager.getManager(myDataSource);
		manager.closeConnection(this);
		super.close();
	}

	private void saveStackTrace() {
		if (createStatementStackTrace == null) {
			StackTraceElement[] stackTrace = Thread.currentThread()
					.getStackTrace();
			ArrayList<StackTraceElement> stackTraceList = new ArrayList<StackTraceElement>(
					stackTrace.length);
			for (int i = 0; i < stackTrace.length; i++) {
				StackTraceElement el = stackTrace[i];
				if (i == 0 && el.getClassName().equals(Thread.class.getName())) {
					continue;
				}
				if (el.getClassName().equals(this.getClass().getName())) {
					if (el.getMethodName().equals("saveStackTrace")
							|| el.getMethodName().startsWith("decorate")) {
						continue;
					}
				}
				stackTraceList.add(el);
			}
			createStatementStackTrace = stackTraceList
					.toArray(new StackTraceElement[0]);
		}
	}

}
