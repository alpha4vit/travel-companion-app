package by.gurinovich.travelcompanionsearch.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDTO {
    private Long id;

    private AddressDTO departure;

    private AddressDTO destination;
}
