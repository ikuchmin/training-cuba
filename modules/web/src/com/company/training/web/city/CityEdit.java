package com.company.training.web.city;

import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.LoadContext.Query;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.training.entity.City;
import com.haulmont.cuba.gui.data.DataSupplier;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CityEdit extends AbstractEditor<City> {

    private static String queryString = "SELECT c FROM training$City c WHERE c.byDefault = true AND c.id <> :id";

    @Inject
    private DataSupplier dataSupplier;

    @Override
    protected boolean postCommit(boolean committed, boolean close) {
        if (committed && Objects.equals(getItem().getByDefault(), Boolean.TRUE)) {
            Query query = LoadContext.createQuery(queryString).setParameter("id", getItem().getId());
            List<City> cities = dataSupplier.loadList(LoadContext.create(City.class).setQuery(query));
            List<City> changed = cities.stream().peek(c -> c.setByDefault(false))
                                                .collect(toList());

            dataSupplier.commit(new CommitContext(changed));
        }

        return super.postCommit(committed, close);
    }
}