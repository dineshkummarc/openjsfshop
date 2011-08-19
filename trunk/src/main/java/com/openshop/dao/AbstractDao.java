package com.openshop.dao;

import javax.persistence.EntityManager;

/**
 * User: PatrickTrautmann
 * Date: 19.08.11
 * Time: 15:56
 */

/**
 * Base Dao
 */
public interface AbstractDao {

      /**
     * Creates the Entity Manager
     *
     * @return Entity manager
     */
    EntityManager getEm();

    /**
     * Detach Entity from context
     *
     * @param entity Entity to be detached
     */
    void detach(Object entity);

    /**
     * Detaches List of Entities from persistence context
     *
     * @param list List of entities to be detached
     */
    void detach(Iterable<?> list);

}
