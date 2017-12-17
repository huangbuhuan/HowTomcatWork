package me.buhuan.ex02;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/17
 */
public class RequestFacade implements ServletRequest {
    
    private ServletRequest request;
    
    public RequestFacade(Request request) {
        this.request = request;
    }
    
    @Override
    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }
    
    @Override
    public Enumeration<String> getAttributeNames() {
        return request.getAttributeNames();
    }
    
    @Override
    public String getCharacterEncoding() {
        return request.getCharacterEncoding();
    }
    
    @Override
    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
        request.setCharacterEncoding(env);
    }
    
    @Override
    public int getContentLength() {
        return request.getContentLength();
    }
    
    @Override
    public long getContentLengthLong() {
        return request.getContentLengthLong();
    }
    
    @Override
    public String getContentType() {
        return request.getContentType();
    }
    
    @Override
    public ServletInputStream getInputStream() throws IOException {
        return request.getInputStream();
    }
    
    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }
    
    @Override
    public Enumeration<String> getParameterNames() {
        return request.getParameterNames();
    }
    
    @Override
    public String[] getParameterValues(String name) {
        return request.getParameterValues(name);
    }
    
    @Override
    public Map<String, String[]> getParameterMap() {
        return request.getParameterMap();
    }
    
    @Override
    public String getProtocol() {
        return request.getProtocol();
    }
    
    @Override
    public String getScheme() {
        return request.getScheme();
    }
    
    @Override
    public String getServerName() {
        return request.getServerName();
    }
    
    @Override
    public int getServerPort() {
        return request.getServerPort();
    }
    
    @Override
    public BufferedReader getReader() throws IOException {
        return request.getReader();
    }
    
    @Override
    public String getRemoteAddr() {
        return request.getRemoteAddr();
    }
    
    @Override
    public String getRemoteHost() {
        return null;
    }
    
    @Override
    public void setAttribute(String name, Object o) {
        
    }
    
    @Override
    public void removeAttribute(String name) {
        
    }
    
    @Override
    public Locale getLocale() {
        return null;
    }
    
    @Override
    public Enumeration<Locale> getLocales() {
        return null;
    }
    
    @Override
    public boolean isSecure() {
        return false;
    }
    
    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }
    
    @Override
    public String getRealPath(String path) {
        return null;
    }
    
    @Override
    public int getRemotePort() {
        return 0;
    }
    
    @Override
    public String getLocalName() {
        return null;
    }
    
    @Override
    public String getLocalAddr() {
        return null;
    }
    
    @Override
    public int getLocalPort() {
        return 0;
    }
    
    @Override
    public ServletContext getServletContext() {
        return null;
    }
    
    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }
    
    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }
    
    @Override
    public boolean isAsyncStarted() {
        return false;
    }
    
    @Override
    public boolean isAsyncSupported() {
        return false;
    }
    
    @Override
    public AsyncContext getAsyncContext() {
        return null;
    }
    
    @Override
    public DispatcherType getDispatcherType() {
        return null;
    }
}
