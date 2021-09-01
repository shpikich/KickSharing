package ru.yushkov.kicksharing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class User {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotNull
    private int age;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private User(String name, String surname, Integer age, Long id) {
        this.name = Objects.requireNonNull(name, "name");
        this.surname = Objects.requireNonNull(surname, "surname");
        this.age = Objects.requireNonNull(age, "age");
        this.id = id;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public static class Builder {
        private String name;
        private String surname;
        private int age;
        private Long id;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withAge(int age) {
            this.age = age;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public User build() {
            return new User(name, surname, age, id);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
