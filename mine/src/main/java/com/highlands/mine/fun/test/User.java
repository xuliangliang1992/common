package com.highlands.mine.fun.test;

/**
 * @author xuliangliang
 * @date 2019-09-10
 * copyright(c) Highlands
 */
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
