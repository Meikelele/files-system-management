package pl.mbak.filesmanager.exception;





// zamiast wszedzei rzucac wyjatek RuntimeException to tworze konkretniejszy wyjatek
// w tym przyadka ze dżadany zasób nie istnieje !
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
