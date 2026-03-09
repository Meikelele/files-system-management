package pl.mbak.filesmanager.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public Folder createFolder(String name, Long parentId) {
        Folder folder = new Folder();
        folder.setName(name);

        // todo czy name zawsze podane

        // jesli jest parent_id to user chce stworzyc podfolder
        if (parentId != null) {
            Folder parent = folderRepository.findById(parentId).orElseThrow(
                    () -> new RuntimeException("Parent folder does not exist!")
            );

            folder.setParent(parent);
        }
        return folderRepository.save(folder);
    }


    // metoda do pobrania wszystkich folderów
    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    // metoda do pobrania konkretnego folderu
    public Optional<Folder> getSpecificFolder(Long Id) {
        return folderRepository.findById(Id);
    }
}
