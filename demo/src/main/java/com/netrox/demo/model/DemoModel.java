package com.netrox.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DemoModel {
    @Id
    private Long id;
    private String name;

    public DemoModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
