package com.altimetric.playground.explorecountries.service;

import com.altimetric.playground.explorecountries.domain.CountryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseService {

    ResponseEntity<?> getResponse(List<CountryDTO> countries);
}
