package com.icloudwhale.cloudpos.fun.test;

import lombok.Data;

/**
 * @author xuliangliang
 * @date 2019-09-10
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Data
public class User {
    private String name;
    private boolean type;

    public User(String name,boolean type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
