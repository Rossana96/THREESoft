/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author damianri
 */
@Entity
@Table(name = "academico", catalog = "postgres", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Academico.findAll", query = "SELECT a FROM Academico a")
    , @NamedQuery(name = "Academico.findByIdAcademico", query = "SELECT a FROM Academico a WHERE a.idAcademico = :idAcademico")
    , @NamedQuery(name = "Academico.findByNombreCompleto", query = "SELECT a FROM Academico a WHERE a.nombreCompleto = :nombreCompleto")
    , @NamedQuery(name = "Academico.findByCorreoAca", query = "SELECT a FROM Academico a WHERE a.correoAca = :correoAca")
    , @NamedQuery(name = "Academico.findByPassword", query = "SELECT a FROM Academico a WHERE a.password = :password")
    , @NamedQuery(name = "Academico.findByDepartamento", query = "SELECT a FROM Academico a WHERE a.departamento = :departamento")
    , @NamedQuery(name = "Academico.findByTipo", query = "SELECT a FROM Academico a WHERE a.tipo = :tipo")
    , @NamedQuery(name = "Academico.findByNoTrabajador", query = "SELECT a FROM Academico a WHERE a.noTrabajador = :noTrabajador")
    , @NamedQuery(name = "Academico.findByFechaActivacion", query = "SELECT a FROM Academico a WHERE a.fechaActivacion = :fechaActivacion")
    , @NamedQuery(name = "Academico.findByUserName", query = "SELECT a FROM Academico a WHERE a.userName = :userName")})
public class Academico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_academico", nullable = false)
    private Long idAcademico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 210)
    @Column(name = "nombre_completo", nullable = false, length = 210)
    private String nombreCompleto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 210)
    @Column(name = "correo_aca", nullable = false, length = 210)
    private String correoAca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 210)
    @Column(name = "password", nullable = false, length = 210)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "departamento", nullable = false, length = 100)
    private String departamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "no_trabajador", nullable = false)
    private long noTrabajador;
    @Column(name = "fecha_activacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActivacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    public Academico() {
    }

    public Academico(Long idAcademico) {
        this.idAcademico = idAcademico;
    }

    public Academico(Long idAcademico, String nombreCompleto, String correoAca, String password, String departamento, String tipo, long noTrabajador, String userName) {
        this.idAcademico = idAcademico;
        this.nombreCompleto = nombreCompleto;
        this.correoAca = correoAca;
        this.password = password;
        this.departamento = departamento;
        this.tipo = tipo;
        this.noTrabajador = noTrabajador;
        this.userName = userName;
    }

    public Long getIdAcademico() {
        return idAcademico;
    }

    public void setIdAcademico(Long idAcademico) {
        this.idAcademico = idAcademico;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoAca() {
        return correoAca;
    }

    public void setCorreoAca(String correoAca) {
        this.correoAca = correoAca;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getNoTrabajador() {
        return noTrabajador;
    }

    public void setNoTrabajador(long noTrabajador) {
        this.noTrabajador = noTrabajador;
    }

    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcademico != null ? idAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Academico)) {
            return false;
        }
        Academico other = (Academico) object;
        if ((this.idAcademico == null && other.idAcademico != null) || (this.idAcademico != null && !this.idAcademico.equals(other.idAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.threesoft.amoxcalitimer.models.Academico[ idAcademico=" + idAcademico + " ]";
    }
    
}
