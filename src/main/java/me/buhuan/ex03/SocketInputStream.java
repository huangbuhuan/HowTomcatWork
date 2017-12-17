package me.buhuan.ex03;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/17
 */
public class SocketInputStream extends InputStream {
   
    private InputStream inputStream;
    private int bufferSize;
    
    public SocketInputStream(InputStream inputStream, int bufferSize) {
        this.inputStream = inputStream;
        this.bufferSize = bufferSize;
    }
    
    public InputStream getInputStream() {
        return inputStream;
    }
    
    public String readRequestLine() {
        return "";
    }
    
    public String readHeader(HttpHeader httpHeader) {
        
        return "";
    }
    
    @Override
    public int read() throws IOException {
        return 0;
    }
}
