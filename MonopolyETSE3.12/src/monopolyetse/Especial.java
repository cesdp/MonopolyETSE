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
public class Especial extends Casilla {
    
    private String tipo;
    private long valorIn;

    public Especial(String name, Tablero valTab,long valorIn,String tipo) {
        super(name, valTab);
        this.tipo=tipo;
        this.valorIn=valorIn;
    }

    public String getTipo() {
        return tipo;
    }

    public long getValorIn() {
        return valorIn;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValorIn(long valorIn) {
        this.valorIn = valorIn;
    }

    @Override
    public void ImprimeCasilla() {
        consola.imprimir("{");
        String jugadores;
        switch(tipo){
            case "carcel":
                consola.imprimir("  salir:" + valorIn);

                jugadores="  jugadores: ";
                for (int i = 1; i < super.getTablero().getJugadores().size(); i++) {
                    if (super.getTablero().getJugadores().get(i).getTurnosCarcel() != 0) {
                        jugadores+=String.format("[" + super.getTablero().getJugadores().get(i).getNombre() + "," + super.getTablero().getJugadores().get(i).getTurnosCarcel() + "]");
                    }
                }
                consola.imprimir(jugadores);
                break;
            case "salida":
                consola.imprimir("  valor a jugador: " + valorIn);
                break; 
            default:
                consola.imprimir("  La casilla no tiene descripcion");
                break;    
        }
        consola.imprimir("}");
    }
    
}
