package by.gurinovich.travelcompanionsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {

    private UUID id;

    @Size(min = 3, max = 50, message = "Должно быть от 3 до 50 символов")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Электронная почта должна быть корректной")
    private String email;

    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    private String bio;

    @Size(min = 5, max = 50, message = "Должно быть от 5 до 50 символов")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String avatar;

    @JsonProperty(value = "confirmation_code", access = JsonProperty.Access.WRITE_ONLY)
    private String confirmationCode;

    @JsonProperty(value = "is_email_verified")
    private boolean isEmailVerified;

    private Double rating;

}
