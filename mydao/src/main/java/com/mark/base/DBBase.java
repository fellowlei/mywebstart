package com.mark.base;

import com.mark.util.MurmurHash;

/**
 * Created by lulei on 2018/5/11.
 * 分库与分表实现策略
 * １、中间变量　＝ user_id%（库数量*每个库的表数量）;
 * ２、库序号　＝　取整（中间变量／每个库的表数量）;
 * ３、表序号　＝　中间变量％每个库的表数量;
 */
public class DBBase{
    protected int dbCount = 1;
    protected int tbCount = 3;

    public int getDBIndex(int id){
        int mid = id %(dbCount * tbCount);
        return mid/tbCount;

    }

    public int getTBIndex(int id){
        int mid = id %(dbCount * tbCount);
        return mid % tbCount;
    }

    public int hash(String key){
        return Math.abs(MurmurHash.hash32(key));
    }

    public static void main(String[] args) {
        DBBase dbBase = new DBBase();
        for(int i=0; i<10;i++){
            String msg = String.format("db:%s,tb:%s", dbBase.getDBIndex(i), dbBase.getTBIndex(i));
            System.out.println(msg);
        }
    }

}
