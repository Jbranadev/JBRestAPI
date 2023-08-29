# JBRestAPI :computer:

JBRestAPI es una librería java que permite el consumo de un RestAPI en segundo plano.
Lo cual evita la interrupción del hilo principal, lo cual la hace una librería útil para aplicaciones android como para
programas que desean tener un mejor rendimiento de manera fácil. Lo cual la hace una potente herramienta para programas
empresariales y personales que exigen grandes niveles de rendimiento.
* * *

## Estado del Proyecto :atom:

JBRestAPI actualmente está en una etapa de desarrollo continuo, por lo cual sus observaciones y recomendaciones,
son bienvenidas para mejorar el proyecto.
***

## ¿Cómo usar JBRestAPI?

Utilizar JBRestAPI es muy fácil.

Usar JBRestAPI es tan fácil como instanciar un objeto de la clase RestApi, configurar el tipo de RestApi que
consumiremos
a travez de los métodos provistos por la clase y hacer el llamado al tipo de solicitud que queremos ejecutar.

Usando el constructor por default.

~~~
/***
* Constructor por default de clase RestApi, crea un objeto de la clase, configurada su contentType como tipo
* JSON y el typeAutentication tipo BEARER, los cuales son los valores por default
*/
RestApi work=new RestApi();
~~~

Usando el constructor personalizado, el cual permite especificar el tipo de contenido que acepta el RestApi que vamos a
consumir
y el tipo de autenticación que está acepta.

~~~
/***
* Constructor de la clase RestApi que permite configurar las propiedades que tendra el objeto creado
* @param AutenticationType Tipo de autenticación que soporta el restapi que se estara consumiendo.
* @param ContentType Tipo de contenido que soporta el restapi que se estara consumiendo.
*/
RestApi work=new RestApi(typeAutentication.BASIC, contentType.AUDIO3GPP);
~~~

[Si necesita conocer más acerca de los Tipos de contenido disponibles en JBRestAPI haga click en este enlace.](/resources/ContentType.md)

[Si necesita conocer más acerca de los Tipos de autenticación disponibles en JBRestAPI haga click en este enlace.](/resources/TypeAutentication.md)

Para realizar una solicitud al RestApi, puedes hacerlo de la siguiente manera.
Tener en consideración que la respuesta obtenida del EndPoint es retornada por medio de un String.

~~~
/***
* Metodo GET que consume el RestAPI haciendo uso del Executor, el cual encapsula
* la logica para que este codigo corra en segundo plano.
* @param url Url del endpoint a consumir
* @param credenciales Credenciales para autenticarse y poder consumir el endPoint
* @return Retorna un string con la respuesta obtenida del RestAPI
*/
respuesta=work.Get(url, credenciales);

/****
* Metodo POST que consume el RestAPI haciendo uso del Executor, el cual encapsula
* la logica para que este codigo corra en segundo plano.
* @param url Url del endpoint a consumir
* @param data Data que se desea envíar al endPoint
* @param credenciales Credenciales para autenticarse y poder consumir el endPoint
* @return Retorna un string con la respuesta obtenida del RestAPI
*/
respuesta=work.Post(url, data, credenciales);

/***
* Metodo PUT que consume el RestAPI haciendo uso del Executor, el cual encapsula
* la logica para que este codigo corra en segundo plano.
* @param url Url del endpoint a consumir
* @param data Data que se desea envíar al endPoint
* @param credenciales Credenciales para autenticarse y poder consumir el endPoint
* @return Retorna un string con la respuesta obtenida del RestAPI
*/
respuesta=work.Put(url, data, credenciales);

/***
* Metodo DELETE que consume el RestAPI haciendo uso del Executor, el cual encapsula
* la logica para que este codigo corra en segundo plano.
* @param url Url del endpoint a consumir
* @param data Data que se desea envíar al endPoint
* @param credenciales Credenciales para autenticarse y poder consumir el endPoint
* @return Retorna un string con la respuesta obtenida del RestAPI
*/
respuesta=work.Delete(url, data, credenciales);
~~~

Adicional a obtener la respuesta del servidor, también podemos revisar el código HTTP con el que respondió
el servidor a nuestra petición de la siguiente manera.

~~~
/***
* Obtiene el codigo de respuesta de haber consumido el RestAPI
* @return Retorna un objeto requestCode con el código de respuesta del EndPoint
*/
requestCode responseCode=work.getCodigorespuesta();
~~~

### ¿Configuración de JBRestAPI de acuerdo a las necesidades de mi implementación?

JBRestAPI puede ser configurada de acuerdo a las necesidades de la implementación que usted esté realizando.

- Modificar el tipo de contenido que acepta el RestApi que se estará consumiendo.

Usted puede modificar el tipo de contenido que acepta el RestApi que se estará consumiendo de la siguiente manera.

~~~
/***
* Setea el contentType que acepta el RestAPI
* @param contenttype Content-Type que acepta el RestAPI
*/
work.setContenttype(contenttype);
~~~

- Modificar el tipo de autenticación que acepta el RestApi que se estará consumiendo.

Usted puede modificar el tipo de autenticación que acepta el RestApi que se estará consumiendo.

~~~
/***
* Setea el tipo de autenticación que estaremos utilizando para consumir el RestAPI
* @param typeAutentication Tipo de autenticación que acepta el RestAPI
*/
work.setTypeAutentication(typeAutentication.BASIC);
~~~

* * *

## ¿Cómo Obtener JBRestAPI para usarlo en mi proyecto?

Puedes obtener la librería JBRestAPI de la siguiente manera

Maven

~~~
<dependency>
    <groupId>io.github.josecarlosbran</groupId>
    <artifactId>JBRestAPI</artifactId>
    <version>0.2.3</version>
</dependency>
~~~

Gradle

~~~
implementation 'io.github.josecarlosbran:JBRestAPI:0.2.3'
~~~

Para mayor información sobre como descargar JBRestAPI desde otros
administradores de paquetes, puedes ir al siguiente Link
<https://search.maven.org/artifact/io.github.josecarlosbran/JBRestAPI>

***

## Licencia :balance_scale:

JBRestAPI es una librería open source desarrollada por José Bran, para poder consumir un EndPoint
de un ApiRest de una manera fácil y óptima, con licencia de Apache License, Versión 2.0;

No puede usar esta librería excepto de conformidad con la Licencia.
Puede obtener una copia de la Licencia en http://www.apache.org/licenses/LICENSE-2.0

A menos que lo exija la ley aplicable o se acuerde por escrito, el software
distribuido bajo la Licencia se distribuye "TAL CUAL",
SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ya sean expresas o implícitas.
Consulte la Licencia para conocer el idioma específico que rige los permisos y
limitaciones bajo la Licencia.

***
