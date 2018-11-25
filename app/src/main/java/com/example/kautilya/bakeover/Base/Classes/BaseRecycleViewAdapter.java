package com.example.kautilya.bakeover.Base.Classes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kautilya on 06/02/18.
 */
public abstract class BaseRecycleViewAdapter<T, K extends BaseRecycleViewAdapter.BaseViewHolder> extends RecyclerView.Adapter<K> implements Filterable {

    protected List<T> data;
    protected List<T> storage;
    protected Context context;
    private boolean filterable;
    private ItemClickListener<T> onClickListener;
    private final OnLoadMoreListener onLoadMoreListener;
    private RecyclerView recyclerView;
    private boolean isLoading;


    public BaseRecycleViewAdapter(List<T> data, Context context) {
        this(data, context, false, null, null);
    }

    public BaseRecycleViewAdapter(List<T> data, Context context, ItemClickListener<T> onClickListener) {
        this(data, context, false, onClickListener, null, null);
    }

    public BaseRecycleViewAdapter(List<T> data, Context context, boolean filterable) {
        this(data, context, filterable, null, null);
    }

    public BaseRecycleViewAdapter(List<T> data, Context context, boolean filterable, ItemClickListener<T> onClickListener) {
        this(data, context, filterable, onClickListener, null, null);
    }


    public BaseRecycleViewAdapter(List<T> data, Context context, boolean filterable, OnLoadMoreListener onLoadMoreListener, RecyclerView recyclerView) {
        this(data, context, filterable, null, onLoadMoreListener, recyclerView);

    }


    public BaseRecycleViewAdapter(List<T> data, Context context, boolean filterable, ItemClickListener<T> onClickListener, OnLoadMoreListener onLoadMoreListener, RecyclerView recyclerView) {
        this.data = data;
        this.context = context;
        this.filterable = filterable;
        this.recyclerView = recyclerView;
        this.onLoadMoreListener = onLoadMoreListener;
        this.onClickListener = onClickListener;
        init();
    }

    @Override
    public void onBindViewHolder(K holder, int position) {
        if (holder != null)
            holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilterable(boolean filterable) {
        this.filterable = filterable;
    }

    public void addMoreData(List<T> job_info) {
        int start = getItemCount();
        data.addAll(job_info);
        notifyItemRangeInserted(start, getItemCount());
    }

    public void clear() {
        data.clear();
        if (storage != null)
            storage.clear();
        notifyDataSetChanged();
    }

    public abstract class BaseViewHolder<L extends ViewDataBinding> extends RecyclerView.ViewHolder {
        protected String LOG_TAG = this.getClass().getName();

        L mViewDataBinding;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mViewDataBinding = DataBindingUtil.bind(itemView);
        }

        protected void bind(final T object) {
            getViewDataBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null)
                        onClickListener.onItemClick(object, getAdapterPosition());
                }
            });
        }

        public L getViewDataBinding() {
            return mViewDataBinding;
        }
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public List<T> getData() {
        return data;
    }

    public interface ItemClickListener<T> {
        void onItemClick(T object, int position);
    }

    private void init() {
        if (onLoadMoreListener != null) {
            final GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView,
                                                 int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoading) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                            if (onLoadMoreListener != null)
                                onLoadMoreListener.onLoadMore();
                        }
                    }
                }
            });
        }
        if (filterable) {
            this.storage = new ArrayList<>();
            this.storage.addAll(data);
        }

    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}