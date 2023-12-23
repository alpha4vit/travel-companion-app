package by.gurinovich.travelcompanionsearch.dto;

import by.gurinovich.travelcompanionsearch.util.enums.PostType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.Calendar;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private UUID id;
    private String title;
    private String description;
    private String fee;

    @JsonProperty(value = "date_there")
    private String dateThere;

    @JsonProperty(value = "date_back")
    private String dateBack;

    @JsonProperty(value = "post_type")
    private PostType postType;

    @JsonProperty(value = "transport_id")
    private Long transportId;

    private UserDTO user;
}
