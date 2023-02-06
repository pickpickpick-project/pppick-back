package com.pickx3.domain.entity.portfolio_package;

import javax.persistence.*;

@Entity
public class Tag {

    @Column(name="tagNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;

    private int tagHits;


}
