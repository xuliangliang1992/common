package com.iwhalecloud.common.base;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;

import com.alibaba.android.arouter.launcher.ARouter;
import com.iwhalecloud.common.constant.BaseConstant;
import com.iwhalecloud.common.util.FileUtil;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

/**
 * application基类 主要做一些共有的初始化动作
 *
 * @author xll
 * @date 2018/1/1
 */
public class APP extends Application {

    public static int APP_VERSION_CODE;
    public static String APP_VERSION_NAME;

    public static String APP_PACKAGE;
    public static String APP_NAME;

    /**
     * 设备指纹
     */
    public static String FINGERPRINT;

    /**
     * 系统版本号
     */
    public static String OS_VERSION;
    /**
     * 设备名称 例如 huawei  meizu
     */
    public static String BRAND;
    /**
     * 设备型号 例如 BAH-W09  m3
     */
    public static String MODEL;
    /**
     * 序列号
     */
    public static String SERIAL;

    public static Locale defLocale;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        initLogger();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Logger.i("width = " + displayMetrics.widthPixels + "\n" + "height = " + displayMetrics.heightPixels);
        initRouter(this);
    }

    public static void initRouter(Application application) {
        if (BaseConstant.IS_DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application);
    }

    /**
     * 初始化日志
     * 打release版的时候 改为NONE
     */
    private void initLogger() {
        Logger.init(APP.class.getSimpleName()).methodCount(3)
                .hideThreadInfo()
                .logLevel(LogLevel.FULL)
                .methodCount(1)
                .methodOffset(5);
    }

    /**
     * 初始化基础信息
     */
    protected void init() {
        final Configuration config = getApplicationContext().getResources().getConfiguration();
        defLocale = config.locale;
        Properties buildProps = new Properties();
        try {
            buildProps.load(new FileInputStream("/system/build.prop"));
        } catch (final Throwable th) {
        }

        final PackageManager pm = getPackageManager();
        try {
            final PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);

            APP_NAME = getString(pi.applicationInfo.labelRes);
            Logger.d("info", "APP_NAME == " + APP_NAME);
            APP_VERSION_CODE = pi.versionCode;
            APP_VERSION_NAME = pi.versionName;
            APP_PACKAGE = pi.packageName;
            File extStorage = Environment.getExternalStorageDirectory();
            FINGERPRINT = Build.FINGERPRINT;
            OS_VERSION = Build.VERSION.RELEASE;
            BRAND = Build.BRAND;
            MODEL = Build.MODEL;
            SERIAL = Build.SERIAL;

            Logger.i(APP_NAME + " (" + APP_PACKAGE + ")" + APP_VERSION_NAME + "(" + pi.versionCode + ")");
            Logger.i("Root             dir: " + Environment.getRootDirectory());
            Logger.i("Data             dir: " + Environment.getDataDirectory());
            Logger.i("External storage dir: " + extStorage);
            Logger.i("Files            dir: " + FileUtil.getAbsolutePath(getFilesDir()));
            Logger.i("Cache            dir: " + FileUtil.getAbsolutePath(getCacheDir()));
            Logger.i("System locale       : " + defLocale);
            Logger.i("BOARD       : " + Build.BOARD);
            Logger.i("BRAND       : " + Build.BRAND);
            Logger.i("CPU_ABI     : " + buildProps.getProperty("ro.product.cpu.abi"));
            Logger.i("CPU_ABI2    : " + buildProps.getProperty("ro.product.cpu.abi2"));
            Logger.i("DEVICE      : " + Build.DEVICE);
            Logger.i("DISPLAY     : " + Build.DISPLAY);
            Logger.i("FINGERPRINT : " + Build.FINGERPRINT);
            Logger.i("ID          : " + Build.ID);
            Logger.i("MANUFACTURER: " + buildProps.getProperty("ro.product.manufacturer"));
            Logger.i("MODEL       : " + Build.MODEL);
            Logger.i("PRODUCT     : " + Build.PRODUCT);
            Logger.i("RELEASE VERSION:" + Build.VERSION.RELEASE);
            Logger.i("SERIAL" + Build.SERIAL);
        } catch (final PackageManager.NameNotFoundException e) {
            Logger.w("init NameNotFoundException", e);
        }
    }

}
