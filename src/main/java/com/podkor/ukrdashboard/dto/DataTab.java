package com.podkor.ukrdashboard.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.time.Instant;

@Getter
@Setter
@Entity
public class DataTab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String htmlData;

    private Instant createdDate;

    private Instant updatedDate;

    @OneToOne
    private User creator;

    private String styles;

    private Integer height;

    private Integer width;
}
