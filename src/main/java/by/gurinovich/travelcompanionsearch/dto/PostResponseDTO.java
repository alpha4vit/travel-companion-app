package by.gurinovich.travelcompanionsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDTO {
    private Long id;

    @NotBlank(message = "Контактная информация не может быть пустой!")
    @Size(max = 50, message = "Длина контактной информации должна не превышать 50 символов!")
    private String contact;

    @Size(max = 300, message = "Длина комментария должна не превышать 300 символов!")
    private String comment;
    private PostDTO post;
    private UserDTO user;

    @JsonProperty(value = "creation_date", access = JsonProperty.Access.READ_ONLY)
    private String creationDate;
}
