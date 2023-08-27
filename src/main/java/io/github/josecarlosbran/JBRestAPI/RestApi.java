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
package io.github.josecarlosbran.JBRestAPI;


import com.josebran.LogsJB.LogsJB;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.contentType;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.Metodo;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.requestCode;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.typeAutentication;

public class RestApi{


    private requestCode codigorespuesta;

    private typeAutentication tipeautentication;
    private contentType contenttype;

    /***
     * Constructor de la clase RestApi que permite configurar las propiedades que tendra el objeto creado
     * @param AutenticationType Tipo de autenticación que soporta el restapi que se estara consumiendo.
     * @param ContentType Tipo de contenido que soporta el restapi que se estara consumiendo.
     */
    public RestApi(typeAutentication AutenticationType, contentType ContentType){
        this.setContenttype(ContentType);
        this.setTypeAutentication(AutenticationType);
    }

    /***
     * Constructor por default de clase RestApi, crea un objeto de la clase, configurada su contentType como tipo
     * JSON y el typeAutentication tipo BEARER, los cuales son los valores por default
     */
    public RestApi(){
        this.setContenttype(contentType.JSON);
        this.setTypeAutentication(typeAutentication.BEARER);
    }



    /***
     * Metodo encargado de realizar el consumo del RestAPI llamando a la clase execute, indicandole los parametros y el tipo de
     * consumo que se desea realizar
     * @param url Url del endpoint a consumir
     * @param data Data que se desea envíar al endPoint
     * @param credenciales Credenciales para autenticarse y poder consumir el endPoint
     * @param typeautentication Tipo de autenticación que utiliza el endPoint que se desea consumir
     * @param contenttype Tipo de contenido que recibe el endPoint
     * @param method Tipo de metodo, GET, POST, PUT, DELETE, ETC..
     * @return Retorna un string con la respuesta obtenida del RestAPI
     */
    private String execute(String url, String data, String credenciales, typeAutentication typeautentication, contentType contenttype, Metodo method){
        String respuesta=null;
        try{
            Executor execute=new Executor();
            execute.setUrl(url);
            execute.setData(data);
            execute.setCredenciales(credenciales);
            execute.setTypeautentication(typeautentication);
            execute.setContenttype(contenttype);
            execute.setMethod(method);
            execute.start();
            while(execute.getState() !=Thread.State.TERMINATED){
                //Thread.sleep(1000);
            }
            respuesta=execute.getRespuesta();
            setCodigorespuesta(execute.getCodigorequest());
        }catch (Exception e) {
            LogsJB.fatal("Excepción disparada en el metodo execute, el cual llama la creación del hilo: "+ e.toString());
            LogsJB.fatal("Tipo de Excepción : "+e.getClass());
            LogsJB.fatal("Causa de la Excepción : "+e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : "+e.getMessage());
            LogsJB.fatal("Trace de la Excepción : "+e.getStackTrace());
        }
        return respuesta;

    }


    /***
     * Metodo GET que consume el RestAPI haciendo uso del Executor, el cual encapsula
     * la logica para que este codigo corra en segundo plano.
     * @param url Url del endpoint a consumir
     * @param credenciales Credenciales para autenticarse y poder consumir el endPoint
     * @return Retorna un string con la respuesta obtenida del RestAPI
     */
    public String Get(String url,  String credenciales){
        String respuesta=null;
        respuesta=execute(url, "", credenciales, this.getTipeautentication(), this.getContenttype(), Metodo.GET);
        return respuesta;
    }


    /****
     * Metodo POST que consume el RestAPI haciendo uso del Executor, el cual encapsula
     * la logica para que este codigo corra en segundo plano.
     * @param url Url del endpoint a consumir
     * @param data Data que se desea envíar al endPoint
     * @param credenciales Credenciales para autenticarse y poder consumir el endPoint
     * @return Retorna un string con la respuesta obtenida del RestAPI
     */
    public String Post(String url, String data, String credenciales){
        String respuesta=null;
        respuesta=execute(url, data, credenciales, this.getTipeautentication(), this.getContenttype(), Metodo.POST);
        return respuesta;
    }


    /***
     * Metodo PUT que consume el RestAPI haciendo uso del Executor, el cual encapsula
     * la logica para que este codigo corra en segundo plano.
     * @param url Url del endpoint a consumir
     * @param data Data que se desea envíar al endPoint
     * @param credenciales Credenciales para autenticarse y poder consumir el endPoint
     * @return Retorna un string con la respuesta obtenida del RestAPI
     */
    public String Put(String url, String data, String credenciales){
        String respuesta=null;
        respuesta=execute(url, data, credenciales, this.getTipeautentication(), this.getContenttype(), Metodo.PUT);
        return respuesta;
    }


    /***
     * Metodo DELETE que consume el RestAPI haciendo uso del Executor, el cual encapsula
     * la logica para que este codigo corra en segundo plano.
     * @param url Url del endpoint a consumir
     * @param data Data que se desea envíar al endPoint
     * @param credenciales Credenciales para autenticarse y poder consumir el endPoint
     * @return Retorna un string con la respuesta obtenida del RestAPI
     */
    public String Delete(String url, String data, String credenciales){
        String respuesta=null;
        respuesta=execute(url, data, credenciales, this.getTipeautentication(), this.getContenttype(), Metodo.DELETE);
        return respuesta;
    }



    /***
     * Obtiene el codigo de respuesta de haber consumido el RestAPI
     * @return Retorna un objeto requestCode con el código de respuesta del EndPoint
     */
    public requestCode getCodigorespuesta() {
        return codigorespuesta;
    }
    /**
     * Setea el codigo de respuesta que a envíado el RestAPI
     * @param codigorespuesta Codigorequest que respondio el RestAPI
     */
    public void setCodigorespuesta(requestCode codigorespuesta) {

        this.codigorespuesta = codigorespuesta;
    }

    /***
     * Obtiene el tipo de autenticación que se indica para consumir el RestAPI
     * @return Retorna un objeto typeAutentication con el tipo de autenticación indicada para
     * consumir el RestAPI
     */
    public typeAutentication getTipeautentication() {
        return this.tipeautentication;
    }

    /***
     * Setea el tipo de autenticación que estaremos utilizando para consumir el RestAPI
     * @param typeAutentication Tipo de autenticación que acepta el RestAPI
     */
    public void setTypeAutentication(typeAutentication typeAutentication) {
        this.tipeautentication = typeAutentication;
    }

    /***
     * Obtiene el contentType que se indica para consumir el RestAPI
     * @return Retorna un objeto contentType con el tipo de contenido indicado para
     * consmir el RestAPI
     */
    public contentType getContenttype() {
        return contenttype;
    }

    /***
     * Setea el contentType que acepta el RestAPI
     * @param contenttype Content-Type que acepta el RestAPI
     */
    protected void setContenttype(contentType contenttype) {
        this.contenttype = contenttype;
    }
}
