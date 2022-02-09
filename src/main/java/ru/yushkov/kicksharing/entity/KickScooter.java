package ru.yushkov.kicksharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "kickscooters")
public class KickScooter {

    @Id
    @Column(name = "kickscooter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kickScooterId;

    @Column(name = "name", unique = true)
    @NotBlank
    private String name;

    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private KickScooter(String name, Status status, Long kickScooterId, User user) {
        this.name = Objects.requireNonNull(name, "name");
        this.status = status;
        this.kickScooterId = kickScooterId;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public static class Builder {
        private String name;
        private Status status;
        private Long kickScooterId;
        private User user;

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

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public KickScooter build() {
            return new KickScooter(name, status, kickScooterId, user);
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
