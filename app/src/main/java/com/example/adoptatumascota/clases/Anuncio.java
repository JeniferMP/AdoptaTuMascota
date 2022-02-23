package com.example.adoptatumascota.clases;

public class Anuncio {
    private int id;
    private String Especie;
    private String Nombre;
    private String Raza;
    private String Descripcion;
    private String Edad;
    private String Provincia;
    private String Distrito;
    private String Imagen;

    public Anuncio(){

    }

    public Anuncio( int id,String especie, String nombre, String raza, String descripcion, String edad,String provincia,
                    String distrito, String Imagen){
        this.setId(id);
        setEspecie(especie);
        setNombre(nombre);
        setRaza(raza);
        setDescripcion(descripcion);
        setEdad(edad);
        setProvincia(provincia);
        setDistrito(distrito);

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String especie) {
        Especie = especie;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String distrito) {
        Distrito = distrito;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }
}
