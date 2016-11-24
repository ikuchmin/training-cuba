package com.company.training.core;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Created by ikuchmin on 24.11.16.
 */
@ManagedResource(description = "Performs operations on employees")
public interface EmployeeWorkerMBean {

    @ManagedOperation(description = "Sends mail")
    @ManagedOperationParameters(
            {@ManagedOperationParameter(name = "scriptName",
                    description = "path to the script relative to conf dir or to the classpath root")})
    void sendGreetings(String scriptName);

    @ManagedOperation(description = "Changes from address")
    @ManagedOperationParameters(
            {@ManagedOperationParameter(name = "fromAddress",
                    description = "new from address")})
    void changeFromAddress(String fromAddress);
}
