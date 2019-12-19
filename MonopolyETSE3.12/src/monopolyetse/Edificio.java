/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import java.util.Random;
import static monopolyetse.Juego.consola;

/**
 *
 * @author cesar
 */
public class Edificio {

    private String id;
    private Casilla ubicacion;
    private Tablero tablero;

    public Edificio(Tablero partida, Casilla casilla) {
        ubicacion = casilla;
        tablero = partida;
    }

    public void setTablero(Tablero tablero) {
        if (tablero != null) {
            this.tablero = tablero;
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public String getId() {
        return id;
    }
    public Casilla getUbicacion() {
        return ubicacion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUbicacion(Casilla ubicacion) {
        if (ubicacion != null && tablero.datosCasillas().containsKey(ubicacion.getNombre())) {
            this.ubicacion = ubicacion;
        }
    }

    public void imprimirEdificio() {
        consola.imprimir("{");
        consola.imprimir("   id:"+id);
        consola.imprimir("   propietario:"+((Propiedad)ubicacion).getPropietario().getNombre());
        consola.imprimir("   casilla:"+ubicacion.getNombre());
        consola.imprimir("   grupo:"+((Solar)ubicacion).getGrupo().getId());
    }

}
