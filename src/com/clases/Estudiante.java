
package com.clases;

/**
 **Nombre de la clase:Estudiante
 * Fecha:15/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 */
public class Estudiante extends Carrera{
    private int Carnet;
    private String NombreEst;
    private int Edad;
    private String SexoEst;
    private String CorreoEst;
    private int IdCarreras ;
    private String EstadoCarrera;
    private String Sede;
    private int estado;

    public Estudiante() {
    }

   

    public Estudiante(int Carnet, String NombreEst, int Edad, String SexoEst, String CorreoEst, int IdCarreras, String EstadoCarrera, String Sede, int estado) {
        this.Carnet = Carnet;
        this.NombreEst = NombreEst;
        this.Edad = Edad;
        this.SexoEst = SexoEst;
        this.CorreoEst = CorreoEst;
        this.IdCarreras = IdCarreras;
        this.EstadoCarrera = EstadoCarrera;
        this.Sede = Sede;
        this.estado = estado;
    }

    public int getCarnet() {
        return Carnet;
    }

    public void setCarnet(int Carnet) {
        this.Carnet = Carnet;
    }

    public String getNombreEst() {
        return NombreEst;
    }

    public void setNombreEst(String NombreEst) {
        this.NombreEst = NombreEst;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public String getSexoEst() {
        return SexoEst;
    }

    public void setSexoEst(String SexoEst) {
        this.SexoEst = SexoEst;
    }

    public String getCorreoEst() {
        return CorreoEst;
    }

    public void setCorreoEst(String CorreoEst) {
        this.CorreoEst = CorreoEst;
    }

    public int getIdCarreras() {
        return IdCarreras;
    }

    public void setIdCarreras(int IdCarreras) {
        this.IdCarreras = IdCarreras;
    }

    public String getEstadoCarrera() {
        return EstadoCarrera;
    }

    public void setEstadoCarrera(String EstadoCarrera) {
        this.EstadoCarrera = EstadoCarrera;
    }

    public String getSede() {
        return Sede;
    }

    public void setSede(String Sede) {
        this.Sede = Sede;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
   
}
