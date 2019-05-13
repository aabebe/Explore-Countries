package com.altimetric.playground.explorecountries.service;

import com.altimetric.playground.explorecountries.domain.Country;
import com.altimetric.playground.explorecountries.domain.CountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> loadCountry();

    List<Country> getCountries();

    List<CountryDTO> getCountries(String code,
                                  Optional<String> region,
                                  Optional<String> incomeLevel,
                                  Optional<String> lendingType);

    List<Country> getByCountryCode(String code);

    List<Country> getByMatchingRegion(String region);

    List<Country> getByIncomeLevel(String incomeLevel);

    List<Country> getByLendingType(String lendingType);

}
