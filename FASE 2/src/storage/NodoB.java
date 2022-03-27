/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import objects.Clients;

/**
 *
 * @author Alex Rose
 */
public class NodoB {

    //Valores
    Long id;
    Clients cliente;
    //Apuntadores
    NodoB siguiente;
    NodoB anterior;
    RamaB derecha;
    RamaB izquierda;

    public NodoB(Long id,Clients cliente) {
        this.id = id;
        this.anterior = null;
        this.siguiente = null;
        this.derecha = null;
        this.izquierda = null;
        this.cliente=cliente;
    }

}
