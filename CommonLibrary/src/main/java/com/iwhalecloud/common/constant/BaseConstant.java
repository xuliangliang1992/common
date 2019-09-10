package com.iwhalecloud.common.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author xll
 * @date 2019-05-10
 */
public class BaseConstant {

    public static final boolean IS_DEBUG = true;
    public static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    public static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;

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

}
