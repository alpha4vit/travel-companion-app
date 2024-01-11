package by.gurinovich.travelcompanionsearch.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "routes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "departure_id", referencedColumnName = "id")
    private Address departure;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    private Address destination;


}
