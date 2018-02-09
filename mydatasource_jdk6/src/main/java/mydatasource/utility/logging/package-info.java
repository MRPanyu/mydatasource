/**
 * MyDataSource sql logging utility.
 * <p>
 * <b>Usage:</b> register {@linkplain mydatasource.utility.logging.LoggingConnectionDecorator} in {@linkplain mydatasource.MyDataSource#getConnectionDecorators()}
 * <p>
 * <b>Configurations:</b> in {@link mydatasource.MyDataSource#getConfigs()}
 * <ul>
 * <li><b>logging.warningTime:</b> Time in milliseconds, if sql execution exceeds this time, log level is changed from DEBUG to WARN. Useful for detection of long executing sqls.</li>
 * <li><b>logging.logParameters:</b> Should the parameters be logged (true or false). Minor overheads if true.</li>
 * <li><b>logging.logStackTrace:</b> Should stack trace be logged (true or false). Minor overheads and much bigger log file size if true.</li>
 * <li><b>logging.stackTraceFilter:</b> Filters for stack trace, for example "com.mycompany;-$$FastClassByCGLIB$$". Multiple filters separated by ";", filters not begin with a "-" means that only stack trace element contains the string should be shown, filters begin with a "-" means that stack trace element which contains string after the "-" should be ignored.</li>
 * </ul>
 */
package mydatasource.utility.logging;