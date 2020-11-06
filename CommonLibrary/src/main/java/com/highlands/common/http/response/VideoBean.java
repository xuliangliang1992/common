package com.highlands.common.http.response;

import java.util.List;

/**
 * @author xuliangliang
 * @date 2020/11/6
 * copyright(c) Highlands
 */
public class VideoBean extends HomeBean {
    public VideoBean(int type) {
        super(type);
    }

    /**
     * id : 1
     * del : false
     * title : 小视频
     * userId : 8
     * content : 这是一个有内容的视频,请仔细查看
     * coverUrl : http://jushi-cd.oss-cn-shenzhen.aliyuncs.com/finance/data/%E6%9C%80%E6%96%B0%E8%A7%86%E9%A2%91%E5%B0%81%E9%9D%A2%E5%9B%BE.png
     * videoUrl : http://jushi-cd.oss-cn-shenzhen.aliyuncs.com/finance/data/%E6%A8%AA%E7%89%88%E8%A7%86%E9%A2%91.mp4
     * praiseNum : 0
     * visitNum : 0
     * sn : 1
     * lecturerInfo : {"id":8,"name":"李时珍","avatar":"https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png","profile":null,"userType":1}
     * categorys : [{"id":1,"createAt":"2020-11-02 21:56:13","updateAt":"2020-11-02 21:56:13","del":false,"title":"制造业","cover":"https://jushi-cd.oss-cn-shenzhen.aliyuncs.com/finance/202011/6c511ea1c3b24415a991a21f9f6c42ce.jpg","status":1,"sn":0},{"id":2,"createAt":"2020-11-02 21:56:14","updateAt":"2020-11-02 21:56:14","del":false,"title":"房地产","cover":"https://jushi-cd.oss-cn-shenzhen.aliyuncs.com/finance/202011/6c511ea1c3b24415a991a21f9f6c42ce.jpg","status":1,"sn":0}]
     * labels : [{"id":1,"del":false,"name":"增值税","color":"红"},{"id":2,"del":false,"name":"营业税","color":"白"}]
     */

    private int id;
    private boolean del;
    private String title;
    private int userId;
    private String content;
    private String coverUrl;
    private String videoUrl;
    private int praiseNum;
    private int visitNum;
    private int sn;
    private LecturerInfoBean lecturerInfo;
    private List<CategorysBean> categorys;
    private List<LabelsBean> labels;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public int getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(int visitNum) {
        this.visitNum = visitNum;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public LecturerInfoBean getLecturerInfo() {
        return lecturerInfo;
    }

    public void setLecturerInfo(LecturerInfoBean lecturerInfo) {
        this.lecturerInfo = lecturerInfo;
    }

    public List<CategorysBean> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategorysBean> categorys) {
        this.categorys = categorys;
    }

    public List<LabelsBean> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelsBean> labels) {
        this.labels = labels;
    }

}
