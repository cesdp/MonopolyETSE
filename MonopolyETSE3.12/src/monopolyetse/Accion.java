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
public class Accion extends Casilla{
    
    private String tipo;
    
    public Accion(String name, Tablero valTab) {
        super(name, valTab);
        if(name.equals("Parking")){
            this.tipo="parking";
        }
    }

    @Override
    public void ImprimeCasilla() {
        consola.imprimir("{");
        if(tipo!=null && tipo.equals("parking")){
            consola.imprimir("  bote: " + this.getTablero().getBote());
            consola.imprimir("  jugadores: " + this.getTablero().getListaJugadores());
            consola.imprimir("}");
        }
    }
    
}
