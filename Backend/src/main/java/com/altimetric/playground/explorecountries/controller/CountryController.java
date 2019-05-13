package com.altimetric.playground.explorecountries.controller;

import com.altimetric.playground.explorecountries.service.CountryService;
import com.altimetric.playground.explorecountries.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private ResponseService responseService;

    @GetMapping("/all")
    public ResponseEntity<?> getCountries() {
        return new ResponseEntity<>(countryService.getCountries(), HttpStatus.OK);
    }

    @GetMapping()
    @CrossOrigin
    public ResponseEntity<?> searchForCountry(@RequestParam("code") String code,
                                              @RequestParam(required = false, value = "region") Optional<String> region,
                                              @RequestParam(required = false, value = "incomeLevel") Optional<String> incomelevel,
                                              @RequestParam(required = false, value = "lendingType") Optional<String> lendingType) {
        return responseService.getResponse(countryService.getCountries(code, region, incomelevel, lendingType));
    }


}
