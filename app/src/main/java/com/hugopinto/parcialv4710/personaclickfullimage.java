package com.hugopinto.parcialv4710;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

public class personaclickfullimage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaclickfullimage);
        PhotoView insert = findViewById(R.id.photo_view3);
        getSupportActionBar().setTitle("Imagen");

        Intent intent = this.getIntent();
        Bundle bundle2 = intent.getExtras();
//creamos cosas necesarias
        Contacto p = (Contacto) bundle2.getSerializable("cimagenf");

        insert.setImageURI(Uri.parse(p.getImagendraw()));
    }
}
