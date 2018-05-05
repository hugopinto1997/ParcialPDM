package com.hugopinto.parcialv4710;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import static com.hugopinto.parcialv4710.R.drawable.inf;

public class personaclickimagen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaclickimagen);
        ImageView comprobando = findViewById(R.id.photo);

        Intent intent = this.getIntent();
        Bundle bundle2 = intent.getExtras();
//creamos cosas necesarias
        String foto = (String) bundle2.getSerializable("images");
        PhotoView photo = findViewById(R.id.photo_view2);
        if(foto.matches("fallo")){
            photo.setImageURI(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+
                    "://"+getResources().getResourcePackageName(R.drawable.inf)+'/'+
                    getResources().getResourceTypeName(R.drawable.inf)+'/'+getResources().getResourceEntryName(R.drawable.inf)));

        }
        if(foto!=null){
            photo.setImageURI(Uri.parse(foto));
        }
    }
}
