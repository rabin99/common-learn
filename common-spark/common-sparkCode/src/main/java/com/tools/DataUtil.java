package com.tools;

import com.sparkSQL.Demo;
import scala.util.Random;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Description:
 *
 * @author linjh
 * @date 2018/10/09
 */
public class DataUtil  {

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        File file = new File("C:\\test\\sort1.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(new FileWriter(file,true)));
        for (int i = 0; i < 10; i++) {
            String line = (random.nextInt(10)+100) +" "+ (random.nextInt(10)+100);
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            if(i!=0 && i%1000==0){
                bufferedWriter.flush();
            }
        }
        bufferedWriter.close();
    }

    public  static String getPath(String fileName){
        String path = Demo.class.getClassLoader().getResource(fileName).getPath();
        return path;
    }

}
