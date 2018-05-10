package com.mark.service.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mark.domain.Student;

import java.util.concurrent.TimeUnit;

/**
 * Created by fellowlei on 2018/5/10.
 */
public class CacheUtil {
    /*** 缓存项在给定时间内没有被写访问（创建或覆盖），则回收 **/
    public static final Integer EXPIRE_AFTER_WRITE_MILLISECONDS = 5000;
    /*** cached 缓存最大数量 **/
    public static final Integer CACHE_MAXIMUMSIZE = 10000;

    public static Cache<String,Student> studentCache = CacheBuilder
            .newBuilder()
            .maximumSize(CACHE_MAXIMUMSIZE)
            .expireAfterWrite(EXPIRE_AFTER_WRITE_MILLISECONDS, TimeUnit.MILLISECONDS)
            .build();


}
