package com.company.training.core;

import com.company.training.entity.Employee;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Scripting;
import com.haulmont.cuba.security.app.Authenticated;
import groovy.lang.Binding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by ikuchmin on 24.11.16.
 */
@Component("employeeWorkerMBean")
public class EmployeeWorker implements EmployeeWorkerMBean {

    protected Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    private Scripting scripting;

    @Inject
    private EmailService emailService;

    @Inject
    private DataManager dataManager;

    @Inject
    private EmployeeEmailConfig emailConfig;

    @Authenticated
    @Override
    public void sendGreetings(String scriptName) {
        LoadContext.Query query = LoadContext.createQuery("select e from training$Employee e");
        List<Employee> employees = dataManager.loadList(LoadContext.create(Employee.class)
                                                                   .setQuery(query)
                                                                   .setView("employee-birthday"));

//        EmployeeEmailConfig config = AppBeans.get(Configuration.class).getConfig(EmployeeEmailConfig.class);
        Function<Employee, Optional<EmailInfo>> currMakeMail = employee -> makeMail(employee, scriptName, emailConfig.getFromAddress());

        employees.stream().filter(this::checkPropertiesAvailable)
                          .filter(this::today)
                          .map(currMakeMail)
                          .forEach(employee -> employee.ifPresent(emailService::sendEmailAsync));
    }

    @Authenticated
    @Override
    public void changeFromAddress(String fromAddress) {
        if (fromAddress == null) return;

        emailConfig.setFromAddress(fromAddress);

    }

    private boolean checkPropertiesAvailable(Employee employee) {
        return employee.getEmail() != null &&
               employee.getBirthDate() != null;
    }

    private Optional<EmailInfo> makeMail(Employee employee, String scriptName, String fromAddress) {
        try {
            String bodyMail =
                    scripting.runGroovyScript(scriptName,
                                              new Binding(ParamsMap.of("em", employee,
                                                                       "age", calculateAge(employee))));

            return Optional.of(new EmailInfo(employee.getEmail(),
                                             "Happy Birthday!",
                                             fromAddress,
                                             bodyMail));
        } catch (Exception e) {
            log.error("Error runGroovyScript", e);
        }

        return Optional.empty();
    }

    private boolean today(Employee employee) {
        ZonedDateTime now = Instant.now().atZone(ZoneId.systemDefault());
        ZonedDateTime birthDate = employee.getBirthDate().toInstant().atZone(ZoneId.systemDefault());

        return now.getDayOfMonth() == birthDate.getDayOfMonth() &&
               now.getMonthValue() == birthDate.getMonthValue();
    }

    private int calculateAge(Employee employee) {
        ZonedDateTime now = Instant.now().atZone(ZoneId.systemDefault());
        ZonedDateTime birthDate = employee.getBirthDate().toInstant().atZone(ZoneId.systemDefault());

        return now.getYear() - birthDate.getYear();
    }
}
