package by.gurinovich.travelcompanionsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @JsonProperty(value = "confirmation_code", access = JsonProperty.Access.READ_ONLY)
    private String confirmationCode;

    @JsonProperty(value = "is_email_verified", access = JsonProperty.Access.READ_ONLY)
    private boolean isEmailVerified;

}
