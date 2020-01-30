
package com.clases;

/**
 * Nombre de la clase:Coordinador
 * Fecha:15/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 * 
 */
public class Coordinador {
    private int CodigoCo;
    private String NombreCo;
    private int EdadCo;
    private String Sexo;
    private String CorreoCo;
    private int escuela;
    private int estado;

    public Coordinador() {
    }

    public Coordinador(int CodigoCo, String NombreCo, int EdadCo, String Sexo, String CorreoCo, int escuela, int estado) {
        this.CodigoCo = CodigoCo;
        this.NombreCo = NombreCo;
        this.EdadCo = EdadCo;
        this.Sexo = Sexo;
        this.CorreoCo = CorreoCo;
        this.escuela = escuela;
        this.estado = estado;
    }

    public int getCodigoCo() {
        return CodigoCo;
    }

    public void setCodigoCo(int CodigoCo) {
        this.CodigoCo = CodigoCo;
    }

    public String getNombreCo() {
        return NombreCo;
    }

    public void setNombreCo(String NombreCo) {
        this.NombreCo = NombreCo;
    }

    public int getEdadCo() {
        return EdadCo;
    }

    public void setEdadCo(int EdadCo) {
        this.EdadCo = EdadCo;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getCorreoCo() {
        return CorreoCo;
    }

    public void setCorreoCo(String CorreoCo) {
        this.CorreoCo = CorreoCo;
    }

    public int getEscuela() {
        return escuela;
    }

    public void setEscuela(int escuela) {
        this.escuela = escuela;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

   
}
