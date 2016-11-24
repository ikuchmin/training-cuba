package com.company.training.web.employee;

import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.training.entity.Employee;
import com.haulmont.cuba.gui.components.FieldGroup;

import javax.inject.Inject;
import java.util.Map;

public class EmployeeEdit extends AbstractEditor<Employee> {

    @WindowParam(name = "carCenterAssigned")
    private boolean carCenterAssigned;

    @Inject
    private FieldGroup fieldGroup;

    @Override
    public void init(Map<String, Object> params) {
        fieldGroup.getFieldComponent("center").setEnabled(!carCenterAssigned);
    }

}