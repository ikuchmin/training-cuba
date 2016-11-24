package com.company.training.web.city;

import com.company.training.entity.City;
import com.company.training.web.action.CustomAction;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.function.Consumer;

public class CityBrowse extends AbstractLookup {

    @Named("citiesTable.edit")
    private EditAction citiesTableEdit;

    @Named("citiesTable.create")
    private CreateAction citiesTableCreate;

    @Inject
    private Table<City> citiesTable;

    @Inject
    private DataManager dataManager;

    @Override
    public void init(Map<String, Object> params) {
        citiesTable.addAction(new CustomAction(citiesTable, WindowManager.OpenType.THIS_TAB, "customEdit"));

        // Refresh table for base actions
//        citiesTableCreate.setAfterCommitHandler(this::refreshTable);
//        citiesTableEdit.setAfterCommitHandler(this::refreshTable);
    }

    private void refreshTable(Entity entity) {
        citiesTable.refresh();
    }
}