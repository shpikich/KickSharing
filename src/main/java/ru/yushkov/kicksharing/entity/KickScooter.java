package ru.yushkov.kicksharing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class KickScooter {

    @NotBlank
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private KickScooter(String name, Long id) {
        this.name = Objects.requireNonNull("name", name);
        this.id = id;
    }

    public KickScooter() {

    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public static class Builder {
        private String name;
        private Long id;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public KickScooter build() {
            return new KickScooter(name, id);
        }
    }

    @Override
    public String toString() {
        return "KickScooter{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
