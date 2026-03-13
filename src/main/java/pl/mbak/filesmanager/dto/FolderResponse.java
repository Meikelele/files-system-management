package pl.mbak.filesmanager.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mbak.filesmanager.model.Folder;

import java.util.List;

@Getter
@AllArgsConstructor
@Schema(
        description = "Response representing folder details and nested child folders"
)
// to obiekt ktory backend wysyla na front do klienta
public class FolderResponse {
    @Schema(description = "Folder identifier", example = "1")
    private Long id;

    @Schema(description = "Folder name", example = "Downloads")
    private String name;

    @Schema(description = "Parent folder identifier. Null means Root folder", example = "null", nullable = true)
    private Long parentId;

    @Schema(description = "List of child folders")
    private List<FolderResponse> children;
}
