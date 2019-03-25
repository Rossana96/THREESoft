/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcallitimer.dao;

/**
 *
 * @author damianri
 */
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.threesoft.amoxcallitimer.model.Espacio;
//import mx.unam.is20191.utils.Rol;

/**
 * Clase que implementa las operaciones con la base de datos en lo que respecta
 * a la entidad de Espacio.
 *
 */
public class EspacioDao extends AbstractDao<Long, Espacio> {

    /**
     * Método que busca a un Espacio en la base por correo o por email.
     *
     * @param userOrEmail Es el correo o el email del Espacio.
     * @return El Espacio que se encontró en la base de datos o, en caso de no
     * encontrarse, regresa un null.
     */
    public Espacio searchByUserNameOrEmail(String userOrEmail) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Espacio> crit = createCriteriaQuery(cb);
        Root<Espacio> r = createRoot(crit);
        return searchByExpressionUnique(crit, r, cb.or(cb.equal(r.get("nombreEspacio"), userOrEmail),
                cb.equal(r.get("nombreEspacio"), userOrEmail)));
    }

    public boolean userExist(String user) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Espacio> crit = createCriteriaQuery(cb);
        Root<Espacio> r = createRoot(crit);
        return this.count(cb.equal(r.get("nombreEspacio"), user)) > 0;
    }

    /**
     * Método que regresa todos los Espacios en el sistema.
     *
     * @return La lista que contiene a todos los Espacios encontrados.
     */
    public List<Espacio> getAll() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Espacio> crit = createCriteriaQuery(cb);
        Root<Espacio> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("nombreEspacio")));
    }

}