package com.company.training.service;

import com.company.training.entity.City;
import com.haulmont.cuba.core.Persistence;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service(CityService.NAME)
public class CityServiceBean implements CityService {

    @Inject
    protected Persistence persistence;

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public City getDefaultCity() {
        String queryString = "SELECT c FROM training$City c WHERE c.byDefault = true";
        List resultList = persistence.getEntityManager().createQuery(queryString).getResultList();

        if (CollectionUtils.isEmpty(resultList)) return null;

        return (City) resultList.get(0);
    }
}