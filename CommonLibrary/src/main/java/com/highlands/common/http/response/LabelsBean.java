package com.highlands.common.http.response;

/**
 * @author xuliangliang
 * @date 2020/11/6
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class LabelsBean {
    /**
     * id : 1
     * del : false
     * name : 增值税
     * color : 红
     */

    private int id;
    private boolean del;
    private String name;
    private String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
