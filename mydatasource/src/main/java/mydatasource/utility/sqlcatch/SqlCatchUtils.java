package mydatasource.utility.sqlcatch;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;

import mydatasource.MyDataSource;

public class SqlCatchUtils {

	public static void matchSql(Log logger, MyDataSource myDataSource, String sql) {
		SqlCatchConfig config = SqlCatchConfig.getConfig(myDataSource);
		for (Pattern pattern : config.getPatterns()) {
			if (pattern.matcher(sql).matches()) {
				StringBuilder msg = new StringBuilder();
				msg.append("Caught SQL: ").append(sql).append("\n");
				printStackTrace(msg);
				logger.info(msg);
			}
		}
	}

	private static void printStackTrace(StringBuilder msg) {
		msg.append("StackTrace:\n");
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (int i = 0; i < stackTrace.length; i++) {
			StackTraceElement el = stackTrace[i];
			if (i == 0 && el.getClassName().equals(Thread.class.getName())) {
				continue;
			}
			if (el.getClassName().equals(SqlCatchUtils.class.getName())) {
				continue;
			}
			String elStr = el.toString();
			msg.append("\t").append(elStr).append("\n");
		}
	}

}
