package com.example.adoptatumascota.clases;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int Usuario_ID;
    private int Rol_ID;
    private String Usu_nombre;
    private String Usu_apellidos;
    private String Usu_telefono;
    private String Usu_direccion;
    private String Usu_correo;
    private String Usu_contrasenia;

    public Usuario() {
    }

    public Usuario(int usuario_ID, int rol_ID, String usu_nombre, String usu_apellidos, String usu_telefono, String usu_direccion, String usu_correo, String usu_contrasenia) {
        Usuario_ID = usuario_ID;
        Rol_ID = rol_ID;
        Usu_nombre = usu_nombre;
        Usu_apellidos = usu_apellidos;
        Usu_telefono = usu_telefono;
        Usu_direccion = usu_direccion;
        Usu_correo = usu_correo;
        Usu_contrasenia = usu_contrasenia;
    }

    public int getUsuario_ID() {
        return Usuario_ID;
    }

    public void setUsuario_ID(int usuario_ID) {
        Usuario_ID = usuario_ID;
    }

    public int getRol_ID() {
        return Rol_ID;
    }

    public void setRol_ID(int rol_ID) {
        Rol_ID = rol_ID;
    }

    public String getUsu_nombre() {
        return Usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        Usu_nombre = usu_nombre;
    }

    public String getUsu_apellidos() {
        return Usu_apellidos;
    }

    public void setUsu_apellidos(String usu_apellidos) {
        Usu_apellidos = usu_apellidos;
    }

    public String getUsu_telefono() {
        return Usu_telefono;
    }

    public void setUsu_telefono(String usu_telefono) {
        Usu_telefono = usu_telefono;
    }

    public String getUsu_direccion() {
        return Usu_direccion;
    }

    public void setUsu_direccion(String usu_direccion) {
        Usu_direccion = usu_direccion;
    }

    public String getUsu_correo() {
        return Usu_correo;
    }

    public void setUsu_correo(String usu_correo) {
        Usu_correo = usu_correo;
    }

    public String getUsu_contrasenia() {
        return Usu_contrasenia;
    }

    public void setUsu_contrasenia(String usu_contrasenia) {
        Usu_contrasenia = usu_contrasenia;
    }
}
