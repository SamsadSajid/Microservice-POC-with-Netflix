package com.microservice.poc.auth.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
@Entity
@Table(name = "pocuser")
public class User {
    @Column(unique = true)
    @Id
    @NonNull
    private UUID id;

    @Column(unique = true, length = 50)
    @NonNull
    private String userName;

    @Column
    @NonNull
    private String password;

    @Column
    @NonNull
    private LocalDateTime lastLoggedIn;
}
