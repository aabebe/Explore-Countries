package com.altimetric.playground.explorecountries.serviceImpl;

import com.altimetric.playground.explorecountries.domain.CountryDTO;
import com.altimetric.playground.explorecountries.service.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseServiceImpl implements ResponseService {
    @Override
    public ResponseEntity<?> getResponse(List<CountryDTO> countries) {
        if (!countries.isEmpty()) {
            return new ResponseEntity<>(countries, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
