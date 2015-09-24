package mydatasource.utility.bigresult;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import mydatasource.MyDataSource;
import mydatasource.ResultSetDecorator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BigResultResultSetDecorator extends ResultSetDecorator {

	protected Log logger = LogFactory.getLog(this.getClass());

	protected MyDataSource myDataSource;
	protected String sql;
	protected Map<Integer, Object> indexedParameters = new LinkedHashMap<Integer, Object>();
	protected int maxParameterIndex = 0;
	protected Map<String, Object> namedParameters = new LinkedHashMap<String, Object>();

	private long nextCount;
	private BigResultConfig config;

	public long getNextCount() {
		return this.nextCount;
	}

	public MyDataSource getMyDataSource() {
		return myDataSource;
	}

	public void setMyDataSource(MyDataSource myDataSource) {
		this.myDataSource = myDataSource;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Map<Integer, Object> getIndexedParameters() {
		return indexedParameters;
	}

	public void setIndexedParameters(Map<Integer, Object> indexedParameters) {
		this.indexedParameters = indexedParameters;
	}

	public int getMaxParameterIndex() {
		return maxParameterIndex;
	}

	public void setMaxParameterIndex(int maxParameterIndex) {
		this.maxParameterIndex = maxParameterIndex;
	}

	public Map<String, Object> getNamedParameters() {
		return namedParameters;
	}

	public void setNamedParameters(Map<String, Object> namedParameters) {
		this.namedParameters = namedParameters;
	}

	public void init() {
		nextCount = 0;
		config = BigResultConfig.getConfig(myDataSource);
	}

	public void onNext() {
		nextCount++;
		long[] rows = config.getRows();
		boolean doLog = false;
		for (int i = 0; i < rows.length; i++) {
			long row = rows[i];
			if (nextCount == row && logger.isWarnEnabled()) {
				doLog = true;
				break;
			}
		}
		if (doLog) {
			StringBuilder msg = new StringBuilder();
			msg.append("Reading row: ").append(nextCount).append("\n");
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
				if (namedParameters != null && !namedParameters.isEmpty()) {
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
			if (config.isLogStackTrace()) {
				msg.append("StackTrace:\n");
				StackTraceElement[] stackTrace = Thread.currentThread()
						.getStackTrace();
				for (int i = 0; i < stackTrace.length; i++) {
					StackTraceElement el = stackTrace[i];
					if (i == 0
							&& el.getClassName().equals(Thread.class.getName())) {
						continue;
					}
					msg.append("\t").append(el.toString()).append("\n");
				}
			}
			logger.warn(msg);
		}
	}

	@Override
	public boolean next() throws SQLException {
		boolean returnValue = super.next();
		if (returnValue) {
			onNext();
		}
		return returnValue;
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
