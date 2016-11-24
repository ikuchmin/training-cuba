package com.company.training.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorColumn;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Column;

@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "TRAINING_INDIVIDUAL")
@Entity(name = "training$Individual")
public class Individual extends Customer {
    private static final long serialVersionUID = 56459321831069468L;

    @Column(name = "PASSPORT_NO", nullable = false, length = 10)
    protected String passportNo;

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPassportNo() {
        return passportNo;
    }


}