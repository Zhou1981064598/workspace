package com.example.demo.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
@Component
public class MySessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext application = session.getServletContext();
        Integer UID = (Integer) session.getAttribute("UID");
        if (UID != null) {
            System.out.println("删除用户(" + UID + ")session");
            application.removeAttribute(UID.toString());
            application.removeAttribute(session.getId());
        }
    }
}
