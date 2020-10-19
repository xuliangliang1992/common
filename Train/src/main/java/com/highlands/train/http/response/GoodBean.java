package com.highlands.train.http.response;

import java.io.Serializable;

import lombok.Data;

/**
 * @author xuliangliang
 * @date 2019-11-01
 * copyright(c) Highlands
 */
@Data
public class GoodBean implements Serializable {
    public String goodName;
    public long goodId;
    public double price;
    public int num;
}
