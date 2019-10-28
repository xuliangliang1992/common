package com.iwhalecloud.common.base.adapter;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iwhalecloud.common.http.response.DataTree;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * 二级列表Adapter
 *
 * @author xuliangliang
 * @date 2019-10-28
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public abstract class BaseTwoLevelAdapter<G, C, M extends DataTree<G, C>, B extends ViewDataBinding> extends BaseAdapter<M, BaseBindingViewHolder<B>> {
    private SparseBooleanArray groupItemStatus;
    protected OnGroupClickListener<G> mGroupClickListener;
    protected OnChildClickListener<C> mChildClickListener;

    public BaseTwoLevelAdapter() {
        super();
        groupItemStatus = new SparseBooleanArray();
    }

    @Override
    public void refresh(List<M> list) {
        super.refresh(list);
        initGroupItemStatus(groupItemStatus);
    }

    @Override
    public void loadMore(List<M> list) {
        super.loadMore(list);
        initGroupItemStatus(groupItemStatus);
    }

    /**
     * 删除某个组中某个子数据
     *
     * @param groupPosition 组position
     * @param childPosition 子position
     */
    public void removeChild(int groupPosition, int childPosition) {
        mItems.get(groupPosition).getSubItems().remove(childPosition);
        initGroupItemStatus(groupItemStatus);
        notifyDataSetChanged();
    }

    private void initGroupItemStatus(SparseBooleanArray sparseBooleanArray) {
        //设置初始值，所有 groupItem 默认为开启状态
        for (int i = 0; i < mItems.size(); i++) {
            sparseBooleanArray.put(i, true);
        }
    }

    public void setGroupClickListener(OnGroupClickListener<G> groupClickListener) {
        mGroupClickListener = groupClickListener;
    }

    public void setChildClickListener(OnChildClickListener<C> childClickListener) {
        mChildClickListener = childClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemStatusByPosition(position).getViewType();
    }

    @NonNull
    @Override
    public BaseBindingViewHolder<B> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding;
        if (viewType == ItemStatus.VIEW_TYPE_GROUP_ITEM) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getGroupLayout(), parent, false);
        } else {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getChildLayout(), parent, false);
        }
        BaseBindingViewHolder<B> viewHolder = new BaseBindingViewHolder<>(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingViewHolder<B> holder, int position) {
        final ItemStatus itemStatus = getItemStatusByPosition(position);
        final DataTree<G, C> dt = mItems.get(itemStatus.getGroupPosition());
        if (itemStatus.getViewType() == ItemStatus.VIEW_TYPE_GROUP_ITEM) {
            onBindGroupItem(holder, itemStatus.getGroupPosition(), dt.getGroupItem());
            addDisposable(RxView.clicks(holder.itemView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(unit -> mGroupClickListener.onGroupItemClick(dt.getGroupItem(), position)));
        } else if (itemStatus.getViewType() == ItemStatus.VIEW_TYPE_SUB_ITEM) {
            onBindChildItem(holder, itemStatus.getGroupPosition(), itemStatus.getChildPosition(), dt.getSubItems().get(itemStatus.getChildPosition()));
            addDisposable(RxView.clicks(holder.itemView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(unit -> mChildClickListener.onChildItemClick(
                            dt.getSubItems().get(itemStatus.getChildPosition()),
                            itemStatus.getGroupPosition(),
                            itemStatus.getChildPosition())));
        }
    }


    @Override
    public int getItemCount() {
        return getCount();
    }

    /**
     * 计算
     *
     * @return 列表总数
     */
    private int getCount() {
        int count = 0;
        if (mItems == null) {
            return count;
        }
        for (int i = 0; i < mItems.size(); i++) {
            List<C> goodBeans = mItems.get(i).getSubItems();
            if (groupItemStatus.get(i) && goodBeans != null) {
                count = count + 1 + goodBeans.size();
            } else {
                count = count + 1;
            }
        }
        return count;
    }

    /**
     * @param position position
     * @return 获得item属性
     */
    private ItemStatus getItemStatusByPosition(int position) {
        ItemStatus itemStatus = new ItemStatus();
        //计算groupItemIndex = i 时，position最大值
        int count = 0;
        int i;
        //轮询 groupItem 的开关状态
        for (i = 0; i < groupItemStatus.size(); i++) {
            if (count == position) {
                //pos刚好等于计数时，item为groupItem
                itemStatus.setViewType(ItemStatus.VIEW_TYPE_GROUP_ITEM);
                itemStatus.setGroupPosition(i);
                break;
            } else if (count > position) {
                //pos大于计数时，item为groupItem(i - 1)中的某个subItem
                itemStatus.setViewType(ItemStatus.VIEW_TYPE_SUB_ITEM);
                itemStatus.setGroupPosition(i - 1);
                itemStatus.setChildPosition(position - (count - mItems.get(i - 1).getSubItems().size()));
                break;
            }
            //无论groupItem状态是开或者关，它在列表中都会存在，所有count++
            count++;
            if (groupItemStatus.get(i)) {
                //当轮询到的groupItem的状态为“开”的话，count需要加上该groupItem下面的子项目数目
                count += mItems.get(i).getSubItems().size();
            }
        }
        //简单地处理当轮询到最后一项groupItem的时候
        if (i >= groupItemStatus.size()) {
            itemStatus.setGroupPosition(i - 1);
            itemStatus.setViewType(ItemStatus.VIEW_TYPE_SUB_ITEM);
            itemStatus.setChildPosition(position - (count - mItems.get(i - 1).getSubItems().size()));
        }
        return itemStatus;
    }

    /**
     * 一级布局
     *
     * @return 布局id
     */
    protected abstract @LayoutRes
    int getGroupLayout();

    /**
     * 二级布局
     *
     * @return 布局id
     */
    protected abstract @LayoutRes
    int getChildLayout();

    /**
     * 二级数据
     *
     * @param holder
     * @param groupPosition 组Position
     * @param childPosition 子Position
     * @param c             二级对象
     */
    protected abstract void onBindChildItem(BaseBindingViewHolder<B> holder, int groupPosition, int childPosition, C c);

    /**
     * 一级数据
     *
     * @param holder
     * @param groupPosition 组Position
     * @param g             一级对象
     */
    protected abstract void onBindGroupItem(BaseBindingViewHolder<B> holder, int groupPosition, G g);

    public interface OnGroupClickListener<G> {
        /**
         * 一级item点击事件
         *
         * @param g             bean
         * @param groupPosition 下标
         */
        void onGroupItemClick(G g, int groupPosition);

    }

    public interface OnChildClickListener<C> {
        /**
         * 二级item点击事件
         *
         * @param c             bean
         * @param groupPosition 组下标
         * @param childPosition 子下标
         */
        void onChildItemClick(C c, int groupPosition, int childPosition);

    }
}
