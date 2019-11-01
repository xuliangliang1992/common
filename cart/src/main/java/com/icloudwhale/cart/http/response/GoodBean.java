package com.icloudwhale.cart.http.response;

import java.io.Serializable;

import lombok.Data;

/**
 * @author xuliangliang
 * @date 2019-11-01
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Data
public class GoodBean implements Serializable {
    public String goodName;
    public long goodId;
    public double price;
    public int num;
}
