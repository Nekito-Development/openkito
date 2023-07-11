package wtf.norma.nekito.util.render.models;

import java.util.HashMap;


// author: saph
// thanks for it


public class HashMapWithDefault<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = 5995791692010816132L;
    private V defaultValue;

    public HashMapWithDefault() {
    }

    public void setDefault(V value) {
        this.defaultValue = value;
    }

    public V getDefault() {
        return this.defaultValue;
    }

    public V get(Object key) {
        return this.containsKey(key) ? super.get(key) : this.defaultValue;
    }
}

