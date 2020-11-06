package com.highlands.common.http.response;

import java.util.List;

/**
 * @author xuliangliang
 * @date 2020/11/6
 * copyright(c) Highlands
 */
public class PolicyBean extends HomeBean {
    public PolicyBean(int type) {
        super(type);
    }
    /**
     * id : 1
     * createAt : 2020-11-04 23:56:17
     * updateAt : 2020-11-04 23:56:17
     * del : false
     * title : 政策政策瞅瞅
     * type : 2
     * userId : 8
     * content : 政策解读解读解读解读解读解读解读解读解读
     * publishDate : 2020-11-04
     * radioUrl : http://jushi-cd.oss-cn-shenzhen.aliyuncs.com/finance/data/%E9%80%97%E6%AF%94%E6%81%B6%E6%90%9E%E6%90%9E%E7%AC%91%E8%83%8C%E6%99%AF%E9%9F%B3%E4%B9%90.mp3
     * videoUrl : http://jushi-cd.oss-cn-shenzhen.aliyuncs.com/finance/data/%E6%A8%AA%E7%89%88%E8%A7%86%E9%A2%91.mp4
     * enclosure : http://jushi-cd.oss-cn-shenzhen.aliyuncs.com/finance/data/111.xlsx
     * praiseNum : 0
     * visitNum : 0
     * sn : 0
     * lecturerInfo : {"id":8,"name":"李时珍","avatar":"https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png","profile":null,"userType":1}
     * categorys : [{"id":1,"createAt":"2020-11-02 21:56:13","updateAt":"2020-11-02 21:56:13","del":false,"title":"制造业","cover":"https://jushi-cd.oss-cn-shenzhen.aliyuncs.com/finance/202011/6c511ea1c3b24415a991a21f9f6c42ce.jpg","status":1,"sn":0}]
     * labels : [{"id":1,"del":false,"name":"增值税","color":"红"},{"id":2,"del":false,"name":"营业税","color":"白"}]
     */

    private int id;
    private String createAt;
    private String updateAt;
    private boolean del;
    private String title;
    private int type;
    private int userId;
    private String content;
    private String publishDate;
    private String radioUrl;
    private String videoUrl;
    private String imageUrl;
    private String enclosure;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getRadioUrl() {
        return radioUrl;
    }

    public void setRadioUrl(String radioUrl) {
        this.radioUrl = radioUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
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
