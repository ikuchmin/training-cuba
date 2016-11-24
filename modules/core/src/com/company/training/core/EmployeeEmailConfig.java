package com.company.training.core;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;

/**
 * Created by ikuchmin on 24.11.16.
 */
@Source(type = SourceType.DATABASE)
public interface EmployeeEmailConfig extends Config {

    @Property("training.fromAddress")
    @Default("no-reply@haulmont.com")
    String getFromAddress();

    void setFromAddress(String fromAddress);
}
