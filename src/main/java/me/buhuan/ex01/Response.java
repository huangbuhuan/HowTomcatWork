package me.buhuan.ex01;

import java.io.*;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/15
 */
public class Response {
    
    private static final int BUFFER_SIZE = 1024;
    
    Request request;
    
    OutputStream output;
    
    public Response(OutputStream output) {
        this.output = output;
    }
    
    public void serRequest(Request request) {
        this.request = request;
    }
    
    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                fis.close();
            }
            
        }
    }
}
