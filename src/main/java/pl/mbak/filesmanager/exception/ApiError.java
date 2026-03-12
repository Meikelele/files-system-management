package pl.mbak.filesmanager.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

    private int statusCode;
    private String error;
    private String message;
    private String path;
}
