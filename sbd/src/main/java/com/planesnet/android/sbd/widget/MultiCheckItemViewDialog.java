package com.planesnet.android.sbd.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.planesnet.android.sbd.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultiCheckItemViewDialog extends DialogFragment  {

    private static final String ITEMS = "items";

    private MultiCheckItemViewAdapter listAdapter;

    private ListView _listViewItems;
    private CheckBox selectAll;

    private List<CheckValue> _Items;

    private String _strTitle;

    private String _strPositiveButtonText = "VOLVER";

    private CheckedItemsListener _checkedItemsListener;


    public interface CheckedItemsListener<T> extends Serializable {
        void onCheckedItemsChanged(List<T> list);
    }




    public MultiCheckItemViewDialog() {

    }

    public static MultiCheckItemViewDialog newInstance(List<CheckValue> items) {
        MultiCheckItemViewDialog multiSelectExpandableFragment = new
                MultiCheckItemViewDialog();

        Bundle args = new Bundle();
        args.putSerializable(ITEMS, (Serializable) items);


        multiSelectExpandableFragment.setArguments(args);

        return multiSelectExpandableFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_HIDDEN);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Getting the layout inflater to inflate the view in an alert dialog.
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View rootView = inflater.inflate(R.layout.multi_checked_list_dialog, null);
        setData(rootView);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setView(rootView);

        String strPositiveButton = _strPositiveButtonText == null ? "CLOSE" : _strPositiveButtonText;
        alertDialog.setPositiveButton(strPositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        String strTitle = _strTitle == null ? "Select Item" : _strTitle;
        alertDialog.setTitle(strTitle);

        final AlertDialog dialog = alertDialog.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(ITEMS, (Serializable) _Items);
        super.onSaveInstanceState(outState);
    }

    public void setTitle(String strTitle) {
        _strTitle = strTitle;
    }

    private void setData(View rootView) {

        _Items = (List) getArguments().getSerializable(ITEMS);

        _listViewItems = (ListView) rootView.findViewById(R.id.listItems);

        //create the adapter by passing your ArrayList data
        listAdapter = new MultiCheckItemViewAdapter(getActivity(), _Items);
        //attach the adapter to the list
        _listViewItems.setAdapter(listAdapter);

        _listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        selectAll = rootView.findViewById(R.id.select_all);
        selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (listAdapter != null) {
                    listAdapter.selectAll(isChecked);
                }
            }
        });



    }


    @Override
    public void onPause()
    {
        super.onPause();
        dismiss();
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(_checkedItemsListener!=null) {
            _checkedItemsListener.onCheckedItemsChanged(getCheckedValues());
        }

    }

    public void setSeletedItemsListener(CheckedItemsListener _checkedItemsListener) {
        this._checkedItemsListener = _checkedItemsListener;
    }

    public List<CheckValue> getCheckedValues() {
        List<CheckValue> checkValues = new ArrayList<>();
        for(CheckValue v : _Items ) {
            if(v.isChecked()) {
                checkValues.add(v);
            }
        }
        return checkValues;
    }

}
