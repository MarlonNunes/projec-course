package com.projectcourse.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@SuperBuilder
public class Administrator extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAdmin;

}
