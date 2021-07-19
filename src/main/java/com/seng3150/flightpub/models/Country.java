/*
    Country.java
        - Models the DB table and maps the contraints via annotations
*/

package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "country")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "country_code3", nullable = false)
    private String countryCode3;

    @Column(name = "alternate_name1", nullable = false)
    private String alternateName1;

    @Column(name = "alternate_name2", nullable = false)
    private String alternateName2;

    @Column(name = "country_code2", nullable = false)
    private String countryCode2;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "mother_country_code3", nullable = false)
    private String motherCountryCode3;

    @Column(name = "mother_country_comment", nullable = false)
    private String motherCountryComment;
}
