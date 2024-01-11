package by.gurinovich.travelcompanionsearch.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
    private Long id;

    private String text;

    private String longitude;

    private String latitude;
}
