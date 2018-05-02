package com.hugopinto.parcialv4710;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Contacto implements Serializable{
    private String nombre;
    private String apellido;
    private String id;
    private String email;
    private String address;
    private String number;
    private String birthdate;
    private String imagendraw;

    public Contacto(String nombre) {
        this.nombre = nombre;
    }
    public Contacto(String nombre, String apellido, String id, String email, String address, String number, String birthdate
    , String imagendraw){
        this.nombre = nombre;
        this.apellido = apellido;
        this.id= id;
        this.email = email;
        this.address = address;
        this.number=number;
        this.birthdate=birthdate;
        this.imagendraw=imagendraw;
    }
    public Contacto(String nombre, String apellido, String id, String email, String address, String number, String birthdate){
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.email = email;
        this.address = address;
        this.number = number;
        this.birthdate = birthdate;
    }


    public String getNombre() {
        return nombre;
    }

    public String getImagendraw() {
        return imagendraw;
    }

    public void setImagendraw(String imagendraw) {
        this.imagendraw = imagendraw;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }



}
