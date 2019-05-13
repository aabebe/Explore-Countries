package com.altimetric.playground.explorecountries.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Pair {

    private String id;

    private String iso2code;

    private String value;
}
