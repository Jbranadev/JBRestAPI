package io.github.josecarlosbran.JBRestAPI;


import io.github.josecarlosbran.JBRestAPI.Enumeraciones.contentType;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.metodo;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.requestCode;
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.typeAutentication;

public class RestApi extends  Methods{
    private requestCode codigorespuesta;

    private typeAutentication tipeautentication;
    private contentType contenttype;

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
    public String execute(String url, String data, String credenciales, typeAutentication typeautentication, contentType contenttype, metodo method){
        String respuesta=null;
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
        respuesta=execute(url, "", credenciales, getTipeautentication(), getContenttype(), metodo.GET);
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
        respuesta=execute(url, data, credenciales, getTipeautentication(), getContenttype(), metodo.POST);
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
        respuesta=execute(url, data, credenciales, getTipeautentication(), getContenttype(), metodo.PUT);
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
        respuesta=execute(url, data, credenciales, getTipeautentication(), getContenttype(), metodo.DELETE);
        return respuesta;
    }



    /***
     * Obtiene el codigo de respuesta de haber consumido el RestAPI
     * @return Retorna un objeto requestCode con el codigo de respuesta del EndPoint
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
        return tipeautentication;
    }

    /***
     * Setea el tipo de autenticación que estaremos utilizando para consumir el RestAPI
     * @param tipeautentication Tipeautentication que acepta el RestAPI
     */
    public void setTipeautentication(typeAutentication tipeautentication) {
        this.tipeautentication = tipeautentication;
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
}
