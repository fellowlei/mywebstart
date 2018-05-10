package com.mark.dao;

import com.mark.domain.Student;

import java.util.List;

/**
 * Created by fellowlei on 2018/5/10.
 */
public interface StudentDao {
    boolean insert(Student student);

    boolean batchInsert(List<Student> studentList);

    List<Student> queryList(Student student);

    List<Student> queryListByPage(int start, int size);

    Integer queryTotalCount();
}
