package io.github.josecarlosbran.JBRestAPI;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

import static io.github.josecarlosbran.JBRestAPI.Methods.stringIsNullOrEmpty;

@ToString
public class JBRestAPI {

    @Getter
    private String url = null;

    private List<ParametroOfRoute> parametrosDeRuta = null;

    private List<ParametroOfRoute> queryParams = null;

    private MediaType mediaType = null;

    @Getter
    private Boolean typeResultIsColeccion = false;


    private MultivaluedMap<String, Object> headers = null;


    private List<Cookie> cookies = null;

    /**
     * Cliente que se usa para registrar un filtro
     */
    private Client cliente = null;

    /**
     * WebTarget Obtenido y por medio del cual se podra realizar solicitudes posteriores.
     */
    private WebTarget myResource = null;

    /**
     * request al cual se setea el MediaType
     */
    public Invocation.Builder request = null;

    JBRestAPI(){

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
    public void Filter(Class filtroRegistro) throws ValorUndefined {
        if (!Objects.isNull(filtroRegistro)) {
            this.cliente = ClientBuilder.newClient().register(filtroRegistro);
        }
    }

    /**
     * Setea la Url a la cual apuntara el RestAPI
     *
     * @param url Url a la cual apuntara el RestAPI
     * @throws ValorUndefined Lanza esta excepción si la url proporcionada es null o esta vacía
     */
    public void Url(String url) throws ValorUndefined {
        if (stringIsNullOrEmpty(url)) {
            throw new ValorUndefined("La url Proporcionada no puede ser nula");
        }
        this.url = url;
        if (!Objects.isNull(this.cliente)) {
            this.myResource = this.cliente.target(url);
        } else {
            this.myResource = ClientBuilder.newClient().target(url);
        }
    }

    /**
     * Parametros de ruta
     *
     * @param parametrosDeRuta Lista de parametros de ruta
     */
    public void ParametrosDeRuta(List<ParametroOfRoute> parametrosDeRuta) {
        if (!Objects.isNull(parametrosDeRuta)) {
            this.parametrosDeRuta = parametrosDeRuta;
            parametrosDeRuta.forEach(parametroOfRoute -> this.myResource.resolveTemplate(parametroOfRoute.getName(), parametroOfRoute.getValue()));
        }
    }

    /**
     * Parametros de ruta
     *
     * @param queryParams Lista de parametros de ruta
     */
    public void QueryParams(List<ParametroOfRoute> queryParams) {
        if (!Objects.isNull(queryParams)) {
            this.queryParams = queryParams;
            queryParams.forEach(queryParam -> this.myResource.queryParam(queryParam.getName(), queryParam.getValue()));
        }
    }

    /**
     * Tipo de mediaType que define el media type con que se negociaran los datos en el servidor
     *
     * @param mediaType mediaType para negociar el contenido con el servidor
     */
    public void MediaType(MediaType mediaType) {
        if (!Objects.isNull(mediaType)) {
            this.mediaType = mediaType;
            this.request = this.myResource.request(mediaType);
        }

    }

    /**
     * Indica si el tipo de Resultado a Obtener es una Colección
     *
     * @param typeResultIsColeccion True si el tipo de retorno es una colección, False si el tipo de retorno no es una
     *                              colección, default is False
     */
    public void TypeResultIsColeccion(Boolean typeResultIsColeccion) {
        this.typeResultIsColeccion = typeResultIsColeccion;
    }

    /**
     * MultiValueMap que representa los headers de nuestra aplicación
     *
     * @param headers headers a enviar en nuestra solicitud al servidor
     */
    public void Headers(MultivaluedMap<String, Object> headers) {
        if (!Objects.isNull(headers)) {
            this.headers = headers;
            this.request.headers(headers);
        }
    }

    /**
     * Lista de Cookie's a enviar al servidor
     *
     * @param cookies Cookie's a enviar al servidor
     */
    public void Cookies(List<Cookie> cookies) {
        if (!Objects.isNull(cookies)) {
            this.cookies = cookies;
            cookies.forEach(cookie -> this.request.cookie(cookie));
        }
    }

    /**
     * Crea un BuilderJBRestAPI
     * @return BuilderJBRestAPI por medio del cual se puede crear una instancia de JBRestAPI
     */
    public static JBRestAPIBuilder builder(){
        return new JBRestAPIBuilder();
    }


}

