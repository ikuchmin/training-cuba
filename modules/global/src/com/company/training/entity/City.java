package com.company.training.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Listeners;

@NamePattern("%s|name")
@Table(name = "TRAINING_CITY")
@Entity(name = "training$City")
//@Listeners("cityEntityListener")
public class City extends StandardEntity {
    private static final long serialVersionUID = -5360810720313271322L;

    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @Column(name = "CODE", unique = true, length = 4)
    protected String code;

    @Column(name = "BY_DEFAULT")
    protected Boolean byDefault;

    public void setByDefault(Boolean byDefault) {
        this.byDefault = byDefault;
    }

    public Boolean getByDefault() {
        return byDefault;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}