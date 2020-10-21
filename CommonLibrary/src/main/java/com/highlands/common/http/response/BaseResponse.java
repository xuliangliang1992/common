package com.highlands.common.http.response;

/**
 * 基类
 *
 * @author xll
 * @date 2019-05-27
 */
public class BaseResponse<T> {
    public T data;
    public String code;
    public boolean success;
    public String message;
    public String httpmsg;
    public int pageIndex;
    public int pages;
    public int total;
    public int httpcode;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHttpmsg() {
        return httpmsg;
    }

    public void setHttpmsg(String httpmsg) {
        this.httpmsg = httpmsg;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHttpcode() {
        return httpcode;
    }

    public void setHttpcode(int httpcode) {
        this.httpcode = httpcode;
    }
}
