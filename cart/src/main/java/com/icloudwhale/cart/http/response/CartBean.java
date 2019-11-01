package com.icloudwhale.cart.http.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author xuliangliang
 * @date 2019-10-25
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Data
public class CartBean implements Serializable {
    public String shopName;
    public long shopId;
    public List<GoodBean> goods;

}
