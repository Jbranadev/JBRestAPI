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
package io.github.josecarlosbran.JBRestAPI.Enumeraciones;

public enum contentType {
    //Documentación: https://developer.mozilla.org/es/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types

    /***
     * Archivo de audio AAC
     */
    AUDIOAAC("audio/aac"),

    /**
     * Documento AbiWord
     */
    ABIWORD("application/x-abiword"),

    /**
     * Documento de Archivo (múltiples archivos incrustados)
     */
    DOCUMENTODEARCHIVOSMULTIPLES("application/octet-stream"),

    /**
     * AVI: Audio Video Intercalado
     */
    AVI("video/x-msvideo"),

    /***
     * Formato  eBook Amazon Kindle
     */
    FORMATOEBOOKAMAZONKINDLE("application/vnd.amazon.ebook"),

    /**
     * Cualquier tipo de datos binarios
     */
    BINARIO("application/octet-stream"),

    /***
     * Archivo BZip
     */
    BZIP("application/x-bzip"),

    /**
     * Archivo BZip2
     */
    BZIP2("application/x-bzip2"),

    /***
     * Script C-Shell
     */
    SCRIPTCSHELL("application/x-csh"),

    /***
     * Hojas de estilo (CSS)
     */
    CSS("text/css"),

    /***
     * Valores separados por coma (CSV)
     */
    CSV("text/csv"),

    /***
     * 	Microsoft Word
     */
    DOC("application/msword"),

    /***
     * 	Publicación Electrónica (EPUB)
     */
    EPUB("application/epub+zip"),

    /***
     * Graphics Interchange Format (GIF)
     */
    GIF("image/gif"),

    /**
     * Hipertexto (HTML)
     */
    HTM("text/html"),

    /**
     * Hipertexto (HTML)
     */
    HTML("text/html"),

    /**
     * Formato Icon
     */
    ICO("image/x-icon"),

    /***
     * 	Formato iCalendar
     */
    ICALENDAR("text/calendar"),

    /***
     * Archivo Java (JAR)
     */
    JAR("application/java-archive"),

    /**
     * Imágenes JPEG
     */
    JPEG("image/jpeg"),

    /**
     * JavaScript (ECMAScript)
     */
    JAVASCRIPT("application/javascript"),

    /***
     * Formato JSON
     */
    JSON("application/json"),

    /***
     * Interfaz Digital de Instrumentos Musicales (MIDI)
     */
    AUDIOMIDI("audio/midi"),

    /***
     * Video MPEG
     */
    VIDEOMPEG("video/mpeg"),

    /***
     * Paquete de instalación de Apple
     */
    PAQUETEINSTALACIONAPPLE("application/vnd.apple.installer+xml"),

    /***
     * Documento de presentación de OpenDocument
     */
    DOCUMENTOPRESENTACIONOPENDOCUMENT("application/vnd.oasis.opendocument.presentation"),


    /***
     * Hoja de Cálculo OpenDocument
     */
    HOJADECALCULOOPENDOCUMENT("application/vnd.oasis.opendocument.spreadsheet"),


    /****
     * Documento de texto OpenDocument
     */
    DOCUMENTODETEXTOOPENDOCUMENT("application/vnd.oasis.opendocument.text"),

    /***
     * Audio OGG
     */
    AUDIOOGG("audio/ogg"),

    /***
     * Video OGG
     */
    VIDEOOGG("video/ogg"),

    /***
     * OGG
     */
    OGG("application/ogg"),

    /***
     * Adobe Portable Document Format (PDF)
     */
    PDF("application/pdf"),

    /***
     * Microsoft PowerPoint
     */
    PPT("application/vnd.ms-powerpoint"),

    /***
     * Archivo RAR
     */
    RAR("application/x-rar-compressed"),

    /***
     * Formato de Texto Enriquecido (RTF)
     */
    RTF("application/rtf"),

    /***
     * Script Bourne shell
     */
    SCRIPTBOURNESHELL("application/x-sh"),


    /***
     * Gráficos Vectoriales (SVG)
     */
    SVG("image/svg+xml"),

    /***
     * Small web format (SWF) o Documento Adobe Flash
     */
    SMALLWEBFORMAT("application/x-shockwave-flash"),

    /***
     * Aerchivo Tape (TAR)
     */
    TAR("application/x-tar"),

    /***
     * Formato de archivo de imagen etiquetado (TIFF)
     */
    TIFF("image/tiff"),

    /**
     * Fuente TrueType
     */
    FUENTETRUETYPE("font/ttf"),

    /***
     * Microsft Visio
     */
    MICROSOFTVISIO("application/vnd.visio"),

    /***
     * Formato de audio de forma de onda (WAV)
     */
    AUDIOWAV("audio/x-wav"),

    /****
     * 	Audio WEBM
     */
    AUDIOWEBM("audio/webm"),

    /***
     * Video WEBM
     */
    VIDEOWEBM("video/webm"),

    /***
     * Imágenes WEBP
     */
    IMAGENESWEBP("image/webp"),

    /***
     * Formato de fuente abierta web (WOFF)
     */
    WOFF("font/woff"),

    /***
     * Formato de fuente abierta web (WOFF)
     */
    WOFF2("font/woff2"),

    /***
     * XHTML
     */
    XHTML("application/xhtml+xml"),

    /***
     * 	Microsoft Excel
     */
    EXCEL("application/vnd.ms-excel"),

    /***
     * XML
     */
    XML("application/xml"),

    /***
     * 	XUL
     */
    XUL("application/vnd.mozilla.xul+xml"),

    /**
     * Archivo ZIP
     */
    ZIP("application/zip"),

    /***
     * Contenedor de audio 3GPP
     */
    AUDIOYVIDEO3GPP("video/3gpp"),

    /***
     * Contenedor de audio/video 3GPP
     */
    AUDIO3GPP("audio/3gpp"),

    /***
     * 	Contenedor de audio 3GPP2
     */
    AUDIO3GPP2("audio/3gpp2"),

    /***
     * 	Contenedor de audio/video 3GPP2
     */
    AUDIOYVIDEO3GPP2("video/3gpp2"),

    /***
     * Archivo 7-zip
     */
    SEVENZIP("application/x-7z-compressed");

    private String contenttype;

    private contentType(String s) {
        this.contenttype = s;
    }

    /***
     * Obtiene el tipo de contenido que corresponde a la numeración.
     * @return Retorna un string con el tipo de contenido que corresponde a la numeración.
     */
    public String getContentType() {
        return contenttype;
    }
}
