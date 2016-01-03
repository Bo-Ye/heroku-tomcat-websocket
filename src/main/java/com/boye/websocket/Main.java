package com.boye.websocket;

import java.io.File;
import javax.servlet.ServletException;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main {

    public static void main(String[] args) throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf("8080"));
        String webappDirLocation = "src/main/webapp/";
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
