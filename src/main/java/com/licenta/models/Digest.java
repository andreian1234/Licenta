package com.licenta.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Digest {

    @Column(name = "label")
    String label;

    @Column(name = "tag")
    String tag;

    @Column(name = "schemaOrgTag")
    String schemaOrgTag;

    @Column(name = "total")
    double total;

    @Column(name = "hasRDI")
    boolean hasRDI;

    @Column(name = "daily")
    double daily;

    @Column(name = "unit")
    String unit;

//    @ElementCollection
//    List<Sub> subList = new ArrayList<>();

}
