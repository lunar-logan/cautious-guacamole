package com.anurag.ams.module;

/**
 * Created by IntelliJ IDEA on 10/30/18
 *
 * @author Anurag Gautam
 */
public interface Module {
    <T> T getSingleton(Class<T> tClass);
}
