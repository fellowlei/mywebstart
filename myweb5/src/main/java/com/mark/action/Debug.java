package com.mark.action;

import com.mark.dao.StudentDao;
import com.mark.domain.Student;
import com.mark.service.domain.StateStatistic;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by fellowlei on 2018/5/10.
 */
public class Debug {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
//        StudentDao studentDao = (StudentDao) context.getBean("studentDao");
//        Student student = new Student("mark101", "beijing101");
//        boolean insert = studentDao.insert(student);
//        if(insert){
//            System.out.println("insert success");
//        }else{
//            System.out.println("insert failed");
//        }

        StudentDao studentDao = (StudentDao) context.getBean("studentSplitDao");

        StateStatistic stateStatistic = new StateStatistic();
        stateStatistic.title="split insert result:";
        stateStatistic.total=10;
        for(int i=0; i<10; i++){
            Student student = new Student("mark" + i, "beijing" + i);
            boolean result = studentDao.insert(student);
            if(result){
                stateStatistic.success++;
            }else{
                stateStatistic.failed++;
            }
        }
        System.out.println(stateStatistic);

    }
}
