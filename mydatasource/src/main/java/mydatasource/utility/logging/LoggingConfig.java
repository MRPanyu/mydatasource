package mydatasource.utility.logging;

import java.util.Map;

import mydatasource.MyDataSource;

public class LoggingConfig {

	public static final String CONFIG_WARNING_TIME = "logging.warningTime";
	public static final String CONFIG_LOG_PARAMETERS = "logging.logParameters";
	public static final String CONFIG_LOG_STACKTRACES = "logging.logStackTraces";

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
					String logStackTracesStr = myDataSource.getConfigs()
							.getProperty(CONFIG_LOG_STACKTRACES);
					if (logStackTracesStr != null
							&& ("true".equalsIgnoreCase(logStackTracesStr
									.trim()) || "1".equals(logStackTracesStr
									.trim()))) {
						config.setLogStackTraces(true);
					}
					storage.put("config", config);
				}
			}
		}
		return config;
	}

	private long warningTime = 0;
	private boolean logParameters = false;
	private boolean logStackTraces = false;

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

	public boolean isLogStackTraces() {
		return logStackTraces;
	}

	public void setLogStackTraces(boolean logStackTraces) {
		this.logStackTraces = logStackTraces;
	}

}
