package by.gurinovich.travelcompanionsearch.model;

import by.gurinovich.travelcompanionsearch.util.enums.PostType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    private UUID id;

    private String title;

    private String description;

    private String fee;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "post_type")
    private PostType type;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "transport_id", referencedColumnName = "id")
    private Transport transport;


}
