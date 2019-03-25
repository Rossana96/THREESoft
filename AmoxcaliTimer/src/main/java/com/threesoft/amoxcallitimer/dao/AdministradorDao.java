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
import com.threesoft.amoxcallitimer.model.Administrador;
//import mx.unam.is20191.utils.Rol;

/**
 * Clase que implementa las operaciones con la base de datos en lo que respecta
 * a la entidad de Administrador.
 *
 * @author Josué Cárdenas
 */
public class AdministradorDao extends AbstractDao<Long, Administrador> {

    /**
     * Método que busca a un Administrador en la base por correo o por email.
     *
     * @param userOrEmail Es el correo o el email del Administrador.
     * @return El Administrador que se encontró en la base de datos o, en caso de no
     * encontrarse, regresa un null.
     */
    public Administrador searchByUserNameOrEmail(String userOrEmail) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Administrador> crit = createCriteriaQuery(cb);
        Root<Administrador> r = createRoot(crit);
        return searchByExpressionUnique(crit, r, cb.or(cb.equal(r.get("correoAdmin"), userOrEmail)));
    }

    /**
     * Método que revisa si un mail existe en la base.
     *
     * @param email Es el email a buscar en la base.
     * @return True si existe ya en la base y false en otro caso.
     */
    public boolean mailExist(String email) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Administrador> crit = createCriteriaQuery(cb);
        Root<Administrador> r = createRoot(crit);
        return this.count(cb.equal(r.get("correoAdmin"), email)) > 0;
    }

    /**
     * Método que revisa si un Administrador existe en la base de datos.
     *
     * @param user Es el Administrador a buscar en la base.
     * @return True si es que existe y false en otro caso.
     */
    public boolean userExist(String user) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Administrador> crit = createCriteriaQuery(cb);
        Root<Administrador> r = createRoot(crit);
        return this.count(cb.equal(r.get("nombreCompleto"), user)) > 0;
    }

    /**
     * Método que revisa si un Administrador existe en la base de datos.
     *
     * @param userName Es el Administrador a buscar en la base.
     * @return True si es que existe y false en otro caso.
     */
    public boolean userNameExist(String userName) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Administrador> crit = createCriteriaQuery(cb);
        Root<Administrador> r = createRoot(crit);
        return this.count(cb.equal(r.get("userName"), userName)) > 0;
    }

    /**
     * Método que revisa si un Administrador existe en la base de datos.
     *
     * @param noTrabajador Es el Administrador a buscar en la base.
     * @return True si es que existe y false en otro caso.
     */
    public boolean userNoTrabajador(String noTrabajador) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Administrador> crit = createCriteriaQuery(cb);
        Root<Administrador> r = createRoot(crit);
        long a = this.count(cb.equal(r.get("noTrabajador"), noTrabajador));
        System.out.println("Num Trabajadores: "+ a);
        return a > 0;
    }

    /**
     * Método que regresa todos los Administradors en el sistema.
     *
     * @return La lista que contiene a todos los Administradors encontrados.
     */
    public List<Administrador> getAll() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Administrador> crit = createCriteriaQuery(cb);
        Root<Administrador> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("nombreCompleto")));
    }

    @Override
    protected CriteriaBuilder createCriteriaBuilder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected CriteriaQuery<Administrador> createCriteriaQuery(CriteriaBuilder cb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}