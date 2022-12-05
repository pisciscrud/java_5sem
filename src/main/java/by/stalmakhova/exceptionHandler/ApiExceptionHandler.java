package by.stalmakhova.exceptionHandler;


import by.stalmakhova.exception.RecordAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

//    @ExceptionHandler(value = {NonExistedUserException.class})
//    public ResponseEntity<Object> handleNonExistedUserException(NonExistedUserException e) {
//          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//    }
    @ExceptionHandler(value={RecordAlreadyExist.class})
        public ResponseEntity<Object> handleRecordAlreadyExist(RecordAlreadyExist e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\" : " + "\"" + e.getMessage() +"\"" + "}");
    }
}



