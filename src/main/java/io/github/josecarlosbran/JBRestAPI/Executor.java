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

import io.github.josecarlosbran.JBRestAPI.Enumeraciones.contentType;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.Metodo;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.requestCode;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.typeAutentication;

class Executor extends Thread{

    private requestCode codigorequest;
    private String url;
    private String data;
    private String credenciales;
    private typeAutentication typeautentication;
    private contentType contenttype;
    private Metodo method;
    private String respuesta;


    public void run(){

        //Decide que tipo de metodo ejecuta
        //Log.d("Metodo que será ejecutado: ", ""+getMethod());
        //Metodo Get
        Methods metodos=new Methods();

        if(this.getMethod() == Metodo.GET){
            setRespuesta(metodos.Get(this.getUrl(),  this.getCredenciales(),
                    this.getTypeautentication().getTipoAutenticacion(), this.getContenttype().getContentType()));
        }

        //Metodo Post
        if(this.getMethod() == Metodo.POST){
            setRespuesta(metodos.Post(this.getUrl(), this.getData(), this.getCredenciales(),
                    this.getTypeautentication().getTipoAutenticacion(), this.getContenttype().getContentType()));
        }

        //Metodo Put
        if(this.getMethod() == Metodo.PUT){
            setRespuesta(metodos.Put(this.getUrl(), this.getData(), this.getCredenciales(),
                    this.getTypeautentication().getTipoAutenticacion(), this.getContenttype().getContentType()));
        }

        //Metodo Delete
        if(this.getMethod() == Metodo.DELETE){
            setRespuesta(metodos.Delete(this.getUrl(), this.getData(), this.getCredenciales(),
                    this.getTypeautentication().getTipoAutenticacion(), this.getContenttype().getContentType()));
        }

        this.setCodigorequest(metodos.getCodigorequest());

    }


    /***
     * Obtiene la url del endpoint a consumir el RestAPI
     * @return retorna la url del EndPoint a consumir del RestAPI
     */
    protected String getUrl() {
        return url;
    }

    /***
     * Setea la url del EndPoint del RestAPI
     * @param url URL del endpoint a consumir
     */
    protected void setUrl(String url) {
        this.url = url;
    }

    /***
     * Obtiene la data que se desea envíar al EndPoint del RestAPI
     * @return Retorna la data que se desea envíar al EndPoint del RestAPI
     */
    protected String getData() {
        return data;
    }

    /***
     * Setea la data que se desea envíar al EndPoint del RestAPI
     * @param data Data que sera envíada al EndPoint del RestAPI
     */
    protected void setData(String data) {
        this.data = data;
    }

    /**
     * Obtiene las credenciales del usuario que consumira el RestAPI
     * @return Retorna un string con las credenciales del usuario que consumira el RestAPI
     */
    protected String getCredenciales() {
        return credenciales;
    }

    /***
     * Setea las credenciales del usuario que consumira el RestAPI
     * @param credenciales credenciales que se utilizaran para consumir el RestAPI
     */
    protected void setCredenciales(String credenciales) {
        this.credenciales = credenciales;
    }


    /***
     * Obtiene el tipo de autenticación que se indica para consumir el RestAPI
     * @return Retorna un objeto typeAutentication con el tipo de autenticación indicada para
     * consumir el RestAPI
     */
    protected typeAutentication getTypeautentication() {
        return typeautentication;
    }

    /***
     * Setea el tipo de autenticación que estaremos utilizando para consumir el RestAPI
     * @param typeautentication Tipeautentication que acepta el RestAPI
     */
    protected void setTypeautentication(typeAutentication typeautentication) {
        this.typeautentication = typeautentication;
    }

    /***
     * Obtiene el contentType que se indica para consumir el RestAPI
     * @return Retorna un objeto contentType con el tipo de contenido indicado para
     * consmir el RestAPI
     */
    protected contentType getContenttype() {
        return this.contenttype;
    }

    /***
     * Setea el contentType que acepta el RestAPI
     * @param contenttype Content-Type que acepta el RestAPI
     */
    protected void setContenttype(contentType contenttype) {
        this.contenttype = contenttype;
    }

    /***
     * Obtiene el metodo que se a indicado al executor que consuma del RestAPI
     * @return Retorna un Enum method con el tipo de metodo que se desea consumir del RestAPI
     */
    protected Metodo getMethod() {
        return method;
    }

    /***
     * Setea el metodo que se utilizara al consumir el RestAPI
     * @param method Enumeración del tipo metodo, el cual indica al executor que metodo se estara ejecutando
     */
    protected void setMethod(Metodo method) {
        this.method = method;
    }


    /***
     * Obtiene la respuesta obtenida del RestAPI
     * @return Retorna la respuesta del RestAPI
     */
    protected String getRespuesta() {
        return respuesta;
    }

    /***
     * Setea la respuesta del RestAPI
     * @param respuesta Respuesta obtenida de haber consumido el RestAPI
     */
    protected void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /***
     * Obtiene el codigo de respuesta de haber consumido el RestAPI
     * @return Retorna un objeto requestCode con el codigo de respuesta del EndPoint
     */
    protected requestCode getCodigorequest() {
        return codigorequest;
    }

    /**
     * Setea el codigo de respuesta que a envíado el RestAPI
     * @param codigorequest Codigorequest que respondio el RestAPI
     */
    protected void setCodigorequest(requestCode codigorequest) {
        this.codigorequest = codigorequest;
    }
}
