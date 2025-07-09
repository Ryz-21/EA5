/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LeoValdiviaSuasnabar.ExamenFinal.Model;

import jakarta.persistence.*;

public class mascotas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

   private String nombre;
   private String tipo;
   private String raza;
   private String edad;
   private String sexo;
   private String nombre_dueño;
   private String telefono_dueño;

    @Enumerated(EnumType.STRING)
    private EstadoMascota estado;

    private String nombreDoctorAsginado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombre_dueño() {
        return nombre_dueño;
    }

    public void setNombre_dueño(String nombre_dueño) {
        this.nombre_dueño = nombre_dueño;
    }

    public String getTelefono_dueño() {
        return telefono_dueño;
    }

    public void setTelefono_dueño(String telefono_dueño) {
        this.telefono_dueño = telefono_dueño;
    }

    public EstadoMascota getEstado() {
        return estado;
    }

    public void setEstado(EstadoMascota estado) {
        this.estado = estado;
    }

    public String getNombreDoctorAsginado() {
        return nombreDoctorAsginado;
    }

    public void setNombreDoctorAsginado(String nombreDoctorAsginado) {
        this.nombreDoctorAsginado = nombreDoctorAsginado;
    }

    public enum EstadoMascota {
        Bueno,
        Regular,
        Grave
    }
}
