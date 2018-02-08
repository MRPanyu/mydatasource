# MyDataSource

MyDataSource是一个在JDBC API基础上进行包装代理的组件，可以对原始数据源提供的Connection/Statement等对象增加额外包装，实现不修改业务程序代码的情况下对JDBC操作进行记录监控和问题排查。

<hr/>

## 使用方法

将mydatasource.jar置入工程classpath中，所需依赖jar包仅commons-logging.jar。

一般通过spring配置将其集成到应用中：

	<!-- 这个是原始的dataSource对象，名字调整成rawDataSource -->
	<bean id="rawDataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
		<property name="jndiName" value="someDataSource"/>
	</bean>
	
	<!-- 用MyDataSource替代原来的dataSource bean -->
	<bean id="dataSource" class="mydatasource.MyDataSource">
		<!-- 引用原始的dataSource -->
		<property name="dataSource" ref="rawDataSource" />
		<!-- 配置jdbc增强组件 -->
		<property name="connectionDecorators">
			<list>
				<!-- 1. sql日志记录组件（记录所有sql执行或超时的sql执行） -->
				<value>mydatasource.utility.logging.LoggingConnectionDecorator</value>
				<!-- 2. 连接数统计组件（定时记录当前连接数，检测可能的连接泄漏问题） -->
				<value>mydatasource.utility.statistic.StatisticConnectionDecorator</value>
				<!-- 3. 查询条数检测组件（发现单个ResultSet查询条数过大问题） -->
				<value>mydatasource.utility.bigresult.BigResultConnectionDecorator</value>
				<!-- 4. 捕获某格式的sql并打印调用堆栈（可用于发现ORM框架生成的sql的相关程序） -->
				<value>mydatasource.utility.sqlcatch.SqlCatchConnectionDecorator</value>
			</list>
		</property>
		<!-- 各jdbc增强组件所需的配置信息 -->
		<property name="configs">
			<props>
				<!-- 1. 以下为sql日志记录组件用 -->
				<!-- 当sql执行超过如下时间（毫秒）后，以warning级别记录日志 -->
				<prop key="logging.warningTime">500</prop>
				<!-- 是否记录sql参数 -->
				<prop key="logging.logParameters">true</prop>
				<!-- 是否记录堆栈信息 -->
				<prop key="logging.logStackTrace">true</prop>
				<!-- 堆栈过滤配置（多段之间用分号隔开，前带减号的段表示堆栈行信息须不包含减号后面的字符串，不带减号的段表示堆栈行信息须包含该字符串。如下例表示堆栈行需包含com.mycompany，但不包含$$FastClassByCGLIB$$或者$$EnhancerByCGLIB$$） -->
				<prop key="logging.stackTraceFilter">com.mycompany;-$$FastClassByCGLIB$$;-$$EnhancerByCGLIB$$</prop>

				<!-- 2. 以下为连接数统计组件用 -->
				<!-- 每间隔多久（秒）记录一次统计信息 -->
				<prop key="statistic.logInterval">30</prop>
				<!-- Connection打开多久（秒）未关闭则报告泄漏问题 -->
				<prop key="statistic.possibleLeakTime">60</prop>
				
				<!-- 3. 以下为查询条数检测组件用 -->
				<!-- 当ResultSet读取到第几条记录时记录日志，可以多个数值逗号隔开，会记录多次 -->
				<prop key="bigResult.rows">500,1000,2000</prop>
				<!-- 日志中是否记录sql参数 -->
				<prop key="bigResult.logParameters">true</prop>
				<!-- 日志中是否记录堆栈信息 -->
				<prop key="bigResult.logStackTrace">true</prop>
				
				<!-- 4. 以下为捕获sql组件用，捕获的格式，多个正则表达式之间用分号";"隔开 -->
				<prop key="sqlcatch.patterns">select count\(\*\) from users where .+;select \* from company .+</prop>
			</props>
		</property>
	</bean>

MyDataSource各组件均通过commons-logging进行日志输出，需配合工程中对应的日志组件使用，如示例的log4j配置片段如下：

	log4j.appender.mydatasource_logging=org.apache.log4j.DailyRollingFileAppender
	log4j.appender.mydatasource_logging.File=logs/mydatasource_logging.log
	log4j.appender.mydatasource_logging.Append=true
	log4j.appender.mydatasource_logging.layout=org.apache.log4j.PatternLayout
	log4j.appender.mydatasource_logging.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss}][%p][%c]%n%m%n
	# 这里配置DEBUG级别适用于开发环境看所有sql，配置为WARN级别只记录执行时间超过配置值的sql
	log4j.logger.mydatasource.utility.logging=DEBUG,mydatasource_logging
	log4j.additivity.mydatasource.utility.logging=false

	log4j.appender.mydatasource_statistic=org.apache.log4j.DailyRollingFileAppender
	log4j.appender.mydatasource_statistic.File=logs/mydatasource_statistic.log
	log4j.appender.mydatasource_statistic.Append=true
	log4j.appender.mydatasource_statistic.layout=org.apache.log4j.PatternLayout
	log4j.appender.mydatasource_statistic.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss}][%p][%c]%n%m%n
	log4j.logger.mydatasource.utility.statistic=INFO,mydatasource_statistic
	log4j.additivity.mydatasource.utility.statistic=false
	
	log4j.appender.mydatasource_bigresult=org.apache.log4j.DailyRollingFileAppender
	log4j.appender.mydatasource_bigresult.File=logs/mydatasource_bigresult.log
	log4j.appender.mydatasource_bigresult.Append=true
	log4j.appender.mydatasource_bigresult.layout=org.apache.log4j.PatternLayout
	log4j.appender.mydatasource_bigresult.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss}][%p][%c]%n%m%n
	log4j.logger.mydatasource.utility.bigresult=INFO,mydatasource_bigresult
	log4j.additivity.mydatasource.utility.bigresult=false