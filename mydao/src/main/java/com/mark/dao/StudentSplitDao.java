package com.mark.dao;

import com.mark.domain.Student;

import java.util.List;

/**
 * Created by fellowlei on 2018/5/10.
 */
public interface StudentSplitDao {
    boolean insert(String key,Student student);

    boolean batchInsert(String key,List<Student> studentList);

    List<Student> queryList(String key,Student student);

    List<Student> queryListByPage(String key,int start, int size);

    Integer queryTotalCount(String key);
}
