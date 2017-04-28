package dv.calc.rest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Client-side tests of CalcRestService endpoints.
 *
 * @author Denys Volchkov
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.JVM)
public class CalcRestServiceTest {

    @Deployment(testable = false)
    public static Archive createDeployment() throws Exception {
        return ShrinkWrap.create(WebArchive.class).addPackages(true, "dv.upwork.calc");
    }

    @ArquillianResource
    private URL base;

    private WebTarget target;


    @Before
    public void setUp() throws MalformedURLException {
        Client client = ClientBuilder.newClient();
        target = client.target(URI.create(new URL(base, "calc").toExternalForm()));
    }

    @Test
    public void testAdd() {
        Result response = target.path("add/1/2").request().get(Result.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(Result.Status.SUCCESS, response.getStatus());
        Assert.assertEquals(3.0, response.getValue(), 0);
    }

    @Test
    public void testSubtract() {
        Result response = target.path("subtract/1/2/-10").request().get(Result.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(Result.Status.SUCCESS, response.getStatus());
        Assert.assertEquals(9.0, response.getValue(), 0);
    }

    @Test
    public void testMultiply() {
        Result response = target.path("multiply/2/2/7").request().get(Result.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(Result.Status.SUCCESS, response.getStatus());
        Assert.assertEquals(28.0, response.getValue(), 0);
    }

    @Test
    public void testDivide() {
        Result response = target.path("divide/27/3/3").request().get(Result.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(Result.Status.SUCCESS, response.getStatus());
        Assert.assertEquals(3.0, response.getValue(), 0);
    }

    @Test
    public void testWrongUrl() {
        Result response = target.path("div/27//3").request().get(Result.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(Result.Status.ERROR, response.getStatus());
    }

    @Test
    public void testWrongArgument() {
        Result response = target.path("divide/27//3").request().get(Result.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(Result.Status.ERROR, response.getStatus());
    }

    @Test
    public void testLittleArgumentsCount() {
        Result response = target.path("divide/27").request().get(Result.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(Result.Status.ERROR, response.getStatus());
    }
}
