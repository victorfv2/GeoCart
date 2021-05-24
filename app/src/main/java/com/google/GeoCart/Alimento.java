package com.google.GeoCart;

import androidx.room.Entity;

import java.io.Serializable;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Alimento implements Serializable {

    public int id,unidades;
    public String nombrep,BarCode;

    public Alimento(int id,String nombrep,String BarCode, int unidades) {
        this.id=id;
        this.nombrep=nombrep;
        this.BarCode=BarCode;
        this.unidades=unidades;

    }

}
