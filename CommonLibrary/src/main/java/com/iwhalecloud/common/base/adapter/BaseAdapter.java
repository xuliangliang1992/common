package com.iwhalecloud.common.base.adapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public abstract class BaseAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements IListChangeCallback<ObservableArrayList<M>> {
    protected ObservableArrayList<M> mItems;
    protected OnItemClickListener<M> mItemClickListener;
    protected OnItemLongClickListener<M> mOnItemLongClickListener;
    private ListChangedCallbackProxy mItemsChangeCallback;
    private CompositeDisposable mCompositeDisposable;

    public BaseAdapter() {
        mItems = new ObservableArrayList<>();
        this.mItemsChangeCallback = new ListChangedCallbackProxy(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    public void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    public void destroy() {
        mCompositeDisposable.clear();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void loadMore(List<M> list) {
        if (list != null && list.size() > 0) {
            mItems.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void refresh(List<M> list) {
        mItems.clear();
        if (list != null && list.size() > 0) {
            mItems.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mItems.remove(position);
        notifyDataSetChanged();
    }

    public void remove(M m) {
        mItems.remove(m);
        notifyDataSetChanged();
    }

    public void add(M m) {
        mItems.add(m);
        notifyDataSetChanged();
    }

    public void addLast(M m) {
        add(m);
    }

    public void addFirst(M m) {
        mItems.add(0, m);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public List<M> getDataList() {
        return mItems;
    }

    public void setItemClickListener(OnItemClickListener<M> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<M> onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public void onChanged(ObservableArrayList<M> sender) {
        notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(ObservableArrayList<M> sender, int positionStart, int itemCount) {
        notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeInserted(ObservableArrayList<M> sender, int positionStart, int itemCount) {
        notifyItemRangeInserted(positionStart, itemCount);
        notifyItemRangeChanged(positionStart + itemCount, mItems.size() - positionStart - itemCount);
    }

    @Override
    public void onItemRangeMoved(ObservableArrayList<M> sender, int fromPosition, int toPosition, int itemCount) {
        notifyDataSetChanged();
    }

    @Override
    public void onItemRangeRemoved(ObservableArrayList<M> sender, int positionStart, int itemCount) {
        notifyItemRangeRemoved(positionStart, itemCount);
        notifyItemRangeChanged(positionStart, mItems.size() - positionStart);
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mItems.addOnListChangedCallback(mItemsChangeCallback);
    }

    @Override
    public void onDetachedFromRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mItems.removeOnListChangedCallback(mItemsChangeCallback);
    }

    public interface OnItemClickListener<M> {
        /**
         * 点击事件
         *
         * @param m        bean
         * @param position 下标
         */
        void onItemClick(M m, int position);
    }

    public interface OnItemLongClickListener<M> {
        /**
         * 长按
         *
         * @param m        bean
         * @param position 下标
         */
        void onItemLongClick(M m, int position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public List<M> getListData() {
        return mItems;
    }

    class ListChangedCallbackProxy extends ObservableArrayList.OnListChangedCallback<ObservableArrayList<M>> {
        private IListChangeCallback<ObservableArrayList<M>> mBase;

        ListChangedCallbackProxy(IListChangeCallback<ObservableArrayList<M>> base) {
            this.mBase = base;
        }

        @Override
        public void onChanged(ObservableArrayList<M> sender) {
            mBase.onChanged(sender);
        }

        @Override
        public void onItemRangeChanged(ObservableArrayList<M> sender, int positionStart, int itemCount) {
            mBase.onItemRangeChanged(sender, positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(ObservableArrayList<M> sender, int positionStart, int itemCount) {
            mBase.onItemRangeInserted(sender, positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(ObservableArrayList<M> sender, int fromPosition, int toPosition, int itemCount) {
            mBase.onItemRangeMoved(sender, fromPosition, toPosition, itemCount);
        }

        @Override
        public void onItemRangeRemoved(ObservableArrayList<M> sender, int positionStart, int itemCount) {
            mBase.onItemRangeRemoved(sender, positionStart, itemCount);
        }
    }

}
