/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import datosjugador.Jugador;
import datosjugador.Avatar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import static monopolyetse.Juego.consola;

/**
 *
 * @author cesar
 */
public class Tablero {

    private ArrayList<ArrayList<Casilla>> casillas;
    private ArrayList<Avatar> avatares;
    private long bote;
    private int contadorEdificacion;
    private int contadorTrato;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Carta> comunidad;
    private ArrayList<Carta> suerte;
    private ArrayList<Grupo> grupos;

    public Tablero() {
        contadorTrato = 0;
        this.casillas = new ArrayList<>();
        this.grupos = new ArrayList<>();
        Jugador banca = new Jugador(this);
        Grupo grupomarron = new Grupo("marron", Valores.MARRON);
        grupos.add(grupomarron);
        Grupo grupoazulclaro = new Grupo("azulclaro", Valores.CYAN);
        grupos.add(grupoazulclaro);
        Grupo gruporosa = new Grupo("rosa", Valores.MAGENTA);
        grupos.add(gruporosa);
        Grupo gruponaranja = new Grupo("naranja", Valores.NARANJA);
        grupos.add(gruponaranja);
        Grupo gruporojo = new Grupo("rojo", Valores.ROJO);
        grupos.add(gruporojo);
        Grupo grupoamarillo = new Grupo("amarillo", Valores.AMARILLO);
        grupos.add(grupoamarillo);
        Grupo grupoverde = new Grupo("verde", Valores.VERDE);
        grupos.add(grupoverde);
        Grupo grupoazul = new Grupo("azul", Valores.AZUL);
        grupos.add(grupoazul);
        ArrayList<Casilla> abajo = new ArrayList<>();
        abajo.add(new Especial("Salida", this, Valores.PASOSALIDA, "salida"));
        abajo.add(new Solar(Valores.SOLARES[0], this, Valores.VALORGRUPO1 / 2, banca, grupomarron));
        abajo.add(new CajaComunidad("Comunidad", this));
        abajo.add(new Solar(Valores.SOLARES[1], this, Valores.VALORGRUPO1 / 2, banca, grupomarron));
        abajo.add(new Impuesto("DeCapital", this, Valores.VALCAPITAL));
        abajo.add(new Transporte("Puerto", this, Valores.VALTRANSPORTE, banca));
        abajo.add(new Solar(Valores.SOLARES[2], this, Valores.VALORGRUPO2 / 3, banca, grupoazulclaro));
        abajo.add(new Suerte("Suerte", this));
        abajo.add(new Solar(Valores.SOLARES[3], this, Valores.VALORGRUPO2 / 3, banca, grupoazulclaro));
        abajo.add(new Solar(Valores.SOLARES[4], this, Valores.VALORGRUPO2 / 3, banca, grupoazulclaro));

        casillas.add(abajo);
        ArrayList<Casilla> izquierda = new ArrayList<>();

        izquierda.add(new Especial("Carcel", this, Valores.SALIRCARCEL, "carcel"));
        izquierda.add(new Solar(Valores.SOLARES[5], this, Valores.VALORGRUPO3 / 3, banca, gruporosa));
        izquierda.add(new Servicio("Telefonica", this, Valores.VALSERVICIO, banca));
        izquierda.add(new Solar(Valores.SOLARES[6], this, Valores.VALORGRUPO3 / 3, banca, gruporosa));
        izquierda.add(new Solar(Valores.SOLARES[7], this, Valores.VALORGRUPO3 / 3, banca, gruporosa));
        izquierda.add(new Transporte("Tren", this, Valores.VALTRANSPORTE, banca));
        izquierda.add(new Solar(Valores.SOLARES[8], this, Valores.VALORGRUPO4 / 3, banca, gruponaranja));
        izquierda.add(new CajaComunidad("Comunidad", this));
        izquierda.add(new Solar(Valores.SOLARES[9], this, Valores.VALORGRUPO4 / 3, banca, gruponaranja));
        izquierda.add(new Solar(Valores.SOLARES[10], this, Valores.VALORGRUPO4 / 3, banca, gruponaranja));
        casillas.add(izquierda);
        ArrayList<Casilla> arriba = new ArrayList<>();

        arriba.add(new Accion("Parking", this));
        arriba.add(new Solar(Valores.SOLARES[11], this, Valores.VALORGRUPO5 / 3, banca, gruporojo));
        arriba.add(new Suerte("Suerte", this));
        arriba.add(new Solar(Valores.SOLARES[12], this, Valores.VALORGRUPO5 / 3, banca, gruporojo));
        arriba.add(new Solar(Valores.SOLARES[13], this, Valores.VALORGRUPO5 / 3, banca, gruporojo));
        arriba.add(new Transporte("Aeropuerto", this, Valores.VALTRANSPORTE, banca));
        arriba.add(new Solar(Valores.SOLARES[14], this, Valores.VALORGRUPO6 / 3, banca, grupoamarillo));
        arriba.add(new Solar(Valores.SOLARES[15], this, Valores.VALORGRUPO6 / 3, banca, grupoamarillo));
        arriba.add(new Servicio("BEGASA", this, Valores.VALSERVICIO, banca));
        arriba.add(new Solar(Valores.SOLARES[16], this, Valores.VALORGRUPO6 / 3, banca, grupoamarillo));
        casillas.add(arriba);
        ArrayList<Casilla> derecha = new ArrayList<>();

        derecha.add(new Especial("IrCarcel", this, 0, "ircarcel"));
        derecha.add(new Solar(Valores.SOLARES[17], this, Valores.VALORGRUPO7 / 3, banca, grupoverde));
        derecha.add(new Solar(Valores.SOLARES[18], this, Valores.VALORGRUPO7 / 3, banca, grupoverde));
        derecha.add(new CajaComunidad("Comunidad", this));
        derecha.add(new Solar(Valores.SOLARES[19], this, Valores.VALORGRUPO7 / 3, banca, grupoverde));
        derecha.add(new Transporte("ESA", this, Valores.VALTRANSPORTE, banca));
        derecha.add(new Suerte("Suerte", this));
        derecha.add(new Solar(Valores.SOLARES[20], this, Valores.VALORGRUPO8 / 2, banca, grupoazul));
        derecha.add(new Impuesto("DeLujo", this, Valores.VALLUJO));
        derecha.add(new Solar(Valores.SOLARES[21], this, Valores.VALORGRUPO8 / 2, banca, grupoazul));
        casillas.add(derecha);

        avatares = new ArrayList<>();
        bote = 0;
        jugadores = new ArrayList<>();
        jugadores.add(banca);
        comunidad = new ArrayList<>();
        contadorEdificacion = 1;
        for (int i = 1; i <= 6; i++) {
            comunidad.add(new CartaCajaComunidad(i, this));
        }
        suerte = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            suerte.add(new CartaSuerte(i, this));
        }
    }

    //Si hace falta cargar la partida en el futuro y no se quiere volver a crear el tablero con todas las casillas y jugadores, se le pasa como argumento
    public Tablero(ArrayList<ArrayList<Casilla>> listacasillas, ArrayList<Avatar> listaavatares, ArrayList<Jugador> listajugadores, long Bote) {
        if (listacasillas == null || listaavatares == null || listajugadores == null || Bote < 0) {
            System.exit(1);
        }

        avatares = listaavatares;
        jugadores = listajugadores;
        casillas = listacasillas;
        bote = Bote;

    }

    public ArrayList<ArrayList<Casilla>> getCasillas() {

        return casillas;
    }

    public ArrayList<Avatar> getAvatares() {
        return avatares;
    }

    public long getBote() {
        return bote;
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public ArrayList<Carta> getComunidad() {
        return comunidad;
    }

    public ArrayList<Carta> getSuerte() {
        return suerte;
    }

    public int getContadorEdificacion() {
        return contadorEdificacion;
    }

    public int getContadorTrato() {
        return contadorTrato;
    }

    public void setContadorTrato(int contadorTrato) {
        this.contadorTrato = contadorTrato;
    }

    public void setCasillas(ArrayList<ArrayList<Casilla>> listacasillas) {
        if (listacasillas != null) {
            casillas = listacasillas;
        }
    }

    public void setAvatares(ArrayList<Avatar> listavatares) {
        if (listavatares != null) {
            avatares = listavatares;
        }
    }

    public void setJugadores(ArrayList<Jugador> listajugadores) {
        if (listajugadores != null) {
            jugadores = listajugadores;
        }
    }

    public void SetBote(long newBote) {
        if (newBote < 0) {
            consola.imprimir("Valor erroneo");
        } else {
            bote = newBote;
        }
    }

    public void setContadorEdificacion(int cont) {
        if (cont < 0) {
            consola.imprimir("Valor erroneo");
        } else {
            contadorEdificacion = cont;
        }
    }

    public void anhadirJugador(Jugador j) {
        if (j == null) {
            consola.imprimir("Valor erroneo");
        } else {
            jugadores.add(j);
            avatares.add(j.getAvatar());
        }
    }

    public ArrayList<String> getListaJugadores() {
        ArrayList<String> m = new ArrayList<>();
        for (int i = 1; i < jugadores.size(); i++) {
            m.add(jugadores.get(i).getNombre());
        }
        return m;
    }

    public ArrayList<String> getListaAvatares() {
        ArrayList<String> m = new ArrayList<>();
        String texto = "";
        for (int i = 0; i < avatares.size(); i++) {
            texto += avatares.get(i).getId();
            m.add(texto);
            texto = "";
        }
        return m;
    }

    public void Barajar(ArrayList<Carta> m) {
        if (m != null) {
            Collections.shuffle(m);
        }
    }

    public Casilla casillaPosicion(int n) {
        if (n >= 0 && n < 10) {
            return casillas.get(0).get(n);
        } else if (n >= 10 && n < 20) {
            return casillas.get(1).get(n % 10);
        } else if (n > 19 && n < 30) {
            return casillas.get(2).get(n % 20);
        } else if (n >= 30 && n < 40) {
            return casillas.get(3).get(n % 30);
        } else {
            consola.imprimir("Posicion incorrecta");
            return null;
        }

    }

    public int PosicionCasilla(Casilla k) {
        if (casillas.get(0).contains(k)) {
            return casillas.get(0).indexOf(k);
        } else if (casillas.get(1).contains(k)) {
            return casillas.get(1).indexOf(k) + 10;
        } else if (casillas.get(2).contains(k)) {
            return casillas.get(2).indexOf(k) + 20;
        } else if (casillas.get(3).contains(k)) {
            return casillas.get(3).indexOf(k) + 30;
        } else {
            consola.imprimir("Posicion incorrecta");
            return -1;
        }
    }

    public ArrayList<Avatar> casillaJugador(Casilla j) {
        ArrayList<Avatar> avt = new ArrayList<>();
        for (int i = 0; i < avatares.size(); i++) {
            if (avatares.get(i).getUbicacion().equals(j)) {
                avt.add(avatares.get(i));
            }
        }
        return avt;
    }

    public HashMap<String, Casilla> datosCasillas() {
        HashMap<String, Casilla> datos = new HashMap<>();
        for (int i = 0; i < 40; i++) {
            datos.put(this.casillaPosicion(i).getNombre(), this.casillaPosicion(i));
        }

        return datos;
    }

    public void verTablero() {
        int i, j = 31;
        String res;

        res = "\n|";
        for (i = 20; i <= 30; i++) {
            if (this.casillaPosicion(i) instanceof Solar) {
                res += String.format("%-37s |", this.casillaPosicion(i).toString());
            } else {
                res += String.format("%-27s |", this.casillaPosicion(i).toString());
            }
        }
        res += "\n";
        for (i = 19; i > 10; i--) {

            if (this.casillaPosicion(i) instanceof Solar) {
                res += String.format("|%-37s |", this.casillaPosicion(i).toString());
            } else {
                res += String.format("|%-27s |", this.casillaPosicion(i).toString());
            }

            for (int k = 0; k < 11; k++) {
                res += String.format("               ");
            }
            if (this.casillaPosicion(j) instanceof Solar) {
                res += String.format("     |%-37s |", this.casillaPosicion(j).toString());
            } else {
                res += String.format("     |%-27s |", this.casillaPosicion(j).toString());
            }
            res += "\n";
            j++;
        }
        res += String.format("|");
        for (i = 10; i >= 0; i--) {
            if (this.casillaPosicion(i) instanceof Solar) {
                res += String.format("%-37s |", this.casillaPosicion(i).toString());
            } else {
                res += String.format("%-27s |", this.casillaPosicion(i).toString());
            }

        }
        res += "\n\n";
        consola.imprimir(res);
    }

    public void enVenta() {
        for (int i = 0; i < jugadores.get(0).getPropiedades().size(); i++) {
            consola.imprimir("{");
            consola.imprimir("  nombre: " + jugadores.get(0).getPropiedades().get(i).getNombre());
            if (jugadores.get(0).getPropiedades().get(i) instanceof Solar) {
                consola.imprimir("  tipo: solar");
            } else if (jugadores.get(0).getPropiedades().get(i) instanceof Transporte) {
                consola.imprimir("  tipo: transporte");
            } else {
                consola.imprimir("  tipo: servicio");
            }
            if (jugadores.get(0).getPropiedades().get(i) instanceof Solar) {
                consola.imprimir("  grupo: " + ((Solar) jugadores.get(0).getPropiedades().get(i)).getGrupo().getId());
            }
            consola.imprimir("  valor: " + jugadores.get(0).getPropiedades().get(i).getValorAct());
            consola.imprimir("{");
        }

    }

    public void listarEdificios() {
        for (int i = 0; i < 40; i++) {
            if (this.casillaPosicion(i) instanceof Solar) {
                for (int j = 0; j < ((Solar) this.casillaPosicion(i)).getEdificios().size(); j++) {
                    ((Solar) this.casillaPosicion(i)).getEdificios().get(j).imprimirEdificio();
                }
            }

        }

    }

    public void listarTratos(Jugador jugador) {
        for (int j = 0; j < jugador.getTratos().size(); j++) {
            if (!jugador.getNombre().equals(jugador.getTratos().get(j).getEmisor().getNombre())) {
                jugador.getTratos().get(j).imprimirTrato();
            }

        }

    }

    public void listarEdificiosGrupo(String grupo) {
        int p, contadorcasa = 0, contadorhotel = 0, contadorpiscina = 0, contadorpista = 0;
        String casa, hotel, pista, piscina;
        for (int i = 0; i < 40; i++) {
            if (this.casillaPosicion(i) instanceof Solar && ((Solar) this.casillaPosicion(i)).getGrupo().getId().equals(grupo)) {
                consola.imprimir("{");
                consola.imprimir("  nombre: " + this.casillaPosicion(i).getNombre());
                casa = "   casas: ";
                for (int j = 0; j < ((Solar) this.casillaPosicion(i)).getEdificios().size(); j++) {
                    if (((Solar) this.casillaPosicion(i)).getEdificios().get(j) instanceof Casa) {
                        casa += String.format("[" + ((Solar) this.casillaPosicion(i)).getEdificios().get(j).getId() + "]");
                        contadorcasa++;
                    }
                }
                casa += "\n";
                consola.imprimir(casa);
                hotel = "   hoteles: ";
                for (int j = 0; j < ((Solar) this.casillaPosicion(i)).getEdificios().size(); j++) {
                    if (((Solar) this.casillaPosicion(i)).getEdificios().get(j) instanceof Hotel) {
                        hotel += String.format("[" + ((Solar) this.casillaPosicion(i)).getEdificios().get(j).getId() + "]");
                        contadorcasa = contadorcasa + 4;
                        contadorhotel++;
                    }
                }
                hotel += "\n";
                consola.imprimir(hotel);
                piscina = "   piscinas: ";
                for (int j = 0; j < ((Solar) this.casillaPosicion(i)).getEdificios().size(); j++) {
                    if (((Solar) this.casillaPosicion(i)).getEdificios().get(j) instanceof Piscina) {
                        piscina += String.format("[" + ((Solar) this.casillaPosicion(i)).getEdificios().get(j).getId() + "]");
                        contadorpiscina++;
                    }
                }
                piscina += "\n";
                consola.imprimir(piscina);
                pista = "   pistaDeporte: ";
                for (int j = 0; j < ((Solar) this.casillaPosicion(i)).getEdificios().size(); j++) {
                    if (((Solar) this.casillaPosicion(i)).getEdificios().get(j) instanceof PistaDeporte) {
                        pista += String.format("[" + ((Solar) this.casillaPosicion(i)).getEdificios().get(j).getId() + "]");
                        contadorpista++;
                    }
                }
                pista += "\n";
                consola.imprimir(pista);
                Dado d = new Dado();
                consola.imprimir("   alquiler: " + ((Solar) this.casillaPosicion(i)).alquiler(d));
                consola.imprimir("}");
            }

        }
        if (grupo.equals("marron") || grupo.equals("azul")) {
            consola.imprimir("   Se pueden construir " + (10 - contadorcasa) + " casas, " + (2 - contadorhotel) + " hoteles, " + (2 - contadorpiscina) + " pisicnas, " + (2 - contadorpista) + " pistasDeporte");
        } else {
            consola.imprimir("   Se pueden construir " + (15 - contadorcasa) + " casas, " + (3 - contadorhotel) + " hoteles, " + (3 - contadorpiscina) + " pisicnas, " + (3 - contadorpista) + " pistasDeporte");
        }

    }

}
