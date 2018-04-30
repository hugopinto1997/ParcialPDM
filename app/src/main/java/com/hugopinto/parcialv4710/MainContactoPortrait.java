package com.hugopinto.parcialv4710;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainContactoPortrait extends AppCompatActivity {

    TextView nombre, apellido, id, correo, direccion, numero, hbd;
    Button btn;
    private String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fabcontacto);

        nombre = findViewById(R.id.name);
        apellido=findViewById(R.id.lastname);
        id = findViewById(R.id.id);
        correo = findViewById(R.id.email);
        direccion = findViewById(R.id.address);
        hbd = findViewById(R.id.birthdate);
        btn = findViewById(R.id.agregarcontacto);

        hbd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Calendar = Calendar.getInstance();
            }
        });


    }







}
