package com.github.mixu78.mixulib.lib;

import java.util.Objects;

public class KVPair<K,V> {
    private K key;
    private V value;

    public KVPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (!(o instanceof KVPair)) return false;
        else {
            KVPair pair2 = (KVPair) o;
            if (this.key != null) {
                if (!this.key.equals(pair2.key)) {
                    return false;
                }
                else if (pair2.key != null) {
                    return false;
                }
            }
            if (this.value != null) {
                if (!this.value.equals(pair2.value)) {
                    return false;
                }
            } else if (pair2.value != null) {
                return false;
            }

            return true;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
