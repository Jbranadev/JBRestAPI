package com.josebran.JBRestAPI;



import com.josebran.JBRestAPI.Enumeraciones.requestCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;


public class Methods {

    private static requestCode codigorequest;

    public static String readFromStream(InputStream inputStream)  {
        StringBuilder output = new StringBuilder();
        try{
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, java.nio.charset.Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            //Log.d("Exepcion disparada al leer la respuesta del servidor: ", e.toString());
        }
        return output.toString();
    }
    
    public static String getCredencialesToBase64(String usuario, String contraseña){
        String result=null;
        String temp=usuario+":"+contraseña;
        //Convierte la cadena de texto en un array de Bytes
        byte[] bytes= temp.getBytes(StandardCharsets.UTF_8);
        //Obtiene la cadena base64 resultante
        result=Base64.getEncoder().encodeToString(bytes);
        //result = Base64.encodeToString(bytes, Base64.DEFAULT);
        return result;
    }

    public static String getStringFromStringBase64(String cadena){
        byte[] decodeBytes=Base64.getDecoder().decode(cadena);
        String result=new String(decodeBytes);
        return result;
    }


    public static String Get(String url, String data,  String credenciales, String typeautentication, String contenttype){
        String respuesta=null;
        try {
            //Log.d( "Hilo creado");
            //Log.d( url);
            //Log.d( data);
            //Log.d( credenciales);
            //Log.d( "Se crea el objeto url");

            URL endPoint = new URL(url);
            //Log.d( "Se crea la Conecxion");
            HttpsURLConnection conexion = (HttpsURLConnection) endPoint.openConnection();
            conexion.setRequestMethod("GET");
            //Log.d( "Setea el metodo");
            conexion.setRequestProperty("Authorization", typeautentication+credenciales);
            //Log.d( "Setea el encabezado");
            conexion.setRequestProperty("Content-Type", contenttype);
            //Log.d( "Setea el contenido");
            conexion.setRequestProperty("Accept", contenttype);
            //Log.d( "Setea lo que acepta el rest api");
            
            int responsecode=conexion.getResponseCode();
            if(responsecode==200){
                setCodigorequest(requestCode.OK);
                //Log.d( "Se obtuvo una respuesta positiva del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("Solicitud aceptada");
                respuesta=temporal;
            }
            if(responsecode==201){
                setCodigorequest(requestCode.CREATED);
                //Log.d( "Se creo o modifico el recurso en el EndPoint del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                respuesta=temporal;
            }
            if(responsecode==204){
                setCodigorequest(requestCode.NO_CONTENT);
                respuesta=" ";
                //Log.d( "Solicitud aceptada, no habían datos para devolver");
            }
            if(responsecode==400){
                setCodigorequest(requestCode.BAD_REQUEST);
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("La solicitud no fue valida");
                respuesta=temporal;
            }
            
            if(responsecode==401){
                setCodigorequest(requestCode.UNAUTHORIZED);
                //Log.d( "Falta la información de autorización en la solicitud");


            }
            if(responsecode==403){
                setCodigorequest(requestCode.FORBIDEN);
                //Log.d( "No tiene los permisos necesarios para consumir el EndPoint del RestAPI");
            }
            if(responsecode==404){
                setCodigorequest(requestCode.NOT_FOUND);
                //Log.d( "No encontro el EndPoint del RestAPI");
            }
            if(responsecode==405){
                setCodigorequest(requestCode.METHOD_NOT_ALLOWED);
                //Log.d( "El metodo no esta disponible para el verbo HTTP utilizado para consumir el EndPoint");
            }
            if(responsecode==406){
                setCodigorequest(requestCode.NOT_ACCEPTABLE);
                //Log.d( "El formato de de datos indicados en la cabecera accept no corresponde al tipo de dato esperado por el servidor");
            }
            if(responsecode==409){
                setCodigorequest(requestCode.CONFLICT);
                //Log.d( "Conflicto al tratar de modificar un recurso en el EndPoint");
            }
            if(responsecode==415){
                setCodigorequest(requestCode.UNSUPPORTED_MEDIA_TYPE);
                //Log.d( "El formato de content type no es soportado");
            }
            if(responsecode==500){
                setCodigorequest(requestCode.INTERNAL_SERVER_ERROR);
                //Log.d( "Error Interno del servidor del RestAPI");
            }
            //Log.d( "Finalizo la consulta al RestAPI");
            conexion.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
            //Log.d("Exepcion disparada en el hilo de consulta Get: ", e.toString());
        }
        return respuesta;
    }

    public static String Post(String url, String data,  String credenciales, String typeautentication, String contenttype){
        String respuesta=null;
        try {
            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Hilo creado");
            //Log.d("Inicia la creacion del hilo de consulta Post: ", url);
            //Log.d("Inicia la creacion del hilo de consulta Post: ", data);
            //Log.d("Inicia la creacion del hilo de consulta Post: ", credenciales);
            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Se crea el objeto url");
            URL endPoint = new URL(url);
            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Se crea la Conecxion");
            HttpsURLConnection conexion = (HttpsURLConnection) endPoint.openConnection();
            conexion.setRequestMethod("POST");
            // Activar método POST
            conexion.setDoOutput(true);
            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Setea el metodo");
            conexion.setRequestProperty("Authorization", typeautentication+credenciales);
            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Setea el encabezado");
            conexion.setRequestProperty("Content-Type", contenttype);
            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Setea el contenido");
            conexion.setRequestProperty("Accept", contenttype);
            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Setea lo que acepta el rest api");
            OutputStream out = conexion.getOutputStream();
            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Crea el OutPut Stream");
            // Usas tu método ingeniado para convertir el archivo a bytes
            out.write(data.getBytes());
            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Escribe los Bytes");
            out.flush();
            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Reaiza el Flush");
            out.close();
            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Cierra la escritura");

            int responsecode=conexion.getResponseCode();
            if(responsecode==200){
                setCodigorequest(requestCode.OK);
                //Log.d( "Se obtuvo una respuesta positiva del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("Solicitud aceptada");
                respuesta=temporal;
            }
            if(responsecode==201){
                setCodigorequest(requestCode.CREATED);
                //Log.d( "Se creo o modifico el recurso en el EndPoint del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                respuesta=temporal;
            }
            if(responsecode==204){
                setCodigorequest(requestCode.NO_CONTENT);
                respuesta=" ";
                //Log.d( "Solicitud aceptada, no habían datos para devolver");
            }
            if(responsecode==400){
                setCodigorequest(requestCode.BAD_REQUEST);
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("La solicitud no fue valida");
                respuesta=temporal;
            }

            if(responsecode==401){
                setCodigorequest(requestCode.UNAUTHORIZED);
                //Log.d( "Falta la información de autorización en la solicitud");


            }
            if(responsecode==403){
                setCodigorequest(requestCode.FORBIDEN);
                //Log.d( "No tiene los permisos necesarios para consumir el EndPoint del RestAPI");
            }
            if(responsecode==404){
                setCodigorequest(requestCode.NOT_FOUND);
                //Log.d( "No encontro el EndPoint del RestAPI");
            }
            if(responsecode==405){
                setCodigorequest(requestCode.METHOD_NOT_ALLOWED);
                //Log.d( "El metodo no esta disponible para el verbo HTTP utilizado para consumir el EndPoint");
            }
            if(responsecode==406){
                setCodigorequest(requestCode.NOT_ACCEPTABLE);
                //Log.d( "El formato de de datos indicados en la cabecera accept no corresponde al tipo de dato esperado por el servidor");
            }
            if(responsecode==409){
                setCodigorequest(requestCode.CONFLICT);
                //Log.d( "Conflicto al tratar de modificar un recurso en el EndPoint");
            }
            if(responsecode==415){
                setCodigorequest(requestCode.UNSUPPORTED_MEDIA_TYPE);
                //Log.d( "El formato de content type no es soportado");
            }
            if(responsecode==500){
                setCodigorequest(requestCode.INTERNAL_SERVER_ERROR);
                //Log.d( "Error Interno del servidor del RestAPI");
            }
            //Log.d( "Finalizo la consulta al RestAPI");
            conexion.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
            //Log.d("Exepcion disparada en el hilo de consulta Post: ", e.toString());
        }

        return respuesta;
    }

    public static String Put(String url, String data,  String credenciales, String typeautentication, String contenttype){
        String respuesta=null;
        try {
            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Hilo creado");
            //Log.d("Inicia la creacion del hilo de consulta Put: ", url);
            //Log.d("Inicia la creacion del hilo de consulta Put: ", data);
            //Log.d("Inicia la creacion del hilo de consulta Put: ", credenciales);
            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Se crea el objeto url");
            URL endPoint = new URL(url);
            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Se crea la Conecxion");
            HttpsURLConnection conexion = (HttpsURLConnection) endPoint.openConnection();
            conexion.setRequestMethod("PUT");
            // Activar método POST
            conexion.setDoOutput(true);
            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Setea el metodo");
            conexion.setRequestProperty("Authorization", typeautentication+credenciales);
            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Setea el encabezado");
            conexion.setRequestProperty("Content-Type", contenttype);
            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Setea el contenido");
            conexion.setRequestProperty("Accept", contenttype);
            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Setea lo que acepta el rest api");
            OutputStream out = conexion.getOutputStream();
            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Crea el OutPut Stream");
            // Usas tu método ingeniado para convertir el archivo a bytes
            out.write(data.getBytes());
            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Escribe los Bytes");
            out.flush();
            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Reaiza el Flush");
            out.close();
            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Cierra la escritura");

            int responsecode=conexion.getResponseCode();
            if(responsecode==200){
                setCodigorequest(requestCode.OK);
                //Log.d( "Se obtuvo una respuesta positiva del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("Solicitud aceptada");
                respuesta=temporal;
            }
            if(responsecode==201){
                setCodigorequest(requestCode.CREATED);
                //Log.d( "Se creo o modifico el recurso en el EndPoint del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                respuesta=temporal;
            }
            if(responsecode==204){
                setCodigorequest(requestCode.NO_CONTENT);
                respuesta=" ";
                //Log.d( "Solicitud aceptada, no habían datos para devolver");
            }
            if(responsecode==400){
                setCodigorequest(requestCode.BAD_REQUEST);
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("La solicitud no fue valida");
                respuesta=temporal;
            }

            if(responsecode==401){
                setCodigorequest(requestCode.UNAUTHORIZED);
                //Log.d( "Falta la información de autorización en la solicitud");


            }
            if(responsecode==403){
                setCodigorequest(requestCode.FORBIDEN);
                //Log.d( "No tiene los permisos necesarios para consumir el EndPoint del RestAPI");
            }
            if(responsecode==404){
                setCodigorequest(requestCode.NOT_FOUND);
                //Log.d( "No encontro el EndPoint del RestAPI");
            }
            if(responsecode==405){
                setCodigorequest(requestCode.METHOD_NOT_ALLOWED);
                //Log.d( "El metodo no esta disponible para el verbo HTTP utilizado para consumir el EndPoint");
            }
            if(responsecode==406){
                setCodigorequest(requestCode.NOT_ACCEPTABLE);
                //Log.d( "El formato de de datos indicados en la cabecera accept no corresponde al tipo de dato esperado por el servidor");
            }
            if(responsecode==409){
                setCodigorequest(requestCode.CONFLICT);
                //Log.d( "Conflicto al tratar de modificar un recurso en el EndPoint");
            }
            if(responsecode==415){
                setCodigorequest(requestCode.UNSUPPORTED_MEDIA_TYPE);
                //Log.d( "El formato de content type no es soportado");
            }
            if(responsecode==500){
                setCodigorequest(requestCode.INTERNAL_SERVER_ERROR);
                //Log.d( "Error Interno del servidor del RestAPI");
            }
            //Log.d( "Finalizo la consulta al RestAPI");
            conexion.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
            //Log.d("Exepcion disparada en el hilo de consulta Put: ", e.toString());
        }
        return respuesta;
    }

    public static String Delete(String url, String data,  String credenciales, String typeautentication, String contenttype){
        String respuesta=null;
        try {
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Hilo creado");
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", url);
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", data);
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", credenciales);
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Se crea el objeto url");
            URL endPoint = new URL(url);
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Se crea la Conecxion");
            HttpsURLConnection conexion = (HttpsURLConnection) endPoint.openConnection();
            conexion.setRequestMethod("DELETE");
            // Activar método POST
            conexion.setDoOutput(true);
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Setea el metodo");
            conexion.setRequestProperty("Authorization", typeautentication+credenciales);
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Setea el encabezado");
            conexion.setRequestProperty("Content-Type", contenttype);
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Setea el contenido");
            conexion.setRequestProperty("Accept", contenttype);
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Setea lo que acepta el rest api");
            OutputStream out = conexion.getOutputStream();
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Crea el OutPut Stream");
            // Usas tu método ingeniado para convertir el archivo a bytes
            out.write(data.getBytes());
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Escribe los Bytes");
            out.flush();
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Reaiza el Flush");
            out.close();
            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Cierra la escritura");
            int responsecode=conexion.getResponseCode();
            if(responsecode==200){
                setCodigorequest(requestCode.OK);
                //Log.d( "Se obtuvo una respuesta positiva del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("Solicitud aceptada");
                respuesta=temporal;
            }
            if(responsecode==201){
                setCodigorequest(requestCode.CREATED);
                //Log.d( "Se creo o modifico el recurso en el EndPoint del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                respuesta=temporal;
            }
            if(responsecode==204){
                setCodigorequest(requestCode.NO_CONTENT);
                respuesta=" ";
                //Log.d( "Solicitud aceptada, no habían datos para devolver");
            }
            if(responsecode==400){
                setCodigorequest(requestCode.BAD_REQUEST);
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("La solicitud no fue valida");
                respuesta=temporal;
            }

            if(responsecode==401){
                setCodigorequest(requestCode.UNAUTHORIZED);
                //Log.d( "Falta la información de autorización en la solicitud");
            }
            if(responsecode==403){
                setCodigorequest(requestCode.FORBIDEN);
                //Log.d( "No tiene los permisos necesarios para consumir el EndPoint del RestAPI");
            }
            if(responsecode==404){
                setCodigorequest(requestCode.NOT_FOUND);
                //Log.d( "No encontro el EndPoint del RestAPI");
            }
            if(responsecode==405){
                setCodigorequest(requestCode.METHOD_NOT_ALLOWED);
                //Log.d( "El metodo no esta disponible para el verbo HTTP utilizado para consumir el EndPoint");
            }
            if(responsecode==406){
                setCodigorequest(requestCode.NOT_ACCEPTABLE);
                //Log.d( "El formato de de datos indicados en la cabecera accept no corresponde al tipo de dato esperado por el servidor");
            }
            if(responsecode==409){
                setCodigorequest(requestCode.CONFLICT);
                //Log.d( "Conflicto al tratar de modificar un recurso en el EndPoint");
            }
            if(responsecode==415){
                setCodigorequest(requestCode.UNSUPPORTED_MEDIA_TYPE);
                //Log.d( "El formato de content type no es soportado");
            }
            if(responsecode==500){
                setCodigorequest(requestCode.INTERNAL_SERVER_ERROR);
                //Log.d( "Error Interno del servidor del RestAPI");
            }
            //Log.d( "Finalizo la consulta al RestAPI");
            conexion.disconnect();
        }catch (Exception e) {
            //Log.d("Exepcion disparada en el hilo de consulta Delete: ", e.toString());
            return null;
        }
        return respuesta;
    }


    public static requestCode getCodigorequest() {
        return codigorequest;
    }

    public static void setCodigorequest(requestCode Codigorequest) throws NoSuchFieldException, IllegalAccessException {
        Field field = Methods.class.getDeclaredField("codigorequest");
        field.setAccessible(true);
        field.set(null, Codigorequest);
        //codigorequest = codigorequest;
    }
}
