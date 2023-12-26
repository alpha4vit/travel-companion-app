package by.gurinovich.travelcompanionsearch.model;

import by.gurinovich.travelcompanionsearch.util.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private UUID id;

    private String username;

    private String email;

    private String password;

    private String bio;

    private String avatar;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_email_verified")
    private boolean isEmailVerified;

    @Column(name = "confirmation_code")
    private String confirmationCode;


    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles")
    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Transport> transports;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

}
