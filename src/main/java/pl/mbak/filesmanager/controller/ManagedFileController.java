package pl.mbak.filesmanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mbak.filesmanager.dto.CreateManagedFileRequest;
import pl.mbak.filesmanager.dto.ManagedFileResponse;
import pl.mbak.filesmanager.service.ManagedFileService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class ManagedFileController {
    private final ManagedFileService managedFileService;

    @PostMapping
    public ManagedFileResponse createFile(@Valid @RequestBody CreateManagedFileRequest request) {
        return managedFileService.createFile(
                request.getFilename(),
                request.getSizeInBytes(),
                request.getFolderId()
        );
    }

    @GetMapping
    public List<ManagedFileResponse> getAllFiles() {
        return managedFileService.getAllFiles();
    }

    @GetMapping("/{id}")
    public ManagedFileResponse getFileById(@PathVariable Long id) {
        return managedFileService.getFileById(id);
    }

    @GetMapping("/by-folder/{folderId}")
    public List<ManagedFileResponse> getFilesByFolderId(@PathVariable Long folderId) {
        return managedFileService.getFilesByFolderId(folderId);
    }


}
