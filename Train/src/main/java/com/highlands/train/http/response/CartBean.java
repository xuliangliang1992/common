package com.highlands.train.http.response;

import java.io.Serializable;
import java.util.List;


/**
 * @author xuliangliang
 * @date 2019-10-25
 * copyright(c) Highlands
 */
public class CartBean implements Serializable {
    public String shopName;
    public long shopId;
    public List<GoodBean> goods;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public List<GoodBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodBean> goods) {
        this.goods = goods;
    }
}
