package dv.calc.exceptions;

import dv.calc.rest.Result;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps {@code NotFoundException} - to handle incorrect URLs and produce JSON response with a hint.
 *
 * @author Denys Volchkov
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    private static String MESSAGE = "Incorrect request. Use '/calc/add|subtract|multiply|divide/{a1}/../{aN}' format.";

    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.ok(Result.error(MESSAGE))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
