package com.company.training.web.repair;

import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.training.entity.Repair;
import com.haulmont.cuba.gui.components.FieldGroup;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.Datasource.ItemChangeEvent;
import com.haulmont.cuba.gui.data.Datasource.ItemPropertyChangeEvent;

import javax.inject.Inject;
import java.util.Map;
import java.util.Objects;

public class RepairEdit extends AbstractEditor<Repair> {

    @WindowParam(name = "carCenterAssigned")
    private boolean carCenterAssigned;

    @Inject
    private FieldGroup fieldGroup;

    @Inject
    private Datasource<Repair> repairDs;

//    @Inject
//    private LookupField employeeLookup;

    @Override
    public void init(Map<String, Object> params) {
        fieldGroup.getFieldComponent("center").setEnabled(!carCenterAssigned);

        repairDs.addItemPropertyChangeListener(this::catchCarCenterChangeEvent);
    }

    private void catchCarCenterChangeEvent(ItemPropertyChangeEvent<Repair> event) {
        if (!Objects.equals("center", event.getProperty())) return;
//        if (Objects.equals(event.getPrevValue(), event.getValue())) return; // Not working

        repairDs.getItem().setEmployee(null); // Reset selected employee
    }
}