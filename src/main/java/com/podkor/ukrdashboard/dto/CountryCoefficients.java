package com.podkor.ukrdashboard.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class CountryCoefficients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String season;

    private int position;

    private String country;

    @Column(name = "season_1_points")
    private double season1Points;

    @Column(name = "season_2_points")
    private double season2Points;

    @Column(name = "season_3_points")
    private double season3Points;

    @Column(name = "season_4_points")
    private double season4Points;

    @Column(name = "season_5_points")
    private double season5Points;

    private double totalPoints;

    private int remainedClubs;

    private int totalClubs;

    private String flagUrl;
}
