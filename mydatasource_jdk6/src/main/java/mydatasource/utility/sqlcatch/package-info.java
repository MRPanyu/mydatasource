/**
 * MyDataSource sql catching and stacktrace printing utility.
 * <p>
 * <b>Usage:</b> register
 * {@linkplain mydatasource.utility.sqlcatch.SqlCatchConnectionDecorator} in
 * {@linkplain mydatasource.MyDataSource#getConnectionDecorators()}
 * <p>
 * <b>Configurations:</b> in {@link mydatasource.MyDataSource#getConfigs()}
 * <ul>
 * <li><b>sqlcatch.patterns:</b> Regex patterns to match sql, multiple patterns
 * separated by ";".</li>
 * </ul>
 * <b>Note:</b> only catches sql in {@link java.sql.Statement} and
 * {@link java.sql.PreparedStatement}, not in
 * {@link java.sql.CallableStatement}.
 */
package mydatasource.utility.sqlcatch;