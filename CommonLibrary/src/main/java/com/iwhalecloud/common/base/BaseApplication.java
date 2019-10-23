package com.iwhalecloud.common.base;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;

import com.alibaba.android.arouter.launcher.ARouter;
import com.iwhalecloud.common.commonlibrary.BuildConfig;
import com.iwhalecloud.common.util.FileUtil;
import com.umeng.commonsdk.UMConfigure;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

import timber.log.Timber;

/**
 * application基类 主要做一些共有的初始化动作
 *
 * @author xll
 * @date 2018/1/1
 */
public class BaseApplication extends Application {
    protected static BaseApplication instance;

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
        instance = this;
        init();
        initLogger();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Timber.i("width = " + displayMetrics.widthPixels + "\n" + "height = " + displayMetrics.heightPixels);
        initRouter(this);

        UMConfigure.setLogEnabled(BuildConfig.LOG_DEBUG);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public static void initRouter(Application application) {
        if (BuildConfig.LOG_DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application);
    }

    /**
     * 初始化日志
     */
    private void initLogger() {
        if (BuildConfig.LOG_DEBUG) {//Timber初始化
            //Timber 是一个日志框架容器,外部使用统一的Api,内部可以动态的切换成任何日志框架(打印策略)进行日志打印
            //并且支持添加多个日志框架(打印策略),做到外部调用一次 Api,内部却可以做到同时使用多个策略
            //比如添加三个策略,一个打印日志,一个将日志保存本地,一个将日志上传服务器
            Timber.plant(new Timber.DebugTree());
            // 如果你想将框架切换为 Logger 来打印日志,请使用下面的代码,如想切换为其他日志框架请根据下面的方式扩展
            //                    Logger.addLogAdapter(new AndroidLogAdapter());
            //                    Timber.plant(new Timber.DebugTree() {
            //                        @Override
            //                        protected void log(int priority, String tag, String message, Throwable t) {
            //                            Logger.log(priority, tag, message, t);
            //                        }
            //                    });
        }
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
            Timber.d("APP_NAME == " + APP_NAME);
            APP_VERSION_CODE = pi.versionCode;
            APP_VERSION_NAME = pi.versionName;
            APP_PACKAGE = pi.packageName;
            File extStorage = Environment.getExternalStorageDirectory();
            FINGERPRINT = Build.FINGERPRINT;
            OS_VERSION = Build.VERSION.RELEASE;
            BRAND = Build.BRAND;
            MODEL = Build.MODEL;
            SERIAL = Build.SERIAL;

            Timber.i(APP_NAME + " (" + APP_PACKAGE + ")" + APP_VERSION_NAME + "(" + pi.versionCode + ")");
            Timber.i("Root             dir: " + Environment.getRootDirectory());
            Timber.i("Data             dir: " + Environment.getDataDirectory());
            Timber.i("External storage dir: " + extStorage);
            Timber.i("Files            dir: " + FileUtil.getAbsolutePath(getFilesDir()));
            Timber.i("Cache            dir: " + FileUtil.getAbsolutePath(getCacheDir()));
            Timber.i("System locale       : " + defLocale);
            Timber.i("BOARD       : " + Build.BOARD);
            Timber.i("BRAND       : " + Build.BRAND);
            Timber.i("CPU_ABI     : " + buildProps.getProperty("ro.product.cpu.abi"));
            Timber.i("CPU_ABI2    : " + buildProps.getProperty("ro.product.cpu.abi2"));
            Timber.i("DEVICE      : " + Build.DEVICE);
            Timber.i("DISPLAY     : " + Build.DISPLAY);
            Timber.i("FINGERPRINT : " + Build.FINGERPRINT);
            Timber.i("ID          : " + Build.ID);
            Timber.i("MANUFACTURER: " + buildProps.getProperty("ro.product.manufacturer"));
            Timber.i("MODEL       : " + Build.MODEL);
            Timber.i("PRODUCT     : " + Build.PRODUCT);
            Timber.i("RELEASE VERSION:" + Build.VERSION.RELEASE);
            Timber.i("SERIAL" + Build.SERIAL);
        } catch (final PackageManager.NameNotFoundException e) {
            Timber.w("init NameNotFoundException" + e);
        }
    }

}
