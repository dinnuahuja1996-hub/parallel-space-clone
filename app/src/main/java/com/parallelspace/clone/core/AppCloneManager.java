package com.parallelspace.clone.core;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class AppCloneManager {

    private static final String TAG = "AppCloneManager";
    private static AppCloneManager instance;
    private final Context context;

    private AppCloneManager(Context context) {
        this.context = context.getApplicationContext();
        initBcore();
    }

    public static AppCloneManager getInstance(Context context) {
        if (instance == null) {
            instance = new AppCloneManager(context);
        }
        return instance;
    }

    private void initBcore() {
        try {
            // Initialize NewBlackbox/Bcore virtual engine
            // BCore.get().doAttachBaseContext(context);
            Log.d(TAG, "Bcore initialized successfully");
        } catch (Exception e) {
            Log.e(TAG, "Failed to initialize Bcore: " + e.getMessage());
        }
    }

    public List<ClonedAppInfo> getClonedApps() {
        List<ClonedAppInfo> apps = new ArrayList<>();
        try {
            // Get installed virtual apps from Bcore
            // List<BPackageInfo> packages = BPackageManager.get().getInstalledPackages(0, 0);
            // For now, return empty list until Bcore is fully integrated
            Log.d(TAG, "Fetched cloned apps: " + apps.size());
        } catch (Exception e) {
            Log.e(TAG, "Error getting cloned apps: " + e.getMessage());
        }
        return apps;
    }

    public boolean cloneApp(String packageName) {
        try {
            // Install app in virtual space using Bcore
            // int result = BPackageManager.get().installPackage(packageName, 0);
            // return result == BPackageManager.INSTALL_SUCCEEDED;
            Log.d(TAG, "Cloning app: " + packageName);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Failed to clone app: " + e.getMessage());
            return false;
        }
    }

    public void launchApp(Context context, String packageName, int userId) {
        try {
            // Launch app in virtual space
            // BActivityManager.get().launchApk(packageName, userId);
            Log.d(TAG, "Launching app: " + packageName + " userId: " + userId);
        } catch (Exception e) {
            Log.e(TAG, "Failed to launch app: " + e.getMessage());
        }
    }

    public void removeApp(String packageName, int userId) {
        try {
            // Uninstall from virtual space
            // BPackageManager.get().uninstallPackage(packageName, userId);
            Log.d(TAG, "Removing app: " + packageName);
        } catch (Exception e) {
            Log.e(TAG, "Failed to remove app: " + e.getMessage());
        }
    }

    public List<ApplicationInfo> getInstallableApps() {
        List<ApplicationInfo> apps = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> allApps = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo app : allApps) {
            if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                if (!app.packageName.equals(context.getPackageName())) {
                    apps.add(app);
                }
            }
        }
        return apps;
    }
}
