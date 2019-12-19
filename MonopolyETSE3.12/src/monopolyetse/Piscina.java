/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import static monopolyetse.Juego.consola;

/**
 *
 * @author cesdp
 */
public final class Piscina extends Edificio {
    
    public Piscina(Tablero partida, Casilla casilla) {
       super(partida, casilla);
        this.setId("piscina-"+this.getTablero().getContadorEdificacion());
        this.getTablero().setContadorEdificacion(this.getTablero().getContadorEdificacion() + 1);
    }

    @Override
    public void imprimirEdificio() {
        super.imprimirEdificio(); 
        consola.imprimir("   coste:" + Math.round(((Propiedad)super.getUbicacion()).getValorIn() * 0.40));
        consola.imprimir("}");
    }
    
}
