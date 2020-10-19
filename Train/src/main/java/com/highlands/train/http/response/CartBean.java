package com.highlands.train.http.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author xuliangliang
 * @date 2019-10-25
 * copyright(c) Highlands
 */
@Data
public class CartBean implements Serializable {
    public String shopName;
    public long shopId;
    public List<GoodBean> goods;

}
