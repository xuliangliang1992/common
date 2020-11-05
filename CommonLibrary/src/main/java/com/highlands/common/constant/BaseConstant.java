package com.highlands.common.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author xll
 * @date 2019-05-10
 */
public class BaseConstant {

    public static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    public static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;

    public static final String BASE_URL = "http://api.finance.baiku.co/";

    public static final String SHARED_PREFERENCE_FILE_NAME = "";

    public static String LOGIN_URL = "https://cloudshop.iwhalecloud.com/";

    public static final String PAID = "paid";
    public static final String DONE = "done";
    public static final String INVOICED = "invoiced";
    public static final String PREPARING = "preparing";
    public static final String DRAFT = "draft";
    public static final String CANCEL = "cancel";
    public static final String DELIVER = "delivering";
    public static final String WAITING = "waiting";

    public static final String SUCCESS = "success";

    public static final String HTTP_ERROR = "HTTP";

    public final static List<String> PAYMENT_MODE_LIST = Arrays.asList("微信", "支付宝");


    /**
     * 系统网络改变广播
     */
    public static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
}
