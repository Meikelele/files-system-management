package pl.mbak.filesmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mbak.filesmanager.model.Folder;

@Getter
@AllArgsConstructor
@Schema(description = "Response for file details")
// prostsze API do zwracanie dla frontu niz cala encja
public class ManagedFileResponse {

    @Schema(description = "File identifier", example = "1")
    private Long id;

    @Schema(description = "Unique file name", example = "invoice.pdf")
    private String filename;

    @Schema(description = "File size in bytes", example = "1024")
    private Long sizeInBytes;

    @Schema(description = "Identifier of the folder containing the file", example = "1")
    private Long folderId;
}
