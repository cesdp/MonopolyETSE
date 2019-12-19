/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import datosjugador.Jugador;
import java.util.Random;
import static monopolyetse.Juego.consola;

/**
 *
 * @author cesar
 */
public abstract class Carta {
   
    private Tablero tablero;
    private Jugador receptor;

    public Carta(Tablero tablero) {
        if(tablero==null){
            consola.imprimir("ERRO CARTA");
            System.exit(1); 
        }
        this.tablero=tablero;
        
    }
    public Tablero getTablero() {
        return tablero;
    }

    public Jugador getReceptor() {
        return receptor;
    }

    public void setTablero(Tablero tablero) {
        if(tablero!=null )
            this.tablero = tablero;
    }

    public void setReceptor(Jugador receptor) {
        this.receptor = receptor;
    }
    
    
    public abstract void accion();
    
   
    
}
