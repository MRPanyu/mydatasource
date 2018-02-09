package mydatasource.utility.sqlcatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import mydatasource.MyDataSource;

public class SqlCatchConfig {

	public static final String CONFIG_PATTERNS = "sqlcatch.patterns";

	public static SqlCatchConfig getConfig(MyDataSource myDataSource) {
		Map<String, Object> storage = myDataSource.getDataStorage(SqlCatchConfig.class);
		SqlCatchConfig config = (SqlCatchConfig) storage.get("config");
		if (config == null) {
			synchronized (storage) {
				config = (SqlCatchConfig) storage.get("config");
				if (config == null) {
					config = initConfig(myDataSource);
					storage.put("config", config);
				}
			}
		}
		return config;
	}

	private static SqlCatchConfig initConfig(MyDataSource myDataSource) {
		SqlCatchConfig config = new SqlCatchConfig();
		List<Pattern> patterns = new ArrayList<Pattern>();
		String strConfigPatterns = myDataSource.getConfigs().getProperty(CONFIG_PATTERNS);
		if (strConfigPatterns != null) {
			String[] arrConfigPatterns = strConfigPatterns.split(";");
			for (String strConfigPattern : arrConfigPatterns) {
				if (strConfigPattern.trim().length() > 0) {
					Pattern pattern = Pattern.compile(strConfigPattern.trim());
					patterns.add(pattern);
				}
			}
		}
		config.patterns = Collections.unmodifiableList(patterns);
		return config;
	}

	private List<Pattern> patterns = Collections.emptyList();

	public List<Pattern> getPatterns() {
		return this.patterns;
	}

}
