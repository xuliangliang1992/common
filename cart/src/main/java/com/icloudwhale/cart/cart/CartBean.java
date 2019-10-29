package com.icloudwhale.cart.cart;

import java.util.List;

import lombok.Data;

/**
 * @author xuliangliang
 * @date 2019-10-25
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Data
public class CartBean {
    public String shopName;
    public long shopId;
    public List<GoodBean> goods;

    @Data
    public class GoodBean {
        public String goodName;
        public long goodId;
        public double price;
        public int num;
    }
}
