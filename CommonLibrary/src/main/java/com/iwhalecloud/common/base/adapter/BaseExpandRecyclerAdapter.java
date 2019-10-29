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
import timber.log.Timber;

/**
 * ExpandRecyclerViewAdapter
 * 仿ExpandListView
 *
 * @author xuliangliang
 * @date 2019-10-28
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public abstract class BaseExpandRecyclerAdapter<G, C, M extends DataTree<G, C>, B extends ViewDataBinding> extends BaseAdapter<M, BaseBindingViewHolder<B>> {
    private SparseBooleanArray groupItemStatus;
    protected OnGroupClickListener<G> mOnGroupClickListener;
    protected OnChildClickListener<C> mOnChildClickListener;
    protected OnGroupExpandListener mOnGroupExpandListener;
    protected OnGroupCollapseListener mOnGroupCollapseListener;
    private boolean groupClickable;
    private boolean childClickable;

    public BaseExpandRecyclerAdapter() {
        super();
        groupItemStatus = new SparseBooleanArray();
        //默认可点击
        groupClickable = true;
        childClickable = true;
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
        if (mItems.get(groupPosition).getSubItems().size() == 0) {
            mItems.remove(groupPosition);
        }
        initGroupItemStatus(groupItemStatus);
        notifyDataSetChanged();
    }

    private void initGroupItemStatus(SparseBooleanArray sparseBooleanArray) {
        //设置初始值，所有 groupItem 默认为关闭状态
        for (int i = 0; i < mItems.size(); i++) {
            sparseBooleanArray.put(i, true);
        }
    }

    public void setGroupClickable(boolean groupClickable) {
        this.groupClickable = groupClickable;
    }

    public void setChildClickable(boolean childClickable) {
        this.childClickable = childClickable;
    }

    public void setOnGroupClickListener(OnGroupClickListener<G> groupClickListener) {
        mOnGroupClickListener = groupClickListener;
    }

    public void setOnChildClickListener(OnChildClickListener<C> childClickListener) {
        mOnChildClickListener = childClickListener;
    }

    public void setOnGroupExpandListener(OnGroupExpandListener groupExpandListener) {
        mOnGroupExpandListener = groupExpandListener;
    }

    public void setOnGroupCollapseListener(OnGroupCollapseListener groupCollapseListener) {
        mOnGroupCollapseListener = groupCollapseListener;
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
            onBindGroupItem(holder, itemStatus.getGroupPosition(), dt.getGroupItem(), dt.getSubItems());
            if (groupClickable) {
                addDisposable(RxView.clicks(holder.itemView)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe(unit -> {
                            if (mOnGroupClickListener == null || mOnGroupClickListener.onGroupItemClick(dt.getGroupItem(), itemStatus.getGroupPosition())) {
                                if (itemStatus.isExpanded()) {
                                    collapseGroup(itemStatus.getGroupPosition());
                                } else {
                                    expandGroup(itemStatus.getGroupPosition());
                                }
                                itemStatus.setExpanded(!itemStatus.isExpanded());
                            }
                        }));
            }
        } else if (itemStatus.getViewType() == ItemStatus.VIEW_TYPE_SUB_ITEM) {
            onBindChildItem(holder, itemStatus.getGroupPosition(), itemStatus.getChildPosition(), dt.getSubItems().get(itemStatus.getChildPosition()));
            if (childClickable) {
                addDisposable(RxView.clicks(holder.itemView)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe(unit -> {
                            if (mOnChildClickListener != null) {
                                Timber.d(position + "-  -  -" + itemStatus.getChildPosition() + "  " + holder.getAdapterPosition() + 1);
                                mOnChildClickListener.onChildItemClick(
                                        dt.getSubItems().get(itemStatus.getChildPosition()),
                                        itemStatus.getGroupPosition(),
                                        itemStatus.getChildPosition());
                            }
                        }));
            }
        }
    }

    @Override
    public int getItemCount() {
        return getCount();
    }

    /**
     * @param adapterPosition 列表中位置
     * @return groupPosition
     */
    public int getGroupPosition(int adapterPosition) {
        return getItemStatusByPosition(adapterPosition).getGroupPosition();
    }

    /**
     * @param adapterPosition 列表中位置
     * @return childPosition
     */
    public int getChildPosition(int adapterPosition) {
        return getItemStatusByPosition(adapterPosition).getChildPosition();
    }

    /**
     * @param groupPosition 给定组
     * @return 给定组当前是否已展开
     */
    public boolean isGroupExpanded(int groupPosition) {
        return groupItemStatus.get(groupPosition);
    }

    /**
     * 在分组列表视图中展开组
     *
     * @param groupPosition 给定组
     */
    public void expandGroup(int groupPosition) {
        if (groupPosition < 0 || groupPosition > groupItemStatus.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (mOnGroupExpandListener != null) {
            mOnGroupExpandListener.onGroupExpand(groupPosition);
        }
        groupItemStatus.put(groupPosition, true);
        notifyItemRangeInserted(getAdapterPosition(groupPosition) + 1, mItems.get(groupPosition).getSubItems().size());
    }

    /**
     * 在分组列表视图中折叠组
     *
     * @param groupPosition 给定组
     */
    public void collapseGroup(int groupPosition) {
        if (groupPosition < 0 || groupPosition > groupItemStatus.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (mOnGroupCollapseListener != null) {
            mOnGroupCollapseListener.onGroupCollapse(groupPosition);
        }
        groupItemStatus.put(groupPosition, false);
        notifyItemRangeRemoved(getAdapterPosition(groupPosition) + 1, mItems.get(groupPosition).getSubItems().size());
    }

    /**
     * @param groupPosition groupPosition
     * @return 获取该组在列表中的位置
     */
    private int getAdapterPosition(int groupPosition) {
        int adapterPosition = 0;
        for (int i = 0; i < groupPosition; i++) {
            adapterPosition++;
            List<C> goodBeans = mItems.get(i).getSubItems();
            if (groupItemStatus.get(i) && goodBeans != null) {
                adapterPosition = adapterPosition + goodBeans.size();
            }
        }
        return adapterPosition;
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
            itemStatus.setExpanded(groupItemStatus.get(i));
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
     * 一级数据
     *
     * @param holder        BaseBindingViewHolder
     * @param groupPosition groupPosition
     * @param g             一级对象
     * @param cList         二级对象列表
     */
    protected abstract void onBindGroupItem(BaseBindingViewHolder<B> holder, int groupPosition, G g, List<C> cList);

    /**
     * 二级数据
     *
     * @param holder        BaseBindingViewHolder
     * @param groupPosition groupPosition
     * @param childPosition childPosition
     * @param c             二级对象
     */
    protected abstract void onBindChildItem(BaseBindingViewHolder<B> holder, int groupPosition, int childPosition, C c);

    public interface OnGroupClickListener<G> {
        /**
         * Group点击事件
         *
         * @param g             GroupBean
         * @param groupPosition groupPosition
         * @return true可以展开折叠组, false不可
         */
        boolean onGroupItemClick(G g, int groupPosition);

    }

    public interface OnChildClickListener<C> {
        /**
         * Child点击事件
         *
         * @param c             ChildBean
         * @param groupPosition groupPosition
         * @param childPosition childPosition
         */
        void onChildItemClick(C c, int groupPosition, int childPosition);

    }

    /**
     * 组展开时监听
     */
    public interface OnGroupExpandListener {
        /**
         * 组已经展开
         *
         * @param groupPosition 给定组
         */
        void onGroupExpand(int groupPosition);
    }

    /**
     * 组折叠时监听
     */
    public interface OnGroupCollapseListener {
        /**
         * 当组已经折叠
         *
         * @param groupPosition 给定组
         */
        void onGroupCollapse(int groupPosition);
    }
}
