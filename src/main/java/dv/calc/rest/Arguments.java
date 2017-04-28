package dv.calc.rest;

/**
 * Class for extracting calculation arguments from request path string ("2/4/7" etc)
 * and provide them as {@code double[]} array.<p>
 * Created by JAX-RS runtime and passed as {@code @PathParam} to {@code CalcRestService} endpoint methods.<p>
 * Supports arbitrary number of arguments ({a1}/{a2}/../{aN}).
 *
 * @author Denys Volchkov
 */
public class Arguments {

    private String path;

    /**
     * Constructor used by JAX-RS runtime.
     *
     * @param path request path part with arguments ("4/26", "20/10/5" etc)
     */
    public Arguments(String path) {
        this.path = path;
    }

    /**
     * Method to get arguments extracted from request path string.
     *
     * @return double[] array.
     * @throws NumberFormatException if any argument can't be parsed as double.
     */
    public double[] toDoubleArray() {
        String[] valuesStr = path.split("/");
        double[] valuesDouble = new double[valuesStr.length];

        for (int i = 0; i < valuesStr.length; i++) {
            try {
                valuesDouble[i] = Double.parseDouble(valuesStr[i]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                        String.format("Error parsing argument %d ('%s'): %s", i + 1, valuesStr[i], e.getMessage()));
            }
        }

        return valuesDouble;
    }
}
