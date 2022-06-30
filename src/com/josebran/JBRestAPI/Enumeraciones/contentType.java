package com.josebran.JBRestAPI.Enumeraciones;

public enum contentType {
    //Documentación: https://developer.mozilla.org/es/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types


    AUDIOAAC("audio/aac"),
    ABIWORD("application/x-abiword"),
    DOCUMENTODEARCHIVOSMULTIPLES("application/octet-stream"),
    AVI("video/x-msvideo"),
    FORMATOEBOOKAMAZONKINDLE("application/vnd.amazon.ebook"),
    BINARIO("application/octet-stream"),
    BZIP("application/x-bzip"),
    BZIP2("application/x-bzip2"),
    SCRIPTCSHELL("application/x-csh"),
    CSS("text/css"),
    CSV("text/csv"),
    DOC("application/msword"),
    EPUB("application/epub+zip"),
    GIF("image/gif"),
    HTM("text/html"),
    HTML("text/html"),
    ICO("image/x-icon"),
    ICALENDAR("text/calendar"),
    JAR("application/java-archive"),
    JPEG("image/jpeg"),
    JAVASCRIPT("application/javascript"),
    JSON("application/json"),
    AUDIOMIDI("audio/midi"),
    VIDEOMPEG("video/mpeg"),
    PAQUETEINSTALACIONAPPLE("application/vnd.apple.installer+xml"),
    DOCUMENTOPRESENTACIONOPENDOCUMENT("application/vnd.oasis.opendocument.presentation"),
    HOJADECALCULOOPENDOCUMENT("application/vnd.oasis.opendocument.spreadsheet"),
    DOCUMENTODETEXTOOPENDOCUMENT("application/vnd.oasis.opendocument.text"),
    AUDIOOGG("audio/ogg"),
    VIDEOOGG("video/ogg"),
    OGG("application/ogg"),
    PDF("application/pdf"),
    PPT("application/vnd.ms-powerpoint"),
    RAR("application/x-rar-compressed"),
    RTF("application/rtf"),
    SCRIPTBOURNESHELL("application/x-sh"),
    SVG("image/svg+xml"),
    SMALLWEBFORMAT("application/x-shockwave-flash"),
    TAR("application/x-tar"),
    TIFF("image/tiff"),
    FUENTETRUETYPE("font/ttf"),
    MICROSOFTVISIO("application/vnd.visio"),
    AUDIOWAV("audio/x-wav"),
    AUDIOWEBM("audio/webm"),
    VIDEOWEBM("video/webm"),
    IMAGENESWEBP("image/webp"),
    WOFF("font/woff"),
    WOFF2("font/woff2"),
    XHTML("application/xhtml+xml"),
    EXCEL("application/vnd.ms-excel"),
    XML("application/xml"),
    XUL("application/vnd.mozilla.xul+xml"),
    ZIP("application/zip"),
    AUDIOYVIDEO3GPP("video/3gpp"),
    AUDIO3GPP("audio/3gpp"),
    AUDIO3GPP2("video/3gpp2"),
    AUDIOYVIDEO3GPP2("video/3gpp2"),
    SEVENZIP("application/x-7z-compressed")

    ;

    private String contenttype;

    private contentType(String s) {
    }

    public String getContentType(){
        return contenttype;
    }
}
