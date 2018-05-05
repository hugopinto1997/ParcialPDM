package com.hugopinto.parcialv4710;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainContactClick extends AppCompatActivity {

    TextView namepersonclick,apell, cid, emaill, address, numero, birthday;
    ImageView imagenclick, btn;
    int auxcosa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactoclick);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        namepersonclick = findViewById(R.id.clickname);
        cid = findViewById(R.id.clickid);
        apell=findViewById(R.id.clickapellido);
        address = findViewById(R.id.clickdireccion);
        numero = findViewById(R.id.clicknumero);
        emaill=findViewById(R.id.clickemail);
        numero=findViewById(R.id.clicknumero);
        birthday=findViewById(R.id.clickbirthdate);
        imagenclick = findViewById(R.id.photo);
        btn=findViewById(R.id.call);

//hacemos un intent y ub bundle
        Intent intent = this.getIntent();
        Bund
        le bundle = intent.getExtras();
//creamos cosas necesarias
        final Contacto persona = (Contacto) bundle.getSerializable("Llave");
// llenamos nuestro auto con las cosas
        namepersonclick.setText(persona.getNombre().toString());
        if(persona.getAddress()==null){
            address.setText("DIRECCION:  No posee dirección");
        }else{
            address.setText("DIRECCION:"+persona.getAddress().toString());
        }
        if(persona.getEmail()==null){
            emaill.setText("DIRECCION:  No posee dirección");
        }else{
            emaill.setText("EMAIL:"+persona.getEmail().toString());
        }
        imagenclick.setImageURI(Uri.parse(persona.getImagendraw()));
        if(persona.getNumber()==null){
            numero.setText("NUMERO: No posee numero");
        }else{
            numero.setText("NUMERO:"+persona.getNumber().toString());
        }
        if(persona.getApellido()==null){
            apell.setText("APELLIDO: No definido");
        }else{
            apell.setText("APELLIDO:"+persona.getApellido().toString());
        }
        if(persona.getBirthdate()==null){
            birthday.setText("BIRTH DATE: No definido");
        }else{
            birthday.setText("BIRTH DATE:"+persona.getBirthdate().toString());
        }
        cid.setText("ID:"+persona.getId().toString());

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(persona.getNumber() != null){

                    //Checking Permission is required Marshmallow up
                    if (ContextCompat.checkSelfPermission(MainContactClick.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainContactClick.this, new String[]{Manifest.permission.CALL_PHONE}, 7);
                    Toast.makeText(v.getContext(),"No tiene permiso",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(v.getContext(),"Llamando a "+persona.getNumber(),Toast.LENGTH_SHORT).show();
                        Intent call = new Intent(Intent.ACTION_CALL);
                        call.setData(Uri.parse("tel:"+persona.getNumber()));
                        startActivity(call);

                    }
                }
            }
        });

    }
}