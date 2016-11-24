package com.company.training.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Column;

@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "TRAINING_COMPANY")
@Entity(name = "training$Company")
public class Company extends Customer {
    private static final long serialVersionUID = -8043907405940274108L;

    @Column(name = "INN", nullable = false, length = 10)
    protected String inn;

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getInn() {
        return inn;
    }


}