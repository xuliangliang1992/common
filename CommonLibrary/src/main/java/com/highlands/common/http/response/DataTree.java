package com.highlands.common.http.response;

import java.util.List;

/**
 * 封装bean
 *
 * @author xuliangliang
 * @date 2019-10-25
 * copyright(c) Highlands
 */
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

    public K getGroupItem() {
        return groupItem;
    }

    public void setGroupItem(K groupItem) {
        this.groupItem = groupItem;
    }

    public List<V> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<V> subItems) {
        this.subItems = subItems;
    }
}
