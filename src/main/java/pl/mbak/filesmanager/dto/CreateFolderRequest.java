package pl.mbak.filesmanager.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter // Lombok generuje gettery - mniej boilerplate
@Setter // Lombok - generuje settery - mniej boilerplate
public class CreateFolderRequest {

    @NotBlank(message = "Folder name must not be blank!") // pole ni emoze byc puste "" "  " null odrzuca
    private String name;

    private Long parentId;
}
