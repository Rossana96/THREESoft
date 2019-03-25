/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcallitimer.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author damianri
 */
@Entity
@Table(name = "espacio", catalog = "postgres", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Espacio.findAll", query = "SELECT e FROM Espacio e")
    , @NamedQuery(name = "Espacio.findByIdEspacio", query = "SELECT e FROM Espacio e WHERE e.idEspacio = :idEspacio")
    , @NamedQuery(name = "Espacio.findByNombreEspacio", query = "SELECT e FROM Espacio e WHERE e.nombreEspacio = :nombreEspacio")
    , @NamedQuery(name = "Espacio.findByEdificio", query = "SELECT e FROM Espacio e WHERE e.edificio = :edificio")
    , @NamedQuery(name = "Espacio.findByCapacidad", query = "SELECT e FROM Espacio e WHERE e.capacidad = :capacidad")
    , @NamedQuery(name = "Espacio.findByRecursos", query = "SELECT e FROM Espacio e WHERE e.recursos = :recursos")
    , @NamedQuery(name = "Espacio.findByPiso", query = "SELECT e FROM Espacio e WHERE e.piso = :piso")})
public class Espacio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_espacio", nullable = false)
    private Long idEspacio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 210)
    @Column(name = "nombre_espacio", nullable = false, length = 210)
    private String nombreEspacio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 210)
    @Column(name = "edificio", nullable = false, length = 210)
    private String edificio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidad", nullable = false)
    private int capacidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "recursos", nullable = false, length = 255)
    private String recursos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "piso", nullable = false)
    private int piso;

    public Espacio() {
    }

    public Espacio(Long idEspacio) {
        this.idEspacio = idEspacio;
    }

    public Espacio(Long idEspacio, String nombreEspacio, String edificio, int capacidad, String recursos, int piso) {
        this.idEspacio = idEspacio;
        this.nombreEspacio = nombreEspacio;
        this.edificio = edificio;
        this.capacidad = capacidad;
        this.recursos = recursos;
        this.piso = piso;
    }

    public Long getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(Long idEspacio) {
        this.idEspacio = idEspacio;
    }

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public void setNombreEspacio(String nombreEspacio) {
        this.nombreEspacio = nombreEspacio;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspacio != null ? idEspacio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Espacio)) {
            return false;
        }
        Espacio other = (Espacio) object;
        if ((this.idEspacio == null && other.idEspacio != null) || (this.idEspacio != null && !this.idEspacio.equals(other.idEspacio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.threesoft.amoxcallitimer.model.Espacio[ idEspacio=" + idEspacio + " ]";
    }
    
}
