package com.yibin.nanxi.scandir2json.web.listener;

import com.yibin.nanxi.scandir2json.task.TimerPrintTask;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Timer;

/**
 * Created by LongFF on 2018/9/9
 */

@WebListener
public class MyListener implements ServletContextListener {

    private Timer timer = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        timer = new Timer(true);
        servletContextEvent.getServletContext().log("定时器已启动");
//        timer.scheduleAtFixedRate(new TimerPrintTask(),1000,1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (timer != null) {
            timer.cancel();
            servletContextEvent.getServletContext().log("定时器销毁");
        }
    }
}
