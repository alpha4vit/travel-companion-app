package by.gurinovich.travelcompanionsearch.dto;

import by.gurinovich.travelcompanionsearch.util.enums.PostType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.Calendar;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostDTO {
    private UUID id;

    @Size(min = 5, max = 50, message = "Длина заголовка должна быть от 5 до 50 символов")
    private String title;

    @Size(max = 300, message = "Длина описания должна не превышать 300 символов")
    private String description;

    @Size(max = 50, message = "Длина описания должна не превышать 50 символов")
    private String fee;

    @JsonProperty(value = "date_there")
    private String dateThere;

    @JsonProperty(value = "date_back")
    private String dateBack;

    @JsonProperty(value = "post_type")
    private PostType postType;

    private UserDTO user;

    @JsonProperty(value = "creation_date", access = JsonProperty.Access.READ_ONLY)
    private String creationDate;

    @JsonProperty(value = "responses_count")
    private long responsesCount;

}
