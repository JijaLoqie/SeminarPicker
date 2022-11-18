package SeminarService.Controllers;

public class SeminarControllerException extends Exception {
    SeminarControllerException(String errorString) {
        super(errorString);
    }
}
