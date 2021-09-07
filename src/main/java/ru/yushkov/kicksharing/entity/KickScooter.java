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
    private Long kickScooterId;

    private KickScooter(String name, Long kickScooterId) {
        this.name = Objects.requireNonNull(name, "name");
        this.kickScooterId = kickScooterId;
    }

    public KickScooter() {

    }

    public String getName() {
        return name;
    }

    public Long getKickScooterId() {
        return kickScooterId;
    }

    public static class Builder {
        private String name;
        private Long kickScooterId;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withId(Long kickScooterId) {
            this.kickScooterId = kickScooterId;
            return this;
        }

        public KickScooter build() {
            return new KickScooter(name, kickScooterId);
        }
    }

    @Override
    public String toString() {
        return "KickScooter{" +
                "name='" + name + '\'' +
                ", kickScooterId=" + kickScooterId +
                '}';
    }
}
