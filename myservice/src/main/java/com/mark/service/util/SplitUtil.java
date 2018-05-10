package com.mark.service.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by fellowlei on 2018/5/10.
 */
public class SplitUtil {
    public static void splitFile(String inputFile,String outputPath) throws Exception {
        HashMap<Integer,PrintWriter> outMap = new HashMap<Integer, PrintWriter>();
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = null;
//        br.readLine(); //第一行信息，为标题信息，不用,如果需要，注释掉
        int i=0;
        while ((line = br.readLine()) != null){
            int index = i % 5;
            PrintWriter printWriter = outMap.get(index);
            if(printWriter == null){
                String outFile = outputPath + i + ".txt";
                printWriter = new PrintWriter(new FileWriter(outFile,true));
                outMap.put(index,printWriter);
            }
            if(i % 1000 == 0){
                System.out.println(i);
            }
            printWriter.println(line);
            i++;
        }

        for (PrintWriter printWriter : outMap.values()) {
            printWriter.flush();
            printWriter.close();
        }
        br.close();
    }
}
