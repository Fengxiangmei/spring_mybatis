# spring_mybatis
spring和mybatis集成

1.在idea中新建一个maven项目，选择quickstart,在pom.xml中添加如下内容：

     <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.7</maven.compiler.source>
        <maven.compiler.target>1.7</maven.compiler.target>
        <spring.version>4.1.3.RELEASE</spring.version>
        <pagehelper.version>5.1.2-beta</pagehelper.version>
        <mysql.version>5.1.6</mysql.version>
        <mybatis.spring.version>1.2.3</mybatis.spring.version>
        <mybatis.version>3.2.7</mybatis.version>
        <log4j.version>1.2.16</log4j.version>
        <commons-logging.version>1.2</commons-logging.version>
        <commons-fileupload.version>1.2.1</commons-fileupload.version>
        <commons-io.version>1.3.2</commons-io.version>
        <commons-lang.version>2.6</commons-lang.version>
        <aopalliance.version>1.0</aopalliance.version>
    </properties>

    <dependencies>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.11</version>
        <scope>test</scope>
      </dependency>

      <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>${mybatis.version}</version>
      </dependency>

      <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>${mybatis.spring.version}</version>
      </dependency>

      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql.version}</version>
      </dependency>

      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>${spring.version}</version>
      </dependency>

      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>${spring.version}</version>
      </dependency>

      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-beans</artifactId>
        <version>${spring.version}</version>
      </dependency>

      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>${spring.version}</version>
      </dependency>

      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aspects</artifactId>
        <version>${spring.version}</version>
      </dependency>

      <!-- pageHelper -->
      <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper</artifactId>
        <version>${pagehelper.version}</version>
      </dependency>

      <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>${log4j.version}</version>
      </dependency>

      <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>${commons-logging.version}</version>
      </dependency>

      <dependency>
        <groupId>commons-lang</groupId>
        <artifactId>commons-lang</artifactId>
        <version>${commons-lang.version}</version>
      </dependency>

      <dependency>
        <groupId>aopalliance</groupId>
        <artifactId>aopalliance</artifactId>
        <version>${aopalliance.version}</version>
      </dependency>

      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-orm</artifactId>
        <version>${spring.version}</version>
      </dependency>
    </dependencies>
    
  同时在build节点下面添加如下内容：
    
     <resources>
        <resource>
          <directory>src/main/resources</directory>
          <includes>
            <include>**/*.properties</include>
            <include>**/*.xml</include>
            <include>**/*.tld</include>
          </includes>
          <filtering>false</filtering>
        </resource>
        <resource>
          <directory>src/main/java</directory>
          <includes>
            <include>**/*.properties</include>
            <include>**/*.xml</include>
          </includes>
          <filtering>false</filtering>
        </resource>
      </resources>
      
 2.在项目中添加dao，service，pojo等包和实现类,并在service中添加@Service注解，目录如下：
 
     ![pic1](https://github.com/Fengxiangmei/spring_mybatis/blob/master/QQ20190828-213645%402x.png)
 
 3.在main文件夹下新建resources文件夹，并将其标注为resources root。
 
 4.在resources文件夹添加mybatis配置文件mybatis_config.xml,内容如下：
    
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD SQL Map Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <settings>
            <setting name="logImpl" value="LOG4J"/>
        </settings>
        <typeAliases>
    <!--        <typeAlias type="com.javafeng.entity.User" alias="User" />-->
        </typeAliases>
    </configuration>
    
 5.在resources文件夹添加数据库连接文件database.properties:
 
    #database connection config
    driverClassName = com.mysql.jdbc.Driver
    url = jdbc:mysql://localhost:3306/nobody?useUnicode=true&characterEncoding=utf-8
    username = root
    password =123456
    
 6.在resources文件夹中添加spring配置文件applicationContext.xml：
    
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
      <!--数据源-链接数据库的基本信息,这里直接写,不放到*.properties资源文件中-->
      <context:property-placeholder location="classpath:database.properties"/>
      <bean id="dataSource"
          class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="${driverClassName}" />
        <property name="url" value="${url}" />
        <property name="username" value="${username}" />
        <property name="password" value="${password}" />
      </bean>
      <!-- 配置数据源,加载配置,也就是dataSource -->
      <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"></property>
        <!--mybatis的配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml" />
        <!--扫描 XXXmapper.xml映射文件,配置扫描的路径-->
        <property name="mapperLocations" value="classpath:com/fxm/study/dao/*.xml"></property>
      </bean>
      <!-- DAO接口所在包名，Spring会自动查找之中的类 -->
      <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.fxm.study.dao" />
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
      </bean>

      <context:component-scan base-package="com.fxm.study.service"/>
      <!--事务管理-->
      <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--注入dataSource-->
        <property name="dataSource" ref="dataSource" />
      </bean>
      <!--开启事务注解扫描-->
      <tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>

    </beans>

7.在resources文件夹下添加日志配置文件log4j.properties:

      # Global logging configuration
      log4j.rootLogger=debug, stdout
      # Console output...
      log4j.appender.stdout=org.apache.log4j.ConsoleAppender
      log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
      log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
 
 8.在test文件夹下AppTest添加测试代码：
  
    package com.fxm.study;

    import static org.junit.Assert.assertTrue;

    import com.fxm.study.pojo.Student;
    import com.fxm.study.service.StudentService;
    import org.apache.log4j.Logger;
    import org.junit.Before;
    import org.junit.BeforeClass;
    import org.junit.Test;
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.support.ClassPathXmlApplicationContext;

    /**
     * Unit test for simple App.
     */
    public class AppTest 
    {
        private static StudentService studentService;
        public static ApplicationContext ctx;

        Logger logger = Logger.getLogger(AppTest.class);
        @Before
        public  void before(){
            ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        }

        @Test
        public  void testMerge(){
            studentService=(StudentService) ctx.getBean("studentServiceImpl");
            Student  student = studentService.selectByPrimaryKey(1);
            logger.info(student.getSname() + ",," + student.getSno());
        }

        @Test
        public void shouldAnswerWithTrue()
        {
            assertTrue( true );
        }
    }

    
 
    
 
 

       
