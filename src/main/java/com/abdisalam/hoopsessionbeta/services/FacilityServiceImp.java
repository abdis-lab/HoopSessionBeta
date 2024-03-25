package com.abdisalam.hoopsessionbeta.services;

import com.abdisalam.hoopsessionbeta.model.Facility;
import com.abdisalam.hoopsessionbeta.repository.FacilityRepository;

public class FacilityServiceImp implements FacilityService{

    private FacilityRepository facilityRepository;

    public FacilityServiceImp(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public void saveFacility(Facility facility) {
        facilityRepository.save(facility);
    }
}
