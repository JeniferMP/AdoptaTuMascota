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
    private String Anunciante;
    private String Direccion;
    private String Telefono;
    private String fecha;



    public Anuncio(){

    }

    public Anuncio( int id,String especie, String nombre, String raza, String descripcion, String edad,String provincia,
                    String distrito, String imagen, String anunciante, String direccion, String fecha, String telefono){
        this.setId(id);
        setEspecie(especie);
        setNombre(nombre);
        setRaza(raza);
        setDescripcion(descripcion);
        setEdad(edad);
        setProvincia(provincia);
        setDistrito(distrito);
        setImagen(imagen);
        setAnunciante(anunciante);
        setDireccion(direccion);
        setTelefono(telefono);
        setFecha(fecha);
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

    public String getAnunciante() {
        return Anunciante;
    }

    public void setAnunciante(String anunciante) {
        Anunciante = anunciante;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
