package by.gurinovich.travelcompanionsearch.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
