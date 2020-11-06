package com.highlands.common.http.response;

/**
 * @author xuliangliang
 * @date 2020/11/6
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class LecturerInfoBean {
    /**
     * id : 8
     * name : 李时珍
     * avatar : https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png
     * profile : null
     * userType : 1
     */

    private int id;
    private String name;
    private String avatar;
    private String profile;
    private int userType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}
