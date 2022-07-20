package io.github.josecarlosbran.JBRestAPI;


import io.github.josecarlosbran.JBRestAPI.Enumeraciones.contentType;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.metodo;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.requestCode;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.typeAutentication;

import static io.github.josecarlosbran.JBRestAPI.Methods.*;

class Executor extends Thread{

    private requestCode codigorequest;
    private String url=null;
    private String data=null;
    private String credenciales=null;
    private typeAutentication typeautentication=null;
    private contentType contenttype=null;
    private metodo method=null;
    private String respuesta=null;


    public void run(){

        //Decide que tipo de metodo ejecuta
        //Log.d("Metodo que será ejecutado: ", ""+getMethod());
        //Metodo Get
        if(getMethod() == metodo.GET){
            setRespuesta(Get(getUrl(),  getCredenciales(),
                    getTypeautentication().getTipoAutenticacion(), getContenttype().getContentType()));
        }

        //Metodo Post
        if(getMethod() == metodo.POST){
            setRespuesta(Post(getUrl(), getData(), getCredenciales(),
                    getTypeautentication().getTipoAutenticacion(), getContenttype().getContentType()));
        }

        //Metodo Put
        if(getMethod() == metodo.PUT){
            setRespuesta(Put(getUrl(), getData(), getCredenciales(),
                    getTypeautentication().getTipoAutenticacion(), getContenttype().getContentType()));
        }

        //Metodo Delete
        if(getMethod() == metodo.DELETE){
            setRespuesta(Delete(getUrl(), getData(), getCredenciales(),
                    getTypeautentication().getTipoAutenticacion(), getContenttype().getContentType()));
        }

        setCodigorequest(Methods.getCodigorequest());

    }


    /***
     * Obtiene la url del endpoint a consumir el RestAPI
     * @return retorna la url del EndPoint a consumir del RestAPI
     */
    public String getUrl() {
        return url;
    }

    /***
     * Setea la url del EndPoint del RestAPI
     * @param url URL del endpoint a consumir
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /***
     * Obtiene la data que se desea envíar al EndPoint del RestAPI
     * @return Retorna la data que se desea envíar al EndPoint del RestAPI
     */
    public String getData() {
        return data;
    }

    /***
     * Setea la data que se desea envíar al EndPoint del RestAPI
     * @param data Data que sera envíada al EndPoint del RestAPI
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Obtiene las credenciales del usuario que consumira el RestAPI
     * @return Retorna un string con las credenciales del usuario que consumira el RestAPI
     */
    public String getCredenciales() {
        return credenciales;
    }

    /***
     * Setea las credenciales del usuario que consumira el RestAPI
     * @param credenciales credenciales que se utilizaran para consumir el RestAPI
     */
    public void setCredenciales(String credenciales) {
        this.credenciales = credenciales;
    }


    /***
     * Obtiene el tipo de autenticación que se indica para consumir el RestAPI
     * @return Retorna un objeto typeAutentication con el tipo de autenticación indicada para
     * consumir el RestAPI
     */
    public typeAutentication getTypeautentication() {
        return typeautentication;
    }

    /***
     * Setea el tipo de autenticación que estaremos utilizando para consumir el RestAPI
     * @param typeautentication Tipeautentication que acepta el RestAPI
     */
    public void setTypeautentication(typeAutentication typeautentication) {
        this.typeautentication = typeautentication;
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
    public void setContenttype(contentType contenttype) {
        this.contenttype = contenttype;
    }

    /***
     * Obtiene el metodo que se a indicado al executor que consuma del RestAPI
     * @return Retorna un Enum method con el tipo de metodo que se desea consumir del RestAPI
     */
    public metodo getMethod() {
        return method;
    }

    /***
     * Setea el metodo que se utilizara al consumir el RestAPI
     * @param method Enumeración del tipo metodo, el cual indica al executor que metodo se estara ejecutando
     */
    public void setMethod(metodo method) {
        this.method = method;
    }


    /***
     * Obtiene la respuesta obtenida del RestAPI
     * @return Retorna la respuesta del RestAPI
     */
    public String getRespuesta() {
        return respuesta;
    }

    /***
     * Setea la respuesta del RestAPI
     * @param respuesta Respuesta obtenida de haber consumido el RestAPI
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /***
     * Obtiene el codigo de respuesta de haber consumido el RestAPI
     * @return Retorna un objeto requestCode con el codigo de respuesta del EndPoint
     */
    public requestCode getCodigorequest() {
        return codigorequest;
    }

    /**
     * Setea el codigo de respuesta que a envíado el RestAPI
     * @param codigorequest Codigorequest que respondio el RestAPI
     */
    public void setCodigorequest(requestCode codigorequest) {
        this.codigorequest = codigorequest;
    }
}
