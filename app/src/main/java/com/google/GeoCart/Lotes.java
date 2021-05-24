package com.google.GeoCart;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Lotes implements Serializable {
    public int IDL, unidades;
    public String fecha_adq, fecha_cad;

}
