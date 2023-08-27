package io.github.josecarlosbran.JBRestAPI;

import com.josebran.LogsJB.LogsJB;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({org.uncommons.reportng.HTMLReporter.class, org.uncommons.reportng.JUnitXMLReporter.class})
public class JBRestAPITest {
    public JBRestAPITest() {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
    }

    @Test(testName = "Test Jakarta Client")
    public void testJakartaClient(){
        Client client = ClientBuilder.newClient();
        WebTarget myResource = client.target("https://pokeapi.co/api/v2/pokemon/ditto");
        String response = myResource.request(MediaType.TEXT_PLAIN)
                .get(String.class);
        LogsJB.info("Result is: "+response);

    }

}
