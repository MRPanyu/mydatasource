package mydatasource.utility.logging;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mydatasource.MyDataSource;

public class LoggingConfig {

	public static final String CONFIG_WARNING_TIME = "logging.warningTime";
	public static final String CONFIG_LOG_PARAMETERS = "logging.logParameters";
	public static final String CONFIG_LOG_STACKTRACE = "logging.logStackTrace";
	public static final String CONFIG_STACKTRACE_FILTER = "logging.stackTraceFilter";

	public static LoggingConfig getConfig(MyDataSource myDataSource) {
		Map<String, Object> storage = myDataSource
				.getDataStorage(LoggingConfig.class);
		LoggingConfig config = (LoggingConfig) storage.get("config");
		if (config == null) {
			synchronized (storage) {
				config = (LoggingConfig) storage.get("config");
				if (config == null) {
					config = new LoggingConfig();
					String warningTimeStr = myDataSource.getConfigs()
							.getProperty(CONFIG_WARNING_TIME);
					if (warningTimeStr != null
							&& warningTimeStr.trim().length() > 0) {
						config.setWarningTime(Long.parseLong(warningTimeStr));
					}
					String logParametersStr = myDataSource.getConfigs()
							.getProperty(CONFIG_LOG_PARAMETERS);
					if (logParametersStr != null
							&& ("true"
									.equalsIgnoreCase(logParametersStr.trim()) || "1"
									.equals(logParametersStr.trim()))) {
						config.setLogParameters(true);
					}
					String logStackTraceStr = myDataSource.getConfigs()
							.getProperty(CONFIG_LOG_STACKTRACE);
					if (logStackTraceStr != null
							&& ("true"
									.equalsIgnoreCase(logStackTraceStr.trim()) || "1"
									.equals(logStackTraceStr.trim()))) {
						config.setLogStackTrace(true);
					}
					String stackTraceFilterStr = myDataSource.getConfigs()
							.getProperty(CONFIG_STACKTRACE_FILTER);
					if (stackTraceFilterStr != null
							&& stackTraceFilterStr.trim().length() > 0) {
						String[] filter = stackTraceFilterStr.split(";");
						List<String> include = new ArrayList<String>();
						List<String> exclude = new ArrayList<String>();
						for (int i = 0; i < filter.length; i++) {
							String f = filter[i].trim();
							boolean isInclude = true;
							if (f.startsWith("+")) {
								f = f.substring(1);
							} else if (f.startsWith("-")) {
								f = f.substring(1);
								isInclude = false;
							}
							if (isInclude) {
								include.add(f);
							} else {
								exclude.add(f);
							}
						}
						config.setStackTraceFilterInclude(include
								.toArray(new String[0]));
						config.setStackTraceFilterExclude(exclude
								.toArray(new String[0]));
					}
					storage.put("config", config);
				}
			}
		}
		return config;
	}

	private long warningTime = 0;
	private boolean logParameters = false;
	private boolean logStackTrace = false;
	private String[] stackTraceFilterInclude = new String[0];
	private String[] stackTraceFilterExclude = new String[0];

	public long getWarningTime() {
		return warningTime;
	}

	public void setWarningTime(long warningTime) {
		this.warningTime = warningTime;
	}

	public boolean isLogParameters() {
		return logParameters;
	}

	public void setLogParameters(boolean logParameters) {
		this.logParameters = logParameters;
	}

	public boolean isLogStackTrace() {
		return logStackTrace;
	}

	public void setLogStackTrace(boolean logStackTrace) {
		this.logStackTrace = logStackTrace;
	}

	public String[] getStackTraceFilterInclude() {
		return stackTraceFilterInclude;
	}

	public void setStackTraceFilterInclude(String[] stackTraceFilterInclude) {
		this.stackTraceFilterInclude = stackTraceFilterInclude;
	}

	public String[] getStackTraceFilterExclude() {
		return stackTraceFilterExclude;
	}

	public void setStackTraceFilterExclude(String[] stackTraceFilterExclude) {
		this.stackTraceFilterExclude = stackTraceFilterExclude;
	}

}
