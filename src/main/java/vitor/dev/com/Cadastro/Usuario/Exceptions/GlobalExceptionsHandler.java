package vitor.dev.com.Cadastro.Usuario.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import vitor.dev.com.Cadastro.Usuario.Exceptions.CustomExceptions.RecursoDuplicadoException;
import vitor.dev.com.Cadastro.Usuario.Exceptions.CustomExceptions.RecursoNaoEncontradoException;
import vitor.dev.com.Cadastro.Usuario.Exceptions.ExceptionsDTO.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @Autowired
    private BuildExceptionResponse build;

    // RECURSO DUPLICADO
    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<Object> recursoDuplicadoHandler(RecursoDuplicadoException ex,
                                                          HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(build.ResponseConflict(HttpStatus.CONFLICT, ex.getMessage(), request.getServletPath()));
    }

    // ARGUMENTO INVÁLIDO OU FALTANDO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestInvalidExceptionDTO> recursoInvalidoHandler(MethodArgumentNotValidException ex,
                                                         HttpServletRequest request) {

        Map<String, String> fieldErrors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(build.ResponseBadRequestInvalid(HttpStatus.BAD_REQUEST, request.getServletPath(), fieldErrors));
    }

    // RECURSO NÃO ENCONTRADO
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<NotFoundExceptionDTO> recursoNaoEncontradoHandler(RecursoNaoEncontradoException ex,
                                                              HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(build.ResponseNotFound(HttpStatus.NOT_FOUND, ex.getMessage(), request.getServletPath()));
    }

    // PARÂMETRO INVÁLIDO
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BadRequestExceptionDTO> argumentoInvalidoHandler(MethodArgumentTypeMismatchException ex,
                                                          HttpServletRequest request) {
        String message = String
                .format("O parâmetro informado '%s' ,não é um id válido! O id deve ser do tipo UUID", ex.getValue());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(build.ResponseBadRequest(HttpStatus.BAD_REQUEST, message, request.getServletPath()));
    }

    // ROTA INEXISTENTE
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<NotFoundExceptionDTO> rotaNaoExistenteHandler(NoHandlerFoundException ex, HttpServletRequest request) {

        String message = String
                .format("Rota '%s' não existente, verifique a URL e tente novamente.", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(build.ResponseNotFound(HttpStatus.NOT_FOUND, message, request.getRequestURI()));
    }

    // ERRO GENÉRICO
    @ExceptionHandler(Exception.class)
    public ResponseEntity<InternalServerErrorExceptionDTO> erroInternoServidorHandler(Exception ex, HttpServletRequest request) {
        String message = "Erro interno do servidor";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(build.ResponseInternalServerError(HttpStatus.INTERNAL_SERVER_ERROR, message, request.getServletPath()));
    }
}