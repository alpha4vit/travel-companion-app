package by.gurinovich.travelcompanionsearch.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Integer stars;
}
