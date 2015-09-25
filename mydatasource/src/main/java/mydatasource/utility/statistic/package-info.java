/**
 * MyDataSource connection statistic utility.
 * <p>
 * <b>Usage:</b> register {@linkplain mydatasource.utility.statistic.StatisticConnectionDecorator} in {@linkplain mydatasource.MyDataSource#getConnectionDecorators()}
 * <p>
 * <b>Configurations:</b> in {@link mydatasource.MyDataSource#getConfigs()}
 * <ul>
 * <li><b>statistic.logInterval:</b> Time in seconds, interval for statistic logging.</li>
 * <li><b>statistic.possibleLeakTime:</b> Time in seconds, how long a connection is opened without close is considered leaked. Leaked connections will have extra log information such as stack trace of first create statement.</li>
 * </ul>
 */
package mydatasource.utility.statistic;