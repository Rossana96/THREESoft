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
import com.threesoft.amoxcallitimer.model.Academico;
//import mx.unam.is20191.utils.Rol;

/**
 * Clase que implementa las operaciones con la base de datos en lo que respecta
 * a la entidad de Academico.
 *
 * @author Josué Cárdenas
 */
public class AcademicoDao extends AbstractDao<Long, Academico> {

    /**
     * Método que busca a un Academico en la base por correo o por email.
     *
     * @param userOrEmail Es el correo o el email del Academico.
     * @return El Academico que se encontró en la base de datos o, en caso de no
     * encontrarse, regresa un null.
     */
    public Academico searchByUserNameOrEmail(String userOrEmail) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Academico> crit = createCriteriaQuery(cb);
        Root<Academico> r = createRoot(crit);
        return searchByExpressionUnique(crit, r, cb.or(cb.equal(r.get("correoAca"), userOrEmail),
                cb.equal(r.get("userName"), userOrEmail)));
    }

    /**
     * Método que revisa si un mail existe en la base.
     *
     * @param email Es el email a buscar en la base.
     * @return True si existe ya en la base y false en otro caso.
     */
    public boolean mailExist(String email) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Academico> crit = createCriteriaQuery(cb);
        Root<Academico> r = createRoot(crit);
        return this.count(cb.equal(r.get("correoAca"), email)) > 0;
    }

    /**
     * Método que revisa si un Academico existe en la base de datos.
     *
     * @param user Es el Academico a buscar en la base.
     * @return True si es que existe y false en otro caso.
     */
    public boolean userExist(String user) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Academico> crit = createCriteriaQuery(cb);
        Root<Academico> r = createRoot(crit);
        return this.count(cb.equal(r.get("nombreCompleto"), user)) > 0;
    }

    /**
     * Método que revisa si un Academico existe en la base de datos.
     *
     * @param userName Es el Academico a buscar en la base.
     * @return True si es que existe y false en otro caso.
     */
    public boolean userNameExist(String userName) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Academico> crit = createCriteriaQuery(cb);
        Root<Academico> r = createRoot(crit);
        return this.count(cb.equal(r.get("userName"), userName)) > 0;
    }

    /**
     * Método que revisa si un Academico existe en la base de datos.
     *
     * @param noTrabajador Es el Academico a buscar en la base.
     * @return True si es que existe y false en otro caso.
     */
    public boolean userNoTrabajador(String noTrabajador) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Academico> crit = createCriteriaQuery(cb);
        Root<Academico> r = createRoot(crit);
        long a = this.count(cb.equal(r.get("noTrabajador"), noTrabajador));
        System.out.println("Num Trabajadores: "+ a);
        return a > 0;
    }

    /**
     * Método que regresa todos los Academicos en el sistema.
     *
     * @return La lista que contiene a todos los Academicos encontrados.
     */
    public List<Academico> getAll() {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Academico> crit = createCriteriaQuery(cb);
        Root<Academico> r = createRoot(crit);

        return this.findAll(crit, r, cb.asc(r.get("nombreCompleto")));
    }

}