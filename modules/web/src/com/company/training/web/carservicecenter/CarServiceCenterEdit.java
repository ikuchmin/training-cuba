package com.company.training.web.carservicecenter;

import com.company.training.entity.CarServiceCenter;
import com.company.training.entity.Company;
import com.company.training.entity.Customer;
import com.company.training.entity.Individual;
import com.company.training.service.CityService;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.FieldGroup;
import com.haulmont.cuba.gui.components.TabSheet;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.UUID;

public class CarServiceCenterEdit extends AbstractEditor<CarServiceCenter> {

    private Map<String, Object> WINDOW_PARAM = ParamsMap.of("carCenterAssigned", true);

    @Named("employeesTable.create")
    private CreateAction employeesTableCreate;

    @Named("employeesTable.edit")
    private EditAction employeesTableEdit;

    @Named("repairsTable.create")
    private CreateAction repairsTableCreate;

    @Named("repairsTable.edit")
    private EditAction repairsTableEdit;

    @Inject
    private CollectionDatasource<Customer, UUID> customersDs;

    @Inject
    private CityService cityService;

    @Inject
    private FieldGroup fieldGroup;

    @Inject
    private TabSheet carServiceSheet;

    @Inject
    private Table<Customer> customersTable;

    @Inject
    private ComponentsFactory componentsFactory;

    @Override
    public void init(Map<String, Object> params) {
        employeesTableCreate.setWindowParams(WINDOW_PARAM);
        employeesTableEdit.setWindowParams(WINDOW_PARAM);
        repairsTableCreate.setWindowParams(WINDOW_PARAM);
        repairsTableEdit.setWindowParams(WINDOW_PARAM);

        customersDs.addCollectionChangeListener(event -> updateSizeOfCustomersInTabCaption());
    }

    @Override
    protected void postInit() {
        updateSizeOfCustomersInTabCaption();

        customersTable.addGeneratedColumn("type", this::generateType);
    }

    private Component generateType(Customer customer) {
        String msgKey = "notDefined";
        if (customer instanceof Individual) {
            msgKey = "individual";
        } else if (customer instanceof Company) {
            msgKey = "company";
        }

        return new Table.PlainTextCell(getMessage(msgKey));
    }

    @Override
    protected void initNewItem(CarServiceCenter item) {
        item.setCity(cityService.getDefaultCity());
    }

    /**
     * Primary for side-effect on customerTab caption
     */
    private void updateSizeOfCustomersInTabCaption() {
        carServiceSheet.getTab("customersTab").setCaption(String.format("Customers (%d)", customersDs.size()));
    }
}