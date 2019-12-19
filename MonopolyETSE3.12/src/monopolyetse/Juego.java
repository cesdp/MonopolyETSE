/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import datosjugador.Coche;
import datosjugador.Esfinge;
import datosjugador.Jugador;
import datosjugador.Pelota;
import datosjugador.Sombrero;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author cesar
 */
public class Juego implements Comandos {

    private Tablero tablero;
    private int turno;
    private int actual;
    private int pelotalanzar;
    private int contadoraccion;
    private HashMap<String, Casilla> datos;
    private Casilla casillaMasRentable;
    private Grupo grupoMasRentable;
    private Casilla casillaMasFrecuentada;
    private Jugador jugadorMasVueltas;
    private Jugador jugadorMasVecesDados;
    private Jugador jugadorEnCabeza;
    public static Consola consola;

    public Juego() {
        boolean salir = false;
        int pos;
        actual = 1;
        consola = new ConsolaNormal();
        boolean incremento = true;
        contadoraccion = 0;
        pelotalanzar = 0;
        tablero = new Tablero();
        datos = tablero.datosCasillas();
        consola.imprimir("Bienvenido al maravillo juego del Monopoly");
        consola.imprimir("Que desea hacer?");
        turno = 0;
        for (int i = 0; i < 40; i++) {
            if (tablero.casillaPosicion(i) instanceof Solar) {
                ((Solar) tablero.casillaPosicion(i)).getGrupo().anhadirCasillas((Solar) tablero.casillaPosicion(i));
            }
        }

        while (!salir) {
            if (tablero.getJugadores().size() >= 3) {
                incremento = true;
                for (int i = 1; i < tablero.getJugadores().size(); i++) {
                    incremento = incremento && (tablero.getJugadores().get(i).getVueltas() == 4);
                }
                if (incremento == true) {
                    for (int i = 0; i < 40; i++) {
                        if (tablero.casillaPosicion(i) instanceof Solar) {
                            ((Solar) tablero.casillaPosicion(i)).setValorAct(Math.round(((Solar) tablero.casillaPosicion(i)).getValorAct() * 1.05));
                        }
                    }
                    for (int i = 1; i < tablero.getJugadores().size(); i++) {
                        tablero.getJugadores().get(i).setVueltas(0);
                    }
                }

            }

            String[] partes = consola.leer("$> ").split(" ");
            String comando = partes[0];
            try {
                switch (comando) {
                    case "crear":
                        crear(partes);
                        break;
                    case "jugador":
                        jugador(partes);
                        break;
                    case "listar":
                        listar(partes);
                        break;
                    case "edificar":
                        edificar(partes);
                        break;
                    case "vender":
                        vender(partes);
                        break;
                    case "lanzar":
                        lanzar(partes);
                        break;
                    case "acabar":
                        acabar(partes);
                        break;
                    case "salir":
                        salir(partes);
                        break;
                    case "describir":
                        describir(partes);
                        break;
                    case "comprar":
                        comprar(partes);
                        break;
                    case "hipotecar":
                        hipotecar(partes);
                        break;
                    case "deshipotecar":
                        deshipotecar(partes);
                        break;
                    case "cambiar":
                        cambiar(partes);
                        break;
                    case "estadisticas":
                        estadistica(partes);
                        break;
                    case "ver":
                        ver(partes);
                        break;
                    case "aceptar":
                        aceptar(partes);
                        break;
                    case "eliminar":
                        eliminar(partes);
                        break;
                    case "trato":
                        trato(partes);
                    case "set":
                        set(partes);
                        break;
                    case "finalizar":
                        salir = true;
                        break;
                    default:
                        consola.imprimir("Comando incorrecto");
                        break;
                }
            } catch (Exceptioncreacion a) {
                consola.imprimir(a.getMessage());
            } catch (Exceptionaccion b) {
                consola.imprimir(b.getMessage());
            }
        }
    }

    public void crear(String[] partes) throws Exceptioncreacion, Exceptioncreacionjugador, Exceptioncreacionavatar {

        if (partes.length > 1 && "jugador".equals(partes[1])) {
            if (partes.length > 2) {
                if (partes.length > 3 && (partes[3].equals("esfinge") || partes[3].equals("coche") || partes[3].equals("sombrero") || partes[3].equals("pelota")) && (!tablero.getListaJugadores().contains(partes[2])) && turno == 0 && (tablero.getJugadores().size() < 7)) {
                    Jugador j = new Jugador(partes[2], partes[3], tablero);
                    tablero.anhadirJugador(j);
                    consola.imprimir("{");
                    consola.imprimir("  Nombre: " + j.getNombre());
                    consola.imprimir("  Avatar: " + j.getAvatar().getId());
                    consola.imprimir("}");
                    tablero.verTablero();
                } else if ((tablero.getJugadores().size() == 7)) {
                    throw new Exceptioncreacion("Ha alcanzado el numero máximo de jugadores, no puede añadir mas");
                } else if (partes.length > 3 && turno != 0) {
                    consola.imprimir("No se puede añadir jugadores ahora");
                } else if (partes.length > 3 && turno == 0 && tablero.getListaJugadores().contains(partes[2])) {
                    throw new Exceptioncreacionjugador("El jugador ya existe");
                } else {
                    throw new Exceptioncreacionavatar("Figura seleccionada para el avatar incorrecta");
                }

            } else {
                consola.imprimir("Comando incorrecto");
            }
        } else {
            consola.imprimir("Comando incorrecto");
        }

    }

    public void jugador(String[] partes) {
        if (tablero.getJugadores().size() > 1) {
            tablero.getJugadores().get(actual).imprimirJugador();
        } else if (!tablero.getListaJugadores().contains(partes[1])) { //Metodo que con el nombre devuelve el jugador HAshMap
            consola.imprimir("El jugador no existe");
        } else { //Modificar para cualquier valor
            consola.imprimir("Comando incorrecto");
        }
    }

    public void listar(String[] partes) {
        if (partes.length == 3 && "edificios".equals(partes[1])) {
            tablero.listarEdificiosGrupo(partes[2]);
        } else {
            if (partes.length < 2) {
                consola.imprimir("Comando incorrecto");
            } else if ("jugadores".equals(partes[1])) {
                for (int i = 1; i < tablero.getJugadores().size(); i++) {
                    tablero.getJugadores().get(i).imprimirJugador();
                }
            } else if ("avatares".equals(partes[1])) {
                for (int i = 0; i < tablero.getAvatares().size(); i++) {
                    tablero.getAvatares().get(i).imprimirAvatar();
                }
            } else if ("edificios".equals(partes[1])) {
                tablero.listarEdificios();
            } else if ("enventa".equals(partes[1])) {
                tablero.enVenta();
            } else if ("tratos".equals(partes[1])) {
                tablero.listarTratos(tablero.getJugadores().get(actual));
            } else {
                consola.imprimir("El jugador no existe");

            }
        }
    }

    public void edificar(String[] partes) throws Exceptionaccionpropiedadedificios {
        long valor = 0;
        int p,
                contador = 0,
                contadorcasa = 0,
                ncasas = 0,
                nhoteles = 0;
        if (partes.length < 2) {
            consola.imprimir("Comando incorrecto");
        } else if ("casa".equals(partes[1]) || "hotel".equals(partes[1]) || "piscina".equals(partes[1]) || "pistaDeporte".equals(partes[1])) {
            if (((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getPropietario().getNombre().equals(tablero.getJugadores().get(actual).getNombre())
                    && ((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).isEdificar()
                    && ((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()) instanceof Solar && (!((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getGrupo().isHipotecada()) && (!((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).isHipotecada())) {
                for (p = 0; p < ((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getGrupo().getEdificios().size(); p++) {
                    if (((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getGrupo().getEdificios().get(p) instanceof PistaDeporte && partes[1].equals("pistaDeporte")) {
                        contador++;
                    }
                    if (((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getGrupo().getEdificios().get(p) instanceof Piscina && partes[1].equals("piscina")) {
                        contador++;
                    }
                    if (((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getGrupo().getEdificios().get(p) instanceof Casa && partes[1].equals("casa")) {
                        contadorcasa++;
                    }
                    if (((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getGrupo().getEdificios().get(p) instanceof Hotel && partes[1].equals("hotel")) {
                        contador++;
                        contadorcasa = contadorcasa + 4;
                    }
                }
                if ((((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getGrupo().getId().equals("marron") || ((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getGrupo().getId().equals("azul")) && (contador >= 2 || contadorcasa >= 10)) {
                    consola.imprimir("No se pueden construir mas edificaciones");
                } else if (contador >= 3 || contadorcasa >= 15) {
                    consola.imprimir("No se pueden construir mas edificaciones");

                } else {
                    ncasas = 0;
                    nhoteles = 0;
                    for (int i = 0; i < ((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getEdificios().size(); i++) {
                        if (((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getEdificios().get(i) instanceof Casa) {
                            ncasas++;
                        }
                        if (((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getEdificios().get(i) instanceof Hotel) {
                            nhoteles++;
                        }
                    }
                    switch (partes[1]) {
                        case ("casa"):
                            valor = Math.round(((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getValorIn() * 0.60);
                            break;
                        case ("hotel"):
                            if (ncasas < 4) {
                                throw new Exceptionaccionpropiedadedificios("No hay suficientes casas");
                            }
                            valor = Math.round(((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getValorIn() * 0.60);
                            break;
                        case ("piscina"):
                            if (ncasas < 2 || nhoteles < 1) {
                                throw new Exceptionaccionpropiedadedificios("No hay suficientes casas y hoteles");
                            }
                            valor = Math.round(((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getValorIn() * 0.40);
                            break;
                        case ("pistaDeporte"):
                            if (nhoteles < 2) {
                                throw new Exceptionaccionpropiedadedificios("No hay suficientes hoteles");
                            }
                            valor = Math.round(((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getValorIn() * 1.25);
                            break;
                    }
                    if (valor > 0) {
                        if (tablero.getJugadores().get(actual).getFortuna() - valor < 0) {
                            consola.imprimir("No tiene suficiente dinero");
                        } else {
                            tablero.getJugadores().get(actual).setFortuna(tablero.getJugadores().get(actual).getFortuna() - valor);
                            tablero.getJugadores().get(actual).setDineroInvertido(tablero.getJugadores().get(actual).getDineroInvertido() + valor);
                            ((Solar) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).Edificar(partes[1]);
                            consola.imprimir(tablero.getJugadores().get(actual).getNombre() + "edifica 1 " + partes[1] + " en " + ((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getNombre() + ". El jugador ha pagado: " + valor + "€");
                            tablero.getJugadores().get(actual).imprimirJugador();

                        }
                    }
                }
            } else {
                consola.imprimir("No puede edificar aqui");
            }
        } else {
            consola.imprimir("No es un edificio valido");

        }
    }

    public void vender(String[] partes) {
        int nedificios = 0, npiscinas = 0, npistas = 0,
                ncasas = 0,
                nhoteles = 0;
        long valorventa = 0;
        if (partes.length < 4) {
            consola.imprimir("Comando incorrecto");
        } else if (("casa".equals(partes[1]) || "hotel".equals(partes[1]) || "piscina".equals(partes[1]) || "pistaDeporte".equals(partes[1])) && Integer.parseInt(partes[3]) > 0 && ((Solar) tablero.datosCasillas().get(partes[2])).getPropietario().getNombre().equals(tablero.getJugadores().get(actual).getNombre()) && (!tablero.getJugadores().get(actual).getHipotecas().contains(((Solar) tablero.datosCasillas().get(partes[2]))))) {
            for (int i = 0; i < ((Solar) tablero.datosCasillas().get(partes[2])).getEdificios().size(); i++) {
                if (((Solar) tablero.datosCasillas().get(partes[2])).getGrupo().getEdificios().get(i) instanceof Casa) {
                    ncasas++;
                } else if (((Solar) tablero.datosCasillas().get(partes[2])).getGrupo().getEdificios().get(i) instanceof Hotel) {
                    nhoteles++;
                } else if (((Solar) tablero.datosCasillas().get(partes[2])).getGrupo().getEdificios().get(i) instanceof Piscina) {
                    npiscinas++;
                } else if (((Solar) tablero.datosCasillas().get(partes[2])).getGrupo().getEdificios().get(i) instanceof PistaDeporte) {
                    npistas++;
                }

            }

            if ("casa".equals(partes[1]) && ncasas < Integer.parseInt(partes[3])) {
                consola.imprimir("No hay suficientes casas");
            } else if ("hotel".equals(partes[1]) && nhoteles < Integer.parseInt(partes[3])) {
                consola.imprimir("No hay suficientes hoteles");
            } else if ("piscina".equals(partes[1]) && npiscinas < Integer.parseInt(partes[3])) {
                consola.imprimir("No hay suficientes piscinas");
            } else if ("pistaDeporte".equals(partes[1]) && npistas < Integer.parseInt(partes[3])) {
                consola.imprimir("No hay suficientes pistas de deporte");
            } else {
                ((Solar) tablero.datosCasillas().get(partes[2])).EliminarEdificios(partes[1], Integer.parseInt(partes[3]));
                switch (partes[1]) {
                    case ("casa"):
                        valorventa = Math.round((((Solar) tablero.datosCasillas().get(partes[2])).getValorIn() * 0.60) / 2);
                        tablero.getJugadores().get(actual).setFortuna(tablero.getJugadores().get(actual).getFortuna() + valorventa);
                        break;
                    case ("hotel"):
                        valorventa = Math.round((((Solar) tablero.datosCasillas().get(partes[2])).getValorIn() * 0.60) / 2);
                        tablero.getJugadores().get(actual).setFortuna(tablero.getJugadores().get(actual).getFortuna() + valorventa);
                        break;
                    case ("piscina"):
                        valorventa = Math.round((((Solar) tablero.datosCasillas().get(partes[2])).getValorIn() * 0.40) / 2);
                        tablero.getJugadores().get(actual).setFortuna(tablero.getJugadores().get(actual).getFortuna() + valorventa);
                        break;
                    case ("pistaDeporte"):
                        valorventa = Math.round((((Solar) tablero.datosCasillas().get(partes[2])).getValorIn() * 1.25) / 2);
                        tablero.getJugadores().get(actual).setFortuna(tablero.getJugadores().get(actual).getFortuna() + valorventa);
                        break;
                }
                consola.imprimir(tablero.getJugadores().get(actual).getNombre() + " vende " + partes[3] + " " + partes[1] + " en " + partes[2] + ". Ha recibido: " + valorventa + "€");
                tablero.getJugadores().get(actual).imprimirJugador();
            }

        } else {
            consola.imprimir("No puede vender edificios en esta casilla");
        }
    }

    public void lanzar(String[] partes) throws Exceptionaccion, Exceptionacciondados {
        Dado dado = new Dado();
        Ejecucion accion = new Ejecucion(tablero);
        if (partes.length < 2 || !("dados".equals(partes[1]))) {
            consola.imprimir("Comando incorrecto");
        } else if (tablero.getJugadores().size() >= 3) {
            try {
                if (turno == 0) {
                    turno = 1;
                }
                //Lanzar dados de jugador;

                if (tablero.getJugadores().get(actual).getAvatar().getModo().equals("avanzado")) {
                    if (tablero.getJugadores().get(actual).getAvatar() instanceof Pelota) {
                        if (tablero.getJugadores().get(actual).getAvatar().getRepeticiones() == 0) {
                            tablero.getJugadores().get(actual).getAvatar().setRepeticiones(dado.Tirada());
                            tablero.getJugadores().get(actual).setTiradasDados(tablero.getJugadores().get(actual).getTiradasDados() + 1);
                            consola.imprimir("Ha sacado un:" + tablero.getJugadores().get(actual).getAvatar().getRepeticiones());
                            if (tablero.getJugadores().get(actual).getAvatar().getRepeticiones() > 4) {
                                pelotalanzar = 5;
                                tablero.getJugadores().get(actual).lanzarDados((pelotalanzar), 0);
                            }
                            if (tablero.getJugadores().get(actual).getAvatar().getRepeticiones() <= 4) {
                                pelotalanzar = 1;
                                tablero.getJugadores().get(actual).lanzarDados((pelotalanzar), 0);
                            }
                        } else {
                            tablero.getJugadores().get(actual).lanzarDados((pelotalanzar % 2) + 1, 0);
                        }
                        tablero.getJugadores().get(actual).setTirada(false);
                        if (contadoraccion == 0) {
                            accion.realizarAccion((tablero.getJugadores().get(actual).getAvatar().getUbicacion()), tablero.getJugadores().get(actual), tablero, dado);
                        }

                        if (tablero.getJugadores().get(actual).getAvatar().getRepeticiones() > pelotalanzar) {
                            pelotalanzar = pelotalanzar + 2;
                            if (tablero.getJugadores().get(actual).getAvatar().getRepeticiones() < pelotalanzar) {
                                pelotalanzar = pelotalanzar - 1;
                            }
                        } else {
                            tablero.getJugadores().get(actual).setTirada(true);
                            contadoraccion = 1;
                        }
                    }

                    if (tablero.getJugadores().get(actual).getAvatar() instanceof Coche) {
                        tablero.getJugadores().get(actual).lanzarDados(dado);
                        if (contadoraccion == 0) {
                            if (tablero.getJugadores().get(actual).getPenalizacion() > 0) {
                                consola.imprimir("Le quedan aun " + tablero.getJugadores().get(actual).getPenalizacion() + " para poder tirar");
                                tablero.getJugadores().get(actual).setTirada(true);
                            } else {
                                accion.realizarAccion((tablero.getJugadores().get(actual).getAvatar().getUbicacion()), tablero.getJugadores().get(actual), tablero, dado);
                                if (tablero.getJugadores().get(actual).getAvatar().getRepeticiones() >= 4) {
                                    contadoraccion = 1;
                                }
                            }
                        }
                    }
                    if (tablero.getJugadores().get(actual).getAvatar() instanceof Esfinge) {
                        tablero.getJugadores().get(actual).lanzarDados(dado);
                        if (contadoraccion == 0) {
                            if (tablero.getJugadores().get(actual).getPenalizacion() > 0) {
                                consola.imprimir("Saco menos de un 5, no puede tirar mas");
                                tablero.getJugadores().get(actual).setTirada(true);
                            } else {
                                accion.realizarAccion(tablero.getJugadores().get(actual).getAvatar().getUbicacion(), tablero.getJugadores().get(actual), tablero, dado);
                                if (tablero.getJugadores().get(actual).getAvatar().getRepeticiones() >= 3) {
                                    contadoraccion = 1;
                                }
                            }
                        }
                    }
                    if (tablero.getJugadores().get(actual).getAvatar() instanceof Sombrero) {
                        tablero.getJugadores().get(actual).lanzarDados(dado);
                        if (contadoraccion == 0) {
                            if (tablero.getJugadores().get(actual).getPenalizacion() > 0) {
                                consola.imprimir("Saco menos de un 5, no puede tirar mas");
                                tablero.getJugadores().get(actual).setTirada(true);
                            } else {
                                accion.realizarAccion(tablero.getJugadores().get(actual).getAvatar().getUbicacion(), tablero.getJugadores().get(actual), tablero, dado);
                                if (tablero.getJugadores().get(actual).getAvatar().getRepeticiones() >= 3) {
                                    contadoraccion = 1;
                                }
                            }
                        }
                    }

                } else {
                    tablero.getJugadores().get(actual).lanzarDados(dado);
                    if (contadoraccion == 0) {
                        accion.realizarAccion((tablero.getJugadores().get(actual).getAvatar().getUbicacion()), tablero.getJugadores().get(actual), tablero, dado);
                        if (dado.getValor1() != dado.getValor2()) {
                            contadoraccion = 1;
                        }

                    }
                }
                tablero.verTablero();
            } catch (Exceptionacciondados a) {
                throw a;
            }
        } else {
            throw new Exceptionaccion("El número de jugadores no es el adecuado");
        }
    }

    public void acabar(String[] partes) throws Exceptionaccion {
        int valorlista = 0;
        if (partes.length > 2 || partes[1].equals("turno") && (tablero.getJugadores().size() >= 3) && (tablero.getJugadores().get(actual).getFortuna() != 0)) {
            if (tablero.getJugadores().get(actual).getTirada() == false) {
                consola.imprimir("Debe tirar los dados antes de acabar el turno");
            } else {
                for (int i = 1; i < tablero.getJugadores().size(); i++) {
                    tablero.getJugadores().get(i).setFortunaPasada(-1);
                }
                tablero.getJugadores().get(actual).setCasillaPasada(null);
                for (int i = 0; i < tablero.getJugadores().get(actual).getListaBlanca().size(); i++) {
                    valorlista = tablero.getJugadores().get(actual).getInmunidadAlquiler().get(i);
                    valorlista--;
                    tablero.getJugadores().get(actual).getInmunidadAlquiler().remove(i);
                    if (valorlista > 0) {
                        tablero.getJugadores().get(actual).getInmunidadAlquiler().add(i, valorlista);
                    } else {
                        tablero.getJugadores().get(actual).getListaBlanca().remove(i);
                    }

                }
                if (tablero.getJugadores().get(actual).getTurnosCarcel() > 0) {
                    tablero.getJugadores().get(actual).setTurnosCarcel(tablero.getJugadores().get(actual).getTurnosCarcel() + 1);
                }
                if (tablero.getJugadores().get(actual).getPenalizacion() > 0) {
                    tablero.getJugadores().get(actual).setPenalizacion(tablero.getJugadores().get(actual).getPenalizacion() - 1);
                }

                actual = actual + 1;
                if ((actual > tablero.getJugadores().size() - 1) && tablero.getJugadores().size() > 1) {
                    turno++;
                    actual = 1;
                    tablero.getJugadores().get(actual).setTirada(false);
                    tablero.getJugadores().get(actual).setCompras(false);
                    tablero.getJugadores().get(actual).getAvatar().setRepeticiones(0);
                    consola.imprimir("El jugador actual es " + tablero.getJugadores().get(actual).getNombre());
                    if (tablero.getJugadores().get(actual).getTratos().size() > 0) {
                        tablero.listarTratos(tablero.getJugadores().get(actual));
                    }
                } else if (tablero.getJugadores().size() > 1) {
                    tablero.getJugadores().get(actual).setTirada(false);
                    tablero.getJugadores().get(actual).setCompras(false);
                    tablero.getJugadores().get(actual).getAvatar().setRepeticiones(0);
                    consola.imprimir("El jugador actual es " + tablero.getJugadores().get(actual).getNombre());
                    if (tablero.getJugadores().get(actual).getTratos().size() > 0) {
                        tablero.listarTratos(tablero.getJugadores().get(actual));
                    }
                } else {
                    consola.imprimir("El jugador no existe");
                }
                contadoraccion = 0;
            }
        } else if (tablero.getJugadores().get(actual).getFortuna() == 0) {
            consola.imprimir("Ya no puede jugar mas");
            tablero.getJugadores().remove(actual);
        } else if ((tablero.getJugadores().size() <= 3)) {
            throw new Exceptionaccion("El número de jugadores no es el adecuado");
        } else {
            consola.imprimir("Comando incorrecto");
        }
    }

    public void salir(String[] partes) {
        if (partes.length < 2 || !("carcel".equals(partes[1]))) {
            consola.imprimir("Comando incorrecto");
        } else if ((tablero.getJugadores().size() >= 3)) {
            if (!((Especial) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getNombre().equals(tablero.casillaPosicion(10).getNombre()) && tablero.getJugadores().get(actual).getTurnosCarcel() <= 1) {
                consola.imprimir("No esta en la carcel. No puede salir de ella");
            } else {

                if (tablero.getJugadores().get(actual).getFortuna() - ((Especial) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getValorIn() < 0) {
                    consola.imprimir("No tiene suficiente dinero");

                } else if ((tablero.getJugadores().get(actual).getFortuna() - ((Especial) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getValorIn() < 0)) {
                    consola.imprimir("El jugador entra en bancarrota");
                    for (Propiedad i : tablero.getJugadores().get(actual).getPropiedades()) {
                        tablero.getJugadores().get(0).anhadirPropiedades(i);
                        tablero.getJugadores().get(actual).getPropiedades().remove(i);
                    }
                } else {
                    tablero.getJugadores().get(actual).setFortuna(tablero.getJugadores().get(actual).getFortuna() - ((Especial) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getValorIn());
                    tablero.getJugadores().get(actual).setTurnosCarcel(0);
                    consola.imprimir("Pagó la cantidad necesaria. Vuelve a ser libre");
                    tablero.getJugadores().get(actual).setTirada(false);
                    contadoraccion = 0;
                }
            }
        } else {
            consola.imprimir("No se puede inciar una partida con menos de dos jugadores");
        }

    }

    public void describir(String[] partes) {
        int pos = 0;

        if (partes.length < 2) {
            consola.imprimir("Comando incorrecto");
        } else if (("jugador".equals(partes[1]))) {
            if (partes.length < 3) {
                consola.imprimir("Comando incorrecto");
            } else if (tablero.getListaJugadores().contains(partes[2])) {
                pos = tablero.getListaJugadores().indexOf(partes[2]) + 1;
                tablero.getJugadores().get(pos).imprimirJugador();

            } else {
                consola.imprimir("El jugador no existe");
            }
        } else if (("avatar".equals(partes[1]))) {
            if (partes.length < 3) {
                consola.imprimir("Comando incorrecto");
            } else if (tablero.getListaAvatares().contains(partes[2])) {
                pos = tablero.getListaAvatares().indexOf(partes[2]);
                tablero.getAvatares().get(pos).imprimirAvatar();

            } else {
                consola.imprimir("El avatar no existe no existe");
            }

        } else if (datos.containsKey(partes[1])) {
            datos.get(partes[1]).ImprimeCasilla();
        } else {
            consola.imprimir("Dicha casilla no existe");
        }
    }

    public void comprar(String[] partes) throws Exceptionaccionpropiedad {
        if (partes.length < 2) {
            consola.imprimir("Comando incorrecto");
        } else {
            if ((tablero.getJugadores().size() < 3)) {
                consola.imprimir("El numero de jugadores no es correcto");
            } else {
                if (tablero.getJugadores().get(actual).getAvatar().getModo().equals("avanzado") && tablero.getJugadores().get(actual).getAvatar() instanceof Coche && tablero.getJugadores().get(actual).getCompras() == true) {
                    consola.imprimir("Ya ha comprado una vez no puede comprar más");
                } else if ((((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()) instanceof Solar || ((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()) instanceof Servicio || ((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()) instanceof Transporte) && ((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getNombre().equals(partes[1])) {
                    if (((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getPropietario().getNombre().equals("Banca")) {

                        if (tablero.getJugadores().get(actual).getFortuna() - ((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getValorAct() < 0) {
                            consola.imprimir("No tiene suficiente dinero");
                        } else {
                            ((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).comprar(tablero.getJugadores().get(actual));
                            tablero.getJugadores().get(0).getPropiedades().remove((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion());

                        }
                        tablero.getJugadores().get(actual).imprimirJugador();
                    } else {
                        consola.imprimir("No puede comprar la propiedad indicada");
                    }
                } else if (!(((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()) instanceof Solar || ((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()) instanceof Servicio || ((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()) instanceof Transporte)) {
                    throw new Exceptionaccionpropiedad("Este tipo de casilla no se puede comprar");
                } else if (!((Propiedad) tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getNombre().equals(partes[1])) {
                    consola.imprimir("No puede comprar una casilla en la que no este");
                } else {
                    consola.imprimir("No ha tirado los dados aún");
                }
            }
        }
    }

    public void hipotecar(String[] partes) throws Exceptionaccionpropiedadhipotecar {
        if (partes.length < 2) {
            consola.imprimir("Comando incorrecto");
        } else {
            if ((tablero.getJugadores().size() < 3)) {
                consola.imprimir("El numero de jugadores no es correcto");
            } else if (tablero.getJugadores().get(actual).getHipotecas().contains(tablero.datosCasillas().get(partes[1]))) {
                throw new Exceptionaccionpropiedadhipotecar(tablero.getJugadores().get(actual).getNombre() + " no puede hipotecar " + partes[1] + ".Ya está hipotecada");
            } else if (!tablero.getJugadores().get(actual).getPropiedades().contains((Propiedad) tablero.datosCasillas().get(partes[1]))) {
                throw new Exceptionaccionpropiedadhipotecar(tablero.getJugadores().get(actual).getNombre() + " no puede hipotecar " + partes[1] + ".No es una propiedad que le pertenezca");
            } else if (tablero.datosCasillas().get(partes[1]) instanceof Solar && ((Solar) tablero.datosCasillas().get(partes[1])).getEdificios().size() > 0) {
                throw new Exceptionaccionpropiedadhipotecar(tablero.getJugadores().get(actual).getNombre() + " no puede hipotecar " + partes[1] + ".Debe vender primero los edificios");
            } else {
                ((Propiedad) tablero.datosCasillas().get(partes[1])).setHipotecada(true);
                if (tablero.datosCasillas().get(partes[1]) instanceof Solar && !((Solar) tablero.datosCasillas().get(partes[1])).getGrupo().getJugador().getNombre().equals("")) {
                    ((Solar) tablero.datosCasillas().get(partes[1])).getGrupo().setHipotecada(true);
                }
                consola.imprimir(tablero.getJugadores().get(actual).getNombre() + " recibe " + Math.round(0.5 * ((Propiedad) tablero.datosCasillas().get(partes[1])).getValorIn()) + "€ por la hipoteca de " + partes[1]);
                if (tablero.datosCasillas().get(partes[1]) instanceof Solar) {
                    consola.imprimir(" No puede recibir alquileres ni edificar en el grupo" + ((Solar) tablero.datosCasillas().get(partes[1])).getGrupo().getId());
                } else {
                    consola.imprimir(" No puede recibir alquileres");
                }
                tablero.getJugadores().get(actual).setFortuna(tablero.getJugadores().get(actual).getFortuna() + Math.round(0.5 * ((Propiedad) tablero.datosCasillas().get(partes[1])).getValorIn()));
                tablero.getJugadores().get(actual).anhadirHipotecas((Propiedad) tablero.datosCasillas().get(partes[1]));
                tablero.getJugadores().get(actual).getPropiedades().remove((Propiedad) tablero.datosCasillas().get(partes[1]));
            }
        }
    }

    public void deshipotecar(String[] partes) {
        if (partes.length < 2) {
            consola.imprimir("Comando incorrecto");
        } else {
            if ((tablero.getJugadores().size() < 3)) {
                consola.imprimir("El numero de jugadores no es correcto");
            } else if (tablero.getJugadores().get(actual).getPropiedades().contains((Propiedad) tablero.datosCasillas().get(partes[1]))) {
                consola.imprimir(tablero.getJugadores().get(actual).getNombre() + " no puede deshipotecar " + partes[1] + ".No está hipotecada");
            } else if (!tablero.getJugadores().get(actual).getPropiedades().contains((Propiedad) tablero.datosCasillas().get(partes[1])) && !tablero.getJugadores().get(actual).getHipotecas().contains(tablero.datosCasillas().get(partes[1]))) {
                consola.imprimir(tablero.getJugadores().get(actual).getNombre() + " no puede deshipotecar " + partes[1] + ".No es una propiedad que le pertenezca");
            } else if ((tablero.getJugadores().get(actual).getFortuna() - Math.round(0.5 * ((Propiedad) tablero.datosCasillas().get(partes[1])).getValorIn())) <= 0) {
                consola.imprimir("No tiene dinero suficiente para deshipotecar");
            } else {
                ((Propiedad) tablero.datosCasillas().get(partes[1])).setHipotecada(false);
                if (tablero.datosCasillas().get(partes[1]) instanceof Solar) {
                    ((Solar) tablero.datosCasillas().get(partes[1])).getGrupo().setHipotecada(false);
                    if (!((Solar) tablero.datosCasillas().get(partes[1])).getGrupo().getJugador().getNombre().equals("")) {
                        for (int i = 0; i < ((Solar) tablero.datosCasillas().get(partes[1])).getGrupo().getCasillas().size(); i++) {
                            if (((Solar) tablero.datosCasillas().get(partes[1])).getGrupo().getCasillas().get(i).isHipotecada()) {
                                ((Solar) tablero.datosCasillas().get(partes[1])).getGrupo().setHipotecada(true);
                            }
                        }
                    }
                }
                consola.imprimir(tablero.getJugadores().get(actual).getNombre() + " paga " + Math.round(0.5 * ((Propiedad) tablero.datosCasillas().get(partes[1])).getValorIn()) + "€ por la hipoteca de " + partes[1]);
                if (tablero.datosCasillas().get(partes[1]) instanceof Solar) {
                    consola.imprimir(" Puede recibir alquileres y edificar en el grupo" + ((Solar) tablero.datosCasillas().get(partes[1])).getGrupo().getId());
                } else {
                    consola.imprimir(" Puede recibir alquileres");
                }
                tablero.getJugadores().get(actual).setFortuna(tablero.getJugadores().get(actual).getFortuna() - Math.round(0.5 * ((Propiedad) tablero.datosCasillas().get(partes[1])).getValorIn()));
                tablero.getJugadores().get(actual).getPropiedades().add((Propiedad) tablero.datosCasillas().get(partes[1]));
                tablero.getJugadores().get(actual).getHipotecas().remove(tablero.datosCasillas().get(partes[1]));
            }
        }
    }

    public void cambiar(String[] partes) {
        String mensaje;
        if (partes.length < 2) {
            consola.imprimir("Comando incorrecto");
        } else if (!partes[1].equals("modo")) {
            consola.imprimir("Comando incorrecto");
        } else {
            if (tablero.getJugadores().get(actual).getAvatar().getModo().equals("avanzado")) {
                tablero.getJugadores().get(actual).getAvatar().setModo("normal");
                mensaje = "A partir de ahora el avatar " + tablero.getJugadores().get(actual).getAvatar().getId() + " de tipo ";
                if (tablero.getJugadores().get(actual).getAvatar() instanceof Coche) {
                    mensaje += String.format("coche, se moverá en modo normal.");
                }
                if (tablero.getJugadores().get(actual).getAvatar() instanceof Esfinge) {
                    mensaje += String.format("esfinge, se moverá en modo normal.");
                }
                if (tablero.getJugadores().get(actual).getAvatar() instanceof Sombrero) {
                    mensaje += String.format("sombrero, se moverá en modo normal.");
                }
                if (tablero.getJugadores().get(actual).getAvatar() instanceof Pelota) {
                    mensaje += String.format("pelota, se moverá en modo normal.");
                }
                consola.imprimir(mensaje);
            } else {
                tablero.getJugadores().get(actual).getAvatar().setModo("avanzado");
                mensaje = "A partir de ahora el avatar " + tablero.getJugadores().get(actual).getAvatar().getId() + " de tipo ";
                if (tablero.getJugadores().get(actual).getAvatar() instanceof Coche) {
                    mensaje += String.format("coche, se moverá en modo avanzado.");
                }
                if (tablero.getJugadores().get(actual).getAvatar() instanceof Esfinge) {
                    mensaje += String.format("esfinge, se moverá en modo avanzado.");
                }
                if (tablero.getJugadores().get(actual).getAvatar() instanceof Sombrero) {
                    mensaje += String.format("sombrero, se moverá en modo avanzado.");
                }
                if (tablero.getJugadores().get(actual).getAvatar() instanceof Pelota) {
                    mensaje += String.format("pelota, se moverá en modo avanzado.");
                }
                consola.imprimir(mensaje);
            }
        }

    }

    public void estadistica(String[] partes) {
        if (partes.length > 1 && tablero.getListaJugadores().contains(partes[1])) {
            int abc = tablero.getListaJugadores().indexOf(partes[1]) + 1;
            consola.imprimir("{\n"
                    + "  dineroInvertido:" + tablero.getJugadores().get(abc).getDineroInvertido() + ",\n"
                    + "  pagoTasasEImpuestos:" + tablero.getJugadores().get(abc).getPagoTasasEImpuestos() + ",\n"
                    + "  pagoDeAlquileres: " + tablero.getJugadores().get(abc).getPagoDeAlquileres() + ",\n"
                    + "  cobroDeAlquileres: " + tablero.getJugadores().get(abc).getCobroDeAlquileres() + ",\n"
                    + "  pasarPorCasillaDeSalida: " + tablero.getJugadores().get(abc).getPasarPorCasillaDeSalida() + ",\n"
                    + "  premiosInversionesOBote: " + tablero.getJugadores().get(abc).getPremiosInversionesOBote() + ",\n"
                    + "  vecesEnLaCarcel:" + tablero.getJugadores().get(abc).getVecesEnLaCarcel() + "\n"
                    + "}");
        } else {
            jugadorMasVueltas = tablero.getJugadores().get(1);
            jugadorEnCabeza = tablero.getJugadores().get(1);
            jugadorMasVecesDados = tablero.getJugadores().get(1);
            grupoMasRentable = tablero.getGrupos().get(1);
            casillaMasRentable = tablero.casillaPosicion(0);
            casillaMasFrecuentada = tablero.casillaPosicion(0);
            for (int i = 1; i < tablero.getJugadores().size(); i++) {
                if (jugadorMasVueltas.getVueltas() <= tablero.getJugadores().get(i).getVueltas()) {
                    jugadorMasVueltas = tablero.getJugadores().get(i);
                }
                if (jugadorEnCabeza.calcularFortuna() <= tablero.getJugadores().get(i).calcularFortuna()) {
                    jugadorEnCabeza = tablero.getJugadores().get(i);
                }
                if (jugadorMasVecesDados.getTiradasDados() <= tablero.getJugadores().get(i).getTiradasDados()) {
                    jugadorMasVecesDados = tablero.getJugadores().get(i);
                }
            }
            for (int i = 0; i < tablero.getGrupos().size(); i++) {
                if (grupoMasRentable.calcularValor() <= tablero.getGrupos().get(i).calcularValor()) {
                    grupoMasRentable = tablero.getGrupos().get(i);
                }
            }
            for (int i = 0; i < 40; i++) {
                if (((Propiedad) casillaMasRentable).getRentabilidad() <= ((Propiedad) casillaMasRentable).getRentabilidad()) {
                    casillaMasRentable = tablero.casillaPosicion(i);
                }
                if (casillaMasFrecuentada.getFrecuencia() <= tablero.casillaPosicion(i).getFrecuencia()) {
                    casillaMasFrecuentada = tablero.casillaPosicion(i);
                }
            }
            consola.imprimir("{\n"
                    + "  casillaMasRentable:  " + casillaMasRentable.getNombre() + ",  \n"
                    + "  grupoMasRentable:  " + grupoMasRentable.getId() + ",  \n"
                    + "  casillaMasFrecuentada:  " + casillaMasFrecuentada.getNombre() + ",  \n"
                    + "  jugadorMasVueltas:  " + jugadorMasVueltas.getNombre() + ",  \n"
                    + "  jugadorMasVecesDados:  " + jugadorMasVecesDados.getNombre() + ",  \n"
                    + "  jugadorEnCabeza:  " + jugadorEnCabeza.getNombre() + "  \n"
                    + "}");
        }
    }

    public void trato(String[] partes) {
        int receptor = 0, selector = 0, selectorb = 0;
        boolean emisorpropiedad = false, receptorpropiedad = false, alquilarpropiedad = false;
        for (int i = 0; i < tablero.getJugadores().size(); i++) {
            if (tablero.getJugadores().get(i).getNombre().equals(partes[1])) {
                receptor = i;
            }
        }
        if (receptor == 0) {
            consola.imprimir("El receptor no existe");
        } else {
            for (int i = 0; i < 40; i++) {
                if (partes.length == 4) {
                    if (tablero.casillaPosicion(i).getNombre().equals(partes[2])) {
                        selector = 2;
                    }
                    if (tablero.casillaPosicion(i).getNombre().equals(partes[3])) {
                        selectorb = 3;
                    }
                    if (selector == 2 && selectorb == 3) {
                        selector = 1;
                    }
                }
                if (partes.length == 5) {
                    if (tablero.casillaPosicion(i).getNombre().equals(partes[3])) {
                        selector = 4;
                    }
                    if (tablero.casillaPosicion(i).getNombre().equals(partes[4])) {
                        selector = 5;
                    }
                }

            }
            if (selector == 1 && datos.get(partes[2]) instanceof Propiedad && datos.get(partes[3]) instanceof Propiedad && datos.containsKey(partes[2]) && datos.containsKey(partes[3])) {
                for (int i = 0; i < tablero.getJugadores().get(actual).getPropiedades().size(); i++) {
                    if (tablero.getJugadores().get(actual).getPropiedades().get(i).getNombre().equals(partes[2])) {
                        emisorpropiedad = true;
                    }
                }
                for (int i = 0; i < tablero.getJugadores().get(receptor).getPropiedades().size(); i++) {
                    if (tablero.getJugadores().get(receptor).getPropiedades().get(i).getNombre().equals(partes[3])) {
                        receptorpropiedad = true;
                    }
                }
                if (emisorpropiedad && receptorpropiedad) {
                    Trato trato = new Trato(tablero.getJugadores().get(actual), tablero.getJugadores().get(receptor), (Propiedad) datos.get(partes[2]), (Propiedad) datos.get(partes[3]));
                    tablero.getJugadores().get(actual).anhadirTratos(trato);
                    tablero.getJugadores().get(receptor).anhadirTratos(trato);
                    consola.imprimir("Se ha creado el " + trato.getId() + " con el jugador " + tablero.getJugadores().get(receptor).getNombre());
                } else {
                    consola.imprimir("Los jugadores no tienen las propiedades en su posesion para poder hacer un trato con ellas");
                }
            } else if (selector == 2 && datos.get(partes[2]) instanceof Propiedad && datos.containsKey(partes[2]) && Integer.parseInt(partes[3]) > 0) {
                for (int i = 0; i < tablero.getJugadores().get(actual).getPropiedades().size(); i++) {
                    if (tablero.getJugadores().get(actual).getPropiedades().get(i).getNombre().equals(partes[2])) {
                        emisorpropiedad = true;
                    }
                }
                if (emisorpropiedad) {
                    Trato trato = new Trato(tablero.getJugadores().get(actual), tablero.getJugadores().get(receptor), (Propiedad) datos.get(partes[2]), Integer.parseInt(partes[3]));
                    tablero.getJugadores().get(actual).anhadirTratos(trato);
                    tablero.getJugadores().get(receptor).anhadirTratos(trato);
                    consola.imprimir("Se ha creado el " + trato.getId() + " con el jugador " + tablero.getJugadores().get(receptor).getNombre());
                } else {
                    consola.imprimir("Los jugadores no tienen las propiedades en su posesion para poder hacer un trato con ellas");
                }

            } else if (selectorb == 3 && datos.get(partes[3]) instanceof Propiedad && datos.containsKey(partes[3]) && Integer.parseInt(partes[2]) > 0) {
                for (int i = 0; i < tablero.getJugadores().get(receptor).getPropiedades().size(); i++) {
                    if (tablero.getJugadores().get(receptor).getPropiedades().get(i).getNombre().equals(partes[3])) {
                        receptorpropiedad = true;
                    }
                }
                if (receptorpropiedad) {
                    Trato trato = new Trato(tablero.getJugadores().get(actual), tablero.getJugadores().get(receptor), Integer.parseInt(partes[2]), (Propiedad) datos.get(partes[3]));
                    tablero.getJugadores().get(actual).anhadirTratos(trato);
                    tablero.getJugadores().get(receptor).anhadirTratos(trato);
                    consola.imprimir("Se ha creado el " + trato.getId() + " con el jugador " + tablero.getJugadores().get(receptor).getNombre());
                } else {
                    consola.imprimir("Los jugadores no tienen las propiedades en su posesion para poder hacer un trato con ellas");
                }

            } else if (selector == 4 && datos.get(partes[2]) instanceof Propiedad && datos.get(partes[3]) instanceof Propiedad && Integer.parseInt(partes[4]) > 0 && datos.containsKey(partes[2]) && datos.containsKey(partes[3])) {
                for (int i = 0; i < tablero.getJugadores().get(actual).getPropiedades().size(); i++) {
                    if (tablero.getJugadores().get(actual).getPropiedades().get(i).getNombre().equals(partes[2])) {
                        emisorpropiedad = true;
                    }
                }
                for (int i = 0; i < tablero.getJugadores().get(receptor).getPropiedades().size(); i++) {
                    if (tablero.getJugadores().get(receptor).getPropiedades().get(i).getNombre().equals(partes[3])) {
                        receptorpropiedad = true;
                    }
                }
                if (emisorpropiedad && receptorpropiedad) {
                    Trato trato = new Trato(tablero.getJugadores().get(actual), tablero.getJugadores().get(receptor), (Propiedad) datos.get(partes[2]), (Propiedad) datos.get(partes[3]), Integer.parseInt(partes[4]));
                    tablero.getJugadores().get(actual).anhadirTratos(trato);
                    tablero.getJugadores().get(receptor).anhadirTratos(trato);
                    consola.imprimir("Se ha creado el " + trato.getId() + " con el jugador " + tablero.getJugadores().get(receptor).getNombre());
                } else {
                    consola.imprimir("Los jugadores no tienen las propiedades en su posesion para poder hacer un trato con ellas");
                }

            } else if (selector == 5 && datos.get(partes[2]) instanceof Propiedad && datos.get(partes[4]) instanceof Propiedad && Integer.parseInt(partes[3]) > 0 && datos.containsKey(partes[2]) && datos.containsKey(partes[4])) {
                for (int i = 0; i < tablero.getJugadores().get(actual).getPropiedades().size(); i++) {
                    if (tablero.getJugadores().get(actual).getPropiedades().get(i).getNombre().equals(partes[2])) {
                        emisorpropiedad = true;
                    }
                }
                for (int i = 0; i < tablero.getJugadores().get(receptor).getPropiedades().size(); i++) {
                    if (tablero.getJugadores().get(receptor).getPropiedades().get(i).getNombre().equals(partes[4])) {
                        receptorpropiedad = true;
                    }
                }
                if (emisorpropiedad && receptorpropiedad) {
                    Trato trato = new Trato(tablero.getJugadores().get(actual), tablero.getJugadores().get(receptor), (Propiedad) datos.get(partes[2]), Integer.parseInt(partes[3]), (Propiedad) datos.get(partes[4]));
                    tablero.getJugadores().get(actual).anhadirTratos(trato);
                    tablero.getJugadores().get(receptor).anhadirTratos(trato);
                    consola.imprimir("Se ha creado el " + trato.getId() + " con el jugador " + tablero.getJugadores().get(receptor).getNombre());
                } else {
                    consola.imprimir("Los jugadores no tienen las propiedades en su posesion para poder hacer un trato con ellas");
                }

            } else if (partes.length == 6 && datos.containsKey(partes[2]) && datos.containsKey(partes[3]) && datos.get(partes[2]) instanceof Propiedad && datos.get(partes[3]) instanceof Propiedad && datos.containsKey(partes[4]) && datos.get(partes[4]) instanceof Propiedad && Integer.parseInt(partes[5]) > 0) {
                for (int i = 0; i < tablero.getJugadores().get(actual).getPropiedades().size(); i++) {
                    if (tablero.getJugadores().get(actual).getPropiedades().get(i).getNombre().equals(partes[2])) {
                        emisorpropiedad = true;
                    }
                }
                for (int i = 0; i < tablero.getJugadores().get(receptor).getPropiedades().size(); i++) {
                    if (tablero.getJugadores().get(receptor).getPropiedades().get(i).getNombre().equals(partes[3])) {
                        receptorpropiedad = true;
                    }
                }
                for (int i = 0; i < tablero.getJugadores().get(receptor).getPropiedades().size(); i++) {
                    if (tablero.getJugadores().get(receptor).getPropiedades().get(i).getNombre().equals(partes[4])) {
                        alquilarpropiedad = true;
                    }
                }
                if (emisorpropiedad && receptorpropiedad && alquilarpropiedad) {
                    Trato trato = new Trato(tablero.getJugadores().get(actual), tablero.getJugadores().get(receptor), (Propiedad) datos.get(partes[2]), (Propiedad) datos.get(partes[3]), (Propiedad) datos.get(partes[4]), Integer.parseInt(partes[5]));
                    tablero.getJugadores().get(actual).anhadirTratos(trato);
                    tablero.getJugadores().get(receptor).anhadirTratos(trato);
                    consola.imprimir("Se ha creado el " + trato.getId() + " con el jugador " + tablero.getJugadores().get(receptor).getNombre());
                } else {
                    consola.imprimir("Los jugadores no tienen las propiedades en su posesion para poder hacer un trato con ellas");
                }

            }
        }
    }

    public void aceptar(String[] partes) {
        int numerotrato = -1;
        for (int i = 0; i < tablero.getJugadores().get(actual).getTratos().size(); i++) {
            if (partes[1].equals(tablero.getJugadores().get(actual).getTratos().get(i).getId()) && tablero.getJugadores().get(actual).getNombre().equals(tablero.getJugadores().get(actual).getTratos().get(i).getReceptor().getNombre())) {
                numerotrato = i;
            }
        }
        if (numerotrato == -1) {
            consola.imprimir("El jugador actual no tiene este trato");
        } else {
            int emisor = 0;
            for (int i = 0; i < tablero.getJugadores().size(); i++) {
                if (tablero.getJugadores().get(i).getNombre().equals(tablero.getJugadores().get(actual).getTratos().get(numerotrato).getEmisor().getNombre())) {
                    emisor = i;
                }
            }
            if (tablero.getJugadores().get(actual).getTratos().get(numerotrato).ejecutarTrato() == true) {
                tablero.getJugadores().get(actual).getTratos().remove(numerotrato);
                for (int i = 0; i < tablero.getJugadores().get(emisor).getTratos().size(); i++) {
                    if (partes[1].equals(tablero.getJugadores().get(emisor).getTratos().get(i).getId())) {
                        numerotrato = i;
                    }
                }
                tablero.getJugadores().get(emisor).getTratos().remove(numerotrato);

            } else {
                consola.imprimir("No se puede realizar el trato porque faltan recursos");
            }
        }

    }

    public void eliminar(String[] partes) {
        int numerotrato = -1;
        for (int i = 0; i < tablero.getJugadores().get(actual).getTratos().size(); i++) {
            if (partes[1].equals(tablero.getJugadores().get(actual).getTratos().get(i).getId()) && tablero.getJugadores().get(actual).getNombre().equals(tablero.getJugadores().get(actual).getTratos().get(i).getReceptor().getNombre())) {
                numerotrato = i;
            }
        }
        if (numerotrato == -1) {
            consola.imprimir("El jugador actual no tiene este trato");
        } else {
            int emisor = 0;
            for (int i = 0; i < tablero.getJugadores().size(); i++) {
                if (tablero.getJugadores().get(i).getNombre().equals(tablero.getJugadores().get(actual).getTratos().get(numerotrato).getEmisor().getNombre())) {
                    emisor = i;
                }
            }
            tablero.getJugadores().get(actual).getTratos().remove(numerotrato);
            for (int i = 0; i < tablero.getJugadores().get(emisor).getTratos().size(); i++) {
                if (partes[1].equals(tablero.getJugadores().get(emisor).getTratos().get(i).getId())) {
                    numerotrato = i;
                }
            }
            tablero.getJugadores().get(emisor).getTratos().remove(numerotrato);

        }

    }

    public void ver(String[] partes) {
        if (partes.length < 2 || !("tablero".equals(partes[1]))) {
            consola.imprimir("Comando incorrecto");
        } else {
            tablero.verTablero();
        }
    }

    public void set(String[] partes) {
        Dado dado = new Dado();
        Ejecucion accion = new Ejecucion(tablero);
        if (partes[1].equals("fortuna")) {
            tablero.getJugadores().get(actual).setFortuna(0);
        }
        if (partes[1].equals("posicion")) {
            contadoraccion = 0;
            tablero.getJugadores().get(actual).getAvatar().setUbicacion(tablero.casillaPosicion(Integer.parseInt(partes[2])));
            (tablero.getJugadores().get(actual).getAvatar().getUbicacion()).setFrecuencia((tablero.getJugadores().get(actual).getAvatar().getUbicacion()).getFrecuencia() + 1);
            accion.realizarAccion((tablero.getJugadores().get(actual).getAvatar().getUbicacion()), tablero.getJugadores().get(actual), tablero, dado);
            tablero.verTablero();

        }

        if (partes[1].equals("vueltas")) {
            for (int i = 1; i < tablero.getJugadores().size(); i++) {
                tablero.getJugadores().get(i).setVueltas(4);
            }
        }

    }

}
