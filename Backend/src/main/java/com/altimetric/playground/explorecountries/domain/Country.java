package com.altimetric.playground.explorecountries.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Country {

    private String id;

    private String iso2Code;

    private String name;

    private String longitude;

    private String latitude;

    private Pair region;

    private Pair incomeLevel;

    private Pair adminregion;

    private Pair lendingType;

    private String capitalCity;



}
