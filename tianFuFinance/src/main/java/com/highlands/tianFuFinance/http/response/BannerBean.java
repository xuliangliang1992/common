package com.highlands.tianFuFinance.http.response;

/**
 * @author xuliangliang
 * @date 2020/11/6
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class BannerBean {
    /**
     * id : 1
     * del : false
     * type : 0
     * fileUrl : http://jushi-cd.oss-cn-shenzhen.aliyuncs.com/finance/data/%E9%A6%96%E9%A1%B5banner%20.png
     * content : 测试富文本
     * status : 1
     * sn : 1
     */

    private int id;
    private boolean del;
    private int type;
    private String fileUrl;
    private String content;
    private int status;
    private int sn;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
