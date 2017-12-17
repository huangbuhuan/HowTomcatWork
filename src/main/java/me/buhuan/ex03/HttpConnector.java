package me.buhuan.ex03;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/17
 */
public class HttpConnector implements Runnable{
    
    private String scheme = "http";
    
    boolean stopped;
    
    private Stack<HttpProcessor> processors = new Stack<>();
    
    protected int minProcessors = 5;
    
    protected int maxProcessors = 20;
    
    protected int curProcessors = 0;
    
    public String getScheme() {
        return scheme;
    }
    
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stopped){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                continue;
            }
            HttpProcessor httpProcessor = createProcessor();
            if (httpProcessor == null) {
                try {
                    System.out.println("httpConnector.noProcessor");
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }
            httpProcessor.process(socket);
        }
        
    }
    
    private HttpProcessor createProcessor() {
        HttpProcessor processor = processors.pop();
        if (processor == null && curProcessors == maxProcessors) {
            return null;
        } else if (processor == null) {
            curProcessors++;
            return new HttpProcessor();
        }
        return processor;
    }
    
    public void start() {
        while (curProcessors < minProcessors) {
            if (maxProcessors > 0 && curProcessors >= maxProcessors) {
                break;
            }
            HttpProcessor processor = new HttpProcessor();
            recycle(processor);
        }
        Thread thread = new Thread(this);
        thread.start();
    }
    
    private void recycle(HttpProcessor processor) {
        processors.push(processor);
    }
    
}
