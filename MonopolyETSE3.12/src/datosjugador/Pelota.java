/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datosjugador;

import static monopolyetse.Juego.consola;
import monopolyetse.Tablero;
import static monopolyetse.Valores.PASOSALIDA;

/**
 *
 * @author cesdp
 */
public class Pelota extends Avatar{

    public Pelota(Jugador nombreJugador, Tablero partida) {
        super(nombreJugador, partida);
    }
    

    @Override
    public void moverEnAvanzado(int n) {
        int pos;
        if(this.getRepeticiones()==0){
            this.setRepeticiones(n);
        }
        if (this.getRepeticiones() > 4) {
            pos = (this.getTablero().PosicionCasilla(this.getUbicacion())+n) % 40;
            if (((this.getTablero().PosicionCasilla(this.getUbicacion()) + n) / 40) == 1) {
                this.getJugador().setFortuna(this.getJugador().getFortuna() + PASOSALIDA);
                this.getJugador().setVueltas(this.getJugador().getVueltas() + 1);
                this.getJugador().setPasarPorCasillaDeSalida(this.getJugador().getPasarPorCasillaDeSalida()+PASOSALIDA);
            }
            this.getUbicacion().setFrecuencia(this.getUbicacion().getFrecuencia()+1);
            this.setUbicacion(this.getTablero().casillaPosicion(pos));
            this.getJugador().setTirada(false);

        }
        if (this.getRepeticiones() <= 4) {
            pos = (this.getTablero().PosicionCasilla(this.getUbicacion()) - n);
            if (pos < 0) {
                pos = 40 + pos;
            }
            this.setUbicacion(this.getTablero().casillaPosicion(pos));
            this.getUbicacion().setFrecuencia(this.getUbicacion().getFrecuencia()+1);
            this.getJugador().setTirada(false);
        }
    }
    @Override
    public void imprimirAvatar() {
        consola.imprimir("{\n id:" + this.getId());
        consola.imprimir(" tipo: pelota"); 
        consola.imprimir(" casilla:" + this.getUbicacion().getNombre()); //String
        consola.imprimir(" jugador:" + this.getJugador().getNombre() + "\n}"); // String
    }
    
}
