
package storage;

import objects.Clients;


public class NodoB {

   //Valores
    Long id;
    public Clients cliente;

    NodoB siguiente;
    NodoB anterior;
    RamaB derecha;
    RamaB izquierda;
    RamaB padre;

    public NodoB(Long id,Clients cliente) {
        this.id = id;
        this.anterior = null;
        this.siguiente = null;
        this.derecha = null;
        this.izquierda = null;
        this.cliente=cliente;
    }

}
