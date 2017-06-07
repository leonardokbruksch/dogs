package com.leonardobruksch.domain.dogs;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Entity
public class Dogs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Dogs(String age, String name) {
        this.age = age;
        this.name = name;
    }

    protected Dogs( ){ }

    @Override
    public String toString() {
        return "Dogs{" +
                "id=" + id +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String age;
    private String name;

}
