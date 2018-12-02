package com.planesnet.android.sbd.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.planesnet.android.sbd.R;
import com.planesnet.android.sbd.util.U;

import java.util.ArrayList;
import java.util.List;

public class EditItemSpinner<T> extends android.support.v7.widget.AppCompatEditText implements View.OnTouchListener,
        SearchableListDialog.SearchableItem<T> {
    public static final int NO_ITEM_SELECTED = -1;
    public static final int EDIT_MODE_ALL = 0;
    public static final int EDIT_MODE_INPUT = 1;
    public static final int EDIT_MODE_SPINNER = 2;

    protected static final int CUSTOM_DRAWABLE = R.drawable.ic_search_edit_text;


    protected Context _context;
    private List<T> _items;
    protected SearchableListDialog _searchableListDialog;
    private int _selectedItemPosition;
    private T _selectedItem;
    private int editMode = EDIT_MODE_ALL;

    private OnItemSelectedListener<T> _onItemSelectedListener;

    public EditItemSpinner(Context context) {
        super(context);
        this._context = context;
        init();
    }

    public EditItemSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this._context = context;
        init();
    }

    public EditItemSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this._context = context;
        init();
    }


    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        updateDrawables();
    }

    private void updateDrawables() {
        // Si está activo, añadir la lupa para indicar que se puede buscar.
        if (isEnabled() && (editMode == EDIT_MODE_ALL || editMode == EDIT_MODE_SPINNER)) {
            Drawable img = getContext().getResources().getDrawable(CUSTOM_DRAWABLE);
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        } else {
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }


    private void init() {
        _selectedItemPosition=NO_ITEM_SELECTED;
        _selectedItem = null;
        _items = new ArrayList<T>();
        _searchableListDialog = SearchableListDialog.newInstance(_items);
        _searchableListDialog.setPositiveButton("VOLVER");
        _searchableListDialog.setTitle("Selecciona un elemento");
        _searchableListDialog.setOnSearchableItemClickListener(this);

        // Force single line.
        this.setInputType(InputType.TYPE_CLASS_TEXT);
        this.setMaxLines(1);

        updateDrawables();

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                Drawable right = getCompoundDrawables()[DRAWABLE_RIGHT];

                if (editMode == EDIT_MODE_ALL) {
                    if (right != null && event.getAction() == MotionEvent.ACTION_UP) {
                        if (event.getX() >= (getRight() - getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            _searchableListDialog.show(scanForActivity(_context).getFragmentManager(), "TAG");
                        }
                    }
                } else if (editMode == EDIT_MODE_SPINNER) {
                    if (right != null && event.getAction() == MotionEvent.ACTION_UP) {
                        _searchableListDialog.show(scanForActivity(_context).getFragmentManager(), "TAG");
                    }
                }

                return false;
            }
        });


        addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _selectedItem=null;
                _selectedItemPosition =  NO_ITEM_SELECTED;
            }
        });

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (_searchableListDialog.isAdded()) {
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {

            if (_items.size()>0) {
                _searchableListDialog.show(scanForActivity(_context).getFragmentManager(), "TAG");
            }
        }
        return true;
    }


    public void setAdapter(ArrayAdapter<T> adapter) {
        _items.clear();
        setSelectedItem(null);
        setText("");
        if (adapter != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                _items.add(adapter.getItem(i));
            }
        }
    }

    @Override
    public void onSearchableItemClicked(T item, int position) {

        if(item!=null && position >= 0) {
            this.setText(item.toString());
            _selectedItem = item;
            _selectedItemPosition = position;

            // Notificar el item seleccionado.
            if(_onItemSelectedListener!=null) {
                _onItemSelectedListener.onItemSelected(item);
            }


        } else {
            _selectedItem = null;
            _selectedItemPosition = NO_ITEM_SELECTED;
        }

    }

    public void setTitle(String strTitle) {
        _searchableListDialog.setTitle(strTitle);
    }

    public void setPositiveButton(String strPositiveButtonText) {
        _searchableListDialog.setPositiveButton(strPositiveButtonText);
    }

    public void setPositiveButton(String strPositiveButtonText, DialogInterface.OnClickListener onClickListener) {
        _searchableListDialog.setPositiveButton(strPositiveButtonText, onClickListener);
    }

    public void setOnSearchTextChangedListener(SearchableListDialog.OnSearchTextChanged onSearchTextChanged) {
        _searchableListDialog.setOnSearchTextChangedListener(onSearchTextChanged);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        _onItemSelectedListener = listener;
    }

    protected Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }

    public T getSelectedItem() {

        return _selectedItem;

    }

    public void setSelectedItem(T item) {
        if(item!=null) {
            setText(item.toString());
        } else {
            setText("");
        }

        _selectedItem = item;
        if(_onItemSelectedListener!=null) {
            _onItemSelectedListener.onItemSelected(item);
        }
    }


    public void setError(CharSequence error) {
        if (error != null) {
            Toast.makeText(_context,error,Toast.LENGTH_LONG).show();
        }
    }


    public boolean isSelectedItem() {
        return _selectedItem != null;
    }


    public int isEditMode() {
        return editMode;
    }


    public void setEditMode(int editMode) {
        this.editMode = editMode;
        if (editMode == EDIT_MODE_ALL || editMode == EDIT_MODE_INPUT) {
            U.readWrite(this, InputType.TYPE_CLASS_TEXT);
        } else {
            U.readOnly(this);
        }
        updateDrawables();

    }
}
