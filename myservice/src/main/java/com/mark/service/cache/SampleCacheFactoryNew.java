package com.mark.service.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fellowlei on 2018/5/9.
 */
public class SampleCacheFactoryNew<K,V> {
    public Map<K,V> map_1 = new HashMap<>();
    public Map<K,V> map_2 = new HashMap<>();
    public Map<K,V> map_3 = new HashMap<>();
    public Map<K,V> map_4 = new HashMap<>();
    public Map<K,V> map_5 = new HashMap<>();


    private List<Map<K,V>> all_list = new ArrayList<>();
    public void init(){
        all_list.add(map_1);
        all_list.add(map_2);
        all_list.add(map_3);
        all_list.add(map_4);
        all_list.add(map_5);
    }

    public SampleCacheFactoryNew() {
        init();
    }

    public void add(K k,V v){
        int index = hash(k);
        Map<K,V> map = all_list.get(index);
        map.put(k,v);
    }

    public V get(K k){
        int index = hash(k);
        Map<K,V> map = all_list.get(index);
       return map.get(k);
    }

    public int hash(K k){
        String id = (String) k;
        int index = (int) (Long.parseLong(id) % 5);
        return index;
    }

    @Override
    public String toString() {
        return "SampleCacheFactoryNew{" +
                "\n map_1=" + map_1 +
                "\n map_2=" + map_2 +
                "\n map_3=" + map_3 +
                "\n map_4=" + map_4 +
                "\n map_5=" + map_5 +
                "\n all_list=" + all_list +
                '}';
    }

    public static void main(String[] args) {
        SampleCacheFactoryNew<String, String> sampleCacheFactoryNew = new SampleCacheFactoryNew<>();
        for(int i=0; i<10; i++){
            sampleCacheFactoryNew.add("" + i,""+i );
        }

        System.out.println(sampleCacheFactoryNew);

        for(int i=0; i<10; i++){
            String value = sampleCacheFactoryNew.get("" + i);
            System.out.println(value);
        }
    }
}
