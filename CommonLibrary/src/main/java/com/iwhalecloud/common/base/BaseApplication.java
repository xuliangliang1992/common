package com.iwhalecloud.common.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.iwhalecloud.common.commonlibrary.BuildConfig;
import com.iwhalecloud.common.util.FileUtil;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
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
    private static final String TAG = "BaseApplication";
    protected static BaseApplication instance;

    public static long APP_VERSION_CODE;
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

    public interface MsgDisplayListener {
        void handle(String msg);
    }

    public static MsgDisplayListener msgDisplayListener = null;
    public static StringBuilder cacheMsg = new StringBuilder();


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        initLogger();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Timber.tag(TAG).d("width = " + displayMetrics.widthPixels + "\n" + "height = " + displayMetrics.heightPixels);
        initRouter(this);

        UMConfigure.setLogEnabled(BuildConfig.LOG_DEBUG);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");

        initCloudChannel(this);
        initManService(this);
    }

    private void initSophix() {
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }

        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                //.setAesKey("0123456789123456")
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        String msg = new StringBuilder("").append("Mode:").append(mode)
                                .append(" Code:").append(code)
                                .append(" Info:").append(info)
                                .append(" HandlePatchVersion:").append(handlePatchVersion).toString();
                        if (msgDisplayListener != null) {
                            msgDisplayListener.handle(msg);
                            Timber.tag("sophix").d(msg);
                        } else {
                            cacheMsg.append("\n").append(msg);
                        }
                    }
                }).initialize();
    }

    private void initManService(Application application) {
        /* 【注意】建议您在Application中初始化MAN，以保证正常获取MANService*/
        // 获取MAN服务
        MANService manService = MANServiceProvider.getService();
        // 打开调试日志，线上版本建议关闭
        manService.getMANAnalytics().turnOnDebug();
        // 若需要关闭 SDK 的自动异常捕获功能可进行如下操作(如需关闭crash report，建议在init方法调用前关闭crash),详见文档5.4
        //        manService.getMANAnalytics().turnOffCrashReporter();
        // 设置渠道（用以标记该app的分发渠道名称），如果不关心可以不设置即不调用该接口，渠道设置将影响控制台【渠道分析】栏目的报表展现。如果文档3.3章节更能满足您渠道配置的需求，就不要调用此方法，按照3.3进行配置即可；1.1.6版本及之后的版本，请在init方法之前调用此方法设置channel.
        //        manService.getMANAnalytics().setChannel("某渠道");
        // MAN初始化方法之一，从AndroidManifest.xml中获取appKey和appSecret初始化，若您采用上述 2.3中"统一接入的方式"，则使用当前init方法即可。
        manService.getMANAnalytics().init(application, getApplicationContext());
        // MAN另一初始化方法，手动指定appKey和appSecret
        // 若您采用上述2.3中"统一接入的方式"，则无需使用当前init方法。
        // String appKey = "******";
        // String appSecret = "******";
        // manService.getMANAnalytics().init(this, getApplicationContext(), appKey, appSecret);
        // 通过此接口关闭页面自动打点功能，详见文档4.2
        //        manService.getMANAnalytics().turnOffAutoPageTrack();
        // 若AndroidManifest.xml 中的 android:versionName 不能满足需求，可在此指定
        // 若在上述两个地方均没有设置appversion，上报的字段默认为null
        //        manService.getMANAnalytics().setAppVersion("3.1.1");
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * 初始化ARouter
     *
     * @param application application
     */
    public static void initRouter(Application application) {
        if (BuildConfig.LOG_DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application);
    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext application
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Timber.tag(TAG).d("init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Timber.tag(TAG).d("init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });

      /*  // 注册方法会自动判断是否支持小米系统推送，如不支持会跳过注册。
        MiPushRegister.register(applicationContext, "小米AppID", "小米AppKey");
        // 注册方法会自动判断是否支持华为系统推送，如不支持会跳过注册。
        HuaWeiRegister.register(this);
        //GCM/FCM辅助通道注册
//        GcmRegister.register(this, sendId, applicationId); //sendId/applicationId为步骤获得的参数
        // OPPO通道注册
//        OppoRegister.register(applicationContext, appKey, appSecret); // appKey/appSecret在OPPO开发者平台获取
        // 魅族通道注册
        MeizuRegister.register(applicationContext, "appId", "appkey"); // appId/appkey在魅族开发者平台获取
        // VIVO通道注册
        VivoRegister.register(applicationContext);*/
    }

    /**
     * 初始化日志
     */
    private void initLogger() {
        if (BuildConfig.LOG_DEBUG) {
            //Timber初始化
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            defLocale = config.getLocales().get(0);
        } else {
            defLocale = config.locale;
        }
        Properties buildProps = new Properties();
        try {
            buildProps.load(new FileInputStream("/system/build.prop"));
        } catch (final Throwable th) {
            th.printStackTrace();
        }

        final PackageManager pm = getPackageManager();
        try {
            final PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);

            APP_NAME = getString(pi.applicationInfo.labelRes);
            Timber.tag(TAG).d("APP_NAME == " + APP_NAME);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                APP_VERSION_CODE = pi.getLongVersionCode();
            } else {
                APP_VERSION_CODE = pi.versionCode;
            }
            APP_VERSION_NAME = pi.versionName;
            APP_PACKAGE = pi.packageName;
            File extStorage = Environment.getExternalStorageDirectory();
            FINGERPRINT = Build.FINGERPRINT;
            OS_VERSION = Build.VERSION.RELEASE;
            BRAND = Build.BRAND;
            MODEL = Build.MODEL;
            SERIAL = Build.SERIAL;

            Timber.tag(TAG).d(APP_NAME + " (" + APP_PACKAGE + ")" + APP_VERSION_NAME + "(" + pi.versionCode + ")");
            Timber.tag(TAG).d("Root             dir: " + Environment.getRootDirectory());
            Timber.tag(TAG).d("Data             dir: " + Environment.getDataDirectory());
            Timber.tag(TAG).d("External storage dir: " + extStorage);
            Timber.tag(TAG).d("Files            dir: " + FileUtil.getAbsolutePath(getFilesDir()));
            Timber.tag(TAG).d("Cache            dir: " + FileUtil.getAbsolutePath(getCacheDir()));
            Timber.tag(TAG).d("System locale       : " + defLocale);
            Timber.tag(TAG).d("BOARD       : " + Build.BOARD);
            Timber.tag(TAG).d("BRAND       : " + Build.BRAND);
            Timber.tag(TAG).d("CPU_ABI     : " + buildProps.getProperty("ro.product.cpu.abi"));
            Timber.tag(TAG).d("CPU_ABI2    : " + buildProps.getProperty("ro.product.cpu.abi2"));
            Timber.tag(TAG).d("DEVICE      : " + Build.DEVICE);
            Timber.tag(TAG).d("DISPLAY     : " + Build.DISPLAY);
            Timber.tag(TAG).d("FINGERPRINT : " + Build.FINGERPRINT);
            Timber.tag(TAG).d("ID          : " + Build.ID);
            Timber.tag(TAG).d("MANUFACTURER: " + buildProps.getProperty("ro.product.manufacturer"));
            Timber.tag(TAG).d("MODEL       : " + Build.MODEL);
            Timber.tag(TAG).d("PRODUCT     : " + Build.PRODUCT);
            Timber.tag(TAG).d("RELEASE VERSION:" + Build.VERSION.RELEASE);
            Timber.tag(TAG).d(String.format("SERIAL%s", SERIAL));
        } catch (final PackageManager.NameNotFoundException e) {
            Timber.tag(TAG).w("init NameNotFoundException" + e);
        }
    }

}
