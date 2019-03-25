/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer.controller;

import com.threesoft.amoxcallitimer.dao.AcademicoDao;
import com.threesoft.amoxcallitimer.model.Academico;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author damianri
 */

@ManagedBean
@ViewScoped
public class ValidarRegistroController implements Serializable {
    private List<Academico> academicos;

    public List<Academico> getAcademicos() {
        AcademicoDao academicos = new AcademicoDao();
        return academicos.getAll();
        
    }

    public void setAcademicos(List<Academico> academicos) {
        this.academicos = academicos;
    }
    
    
}