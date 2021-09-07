package ru.yushkov.kicksharing.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToMany
    private List<KickScooter> kickScooters;

    private User(String name, String surname, Integer age, Long userId, List<KickScooter> kickScooters) {
        this.name = Objects.requireNonNull(name, "name");
        this.surname = Objects.requireNonNull(surname, "surname");
        this.age = Objects.requireNonNull(age, "age");
        this.userId = userId;
        this.kickScooters = kickScooters;
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

    public Long getUserId() {
        return userId;
    }

    public List<KickScooter> getKickScooters() {
        return kickScooters;
    }

    public static class Builder {
        private String name;
        private String surname;
        private int age;
        private Long userId;
        private List<KickScooter> kickScooters;

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

        public Builder withId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder withKickScooters(List<KickScooter> kickScooters) {
            this.kickScooters = kickScooters;
            return this;
        }

        public User build() {
            return new User(name, surname, age, userId, kickScooters);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", userId=" + userId +
                ", kickScooters=" + kickScooters +
                '}';
    }
}
