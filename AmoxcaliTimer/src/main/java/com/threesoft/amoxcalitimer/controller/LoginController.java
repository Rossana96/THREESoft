
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.persistence.EntityManagerFactory;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.threesoft.amoxcallitimer.model.Administrador;
import com.threesoft.amoxcallitimer.model.Academico;
import com.threesoft.amoxcalitimer.AdministradorJpaController;
import com.threesoft.amoxcalitimer.AcademicoJpaController;

/**
 *
 * @author rossa
 */
@ManagedBean
@SessionScoped
public class LoginController {

    private final EntityManagerFactory emf;
    private Academico acdmc;
    private Administrador admin;
    private int rol;
    private long id;
    private final AdministradorJpaController adjc;
    private final AcademicoJpaController ajc;
    private String email;
    private String pass;

    public LoginController() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        emf = EntityProvider.provider();
        acdmc = new Academico();
        admin = new Administrador();
        adjc = new AdministradorJpaController(emf);
        ajc = new AcademicoJpaController(emf);
        email = "";
        pass = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    

    public int getRol() {
        return rol;
    }

    /**
     * @param rol
     */
    public void setRol(int rol) {
        this.rol = rol;
    }

    public Academico getAcdmc() {
        return acdmc;
    }

    /**
     * @param acdmc the alum to set
     */
    public void setAcdmc(Academico acdmc) {
        this.acdmc = acdmc;
    }

    /**
     * @return the admin
     */
    public Administrador getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    public String loginUser() throws IOException {
        boolean loggedAlumno = ajc.findAcdmcCorreoYPass(email, pass);
        boolean loggedAdmin = adjc.findAdministradorCorreoYPass(email, pass);

        if (loggedAlumno) {
            Academico academico = ajc.findAcademicoCorreo(email);
            acdmc = academico;
            rol = 1;
            id = acdmc.getIdAcademico();
            FacesContext context = getCurrentInstance();
            context.getExternalContext().getSessionMap().put("user_name", academico);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Felicidades, el academico si existe en ele sistema", ""));
            FacesContext.getCurrentInstance().getExternalContext().redirect("AcademicoIndex.xhtml");
        } else if (loggedAdmin) {
            Administrador administrador = adjc.findAdministrador(email);
            FacesContext context = getCurrentInstance();
            context.getExternalContext().getSessionMap().put("user_name", administrador);
            admin = administrador;
            rol = 2;
            id = admin.getIdAdministrador();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Felicidades, el administrador si existe en ele sistema", ""));
            return "AdminIndex?faces-redirect=true";
        } 
        return "registro?faces-redirect=true";
    }

    public String logoutUser() {
        FacesContext context = getCurrentInstance();
        context.getExternalContext().invalidateSession();
        return "/faces/Index?faces-redirect=true";
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

}
