package pl.mbak.filesmanager.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // ta klasa ma przechwycac wyajtki z kontrolerow REST
// nie trzeb pisac try catch tylko tutaj wszystkie wyjatki trafiaja
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    // jesli gdzies poleci blad typu ResourceNotFoundException to obsluz to w tym handlerze
    public ResponseEntity<ApiError> handlerResourceNotFound(
            ResourceNotFoundException exception,
            HttpServletRequest request // to daje dostep do danych requestu czyli sciezki '/godlers/99'
    ) {
        ApiError error = new ApiError(
                // zwraca kod bledu
                HttpStatus.NOT_FOUND.value(),
                // zwraca jakby znaczenie kodu bledu
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                // zwraca moja wiadomosc bledu custmowa
                exception.getMessage(),
                // zwraca sciezke pod ktora poszedl request
                request.getRequestURI()
        );

        // zwracam status NOT_FOUND czyli 404 i w ciele ApiError
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    // ta klasa sie wywoluje kiedy w kontrolerze jest jakies @Valid dla body,
    // i jesli to valid wylapie blad to ta metoda sie wykonuje
    public ResponseEntity<ApiError> handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request // to pole zawiera inforamcje o requescie, scieke, uri, parametry, naglowki
    ) {

        // getBindingResult() - spring trzyma tutaj wynik proby walidacji requestu
        // spring probowal dane z requestu przypisac do DTO i cos poszlo nie tak i tutaj siedzi co poszlo nie tak
        //
        // getFieldErrors() - pobierz liste bledow, wiem dla jakich pol wystapil problem np: {"name": "  "}
        //
        // stream() - zamiana listy bledow na strumien Java Stream API, bo 'latwo' sie pracuje na niej xd
        //
        // findFirst() - pobiera pierwszy blad z listy (nie wszystkie)
        //
        // map() - dla wygody zwracania bledu jest ladna odp, czyli jak np. pole name mialo problem z walidacja to
        // zwroci w postaci name: Folder name must not be blank
        // to sie zapisuej do zmiennej message, ktora potem zwracam w ApiError
        //
        // orElse() - to gdyby lista bledow byla pusta, teoretycznie nie powinno sie wywolac ale dla bezpieczenstwa
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .orElse("Validation failed");

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }



    @ExceptionHandler(Exception.class)
    // dowolny nieobsluzony blad
    public ResponseEntity<ApiError> handlerGenericException(
            Exception exception,
            HttpServletRequest request
    ) {
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Something unexpected occured! -_-",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
