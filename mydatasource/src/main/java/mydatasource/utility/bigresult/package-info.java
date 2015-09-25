/**
 * MyDataSource big result detection utility.
 * <p>
 * <b>Usage:</b> register {@linkplain mydatasource.utility.bigresult.BigResultConnectionDecorator} in {@linkplain mydatasource.MyDataSource#getConnectionDecorators()}
 * <p>
 * <b>Configurations:</b> in {@link mydatasource.MyDataSource#getConfigs()}
 * <ul>
 * <li><b>bigResult.rows:</b> Row numbers to log, separated by ",", for example "500,1000", means when the 500th or the 1000th row of a single query is fetched, a log message is written.</li>
 * <li><b>bigResult.logParameters:</b> Should the related sql parameters be logged (true or false), minor overhead if true.</li>
 * <li><b>bigResult.logStackTrace:</b> Should the stack trace be logged (true or false), minor overhead and larger log file size.</li>
 * </ul>
 */
package mydatasource.utility.bigresult;