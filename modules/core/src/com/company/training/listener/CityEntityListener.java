package com.company.training.listener;

import com.company.training.entity.City;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.PersistenceTools;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Objects;

/**
 * Created by ikuchmin on 23.11.16.
 * Constraint to control that only one city has flag byDefault
 */
//@Component("cityEntityListener")
public class CityEntityListener implements BeforeInsertEntityListener<City>, BeforeUpdateEntityListener<City> {

    private static String queryString = "UPDATE training$City c SET c.byDefault = false WHERE c.byDefault = true AND c.id <> :id";

    @Inject
    PersistenceTools pt;

    @Override
    public void onBeforeInsert(City entity, EntityManager entityManager) {
        removePreviousFlagsByDefault(entity, entityManager);
    }

    @Override
    public void onBeforeUpdate(City entity, EntityManager entityManager) {
        if (!pt.isDirty(entity, "byDefault")) return;

        removePreviousFlagsByDefault(entity, entityManager);
    }

    private void removePreviousFlagsByDefault(City entity, EntityManager entityManager) {
        if (!Objects.equals(Boolean.TRUE, entity.getByDefault())) return;

        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", entity.getId());
        query.executeUpdate();
    }
}
