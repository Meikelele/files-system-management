package pl.mbak.filesmanager.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter // Lombok generuje gettery - mniej boilerplate
@Setter // Lombok - generuje settery - mniej boilerplate
// to obiekt ktory user wysyla na backend w celu utworzenia folderu
@Schema(description = "Request body to create a new folder") // adnotacja do dokumentacji API
public class CreateFolderRequest {

    @Schema(
            description = "Unique name of the folder",
            example = "Downloads"
    )
    @NotBlank(message = "Folder name must not be blank!") // pole ni emoze byc puste "" "  " null odrzuca
    private String name;

    @Schema(
            description = "Id of the parent folder. Null means root folder",
            example = "1",
            nullable = true
    )
    private Long parentId;
}
