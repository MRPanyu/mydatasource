package mydatasource.utility.logging;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;

import mydatasource.ConnectionDecorator;

public class LoggingConnectionDecorator extends ConnectionDecorator {

	@Override
	public Statement decorateStatement(Statement statement) {
		LoggingStatementDecorator dec = new LoggingStatementDecorator();
		dec.setDelegateStatement(statement);
		dec.setMyDataSource(myDataSource);
		dec.setConnection(fullyDecoratedConnection);
		return dec;
	}

	@Override
	public PreparedStatement decoratePreparedStatement(
			PreparedStatement preparedStatement, String sql) {
		LoggingConfig config = LoggingConfig.getConfig(myDataSource);
		if (config.isLogParameters()) {
			LoggingPreparedStatementDecorator dec = new LoggingPreparedStatementDecorator();
			dec.setDelegatePreparedStatement(preparedStatement);
			dec.setMyDataSource(myDataSource);
			dec.setConnection(fullyDecoratedConnection);
			dec.setSql(sql);
			return dec;
		} else {
			LoggingPreparedStatementDecoratorEx dec = new LoggingPreparedStatementDecoratorEx();
			dec.setDelegatePreparedStatement(preparedStatement);
			dec.setMyDataSource(myDataSource);
			dec.setConnection(fullyDecoratedConnection);
			dec.setSql(sql);
			return dec;
		}
	}

	@Override
	public CallableStatement decorateCallableStatement(
			CallableStatement callableStatement, String sql) {
		LoggingConfig config = LoggingConfig.getConfig(myDataSource);
		if (config.isLogParameters()) {
			LoggingCallableStatementDecorator dec = new LoggingCallableStatementDecorator();
			dec.setDelegateCallableStatement(callableStatement);
			dec.setMyDataSource(myDataSource);
			dec.setConnection(fullyDecoratedConnection);
			dec.setSql(sql);
			return dec;
		} else {
			LoggingCallableStatementDecoratorEx dec = new LoggingCallableStatementDecoratorEx();
			dec.setDelegateCallableStatement(callableStatement);
			dec.setMyDataSource(myDataSource);
			dec.setConnection(fullyDecoratedConnection);
			dec.setSql(sql);
			return dec;
		}
	}

}
