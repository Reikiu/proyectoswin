
package com.clases;

/**
 **Nombre de la clase:Usuarios
* Fecha:15/01/2020
 * Version:1.4
 * Copy Right: ITCA-FEPADE
 * @author ServicioSocial
 */
public class Usuarios {
    private int IdUsuario;
    private String Usuario;
    private String Pass;
    private int Privilegios;
    private int estado;

    public Usuarios() {
    }

    public Usuarios(int IdUsuario, String Usuario, String Pass, int Privilegios, int estado) {
        this.IdUsuario = IdUsuario;
        this.Usuario = Usuario;
        this.Pass = Pass;
        this.Privilegios = Privilegios;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public int getPrivilegios() {
        return Privilegios;
    }

    public void setPrivilegios(int Privilegios) {
        this.Privilegios = Privilegios;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}

