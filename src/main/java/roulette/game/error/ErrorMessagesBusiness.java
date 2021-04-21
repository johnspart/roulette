package roulette.game.error;
import roulette.game.constant.ErrorCodes;

import java.util.HashMap;
import java.util.Map;

import static roulette.game.constant.ErrorCodes.*;
public class ErrorMessagesBusiness {
    private final Map<ErrorCodes, String> errorMessagesByCode = new HashMap<>();
    private ErrorMessagesBusiness() {
        super();
        errorMessagesByCode.put(CREATE_NEW_ROULETTE, "No es posible crear una nueva ruleta");
        errorMessagesByCode.put(OPEN_ROULETTE, "No es posible abrir la ruleta por el id dado, " +
                "verifique que la ruleta no se encuntre cerrada o ya este abierta");
        errorMessagesByCode.put(NOT_PRESENT_HEADER_USER_ID, "Para poder consumir las API’s necesita un encabezado llamado " +
                "userId con el id del usuario que realiza la petición");
        errorMessagesByCode.put(THE_BET_NUMBER_NOT_IS_VALID, "El numero para apostar debe de estar entre 0 y 36, " +
                "de color rojo los pares y negro los impares");
        errorMessagesByCode.put(THE_BET_VALUE_NOT_IS_VALID, "El valor a apstar debe ser mayor a 0 y maximo de 10.000");
        errorMessagesByCode.put(NOT_EXIST_ROULETTE_VALID_FOR_THE_BET, "No existe una ruleta con el id ingresado que " +
                "este en estado abierta para las apuestas");
        errorMessagesByCode.put(THE_ROULETTE_NOT_IS_OPEN, "La ruleta no esta abierta");
    }
    private static class SingletonHelper {
        private static final ErrorMessagesBusiness INSTANCE = new ErrorMessagesBusiness();
    }
    public static String getMessage(ErrorCodes errorCode) {
        return SingletonHelper.INSTANCE.errorMessagesByCode.getOrDefault(errorCode, "Error desconocido");
    }
}
