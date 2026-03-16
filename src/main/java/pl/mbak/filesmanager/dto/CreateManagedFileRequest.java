package pl.mbak.filesmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for creating a new file")
public class CreateManagedFileRequest {

    @Schema(description = "Unique file name", example = "invoice.txt")
    @NotBlank(message = "File name must not be blank!")
    // nazwa pliku
    private String filename;

    @Schema(description = "Size in bytes for file", example = "1024")
    @NotNull(message = "File size must no be blank!")
    @PositiveOrZero(message = "File size must be a greater than or equal to 0")
    // rozmiar pliku
    private Long sizeInBytes;

    @Schema(description = "Identifier for file's parent folder, that will containt the file", example = "1")
    @NotNull(message = "File's folder must not be blank!")
    // do jakeigo folderu plik nalezy
    private Long folderId;
}
