package com.altimetric.playground.explorecountries.repository;

import com.altimetric.playground.explorecountries.domain.Country;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryRepository {

    private List<Country> countries= new ArrayList<>();

    public List<Country> addCountry(Country country){
        countries.add(country);
        return countries;
    }

    public List<Country> addAllCountries(List<Country> countryList){
        countries.addAll(countryList);
        return countries;
    }

    public List<Country> getCountries(){
        return countries;
    }
}
