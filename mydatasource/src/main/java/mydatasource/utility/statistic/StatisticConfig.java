package mydatasource.utility.statistic;

import java.util.Map;

import mydatasource.MyDataSource;

public class StatisticConfig {

	public static final String CONFIG_LOG_INTERVAL = "statistic.logInterval";
	public static final String CONFIG_POSSIBLE_LEAK_TIME = "statistic.possibleLeakTime";

	public static StatisticConfig getConfig(MyDataSource myDataSource) {
		Map<String, Object> storage = myDataSource
				.getDataStorage(StatisticConfig.class);
		StatisticConfig config = (StatisticConfig) storage.get("config");
		if (config == null) {
			synchronized (storage) {
				config = (StatisticConfig) storage.get("config");
				if (config == null) {
					config = new StatisticConfig();
					String logIntervalStr = myDataSource.getConfigs()
							.getProperty(CONFIG_LOG_INTERVAL);
					if (logIntervalStr != null
							&& logIntervalStr.trim().length() > 0) {
						config.setLogInterval(Long.parseLong(logIntervalStr));
					}
					String possibleLeakTimeStr = myDataSource.getConfigs()
							.getProperty(CONFIG_POSSIBLE_LEAK_TIME);
					if (possibleLeakTimeStr != null
							&& possibleLeakTimeStr.trim().length() > 0) {
						config.setPossibleLeakTime(Long
								.parseLong(possibleLeakTimeStr));
					}
					storage.put("config", config);
				}
			}
		}
		return config;
	}

	/** Log interval in seconds. */
	private long logInterval = 0;
	/**
	 * How long a connection is opened without close is considered "leaked", in
	 * seconds.
	 */
	private long possibleLeakTime = 0;

	public long getLogInterval() {
		return logInterval;
	}

	public void setLogInterval(long logInterval) {
		this.logInterval = logInterval;
	}

	public long getPossibleLeakTime() {
		return possibleLeakTime;
	}

	public void setPossibleLeakTime(long possibleLeakTime) {
		this.possibleLeakTime = possibleLeakTime;
	}

}
