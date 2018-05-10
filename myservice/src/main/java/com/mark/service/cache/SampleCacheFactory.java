package com.mark.service.cache;

import com.mark.domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fellowlei on 2018/5/9.
 */
public class SampleCacheFactory {
    public Map<Long,List<Student>> student_Map_1 = new HashMap<>();
    public Map<Long,List<Student>> student_Map_2 = new HashMap<>();
    public Map<Long,List<Student>> student_Map_3 = new HashMap<>();
    public Map<Long,List<Student>> student_Map_4 = new HashMap<>();
    public Map<Long,List<Student>> student_Map_5 = new HashMap<>();


    private List<Map<Long,List<Student>>> all_list = new ArrayList<>();
    public void init(){
        all_list.add(student_Map_1);
        all_list.add(student_Map_2);
        all_list.add(student_Map_3);
        all_list.add(student_Map_4);
        all_list.add(student_Map_5);
    }

    public SampleCacheFactory() {
        init();
    }

    public void add(Student student){
        long id = student.getId();
        int index = (int) (id % 5);
        Map<Long, List<Student>> student_Map = all_list.get(index);
        List<Student> tmpList = student_Map.get(id);
        if(tmpList == null){
            tmpList = new ArrayList<>();
            student_Map.put(id,tmpList);
        }
        tmpList.add(student);
    }

    public List<Student> query(Long id){
        int index = (int) (id % 5);
        Map<Long, List<Student>> student_Map = all_list.get(index);
        List<Student> studentList = student_Map.get(id);
        return studentList;
    }

    public int hash(Long id){
        int index = (int) (id % 5);
        return index;
    }



    public static void main(Long[] args) {
        SampleCacheFactory simpleCacheFactory = new SampleCacheFactory();
        for(long i=0; i<10; i++){
            Student student = new Student();
            student.setId(i);
            simpleCacheFactory.add(student);
        }

        System.out.println(simpleCacheFactory);


        for(long i=0;i<10; i++){
            List<Student> query = simpleCacheFactory.query(i);
            System.out.println(query);
        }

    }
}
