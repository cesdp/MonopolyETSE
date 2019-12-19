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
public final class Casa extends Edificio {
    
    public Casa(Tablero partida, Casilla casilla) {
        super(partida, casilla);
        this.setId("casa-"+this.getTablero().getContadorEdificacion());
        this.getTablero().setContadorEdificacion(this.getTablero().getContadorEdificacion() + 1);
    }

    @Override
    public void imprimirEdificio() {
        super.imprimirEdificio(); //To change body of generated methods, choose Tools | Templates.
        consola.imprimir("   coste:" + Math.round(((Propiedad)super.getUbicacion()).getValorIn() * 0.60));
        consola.imprimir("}");
    }
    
    
}
