package dv.calc.rest;

import dv.calc.filters.Cached;
import dv.calc.filters.CacheFilter;
import dv.calc.math.Calc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Calculator web service class.<p>
 * Handles requests of the following types (see corresponding endpoint methods below):<p>
 * {@code /calc/add/1/2/3} - sums up all args<p>
 * {@code /calc/subtract/4/2/1} - from the 1st arg subtracts 2nd, 3rd and so forth<p>
 * {@code /calc/multiply/100/100/100} - multiplies all args<p>
 * {@code /calc/divide/10/5/2} - divides 1st arg by 2nd, 3rd and so forth<p>
 *
 * Minimum 2 arguments required.<p>
 * Each argument must be a string parsable to double number (1, 2.5, -3.4 etc).<p>
 *
 * Results of calculation are cached - see {@link Cached}, {@link CacheFilter}
 *
 * @author Denys Volchkov
 */
@Path("/calc")
@Produces(MediaType.APPLICATION_JSON)
@Cached
public class CalcRestService {

    @GET
    @Path("/add/{args: .*}")
    public Result add(@PathParam("args") Arguments args) {
        double result = Calc.add(args.toDoubleArray());

        return Result.success(result);
    }

    @GET
    @Path("/subtract/{args: .*}")
    public Result subtract(@PathParam("args") Arguments args) {
        double result = Calc.subtract(args.toDoubleArray());

        return Result.success(result);
    }

    @GET
    @Path("/multiply/{args: .*}")
    public Result multiply(@PathParam("args") Arguments args) {
        double result = Calc.multiply(args.toDoubleArray());

        return Result.success(result);
    }

    @GET
    @Path("/divide/{args: .*}")
    public Result divide(@PathParam("args") Arguments args) {
        double result = Calc.divide(args.toDoubleArray());

        return Result.success(result);
    }
}
