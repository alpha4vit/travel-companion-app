package by.gurinovich.travelcompanionsearch.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private MultipartFile file;
}
