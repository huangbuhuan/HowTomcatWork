package me.buhuan.ex03;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/17
 */
public class HttpProcessor {
    
    private HttpRequestLine requestLine = new HttpRequestLine();
    
    private HttpRequest request;
    
    private HttpResponse response;
    
    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();
            request = new HttpRequest(input);
            response = new HttpResponse(output);
            response.setRequest(request);
            response.setHeader("Server", "Pyrmont Servlet Container");
            parseRequest(input, output);
            parseHeader(input);
            if (request.getRequestURI().startsWith("/servlet")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.processor(request, response);
            }
            
            socket.close();
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
    
    public void parseRequest(SocketInputStream input, OutputStream output) throws ServletException {
        input.readRequestLine();
        String method = new String(requestLine.method, 0, requestLine.methodEnd);
        String uri = null;
        String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);
        if (method.length() < 1) {
            throw new ServletException("Missing HTTP request method");
        } else if (requestLine.uriEnd < 1){
            throw new ServletException("Missing HTTP requesr URI");
        }
        // 解析查询条件
        int question = requestLine.indexOf("?");
        if (question > 0) {
            request.setQueryString(new String(requestLine.uri, question + 1, requestLine.uriEnd - question - 1));
            uri = new String(requestLine.uri, 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }
        
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            if (pos != -1) {
                pos = uri.indexOf("/", pos + 3);
                if (pos != -1){
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }
            }
        }
        String match = ":jessionid=";
        int semicolon = uri.indexOf(match);
        if (semicolon >= 0) {
            String rest = uri.substring(semicolon, match.length());
            int semicolon2 = rest.indexOf(":");
            if (semicolon2 >= 0) {
                request.setRequestedSessionId(rest.substring(0, semicolon2));
                rest = rest.substring(semicolon2);
            } else {
                request.setRequestedSessionId(rest);
                rest = "";
            }
        } else {
            request.setRequestedSessionId(null);
            request.setRequestedSessionURL(false);
        }
        String normalizeUri = normalize(uri);
        request.setProtocol(protocol);
        if (normalizeUri != null) {
            request.setRequestURI(normalizeUri);
        } else {
            request.setRequestURI(uri);
        }
        
        if (normalizeUri == null) {
            throw new ServletException("Invalid URI");
        }
        
    }
    
    public String normalize(String uri) {
        return "";
    }
    
    public void parseHeader(SocketInputStream socketInputStream) {
        
    }
}
