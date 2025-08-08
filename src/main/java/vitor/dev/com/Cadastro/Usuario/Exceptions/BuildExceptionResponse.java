package vitor.dev.com.Cadastro.Usuario.Exceptions;

import org.springframework.http.HttpStatus;
import vitor.dev.com.Cadastro.Usuario.Exceptions.ExceptionsDTO.*;

import java.time.LocalDateTime;
import java.util.Map;

public class BuildExceptionResponse {

    // MÉTODOS PARA CONSTRUIR A RESPOSTA DE CADA EXCEÇÃO
    public ConflictExceptionDTO ResponseConflict(HttpStatus status, String message, String path) {
        return new ConflictExceptionDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
    }

    public BadRequestInvalidExceptionDTO ResponseBadRequestInvalid(HttpStatus status, String path, Map<String,String> error) {
        return new BadRequestInvalidExceptionDTO(
                LocalDateTime.now(),
                status.value(),
                error,
                status.getReasonPhrase(),
                path
        );
    }

    public NotFoundExceptionDTO ResponseNotFound(HttpStatus status, String message, String path) {
        return new NotFoundExceptionDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
    }

    public InternalServerErrorExceptionDTO ResponseInternalServerError(HttpStatus status, String message, String path) {
        return new InternalServerErrorExceptionDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
    }

    public BadRequestExceptionDTO ResponseBadRequest(HttpStatus status, String message, String path) {
        return new BadRequestExceptionDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
    }
}