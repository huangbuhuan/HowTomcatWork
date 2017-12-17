package me.buhuan.ex03;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/17
 */
public class BootStrap {
    
    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
    }
}
