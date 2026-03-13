package pl.mbak.filesmanager.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mbak.filesmanager.dto.FolderResponse;
import pl.mbak.filesmanager.exception.ResourceNotFoundException;
import pl.mbak.filesmanager.model.Folder;
import pl.mbak.filesmanager.repository.FolderRepository;

import java.util.List;
import java.util.Optional;

@Service // ta klasa jest serwisem
@RequiredArgsConstructor // Lombok, wstrzykuje ropository do serwisu, nie musze w konstruktorze sam wstrzykiwac
public class FolderService {

    // wstrzykniecie zaleznosci - repozytorium
    private final FolderRepository folderRepository;

    // metoda do tworzenia folderu i przypisania do niego nadrzednego folderu
    public FolderResponse createFolder(String name, Long parentId) {
        Folder folder = new Folder();
        folder.setName(name);

        // todo czy name zawsze podane

        // jesli jest parent_id to user chce stworzyc podfolder
        if (parentId != null) {
            Folder parent = folderRepository.findById(parentId).orElseThrow(
                    () -> new ResourceNotFoundException("Parent folder does not exist!")
            );

            folder.setParent(parent);
        }
        return mapToResponse(folderRepository.save(folder));
    }


    // metoda do pobrania wszystkich folderów
    public List<FolderResponse> getAllFolders() {

        List<FolderResponse> allFolders = folderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();

        return allFolders;
    }

    // metoda do pobrania konkretnego folderu
    public FolderResponse getSpecificFolder(Long Id) {

        Folder folder = folderRepository.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException("Folder does not exist! -_-")
        );

        return mapToResponse(folder);
    }


    // Folder to jest encja w bazie danyc
    // nie chce aby encja szla na front i do klienta
    // wiec robie takie mapowanie na FolderResponse, taka lzejsze DTO ktore wysylam na front
    // to taki tłuamcz z encji z bazy danych na odpowiedz na front
    private FolderResponse mapToResponse(Folder folder) {


        List<FolderResponse> children = folder.getChildren()
                .stream()
                // dla kazdego dziecka mao() wywolaj metoda mapToResponse zeby bylo w formacie odsylki na front
                .map(this::mapToResponse)
                .toList();

        // jesli parent istnieje to wez jego id
        Long parentId = folder.getParent() != null
                ? folder.getParent().getId()
                : null;

        return new FolderResponse(
                folder.getId(),
                folder.getName(),
                parentId,
                children
        );
    }
}
