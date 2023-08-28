package io.github.josecarlosbran.JBRestAPI;

/**
 * @author Jose Bran
 * Excepción que indica que un valor proporcionado es null o vacío
 * por lo cual el metodo no puede realizar una acción y en su lugar lanza la excepción
 */
public class ValorUndefined extends Exception {
    public ValorUndefined(String mensaje) {
        super(mensaje);
    }
}