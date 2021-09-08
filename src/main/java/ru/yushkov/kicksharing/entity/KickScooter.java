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

    private Status status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kickScooterId;

    private KickScooter(String name, Status status, Long kickScooterId) {
        this.name = Objects.requireNonNull(name, "name");
        this.status = status;
        this.kickScooterId = kickScooterId;
    }

    public KickScooter() {

    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Long getKickScooterId() {
        return kickScooterId;
    }

    public static class Builder {
        private String name;
        private Status status;
        private Long kickScooterId;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withId(Long kickScooterId) {
            this.kickScooterId = kickScooterId;
            return this;
        }

        public KickScooter build() {
            return new KickScooter(name, status, kickScooterId);
        }
    }

    @Override
    public String toString() {
        return "KickScooter{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", kickScooterId=" + kickScooterId +
                '}';
    }
}
