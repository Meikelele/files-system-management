package pl.mbak.filesmanager.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.mbak.filesmanager.dto.ManagedFileResponse;
import pl.mbak.filesmanager.exception.ResourceNotFoundException;
import pl.mbak.filesmanager.model.Folder;
import pl.mbak.filesmanager.model.ManagedFile;
import pl.mbak.filesmanager.repository.FolderRepository;
import pl.mbak.filesmanager.repository.ManagedFileRepository;

import java.util.List;

@Service // ta klasa to serwis
@RequiredArgsConstructor // lombok wstrzykuje zaleznsci
public class ManagedFileService {

    private final ManagedFileRepository managedFileRepository;
    private final FolderRepository folderRepository;

    public ManagedFileResponse createFile(String filename, Long sizeInBytes, Long folderId) {
        // pobieram folder po id, dojakiego plik manalezc
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new ResourceNotFoundException("Folder not found! -_-"));

        // tworze nowy ManagedFile
        ManagedFile file = new ManagedFile();
        file.setFilename(filename);
        file.setFolder(folder);
        file.setSizeInBytes(sizeInBytes);

        // zapisuje do bazy
        ManagedFile savedFile = managedFileRepository.save(file);

        // mapuje dto na odpowiedz prostsza API
        return mapToResponse(savedFile);
    }

    public List<ManagedFileResponse> getAllFiles() {
        return managedFileRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    public ManagedFileResponse getFileById(Long id) {
        ManagedFile file = managedFileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File does not exist! -_-"));

        return mapToResponse(file);
    }


    // getFilesByFolderId
    public List<ManagedFileResponse> getFilesByFolderId(Long folderId) {

        return managedFileRepository.findByFolderId(folderId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // mapToResponse
    private ManagedFileResponse mapToResponse(ManagedFile file) {
        return new ManagedFileResponse(
                file.getId(),
                file.getFilename(),
                file.getSizeInBytes(),
                file.getFolder().getId()
        );
    }



}
