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

Usar JBRestAPI es tan fácil como instanciar un objeto de la clase RestApi, configurar el tipo de RestApi que consumiremos
a travez de los métodos provistos por la clase y hacer el llamado al tipo de solicitud que queremos ejecutar.

Usando el constructor por default.
~~~
/***
* Constructor por default de clase RestApi, crea un objeto de la clase, configurada su contentType como tipo
* JSON y el typeAutentication tipo BEARER, los cuales son los valores por default
*/
RestApi work=new RestApi();
~~~

Usando el constructor personalizado, el cual permite especificar el tipo de contenido que acepta el RestApi que vamos a consumir
y el tipo de autenticación que está acepta.
~~~
/***
* Constructor de la clase RestApi que permite configurar las propiedades que tendra el objeto creado
* @param AutenticationType Tipo de autenticación que soporta el restapi que se estara consumiendo.
* @param ContentType Tipo de contenido que soporta el restapi que se estara consumiendo.
*/
RestApi work=new RestApi(typeAutentication.BASIC, contentType.AUDIO3GPP);
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
    <version>0.1</version>
</dependency>
~~~

Gradle
~~~
implementation 'io.github.josecarlosbran:JBRestAPI:0.1'
~~~

Para mayor información sobre como descargar JBRestAPI desde otros
administradores de paquetes, puedes ir al siguiente Link
<https://search.maven.org/artifact/io.github.josecarlosbran/JBRestAPI>

***

## Licencia :balance_scale:
JBRestAPI es una librería open source desarrollada por José Bran, para la administración
de los registros de un programa, con licencia de Apache License, Versión 2.0;

No puede usar esta librería excepto de conformidad con la Licencia.
Puede obtener una copia de la Licencia en http://www.apache.org/licenses/LICENSE-2.0

A menos que lo exija la ley aplicable o se acuerde por escrito, el software
distribuido bajo la Licencia se distribuye "TAL CUAL",
SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ya sean expresas o implícitas.
Consulte la Licencia para conocer el idioma específico que rige los permisos y
limitaciones bajo la Licencia.

***
