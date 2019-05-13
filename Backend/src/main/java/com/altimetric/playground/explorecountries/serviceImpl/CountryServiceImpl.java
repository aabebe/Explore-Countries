package com.altimetric.playground.explorecountries.serviceImpl;

import com.altimetric.playground.explorecountries.domain.Country;
import com.altimetric.playground.explorecountries.domain.CountryDTO;
import com.altimetric.playground.explorecountries.domain.Page;
import com.altimetric.playground.explorecountries.repository.CountryRepository;
import com.altimetric.playground.explorecountries.service.CountryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ObjectMapper mapper;

    private final String url = "http://api.worldbank.org/v2/country?format=json";


    @Override
    public List<Country> loadCountry() {
        return countryRepository.addAllCountries(getAllCountries());
    }

    @Override
    public List<Country> getCountries() {
        return countryRepository.getCountries();
    }

    public List<Country> getAllCountries() {
        List<Country> countries1 = null;
        Page page = null;


        try {
            // getting the countries on the first page of the api response
            ResponseEntity<Object[]> countries = restTemplate.getForEntity(url, Object[].class);

            // getting the page detail from api JSON response

            page = mapper.convertValue(countries.getBody()[0], Page.class);

            countries1 = mapper.convertValue(countries.getBody()[1], new TypeReference<List<Country>>() {
            });


            // getting the countries from the rest of the pages

            for (int i = 2; i <= page.getPages(); i++) {
                ResponseEntity<Object[]> countryList = restTemplate.getForEntity(url + i,
                        Object[].class);
                countries1.addAll(mapper.convertValue(countryList.getBody()[1],
                        new TypeReference<List<Country>>() {
                        }));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return countries1;
    }

    @Override
    public List<CountryDTO> getCountries(String code,
                                         Optional<String> region,
                                         Optional<String> incomeLevel,
                                         Optional<String> lendingType) {
        List<CountryDTO> countries= new ArrayList<>();

        Predicate<Country> predicate= getCountryPredicate(code);
        Predicate<Country> regionPredicate= getRegionPredicate(region);
        Predicate<Country> incomeLevelPredicate= getIncomeLevelPredicate(incomeLevel);
        Predicate<Country> lendingTypePredicate= getLendingTypePredicate(lendingType);

        countries.addAll(getCountries().stream()
                .filter(regionPredicate.or(incomeLevelPredicate).or(lendingTypePredicate))
        .map(country -> new CountryDTO(country.getName(),country.getCapitalCity()))
        .collect(Collectors.toList()));

        return countries;
    }

    private Predicate<Country> getCountryPredicate(String code) {
        return c -> {
            return c.getId().equals(code);
        };
    }

    private Predicate<Country> getRegionPredicate(Optional<String> region) {
        return c -> {
            if (region.isPresent()) {
                return c.getRegion().getId().equals(region.get());
            }
            return false;
        };
    }
    private Predicate<Country> getIncomeLevelPredicate(Optional<String> incomeLevel){
        return c->{
            if (incomeLevel.isPresent()){
                return c.getIncomeLevel().getId().equals(incomeLevel.get());
            }
            return false;
        };
    }
    private Predicate<Country> getLendingTypePredicate(Optional<String> lendingType){
        return c->{
            if(lendingType.isPresent()){
                return c.getLendingType().getId().equals(lendingType.get());
            }
            return  false;

        };
    }

    @Override
    public List<Country> getByCountryCode(String code) {
        return countryRepository.getCountries().stream()
                .filter(country -> country.getId().equals(code))
                .collect(Collectors.toList());
    }

    @Override
    public List<Country> getByMatchingRegion(String region) {
        return countryRepository.getCountries().stream()
                .filter(country->country.getRegion().getId().equals(region))
                .collect(Collectors.toList());
    }

    @Override
    public List<Country> getByIncomeLevel(String incomeLevel) {
        return countryRepository.getCountries().stream()
                .filter(country -> country.getIncomeLevel().getId().equals(incomeLevel))
                .collect(Collectors.toList());
    }

    @Override
    public List<Country> getByLendingType(String lendingType) {
        return countryRepository.getCountries().stream()
                .filter(country -> country.getLendingType().getId().equals(lendingType))
                .collect(Collectors.toList());
    }
}
