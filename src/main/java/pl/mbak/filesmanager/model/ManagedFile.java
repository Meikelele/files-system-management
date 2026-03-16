package pl.mbak.filesmanager.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // obiekt bazy danych, encja JPA
@Table(name = "files") // kolumna files w bazie danych
@Getter // lombok
@Setter // lombik
public class ManagedFile {

    @Id // klucz glowny
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nazwa pliku
    @Column(nullable = false, unique = true)
    private String filename;

    // rozmiar pliku
    @Column(nullable = false)
    private Long sizeInBytes;

    @ManyToOne // wiele plikow moze byc w folderze
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;




}
