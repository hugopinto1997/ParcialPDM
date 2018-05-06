package com.hugopinto.parcialv4710;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class editpersonaclick extends AppCompatActivity {

    EditText namepersonclicke,apelle, cide, emaille, addresse, numeroe, editare;
    TextView birthdaye;
    ImageView imagenclicke;
    Button bc;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Button btn;
    ImageView imagenbot;
    String saveuri, posicion;

    static final int REQUEST_CODE_ASK_PERMISSION = 2018;
    static final int rqw = 200;
    int calendario, calendario2;

    private void accessPermission() {
        calendario = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR);

        if (calendario != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CALENDAR}, REQUEST_CODE_ASK_PERMISSION);
        }
    }

    private void accessPermission2() {
        calendario = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR);

        if (calendario2 != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CALENDAR}, rqw);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Ha autorizado el permiso", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Permiso denegado", Toast.LENGTH_SHORT).show();
                }
                break;
            case rqw:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Ha autorizado el permiso", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Permiso denegado", Toast.LENGTH_SHORT).show();
                }
                break;


            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpersonaclick);
        accessPermission();
        accessPermission2();

        namepersonclicke = findViewById(R.id.clicknameeditar);
        cide = findViewById(R.id.clickideditar);
        apelle = findViewById(R.id.clickapellidoeditar);
        addresse = findViewById(R.id.clickdireccionedittar);
        numeroe = findViewById(R.id.clicknumeroeditar);
        emaille = findViewById(R.id.clickemaileditar);
        birthdaye = findViewById(R.id.clickbirthdateeditar);
        imagenclicke = findViewById(R.id.photoeditar);
        bc = findViewById(R.id.btnsc);
        btn = findViewById(R.id.btneditar);
        imagenbot = findViewById(R.id.photoeditar);

//hacemos un intent y ub bundle
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        posicion = intent.getStringExtra(Intent.EXTRA_TEXT);
//creamos cosas necesarias
        final Contacto persona = (Contacto) bundle.getSerializable("editar");
// llenamos nuestro auto con las cosas
        namepersonclicke.setText(persona.getNombre().toString());
        if (persona.getAddress() == null) {
            addresse.setText("          ");
        } else {
            addresse.setText(persona.getAddress().toString());
        }
        if (persona.getEmail() == null) {
            emaille.setText("         ");
        } else {
            emaille.setText(persona.getEmail().toString());
        }
        imagenclicke.setImageURI(Uri.parse(persona.getImagendraw()));
        if (persona.getNumber() == null) {
            numeroe.setText("        ");
        } else {
            numeroe.setText(persona.getNumber().toString());
        }
        if (persona.getApellido() == null) {
            apelle.setText("        ");
        } else {
            apelle.setText(persona.getApellido().toString());
        }
        if (persona.getBirthdate() != null) {
            birthdaye.setText(persona.getBirthdate().toString());
        } else {
            birthdaye.setText("click aqui");
        }
        cide.setText(persona.getId().toString());

        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(editpersonaclick.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                birthdaye.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (namepersonclicke.getText().toString().isEmpty() ||
                        apelle.getText().toString().isEmpty() ||
                        cide.getText().toString().isEmpty() ||
                        emaille.getText().toString().isEmpty() ||
                        addresse.getText().toString().isEmpty() ||
                        numeroe.getText().toString().isEmpty() ||
                        birthdaye.getText().toString().isEmpty() ||
                        imagenbot == null) {
                    Toast.makeText(view.getContext(), "llene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Contacto c = new Contacto(namepersonclicke.getText().toString(), apelle.getText().toString(), cide.getText().toString(), emaille.getText().toString(),
                            addresse.getText().toString(), numeroe.getText().toString(), birthdaye.getText().toString(),
                            saveuri);
                    Intent sendIntent = new Intent(getApplicationContext(), MainActivity.class);
                    Bundle caja =new Bundle();
                    caja.putSerializable("main",c);
                    sendIntent.putExtras(caja);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,posicion);
                    editpersonaclick.this.startActivity(sendIntent);
                }
            }
        });
    }
    public void addimgeditar(View view) {
        cargarImagen();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        editpersonaclick.super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            imagenbot.setImageURI(path);
            saveuri = path.toString();
        }
    }
}
