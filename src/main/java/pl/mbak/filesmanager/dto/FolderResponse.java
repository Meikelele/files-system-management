package pl.mbak.filesmanager.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mbak.filesmanager.model.Folder;

import java.util.List;

@Getter
@AllArgsConstructor
// to obiekt ktory backend wysyla na front do klienta
public class FolderResponse {
    private Long id;
    private String name;
    private Long parentId;
    private List<FolderResponse> children;
}
