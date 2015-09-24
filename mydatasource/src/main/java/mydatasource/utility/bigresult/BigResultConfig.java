package mydatasource.utility.bigresult;

import java.util.Map;

import mydatasource.MyDataSource;

public class BigResultConfig {

	public static final String CONFIG_ROWS = "bigResult.rows";
	public static final String CONFIG_LOG_PARAMETERS = "bigResult.logParameters";
	public static final String CONFIG_LOG_STACKTRACE = "bigResult.logStackTrace";

	public static BigResultConfig getConfig(MyDataSource myDataSource) {
		Map<String, Object> storage = myDataSource
				.getDataStorage(BigResultConfig.class);
		BigResultConfig config = (BigResultConfig) storage.get("config");
		if (config == null) {
			synchronized (storage) {
				config = (BigResultConfig) storage.get("config");
				if (config == null) {
					config = new BigResultConfig();
					String rowsStr = myDataSource.getConfigs()
							.getProperty(CONFIG_ROWS);
					if (rowsStr != null
							&& rowsStr.trim().length() > 0) {
						String[] rowsStrArr = rowsStr.split(",");
						long[] rows = new long[rowsStrArr.length];
						for (int i = 0; i < rowsStrArr.length; i++) {
							long q = Long.parseLong(rowsStrArr[i].trim());
							rows[i] = q;
						}
						config.rows = rows;
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
					storage.put("config", config);
				}
			}
		}
		return config;
	}

	private long[] rows = new long[0];
	private boolean logParameters = false;
	private boolean logStackTrace = false;

	public long[] getRows() {
		return rows;
	}

	public void setRows(long[] rows) {
		this.rows = rows;
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

}
