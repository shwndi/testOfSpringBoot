package com.example.demo.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 测试guava
 *
 * @author czy
 * @date 2021/5/14
 */
@Component
public class TestGuava {
//    private static LoadingCache<String, Object> cacheMap = CacheBuilder.newBuilder()
//            .maximumSize(100) //缓存大小
////            .expireAfterAccess(5, TimeUnit.MINUTES) //加载缓存之后，多久过期
//            .expireAfterWrite(5, TimeUnit.MINUTES)//缓存被写入,update之后，多久过期
//            .recordStats().build(new CacheLoader<String, Object>() {
//                //当本地缓存命没有中时，调用load方法获取结果并将结果缓存
//                @Override
//                public Object load(String key) throws Exception {
//                    //"缓存没了，请去数据库查找";
//                    System.out.println("load():key = "+key);
//                    if(cacheMap.getIfPresent(key) == null){
//                        return getDbResultInfo(key);
//                    }else{
//                        return cacheMap.getIfPresent(key);
//                    }
//                }
//
//                private Object getDbResultInfo(String key) throws Exception {
//                    System.out.println("正在查询...");
//                    cacheMap.put(key,"这是数据库查询的结果");
//                    return cacheMap.getIfPresent(key);
//                }
//            });

}
