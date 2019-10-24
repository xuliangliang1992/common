package com.icloudwhale.home.home;

import lombok.Data;

/**
 * @author xuliangliang
 * @date 2019-10-23
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Data
public class HomeBean {
    public int num;

    public HomeBean(int num) {
        this.num = num;
    }
}
