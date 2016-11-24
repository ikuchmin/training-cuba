package com.company.training.service;


import com.company.training.entity.City;

public interface CityService {
    String NAME = "training_CityService";

    City getDefaultCity();
}