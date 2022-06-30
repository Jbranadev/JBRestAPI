package com.josebran.JBRestAPI;


import com.josebran.JBRestAPI.Enumeraciones.contentType;
import com.josebran.JBRestAPI.Enumeraciones.metodo;
import com.josebran.JBRestAPI.Enumeraciones.typeAutentication;

public class RestApi extends  Methods{
    private int codigorespuesta;

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
        return respuesta;
    }

}
