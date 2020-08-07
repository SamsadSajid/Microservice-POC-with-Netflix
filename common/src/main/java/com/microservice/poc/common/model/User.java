package com.microservice.poc.common.model;

import com.microservice.poc.common.utility.Utility;
import lombok.*;

import javax.persistence.*;
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
@Table(name = Utility.USERTABLE)
public class User {

    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;
}
