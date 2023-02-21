package com.awx.moxu.utils.R;

import java.util.HashMap;

import org.springframework.util.LinkedCaseInsensitiveMap;

public class Kv extends LinkedCaseInsensitiveMap<Object> {
    private Kv() {
    }

    public static Kv init() {
        return new Kv();
    }

    public static <K, V> HashMap<K, V> newMap() {
        return new HashMap(16);
    }

    public Kv set(String attr, Object value) {
        this.put(attr, value);
        return this;
    }

    public Kv setIgnoreNull(String attr, Object value) {
        if (null != attr && null != value) {
            this.set(attr, value);
        }

        return this;
    }

    public Object getObj(String key) {
        return super.get(key);
    }

    public <T> T get(String attr, T defaultValue) {
        Object result = this.get(attr);
        return result != null ? (T) result : defaultValue;
    }

    @Override
    public Kv clone() {
        Kv clone = new Kv();
        clone.putAll(this);
        return clone;
    }
}
