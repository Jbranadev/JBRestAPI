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
import io.github.josecarlosbran.JBRestAPI.Enumeraciones.requestCode;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;


class Methods {

    private requestCode codigorequest = requestCode.NOT_FOUND;

    /****
     * Verifica si una cadena esta vacía o es nula
     * @param cadena Cadena a Validar
     * @return Retorna True si la cadena envíada esta vacía o nula, de lo contrario retorna false
     */
    public static boolean stringIsNullOrEmpty(String cadena) {
        //System.out.println(cadena);
        if (Objects.isNull(cadena) || cadena.isEmpty()) {
            return true;
        }
        return false;
    }


    /****
     * Lee la información que contiene un imputstream y la retorna en un string.
     * es usado para leer la respuesta del EndPoit
     * @param inputStream inputStream del cual obtendremos el contenido del string
     * @return Retorna el contenido del inputStream en un String
     */
    public static String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();
        try {
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, java.nio.charset.Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            LogsJB.fatal("Excepción disparada al leer la respuesta del servidor: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
        return output.toString();
    }

    /***
     * Convierte las credenciales del usuario a base64
     * @param usuario Usuario que desea autenticar
     * @param contraseña Contraseña del usuario que desea autenticar
     * @return Retorna el string de autorización base64 de las credenciales del usuario
     */
    public static String getCredencialesToBase64(String usuario, String contraseña) {
        String result = null;
        try {
            String temp = usuario + ":" + contraseña;
            //Convierte la cadena de texto en un array de Bytes
            byte[] bytes = temp.getBytes(StandardCharsets.UTF_8);
            //Obtiene la cadena base64 resultante
            result = Base64.getEncoder().encodeToString(bytes);
            //result = Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            LogsJB.fatal("Excepción capturada en el metodo que combierte las credenciales a 64Bits: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
        return result;
    }

    /**
     * Convierte una cadena de base64 a lenguaje normal
     *
     * @param cadena Cadena de texto que deseamos convertir
     * @return Retorna el resultado de convertir la cadena base 64
     */
    public static String getStringFromStringBase64(String cadena) {
        String result = null;
        try {
            byte[] decodeBytes = Base64.getDecoder().decode(cadena);
            result = new String(decodeBytes);
        } catch (Exception e) {
            LogsJB.fatal("Excepción capturada en el método que combierte una cadena Base64 a Normal: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
        return result;
    }


    /***
     * Metodo GET que consume el EndPoint del RestAPI
     * @param url Url del endpoint a consumir
     * @param credenciales Credenciales para autenticarse y poder consumir el endPoint
     * @param typeautentication Tipo de autenticación que utiliza el endPoint que se desea consumir
     * @param contenttype Tipo de contenido que recibe el endPoint
     * @return Retorna la respuesta del servidor en un String, si no obtuvo una respuesta retorna Null
     */
    protected String Get(String url, String credenciales, String typeautentication, String contenttype) {
        String respuesta = null;
        try {
            LogsJB.info("Inicia la creacion del hilo de consulta Get: " + "Hilo creado");
            LogsJB.debug("Inicia la creacion del hilo de consulta Get: " + url);
            LogsJB.debug("Inicia la creacion del hilo de consulta Get: " + credenciales);
            LogsJB.debug("Se crea el objeto url");
            URL endPoint = new URL(url);
            LogsJB.info("Se crea la Conexión");
            HttpURLConnection conexion = (HttpURLConnection) endPoint.openConnection();
            conexion.setRequestMethod("GET");
            LogsJB.debug("Setea el metodo");
            conexion.setRequestProperty("Authorization", typeautentication + credenciales);
            LogsJB.debug("Setea el encabezado");
            conexion.setRequestProperty("Content-Type", contenttype);
            LogsJB.debug("Setea el contenido");
            conexion.setRequestProperty("Accept", contenttype);
            LogsJB.debug("Setea el contentype que acepta el rest api");
            respuesta = procesarRespuesta(conexion);
            LogsJB.info("Finalizo la consulta al RestAPI");
            conexion.disconnect();
        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada en el hilo de consulta Get: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
        return respuesta;
    }

    /***
     * Metodo POST que consume el EndPoint del RestAPI
     * @param url Url del endpoint a consumir
     * @param data Data que se desea envíar al endPoint
     * @param credenciales Credenciales para autenticarse y poder consumir el endPoint
     * @param typeautentication Tipo de autenticación que utiliza el endPoint que se desea consumir
     * @param contenttype Tipo de contenido que recibe el endPoint del RestAPI
     * @return Retorna la respuesta del servidor en un String, si no obtuvo una respuesta retorna Null
     */
    protected String Post(String url, String data, String credenciales, String typeautentication, String contenttype) {
        String respuesta = null;
        try {
            LogsJB.info("Inicia la creacion del hilo de consulta Post: " + "Hilo creado");
            LogsJB.debug("Inicia la creacion del hilo de consulta Post: " + url);
            LogsJB.debug("Inicia la creacion del hilo de consulta Post: " + data);
            LogsJB.debug("Inicia la creacion del hilo de consulta Post: " + credenciales);
            LogsJB.debug("Inicia la creacion del hilo de consulta Post: " + contenttype);
            LogsJB.debug("Se crea el objeto url");
            URL endPoint = new URL(url);
            LogsJB.info("Se crea la Conexión");
            HttpURLConnection conexion = (HttpURLConnection) endPoint.openConnection();
            conexion.setRequestMethod("POST");
            // Activar método POST
            conexion.setDoOutput(true);
            LogsJB.debug("Setea el metodo");
            conexion.setRequestProperty("Authorization", typeautentication + credenciales);
            LogsJB.debug("Setea el encabezado");
            conexion.setRequestProperty("Content-Type", contenttype);
            LogsJB.debug("Setea el contenido");
            conexion.setRequestProperty("Accept", contenttype);
            LogsJB.debug("Setea el contentype que acepta el rest api");
            OutputStream out = conexion.getOutputStream();
            LogsJB.debug("Crea el OutPut Stream");
            // Usas tu método ingeniado para convertir el archivo a bytes
            out.write(data.getBytes());
            LogsJB.debug("Escribe los Bytes");
            out.flush();
            LogsJB.debug("Reaiza el Flush");
            out.close();
            LogsJB.debug("Cierra la escritura");
            respuesta = procesarRespuesta(conexion);
            LogsJB.info("Finalizo la consulta al RestAPI");
            conexion.disconnect();
        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada en el hilo de consulta Post: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
        return respuesta;
    }

    /***
     * Metodo PUT que consume el EndPoint del RestAPI
     * @param url Url del endpoint a consumir
     * @param data Data que se desea envíar al endPoint
     * @param credenciales Credenciales para autenticarse y poder consumir el endPoint
     * @param typeautentication Tipo de autenticación que utiliza el endPoint que se desea consumir
     * @param contenttype Tipo de contenido que recibe el endPoint del RestAPI
     * @return Retorna la respuesta del servidor en un String, si no obtuvo una respuesta retorna Null
     */
    protected String Put(String url, String data, String credenciales, String typeautentication, String contenttype) {
        String respuesta = null;
        try {
            LogsJB.info("Inicia la creacion del hilo de consulta Put: " + "Hilo creado");
            LogsJB.debug("Inicia la creacion del hilo de consulta Put: " + url);
            LogsJB.debug("Inicia la creacion del hilo de consulta Put: " + data);
            LogsJB.debug("Inicia la creacion del hilo de consulta Put: " + credenciales);
            LogsJB.debug("Se crea el objeto url");
            URL endPoint = new URL(url);
            LogsJB.info("Se crea la Conexión");
            HttpURLConnection conexion = (HttpURLConnection) endPoint.openConnection();
            conexion.setRequestMethod("PUT");
            // Activar método POST
            conexion.setDoOutput(true);
            LogsJB.debug("Setea el metodo");
            conexion.setRequestProperty("Authorization", typeautentication + credenciales);
            LogsJB.debug("Setea el encabezado");
            conexion.setRequestProperty("Content-Type", contenttype);
            LogsJB.debug("Setea el contenido");
            conexion.setRequestProperty("Accept", contenttype);
            LogsJB.debug("Setea el contentype que acepta el rest api");
            OutputStream out = conexion.getOutputStream();
            LogsJB.debug("Crea el OutPut Stream");
            // Usas tu método ingeniado para convertir el archivo a bytes
            out.write(data.getBytes());
            LogsJB.debug("Escribe los Bytes");
            out.flush();
            LogsJB.debug("Reaiza el Flush");
            out.close();
            LogsJB.debug("Cierra la escritura");
            respuesta = procesarRespuesta(conexion);
            LogsJB.info("Finalizo la consulta al RestAPI");
            conexion.disconnect();
        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada en el hilo de consulta Put: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
        return respuesta;
    }

    /***
     * Metodo DELETE que consume el EndPoint del RestAPI
     * @param url Url del endpoint a consumir
     * @param data Data que se desea envíar al endPoint
     * @param credenciales Credenciales para autenticarse y poder consumir el endPoint
     * @param typeautentication Tipo de autenticación que utiliza el endPoint que se desea consumir
     * @param contenttype Tipo de contenido que recibe el endPoint del RestAPI
     * @return Retorna la respuesta del servidor en un String, si no obtuvo una respuesta retorna Null
     */
    protected String Delete(String url, String data, String credenciales, String typeautentication, String contenttype) {
        String respuesta = null;
        try {
            LogsJB.info("Inicia la creacion del hilo de consulta Delete: " + "Hilo creado");
            LogsJB.debug("Inicia la creacion del hilo de consulta Delete: " + url);
            LogsJB.debug("Inicia la creacion del hilo de consulta Delete: " + data);
            LogsJB.debug("Inicia la creacion del hilo de consulta Delete: " + credenciales);
            LogsJB.debug("Se crea el objeto url");
            URL endPoint = new URL(url);
            LogsJB.info("Se crea la Conexión");
            HttpURLConnection conexion = (HttpURLConnection) endPoint.openConnection();
            conexion.setRequestMethod("DELETE");
            // Activar método POST
            conexion.setDoOutput(true);
            LogsJB.debug("Setea el metodo");
            conexion.setRequestProperty("Authorization", typeautentication + credenciales);
            LogsJB.debug("Setea el encabezado");
            conexion.setRequestProperty("Content-Type", contenttype);
            LogsJB.debug("Setea el contenido");
            conexion.setRequestProperty("Accept", contenttype);
            LogsJB.debug("Setea el contentype que acepta el rest api");
            OutputStream out = conexion.getOutputStream();
            LogsJB.debug("Crea el OutPut Stream");
            // Usas tu método ingeniado para convertir el archivo a bytes
            out.write(data.getBytes());
            LogsJB.debug("Escribe los Bytes");
            out.flush();
            LogsJB.debug("Reaiza el Flush");
            out.close();
            LogsJB.debug("Cierra la escritura");
            respuesta = procesarRespuesta(conexion);
            LogsJB.info("Finalizo la consulta al RestAPI");
            conexion.disconnect();
        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada en el hilo de consulta Delete: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
        return respuesta;
    }

    /***
     * Procesa la respuesta obtenida de consumir el RestAPI
     * @param conexion la conexción que esta consumiendo el EndPoint
     * @return Retorna un string que representa la respuesta del servidor.
     */
    protected String procesarRespuesta(HttpURLConnection conexion) {
        String respuesta = null;
        try {
            int responsecode = conexion.getResponseCode();
            if (responsecode == 200) {
                setCodigorequest(requestCode.OK);

                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                LogsJB.info("Solicitud aceptada");
                respuesta = temporal;
            }
            if (responsecode == 201) {
                setCodigorequest(requestCode.CREATED);
                LogsJB.info("Se creo o modifico el recurso en el EndPoint del RestAPI");
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                respuesta = temporal;
            }
            if (responsecode == 204) {
                setCodigorequest(requestCode.NO_CONTENT);
                respuesta = " ";
                LogsJB.info("Solicitud aceptada, no habían datos para devolver");
            }
            if (responsecode == 400) {
                setCodigorequest(requestCode.BAD_REQUEST);
                InputStream responseBody = conexion.getInputStream();
                String temporal = readFromStream(responseBody);
                LogsJB.info("La solicitud no fue valida");
                respuesta = temporal;
            }

            if (responsecode == 401) {
                setCodigorequest(requestCode.UNAUTHORIZED);
                LogsJB.info("Falta la información de autorización en la solicitud");
            }
            if (responsecode == 403) {
                setCodigorequest(requestCode.FORBIDEN);
                LogsJB.info("No tiene los permisos necesarios para consumir el EndPoint del RestAPI");
            }
            if (responsecode == 404) {
                setCodigorequest(requestCode.NOT_FOUND);
                LogsJB.info("No encontro el EndPoint del RestAPI");
            }
            if (responsecode == 405) {
                setCodigorequest(requestCode.METHOD_NOT_ALLOWED);
                LogsJB.info("El metodo no esta disponible para el verbo HTTP utilizado para consumir el EndPoint");
            }
            if (responsecode == 406) {
                setCodigorequest(requestCode.NOT_ACCEPTABLE);
                LogsJB.info("El formato de de datos indicados en la cabecera accept no corresponde al tipo de dato esperado por el servidor");
            }
            if (responsecode == 409) {
                setCodigorequest(requestCode.CONFLICT);
                LogsJB.info("Conflicto al tratar de modificar un recurso en el EndPoint");
            }
            if (responsecode == 415) {
                setCodigorequest(requestCode.UNSUPPORTED_MEDIA_TYPE);
                LogsJB.info("El formato de content type no es soportado");
            }
            if (responsecode == 500) {
                setCodigorequest(requestCode.INTERNAL_SERVER_ERROR);
                LogsJB.info("Error Interno del servidor del RestAPI");
            }
        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada al procesar la respuesta del servidor: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
        return respuesta;
    }


    /***
     * Obtiene el codigo de respuesta de haber consumido el RestAPI
     * @return Retorna un objeto requestCode con el codigo de respuesta del EndPoint
     */
    protected requestCode getCodigorequest() {
        return this.codigorequest;
    }

    /***
     * Setea el codigo de respuesta que a envíado el RestAPI
     * @param Codigorequest Codigorequest que respondio el RestAPI
     */
    protected void setCodigorequest(requestCode Codigorequest) {
        try {
            /*Field field = Methods.class.getDeclaredField("codigorequest");
            field.setAccessible(true);
            field.set(null, Codigorequest);*/
            this.codigorequest = Codigorequest;
        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada en el método que modifica el codigorequest: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
    }
}
