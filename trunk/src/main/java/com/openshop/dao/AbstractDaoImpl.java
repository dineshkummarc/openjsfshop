package com.openshop.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: PatrickTrautmann
 * Date: 19.08.11
 * Time: 15:56
 */
public class AbstractDaoImpl implements AbstractDao {

    @PersistenceContext
    private EntityManager em;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Constructor
     */
    public AbstractDaoImpl() {
        //Hey there! I'm empty and kind of senseless :)
    }

    /**
     * Constructor
     *
     * @param em EntityManager
     */
    public AbstractDaoImpl(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }

    public void detach(Object entity) {
        if (entity != null) {
            getEm().detach(entity);
        }
    }

    public void detach(Iterable<?> list) {
        if (list != null) {
            for (Object entity : list) {
                getEm().detach(entity);
            }
        }
    }

}
