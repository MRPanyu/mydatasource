/**
 * MyDataSource provides ways to intercept jdbc apis and add custom functions.
 * <p>
 * Classes in this package provides the base decorator classes for {@link java.sql.Connection}, {@link java.sql.Statement}, {@link java.sql.PreparedStatement} and {@link java.sql.CallableStatement}.
 * Which are used as base class for customization. Useful utilities are under <code>mydatasource.utility</code> packages.
 */
package mydatasource;