package com.highlands.common.http.response;

import lombok.Data;

/**
 * 基类
 *
 * @author xll
 * @date 2019-05-27
 */
@Data
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
}
