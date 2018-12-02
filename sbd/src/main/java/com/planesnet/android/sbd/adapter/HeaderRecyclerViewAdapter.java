package com.planesnet.android.sbd.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

public abstract class HeaderRecyclerViewAdapter<T, H, I> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private List<T> itemObjects;

    private final int LAYOUT_HEADER, LAYOUT_ITEM;


    public abstract void onBindViewHolderHeader(H holder);
    public abstract void onBindViewHolderItem(I holder, T item);

    public abstract RecyclerView.ViewHolder onCreateHeaderHolder(View layoutView);
    public abstract RecyclerView.ViewHolder onCreateItemHolder(View layoutView);


    public HeaderRecyclerViewAdapter(int layoutHeader, int layoutItem, List<T> itemObjects) {
        this.LAYOUT_HEADER = layoutHeader;
        this.LAYOUT_ITEM = layoutItem;
        this.itemObjects = itemObjects;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(LAYOUT_HEADER, parent, false);
            return onCreateHeaderHolder(layoutView);

        } else if (viewType == TYPE_ITEM) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(LAYOUT_ITEM, parent, false);
            return onCreateItemHolder(layoutView);
        }
        throw new RuntimeException("No match for " + viewType + ".");
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(isPositionHeader(position)) {
            onBindViewHolderHeader((H) holder);
        } else {
            T mObject = itemObjects.get(position-1);
            onBindViewHolderItem((I) holder, mObject);
        }

    }


    @Override
    public int getItemCount() {
        if(itemObjects!=null) {
            return itemObjects.size() + 1;
        }
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }
    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    public abstract static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class ItemViewHolder extends HeaderRecyclerViewAdapter.ViewHolder {
        public int getItemPosition() {
            int position = super.getAdapterPosition();
            if (position != NO_POSITION) {
                return super.getAdapterPosition()-1;
            }
            return  position;
        }

        public ItemViewHolder(View itemView) {
            super(itemView);


        }
    }

    public class HeaderViewHolder extends HeaderRecyclerViewAdapter.ViewHolder {
        public HeaderViewHolder(View headerView) {
            super(headerView);

        }
    }

}