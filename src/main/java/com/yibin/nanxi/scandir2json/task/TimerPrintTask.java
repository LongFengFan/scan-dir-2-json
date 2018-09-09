package com.yibin.nanxi.scandir2json.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * Created by LongFF on 2018/8/28
 */
public class TimerPrintTask extends TimerTask{
    private Logger logger = LoggerFactory.getLogger(PrintTimeTask.class);
    private int i;
    @Override
    public void run() {
        logger.info(String.valueOf(i++));
    }
}
