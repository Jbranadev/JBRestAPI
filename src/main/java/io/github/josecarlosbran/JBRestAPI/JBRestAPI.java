package io.github.josecarlosbran.JBRestAPI;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.sse.SseEventSource;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;

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
            if(!stringIsNullOrEmpty(this.getUrl())){
                this.Url(this.getUrl());
            }
            this.ParametrosDeRuta(this.parametrosDeRuta);
            this.QueryParams(this.queryParams);
            this.MediaType(this.mediaType);
            this.TypeResultIsColeccion(this.getTypeResultIsColeccion());
            this.Headers(this.headers);
            this.Cookies(this.cookies);
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
        this.ParametrosDeRuta(this.parametrosDeRuta);
        this.QueryParams(this.queryParams);
        this.MediaType(this.mediaType);
        this.TypeResultIsColeccion(this.getTypeResultIsColeccion());
        this.Headers(this.headers);
        this.Cookies(this.cookies);
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
            this.QueryParams(this.queryParams);
            this.MediaType(this.mediaType);
            this.TypeResultIsColeccion(this.getTypeResultIsColeccion());
            this.Headers(this.headers);
            this.Cookies(this.cookies);
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
            this.ParametrosDeRuta(this.parametrosDeRuta);
            this.MediaType(this.mediaType);
            this.TypeResultIsColeccion(this.getTypeResultIsColeccion());
            this.Headers(this.headers);
            this.Cookies(this.cookies);
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
            this.TypeResultIsColeccion(this.getTypeResultIsColeccion());
            this.Headers(this.headers);
            this.Cookies(this.cookies);
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
            this.Cookies(this.cookies);
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
            this.Headers(this.headers);
            this.request.get(String.class);
        }
    }

    /**
     * Crea un BuilderJBRestAPI
     * @return BuilderJBRestAPI por medio del cual se puede crear una instancia de JBRestAPI
     */
    public static JBRestAPIBuilder builder(){
        return new JBRestAPIBuilder();
    }


    /**
     * Invoke HTTP GET method for the current request synchronously.
     *
     * @return invocation response.
     * @throws jakarta.ws.rs.ProcessingException in case the invocation processing has failed.
     */
    public Response get(){
        return this.request.get();
    }

    /**
     * Invoke HTTP GET method for the current request synchronously.
     *
     * @param <T> response entity type.
     * @param responseType Java type the response entity will be converted to.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     * @throws WebApplicationException in case the response status code of the response returned by the server is not
     * {@link jakarta.ws.rs.core.Response.Status.Family#SUCCESSFUL successful} and the specified response type is not
     * {@link jakarta.ws.rs.core.Response}.
     */
    public <T> T get(Class<T> responseType){
        return this.request.get(responseType);
    }

    /**
     * Invoke HTTP GET method for the current request synchronously.
     *
     * @param <T> generic response entity type.
     * @param responseType representation of a generic Java type the response entity will be converted to.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     * @throws WebApplicationException in case the response status code of the response returned by the server is not
     * {@link jakarta.ws.rs.core.Response.Status.Family#SUCCESSFUL successful} and the specified generic response type does
     * not represent {@link jakarta.ws.rs.core.Response}
     */
    public <T> T get(GenericType<T> responseType){
        return this.request.get(responseType);
    }

    /**
     * Invoke HTTP PUT method for the current request synchronously.
     *
     * @param entity request entity, including it's full {@link jakarta.ws.rs.core.Variant} information. Any variant-related
     * HTTP headers previously set (namely {@code Content-Type}, {@code Content-Language} and {@code Content-Encoding}) will
     * be overwritten using the entity variant information.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     */
    public Response put(Entity<?> entity){
        return this.request.put(entity);
    }

    /**
     * Invoke HTTP PUT method for the current request synchronously.
     *
     * @param <T> response entity type.
     * @param entity request entity, including it's full {@link jakarta.ws.rs.core.Variant} information. Any variant-related
     * HTTP headers previously set (namely {@code Content-Type}, {@code Content-Language} and {@code Content-Encoding}) will
     * be overwritten using the entity variant information.
     * @param responseType Java type the response entity will be converted to.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     * @throws WebApplicationException in case the response status code of the response returned by the server is not
     * {@link jakarta.ws.rs.core.Response.Status.Family#SUCCESSFUL successful} and the specified response type is not
     * {@link jakarta.ws.rs.core.Response}.
     */
    public <T> T put(Entity<?> entity, Class<T> responseType){
        return this.request.put(entity, responseType);
    }

    /**
     * Invoke HTTP PUT method for the current request synchronously.
     *
     * @param <T> generic response entity type.
     * @param entity request entity, including it's full {@link jakarta.ws.rs.core.Variant} information. Any variant-related
     * HTTP headers previously set (namely {@code Content-Type}, {@code Content-Language} and {@code Content-Encoding}) will
     * be overwritten using the entity variant information.
     * @param responseType representation of a generic Java type the response entity will be converted to.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     * @throws WebApplicationException in case the response status code of the response returned by the server is not
     * {@link jakarta.ws.rs.core.Response.Status.Family#SUCCESSFUL successful} and the specified generic response type does
     * not represent {@link jakarta.ws.rs.core.Response}.
     */
    public <T> T put(Entity<?> entity, GenericType<T> responseType){
        return this.request.put(entity, responseType);
    }

    /**
     * Invoke HTTP POST method for the current request synchronously.
     *
     * @param entity request entity, including it's full {@link jakarta.ws.rs.core.Variant} information. Any variant-related
     * HTTP headers previously set (namely {@code Content-Type}, {@code Content-Language} and {@code Content-Encoding}) will
     * be overwritten using the entity variant information.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     */
    public Response post(Entity<?> entity){
        return this.request.post(entity);
    }

    /**
     * Invoke HTTP POST method for the current request synchronously.
     *
     * @param <T> response entity type.
     * @param entity request entity, including it's full {@link jakarta.ws.rs.core.Variant} information. Any variant-related
     * HTTP headers previously set (namely {@code Content-Type}, {@code Content-Language} and {@code Content-Encoding}) will
     * be overwritten using the entity variant information.
     * @param responseType Java type the response entity will be converted to.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     * @throws WebApplicationException in case the response status code of the response returned by the server is not
     * {@link jakarta.ws.rs.core.Response.Status.Family#SUCCESSFUL successful} and the specified response type is not
     * {@link jakarta.ws.rs.core.Response}.
     */
    public <T> T post(Entity<?> entity, Class<T> responseType){
        return this.request.post(entity, responseType);
    }

    /**
     * Invoke HTTP POST method for the current request synchronously.
     *
     * @param <T> generic response entity type.
     * @param entity request entity, including it's full {@link jakarta.ws.rs.core.Variant} information. Any variant-related
     * HTTP headers previously set (namely {@code Content-Type}, {@code Content-Language} and {@code Content-Encoding}) will
     * be overwritten using the entity variant information.
     * @param responseType representation of a generic Java type the response entity will be converted to.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     * @throws WebApplicationException in case the response status code of the response returned by the server is not
     * {@link jakarta.ws.rs.core.Response.Status.Family#SUCCESSFUL successful} and the specified generic response type does
     * not represent {@link jakarta.ws.rs.core.Response}.
     */
    public <T> T post(Entity<?> entity, GenericType<T> responseType){
        return this.request.post(entity, responseType);
    }

    /**
     * Invoke HTTP DELETE method for the current request synchronously.
     *
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     */
    public Response delete(){
        return this.request.delete();
    }

    /**
     * Invoke HTTP DELETE method for the current request synchronously.
     *
     * @param <T> response entity type.
     * @param responseType Java type the response entity will be converted to.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     * @throws WebApplicationException in case the response status code of the response returned by the server is not
     * {@link jakarta.ws.rs.core.Response.Status.Family#SUCCESSFUL successful} and the specified response type is not
     * {@link jakarta.ws.rs.core.Response}.
     */
    public <T> T delete(Class<T> responseType){
        return this.request.delete(responseType);
    }

    /**
     * Invoke HTTP DELETE method for the current request synchronously.
     *
     * @param <T> generic response entity type.
     * @param responseType representation of a generic Java type the response entity will be converted to.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     * @throws WebApplicationException in case the response status code of the response returned by the server is not
     * {@link jakarta.ws.rs.core.Response.Status.Family#SUCCESSFUL successful} and the specified generic response type does
     * not represent {@link jakarta.ws.rs.core.Response}.
     */
    public <T> T delete(GenericType<T> responseType){
        return this.request.delete(responseType);
    }

    // HEAD

    /**
     * Invoke HTTP HEAD method for the current request synchronously.
     *
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     */
    public Response head(){
        return this.request.head();
    }

    // OPTIONS

    /**
     * Invoke HTTP OPTIONS method for the current request synchronously.
     *
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     */
    public Response options(){
        return this.request.options();
    }

    /**
     * Invoke HTTP OPTIONS method for the current request synchronously.
     *
     * @param <T> response entity type.
     * @param responseType Java type the response entity will be converted to.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     * @throws WebApplicationException in case the response status code of the response returned by the server is not
     * {@link jakarta.ws.rs.core.Response.Status.Family#SUCCESSFUL successful} and the specified response type is not
     * {@link jakarta.ws.rs.core.Response}.
     */
    public <T> T options(Class<T> responseType){
        return this.request.options(responseType);
    }

    /**
     * Invoke HTTP OPTIONS method for the current request synchronously.
     *
     * @param <T> generic response entity type.
     * @param responseType representation of a generic Java type the response entity will be converted to.
     * @return invocation response.
     * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
     * conversion of the response entity data to an instance of a particular Java type).
     * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
     * @throws WebApplicationException in case the response status code of the response returned by the server is not
     * {@link jakarta.ws.rs.core.Response.Status.Family#SUCCESSFUL successful} and the specified generic response type does
     * not represent {@link jakarta.ws.rs.core.Response}.
     */
    public <T> T options(GenericType<T> responseType){

        return this.request.options(responseType);
    }

    /**
     * Build new SSE event source pointing at a SSE streaming {@link WebTarget web target}.
     * <p>
     * The returned event source is ready, but not {@link SseEventSource#open() connected} to the SSE endpoint. It is
     * expected that you will manually invoke its method once you are ready to start receiving SSE events.
     * In case you want to build an event source instance that is already connected to the SSE endpoint, use the event
     * source builder  method instead.
     * <p>
     * Once the event source is open, the incoming events are processed by the event source in an asynchronous task that
     * runs in an internal single-threaded {@link ScheduledExecutorService scheduled executor service}.
     *
     * @return new event source instance, ready to be connected to the SSE endpoint.
     */
    public SseEventSource suscribeEventSource(){
        return SseEventSource.target(this.myResource).build();
    }


    /**
     * Access the asynchronous uniform request invocation interface to asynchronously invoke the built request.
     *
     * @return asynchronous uniform request invocation interface.
     */
    public AsyncInvoker async(){
        return this.request.async();
    }

    /**
     * Access the default reactive invoker based on {@link java.util.concurrent.CompletionStage}.
     *
     * @return default reactive invoker instance.
     * @since 2.1
     * @see jakarta.ws.rs.client.Invocation.Builder#rx(Class)
     */
    public CompletionStageRxInvoker rx(){
        return this.request.rx();
    }

    /**
     * Access a reactive invoker based on a {@link RxInvoker} subclass provider. Note that corresponding
     * {@link RxInvokerProvider} must be registered in the client runtime.
     * <p>
     * This method is an extension point for JAX-RS implementations to support other types representing asynchronous
     * computations.
     *
     * @param <T> generic invoker type.
     * @param clazz {@link RxInvoker} subclass.
     * @return reactive invoker instance.
     * @throws IllegalStateException when provider for given class is not registered.
     * @see jakarta.ws.rs.client.Client#register(Class)
     * @since 2.1
     */
    public <T extends RxInvoker> T rx(Class<T> clazz){
        return this.request.rx(clazz);
    }


}

