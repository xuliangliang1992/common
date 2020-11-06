package com.highlands.common.http.response;

/**
 * @author xuliangliang
 * @date 2020/11/6
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class CategorysBean {
    /**
     * id : 1
     * createAt : 2020-11-02 21:56:13
     * updateAt : 2020-11-02 21:56:13
     * del : false
     * title : 制造业
     * cover : https://jushi-cd.oss-cn-shenzhen.aliyuncs.com/finance/202011/6c511ea1c3b24415a991a21f9f6c42ce.jpg
     * status : 1
     * sn : 0
     */

    private int id;
    private String createAt;
    private String updateAt;
    private boolean del;
    private String title;
    private String cover;
    private int status;
    private int sn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

}
