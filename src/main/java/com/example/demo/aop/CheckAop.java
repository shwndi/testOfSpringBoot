package com.example.demo.aop;


import com.example.demo.annocation.Check;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;


/**
 * 参数校验
 *
 * @author czy
 * @date 2020/04/23
 */
//@Aspect
//@Component
public class CheckAop {


    @Around("@annotation(com.example.demo.annocation.Check)")
    public Object check1(ProceedingJoinPoint jp) {
        Object obj = null;
        //注解参数
        Object[] args = jp.getArgs();
        //切入方式
        String kind = jp.getKind();
        //切入点
        SourceLocation sourceLocation = jp.getSourceLocation();
        //切入对象信息
        JoinPoint.StaticPart staticPart = jp.getStaticPart();
        //是一个实实在在的对象,既不是参数也不是相关信息
        Object target = jp.getTarget();
        //动态代理
        Object This = jp.getThis();
        //汇总：（1）getClass()方法是获得调用该方法的对象的类；getClass().getName()可以得到该类的路径；
        //
        //（2）通过getClass()方法得到该对象类Class后，可以通过Class获取这个类中的相关属性和方法；
        Class<? extends ProceedingJoinPoint> Class1 = jp.getClass();
        //方法属性
        MethodSignature signature = (MethodSignature) jp.getSignature();
        //入参名
        String[] parameterNames = signature.getParameterNames();
        //方发明
        Method method = signature.getMethod();
        //getReturnType()可以用在环绕通知中,我们可以根据这个class类型,做定制化操作.而method的参数和参数上的注解,就可以从getMethod()返回的Method对象中拿,api如下:
        //获取方法上的注解
        //XXX xxx = signature.getMethod().getAnnotation(XXX.class)
        //获取所有参数上的注解
        //Annotation[][] parameterAnnotations= signature.getMethod().getParameterAnnotations();
        //链接：https://www.jianshu.com/p/f5c7417a75f9
        //返回值类型
        Class returnType = signature.getReturnType();
        Class declaringType = signature.getDeclaringType();
        String declaringTypeName = signature.getDeclaringTypeName();
        Class[] exceptionTypes = signature.getExceptionTypes();
        int modifiers = signature.getModifiers();
        //方法名
        String name = signature.getName();
        Class[] parameterTypes = signature.getParameterTypes();
        Class<? extends MethodSignature> aClass = signature.getClass();
        System.out.println("就这么多参数");
        return obj;
    }

}
