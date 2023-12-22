package by.gurinovich.travelcompanionsearch.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {

    @NotNull(message = "Image must not be null")
    private MultipartFile file;
}
