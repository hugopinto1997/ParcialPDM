package com.hugopinto.parcialv4710;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainContactoPortrait extends AppCompatActivity {

    TextView nombre, apellido, id, correo, direccion, numero, hbd;
    Button btn;
    TextView setcumple;
    private String fecha;
    ImageView imagen, btnimg;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    static final int REQUEST_CODE_ASK_PERMISSION = 2018;
    static final int rqw=200;
    int calendario, calendario2;

    private void accessPermission(){
        calendario = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR);

        if(calendario != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CALENDAR},REQUEST_CODE_ASK_PERMISSION);
        }
    }
    private void accessPermission2(){
        calendario = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR);

        if(calendario2 != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_CALENDAR},rqw);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case REQUEST_CODE_ASK_PERMISSION:
                if(grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), "Ha autorizado el permiso", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Permiso denegado",Toast.LENGTH_SHORT).show();
                }
                break;
            case rqw:
                if(grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), "Ha autorizado el permiso", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Permiso denegado",Toast.LENGTH_SHORT).show();
                }
                break;


            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fabcontacto);
        accessPermission();
        accessPermission2();

        nombre = findViewById(R.id.name);
        apellido=findViewById(R.id.lastname);
        id = findViewById(R.id.id);
        correo = findViewById(R.id.email);
        direccion = findViewById(R.id.address);
        hbd = findViewById(R.id.birthdate);
        btn = findViewById(R.id.agregarcontacto);
        imagen=findViewById(R.id.cimg);
        btnimg=findViewById(R.id.addimg);
        setcumple=findViewById(R.id.sethbd);

        hbd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(MainContactoPortrait.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setcumple.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        };






    }







}
