package com.zly.diycode.common.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zly.diycode.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangly on 2017/3/18.
 */

public class BaseAdapter extends RecyclerView.Adapter<DataBindingViewHolder> {

    public interface Presenter {

    }

    public interface Converter<T extends Item> {

        void convert(BaseAdapter adapter, DataBindingViewHolder viewHolder, T item, int position);
    }

    protected List<Item> mDataList;
    protected final Context mContext;
    protected final LayoutInflater mInflater;
    private Presenter mPresenter;
    private SparseArrayCompat<ViewDataBinding> mViewDataBindingList;
    private SparseArrayCompat<Converter> mConverters = new SparseArrayCompat<>();

    public BaseAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mViewDataBindingList == null) {
            mViewDataBindingList = new SparseArrayCompat<>();
        }
        ViewDataBinding dataBinding = DataBindingUtil.inflate(mInflater, viewType, parent, false);
        mViewDataBindingList.put(viewType, dataBinding);
        return new DataBindingViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(final DataBindingViewHolder holder, int position) {
        if (mDataList == null || mDataList.isEmpty()) {
            return;
        }
        final Item data = mDataList.get(position);
        final ViewDataBinding dataBinding = holder.getDataBinding();
        dataBinding.setVariable(BR.item, data);
        dataBinding.setVariable(BR.presenter, mPresenter);
        dataBinding.setVariable(BR.position, position);
        dataBinding.executePendingBindings();
        Converter converter = mConverters.get(data.getItemViewType());
        if (converter != null) {
            converter.convert(this, holder, data, position);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.get(position).getItemViewType();
    }

    public <T extends Item> void setDataList(List<T> dataList) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void add(int position, Item data) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.add(position, data);
        notifyItemChanged(position);
    }

    public void add(Item data) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.add(data);
        notifyItemChanged(mDataList.size() - 1);
    }

    public <T extends Item> void addAll(List<T> datas) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void update(Item item, int position) {
        if (mDataList == null || mDataList.isEmpty()) {
            return;
        }

        mDataList.set(position, item);
        notifyItemChanged(position);
    }

    public void remove(int position) {
        if (mDataList == null || mDataList.isEmpty()) {
            return;
        }
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    public Context getContext() {
        return mContext;
    }

    public
    @Nullable
    List<Item> getDataList() {
        return mDataList;
    }

    public void setPresenter(Presenter presenter) {
        this.mPresenter = presenter;
    }

    @SuppressWarnings("unchecked")
    public <T extends ViewDataBinding> T getDataBindingByItemType(int itemType) {
        return (T) mViewDataBindingList.get(itemType);
    }

    /**
     * According to the item type to obtain the corresponding item instance.
     *
     * @param itemType
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    @Nullable
    <T extends Item> T getItemByType(int itemType, int position) {
        List<Item> itemList = mDataList;
        Item item = itemList.get(position);
        if (item.getItemViewType() != itemType) {
            return null;
        }

        return (T) item;
    }

    public <T extends Item> void addConverter(int layoutType, Converter<T> binder) {
        mConverters.put(layoutType, binder);
    }

    public void removeAllConverters() {
        if (mConverters != null) {
            mConverters.clear();
            mConverters = null;
        }
    }
}
