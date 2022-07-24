/***
 * Copyright (C) 2022 El proyecto de código abierto JBRestAPI de José Bran
 *
 * Con licencia de Apache License, Versión 2.0 (la "Licencia");
 * no puede usar este archivo excepto de conformidad con la Licencia.
 * Puede obtener una copia de la Licencia en
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * A menos que lo exija la ley aplicable o se acuerde por escrito, el software
 * distribuido bajo la Licencia se distribuye "TAL CUAL",
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ya sean expresas o implícitas.
 * Consulte la Licencia para conocer el idioma específico que rige los permisos y
 * limitaciones bajo la Licencia.
 */
package io.github.josecarlosbran.JBRestAPI.Enumeraciones;

public enum metodo {
    /***
     * Recupera un recurso.
     * Es utilizado únicamente para consultar información al servidor,
     * muy parecidos a realizar un SELECT a la base de datos. No soporta el envío del payload
     */
    GET,

    /***
     *Crea un recurso nuevo.
     * Es utilizado para solicitar la creación de un nuevo registro,
     * es decir, algo que no existía previamente, es decir,
     * es equivalente a realizar un INSERT en la base de datos. Soporta el envío del payload.
     */
    POST,

    /***
     * Actualiza un recurso existente.
     * Se utiliza para actualizar por completo un registro existente, es decir,
     * es parecido a realizar un UPDATE a la base de datos. Soporta el envío del payload.
     */
    PUT,

    /***
     * Suprime un recurso.
     * Este método se utiliza para eliminar un registro existente,
     * es similar a DELETE a la base de datos. No soporta el envío del payload.
     */
    DELETE,

    /****
     * Este método se utilizar para obtener información sobre un determinado
     * recurso sin retornar el registro. Este método se utiliza a menudo para
     * probar la validez de los enlaces de hipertexto, la accesibilidad y las modificaciones recientes.
     */
    HEAD,

    /***
     * Este método es utilizado para describir las opciones de comunicación para el recurso de destino.
     * Es muy utilizado con CORS (Cross-Origin Resource Sharing)
     * para validar si el servidor acepta peticiones de diferentes origines.
     */
    OPTIONS
}
