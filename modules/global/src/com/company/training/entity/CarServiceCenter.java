package com.company.training.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import java.util.Set;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

@NamePattern("%s - %s|name,city")
@Table(name = "TRAINING_CAR_SERVICE_CENTER")
@Entity(name = "training$CarServiceCenter")
public class CarServiceCenter extends StandardEntity {
    private static final long serialVersionUID = -1962295644871768683L;

    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @Column(name = "PHONE", length = 20)
    protected String phone;

    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CITY_ID")
    protected City city;

    @JoinTable(name = "TRAINING_CUSTOMER_CAR_SERVICE_CENTER_LINK",
        joinColumns = @JoinColumn(name = "CAR_SERVICE_CENTER_ID"),
        inverseJoinColumns = @JoinColumn(name = "CUSTOMER_ID"))
    @ManyToMany
    protected Set<Customer> customers;

    @Composition
    @OneToMany(mappedBy = "center")
    protected Set<Employee> employees;

    @Column(name = "ADDRESS")
    protected String address;

    @Composition
    @OneToMany(mappedBy = "center")
    protected Set<Repair> repairs;

    public void setRepairs(Set<Repair> repairs) {
        this.repairs = repairs;
    }

    public Set<Repair> getRepairs() {
        return repairs;
    }


    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }


    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Customer> getCustomers() {
        return customers;
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

    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }


}