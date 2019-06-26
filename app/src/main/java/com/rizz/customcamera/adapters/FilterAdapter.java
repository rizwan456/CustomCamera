package com.rizz.customcamera.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rizz.customcamera.interfaces.IFilterClicked;
import com.rizz.customcamera.R;
import com.rizz.customcamera.databinding.FilterItemsBinding;
import com.rizz.customcamera.model.Filter;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    FilterItemsBinding filterItemsBinding;
    List<Filter> filterList;
    Context context;
    IFilterClicked iFilterClicked;

   /* public FilterAdapter(List<Filter> filterList, Context context) {
        this.filterList = filterList;
        this.context = context;
    }*/

    public FilterAdapter(List<Filter> filterList, Context context, IFilterClicked iFilterClicked) {
        this.filterList = filterList;
        this.context = context;
        this.iFilterClicked = iFilterClicked;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        filterItemsBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.filter_items, viewGroup, false);
        return new FilterViewHolder(filterItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof FilterViewHolder) {
            FilterViewHolder filterViewHolder = (FilterViewHolder) viewHolder;
            filterViewHolder.bindData(i);
        }
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    class FilterViewHolder extends RecyclerView.ViewHolder {
        FilterItemsBinding filterItemsBinding;
        int index = -1;

        public FilterViewHolder(FilterItemsBinding filterItemsBinding) {
            super(filterItemsBinding.getRoot());
            this.filterItemsBinding = filterItemsBinding;
        }

        public void bindData(int pos) {
            filterItemsBinding.filterName.setText(filterList.get(pos).getFilterName());

            filterItemsBinding.filterImage.setOnClickListener(v -> {
                iFilterClicked.onClickFilter(filterList.get(pos).getFilterId());
            });
        }
    }
}
