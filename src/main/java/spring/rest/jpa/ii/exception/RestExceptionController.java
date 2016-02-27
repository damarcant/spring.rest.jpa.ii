package spring.rest.jpa.ii.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus; 

/**
 * La anotación @ControllerAdvice aplicará todos los gestores 
 * de excepciones definidos en esta clase a todos los controladores 
 * anotados con @RequestMapping.
 * 
 * De esta forma, cuando algún método de nuestra API produzca el 
 * lanzamiento de la excepción ResourceNotFoundException,
 * se invocará automáticamente el método resourceNotFound, 
 * devolviendo el mensaje configurado y un código de error 404, 
 * gracias a la anotación @ResponseStatus
 * 
 * @author dmartin
 *
 */
@ControllerAdvice //anotación para aplicar a todos los controladores 
public class RestExceptionController {

	// se invoca si algún metodo hace throw de ResourceNotFoundException 
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND/*404*/)
	@ResponseBody
	public String resourceNotFound(ResourceNotFoundException e) {
		StringBuilder errorMsg = new StringBuilder();	
		long resourceId = e.getResourceId();
		errorMsg.append("El recurso [" + resourceId + "] no ha sido encontrado");
		
		return errorMsg.toString();
	}
	  
}
