package by.gurinovich.travelcompanionsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {

    private UUID id;

    private String username;

    private String email;

    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    private String bio;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String avatar;

    @JsonProperty(value = "confirmation_code")
    private String confirmationCode;

    @JsonProperty(value = "is_email_verified")
    private boolean isEmailVerified;

    private Double rating;

}
