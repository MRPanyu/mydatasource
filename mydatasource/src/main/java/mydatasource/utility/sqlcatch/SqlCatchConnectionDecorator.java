package mydatasource.utility.sqlcatch;

import java.sql.PreparedStatement;
import java.sql.Statement;

import mydatasource.ConnectionDecorator;

public class SqlCatchConnectionDecorator extends ConnectionDecorator {

	@Override
	public Statement decorateStatement(Statement statement) {
		SqlCatchStatementDecorator dec = new SqlCatchStatementDecorator();
		dec.setDelegateStatement(statement);
		dec.setMyDataSource(myDataSource);
		dec.setConnection(fullyDecoratedConnection);
		return dec;
	}

	@Override
	public PreparedStatement decoratePreparedStatement(PreparedStatement preparedStatement, String sql) {
		SqlCatchPreparedStatementDecorator dec = new SqlCatchPreparedStatementDecorator();
		dec.setDelegatePreparedStatement(preparedStatement);
		dec.setMyDataSource(myDataSource);
		dec.setConnection(fullyDecoratedConnection);
		dec.setSql(sql);
		return dec;
	}
}
