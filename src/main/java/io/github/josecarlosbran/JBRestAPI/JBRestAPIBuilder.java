package io.github.josecarlosbran.JBRestAPI;

import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.List;

import static io.github.josecarlosbran.JBRestAPI.Methods.stringIsNullOrEmpty;

class JBRestAPIBuilder {

    @Getter(AccessLevel.PROTECTED)
    private String url = null;

    @Getter(AccessLevel.PROTECTED)
    private List<ParametroOfRoute> parametrosDeRuta = null;

    @Getter(AccessLevel.PROTECTED)
    private List<ParametroOfRoute> queryParams = null;

    @Getter(AccessLevel.PROTECTED)
    private MediaType mediaType = null;

    @Getter(AccessLevel.PROTECTED)
    private Boolean typeResultIsColeccion = false;

    @Getter(AccessLevel.PROTECTED)
    private MultivaluedMap<String, Object> headers = null;

    @Getter(AccessLevel.PROTECTED)
    private List<Cookie> cookies = null;

    @Getter(AccessLevel.PROTECTED)
    private Class filter = null;

    JBRestAPIBuilder(){

    }

    /**
     * Setea la filtroRegistro que filtrara las solicitudes al RestAPI
     *
     * @param filtroRegistro a class of a custom JAX-RS component
     *                       Register a class of a custom JAX-RS component (such as an extension provider or a feature meta-provider)
     *                       to be instantiated and used in the scope of this configurable context. Implementations SHOULD warn about and ignore
     *                       registrations that do not conform to the requirements of supported JAX-RS component types in the given configurable context.
     *                       Any subsequent registration attempts for a component type, for which a class or instance-based registration already exists
     *                       in the system MUST be rejected by the JAX-RS implementation and a warning SHOULD be raised to inform the user about
     *                       the rejected registration. The registered JAX-RS component class is registered as a contract provider of all the
     *                       recognized JAX-RS or implementation-specific extension contracts including meta-provider contracts, such as Feature
     *                       or jakarta.ws.rs.container.DynamicFeature.
     *                       As opposed to component instances registered via register(Object) method, the lifecycle of components registered using
     *                       this class-based register(...) method is fully managed by the JAX-RS implementation or any underlying IoC container
     *                       supported by the implementation.
     */
    public JBRestAPIBuilder Filter(Class filtroRegistro) throws ValorUndefined {
        this.filter = filtroRegistro;
        return this;
    }

    /**
     * Setea la Url a la cual apuntara el RestAPI
     *
     * @param url Url a la cual apuntara el RestAPI
     * @throws ValorUndefined Lanza esta excepción si la url proporcionada es null o esta vacía
     */
    public JBRestAPIBuilder Url(String url) throws ValorUndefined {
        if (stringIsNullOrEmpty(url)) {
            throw new ValorUndefined("La url Proporcionada no puede ser nula");
        }
        this.url = url;
        return this;
    }

    /**
     * Parametros de ruta
     *
     * @param parametrosDeRuta Lista de parametros de ruta
     */
    public JBRestAPIBuilder ParametrosDeRuta(List<ParametroOfRoute> parametrosDeRuta) {
        this.parametrosDeRuta = parametrosDeRuta;
        return this;
    }

    /**
     * Parametros de ruta
     *
     * @param queryParams Lista de parametros de ruta
     */
    public JBRestAPIBuilder QueryParams(List<ParametroOfRoute> queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    /**
     * Tipo de mediaType que define el media type con que se negociaran los datos en el servidor
     *
     * @param mediaType mediaType para negociar el contenido con el servidor
     */
    public JBRestAPIBuilder MediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    /**
     * Indica si el tipo de Resultado a Obtener es una Colección
     *
     * @param typeResultIsColeccion True si el tipo de retorno es una colección, False si el tipo de retorno no es una
     *                              colección, default is False
     */
    public JBRestAPIBuilder TypeResultIsColeccion(Boolean typeResultIsColeccion) {
        this.typeResultIsColeccion = typeResultIsColeccion;
        return this;
    }

    /**
     * MultiValueMap que representa los headers de nuestra aplicación
     *
     * @param headers headers a enviar en nuestra solicitud al servidor
     */
    public JBRestAPIBuilder Headers(MultivaluedMap<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    /**
     * Lista de Cookie's a enviar al servidor
     *
     * @param cookies Cookie's a enviar al servidor
     */
    public JBRestAPIBuilder Cookies(List<Cookie> cookies) {
        this.cookies = cookies;
        return this;
    }

    /**
     * Crea un nuevo object del tipo JBRestAPI, en base a los parametros obtenidos previamente
     * @return object del tipo JBRestAPI, en base a los parametros obtenidos previamente
     * @throws ValorUndefined Lanza esta excepción si la url proporcionada es null o esta vacía
     */
    public JBRestAPI newClient() throws ValorUndefined {
        JBRestAPI restAPI=new JBRestAPI();
        restAPI.Filter(this.getFilter());
        restAPI.Url(this.getUrl());
        restAPI.ParametrosDeRuta(this.getParametrosDeRuta());
        restAPI.QueryParams(this.getQueryParams());
        restAPI.MediaType(this.getMediaType());
        restAPI.Headers(this.getHeaders());
        restAPI.Cookies(this.getCookies());
        return restAPI;
    }

    /**
     * Crea un object del tipo JBRestAPI en base a los parametros obtenidos anteriormente
     * @param base Objeto al cual se añadira la configuración enviada por medio del Builder
     * @return object del tipo JBRestAPI, en base a los parametros obtenidos previamente
     * @throws ValorUndefined Lanza esta excepción si la url proporcionada es null o esta vacía
     */
    public JBRestAPI clientBaseOn(JBRestAPI base) throws ValorUndefined {
        base.Filter(this.getFilter());
        base.Url(this.getUrl());
        base.ParametrosDeRuta(this.getParametrosDeRuta());
        base.QueryParams(this.getQueryParams());
        base.MediaType(this.getMediaType());
        base.Headers(this.getHeaders());
        base.Cookies(this.getCookies());
        return base;
    }

}