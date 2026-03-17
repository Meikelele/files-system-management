package pl.mbak.filesmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Schema(description = "Response for global statistics")
public class GlobalStatisticsResponse {

    @Schema(description = "Total count of folders")
    private Long totalFolders;

    @Schema(description = "Total count of files")
    private Long totalFiles;

    @Schema(description = "Size of all files")
    private Long totalSizeInBytes;

    @Schema(description = "Average file size")
    private Double avgFileSize;

    @Schema(description = "The longest file name")
    private String largestFileName;

    @Schema(description = "The biggest file size")
    private Long largestFileSize;

}
