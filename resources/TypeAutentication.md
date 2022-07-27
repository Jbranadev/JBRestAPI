~~~
/***
* Autentificación básica
*Esta es la forma más sencilla de autenticar a los usuarios.
* La solicitud envía credenciales como usernamey passworden forma de username:passwordal encabezado.
* Está codificado con Base64 y se pasa en el encabezado de Autorización de la siguiente manera:
* Example: Authorization: Basic AKsdKfsdljOf1POs
*/
BASIC("Basic "),


/**
*La autenticación Digest aplica una función hash al nombre de usuario y la contraseña antes
* de enviarlos a través de la red. Es un proceso de aplicación de hashing criptográfico MD5
* con el uso de valores nonce para evitar ataques de repetición.
*
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
~~~