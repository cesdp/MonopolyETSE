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
public class Coche extends Avatar {

    public Coche(Jugador nombreJugador, Tablero partida) {
        super(nombreJugador, partida);
    }

    @Override
    public void moverEnAvanzado(int n) {
        int pos;
        if (n > 4) {
            if (this.getRepeticiones() < 3) {
                this.setRepeticiones(this.getRepeticiones()+1);
                consola.imprimir("Puede seguir tirando los dados " + (4 - this.getRepeticiones()) + " veces más");
                pos = (this.getTablero().PosicionCasilla(this.getUbicacion()) + n) % 40;
                if (((this.getTablero().PosicionCasilla(this.getUbicacion()) + n) / 40) == 1) {
                    this.getJugador().setFortuna(this.getJugador().getFortuna() + PASOSALIDA);
                    this.getJugador().setVueltas(this.getJugador().getVueltas() + 1);
                    this.getJugador().setPasarPorCasillaDeSalida(this.getJugador().getPasarPorCasillaDeSalida()+PASOSALIDA);
                }
                this.setUbicacion(this.getTablero().casillaPosicion(pos));
                this.getUbicacion().setFrecuencia(this.getUbicacion().getFrecuencia()+1);
                this.getJugador().setTirada(false);
                } else {
                    this.setRepeticiones(this.getRepeticiones()+1);
                    pos = (this.getTablero().PosicionCasilla(this.getUbicacion()) + n) % 40;
                    if (((this.getTablero().PosicionCasilla(this.getUbicacion()) + n) / 40) == 1) {
                        this.getJugador().setFortuna(this.getJugador().getFortuna() + PASOSALIDA);
                        this.getJugador().setVueltas(this.getJugador().getVueltas() + 1);
                        this.getJugador().setPasarPorCasillaDeSalida(this.getJugador().getPasarPorCasillaDeSalida()+PASOSALIDA);
                    }
                    this.setUbicacion(this.getTablero().casillaPosicion(pos));
                    this.getUbicacion().setFrecuencia(this.getUbicacion().getFrecuencia()+1);
                    this.getJugador().setTirada(true);
                }
            } else {
                consola.imprimir("No puede tirar en los siguientes dos turnos");
                this.getJugador().setPenalizacion(2);
                pos = (this.getTablero().PosicionCasilla(this.getUbicacion()) - n);
                if (pos < 0) {
                    pos = 40 + pos;
                }
                this.setUbicacion(this.getTablero().casillaPosicion(pos));
                this.getUbicacion().setFrecuencia(this.getUbicacion().getFrecuencia()+1);
                this.setRepeticiones(4);
                this.getJugador().setTirada(true);
                //No podrá tirar en los dos siguientes turnos
            }
    }
    @Override
    public void imprimirAvatar() {
        consola.imprimir("{\n id:" + this.getId());
        consola.imprimir(" tipo: coche"); 
        consola.imprimir(" casilla:" + this.getUbicacion().getNombre()); //String
        consola.imprimir(" jugador:" + this.getJugador().getNombre() + "\n}"); // String
    }
    
}
