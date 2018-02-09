package mydatasource.utility.bigresult;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;

import mydatasource.ConnectionDecorator;
import mydatasource.utility.bigresult.BigResultCallableStatementDecorator;
import mydatasource.utility.bigresult.BigResultCallableStatementDecoratorEx;
import mydatasource.utility.bigresult.BigResultConfig;
import mydatasource.utility.bigresult.BigResultPreparedStatementDecorator;
import mydatasource.utility.bigresult.BigResultPreparedStatementDecoratorEx;
import mydatasource.utility.bigresult.BigResultStatementDecorator;

public class BigResultConnectionDecorator extends ConnectionDecorator {

	@Override
	public Statement decorateStatement(Statement statement) {
		BigResultStatementDecorator dec = new BigResultStatementDecorator();
		dec.setDelegateStatement(statement);
		dec.setMyDataSource(myDataSource);
		dec.setConnection(fullyDecoratedConnection);
		return dec;
	}

	@Override
	public PreparedStatement decoratePreparedStatement(
			PreparedStatement preparedStatement, String sql) {
		BigResultConfig config = BigResultConfig.getConfig(myDataSource);
		if (config.isLogParameters()) {
			BigResultPreparedStatementDecoratorEx dec = new BigResultPreparedStatementDecoratorEx();
			dec.setDelegatePreparedStatement(preparedStatement);
			dec.setMyDataSource(myDataSource);
			dec.setConnection(fullyDecoratedConnection);
			dec.setSql(sql);
			return dec;
		} else {
			BigResultPreparedStatementDecorator dec = new BigResultPreparedStatementDecorator();
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
		BigResultConfig config = BigResultConfig.getConfig(myDataSource);
		if (config.isLogParameters()) {
			BigResultCallableStatementDecoratorEx dec = new BigResultCallableStatementDecoratorEx();
			dec.setDelegateCallableStatement(callableStatement);
			dec.setMyDataSource(myDataSource);
			dec.setConnection(fullyDecoratedConnection);
			dec.setSql(sql);
			return dec;
		} else {
			BigResultCallableStatementDecorator dec = new BigResultCallableStatementDecorator();
			dec.setDelegateCallableStatement(callableStatement);
			dec.setMyDataSource(myDataSource);
			dec.setConnection(fullyDecoratedConnection);
			dec.setSql(sql);
			return dec;
		}
	}

}
