package com.projectcourse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Administrator extends User{

    @Id
    private Integer idAdmin;

}
