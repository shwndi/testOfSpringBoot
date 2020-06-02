####【Spring AOP】切入点表达式（四）
一、切入点指示符
　　切入点指示符用来指示切入点表达式目的，在Spring AOP中目前只有执行方法这一个连接点，Spring AOP支持的AspectJ切入点指示符如下：

execution：用于匹配方法执行的连接点；
within：用于匹配指定的类及其子类中的所有方法。
this：匹配可以向上转型为this指定的类型的代理对象中的所有方法。
target：匹配可以向上转型为target指定的类型的目标对象中的所有方法。
args：用于匹配运行时传入的参数列表的类型为指定的参数列表类型的方法；
@within：用于匹配持有指定注解的类的所有方法；
@target：用于匹配的持有指定注解目标对象的所有方法；
@args：用于匹配运行时 传入的参数列表的类型持有 注解列表对应的注解 的方法；
@annotation：用于匹配持有指定注解的方法；
　　AspectJ切入点支持的切入点指示符还有： call、get、set、preinitialization、staticinitialization、initialization、handler、adviceexecution、withincode、cflow、cflowbelow、if、@this、@withincode；但Spring AOP目前不支持这些指示符，使用这些指示符将抛出IllegalArgumentException异常。

二、类型匹配语法
(1) *：匹配任何数量字符；

(2) ..：匹配任何数量字符的重复，如在类型模式中匹配任何数量子包；而在方法参数模式中匹配任何数量参数

(3) +：匹配指定类型的子类型；仅能作为后缀放在类型模式后边。

AspectJ使用 且（&&）、或（||）、非（！）来组合切入点表达式。
在Schema风格下，由于在XML中使用“&&”需要使用转义字符“&amp;&amp;”来代替之，所以很不方便，因此Spring ASP 提供了and、or、not来代替&&、||、！。

三、切入点指示符详解
1. execution

execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)  除了返回类型模式、方法名模式和参数模式外，其它项都是可选的。

参数模式如下：

() 匹配一个不接受任何参数的方法
(..) 匹配一个接受任意数量参数的方法
(*) 匹配了一个接受一个任何类型的参数的方法
(*,String) 匹配了一个接受两个参数的方法，其中第一个参数是任意类型，第二个参数必须是String类型

举例：

#匹配所有目标类的public方法
execution(public * *(..))

#匹配所有以To为后缀的方法
execution(* *To(..))

#匹配Waiter接口中的所有方法
execution(* com.aop.learn.service.Writer.*(..))

#匹配Waiter接口中及其实现类的方法
execution(* com.aop.learn.service.Writer+.*(..))

#匹配 com.aop.learn.service 包下所有类的所有方法
execution(* com.aop.learn.service.*(..))

#匹配 com.aop.learn.service 包,子孙包下所有类的所有方法
execution(* com.aop.learn.service..*(..))

#匹配 包名前缀为com的任何包下类名后缀为ive的方法,方法必须以Smart为前缀
execution(* com..*.*ive.Smart*(..))

# 匹配 save(String name,int age) 函数
execution(* save(String,int))

#匹配 save(String name,*) 函数 第二个参数为任意类型
execution(* save(String,*))

#匹配 save(String name,..) 函数 除第一个参数固定外,接受后面有任意个入参且入参类型不限
execution(* save(String,..))

#匹配 save(String+) 函数  String+ 表示入参类型是String的子类
execution(* save(String+))
2. with 

within是用来指定类型的，指定类型中的所有方法将被拦截。

举例：

#表示匹配包aop_part以及子包的所有方法
within(aop_part..*) 

#匹配UserServiceImpl类对应对象的所有方法外部调用，而且这个对象只能是UserServiceImpl类型，不能是其子类型。
within(com.elim.spring.aop.service.UserServiceImpl)
由于execution可以匹配包、类、方法，而within只能匹配包、类，因此execution完全可以代替within的功能。

3. this 

Spring Aop是基于代理的，this就表示代理对象。this类型的Pointcut表达式的语法是this(type)，当生成的代理对象可以转换为type指定的类型时则表示匹配。基于JDK接口的代理和基于CGLIB的代理生成的代理对象是不一样的。

举例：

#表示匹配了GodService接口的代理对象的所有连接点
this(aop_part.service.GodService)        
4. target 

Spring Aop是基于代理的，target则表示被代理的目标对象。当被代理的目标对象可以被转换为指定的类型时则表示匹配。

举例：

#表示匹配实现了GodService接口的目标对象的所有连接点
target(aop_part.service.GodService)      
5. args 

args用来匹配方法参数的。

“args()”匹配任何不带参数的方法。
“args(java.lang.String)”匹配任何只带一个参数，而且这个参数的类型是String的方法。
“args(..)”带任意参数的方法。
“args(java.lang.String,..)”匹配带任意个参数，但是第一个参数的类型是String的方法。
“args(..,java.lang.String)”匹配带任意个参数，但是最后一个参数的类型是String的方法。
6. @target

匹配当被代理的目标对象对应的类型及其父类型上拥有指定的注解时。

举例：

#匹配被代理的目标对象对应的类型上拥有MyAnnotation注解时
@target(com.elim.spring.support.MyAnnotation)
7. @args

匹配被调用的方法上含有参数，且对应的参数类型上拥有指定的注解的情况。

“@args(com.elim.spring.support.MyAnnotation)”匹配方法参数类型上拥有MyAnnotation注解的方法调用。如我们有一个方法add(MyParam param)接收一个MyParam类型的参数，而MyParam这个类是拥有注解MyAnnotation的，则它可以被Pointcut表达式“@args(com.elim.spring.support.MyAnnotation)”匹配上。

8. @within

用于匹配被代理的目标对象对应的类型或其父类型拥有指定的注解的情况，但只有在调用拥有指定注解的类上的方法时才匹配。

“@within(com.elim.spring.support.MyAnnotation)”匹配被调用的方法声明的类上拥有MyAnnotation注解的情况。比如有一个ClassA上使用了注解MyAnnotation标注，并且定义了一个方法a()，那么在调用ClassA.a()方法时将匹配该Pointcut；如果有一个ClassB上没有MyAnnotation注解，但是它继承自ClassA，同时它上面定义了一个方法b()，那么在调用ClassB().b()方法时不会匹配该Pointcut，但是在调用ClassB().a()时将匹配该方法调用，因为a()是定义在父类型ClassA上的，且ClassA上使用了MyAnnotation注解。但是如果子类ClassB覆写了父类ClassA的a()方法，则调用ClassB.a()方法时也不匹配该Pointcut。

9. @annotation

用于匹配方法上拥有指定注解的情况。

举例：

#匹配所有的方法上拥有MyAnnotation注解的方法外部调用。
@annotation(com.elim.spring.support.MyAnnotation)
10. bean 

用于匹配当调用的是指定的Spring的某个bean的方法时。

“bean(abc)”匹配Spring Bean容器中id或name为abc的bean的方法调用。
“bean(user*)”匹配所有id或name为以user开头的bean的方法调用。
四、常见切入点表达式
#任意公共方法的执行： 
execution(public * *(..)) 

#任何一个以“set”开始的方法的执行： 
execution(* set*(..)) 

#AccountService 接口的任意方法的执行： 
execution(* com.xyz.service.AccountService.*(..)) 

#定义在service包里的任意方法的执行： 
execution(* com.xyz.service.*.*(..)) 

#定义在service包或者子包里的任意方法的执行： 
execution(* com.xyz.service..*.*(..)) 

#在service包里的任意连接点（在Spring AOP中只是方法执行） ： 
within(com.xyz.service.*) 

#在service包或者子包里的任意连接点（在Spring AOP中只是方法执行） ： 
within(com.xyz.service..*) 

#实现了 AccountService 接口的代理对象的任意连接点（在Spring AOP中只是方法执行） ： 
this(com.xyz.service.AccountService) 

#实现了 AccountService 接口的目标对象的任意连接点（在Spring AOP中只是方法执行） ： 
target(com.xyz.service.AccountService) 

#任何一个只接受一个参数，且在运行时传入的参数实现了 Serializable 接口的连接点 （在Spring AOP中只是方法执行） 
args(java.io.Serializable) 

#有一个 @Transactional 注解的目标对象中的任意连接点（在Spring AOP中只是方法执行） 
@target(org.springframework.transaction.annotation.Transactional) 

#任何一个目标对象声明的类型有一个 @Transactional 注解的连接点（在Spring AOP中只是方法执行） 
@within(org.springframework.transaction.annotation.Transactional) 

#任何一个执行的方法有一个 @Transactional annotation的连接点（在Spring AOP中只是方法执行） 
@annotation(org.springframework.transaction.annotation.Transactional) 

#任何一个接受一个参数，并且传入的参数在运行时的类型实现了 @Classified annotation的连接点（在Spring AOP中只是方法执行） 
@args(com.xyz.security.Classified)