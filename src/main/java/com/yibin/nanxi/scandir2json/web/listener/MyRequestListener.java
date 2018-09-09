package com.yibin.nanxi.scandir2json.web.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by LongFF on 2018/9/9
 */
@WebListener
public class MyRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest servletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        System.out.println(servletRequest.getParameter("path"));
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
