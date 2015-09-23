package mydatasource.utility.statistic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import mydatasource.MyDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StatisticManager {

	protected static Log logger = LogFactory.getLog(StatisticManager.class);

	public static StatisticManager getManager(MyDataSource myDataSource) {
		Map<String, Object> storage = myDataSource
				.getDataStorage(StatisticManager.class);
		StatisticManager manager = (StatisticManager) storage.get("manager");
		if (manager == null) {
			synchronized (storage) {
				manager = (StatisticManager) storage.get("manager");
				if (manager == null) {
					manager = new StatisticManager();
					manager.myDataSource = myDataSource;
					manager.initTimer();
					storage.put("manager", manager);
				}
			}
		}
		return manager;
	}

	private MyDataSource myDataSource;
	private LinkedList<StatisticConnectionDecorator> connections = new LinkedList<StatisticConnectionDecorator>();
	private long totalOpens = 0;
	private long totalCloses = 0;

	private boolean timerInited = false;

	public MyDataSource getMyDataSource() {
		return myDataSource;
	}

	public List<StatisticConnectionDecorator> getConnections() {
		return Collections.unmodifiableList(connections);
	}

	public synchronized void openConnection(
			StatisticConnectionDecorator connection) {
		connections.add(connection);
		totalOpens++;
	}

	public synchronized void closeConnection(
			StatisticConnectionDecorator connection) {
		connections.remove(connection);
		totalCloses++;
	}

	public long getTotalOpens() {
		return totalOpens;
	}

	public long getTotalCloses() {
		return totalCloses;
	}

	public int getCurrentOpenCount() {
		return connections.size();
	}

	public void initTimer() {
		if (timerInited) {
			return;
		}
		StatisticConfig config = StatisticConfig.getConfig(myDataSource);
		long logInterval = config.getLogInterval();
		if (logInterval > 0) {
			// start at next second
			long now = System.currentTimeMillis();
			long startTime = now / 1000 * 1000 + 1000;
			Timer timer = new Timer("mydatasource.statistic."
					+ myDataSource.getName(), true);
			timer.scheduleAtFixedRate(new Task(), new Date(startTime),
					logInterval * 1000);
		}
		timerInited = true;
	}

	private class Task extends TimerTask {
		public void run() {
			StatisticConfig config = StatisticConfig.getConfig(myDataSource);
			long possibleLeakTime = config.getPossibleLeakTime() * 1000;
			StringBuilder msg = new StringBuilder();
			msg.append("Connection statistic report >> ")
					.append(myDataSource.getName()).append("\n");
			msg.append("Current opened count: ").append(connections.size())
					.append("\n");
			msg.append("Total open count: ").append(totalOpens).append("\n");
			msg.append("Total close count: ").append(totalCloses).append("\n");
			List<StatisticConnectionDecorator> leakedConnections = new ArrayList<StatisticConnectionDecorator>();
			if (possibleLeakTime > 0) {
				long now = System.currentTimeMillis();
				synchronized (StatisticManager.this) {
					for (StatisticConnectionDecorator connection : connections) {
						long openTime = connection.getOpenTime();
						if (now - openTime >= possibleLeakTime) {
							leakedConnections.add(connection);
						}
					}
				}
				if (!leakedConnections.isEmpty()) {
					msg.append("Possible leaked count (opened for at least ")
							.append(config.getPossibleLeakTime())
							.append(" seconds): ")
							.append(leakedConnections.size()).append("\n");
					for (StatisticConnectionDecorator connection : leakedConnections) {
						msg.append("\tConnection: ")
								.append(connection.getRawConnection())
								.append("\n");
						msg.append("\t\tOpened time: ")
								.append(new SimpleDateFormat(
										"yyyy-MM-dd HH:mm:ss.SSS")
										.format(connection.getOpenTime()))
								.append("\n");
						msg.append("\t\tOpened duration: ")
								.append((now - connection.getOpenTime()) / 1000)
								.append("(s)\n");
						if (connection.getCreateStatementStackTrace() != null) {
							msg.append("\t\tRelated stack trace (first time create statement): \n");
							for (StackTraceElement el : connection
									.getCreateStatementStackTrace()) {
								msg.append("\t\t\t").append(el.toString())
										.append("\n");
							}
						}
					}
				}
			}
			logger.info(msg);
		}
	}
}
