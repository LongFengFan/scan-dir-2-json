package com.yibin.nanxi.scandir2json.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LongFF on 2018/8/28
 */
//@Component
public class PrintTimeTask {
    private Logger logger = LoggerFactory.getLogger(PrintTimeTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Integer count0 = 1;
    private Integer count1 = 1;
    private Integer count2 = 1;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime()  {
        logger.info(String.format("---第%s次执行rate，当前时间为：%s", count0++, dateFormat.format(new Date())));
    }

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTimeAfterSleep() {
        logger.info(String.format("===第%s次执行delay，当前时间为：%s", count1++, dateFormat.format(new Date())));
    }

    @Scheduled(cron = "5 * * * * ?")
    public void reportCurrentTimeCron() {
        logger.info(String.format("+++第%s次执行cron，当前时间为：%s", count2++, dateFormat.format(new Date())));
    }
}
