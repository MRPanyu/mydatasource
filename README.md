# mydatasource

mydatasource是一个jdbc datasource包装工具，通过mydatasource包装原始的datasource对象，可以对获取到Connection/Statement等对象增加额外的包装，从而实现不修改程序代码的情况下对jdbc操作进行记录监控等操作。

## 使用方法

一般通过spring配置将其集成到应用中：
比如原始的spring数据源配置为：（这里以jndi数据源为例，其他类型的如CommonsDBCP的数据源也一样）

	<bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
		<property name="jndiName" value="someDataSource"/>
	</bean>

配置mydatasource的方式为：

	<!-- 名字调整成rawdataSource -->
	<bean id="rawdataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
		<property name="jndiName" value="someDataSource"/>
	</bean>
	<!-- 这个名字是dataSource，替换原来的bean -->
	<bean id="dataSource" class="mydatasource.MyDataSource">
		<!-- 引用原始dataSource -->
		<property name="dataSource" ref="rawdataSource" />
		<!-- 配置jdbc增强组件 -->
		<property name="connectionDecorators">
			<list>
				<!-- sql日志记录组件 -->
				<value>mydatasource.utility.logging.LoggingConnectionDecorator</value>
			</list>
		</property>
		<!-- jdbc增强组件所需的配置信息 -->
		<property name="configs">
			<props>
				<!-- 以下为sql日志记录组件用 -->
				<!-- 当sql执行超过如下时间（毫秒）后，以warning级别记录日志 -->
				<prop key="logging.warningTime">500</prop>
				<!-- 是否记录sql参数 -->
				<prop key="logging.logParameters">true</prop>
				<!-- 是否记录堆栈信息 -->
				<prop key="logging.logStackTrace">true</prop>
				<!-- 堆栈过滤配置（多个过滤配置之间用分号隔开，配置前带减号的表示堆栈信息必须不包含减号后面的字符串，不带减号的表示堆栈信息必须包含该字符串。如下面例子就表示堆栈信息需包含sinosoft，但不包含$$FastClassByCGLIB$$或者$$EnhancerByCGLIB$$） -->
				<prop key="logging.stackTraceFilter">sinosoft;-$$FastClassByCGLIB$$;-$$EnhancerByCGLIB$$</prop>
			</props>
		</property>
	</bean>
