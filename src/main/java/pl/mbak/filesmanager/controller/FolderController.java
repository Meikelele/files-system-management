package pl.mbak.filesmanager.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.mbak.filesmanager.model.Folder;
import pl.mbak.filesmanager.service.FolderService;

import java.util.List;

@RestController // klasa zajmuje sie zadaniami HTTP i zwraca dane jako JSON
@RequestMapping("/folders") // controller odbiera zadania dla takiego prefiksu URL
@RequiredArgsConstructor // wstrzykuje dependency bez korzystania z konstruktora
public class FolderController {

    private final FolderService folderService;


    @PostMapping // ta metoda obsluguje request HTTP na POST '/folders'
    @ResponseStatus(HttpStatus.CREATED) // zwraca kod HTTP jako 201 CREATED a nie 200 OK, bardziej szczegolowe
    // @RequestParam pobiera name z reuqestu np. POST /folders?name=Documents
    public Folder createFolder(@RequestParam String name, @RequestParam(required = false) Long parentId) {
        return folderService.createFolder(name, parentId);
    }


    @GetMapping
    public List<Folder> getAllFolders() {
        return folderService.getAllFolders();
    }

    @GetMapping("/{id}")
    // @PathVariable pobiera id ze sciezki np. GET /folders/5
    // wyciaga id z URL
    public Folder getFolderById(@PathVariable Long id) {
        return folderService.getSpecificFolder(id).orElseThrow(
                () -> new RuntimeException("Folder does not found!")
        );
    }



}
