package cz.enehano.training.demoapp.restapi.model;

import javax.persistence.Id;

public class User {

    @Id
    private Long id;

    private String name;
}
