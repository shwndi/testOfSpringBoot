package com.example.demo.cache;

import com.example.demo.dto.Student;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ExpiringMap示例
 *
 * @author czy
 * @date 2021/3/26
 */
public class MyExpiringMap {
    /**
     * 简单使用，设置5秒的过期时间
     *
     * @throws InterruptedException
     */
    private static void test1() throws InterruptedException {
        ExpiringMap<String, String> map = ExpiringMap.builder()
                // 设置过期时间和过期时间单位
                .expiration(5, TimeUnit.SECONDS)
                .build();
        map.put("1", "测试");
        map.put("2", "测试");
        Thread.sleep(6000);
        System.out.println(map);
    }
    /**
     * 过期策略的使用：
     * CREATED：  在每次更新元素时，过期倒计时清零
     * ACCESSED： 在每次访问元素时，过期倒计时清零
     *
     * @throws InterruptedException
     */
    private static void test2() throws InterruptedException {

        // 使用默认的CREATED策略：
        System.out.println("----使用默认的CREATED策略：----");
        ExpiringMap<String, String> map = ExpiringMap.builder()
                .expirationPolicy(ExpirationPolicy.CREATED)
                .expiration(2, TimeUnit.SECONDS)
                .build();
        // -- 测试CREATED策略，访问的情况
        map.put("1", "测试");
        Thread.sleep(1000);
        System.out.println(map.get("1"));  // 测试
        Thread.sleep(1001);
        System.out.println(map.get("1"));  // null
        // -- 测试CREATED策略，更新的情况
        map.put("2","测试");
        Thread.sleep(1000);
        System.out.println(map.get("2")); // 测试
        map.put("2","测试1");
        Thread.sleep(1000);
        System.out.println(map.get("2")); // 测试1
        Thread.sleep(900);
        System.out.println(map.get("2")); // 测试1
        Thread.sleep(200);
        System.out.println(map.get("2")); // null


        // 使用默认的ACCESSED策略：
        System.out.println("----使用默认的ACCESSED策略：----");
        ExpiringMap<String, String> map1 = ExpiringMap.builder()
                .expirationPolicy(ExpirationPolicy.ACCESSED)
                .expiration(2, TimeUnit.SECONDS)
                .build();
        // -- 测试ACCESSED策略，访问的情况
        map1.put("1", "测试");
        Thread.sleep(1000);
        System.out.println(map1.get("1"));  // 测试
        Thread.sleep(1100);
        System.out.println(map1.get("1"));  // 测试
        Thread.sleep(2100);
        System.out.println(map1.get("1"));  // null

        // 只是访问时，过期倒计时清零
        map1.put("2","测试");
        Thread.sleep(1000);
        System.out.println(map1); // {2=测试}
        Thread.sleep(1100);
        System.out.println(map1); // {}
    }
    /**
     * 可变有效期，即单独为每个entity设置过期时间和策略：
     *
     * @throws InterruptedException
     */
    private static void test3() throws InterruptedException {
        ExpiringMap<String, String> map = ExpiringMap.builder()
                .variableExpiration()
                .expiration(2, TimeUnit.SECONDS)
                .build();
        map.put("1", "测试", ExpirationPolicy.CREATED, 1, TimeUnit.SECONDS);
        map.put("2", "测试", ExpirationPolicy.CREATED, 2, TimeUnit.SECONDS);
        map.put("3", "测试", ExpirationPolicy.CREATED, 999, TimeUnit.MILLISECONDS);
        map.put("4", "测试", ExpirationPolicy.CREATED, 1003, TimeUnit.MILLISECONDS);
        Thread.sleep(1002);
        System.out.println(map); // {2=测试, 4=测试}
    }

    /**
     * 最大值的使用：
     * Map中映射数目超过最大值的大小时，先过期第一个要过期的entity过期
     *
     * @throws InterruptedException
     */
    private static void test4() throws InterruptedException {
        ExpiringMap<String, String> map = ExpiringMap.builder()
                .maxSize(3)
                .expiration(2, TimeUnit.SECONDS)
                .build();
        map.put("1", "测试");
        map.put("2", "测试");
        map.put("3", "测试");
        System.out.println(map); // {1=测试, 2=测试, 3=测试}
        map.put("4", "测试");
        System.out.println(map); // {2=测试, 3=测试, 4=测试}

    }
    public static void main(String[] args) {
        try {
            test5();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 过期侦听器的使用：当entity过期时，可以通知过期侦听器：
     *
     * @throws InterruptedException
     */
    private static void test5() throws InterruptedException {
                // 同步过期提醒
                /*.expirationListener((key, value) -> remindExpiration(key, value))*/
        ExpiringMap<String, String> map = ExpiringMap.builder()
                .expirationPolicy(ExpirationPolicy.ACCESSED)
                .maxSize(2000)
                // 异步过期提醒
                .asyncExpirationListener((key, value) -> remindAsyncExpiration(key, value))
                .expiration(5, TimeUnit.SECONDS)
                .build();
        map.put("1", "测试");
        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i),"第0"+i+"次测试");
        }
        while (true){
            System.out.println(map.size());
            System.out.println(a);
        }

    }
    /**
     * 过期提醒
     *
     * @param key
     * @param value
     */
    private static void remindExpiration(Object key, Object value) {
        System.out.println("过期提醒,key: " + key + " value: " + value);
    }
    static AtomicInteger a = new AtomicInteger(0);
    /**
     * 异步过期提醒
     *
     * @param key
     * @param value
     */
    private static void remindAsyncExpiration(Object key, Object value) {
        value = value.toString()+"close()";
        System.out.println("异步过期提醒,key: " + key + " value: " + value);
        a.getAndIncrement();
    }
    /**
     * 懒加载的使用：put方法时不创建对象，在调用get方法时自动去创建对象
     *
     * @throws InterruptedException
     */
    private static void test6() throws InterruptedException {
        ExpiringMap<String, Student> map = ExpiringMap.builder()
                .expiration(2, TimeUnit.SECONDS)
                .entryLoader(name -> new Student(name))
                .build();
        System.out.println(map); // {}
        map.get("hanxiaozhang");
        System.out.println(map); // {hanxiaozhang=com.hanxiaozhang.expiringmap.ExpiringMapTest$Student@5d6f64b1}
    }
    /**
     * 其他：
     *
     * @throws InterruptedException
     */
    private static void test7() throws InterruptedException {
        ExpiringMap<String, String> map = ExpiringMap.builder()
                .expiration(2, TimeUnit.SECONDS)
                .build();
        map.put("1", "测试");
        // 查看剩余过期时间：
        long remainExpiration = map.getExpectedExpiration("1");
        System.out.println("查看剩余过期时间：" + remainExpiration);  // 查看剩余过期时间：1970
        // 查看设置过期时间：
        long setExpiration = map.getExpiration("1");
        System.out.println("查看设置过期时间：" + setExpiration);  // 查看设置过期时间：2000
        // 重置过期时间
        map.resetExpiration("1");
        System.out.println("查看剩余过期时间：" + map.getExpectedExpiration("1")); // 查看剩余过期时间：1999

    }
}
