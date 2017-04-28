package dv.calc.rest;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests of Arguments class.
 *
 * @author Denys Volchkov
 */
public class ArgumentsTest {

    @Test
    public void testParseArguments() {
        Arguments args = new Arguments("2/8/12");
        Assert.assertArrayEquals(new double[] {2d, 8d, 12d}, args.toDoubleArray(), 0);
    }

    @Test(expected = NumberFormatException.class)
    public void testArgumentsParseError() {
        new Arguments("2//12").toDoubleArray();
    }
}
