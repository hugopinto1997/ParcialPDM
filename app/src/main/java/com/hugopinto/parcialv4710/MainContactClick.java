package com.hugopinto.parcialv4710;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

    TextView namepersonclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactoclick);

        namepersonclick = findViewById(R.id.clickname);

//hacemos un intent y ub bundle
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
//creamos cosas necesarias
        Contacto persona = (Contacto) bundle.getSerializable("Llave");

// llenamos nuestro auto con las cosas
        namepersonclick.setText(persona.getNombre().toString());

    }
}