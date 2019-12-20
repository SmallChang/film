package org.cly.controller.common;

public final class TraceUtil {

    private TraceUtil(){}

    public  static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void initUserId(String userId){
        threadLocal.set(userId);
    }

    public static String getUserId(){
        return threadLocal.get();
    }
}
