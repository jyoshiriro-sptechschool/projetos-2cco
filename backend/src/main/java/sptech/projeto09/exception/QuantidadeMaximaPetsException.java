package sptech.projeto09.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
Exemplo de exceção personalizada com mensagem estática (fixa).
IMPORTANTE: A classe DEVE estender RuntimeException
 */
@ResponseStatus(
    code = HttpStatus.UNPROCESSABLE_ENTITY,
    reason = "A quantidade máxima de pets excedida"
)
public class QuantidadeMaximaPetsException
        extends RuntimeException {
}
