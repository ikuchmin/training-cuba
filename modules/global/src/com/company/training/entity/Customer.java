package com.company.training.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.MappedSuperclass;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s - %s|name,email")
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TRAINING_CUSTOMER")
@Entity(name = "training$Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = -7706807642556309159L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "PHONE", nullable = false, length = 20)
    protected String phone;

    @Column(name = "EMAIL")
    protected String email;

    @JoinTable(name = "TRAINING_CUSTOMER_CAR_SERVICE_CENTER_LINK",
        joinColumns = @JoinColumn(name = "CUSTOMER_ID"),
        inverseJoinColumns = @JoinColumn(name = "CAR_SERVICE_CENTER_ID"))
    @ManyToMany
    protected Set<CarServiceCenter> services;

    public void setServices(Set<CarServiceCenter> services) {
        this.services = services;
    }

    public Set<CarServiceCenter> getServices() {
        return services;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


}