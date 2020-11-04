package com.highlands.common.constant;

/**
 * @author xll
 * @date 2019-07-05
 */
public class RouterUrl {


    public static final String SERVICE_JSON = "/service/json";


    public static final String ACTIVITY = "/activity/";
    public static final String FRAGMENT = "/fragment/";
    public static final String SERVICE = "/service/";

    public static final String TAX = "/tax";
    public static final String HOME = "/home";
    public static final String INFORMATION = "/information";
    public static final String TRAIN = "/train";
    public static final String MINE = "/mine";


    public static final String TAX_MAIN = TAX + ACTIVITY + "main";
    public static final String TAX_SPLASH = TAX + ACTIVITY + "splash";
    public static final String TAX_LOGIN = TAX + ACTIVITY + "login";
    public static final String TAX_REGISTER = TAX + ACTIVITY + "register";

    public static final String HOME_FRAGMENT_HOME = HOME + FRAGMENT + "home";

    public static final String INFORMATION_FRAGMENT_COLUMN = INFORMATION + FRAGMENT + "column";
    public static final String INFORMATION_FRAGMENT_DISCOVER = INFORMATION + FRAGMENT + "discover";

    public static final String TRAIN_FRAGMENT_CART = TRAIN + FRAGMENT + "cart";


    public static final String HISTORY = "history/";
    public static final String MINE_FRAGMENT_MINE = MINE + FRAGMENT + "mine";
    public static final String MINE_ACTIVITY_HISTORY = MINE + ACTIVITY + "history";
    public static final String MINE_FRAGMENT_HISTORY_POLICY = MINE + FRAGMENT +HISTORY+ "policy";
    public static final String MINE_FRAGMENT_HISTORY_VIDEO = MINE + FRAGMENT +HISTORY+ "video";
    public static final String MINE_FRAGMENT_HISTORY_LIVE = MINE + FRAGMENT +HISTORY+ "live";


}
