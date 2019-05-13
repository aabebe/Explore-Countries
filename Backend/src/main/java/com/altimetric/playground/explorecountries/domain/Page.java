package com.altimetric.playground.explorecountries.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Page {

    private Integer page;

    private Integer pages;

    private String per_page;

    private Integer total;
}
