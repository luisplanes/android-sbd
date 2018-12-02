package com.planesnet.android.sbd.activity;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.planesnet.android.sbd.R;
import com.planesnet.android.sbd.util.Checking;

import java.util.Arrays;


public abstract class CRUDActivity extends AppCompatActivity {
    static final String TAG = CRUDActivity.class.getSimpleName();

    private FloatingActionButton fabSave;

    // Muestra los datos del documeto actual
    public abstract void findViews(View v);

    public abstract void displayViews();


    // Permite inicializar los datos por defecto de un documento.
    public abstract void defaultValues();

    // Permite inicializar los datos a partir de un documento existente
    public abstract void updateValuesFromDatabase(int id);

    // Actualiza el objeto del documeto con los valores de las vistas.
    public abstract void updateValuesFromViews();

    // Permite inicializar los datos de campos relacionados.
    public abstract void settingAdapters();

    // Verificar los datos
    public abstract void checkValues(OnChecking action);

    public abstract void requestFirstFocus();

    public abstract int layout();

    private boolean documentSave = false;

    public abstract boolean saveDocument();

    public abstract void printDocument();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fabSave = (FloatingActionButton) findViewById(R.id.fabSave);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionSave();
            }
        });

        /*
        View contentWrapper = findViewById(R.id.content_wrapper);
        ViewGroup parent = (ViewGroup) contentWrapper.getParent();
        int index = parent.indexOfChild(contentWrapper);
        parent.removeView(contentWrapper);
        View content = getLayoutInflater().inflate(layout(), parent, false);
        parent.addView(content, index);

        */
        LinearLayout contentWrapper = findViewById(R.id.content_wrapper);
        contentWrapper.setGravity(Gravity.TOP);
        View content = getLayoutInflater().inflate(layout(), contentWrapper, false);
        contentWrapper.addView(content);


        // Buscar las vistas asociadas a los datos.
        findViews(content);


    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int id = extras.getInt("doc_id");
            updateWorkflow(id);
        } else {
            insertWorkflow();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_print) {
            printWorkflow();
            return true;

        } else if (id == android.R.id.home) {
            backWorkflow();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        final int keyCode = event.getKeyCode();
        final int keyAction = event.getAction();


        if (keyCode == KeyEvent.KEYCODE_BACK && keyAction == KeyEvent.ACTION_DOWN) {
            backWorkflow();
            return true;
        }

        int[] keyCodeEx = {KeyEvent.KEYCODE_DPAD_LEFT, KeyEvent.KEYCODE_DPAD_RIGHT};
        boolean keyCodeException = Arrays.asList(keyCodeEx).contains(keyCode);
        if (keyAction == KeyEvent.ACTION_DOWN && !keyCodeException) {
            documentSave = false;
        }

        Log.i(TAG, "KeyCode:" + keyCode + " KeyAction:" + keyAction);

        return super.dispatchKeyEvent(event);

    }




    /* Añadir el menú asociado al documento */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.document, menu);
        return true;
    }


    // Accion para gestionar el guardado de datos.
    private void actionSave() {
        updateValuesFromViews();
        checkValues(new OnChecking() {
            @Override
            public void valid() {
                if (saveDocument()) {
                    documentSave = true;
                    Toast.makeText(CRUDActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void invalid(View view, String msg) {
                error(view, msg);
            }

            @Override
            public void invalid(String msg) {
                error(msg);
            }
        });
    }


    // Update workflow
    protected void updateWorkflow(int id) {
        // Inicializa el documento con los valores por defecto
        defaultValues();

        updateValuesFromDatabase(id);

        // Inicializar los adaptadores.
        settingAdapters();


        displayViews();
        requestFirstFocus();
    }

    // Insert workflow
    protected void insertWorkflow() {
        // Inicializa el documento con los valores por defecto
        defaultValues();

        // Inicializar los adaptadores.
        settingAdapters();


        displayViews();
        requestFirstFocus();
    }

    // Volver hacia atrás..
    protected void backWorkflow() {
        if (documentSave || documentIsSend()) {
            NavUtils.navigateUpFromSameTask(this);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("El documento no está guardardo, ¿Deseas abandonar la edición?")
                    .setCancelable(true)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            NavUtils.navigateUpFromSameTask(CRUDActivity.this);
                        }
                    })
                    .setNegativeButton("No", null);

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    // Imprime el documento.
    protected void printWorkflow() {
        updateValuesFromViews();
        checkValues(new OnChecking() {
            @Override
            public void valid() {
                if (!documentSave) {
                    if (saveDocument()) {
                        documentSave = true;
                        Toast.makeText(CRUDActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                        printDocument();
                    }
                } else {
                    printDocument();
                }
            }

            @Override
            public void invalid(View view, String msg) {
                error(view, msg);
            }

            @Override
            public void invalid(String msg) {
                error(msg);
            }
        });


    }


    // Permite deshabilitar el botón de guardado.
    public void disableSave() {
        fabSave.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccentDisabled)));
        fabSave.setEnabled(false);
    }


    public void error(View v) {
        Checking chk = new Checking(this, TAG);
        chk.error(v, null);
    }

    public void error(View v, String msg) {
        Checking chk = new Checking(this, TAG);
        chk.error(v, msg);
    }

    public void error(String msg) {
        Checking chk = new Checking(this, TAG);
        chk.error(null, msg);
    }


    public void setDocumentIsNotSave() {
        documentSave = false;
    }

    public void setDocumentIsSave() {
        documentSave = true;
    }

    public boolean documentIsSend() {
        return false;
    }

}
