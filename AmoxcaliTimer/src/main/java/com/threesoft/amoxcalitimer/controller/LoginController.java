/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.controller;

/**
 *
 * @author dams_
 */
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import com.threesoft.amoxcalitimer.dao.AcademicoDao;
import com.threesoft.amoxcalitimer.models.Academico;

/**
 * Controlador que implmenta las acciones relacionadas con el inicio de sesión
 * del Academico.
 *
 * @author Josué Rodrigo Cárdenas Vallarta
 */
@ManagedBean
@ViewScoped
public class LoginController implements Serializable {

    /**
     * Atributo donde almacenaremos el nombre o correo del Academico.
     */
    private String userName;
    /**
     * Atributo donde almacenamos la contrqaseña sin encriptar.
     */
    private String password;

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

    /**
     * Contructor del controlador.
     */
    public LoginController() {
    }

    /**
     * Método que inicia sesión a un Academico.
     *
     */
    public void loginUser() {
        AcademicoDao usuarioDao = new AcademicoDao();
        Academico u = usuarioDao.searchByUserNameOrEmail(userName);
        try {
            if (u == null || !u.getPassword().equals(password)) {
                FacesContext.getCurrentInstance().addMessage("messages",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o Contraseña incorrectos.", ""));
            } else {
                if (u.getFechaActivacion() == null) {//cambiar línea de comparación por "!="
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.getExternalContext().getSessionMap().put("user", u);
                    ExternalContext eContext = context.getExternalContext();
                    eContext.redirect(eContext.getRequestContextPath() + "/views/academico/solicitar_espacio.xhtml");
                }
                FacesContext.getCurrentInstance().addMessage("messages",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Tu cuenta aun no ha sido verificada, intenta en otro momento", ""));
            }
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Por el momento no se puede iniciar sesiòn en el sistema, intèntelo màs tarde.", ""));
        }
    }

    /**
     * Método que cierra la sesión de un Academico.
     */
    public void logoutUser(){
        try {
            System.out.println("Cerrando Sesión");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha cerrado la sesión con éxito, vuelva pronto.",
                            "Se ha cerrado la sesión con éxito, vuelva pronto."));
            context.getExternalContext().getFlash().setKeepMessages(true);
            ExternalContext eContext = context.getExternalContext();
            eContext.redirect(eContext.getRequestContextPath());
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Ha ocurrido un problema con el cierre de sesión, inténtelo más tarde.",
                            "Ha ocurrido un problema con el cierre de sesión, inténtelo más tarde."));
        }
    }

    public void clear(){
        this.password = null;
        this.userName = null;
    }
    
}
