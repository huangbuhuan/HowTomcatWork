package me.buhuan.ex03;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/17
 */
public class ParameterMap extends HashMap{
    
    
    public ParameterMap() {
        super();
    }
    
    public ParameterMap(int initialCapacity) {
        super(initialCapacity);
    }
    
    public ParameterMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }
    
    public ParameterMap(Map m) {
        super(m);
    }
    
    private boolean locked = false;
    
    public boolean isLocked() {
        return locked;
    }
    
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    private static final StringManager sm = StringManager.getManager("org.apache.catalina.util;");
    
    public void clear() {
        if (locked) {
            throw new IllegalArgumentException(sm.getString("parameterMap.locked"));
        }
        super.clear();
    }
    
    public Object put(Object key, Object value) {
        if (locked) {
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }
        return (super.put(key, value));
    }
    public void putAll(Map map) {
        if (locked) {
            throw new IllegalStateException (sm.getString("parameterMap.locked"));
        }
        super.putAll(map);
    }
    public Object remove(Object key) {
        if (locked) {
            throw new IllegalStateException (sm.getString("parameterMap.locked"));
        }
        return (super.remove(key)); }
}
