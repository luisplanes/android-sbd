package com.planesnet.android.sbd.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class ItemSpinner<T> extends EditItemSpinner<T> {
    static final String TAG = ItemSpinner.class.getSimpleName();

    public ItemSpinner(Context context) {
        super(context);
        init();
    }

    public ItemSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private final int[] allowedKeyCodes= {61};

    private void init() {

        // Consume los eventos de teclado y hace que el spinner no
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


        // Modificar el onTouchListener cuando se pulsa sobre cualquier parte del widget.
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                Drawable right = getCompoundDrawables()[DRAWABLE_RIGHT];

                if(right != null && event.getAction() == MotionEvent.ACTION_UP) {
                    _searchableListDialog.show(scanForActivity(_context).getFragmentManager(), "TAG");
                }
                return false;
            }
        });




    }

}
