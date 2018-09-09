package com.yibin.nanxi.scandir2json.init;

import com.yibin.nanxi.scandir2json.task.TimerPrintTask;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Timer;

/**
 * Created by LongFF on 2018/8/28
 */
@Component
public class InitElement implements InitializingBean {
    private static Timer timer = new Timer();
    @Override
    public void afterPropertiesSet() {
//        timer.scheduleAtFixedRate(new TimerPrintTask(),1000,1000);
    }
}
