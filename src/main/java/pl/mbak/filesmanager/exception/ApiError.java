package pl.mbak.filesmanager.exception;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(
        description = "Standard API error response"
)
public class ApiError {

    @Schema(description = "HTTP status code", example = "400")
    private int statusCode;

    @Schema(description = "HTTP error name", example = "Bad Request")
    private String error;

    @Schema(description = "Custom detailed error message", example = "name: Folder must not be blank! -_-")
    private String message;

    @Schema(description = "Request path that caused the error", example = "/folder/1919")
    private String path;
}
