package com.yibin.nanxi.scandir2json.util;

import com.yibin.nanxi.scandir2json.service.impl.ScanDirServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by LongFF on 2018/9/9
 */
public class ShellUtils {
    private static Logger logger = LoggerFactory.getLogger(ScanDirServiceImpl.class);

    public static Boolean executeShell(String cmd, File dir){
        logger.info("cmd is : " + cmd);
        logger.info("dir is : " +  dir.getAbsolutePath());
        Process process;
        try {
            process = Runtime.getRuntime().exec(cmd, null, dir);
            int exitValue = process.waitFor();
            new Thread(() -> {
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new InputStreamReader(process.getInputStream(),"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                dealInputStream(in,"InputStream");
                logger.info("处理InputStream结束");
            }).start();

            new Thread(() -> {
                BufferedReader err = null;
                try {
                    err = new BufferedReader(new InputStreamReader(process.getErrorStream(),"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                dealInputStream(err,"ErrorStream:");
                logger.info("处理ErrorStream结束");
            }).start();
            if (0 != exitValue) {
                logger.error("call shell failed. error code is :" + exitValue);
                return false;
            }else {
                return true;
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            return false;
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            return false;
        }
    }
    private static void dealInputStream(BufferedReader in ,String type) {
        StringBuilder result = new StringBuilder();
        String line = null;
        try{
            while((line = in.readLine()) != null) {
                result.append(line).append('\n');
            }
            logger.info(type + "===========================" + result);
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
