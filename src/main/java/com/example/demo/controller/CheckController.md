###  通过AOP实现
##  Maven 依赖
#除了spring-boot依赖之外，需要的第三方依赖，不是核心的依赖，可以根据个人习惯取舍
<!--AOP-->
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<!-- 用于字符串校验 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.3.2</version>
</dependency>
<!-- 用于日志打印 -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.25</version>
</dependency>