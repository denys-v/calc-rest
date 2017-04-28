package dv.calc.math;

import java.util.Arrays;

/**
 * Simple calculator component.
 * Operates with {@code double} type arguments.
 *
 * @author Denys Volchkov
 */
public class Calc {
    
    private static int MIN_ARG_COUNT = 2;
    private static int MAX_ARG_COUNT = Integer.MAX_VALUE;


    /**
     * Sums up input arguments.
     *
     * @param args
     * @return
     */
    public static double add(double... args) {
        assertArgsCount(args);

        return Arrays.stream(args).sum();
    }

    /**
     * Subtracts 2nd (3rd and so forth, if any) argument from the 1st one.
     *
     * @param args
     * @return
     */
    public static double subtract(double... args) {
        assertArgsCount(args);

        return args[0] - Arrays.stream(args).skip(1).sum();
    }

    /**
     * Multiplication of input arguments.
     *
     * @param args
     * @return
     */
    public static double multiply(double... args) {
        assertArgsCount(args);

        return Arrays.stream(args).reduce(1.0, (a, b) -> a * b);
    }

    /**
     * Divides 1st argument by 2nd (3rd and so forth, if any).
     *
     * @param args
     * @return
     */
    public static double divide(double... args) {
        assertArgsCount(args);

        return args[0] / Arrays.stream(args).skip(1).reduce(1.0, (a, b) -> a * b);
    }

    /**
     * Checks the number of input arguments to be in bounds.
     * 
     * @param args
     */
    private static void assertArgsCount(double[] args) {
        if (args == null || args.length < MIN_ARG_COUNT) {
            throw new IllegalArgumentException(
                    String.format("At least %d arguments must be provided.", MIN_ARG_COUNT));
        }
        if (args.length > MAX_ARG_COUNT) {
            throw new IllegalArgumentException(
                    String.format("Not more than %d arguments must be provided.", MAX_ARG_COUNT));
        }
    }
}
