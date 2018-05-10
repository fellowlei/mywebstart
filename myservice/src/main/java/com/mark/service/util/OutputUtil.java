package com.mark.service.util;

import com.alibaba.fastjson.JSON;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by fellowlei on 2018/4/15.
 */
public class OutputUtil {
    // clean output
    public static void output(String fileName,Object obj) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        printWriter.println(JSON.toJSONString(obj));
        printWriter.flush();
        printWriter.close();
        System.out.println("write to file:" + fileName);
    }


    // clean output
    public static void append(String fileName,Object obj) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName,true));
        printWriter.println(JSON.toJSONString(obj));
        printWriter.flush();
        printWriter.close();
        System.out.println("write to file:" + fileName);
    }

    // clean output
    public static void appendStr(String fileName,String msg) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName,true));
        printWriter.println(msg);
        printWriter.flush();
        printWriter.close();
        System.out.println("write to file:" + fileName);
    }
}
