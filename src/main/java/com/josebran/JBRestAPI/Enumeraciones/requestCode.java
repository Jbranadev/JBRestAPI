package com.josebran.JBRestAPI.Enumeraciones;

/***
 * Enumeración que detalla los tipos de codigo de respuesta que podemos recibir al consumir un EndPoint
 */
public enum requestCode {
    //Documentation: https://www.ibm.com/docs/es/odm/8.5.1?topic=api-rest-response-codes-error-messages
    //Documentation: https://code.tutsplus.com/es/tutorials/a-beginners-guide-to-http-and-rest--net-16340

    /***
     * Solicitud aceptada; la respuesta contiene el resultado. Este es un código de respuesta general a cualquier solicitud.
     * En las solicitudes GET, el recurso o datos solicitados están en el cuerpo de la respuesta. En las solicitudes PUT o DELETE,
     * la solicitud fue satisfactoria y la información acerca del resultado
     * (como los identificadores de recursos nuevo o los cambios en el estado del recurso) se puede encontrar en el cuerpo de la respuesta.
     */
    OK(200),

    /***
     * Las operaciones PUT o POST devuelven este código de respuesta e indica que se ha creado un recurso de forma satisfactoria.
     * El cuerpo de la respuesta podría, por ejemplo,
     * contener información acerca de un nuevo recurso o información de validación (por ejemplo, cuándo se actualiza un activo).
     */
    CREATED(201),

    /***
     * Indica que se ha aceptado la solicitud, pero no había datos para devolver.
     * Este respuesta se devuelve cuando se ha procesado la solicitud,
     * pero no se ha devuelto ninguna información adicional acerca de los resultados.
     */
    NO_CONTENT(204),

    /***
     * La solicitud no fue válida. Este código se devuelve cuando el servidor ha intentado procesar la solicitud,
     * pero algún aspecto de la solicitud no es válido; por ejemplo,
     * un recurso formateado de forma incorrecta o un intento de despliegue de un proyecto de sucesos no válido en el tiempo de ejecución de sucesos.
     * La información acerca de la solicitud se proporciona en el cuerpo de la respuesta e incluye un código de error y un mensaje de error.
     */
    BAD_REQUEST(400),

    /***
     * El servidor de aplicaciones devuelve este código de respuesta cuando está habilitada la seguridad
     * y faltaba la información de autorización en la solicitud.
     */
    UNAUTHORIZED(401),

    /***
     * Indica que el cliente ha intentado acceder a un recurso al que no tiene acceso.
     * Podría producirse si el usuario que accede al recurso remoto no tiene privilegios suficientes;
     * por ejemplo, con el rol WBERestApiUsers o WBERestApiPrivilegedUsers. Los usuarios que intenten acceder a
     * proyectos de sucesos privados que son propiedad de otros podrían recibir también este error,
     * pero solo si tienen el rol WBERestApiUsers en lugar de WBERestApiPrivilegedUsers.
     */
    FORBIDEN(403),

    /***
     * Indica que el recurso de destino no existe.
     * Esto podría deberse a que el URI no está bien formado o a que se ha suprimido el recurso.
     */
    NOT_FOUND(404),

    /***
     * Se produce cuando el recurso de destino no admite el método HTTP solicitado;
     * por ejemplo, el recurso de funciones solo permite operaciones GET.
     */
    METHOD_NOT_ALLOWED(405),

    /***
     * El recurso de destino no admite el formato de datos solicitado en la cabecera de Accept
     * o el parámetro accept.
     * Es decir, el cliente ha solicitado la devolución de los datos en un determinado formato,
     * pero el servidor no puede devolver datos en ese formato.
     */
    NOT_ACCEPTABLE(406),

    /***
     * Indica que se ha detectado un cambio conflictivo durante un intento de modificación de un recurso.
     * El cuerpo de la respuesta contiene más información.
     */
    CONFLICT(409),

    /***
     * El recurso de destino no admite el formato de datos del cuerpo de la solicitud especificado en la cabecera de Content-Type.
     */
    UNSUPPORTED_MEDIA_TYPE(415),

    /***
     * Se ha producido un error interno en el servidor.
     * Esto podría indicar un problema con la solicitud o un problema en el código del lado del servidor.
     * Se puede encontrar información acerca del error en el cuerpo de respuesta.
     */
    INTERNAL_SERVER_ERROR(500)
    ;

    private int codigo;

    private requestCode(int i) {
        this.codigo=i;
    }

    /***
     * Obtiene el codigo de respuesta asociado a la numeración
     * @return Retorna el codigo de respuesta de la numeración.
     */
    public int getCodigo(){
        return this.codigo;
    }

}
