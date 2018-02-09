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
			msg.append("ExecuteTime: ").append(executeTime).append("(ms)\n");
			if (config.isLogStackTrace()) {
				printStackTrace(msg, config);
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
			msg.append("ExecuteTime: ").append(executeTime).append("(ms)\n");
			if (config.isLogStackTrace()) {
				printStackTrace(msg, config);
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
			msg.append("ExecuteTime: ").append(executeTime).append("(ms)\n");
			if (config.isLogStackTrace()) {
				printStackTrace(msg, config);
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
			msg.append("ExecuteTime: ").append(executeTime).append("(ms)\n");
			if (config.isLogStackTrace()) {
				printStackTrace(msg, config);
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
			msg.append("ExecuteTime: ").append(executeTime).append("(ms)\n");
			if (config.isLogStackTrace()) {
				printStackTrace(msg, config);
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
			msg.append("ExecuteTime: ").append(executeTime).append("(ms)\n");
			if (config.isLogStackTrace()) {
				printStackTrace(msg, config);
			}
			if (isWarning) {
				logger.warn(msg);
			} else {
				logger.debug(msg);
			}
		}
	}

	private static void printStackTrace(StringBuilder msg, LoggingConfig config) {
		msg.append("StackTrace:\n");
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		int filteredLines = 0;
		String[] include = config.getStackTraceFilterInclude();
		String[] exclude = config.getStackTraceFilterExclude();
		for (int i = 0; i < stackTrace.length; i++) {
			StackTraceElement el = stackTrace[i];
			if (i == 0 && el.getClassName().equals(Thread.class.getName())) {
				continue;
			}
			if (el.getClassName().equals(LoggingUtils.class.getName())) {
				continue;
			}
			String elStr = el.toString();
			if (include.length > 0 || exclude.length > 0) {
				boolean isPrint = true;
				if (include.length > 0) {
					isPrint = false;
					for (int j = 0; j < include.length; j++) {
						if (elStr.contains(include[j])) {
							isPrint = true;
							break;
						}
					}
				}
				if (isPrint && exclude.length > 0) {
					for (int j = 0; j < exclude.length; j++) {
						if (elStr.contains(exclude[j])) {
							isPrint = false;
							break;
						}
					}
				}
				if (!isPrint) {
					filteredLines++;
					continue;
				}
			}
			if (filteredLines > 0) {
				msg.append("\t").append("... ").append(filteredLines)
						.append(" lines filtered ...\n");
			}
			msg.append("\t").append(elStr).append("\n");
			filteredLines = 0;
		}
		if (filteredLines > 0) {
			msg.append("\t").append("... ").append(filteredLines)
					.append(" lines filtered ...\n");
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
