package com.hugopinto.parcialv4710;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

public class imagenfull extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenfull);


        ImageView comprobando = findViewById(R.id.photo);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Bundle bundle2 = intent.getExtras();
//creamos cosas necesarias
        String foto = (String) bundle2.getSerializable("image");
        final Contacto persona = (Contacto) bundle.getSerializable("imagen");
        getSupportActionBar().setTitle("Foto de: "+persona.getNombre());
        PhotoView photo = findViewById(R.id.photo_view);
        photo.setImageURI(Uri.parse(persona.getImagendraw()));


    }

}
