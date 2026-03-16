package pl.mbak.filesmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mbak.filesmanager.model.ManagedFile;

import java.util.List;
import java.util.Optional;


// stworz repozytorium (wartwa kontaktu z baze danych) dla ManagedFile, z id typu long
public interface ManagedFileRepository extends JpaRepository<ManagedFile, Long> {


    Optional<ManagedFile> findByFilename(String filename);

    List<ManagedFile> findByFolderId(Long folderId);
}
