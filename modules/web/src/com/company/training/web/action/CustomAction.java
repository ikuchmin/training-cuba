package com.company.training.web.action;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.ListComponent;
import com.haulmont.cuba.gui.components.actions.EditAction;

/**
 * Created by ikuchmin on 23.11.16.
 */
public class CustomAction extends EditAction {

    public CustomAction(ListComponent target) {
        super(target);
    }

    public CustomAction(ListComponent target, WindowManager.OpenType openType) {
        super(target, openType);
    }

    public CustomAction(ListComponent target, WindowManager.OpenType openType, String id) {
        super(target, openType, id);
    }

    @Override
    protected void afterCommit(Entity entity) {
        getTarget().refresh();
    }
}
