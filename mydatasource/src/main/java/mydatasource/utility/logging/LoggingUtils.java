package mydatasource.utility.logging;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import mydatasource.MyDataSource;

import org.apache.commons.logging.Log;

public class LoggingUtils {

	public static void log(Log logger, MyDataSource myDataSource, String sql,
			long executeTime) {
		LoggingConfig config = LoggingConfig.getConfig(myDataSource);
		boolean isWarning = executeTime >= config.getWarningTime()
				&& config.getWarningTime() > 0;
		if ((isWarning && logger.isWarnEnabled()) || logger.isDebugEnabled()) {
			StringBuilder msg = new StringBuilder();
			msg.append("Sql: ").append(sql).append("\n");
			msg.append("ExecuteTime: ").append(executeTime).append("\n");
			if (config.isLogStackTraces()) {
				printStackTrace(msg);
			}
			if (isWarning) {
				logger.warn(msg);
			} else {
				logger.debug(msg);
			}
		}
	}

	public static void logBatch(Log logger, MyDataSource myDataSource,
			List<String> batchedSqls, long executeTime) {
		LoggingConfig config = LoggingConfig.getConfig(myDataSource);
		boolean isWarning = executeTime >= config.getWarningTime()
				&& config.getWarningTime() > 0;
		if ((isWarning && logger.isWarnEnabled()) || logger.isDebugEnabled()) {
			StringBuilder msg = new StringBuilder();
			for (String sql : batchedSqls) {
				msg.append("Sql: ").append(sql).append("\n");
			}
			msg.append("ExecuteTime: ").append(executeTime).append("\n");
			if (config.isLogStackTraces()) {
				printStackTrace(msg);
			}
			if (isWarning) {
				logger.warn(msg);
			} else {
				logger.debug(msg);
			}
		}
	}

	public static void log(Log logger, MyDataSource myDataSource, String sql,
			Map<Integer, Object> indexedParameters, int maxParameterIndex,
			long executeTime) {
		LoggingConfig config = LoggingConfig.getConfig(myDataSource);
		boolean isWarning = executeTime >= config.getWarningTime()
				&& config.getWarningTime() > 0;
		if ((isWarning && logger.isWarnEnabled()) || logger.isDebugEnabled()) {
			StringBuilder msg = new StringBuilder();
			msg.append("Sql: ").append(sql).append("\n");
			if (config.isLogParameters()) {
				msg.append("Parameters: [");
				for (int i = 1; i <= maxParameterIndex; i++) {
					Object value = indexedParameters.get(i);
					msg.append(formatValue(value));
					if (i < maxParameterIndex) {
						msg.append(", ");
					}
				}
				msg.append("]\n");
			}
			msg.append("ExecuteTime: ").append(executeTime).append("\n");
			if (config.isLogStackTraces()) {
				printStackTrace(msg);
			}
			if (isWarning) {
				logger.warn(msg);
			} else {
				logger.debug(msg);
			}
		}
	}

	public static void logBatch(Log logger, MyDataSource myDataSource,
			List<String> batchedSqls,
			List<Map<Integer, Object>> batchedIndexedParameters,
			List<Integer> batchedMaxParameterIndex, long executeTime) {
		LoggingConfig config = LoggingConfig.getConfig(myDataSource);
		boolean isWarning = executeTime >= config.getWarningTime()
				&& config.getWarningTime() > 0;
		if ((isWarning && logger.isWarnEnabled()) || logger.isDebugEnabled()) {
			StringBuilder msg = new StringBuilder();
			for (int a = 0; a < batchedSqls.size(); a++) {
				String sql = batchedSqls.get(a);
				msg.append("Sql: ").append(sql).append("\n");
				if (config.isLogParameters()) {
					Map<Integer, Object> indexedParameters = batchedIndexedParameters
							.get(a);
					Integer maxParameterIndex = batchedMaxParameterIndex.get(a);
					msg.append("Parameters: [");
					for (int i = 1; i <= maxParameterIndex; i++) {
						Object value = indexedParameters.get(i);
						msg.append(formatValue(value));
						if (i < maxParameterIndex) {
							msg.append(", ");
						}
					}
					msg.append("]\n");
				}
			}
			msg.append("ExecuteTime: ").append(executeTime).append("\n");
			if (config.isLogStackTraces()) {
				printStackTrace(msg);
			}
			if (isWarning) {
				logger.warn(msg);
			} else {
				logger.debug(msg);
			}
		}
	}

	public static void log(Log logger, MyDataSource myDataSource, String sql,
			Map<Integer, Object> indexedParameters, int maxParameterIndex,
			Map<String, Object> namedParameters, long executeTime) {
		LoggingConfig config = LoggingConfig.getConfig(myDataSource);
		boolean isWarning = executeTime >= config.getWarningTime()
				&& config.getWarningTime() > 0;
		if ((isWarning && logger.isWarnEnabled()) || logger.isDebugEnabled()) {
			StringBuilder msg = new StringBuilder();
			msg.append("Sql: ").append(sql).append("\n");
			if (config.isLogParameters()) {
				if (maxParameterIndex > 0) {
					msg.append("Parameters: [");
					for (int i = 1; i <= maxParameterIndex; i++) {
						Object value = indexedParameters.get(i);
						msg.append(formatValue(value));
						if (i < maxParameterIndex) {
							msg.append(", ");
						}
					}
					msg.append("]\n");
				}
				if (!namedParameters.isEmpty()) {
					msg.append("NamedParameters: {");
					for (Map.Entry<String, Object> entry : namedParameters
							.entrySet()) {
						String key = entry.getKey();
						Object value = entry.getValue();
						msg.append(key).append(":").append(formatValue(value))
								.append(", ");
					}
					// remove last ", "
					msg.delete(msg.length() - 2, msg.length());
					msg.append("}\n");
				}
			}
			msg.append("ExecuteTime: ").append(executeTime).append("\n");
			if (config.isLogStackTraces()) {
				printStackTrace(msg);
			}
			if (isWarning) {
				logger.warn(msg);
			} else {
				logger.debug(msg);
			}
		}
	}

	public static void logBatch(Log logger, MyDataSource myDataSource,
			List<String> batchedSqls,
			List<Map<Integer, Object>> batchedIndexedParameters,
			List<Integer> batchedMaxParameterIndex,
			List<Map<String, Object>> batchedNamedParameters, long executeTime) {
		LoggingConfig config = LoggingConfig.getConfig(myDataSource);
		boolean isWarning = executeTime >= config.getWarningTime()
				&& config.getWarningTime() > 0;
		if ((isWarning && logger.isWarnEnabled()) || logger.isDebugEnabled()) {
			StringBuilder msg = new StringBuilder();
			for (int a = 0; a < batchedSqls.size(); a++) {
				String sql = batchedSqls.get(a);
				msg.append("Sql: ").append(sql).append("\n");
				if (config.isLogParameters()) {
					Map<Integer, Object> indexedParameters = batchedIndexedParameters
							.get(a);
					Integer maxParameterIndex = batchedMaxParameterIndex.get(a);
					Map<String, Object> namedParameters = batchedNamedParameters
							.get(a);
					if (maxParameterIndex > 0) {
						msg.append("Parameters: [");
						for (int i = 1; i <= maxParameterIndex; i++) {
							Object value = indexedParameters.get(i);
							msg.append(formatValue(value));
							if (i < maxParameterIndex) {
								msg.append(", ");
							}
						}
						msg.append("]\n");
					}
					if (!namedParameters.isEmpty()) {
						msg.append("NamedParameters: {");
						for (Map.Entry<String, Object> entry : namedParameters
								.entrySet()) {
							String key = entry.getKey();
							Object value = entry.getValue();
							msg.append(key).append(":")
									.append(formatValue(value)).append(", ");
						}
						// remove last ", "
						msg.delete(msg.length() - 2, msg.length());
						msg.append("}\n");
					}
				}
			}
			msg.append("ExecuteTime: ").append(executeTime).append("\n");
			if (config.isLogStackTraces()) {
				printStackTrace(msg);
			}
			if (isWarning) {
				logger.warn(msg);
			} else {
				logger.debug(msg);
			}
		}
	}

	private static void printStackTrace(StringBuilder msg) {
		msg.append("StackTrace: ");
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		// ignore the first two elements which is printStackTrace and log
		for (int i = 2; i < stackTrace.length; i++) {
			StackTraceElement el = stackTrace[i];
			msg.append("\t").append(el.toString()).append("\n");
		}
	}

	private static String formatValue(Object value) {
		if (value == null) {
			return "<null>";
		} else if (value instanceof Timestamp) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
		} else if (value instanceof Time) {
			return new SimpleDateFormat("HH:mm:ss").format(value);
		} else if (value instanceof Date) {
			return new SimpleDateFormat("yyyy-MM-dd").format(value);
		} else {
			return value.toString();
		}
	}

}
