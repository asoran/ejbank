package com.ejbank.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

abstract public class RepositoryImpl<T> implements Repository<T> {

    @PersistenceContext(unitName = "EJBankPU")
    protected EntityManager em;

    @Override
    public T getById(int id) {
        return em.find(getClassT(), id);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getClassT());
        Root<T> rootEntry = cq.from(getClassT());
        CriteriaQuery<T> all = cq.select(rootEntry);

        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

     @Override
     public T add(T element) {
         em.persist(element);
         return element;
     }

     @Override
     public T remove(T element) {
         em.remove(element);
         return element;
     }

     @Override
     public T update(T element) {
         return em.merge(element);
     }

 }
