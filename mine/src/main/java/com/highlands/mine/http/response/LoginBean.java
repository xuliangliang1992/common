package com.highlands.mine.http.response;


import java.util.List;

/**
 * @author xll
 * @date 2019-07-19
 */
public class LoginBean {
    boolean login_success;
    String error;
    List<User> shop_info;

    public boolean isLogin_success() {
        return login_success;
    }

    public void setLogin_success(boolean login_success) {
        this.login_success = login_success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<User> getShop_info() {
        return shop_info;
    }

    public void setShop_info(List<User> shop_info) {
        this.shop_info = shop_info;
    }
}
