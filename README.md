## brand-case-demo前言
b站黑马的java web新版课程最后综合练习实现个人的源码，部分代码有个人放开写的地方，仅供参考，有可能有bug，欢迎提issue一起进步学习

## 目前已知bug
1. 前后端没有做表单验证，如果提交空数据或者提交order数据是字符肯定会出问题，这个就暂时不管了
2. 修改表单项数据回显时，状态的switch开关不能正确回显，暂时原因未知

## 使用方式
方法1：直接download下载源码，然后在IDEA中打开源码位置，IDEA会自动识别Maven项目
方法2：
1. 先git clone 到本地
```bash
git clone git@github.com:genres17/brand-case-demo.git
```
2. 使用方法1方式导入IDEA使用
## 使用注意事项
1. 修改数据库驱动和自己MySQL版本对应上
目前项目中使用的MySQL是8.0+的，因此使用的是8.0+驱动，和5.x版本的配置写法稍微不同
pom.xml中修改版本号
``` xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.25</version>
</dependency>
```
mybatis-config.xml中重新配置驱动名称和用户信息
```xml
<environments default="development">
  <environment id="development">
      <transactionManager type="JDBC"/>
      <!--            数据库的配置文件-->
      <dataSource type="POOLED">
          <!--   mysql8.0以后驱动用这个             -->
          <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
          <property name="url" value=""/>
          <property name="username" value=""/>
          <property name="password" value=""/>
      </dataSource>
  </environment>
</environments>
```
2. 修改pom.xml中jdk版本信息,项目采用的是16的
```xml
<properties>
    <maven.compiler.source>16</maven.compiler.source>
    <maven.compiler.target>16</maven.compiler.target>
</properties>
