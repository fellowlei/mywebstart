package com.mark.dao.impl;

import com.mark.dao.StudentDao;
import com.mark.domain.Student;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fellowlei on 2018/5/10.
 */
public class StudentDaoImpl extends SqlMapClientTemplate implements StudentDao {
    @Override
    public boolean insert(Student student) {
        this.insert("student.insert",student);
        return true;
    }

    @Override
    public boolean batchInsert(List<Student> studentList) {
        this.insert("student.batchInsert",studentList);
        return true;
    }

    @Override
    public List<Student>  queryList(Student student) {
        List list =  this.queryForList("student.selectList", student);
        return list;
    }

    @Override
    public List<Student> queryListByPage(int start, int size) {
        Map<String,Object> map = new HashMap<>();
        map.put("start",start );
        map.put("size",size);
        List list = this.queryForList("student.selectListByPage", map);
        return list;
    }

    @Override
    public Integer queryTotalCount() {
        Integer count = (Integer) this.queryForObject("student.selectCount");
        return count;
    }
}
