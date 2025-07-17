package vitor.dev.com.Cadastro.Usuario.Exceptions.CustomExceptions;

public class RecursoDuplicadoException extends RuntimeException {

    public RecursoDuplicadoException(String message) {
        super(message);
    }
}