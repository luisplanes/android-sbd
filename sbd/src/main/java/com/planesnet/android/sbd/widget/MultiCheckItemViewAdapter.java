package com.planesnet.android.sbd.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.planesnet.android.sbd.R;

import java.util.List;


/**
 * Created by Pavneet_Singh on 12/20/17.
 */

public class MultiCheckItemViewAdapter extends ArrayAdapter<CheckValue> {
    private List<CheckValue> dataSet;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        CheckBox checkBox;
        TextView text;
    }


    public MultiCheckItemViewAdapter(@NonNull Context context, @NonNull List<CheckValue> items) {
        super(context, R.layout.multi_checked_list_dialog_detail, items);
        this.dataSet = items;
        this.mContext=context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final CheckValue checkValue = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.multi_checked_list_dialog_detail, parent, false);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text= convertView.findViewById(R.id.text1);
        viewHolder.checkBox= convertView.findViewById(R.id.checkBox);

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkValue.setChecked(isChecked);
            }
        });

        viewHolder.checkBox.setChecked(checkValue.isChecked());
        viewHolder.text.setText(checkValue.toString());

        return convertView;
    }

    public void selectAll(boolean isChecked) {
        for (CheckValue value : dataSet) {
            value.setChecked(isChecked);
        }
        this.notifyDataSetChanged();
    }


}