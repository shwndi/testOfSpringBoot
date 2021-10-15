package com.example.demo.cache.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author czy
 * @date 2021/5/14
 */
public class LoadingCacheTest {
    private static LoadingCache<Integer, String> cacheMap = CacheBuilder.newBuilder()
            .maximumSize(3) //缓存大小
//            .expireAfterAccess(5, TimeUnit.SECONDS) //加载缓存之后，多久过期
            .expireAfterWrite(5,TimeUnit.SECONDS)//缓存被写入,update之后，多久过期
            .recordStats().build(new CacheLoader<Integer, String>() {
                //当本地缓存命没有中时，调用load方法获取结果并将结果缓存
                @Override
                public String load(Integer key) throws Exception {
                    //"缓存没了，请去数据库查找";
                    System.out.println("load():key = "+key);
                    if(cacheMap.getIfPresent(key) == null){
                        return getDbResultInfo(key);
                    }else{
                        return cacheMap.getIfPresent(key);
                    }
                }

                private String getDbResultInfo(int key) throws Exception {
                    System.out.println("正在查询...");
                    cacheMap.put(key,"这是数据库查询的结果");
                    return cacheMap.getIfPresent(key);
                }
            });

    public static void main(String[] args) throws ExecutionException {
        //超过上限的时候，后面覆盖前面的key
        System.out.println("cacheMap size："+cacheMap.size());
        cacheMap.put(1,"a");
        cacheMap.put(2,"b");
        cacheMap.put(3,"c");
        cacheMap.put(4,"d");
        System.out.println("cacheMap size："+cacheMap.size());
        //缓存中如果有这个key返回value，如果没有这个key直接返回null
        System.out.println("cacheMap getIfPresent："+cacheMap.getIfPresent(1));
        System.out.println("cacheMap getIfPresent："+cacheMap.getIfPresent(2));
        System.out.println("cacheMap getIfPresent："+cacheMap.getIfPresent(3));
        System.out.println("cacheMap getIfPresent："+cacheMap.getIfPresent(4));
        System.out.println("--------------------------------------");

        try {
            System.out.println("第一次查询："+cacheMap.get(1));
            System.out.println("第二次查询："+cacheMap.get(1));
            System.out.println("第三次查询："+cacheMap.get(1)); //这个时候key=2被key=1覆盖
            System.out.println("cacheMap size："+cacheMap.size());
            System.out.println("--------------------------------------");
            System.out.println("第一次查询："+cacheMap.get(3));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("cacheMap getIfPresent："+cacheMap.getIfPresent(5));
        System.out.println("cacheMap get："+cacheMap.get(5));
        System.out.println("第二次查询："+cacheMap.get(1));
        System.out.println("第二次查询："+cacheMap.get(2));
        System.out.println("第二次查询："+cacheMap.get(3));
        System.out.println("第一次查询："+cacheMap.get(4));
        System.out.println("cacheMap get："+cacheMap.get(5));
        System.out.println(cacheMap.asMap().values());
        System.out.println("---------------后面是过期了的数据打印-------------------------------");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //目前其实是key =3,4,5 过期
        System.out.println("cacheMap size："+cacheMap.size());
        //说明过期的缓存是没有清楚的，而是被标识无法读取
//        System.out.println(cacheMap.getIfPresent(4));
        /**
         * getIfPresent()如果存在就返回，否则返回null
         * 如果数据过期，那么将清除数据。
         */


        /**
         * get() 缓存有值就用没有就查询保存再使用
         *  整个过程为 "if cached, return; otherwise create, cache and return"
         * 注意valueLoader要么返回非null值，要么抛出异常，绝对不能返回nul
         */

        System.out.println(cacheMap.getIfPresent(6));

        System.out.println("cacheMap size："+cacheMap.size());
        System.out.println(cacheMap.getIfPresent(7));
        System.out.println("cacheMap size："+cacheMap.size());
        System.out.println(cacheMap.getIfPresent(6));
        System.out.println("cacheMap size："+cacheMap.size());

        cacheMap.put(1,"aaaa");

        System.out.println("cacheMap size："+cacheMap.size());


    }
}
