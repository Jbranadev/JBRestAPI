package com.josebran.JBRestAPI;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;


public class Methods {

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
            //Log.d("Inicia la creacion del hilo de consulta Get: ", "Hilo creado");
            //Log.d("Inicia la creacion del hilo de consulta Get: ", url);
            //Log.d("Inicia la creacion del hilo de consulta Get: ", data);
            //Log.d("Inicia la creacion del hilo de consulta Get: ", credenciales);
            //Log.d("Inicia la creacion del hilo de consulta Get: ", "Se crea el objeto url");

            URL endPoint = new URL(url);
            //Log.d("Inicia la creacion del hilo de consulta Get: ", "Se crea la Conecxion");
            HttpsURLConnection conexion = (HttpsURLConnection) endPoint.openConnection();
            conexion.setRequestMethod("GET");
            //Log.d("Inicia la creacion del hilo de consulta Get: ", "Setea el metodo");
            conexion.setRequestProperty("Authorization", typeautentication+credenciales);
            //Log.d("Inicia la creacion del hilo de consulta Get: ", "Setea el encabezado");
            conexion.setRequestProperty("Content-Type", contenttype);
            //Log.d("Inicia la creacion del hilo de consulta Get: ", "Setea el contenido");
            conexion.setRequestProperty("Accept", contenttype);
            //Log.d("Inicia la creacion del hilo de consulta Get: ", "Setea lo que acepta el rest api");

            if(conexion.getResponseCode()==200){
                //Log.d("Inicia la creacion del hilo de consulta Get: ", "Se obtuvo una respuesta positiva del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("Inicia la creacion del hilo de consulta Get, resultado del metodo: ", temporal);
                respuesta=temporal;
            }
            if(conexion.getResponseCode()==204){
                //Log.d("Inicia la creacion del hilo de consulta Get: ", "Se ejecuto la accion, pero no hay respuesta por parte del servidor");
            }
            if(conexion.getResponseCode()==403){
                //Log.d("Inicia la creacion del hilo de consulta Get: ", "No tiene los permisos necesarios del RestAPI");
            }
            if(conexion.getResponseCode()==406){
                //Log.d("Inicia la creacion del hilo de consulta Get: ", "El formato de Devolucion de la informacion no es el del servidor");
            }

            if(conexion.getResponseCode()==404){
                //Log.d("Inicia la creacion del hilo de consulta Get: ", "No encontro el recurso del RestAPI");
            }
            if(conexion.getResponseCode()==401){
                //Log.d("Inicia la creacion del hilo de consulta Get: ", "No esta autorizado para consumir el recurso del RestAPI");
            }
            if(conexion.getResponseCode()==500){
                //Log.d("Inicia la creacion del hilo de consulta Get: ", "Error Interno del servidor del RestAPI");
            }
            //Log.d("Inicia la creacion del hilo de consulta Get, mensaje del resultado: ", conexion.getResponseMessage());
            //Log.d("Inicia la creacion del hilo de consulta Get: ", "Finalizo la consulta al RestAPI");
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

            if(conexion.getResponseCode()==200){
                //Log.d("Inicia la creacion del hilo de consulta Post: ", "Se obtuvo una respuesta positiva del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("Inicia la creacion del hilo de consulta Post, resultado del metodo: ", temporal);
                respuesta=temporal;
            }
            if(conexion.getResponseCode()==201){
                //Log.d("Inicia la creacion del hilo de consulta Post: ", "Se obtuvo una respuesta positiva del CREATING RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("Inicia la creacion del hilo de consulta Post, resultado del metodo: ", temporal);
                //respuesta=temporal;
            }

            if(conexion.getResponseCode()==204){
                //Log.d("Inicia la creacion del hilo de consulta Post: ", "Se ejecuto la accion, pero no hay respuesta por parte del servidor");
            }
            if(conexion.getResponseCode()==403){
                //Log.d("Inicia la creacion del hilo de consulta Post: ", "No tiene los permisos necesarios del RestAPI");
            }
            if(conexion.getResponseCode()==406){
                //Log.d("Inicia la creacion del hilo de consulta Post: ", "El formato de Devolucion de la informacion no es el del servidor");
            }


            if(conexion.getResponseCode()==404){
                //Log.d("Inicia la creacion del hilo de consulta Post: ", "No encontro el recurso del RestAPI");
            }
            if(conexion.getResponseCode()==401){
                //Log.d("Inicia la creacion del hilo de consulta Post: ", "No esta autorizado para consumir el recurso del RestAPI");
            }
            if(conexion.getResponseCode()==500){
                //Log.d("Inicia la creacion del hilo de consulta Post: ", "Error Interno del servidor del RestAPI");
            }
            //Log.d("Inicia la creacion del hilo de consulta Post, mensaje del resultado: ", conexion.getResponseMessage());


            //Log.d("Inicia la creacion del hilo de consulta Post: ", "Finalizo la consulta al RestAPI");
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

            if(conexion.getResponseCode()==200){
                //Log.d("Inicia la creacion del hilo de consulta Put: ", "Se obtuvo una respuesta positiva del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("Inicia la creacion del hilo de consulta Put, resultado del metodo: ", temporal);
                respuesta=temporal;
            }
            if(conexion.getResponseCode()==201){
                //Log.d("Inicia la creacion del hilo de consulta Put: ", "Se obtuvo una respuesta positiva del CREATING RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("Inicia la creacion del hilo de consulta Put, resultado del metodo: ", temporal);
                //respuesta=temporal;
            }
            if(conexion.getResponseCode()==204){
                //Log.d("Inicia la creacion del hilo de consulta Put: ", "Se ejecuto la accion, pero no hay respuesta por parte del servidor");
            }
            if(conexion.getResponseCode()==403){
                //Log.d("Inicia la creacion del hilo de consulta Put: ", "No tiene los permisos necesarios del RestAPI");
            }
            if(conexion.getResponseCode()==406){
                //Log.d("Inicia la creacion del hilo de consulta Put: ", "El formato de Devolucion de la informacion no es el del servidor");
            }

            if(conexion.getResponseCode()==404){
                //Log.d("Inicia la creacion del hilo de consulta Put: ", "No encontro el recurso del RestAPI");
            }
            if(conexion.getResponseCode()==401){
                //Log.d("Inicia la creacion del hilo de consulta Put: ", "No esta autorizado para consumir el recurso del RestAPI");
            }
            if(conexion.getResponseCode()==500){
                //Log.d("Inicia la creacion del hilo de consulta Put: ", "Error Interno del servidor del RestAPI");
            }
            //Log.d("Inicia la creacion del hilo de consulta Put, mensaje del resultado: ", conexion.getResponseMessage());

            //Log.d("Inicia la creacion del hilo de consulta Put: ", "Finalizo la consulta al RestAPI");
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

            if(conexion.getResponseCode()==200){
                //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Se obtuvo una respuesta positiva del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("Inicia la creacion del hilo de consulta Delete, resultado del metodo: ", temporal);
                respuesta=temporal;
            }
            if(conexion.getResponseCode()==201){
                //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Se obtuvo una respuesta positiva del CREATING RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                //Log.d("Inicia la creacion del hilo de consulta Delete, resultado del metodo: ", temporal);
                //respuesta=temporal;
            }
            if(conexion.getResponseCode()==204){
                //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Se ejecuto la accion, pero no hay respuesta por parte del servidor");
            }
            if(conexion.getResponseCode()==404){
                //Log.d("Inicia la creacion del hilo de consulta Delete: ", "No encontro el recurso del RestAPI");
            }
            if(conexion.getResponseCode()==401){
                //Log.d("Inicia la creacion del hilo de consulta Delete: ", "No esta autorizado para consumir el recurso del RestAPI");
            }
            if(conexion.getResponseCode()==403){
                //Log.d("Inicia la creacion del hilo de consulta Delete: ", "No tiene los permisos necesarios del RestAPI");
            }
            if(conexion.getResponseCode()==403){
                //Log.d("Inicia la creacion del hilo de consulta Delete: ", "El formato de Devolucion de la informacion no es el del servidor");
            }
            if(conexion.getResponseCode()==500){
                //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Error Interno del servidor del RestAPI");
            }
            //Log.d("Inicia la creacion del hilo de consulta Delete, mensaje del resultado: ", conexion.getResponseMessage());


            //Log.d("Inicia la creacion del hilo de consulta Delete: ", "Finalizo la consulta al RestAPI");
        }catch (Exception e) {
            //Log.d("Exepcion disparada en el hilo de consulta Delete: ", e.toString());
            return null;
        }
        return respuesta;
    }


}
