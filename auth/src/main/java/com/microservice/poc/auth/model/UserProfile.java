package com.microservice.poc.auth.model;

import com.microservice.poc.common.utility.Utility;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
@Entity
@Table(name = Utility.USERPROFILETABLE)
public class UserProfile {

    @Column(unique = true)
    @Id
    private UUID id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true, length = 50)
    @NonNull
    private String email;

    @Column
    private String age;

    @Column
    private String sex;

    @Column
    private String imagePath;

    @Column
    private String dateOfBirth;

    @Column
    private String phone;

    @OneToOne
    @MapsId
    private User user;

}
