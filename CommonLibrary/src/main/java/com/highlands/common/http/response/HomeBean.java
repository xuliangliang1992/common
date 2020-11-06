package com.highlands.common.http.response;

/**
 * @author xuliangliang
 * @date 2019-10-23
 * copyright(c) Highlands
 */
public class HomeBean {

    int viewType;


    public HomeBean(int viewType) {
        this.viewType = viewType;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "type=" + viewType +
                '}';
    }
}
