package pl.mbak.filesmanager.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity // to jest encja czyli cos takiego chcemy miec w bazie danych, obiekt z bazy danych
@Getter // zrob gettery - Lombok
@Setter // zrob settery - Lombok
@Table(name = "folders") // ten model encji ma byc w bazie danych w tabeli 'folders'
public class Folder {

    @Id // to jet identyfikator w bazie danych
    @GeneratedValue(strategy = GenerationType.IDENTITY) // baza danych bedzie generowac sb id sama,
    private Long id;

    @Column(nullable = false, unique = true) // nazwa nie moze byc pusta i musi byc unikatowa
    // nazwa dla folderu
    private String name;

    // rodzic folderu, wskazywanie na inny folder self-reference
    @ManyToOne // wiele folderów moze miec jednego rodzica
    @JoinColumn(name = "parent_id") // w tabeli 'folders' ma byc kolumna 'parent_id' ktora wskazuje rodzica
    @JsonBackReference // nie serializuj tego, bo powstanie petla w json
    private Folder parent;

    @OneToMany(mappedBy = "parent") // jeden folder moze miec wiele podfolderow, a wlascicielem tej relacji jest pole 'parent'
    @JsonManagedReference // tę strone traktuj jako glowna przy serializacji
    // to lista dzieci dla danego folderu
    private List<Folder> children = new ArrayList<>();

    @OneToMany(mappedBy = "folder")
    // 'mappedBy' bo wlasciciel relacji jest w encji File
    private List<ManagedFile> files = new ArrayList<>();
}
