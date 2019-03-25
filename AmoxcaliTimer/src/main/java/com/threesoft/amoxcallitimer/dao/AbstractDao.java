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
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

/**
 * Clase abstracta que especifica todos los métodos que tendrán los DAOs
 * ocupados en el proyecto.
 *
 * @param <PK> Es la clase que tendrá la llave primaria en el modelo
 * @param <T> Es la clase con la que se mapeará a la entidad en la base de
 * datos.
 */
public abstract class AbstractDao<PK extends Serializable, T> {

    /**
     * Debido a que la clase T no está determinada hasta la implementación y es
     * requerida en varias partes de la implementación se almacenará en una
     * variable.
     */
    protected Class<T> persistentClass;

    /**
     * Entity manager de la sesión en curso.
     */
    protected EntityManager em;

    /**
     * Variable a la cuál se enlazará el entityManagerFactory especificado en la
     * configuración de la base de datos.
     */
    private final static EntityManagerFactory FACTORY;

    static {
        FACTORY = Persistence.createEntityManagerFactory("postgres_PU");
    }

    /**
     * Método constructor que instacia clase y obtiene la clase de persistencia
     * del DAO que implemente esta clase.
     */
    public AbstractDao() {
        FACTORY.getCache().evictAll();
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * Método que, apartir del EntityManagerFactory, se obtendrá un
     * EntityManager para hacer operaciones con los modelos.
     *
     * @return El EntityManager asociado a la sesión.
     */
    public EntityManager getEntityManager() {
        if (em == null) {
            em = FACTORY.createEntityManager();
        }
        return em;
    }

    /**
     * Método que obtiene un objeto de la entidad a partir de su llave primaria.
     *
     * @param key El objeto que representa la llave primaria.
     * @return El objeto que tiene esa llave primaria o nulo en otro caso.
     */
    public T getByKey(PK key) {
        return (T) getEntityManager().find(persistentClass, key);
    }

    /**
     * Método que, a partir de una entidad, la almacena en la base de datos.
     *
     * @param entity El objeto que tiene los datos del registro a insertar.
     */
    public void save(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * Método que se aplica para actualizar una entidad en la base de datos.
     *
     * @param entity Es el objeto que contiene los nuevos datos actualizados.
     * @return El objeto con los datos ya actualizados y con el formato
     * requerido en caso que la base le haya hecho algo al registro.
     */
    public T update(T entity) {
        return getEntityManager().merge(entity);
    }

    /**
     * Método que elimina una entidad a partir del objeto que se le pasa como
     * parámetro.
     *
     * @param entity Es el objeto que queremos eliminar de la base.
     */
    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));
    }

    /**
     * Método que sincroniza con la base de datos las operaciones pendientes.
     */
    public void flush() {
        getEntityManager().flush();
    }

    protected CriteriaBuilder createCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    protected CriteriaQuery<T> createCriteriaQuery(CriteriaBuilder builder) {
        return builder.createQuery(persistentClass);
    }

    protected CriteriaDelete<T> createCriteriaDelete(CriteriaBuilder builder) {
        return builder.createCriteriaDelete(persistentClass);
    }

    protected Root<T> createRoot(CriteriaQuery<T> crit) {
        return crit.from(persistentClass);
    }

    protected Root<T> createRoot(CriteriaDelete<T> crit) {
        return crit.from(persistentClass);
    }

    private TypedQuery<T> createCriteriaQueryByExpression(CriteriaQuery<T> crit, Selection<T> r, Predicate exp, Order... orderBy) {
        if (r != null) {
            crit.select(r);
        }
        if (exp != null) {
            crit.where(exp);
        }
        if (orderBy != null) {
            crit.orderBy(orderBy);
        }
        return getEntityManager().createQuery(crit);
    }

    protected List<T> findAll(CriteriaQuery<T> crit, Selection<T> r, Order... orderBy) {
        return createCriteriaQueryByExpression(crit, r, null, orderBy).getResultList();
    }

    protected List<T> searchByExpression(CriteriaQuery<T> crit, Selection<T> r, Predicate exp, Order... orderBy) {
        return createCriteriaQueryByExpression(crit, r, exp, orderBy).getResultList();
    }

    protected T searchByExpressionUnique(CriteriaQuery<T> crit, Selection<T> r, Predicate exp, Order... orderBy) {
        try {
            return createCriteriaQueryByExpression(crit, r, exp, orderBy).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    protected List<T> searchByExpression(CriteriaQuery<T> crit, Selection<T> r, Predicate exp) {
        return searchByExpression(crit, r, exp, (Order[]) null);
    }

    protected T searchByExpressionUnique(CriteriaQuery<T> crit, Selection<T> r, Predicate exp) {
        try {
            return searchByExpressionUnique(crit, r, exp, (Order[]) null);
        } catch (NoResultException nre) {
            return null;
        }
    }

    protected T searchByExpressionUnique(CriteriaQuery<T> crit, Selection<T> r) {
        try {
            return searchByExpressionUnique(crit, r, null, (Order[]) null);
        } catch (NoResultException nre) {
            return null;
        }
    }

    protected int deleteByExpression(CriteriaDelete<T> crit, Predicate exp) {
        return createCriteriaDeleteByExpression(crit, exp);
    }

    protected Query createQuery(String query) {
        return getEntityManager().createQuery(query);
    }

    protected Query createSqlQuery(String query) {
        return getEntityManager().createNativeQuery(query);
    }

    protected int executeQuery(Query q) {
        return q.executeUpdate();
    }

    protected List<T> listQuery(Query q) {
        return q.getResultList();
    }

    protected T getQuery(Query q) {
        List<T> ans = listQuery(q);
        return ans.isEmpty() ? null : ans.get(0);
    }

    private int createCriteriaDeleteByExpression(CriteriaDelete<T> crit, Predicate exp) {
        crit.where(exp);
        return getEntityManager().createQuery(crit).executeUpdate();
    }

    /**
     * Método que obtiene el conteo de registros de una entidad a partir de un
     * predicado.
     *
     * @param exp Es el predicado que requerimos para hacer el filtro del
     * conteo.
     * @return El conteo de registros que se obtuvieron a partir del predicado.
     */
    public long count(Predicate exp) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(this.persistentClass)));
        if (exp != null) {
            cq.where(exp);
        }
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    /**
     * Método que obtiene el conteo de registros totales en la entidad.
     *
     * @return El número de registros.
     */
    public long count() {
        return count(null);
    }

}
