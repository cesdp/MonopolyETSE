/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import datosjugador.Jugador;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author cesar
 */
public class Ejecucion {

    private Jugador jugador;
    private Casilla casilla;
    private Tablero tablero;

    public Ejecucion(Tablero tableroactual) {
        casilla = null;
        jugador = null;
        tablero = tableroactual;

    }

    public Casilla getCasilla() {
        return casilla;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setNombre(Jugador player) {
        jugador = player;
    }

    public void setCasilla(Casilla tile) {
        casilla = tile;
    }

    public void setTablero(Tablero valorTablero) {
        tablero = valorTablero;
    }

    public void realizarAccion(Casilla casillaactual, Jugador jugadoractual, Tablero tableroactual, Dado dado) {
        int coste = 0;
        int casillaprotegida=0;
        Consola consola = Juego.consola;
        casilla = casillaactual;
        jugador = jugadoractual;
        tablero = tableroactual;
        if (casilla instanceof Solar && ((Propiedad) casilla).getPropietario().getNombre().equals(jugador.getNombre()) && ((Solar) casilla).getPaso() < 2) {
            ((Solar) casilla).setPaso(((Solar) casilla).getPaso() + 1);
            if (((Solar) casilla).getPaso() >= 2) {
                ((Solar) casilla).setEdificar(true);
            }
        }
        for (int j = 0; j < jugador.getListaBlanca().size(); j++) {
                if (casilla.getNombre().equals(jugador.getListaBlanca().get(j).getNombre())) {
                    casillaprotegida=1;
            }
        }
        if (casilla instanceof Solar) {
            if ((((Propiedad) casilla).getPropietario().getNombre()).equals("Banca")) {
                consola.imprimir("Esta propiedad está disponible para su compra. Coste: " + (((Propiedad) casilla).getValorAct()));
            } else if (((Propiedad) casilla).getPropietario().getNombre().equals(jugador.getNombre())) {
                consola.imprimir("Esta casilla es de su propiedad");
            } else if(casillaprotegida!=1){

                consola.imprimir("Se han pagado " + ((Solar) casilla).alquiler(dado) + "€ de alquiler");

                if (jugador.getFortuna() - ((Solar) casilla).alquiler(dado) < 0) {
                    ((Propiedad) casilla).getPropietario().setFortuna(((Propiedad) casilla).getPropietario().getFortuna() + jugador.getFortuna());
                    jugador.setFortuna(0);
                    ((Propiedad) casilla).setRentabilidad(((Propiedad) casilla).getRentabilidad() + ((Solar) casilla).alquiler(dado));
                    consola.imprimir("El jugador " + jugador.getNombre() + " ha entrado en bancarrota. No puede seguir jugando");
                    for (Propiedad k : jugador.getPropiedades()) {
                        ((Propiedad) casilla).getPropietario().anhadirPropiedades(k);
                        jugador.getPropiedades().remove(k);
                    }
                } else {

                    ((Propiedad) casilla).getPropietario().setFortuna(((Propiedad) casilla).getPropietario().getFortuna() + ((Solar) casilla).alquiler(dado));
                    jugador.setFortuna(jugador.getFortuna() - ((Solar) casilla).alquiler(dado));
                    jugador.setPagoDeAlquileres(jugador.getPagoDeAlquileres() + ((Solar) casilla).alquiler(dado));
                    ((Propiedad) casilla).getPropietario().setCobroDeAlquileres(((Propiedad) casilla).getPropietario().getCobroDeAlquileres() + ((Solar) casilla).alquiler(dado));
                    ((Propiedad) casilla).setRentabilidad(((Propiedad) casilla).getRentabilidad() + ((Solar) casilla).alquiler(dado));
                }

                consola.imprimir("Fortuna actual: " + jugador.getFortuna());
            }
        }

        //En el caso de que no tenga el dinero suficiente dinero que pagar, se le da al propietario el dinero que tenga aunque no cubra el dinero total que le tendría que dar
        if (casilla instanceof Servicio) {
            if ((((Propiedad) casilla).getPropietario().getNombre()).equals("Banca")) {
                consola.imprimir("Esta propiedad está disponible para su compra. Coste: " + ((Propiedad) casilla).getValorAct());
            } else if (((Propiedad) casilla).getPropietario().getNombre().equals(jugador.getNombre())) {
                consola.imprimir("Esta casilla es de su propiedad");
            } else if(casillaprotegida!=1){

                consola.imprimir("Se han pagado " + ((Servicio) casilla).alquiler(dado) + "€ de alquiler");
                if (jugador.getFortuna() - ((Servicio) casilla).alquiler(dado) < 0) {
                    ((Propiedad) casilla).getPropietario().setFortuna(((Propiedad) casilla).getPropietario().getFortuna() + jugador.getFortuna());
                    jugador.setFortuna(0);
                    ((Propiedad) casilla).setRentabilidad(((Propiedad) casilla).getRentabilidad() + ((Servicio) casilla).alquiler(dado));
                    consola.imprimir("El jugador " + jugador.getNombre() + " ha entrado en bancarrota. No puede seguir jugando");
                    for (Propiedad k : jugador.getPropiedades()) {
                        ((Propiedad) casilla).getPropietario().anhadirPropiedades(k);
                        jugador.getPropiedades().remove(k);
                    }
                } else {

                    ((Propiedad) casilla).getPropietario().setFortuna(((Propiedad) casilla).getPropietario().getFortuna() + ((Servicio) casilla).alquiler(dado));
                    jugador.setFortuna(jugador.getFortuna() - ((Servicio) casilla).alquiler(dado));
                    jugador.setPagoDeAlquileres(jugador.getPagoDeAlquileres() + ((Servicio) casilla).alquiler(dado));
                    ((Propiedad) casilla).getPropietario().setCobroDeAlquileres(((Propiedad) casilla).getPropietario().getCobroDeAlquileres() + ((Servicio) casilla).alquiler(dado));
                    ((Propiedad) casilla).setRentabilidad(((Propiedad) casilla).getRentabilidad() + ((Servicio) casilla).alquiler(dado));
                }
                consola.imprimir("Fortuna actual: " + jugador.getFortuna());
            }
        }
        if (casilla instanceof Accion && !(casilla instanceof Suerte) && !(casilla instanceof CajaComunidad)) {
            consola.imprimir("El jugador " + jugador.getNombre() + " recibe " + tablero.getBote() + "€,el bote de la banca");
            jugador.setFortuna(jugador.getFortuna() + tablero.getBote());
            jugador.setPremiosInversionesOBote(jugador.getPremiosInversionesOBote() + tablero.getBote());
            tablero.SetBote(0);
            consola.imprimir("Fortuna actual: " + jugador.getFortuna());
        }

        if (casilla instanceof Transporte) {
            if ((((Propiedad) casilla).getPropietario().getNombre()).equals("Banca")) {
                consola.imprimir("Esta propiedad está disponible para su compra. Coste: " + ((Propiedad) casilla).getValorAct() + "€");
            } else if (((Propiedad) casilla).getPropietario().getNombre().equals(jugador.getNombre())) {
                consola.imprimir("Esta casilla es de su propiedad");
            } else if(casillaprotegida!=1){

                consola.imprimir("Se han pagado " + ((Transporte) casilla).alquiler(dado) + "€ de alquiler");
                if (jugador.getFortuna() - ((Transporte) casilla).alquiler(dado) < 0) {
                    ((Propiedad) casilla).getPropietario().setFortuna(((Propiedad) casilla).getPropietario().getFortuna() + jugador.getFortuna());
                    jugador.setFortuna(0);
                    ((Propiedad) casilla).setRentabilidad(((Propiedad) casilla).getRentabilidad() + ((Transporte) casilla).alquiler(dado));
                    consola.imprimir("El jugador " + jugador.getNombre() + " ha entrado en bancarrota. No puede seguir jugando");
                    for (Propiedad k : jugador.getPropiedades()) {
                        ((Propiedad) casilla).getPropietario().anhadirPropiedades(k);
                        jugador.getPropiedades().remove(k);
                    }
                } else {

                    ((Propiedad) casilla).getPropietario().setFortuna(((Propiedad) casilla).getPropietario().getFortuna() + ((Transporte) casilla).alquiler(dado));
                    jugador.setFortuna(jugador.getFortuna() - ((Transporte) casilla).alquiler(dado));
                    jugador.setPagoDeAlquileres(jugador.getPagoDeAlquileres() + ((Transporte) casilla).alquiler(dado));
                    ((Propiedad) casilla).getPropietario().setCobroDeAlquileres(((Propiedad) casilla).getPropietario().getCobroDeAlquileres() + ((Transporte) casilla).alquiler(dado));
                    ((Propiedad) casilla).setRentabilidad(((Propiedad) casilla).getRentabilidad() + ((Transporte) casilla).alquiler(dado));
                }
                consola.imprimir("Fortuna actual: " + jugador.getFortuna());

            }
        }
        if (casilla instanceof Especial) {
            if (((Especial) casilla).getTipo().equals("ircarcel")) {
                consola.imprimir("El avatar se coloca en la casilla de carcel");
                jugador.getAvatar().setUbicacion(tablero.casillaPosicion(10));
                tablero.casillaPosicion(10).setFrecuencia(tablero.casillaPosicion(10).getFrecuencia() + 1);
                jugador.setTurnosCarcel(1);
                jugador.setVecesEnLaCarcel(jugador.getVecesEnLaCarcel() + 1);
            }

            if (((Especial) casilla).getTipo().equals("carcel")) {
                if (jugador.getTurnosCarcel() < 4 && jugador.getTurnosCarcel() > 0) {
                    consola.imprimir("Puede intentar sacar dados dobles en el siguiente turno o pagar la cantidad de " + ((Especial) casilla).getValorIn() + "€");

                }
                if (jugador.getTurnosCarcel() == 4) {
                    consola.imprimir("En el siguiente turno deberá pagar la cantidad de " + ((Especial) casilla).getValorIn() + "€");

                }
                if (jugador.getTurnosCarcel() > 4) {
                    consola.imprimir("Solo le queda la opción de pagar la cantidad de " + ((Especial) casilla).getValorIn() + "€");
                    jugador.setTirada(false);
                    if ((jugador.getFortuna() - ((Especial) casilla).getValorIn() < 0)) {
                        jugador.setTurnosCarcel(0);
                        consola.imprimir("El jugador entra en bancarrota");
                        for (Propiedad i : jugador.getPropiedades()) {
                            tablero.getJugadores().get(0).anhadirPropiedades(i);
                            jugador.getPropiedades().remove(i);
                        }
                    }
                }
            }
        }
        if (casilla instanceof Impuesto) {
            consola.imprimir("El jugador paga: " + ((Impuesto) casilla).getValorIn() + "€");
            if (jugador.getFortuna() - ((Impuesto) casilla).getValorIn() < 0) {
                tablero.SetBote(tablero.getBote() + jugador.getFortuna());
                jugador.setFortuna(0);
                consola.imprimir("El jugador " + jugador.getNombre() + " ha entrado en bancarrota. No puede seguir jugando");
                for (Propiedad k : jugador.getPropiedades()) {
                    tablero.getJugadores().get(0).anhadirPropiedades(k);
                    jugador.getPropiedades().remove(k);
                }
            } else {
                tablero.SetBote(tablero.getBote() + ((Impuesto) casilla).getValorIn());
                jugador.setFortuna(jugador.getFortuna() - ((Impuesto) casilla).getValorIn());
                jugador.setPagoTasasEImpuestos(jugador.getPagoTasasEImpuestos() + ((Impuesto) casilla).getValorIn());
            }
            consola.imprimir("Fortuna actual: " + jugador.getFortuna());
        }

        if (casilla instanceof Suerte || casilla instanceof CajaComunidad) {
            int n;

            
            String orden = consola.leer("Que carta quiere ");
            if (casilla instanceof Suerte) {
                n = Integer.parseInt(orden);
                tablero.Barajar(tablero.getSuerte());
                ((CartaSuerte) tablero.getSuerte().get(n - 1)).setReceptor(jugador);
                ((CartaSuerte) tablero.getSuerte().get(n - 1)).accion();

            } else {
                n = Integer.parseInt(orden);
                tablero.Barajar(tablero.getComunidad());
                ((CartaCajaComunidad) tablero.getComunidad().get(n - 1)).setReceptor(jugador);
                ((CartaCajaComunidad) tablero.getComunidad().get(n - 1)).accion();

            }

        }
    }

}
