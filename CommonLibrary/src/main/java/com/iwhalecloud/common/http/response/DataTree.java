package com.iwhalecloud.common.http.response;

import java.util.List;

import lombok.Data;

/**
 * 封装bean
 *
 * @author xuliangliang
 * @date 2019-10-25
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
@Data
public class DataTree<K, V> {
    /**
     * 一级数据K
     */
    private K groupItem;
    /**
     * 二级数据V列表
     */
    private List<V> subItems;

    public DataTree(K groupItem, List<V> subItems) {
        this.groupItem = groupItem;
        this.subItems = subItems;
    }
}