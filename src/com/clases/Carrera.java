
package com.clases;

/**
 * Nombre de la clase:Carrera
 * Fecha:15/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 */
public class Carrera {
     private int IdCarrera;
    private String NombreCarrera;
    private String TipoCarrera;
    private int estado;

    public Carrera() {
    }

    public Carrera(int IdCarrera, String NombreCarrera, String TipoCarrera, int estado) {
        this.IdCarrera = IdCarrera;
        this.NombreCarrera = NombreCarrera;
        this.TipoCarrera = TipoCarrera;
        this.estado = estado;
    }

    public int getIdCarrera() {
        return IdCarrera;
    }

    public void setIdCarrera(int IdCarrera) {
        this.IdCarrera = IdCarrera;
    }

    public String getNombreCarrera() {
        return NombreCarrera;
    }

    public void setNombreCarrera(String NombreCarrera) {
        this.NombreCarrera = NombreCarrera;
    }

    public String getTipoCarrera() {
        return TipoCarrera;
    }

    public void setTipoCarrera(String TipoCarrera) {
        this.TipoCarrera = TipoCarrera;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
  
}
