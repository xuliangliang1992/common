package com.highlands.train.http.response;

import java.io.Serializable;

/**
 * @author xuliangliang
 * @date 2019-11-01
 * copyright(c) Highlands
 */
public class GoodBean implements Serializable {
    public String goodName;
    public long goodId;
    public double price;
    public int num;

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
