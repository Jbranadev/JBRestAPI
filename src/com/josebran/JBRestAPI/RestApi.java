package com.josebran.JBRestAPI;


import com.josebran.JBRestAPI.Enumeraciones.*;

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


    public String Get(String url, String data, String credenciales){
        String respuesta=null;
        respuesta=execute(url, data, credenciales, getTipeautentication(), getContenttype(), metodo.GET);
        return respuesta;
    }


    public String Post(String url, String data, String credenciales){
        String respuesta=null;
        respuesta=execute(url, data, credenciales, getTipeautentication(), getContenttype(), metodo.POST);
        return respuesta;
    }


    public String Put(String url, String data, String credenciales){
        String respuesta=null;
        respuesta=execute(url, data, credenciales, getTipeautentication(), getContenttype(), metodo.PUT);
        return respuesta;
    }


    public String Delete(String url, String data, String credenciales){
        String respuesta=null;
        respuesta=execute(url, data, credenciales, getTipeautentication(), getContenttype(), metodo.DELETE);
        return respuesta;
    }




    public requestCode getCodigorespuesta() {
        return codigorespuesta;
    }

    public void setCodigorespuesta(requestCode codigorespuesta) {

        this.codigorespuesta = codigorespuesta;
    }

    public typeAutentication getTipeautentication() {
        return tipeautentication;
    }

    public void setTipeautentication(typeAutentication tipeautentication) {
        this.tipeautentication = tipeautentication;
    }

    public contentType getContenttype() {
        return contenttype;
    }

    public void setContenttype(contentType contenttype) {
        this.contenttype = contenttype;
    }
}
