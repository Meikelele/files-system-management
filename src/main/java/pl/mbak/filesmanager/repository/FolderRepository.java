package pl.mbak.filesmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mbak.filesmanager.model.Folder;

import java.util.Optional;

// extends JpaRepository<Folder, Long> - stworz mi repozytorium, czyli warstwe dostepu do bazy danych dla
// encji Folder gdzie ID ma typ Long
public interface FolderRepository extends JpaRepository<Folder, Long> {

    // Optional a nie Folder jako typ zwracany, bo szukany folder moze nie istnieć
    Optional<Folder> findByName(String name);
    Optional<Folder> findById(String name);

}
