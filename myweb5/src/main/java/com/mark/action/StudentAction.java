package com.mark.action;

import com.alibaba.fastjson.JSON;
import com.mark.dao.StudentDao;
import com.mark.domain.Student;
import com.mark.service.domain.StateStatistic;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fellowlei on 2018/5/10.
 */
@Controller
public class StudentAction {
    @Resource
    private StudentDao studentDao;

    @RequestMapping("/query")
    @ResponseBody
    public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out  = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        String id = request.getParameter("id");
        Student param = new Student();
        param.setId(Long.parseLong(id));
        List<Student> studentList = studentDao.queryList(param);
        String result = JSON.toJSONString(studentList);
        out.println(result);
        out.flush();
    }

    @RequestMapping("/insert")
    @ResponseBody
    public void insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out  = response.getWriter();
        Student student = new Student("mark", "beijing");
        boolean insert = studentDao.insert(student);
        if(insert){
            System.out.println("insert success");
        }else{
            System.out.println("insert failed");
        }
        response.setContentType("application/json;charset=UTF-8");
        out.println("test insert");
        out.flush();
    }


    @RequestMapping("/batchInsert")
    @ResponseBody
    public void batchInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out  = response.getWriter();
        List<Student> studentList  = new ArrayList<>();
        for(int i=0; i<100; i++){
            Student student = new Student("mark" + i, "beijing" + i);
            studentList.add(student);
        }

        StateStatistic stateStatistic = new StateStatistic();
        stateStatistic.title="batchInsert result:";
        stateStatistic.total = studentList.size();
        boolean insert = studentDao.batchInsert(studentList);
        if(insert){
            stateStatistic.success++;
        }else{
            stateStatistic.failed++;
        }
        System.out.println(stateStatistic);
        response.setContentType("application/json;charset=UTF-8");
        out.println(stateStatistic.toString());
        out.println("test insert");
        out.flush();
    }


    @RequestMapping("/readFile")
    @ResponseBody
    public void readFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        InputStream inputStream = request.getServletContext().getResourceAsStream("WEB-INF/classes/file/student.json");
        List<String> lines = IOUtils.readLines(new InputStreamReader(inputStream, Charset.forName("utf-8")));
        List<Student> studentList = new ArrayList<>();
        for(int i=0; i<lines.size(); i++){
            String json = lines.get(i);
            Student student = JSON.parseObject(json, Student.class);
            studentList.add(student);
        }

        StateStatistic stateStatistic = new StateStatistic();
        stateStatistic.title="readFile result:";
        stateStatistic.total = studentList.size();
        boolean insert = studentDao.batchInsert(studentList);
        if(insert){
            stateStatistic.success++;
        }else{
            stateStatistic.failed++;
        }

        PrintWriter out  = response.getWriter();
        String result = JSON.toJSONString("invoke read file over");
        out.println(result);
        out.println(stateStatistic.toString());
        out.flush();
    }
}
