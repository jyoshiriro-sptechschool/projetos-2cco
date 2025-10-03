package sptech.projeto09.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
Exemplo de exceção personalizada com mensagem dinâmica
IMPORTANTE: A classe DEVE estender RuntimeException
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PetProtegidoException extends RuntimeException {

    private final Integer idProtegido;

    public PetProtegidoException(Integer idProtegido) {
        this.idProtegido = idProtegido;
    }

    @Override
    public String getMessage() {
        /*
Para mensagens em vários idiomas deveríamos usar a
classe MessageSource do Spring
         */
        return "O pet de ID %d é protegido e não pode ser excluído"
                .formatted(idProtegido);
    }
}
