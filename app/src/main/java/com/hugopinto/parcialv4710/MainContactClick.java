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

    TextView namepersonclick, numero, direccion, cid;
    ImageView imagenclick;
    int auxcosa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactoclick);

        namepersonclick = findViewById(R.id.clickname);
        cid = findViewById(R.id.clickid);
        direccion = findViewById(R.id.clickdireccion);
        numero = findViewById(R.id.clicknumero);
        imagenclick = findViewById(R.id.photo);

//hacemos un intent y ub bundle
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
//creamos cosas necesarias
        Contacto persona = (Contacto) bundle.getSerializable("Llave");
// llenamos nuestro auto con las cosas
        namepersonclick.setText(persona.getNombre().toString());
        direccion.setText(persona.getAddress().toString());
        numero.setText(persona.getNumber().toString());
        cid.setText(persona.getId().toString());

    }
}