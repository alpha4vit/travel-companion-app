package by.gurinovich.travelcompanionsearch.model;

import by.gurinovich.travelcompanionsearch.util.enums.PostType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

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

    @Column(name = "date_there")
    private Instant dateThere;

    @Column(name = "date_back")
    private Instant dateBack;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "post_type")
    private PostType type;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "creation_date")
    private Instant creationDate;

    @OneToMany(mappedBy = "post")
    private List<PostResponse> responses = new ArrayList<>();

}
