package dv.calc.exceptions;

import dv.calc.rest.Result;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps most of possible exceptions and generates JSON response with a message.
 *
 * @author Denys Volchkov
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.ok(Result.error(exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
