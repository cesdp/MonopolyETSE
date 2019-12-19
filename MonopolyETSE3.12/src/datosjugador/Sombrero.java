/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datosjugador;

import static monopolyetse.Juego.consola;
import monopolyetse.Tablero;

/**
 *
 * @author cesdp
 */
public class Sombrero extends Avatar {

    public Sombrero(Jugador nombreJugador, Tablero partida) {
        super(nombreJugador, partida);
    }

    @Override
    public void moverEnAvanzado(int n) {
        int pos = 0;
        if (n > 4) {
            this.setRepeticiones(this.getRepeticiones() + 1);
            for (int i = 1; i < this.getTablero().getJugadores().size(); i++) {
                this.getTablero().getJugadores().get(i).setFortunaPasada(this.getTablero().getJugadores().get(i).getFortuna());
            }
            consola.imprimir("Puede seguir tirando los dados " + (3 - this.getRepeticiones()) + " veces más");
            if ((this.getTablero().PosicionCasilla(this.getUbicacion()) <= 19 && this.getTablero().PosicionCasilla(this.getUbicacion()) >= 0)) {
                for (int i = 0; i < n; i++) {
                    if (this.getRepeticiones() <= 3) {
                        if (this.getTablero().PosicionCasilla(this.getUbicacion()) <= 9 && this.getTablero().PosicionCasilla(this.getUbicacion()) >= 0) {
                            pos = 11;
                        } else {
                            if (this.getTablero().PosicionCasilla(this.getUbicacion()) <= 19 && this.getTablero().PosicionCasilla(this.getUbicacion()) >= 10) {
                                pos = (49 - this.getTablero().PosicionCasilla(this.getUbicacion()));
                                if (this.getTablero().PosicionCasilla(this.getUbicacion()) == 19) {
                                    pos = 0;

                                }
                            }
                            if (this.getTablero().PosicionCasilla(this.getUbicacion()) <= 39 && this.getTablero().PosicionCasilla(this.getUbicacion()) >= 31) {
                                pos = (21 - (this.getTablero().PosicionCasilla(this.getUbicacion()) - 30));
                                if (this.getTablero().PosicionCasilla(this.getUbicacion()) == 31) {
                                    pos = 10;

                                }
                            }
                        }

                        this.setUbicacion(this.getTablero().casillaPosicion(pos));
                        this.getUbicacion().setFrecuencia(this.getUbicacion().getFrecuencia() + 1);
                        this.getJugador().setTirada(false);
                        this.getJugador().setCasillaPasada(null);
                        if (this.getRepeticiones() == 3) {
                            this.getJugador().setTirada(true);
                        }
                    }
                }
            } else if ((this.getTablero().PosicionCasilla(this.getUbicacion()) <= 39 && this.getTablero().PosicionCasilla(this.getUbicacion()) >= 20)) {
                for (int i = 0; i < n; i++) {
                    if (this.getRepeticiones() <= 3) {

                        if (this.getTablero().PosicionCasilla(this.getUbicacion()) <= 29 && this.getTablero().PosicionCasilla(this.getUbicacion()) >= 20) {
                            pos = 31;
                        } else {
                            if (this.getTablero().PosicionCasilla(this.getUbicacion()) <= 39 && this.getTablero().PosicionCasilla(this.getUbicacion()) >= 30) {
                                pos = (49 - this.getTablero().PosicionCasilla(this.getUbicacion()));
                                if (this.getTablero().PosicionCasilla(this.getUbicacion()) == 39) {
                                    pos = 20;

                                }
                            }
                            if (this.getTablero().PosicionCasilla(this.getUbicacion()) <= 19 && this.getTablero().PosicionCasilla(this.getUbicacion()) >= 10) {
                                pos = (39 - (this.getTablero().PosicionCasilla(this.getUbicacion()) - 12));
                                if (this.getTablero().PosicionCasilla(this.getUbicacion()) == 11) {
                                    pos = 30;

                                }
                            }
                        }

                        this.setUbicacion(this.getTablero().casillaPosicion(pos));
                        this.getUbicacion().setFrecuencia(this.getUbicacion().getFrecuencia() + 1);
                        this.getJugador().setTirada(false);
                        this.getJugador().setCasillaPasada(null);
                        if (this.getRepeticiones() == 3) {
                            this.getJugador().setTirada(true);
                        }
                    }
                }
            }

        } else {
            if (this.getJugador().getCasillaPasada() != null) {
                for (int i = 0; i < this.getJugador().getPropiedades().size(); i++) {
                    if (this.getJugador().getPropiedades().get(this.getJugador().getPropiedades().size() - 1 - i).getNombre().equals(this.getJugador().getCasillaPasada().getNombre())) {
                        this.getJugador().getPropiedades().get(this.getJugador().getPropiedades().size() - 1 - i).anhadirPropietario(this.getTablero().getJugadores().get(0));
                        this.getJugador().getPropiedades().remove(this.getJugador().getPropiedades().size() - 1 - i);

                    }
                }
                for (int j = 0; j < this.getJugador().getHipotecas().size(); j++) {
                    if (this.getJugador().getHipotecas().get(this.getJugador().getHipotecas().size() - 1 - j).getNombre().equals((this.getJugador().getCasillaPasada()).getNombre())) {
                        this.getJugador().getHipotecas().get(this.getJugador().getHipotecas().size() - 1 - j).anhadirPropietario(this.getTablero().getJugadores().get(0));
                        this.getJugador().getHipotecas().remove(this.getJugador().getHipotecas().size() - 1 - j);
                    }
                }
            }
            for (int i = 1; i < this.getTablero().getJugadores().size(); i++) {
                if (this.getTablero().getJugadores().get(i).getFortunaPasada() > 0) {
                    this.getTablero().getJugadores().get(i).setFortuna(this.getTablero().getJugadores().get(i).getFortunaPasada());
                }
            }
            this.getJugador().setPenalizacion(1);
            this.setRepeticiones(3);
            this.getJugador().setTirada(true);
        }

    }

    @Override
    public void imprimirAvatar() {
        consola.imprimir("{\n id:" + this.getId());
        consola.imprimir(" tipo: sombrero");
        consola.imprimir(" casilla:" + this.getUbicacion().getNombre()); //String
        consola.imprimir(" jugador:" + this.getJugador().getNombre() + "\n}"); // String
    }

}
