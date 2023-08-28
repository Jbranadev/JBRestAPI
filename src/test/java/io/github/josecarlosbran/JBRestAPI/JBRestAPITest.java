package io.github.josecarlosbran.JBRestAPI;

import com.josebran.LogsJB.LogsJB;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.contentType;
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
    public void testJakartaClient() throws ValorUndefined {
        LogsJB.info("Inicio Test: ");
        Client client = ClientBuilder.newClient();
        /*WebTarget myResource = client.target("https://pokeapi.co/api/v2/pokemon/ditto");
        String response = myResource.request(MediaType.TEXT_PLAIN)
                .get(String.class);*/
        JBRestAPI clienteJB=JBRestAPI.builder().Url("https://pokeapi.co/api/v2/pokemon/ditto").MediaType(MediaType.TEXT_PLAIN_TYPE).newClient();
        int i=0;
        while(i<100){
            String response = clienteJB.request.get(String.class);
            LogsJB.info("Result is: " + response);
            i++;
        }
    }

    @Test(testName = "Test RestAPI Bran",
    dependsOnMethods = "testJakartaClient")
    public void testWorkAPI() throws ValorUndefined {
        LogsJB.info("Inicio Test: ");
        RestApi work=new RestApi();
        work.setContenttype(contentType.JSON);
        int i=0;
        while(i<100){
            String response = work.Get("https://pokeapi.co/api/v2/pokemon/ditto", "");;
            LogsJB.info("Result is: " + response);
            i++;
        }
    }

}
