package me.buhuan.ex02;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/15
 */
public class ServletProcessor1 {
    
    public void process(Request request, Response response) {
        String uri = request.getUri();
        int lastIndex = uri.lastIndexOf("/");
        if (lastIndex == -1) {
            return;
        }
        String servletName = uri.substring(lastIndex + 1);
        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file", null, classPath.getCanonicalPath() +
                File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        Servlet servlet = null;
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(request, response);
            
        } catch (IllegalAccessException | IOException | ServletException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
