package com.planesnet.android.sbd.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.planesnet.android.sbd.R;

import java.util.ArrayList;
import java.util.List;

public class MultiCheckItemView extends android.support.v7.widget.AppCompatEditText implements View.OnTouchListener,
        MultiCheckItemViewDialog.CheckedItemsListener<CheckValue> {

    protected static final int CUSTOM_DRAWABLE = R.drawable.ic_checked_list_gray_24dp;

    protected Context _context;
    private List<CheckValue> _items;
    private OnMultiChecktemListener listener;

    protected MultiCheckItemViewDialog _dialog;


    public MultiCheckItemView(Context context) {
        super(context);
        _context = context;
        init();
    }

    public MultiCheckItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _context = context;
        init();
    }

    public MultiCheckItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _context = context;
        init();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        updateDrawables();
    }

    private void updateDrawables() {
        // Si está activo, añadir la lupa para indicar que se puede buscar.
        if(isEnabled()) {
            Drawable img = getContext().getResources().getDrawable(CUSTOM_DRAWABLE);
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        } else {
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }


    private void init() {
        _items = new ArrayList<CheckValue>();
        _dialog = MultiCheckItemViewDialog.newInstance(_items);
        _dialog.setSeletedItemsListener(this);
        _dialog.setTitle(getHint() != null ? getHint().toString():"");

        updateDrawables();

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                Drawable right = getCompoundDrawables()[DRAWABLE_RIGHT];

                if(right != null && event.getAction() == MotionEvent.ACTION_UP) {
                    _dialog.show(scanForActivity(_context).getFragmentManager(), "TAG");
                }
                return false;
            }
        });

        // Consume los eventos de teclado y hace que el editText no sea editable.
        final int[] allowedKeyCodes= {61};
        this.setInputType(InputType.TYPE_NULL);
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                for( int k : allowedKeyCodes) {
                    if(k==keyCode) return false;
                }
                return true;
            }
        });
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (_dialog.isAdded()) {
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {

            if (_items.size()>0) {
                _dialog.show(scanForActivity(_context).getFragmentManager(), "TAG");
            }
        }
        return true;
    }



    // Indica la lista de items utilizada en el dialogo.
    public void setAllItems(List items) {
        CheckValue v;
        _items.clear();
        if(items==null) {
            updateText();
        } else {
            for(Object item : items) {
                v = new CheckValue(item);
                _items.add(v);
            }
        }
        updateText();
    }


    // Borrar el contenido.
    public void clearAllItems() {
        _items.clear();
        updateText();
    }


    // Permite especificar los items seleccionados.
    public void setSelectedItems(List selectedItems) {
        for(CheckValue v : _items) {
            v.setChecked(false);
        }
        if(selectedItems!=null) {
            for(CheckValue v : _items) {
                if (selectedItems.contains(v.getValue())) {
                    v.setChecked(true);
                }
            }
        }
        updateText();
    }


    @Override
    public void onCheckedItemsChanged(List<CheckValue> checkedItems) {
        updateText();
        if(listener != null) {
            List items = new ArrayList();
            for(CheckValue v : checkedItems) {
                items.add(v.getValue());
            }
            listener.onItemSelected(items);
        }
    }

    // Actualizar el texto mostrado por el widget.
    private void updateText() {
        String s = null;
        for (CheckValue item : _items) {
            if(item.isChecked()) {
                if (s == null) {
                    s = item.toString();
                } else {
                    s += ", " + item.toString();
                }
            }
        }

        this.setText(s);
    }

    public void setOnMultiChecktemListener(OnMultiChecktemListener listener) {
        this.listener = listener;
    }

    public void setError(CharSequence error) {
        if (error != null) {
            Toast.makeText(_context,error,Toast.LENGTH_LONG).show();
        }
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


}
