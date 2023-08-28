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

public enum typeAutentication {

    /***
     * Autentificación básica
     *Esta es la forma más sencilla de autenticar a los usuarios.
     * La solicitud envía credenciales como usernamey passworden forma de username:passwordal encabezado.
     * Está codificado con Base64 y se pasa en el encabezado de Autorización de la siguiente manera:
     * Example: Authorization: Basic AKsdKfsdljOf1POs
     */
    BASIC("Basic "),
    /**
     * La autenticación Digest aplica una función hash al nombre de usuario y la contraseña antes
     * de enviarlos a través de la red. Es un proceso de aplicación de hashing criptográfico MD5
     * con el uso de valores nonce para evitar ataques de repetición.
     * <p>
     * Example: Authorization: Digest username="Admin", realm="admin@wso2.com",
     * nonce="dcd98b7102dd2f0e8b11d0f600bfb0c093", uri="/dir/index.html", qop=auth, nc=00000001, cnonce="0a4f113b",
     * response="6629fae49393a05397450978507c4ef1", opaque="5ccc069c403ebaf9f0171e9517f40e41"
     */
    DIGEST("Digest "),


    /***
     * Token de acceso Oauth
     * Autentificación basada en token JWT
     * Example: Authorization: Bearer JWT_TOKEN
     */
    BEARER("Bearer "),
    /**
     * Autentificación basada en clave APIKEY
     */
    APIKEY("Apikey ");

    private String tipoautenticacion;

    private typeAutentication(String s) {
        this.tipoautenticacion = s;

    }

    /**
     * Permite obtener el tipo de autenticación correspondiente a la numeración seleccionada
     *
     * @return
     */
    public String getTipoAutenticacion() {
        return tipoautenticacion;
    }

}
