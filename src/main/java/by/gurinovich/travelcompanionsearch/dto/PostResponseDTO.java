package by.gurinovich.travelcompanionsearch.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDTO {
    private Long id;
    private String comment;
    private PostDTO post;
    private UserDTO user;
}
