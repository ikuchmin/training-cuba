package com.company.training.web.carservicecenter;

import com.company.training.entity.CarServiceCenter;
import com.company.training.entity.Company;
import com.company.training.entity.Customer;
import com.company.training.entity.Individual;
import com.company.training.service.CityService;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.chile.core.model.Session;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.FieldGroup;
import com.haulmont.cuba.gui.components.TabSheet;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.AddAction;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.components.actions.RemoveAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.entity.EntityAttrAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.global.UserSession;

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

    @Named("employeesTable.remove")
    private RemoveAction employeesTableRemove;

    @Named("repairsTable.create")
    private CreateAction repairsTableCreate;

    @Named("repairsTable.edit")
    private EditAction repairsTableEdit;

    @Named("repairsTable.remove")
    private RemoveAction repairsTableRemove;

    @Named("customersTable.add")
    private AddAction customersTableAdd;

    @Named("customersTable.remove")
    private RemoveAction customersTableRemove;

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

    @Inject
    private Security security;

    @Inject
    private Metadata metadata;

    @Inject
    private UserSession userSession;

    @Override
    public void init(Map<String, Object> params) {
        employeesTableCreate.setWindowParams(WINDOW_PARAM);
        employeesTableEdit.setWindowParams(WINDOW_PARAM);
        repairsTableCreate.setWindowParams(WINDOW_PARAM);
        repairsTableEdit.setWindowParams(WINDOW_PARAM);

        customersDs.addCollectionChangeListener(event -> updateSizeOfCustomersInTabCaption());
        fieldGroup.getFieldComponent("owner").setEnabled(false);

        Session session = metadata.getSession();
        MetaClass metaCarServiceCenter = session.getClassNN(CarServiceCenter.class);
        checkPermissionsOnActionsRepairsTable(metaCarServiceCenter);
        checkPermissionsOnActionsCustomersTable(metaCarServiceCenter);
        checkPermissionsOnActionsEmployeesTable(metaCarServiceCenter);
    }

    @Override
    protected void postInit() {
        updateSizeOfCustomersInTabCaption();

        customersTable.addGeneratedColumn("type", this::generateType);
    }

    /**
     * Primary for side-effect
     */
    private void checkPermissionsOnActionsRepairsTable(MetaClass metaClass) {
        if (!security.isEntityAttrUpdatePermitted(metaClass, "repairs")) {
            repairsTableCreate.setEnabled(false);
            repairsTableRemove.setEnabled(false);
        }
    }

    /**
     * Primary for side-effect
     */
    private void checkPermissionsOnActionsCustomersTable(MetaClass metaClass) {
        if (!security.isEntityAttrUpdatePermitted(metaClass, "employees")) {
            customersTableAdd.setEnabled(false);
            customersTableRemove.setEnabled(false);
        }
    }

    /**
     * Primary for side-effect
     */
    private void checkPermissionsOnActionsEmployeesTable(MetaClass metaClass) {
        if (!security.isEntityAttrUpdatePermitted(metaClass, "employees")) {
            employeesTableCreate.setEnabled(false);
            employeesTableRemove.setEnabled(false);
        }
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
        item.setOwner(userSession.getUser());
        item.setCity(cityService.getDefaultCity());
    }

    /**
     * Primary for side-effect on customerTab caption
     */
    private void updateSizeOfCustomersInTabCaption() {
        carServiceSheet.getTab("customersTab").setCaption(String.format("Customers (%d)", customersDs.size()));
    }
}