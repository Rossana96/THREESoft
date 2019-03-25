/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.controller;

/**
 *
 * @author damianri
 */
import com.threesoft.amoxcalitimer.Correo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import com.threesoft.amoxcallitimer.dao.AcademicoDao;
import com.threesoft.amoxcallitimer.model.Academico;
import com.threesoft.amoxcallitimer.dao.AdministradorDao;
import com.threesoft.amoxcallitimer.model.Administrador;
import com.threesoft.amoxcallitimer.dao.EspacioDao;
import com.threesoft.amoxcallitimer.model.Espacio;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class RegistroController implements Serializable {

    private String nombreCompleto;
    private String userName;
    private String password;
    private String correo;
    private Integer noTrabajador;
    private String departamento;
    private String tipoProfesor;
    private String edificio;
    private int capacidad;
    private String recursos;
    private int piso;

    public Integer getNoTrabajador() {
        return noTrabajador;
    }

    public void setNoTrabajador(Integer noTrabajador) {
        this.noTrabajador = noTrabajador;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTipoProfesor() {
        return tipoProfesor;
    }

    public void setTipoProfesor(String tipoProfesor) {
        this.tipoProfesor = tipoProfesor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEdificio() {
        return this.edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getRecursos() {
        return recursos;
    }

    public int getPiso() {
        return piso;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public RegistroController() {
        this.nombreCompleto = null;
        this.correo = null;
        this.password = null;
        this.userName = null;
        this.departamento = null;
        this.noTrabajador = null;
        this.tipoProfesor = null;
        this.capacidad = 0;
        this.edificio = null;
        this.piso = 0;
        this.recursos = null;
    }

    /**
     * Método que registra al academico con el formulario ya validado.
     *
     */
    public void registrarAcademico() throws Exception {
        try {
            System.out.println("Registrando");
            Academico nuevoAcademico = new Academico();
            nuevoAcademico.setNombreCompleto(nombreCompleto);
            nuevoAcademico.setCorreoAca(correo);
            nuevoAcademico.setUserName(userName);
            nuevoAcademico.setPassword(password);
            nuevoAcademico.setNoTrabajador(noTrabajador);
            nuevoAcademico.setDepartamento(departamento);
            nuevoAcademico.setTipo(tipoProfesor);
            nuevoAcademico.setIdAcademico(Long.MIN_VALUE);

            AcademicoDao academicoDao = new AcademicoDao();
            academicoDao.getEntityManager().getTransaction().begin();
            academicoDao.save(nuevoAcademico);
            academicoDao.getEntityManager().getTransaction().commit();
            Correo.correoDeRegistro(nuevoAcademico.getCorreoAca(), nuevoAcademico.getNombreCompleto());
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Tu registro se generó correctamente, espera correo de confirmación de alta.",
                            "Tu registro se generó correctamente, espera correo de confirmación de alta."));
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            ExternalContext eContext = context.getExternalContext();
            eContext.redirect(eContext.getRequestContextPath() + "/index.xhtml");
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos agregar su registro al sistema, inténtelo más tarde.",
                            "Por el momento no podemos agregar su registro al sistema, inténtelo más tarde."));
        }
    }

    /**
     * Método que registra al administrador con el formulario ya validado.
     *
     */
    public void registrarAdministrador() throws Exception {
        try {
            System.out.println("Registrando");
            Administrador nuevoAdministrador = new Administrador();
            nuevoAdministrador.setNombreCompleto(nombreCompleto);
            nuevoAdministrador.setCorreoAdmin(correo);
            nuevoAdministrador.setPassword(password);
            nuevoAdministrador.setNoTrabajador(noTrabajador);

            AdministradorDao administradorDao = new AdministradorDao();
            administradorDao.getEntityManager().getTransaction().begin();
            administradorDao.save(nuevoAdministrador);
            administradorDao.getEntityManager().getTransaction().commit();
            Correo.correoDeActivacionAdmin(nuevoAdministrador.getCorreoAdmin(), nuevoAdministrador.getNombreCompleto(), nuevoAdministrador.getPassword());
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "El registro se lleneró correctamente, le llegará un correo al nuevo administrador.",
                            "El registro se lleneró correctamente, le llegará un correo al nuevo administrador."));
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            ExternalContext eContext = context.getExternalContext();
            eContext.redirect(eContext.getRequestContextPath() + "/views/administrador/registro_administrador.xhtml");
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos agregar su registro al sistema, inténtelo más tarde.",
                            "Por el momento no podemos agregar su registro al sistema, inténtelo más tarde."));
        }
    }

    /**
     * Método que registra al espacio con el formulario ya validado.
     *
     */
    public void registrarEspacio() throws Exception {
        try {
            System.out.println("Registrando");
            Espacio nuevoEspacio = new Espacio();
            nuevoEspacio.setNombreEspacio(nombreCompleto);
            nuevoEspacio.setEdificio(edificio);
            nuevoEspacio.setCapacidad(capacidad);
            nuevoEspacio.setPiso(piso);
            nuevoEspacio.setRecursos(recursos);

            EspacioDao espacioDao = new EspacioDao();
            espacioDao.getEntityManager().getTransaction().begin();
            espacioDao.save(nuevoEspacio);
            espacioDao.getEntityManager().getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Tu registro se generó correctamente, nuevo administrador.",
                            "Tu registro se generó correctamente, nuevo administrador."));
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            ExternalContext eContext = context.getExternalContext();
            eContext.redirect(eContext.getRequestContextPath() + "/views/administrador/registro_administrador.xhtml");
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos agregar su registro al sistema, inténtelo más tarde.",
                            "Por el momento no podemos agregar su registro al sistema, inténtelo más tarde."));
        }
    }

    //Ejemplo para algunos casos que se necesitaran para validar.
    public void validatePassword(FacesContext context, UIComponent component, Object value) {
        // Retrieve the value passed to this method
        String confirmPassword = (String) value;
        // Retrieve the temporary value from the password field
        UIInput passwordInput = (UIInput) component.findComponent("password");
        String password = (String) passwordInput.getLocalValue();
        if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", "Las contraseñas no coinciden");
            throw new ValidatorException(msg);
        }
    }

    /**
     * Método que valida si un academico ya está registrado en la base de datos,
     * manda error si se escribe un academico que ya esté registrado.
     *
     * @param context Es el contexto del jsf.
     * @param component Es el componente que contiene el user name.
     * @param value Es el valor obtenido del componente que llama al validador,
     * es decir, el user name.
     */
    public void validateUniqueUserName(FacesContext context, UIComponent component, Object value) {
        AcademicoDao academicoDao = new AcademicoDao();
        if (academicoDao.userNameExist(value.toString())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El academico ya existe, escriba otro.",
                    "El academico ya existe, escriba otro.");
            throw new ValidatorException(msg);
        }
    }

    public void validateUniqueNameAdmin(FacesContext context, UIComponent component, Object value) {
        AdministradorDao administradorDao = new AdministradorDao();
        if (administradorDao.userExist(value.toString())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El administrador ya existe, escriba otro.",
                    "El administrador ya existe, escriba otro.");
            throw new ValidatorException(msg);
        }
    }

    public void validateUniqueNameEspacio(FacesContext context, UIComponent component, Object value) {
        EspacioDao espacioDao = new EspacioDao();
        if (espacioDao.userExist(value.toString())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El espacio ya existe, escriba otro.",
                    "El espacio ya existe, escriba otro.");
            throw new ValidatorException(msg);
        }
    }

    public void validateUniqueName(FacesContext context, UIComponent component, Object value) {
        AcademicoDao academicoDao = new AcademicoDao();
        if (academicoDao.userExist(value.toString())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El academico ya existe, escriba otro.",
                    "El academico ya existe, escriba otro.");
            throw new ValidatorException(msg);
        }
    }

    public void validateUniqueNumAdmin(FacesContext context, UIComponent component, Object value) {
        AdministradorDao administradorDao = new AdministradorDao();
        if (administradorDao.userNoTrabajador(value.toString())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Ya existe un Administrador con ese número, ingresar otro.",
                    "Ya existe un Administrador con ese número, ingresar otro.");
            throw new ValidatorException(msg);
        }
    }

    public void validateUniqueNumAca(FacesContext context, UIComponent component, Object value) {
        AcademicoDao academicoDao = new AcademicoDao();
        if (academicoDao.userNoTrabajador(value.toString())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Ya existe un Acádemico con ese número, ingresar otro.",
                    "Ya existe un Acádemico con ese número, ingresar otro.");
            throw new ValidatorException(msg);
        }
    }

    /**
     * Método que verifica si un email es único.
     *
     * @param context Es el contexto del jsf.
     * @param component Es el componente que contiene el email.
     * @param value Es el valor del mail que se tiene.
     */
    public void validateUniqueEmail(FacesContext context, UIComponent component, Object value) {
        AcademicoDao academicoDao = new AcademicoDao();
        if (academicoDao.mailExist((String) value)) {
            validateUniqueEmailAdmin(context, component, value);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El correo que intenta dar ya está registrado, escriba otro.",
                    "El correo que intenta dar ya está registrado, escriba otro.");
            throw new ValidatorException(msg);
        }
    }

    public void validateUniqueEmailAdmin(FacesContext context, UIComponent component, Object value) {
        AdministradorDao administradorDao = new AdministradorDao();
        if (administradorDao.mailExist((String) value)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El correo que intenta dar ya está registrado, escriba otro.",
                    "El correo que intenta dar ya está registrado, escriba otro.");
            throw new ValidatorException(msg);
        }
    }

    /**
     * Método que verifica que los campos necesarios esten llenos
     */
    public boolean formulario() {
        if (this.nombreCompleto != null && this.correo != null && this.password != null && this.userName != null
                && this.departamento != null && this.noTrabajador != null && this.tipoProfesor != null) {
            return true;
        }
        return false;
    }

    /**
     * Método que elimina el archivo subido cuando se ingresa en la página de
     * regitro.
     */
    public void clear() {
        this.nombreCompleto = null;
        this.correo = null;
        this.password = null;
        this.userName = null;
        this.departamento = null;
        this.noTrabajador = null;
        this.tipoProfesor = null;
        this.capacidad = 0;
        this.edificio = null;
        this.piso = 0;
        this.recursos = null;
    }

}