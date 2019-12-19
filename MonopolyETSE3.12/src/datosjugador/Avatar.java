/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datosjugador;

import java.util.ArrayList;
import java.util.Random;
import monopolyetse.Casilla;
import monopolyetse.Tablero;
import static monopolyetse.Valores.PASOSALIDA;

/**
 *
 * @author cesar
 */
public abstract class Avatar {

    private char id;
    private Casilla ubicacion;
    private String modo;
    private Jugador jugador;
    private Tablero tablero;
    private int repeticiones;

    public Avatar(Jugador nombreJugador, Tablero partida) {
        if (nombreJugador == null || partida == null) {
            System.exit(1);
        }
        Random identif = new Random();
        int n;

        n = identif.nextInt(90 - 65 + 1) + 65;
        id = (char) n;
        while (partida.getListaAvatares().contains(Character.toString(id))) {
            n = identif.nextInt(90 - 65 + 1) + 65;
            id = (char) n;
        }
        ubicacion = partida.casillaPosicion(0);
        jugador = nombreJugador;
        tablero = partida;
        repeticiones = 0;
        modo="normal";
    }

    public char getId() {
        return id;
    }
    public Casilla getUbicacion() {
        return ubicacion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Tablero getTablero() {
        return tablero;
    }
    public int getRepeticiones() {
        return repeticiones;
    }
    
    public String getModo() {
        return modo;
    }
    public void setId(char valorId) {
        id = valorId;
    }

    public void setUbicacion(Casilla valorUbicacion) {
        if (valorUbicacion != null && tablero.datosCasillas().containsKey(valorUbicacion.getNombre())) {
            ubicacion = valorUbicacion;
        }
    }

    public void setJugador(Jugador valorJugador) {
        if (valorJugador != null) {
            jugador = valorJugador;
        }
    }

    public void setTablero(Tablero valorTablero) {
        if (valorTablero != null) {
            tablero = valorTablero;
        }
    }
    
    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public void moverEnBasico(int n){
        int pos;
        pos = (tablero.PosicionCasilla(ubicacion) + n) % 40;
            if (((tablero.PosicionCasilla(ubicacion) + n) / 40) == 1) {
                jugador.setFortuna(jugador.getFortuna() + PASOSALIDA);
                jugador.setVueltas(jugador.getVueltas() + 1);
                jugador.setPasarPorCasillaDeSalida(jugador.getPasarPorCasillaDeSalida()+PASOSALIDA);
            }
            ubicacion = tablero.casillaPosicion(pos);
            ubicacion.setFrecuencia(ubicacion.getFrecuencia()+1);
    }
    public abstract void moverEnAvanzado(int n);
    
    
    public abstract void imprimirAvatar();

}
