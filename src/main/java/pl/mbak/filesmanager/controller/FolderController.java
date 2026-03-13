package pl.mbak.filesmanager.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.mbak.filesmanager.dto.CreateFolderRequest;
import pl.mbak.filesmanager.dto.FolderResponse;
import pl.mbak.filesmanager.exception.ApiError;
import pl.mbak.filesmanager.exception.ResourceNotFoundException;
import pl.mbak.filesmanager.model.Folder;
import pl.mbak.filesmanager.service.FolderService;

import java.util.List;

@RestController // klasa zajmuje sie zadaniami HTTP i zwraca dane jako JSON
@RequestMapping("/folders") // controller odbiera zadania dla takiego prefiksu URL
@RequiredArgsConstructor // wstrzykuje dependency bez korzystania z konstruktora
@Tag(
        name = "Folders",
        description = "Operations for mapping folders and folder hierarchy"
) // @Tag sluzy do grupowania kontrolerow
// @Tag grupuje kontrolery
// @operation opis dla konkretnego endpointu
public class FolderController {

    private final FolderService folderService;


    @PostMapping // ta metoda obsluguje request HTTP na POST '/folders'
    @ResponseStatus(HttpStatus.CREATED) // zwraca kod HTTP jako 201 CREATED a nie 200 OK, bardziej szczegolowe
    // @RequestParam pobiera name z reuqestu np. POST /folders?name=Documents
    // @Valid - uruchom walidacje obiektu przed wejscie do metody
    // @RequestBody - wez JSONz body requestu i zamien na obiekt Javaco
    @Operation(
            summary = "Create a new folder",
            description = "Creates a new folder. If parentdId is provided, the folder is a child of the existing folder."
    ) // @Operation to opis dla konkretnego endpointu
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Folder created successfully",
                    content = @Content(schema = @Schema(implementation = FolderResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request body or validation problem",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Parent folder not found -_-",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    public FolderResponse createFolder(
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Payload used to create a folder",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CreateFolderRequest.class))
                    )
            CreateFolderRequest request
    ) {

        return folderService.createFolder(request.getName(), request.getParentId());
    }


    @GetMapping
    @Operation(
            summary = "Get all folders",
            description = "Returns all folders with their nested child folder structure."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Folders retrieved successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FolderResponse.class)))
            )
    })
    public List<FolderResponse> getAllFolders() {
        return folderService.getAllFolders();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get folder by id",
            description = "Returns a single folder with its nested child folder hierarchy."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Folder retrieved successfully",
                    content = @Content(schema = @Schema(implementation = FolderResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Folder not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    public FolderResponse getFolderById(
            @PathVariable Long id
    ) {
        return folderService.getSpecificFolder(id);
    }



}
