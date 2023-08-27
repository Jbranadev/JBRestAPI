package io.github.josecarlosbran.JBRestAPI;

import io.github.josecarlosbran.JBRestAPI.Enumeraciones.Metodo;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

class Propiedades {
    String Uri;
    List<ParametroOfRoute> parametrosDeRuta;
    MediaType mediaType;
    Metodo metodo;
    Class TypeResult;

    Boolean TypeResultIsColeccion;



}
