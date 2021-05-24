package com.google.GeoCart;

import androidx.room.Entity;

import java.io.Serializable;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tiendas implements Serializable {
    private String nombre;
    private double latitud, longitud;


    public Tiendas(double latitud, double longitud,String nombre) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
    }


}
