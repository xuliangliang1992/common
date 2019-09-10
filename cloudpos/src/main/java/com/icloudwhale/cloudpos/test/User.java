package com.icloudwhale.cloudpos.test;

import lombok.Data;

/**
 * @author xuliangliang
 * @date 2019-09-10
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Data
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
