package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tablacontactos")
public class Contactos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requerimiento")
    private int id_requerimiento;
    
    @Column(name = "Correo_electronico")
    private String Correo_electronico;
    
    @Column(name = "Telefono_contacto")
    private int Telefono_contacto;
    
    @Column(name = "Mensaje")
    private String Mensaje;
    
    // Constructor por defecto (obligatorio para Hibernate)
    public Contactos() {
    }

    // Constructor con parámetros
    public Contactos(String correoElectronico, int telefonoContacto, String mensaje) {
        this.Correo_electronico = correoElectronico;
        this.Telefono_contacto = telefonoContacto;
        this.Mensaje = mensaje;
    }

    // Getters y setters
    public int getIdRequerimiento() {
        return id_requerimiento;
    }

    public void setIdRequerimiento(int idRequerimiento) {
        this.id_requerimiento = idRequerimiento;
    }

    public String getCorreoElectronico() {
        return Correo_electronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.Correo_electronico = correoElectronico;
    }

    public int getTelefonoContacto() {
        return Telefono_contacto;
    }

    public void setTelefonoContacto(int telefonoContacto) {
        this.Telefono_contacto = telefonoContacto;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        this.Mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Contactos [id_requerimiento=" + id_requerimiento + ", Correo_electronico=" + Correo_electronico
                + ", Telefono_contacto=" + Telefono_contacto + ", Mensaje=" + Mensaje + "]";
    }
}
