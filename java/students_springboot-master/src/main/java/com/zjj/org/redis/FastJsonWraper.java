package com.zjj.org.redis;

/**
 * @author zjj
 * @date 2022-04-30
 */
public class FastJsonWraper<T> {
    private T value;

    public FastJsonWraper() {
    }

    public FastJsonWraper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
