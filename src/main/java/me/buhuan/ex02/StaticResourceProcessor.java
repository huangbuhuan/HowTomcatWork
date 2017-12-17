package me.buhuan.ex02;


import java.io.IOException;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/15
 */
public class StaticResourceProcessor {
    
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
