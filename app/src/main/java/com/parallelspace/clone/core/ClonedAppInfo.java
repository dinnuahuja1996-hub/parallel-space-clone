package com.parallelspace.clone.core;

public class ClonedAppInfo {
    public String packageName;
    public String appName;
    public int userId;
    public long installTime;

    public ClonedAppInfo(String packageName, String appName, int userId) {
        this.packageName = packageName;
        this.appName = appName;
        this.userId = userId;
        this.installTime = System.currentTimeMillis();
    }
}
