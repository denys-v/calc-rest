package dv.calc.math;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Tests of Calc class business logic.
 *
 * @author Denys Volchkov
 */
@FixMethodOrder(MethodSorters.JVM)
public class CalcTest {

    private double[] args1 = {2.0, 4d, 6d};
    private double[] args2 = {2.0, 4d, 2d};

    @Test
    public void testAdd() {
        Assert.assertEquals(6d, Calc.add(2d, 4d), 0d);
        Assert.assertEquals(12d, Calc.add(args1), 0d);
    }

    @Test
    public void testSubtract() {
        Assert.assertEquals(5d, Calc.subtract(20d, 15d), 0d);
        Assert.assertEquals((-8d), Calc.subtract(args1), 0d);
    }

    @Test
    public void testMultiply() {
        Assert.assertEquals(27d, Calc.multiply(3d, 9d), 0d);
        Assert.assertEquals(48d, Calc.multiply(args1), 0d);
    }

    @Test
    public void testDivide() {
        Assert.assertEquals(4d, Calc.divide(100d, 25d), 0d);
        Assert.assertEquals(0.25, Calc.divide(args2), 0d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongArgsCount() {
        Calc.divide(4d);
    }
}
