package io.github.josecarlosbran.JBRestAPI;

import com.google.gson.JsonObject;
import com.josebran.LogsJB.LogsJB;
import com.josebran.LogsJB.Numeracion.NivelLog;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.contentType;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
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
        JBRestAPI clienteJB=JBRestAPI.builder().Url("http://localhost:8081/WebServicesPrueba/hello").MediaType(MediaType.APPLICATION_JSON_TYPE).newClient();
        int i=0;
        while(i<1000){
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
        while(i<1000){
            String response = work.Get("http://localhost:8081/WebServicesPrueba/hello", "");;
            LogsJB.info("Result is: " + response);
            i++;
        }
    }

    @Test(testName = "Test Jakarta Client Post")
    public void testJakartaClientPost() throws ValorUndefined {
        LogsJB.info("Inicio Test: ");
        Log log=new Log();
        log.setNivelLog(NivelLog.TRACE.name());
        log.setMetodo("testJakartaClientPost");
        log.setFecha("2023/08/24 15:43:55 422");
        log.setClase("io.github.josecarlosbran.LogsJB.LogsJBTest");
        JBRestAPI clienteJB=JBRestAPI.builder().Url("http://localhost:8081/WebServicesPrueba/Logs").MediaType(MediaType.APPLICATION_JSON_TYPE).newClient();
        int i=0;
        while(i<10000){
            try{
                log.setTexto("Comentario # "+i);
                Response respuesta= clienteJB.post(Entity.entity(log, MediaType.APPLICATION_JSON_TYPE));
                LogsJB.info("Result is: " + respuesta);
                i++;
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
        while(i<10000){
            log.setTexto("Comentario # "+i);
            json.addProperty("texto", log.getTexto());
            String response = work.Post("http://localhost:8081/WebServicesPrueba/Logs", json.toString(), "");;
            LogsJB.info("Result is: " + response);
            i++;
        }
    }


}
