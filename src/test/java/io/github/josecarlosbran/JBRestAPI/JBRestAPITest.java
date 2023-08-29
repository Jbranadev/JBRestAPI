package io.github.josecarlosbran.JBRestAPI;

import com.google.gson.JsonObject;
import com.josebran.LogsJB.LogsJB;
import com.josebran.LogsJB.Numeracion.NivelLog;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.contentType;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.internal.util.collection.ImmutableMultivaluedMap;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({org.uncommons.reportng.HTMLReporter.class, org.uncommons.reportng.JUnitXMLReporter.class})
public class JBRestAPITest {

    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb3NlIENhcmxvcyBBbGZyZWRvIEJyYW4gQWd1aXJyZSIsImlzcyI6ImxvY2FsaG9zdDo4MDgwIiwiaWF0IjoxNjkzMjgwNTY3LCJleHAiOjE2OTMyODE0Njd9.C51Mux0vV7oQTW5GB63Pe7XiytSmqUF9SRGpsGT9xl1XroRJJ-4dw2dI55Mp8gP88BPGw2PJMCLa45vWew0GoA";

    public JBRestAPITest() {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
    }

    @Test(testName = "Test Jakarta Client")
    public void testJakartaClient() throws ValorUndefined {
        LogsJB.info("Inicio Test: ");
        JBRestAPI clienteJB=JBRestAPI.builder().Url("http://localhost:8080/WebServicesPrueba/hello").MediaType(MediaType.APPLICATION_JSON_TYPE).newClient();
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
            String response = work.Get("http://localhost:8080/WebServicesPrueba/hello", "");;
            LogsJB.info("Result is: " + response);
            i++;
        }
    }

    @Test(testName = "Test Jakarta Client Post",
            dependsOnMethods = "testWorkAPI")
    public void testJakartaClientPost() throws ValorUndefined {
        LogsJB.info("Inicio Test: ");
        Log log=new Log();
        log.setNivelLog(NivelLog.TRACE.name());
        log.setMetodo("testJakartaClientPost");
        log.setFecha("2023/08/24 15:43:55 422");
        log.setClase("io.github.josecarlosbran.LogsJB.LogsJBTest");
        MultivaluedMap<String, Object> myHeaders= new MultivaluedHashMap<>();
        myHeaders.add("Authorization", "Bearer "+this.token);
        JBRestAPI clienteJB=JBRestAPI.builder().Url("http://localhost:8080/WebServicesPrueba/Logs").
                MediaType(MediaType.APPLICATION_JSON_TYPE).
                Headers(myHeaders).newClient();
        int i=0;
        while(i<100){
            try{
                i++;
                log.setTexto("Comentario # "+i);
                Response respuesta= clienteJB.post(Entity.entity(log, MediaType.APPLICATION_JSON_TYPE));
                LogsJB.info("Result is: " + respuesta);
            }catch (Exception e){
                com.josebran.LogsJB.LogsJB.fatal("Excepción capturada en el metodo encargado de crear " +
                        "la tabla de Log en BD's");
                com.josebran.LogsJB.LogsJB.fatal("Tipo de Excepción : " + e.getClass());
                com.josebran.LogsJB.LogsJB.fatal("Causa de la Excepción : " + e.getCause());
                com.josebran.LogsJB.LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
                com.josebran.LogsJB.LogsJB.fatal("Trace de la Excepción : " + ExceptionUtils.getStackTrace(e));
            }
        }
    }

    @Test(testName = "Test RestAPI Bran Post",
            dependsOnMethods = "testJakartaClientPost")
    public void testWorkAPIPost() throws ValorUndefined {
        LogsJB.info("Inicio Test: ");
        Log log=new Log();
        log.setNivelLog(NivelLog.TRACE.name());
        log.setMetodo("Test RestAPI Bran Post");
        log.setFecha("2023/08/24 15:43:55 422");
        log.setClase("io.github.josecarlosbran.LogsJB.LogsJBTest");
        JsonObject json=new JsonObject();
        json.addProperty("nivelLog", log.getNivelLog());

        json.addProperty("clase", log.getClase());
        json.addProperty("metodo", log.getMetodo());
        json.addProperty("fecha", log.getFecha());
        RestApi work=new RestApi();
        work.setContenttype(contentType.JSON);
        int i=0;
        while(i<100){
            log.setTexto("Comentario # "+i);
            json.addProperty("texto", log.getTexto());
            String response = work.Post("http://localhost:8080/WebServicesPrueba/Logs", json.toString(), this.token);;
            LogsJB.info("Result is: " + response);
            i++;
        }
    }


}
