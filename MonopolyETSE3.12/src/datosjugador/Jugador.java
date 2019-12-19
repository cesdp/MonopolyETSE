/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datosjugador;

import java.util.ArrayList;
import monopolyetse.Casa;
import monopolyetse.Casilla;
import monopolyetse.Dado;
import monopolyetse.Edificio;
import monopolyetse.Exceptionaccion;
import monopolyetse.Exceptionacciondados;
import monopolyetse.Hotel;
import static monopolyetse.Juego.consola;
import monopolyetse.PistaDeporte;
import monopolyetse.Propiedad;
import monopolyetse.Tablero;
import monopolyetse.Trato;
import static monopolyetse.Valores.FORTUNAINICIAL;

/**
 *
 * @author cesar
 */
public class Jugador {

    private String nombre;
    private Avatar avatar;
    private long fortuna;
    private long fortunapasada;
    private Propiedad casillapasada;
    private ArrayList<Propiedad> propiedades;
    private ArrayList<Edificio> edificios;
    private ArrayList<Propiedad> hipotecas;
    private ArrayList<Trato> tratos;
    private Tablero partida;
    private int turnosCarcel;
    private boolean tirada;
    private int dobles;
    private int vueltas;
    private boolean compras;
    private int penalizacion;
    private long dineroInvertido;
    private long pagoDeAlquileres;
    private long cobroDeAlquileres;
    private long pasarPorCasillaDeSalida;
    private long premiosInversionesOBote;
    private int vecesEnLaCarcel;
    private long pagoTasasEImpuestos;
    private int tiradasDados;
    private ArrayList<Integer> inmunidadalquiler;
    private ArrayList<Propiedad> listablanca;

    public long getPagoTasasEImpuestos() {
        return pagoTasasEImpuestos;
    }

    public void setPagoTasasEImpuestos(long pagoTasasEImpuestos) {
        if (pagoTasasEImpuestos >= 0) {
            this.pagoTasasEImpuestos = pagoTasasEImpuestos;
        }
    }

    public void setDineroInvertido(long dineroInvertido) {
        if (dineroInvertido >= 0) {
            this.dineroInvertido = dineroInvertido;
        }
    }

    public void setPagoDeAlquileres(long pagoDeAlquileres) {
        if (pagoDeAlquileres >= 0) {
            this.pagoDeAlquileres = pagoDeAlquileres;
        }
    }

    public void setCobroDeAlquileres(long cobroDeAlquileres) {
        if (cobroDeAlquileres >= 0) {
            this.cobroDeAlquileres = cobroDeAlquileres;
        }
    }

    public void setPasarPorCasillaDeSalida(long pasarPorCasillaDeSalida) {
        if (pasarPorCasillaDeSalida >= 0) {
            this.pasarPorCasillaDeSalida = pasarPorCasillaDeSalida;
        }
    }

    public void setPremiosInversionesOBote(long premiosInversionesOBote) {
        if (premiosInversionesOBote >= 0) {
            this.premiosInversionesOBote = premiosInversionesOBote;
        }
    }

    public void setVecesEnLaCarcel(int vecesEnLaCarcel) {
        if (vecesEnLaCarcel >= 0) {
            this.vecesEnLaCarcel = vecesEnLaCarcel;
        }
    }

    public void setFortunaPasada(long fortunap) {
        fortunapasada = fortunap;
    }

    public long getFortunaPasada() {
        return fortunapasada;
    }

    public long getDineroInvertido() {
        return dineroInvertido;
    }

    public long getPagoDeAlquileres() {
        return pagoDeAlquileres;
    }

    public long getCobroDeAlquileres() {
        return cobroDeAlquileres;
    }

    public long getPasarPorCasillaDeSalida() {
        return pasarPorCasillaDeSalida;
    }

    public long getPremiosInversionesOBote() {
        return premiosInversionesOBote;
    }

    public int getVecesEnLaCarcel() {
        return vecesEnLaCarcel;
    }

    public Jugador() {
        nombre = "";
        avatar = null; // Porque no va a aparecer en el tablero
        fortuna = 0;
        propiedades = null;
        edificios = null;
        hipotecas = null;
        partida = null;
        tratos = null;
        turnosCarcel = 0;
        tirada = false;
        dobles = 0;
        vueltas = 0;
        compras = false;
        penalizacion = 0;
        dineroInvertido = 0;
        pagoDeAlquileres = 0;
        cobroDeAlquileres = 0;
        pasarPorCasillaDeSalida = 0;
        premiosInversionesOBote = 0;
        vecesEnLaCarcel = 0;
        pagoTasasEImpuestos = 0;
        tiradasDados = 0;
        inmunidadalquiler = new ArrayList();
        listablanca = new ArrayList();
        fortunapasada = 0;
        casillapasada = null;
    }

    public Jugador(Tablero tab) {
        if (tab == null) {
            System.exit(1);
        }
        nombre = "Banca";
        avatar = null; // Porque no va a aparecer en el tablero
        fortuna = -1;
        propiedades = new ArrayList<>();
        edificios = null;
        hipotecas = null;
        tratos = null;
        partida = tab;
        turnosCarcel = 0;
        tirada = false;
        dobles = 0;
        vueltas = 0;
        compras = false;
        penalizacion = 0;
        dineroInvertido = 0;
        pagoDeAlquileres = 0;
        cobroDeAlquileres = 0;
        pasarPorCasillaDeSalida = 0;
        premiosInversionesOBote = 0;
        vecesEnLaCarcel = 0;
        pagoTasasEImpuestos = 0;
        tiradasDados = 0;
        inmunidadalquiler = new ArrayList();
        listablanca = new ArrayList();
        fortunapasada = 0;
        casillapasada = null;

    }

    public Jugador(String name, String Avatar, Tablero tab) {
        if (tab == null || name == null || Avatar == null) {
            System.exit(1);
        }
        nombre = name;
        switch (Avatar) {
            case "esfinge":
                avatar = new Esfinge(this, tab);
                break;
            case "sombrero":
                avatar = new Sombrero(this, tab);
                break;
            case "coche":
                avatar = new Coche(this, tab);
                break;
            case "pelota":
                avatar = new Pelota(this, tab);
                break;
        }
        fortuna = FORTUNAINICIAL;
        propiedades = new ArrayList<>();
        edificios = new ArrayList<>();
        hipotecas = new ArrayList<>();
        tratos = new ArrayList<>();
        partida = tab;
        turnosCarcel = 0;
        tirada = false;
        dobles = 0;
        vueltas = 0;
        compras = false;
        penalizacion = 0;
        dineroInvertido = 0;
        pagoDeAlquileres = 0;
        cobroDeAlquileres = 0;
        pasarPorCasillaDeSalida = 0;
        premiosInversionesOBote = 0;
        vecesEnLaCarcel = 0;
        pagoTasasEImpuestos = 0;
        tiradasDados = 0;
        inmunidadalquiler = new ArrayList();
        listablanca = new ArrayList();
        fortunapasada = 0;
        casillapasada = null;

    }

    public String getNombre() {
        return nombre;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public long getFortuna() {
        return fortuna;
    }

    public ArrayList<Propiedad> getPropiedades() {
        return propiedades;
    }

    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }

    public ArrayList<Propiedad> getHipotecas() {
        return hipotecas;
    }

    public int getTurnosCarcel() {
        return turnosCarcel;
    }

    public boolean getTirada() {
        return tirada;
    }

    public ArrayList<Propiedad> getListaBlanca() {
        return listablanca;
    }

    public int getVueltas() {
        return vueltas;
    }

    public int getDobles() {
        return dobles;
    }

    public Tablero getPartida() {
        return partida;
    }

    public ArrayList<Integer> getInmunidadAlquiler() {
        return inmunidadalquiler;
    }

    public Propiedad getCasillaPasada() {
        return casillapasada;
    }

    public void setNombre(String name) {
        if (name != null) {
            nombre = name;
        }
    }

    public void setAvatar(Avatar avat) {
        if (avat != null) {
            avatar = avat;
        }
    }

    public void setPropiedades(ArrayList<Propiedad> listacasillas) {
        if (listacasillas != null) {
            propiedades = listacasillas;
        }
    }

    public void setCasillaPasada(Propiedad casilla) {
        casillapasada = casilla;
    }

    public void setHipotecas(ArrayList<Propiedad> listacasillas) {
        if (listacasillas != null) {
            hipotecas = listacasillas;
        }
    }

    public void setTurnosCarcel(int t) {
        if (t >= 0 && t <= 5) {
            turnosCarcel = t;
        } else {
            consola.imprimir("Turno no valido");
        }
    }

    public void setFortuna(long dinero) {
        if (dinero >= 0) {
            fortuna = dinero;
        }
    }

    public void anhadirInmunidadAlquiler(int inmunidad) {
        inmunidadalquiler.add(inmunidad);

    }

    public void setTirada(boolean dato) {
        tirada = dato;
    }

    public void setVueltas(int nvuelta) {
        if (nvuelta >= 0) {
            vueltas = nvuelta;
        }
    }

    public void setTablero(Tablero valorTablero) {
        if (valorTablero != null) {
            partida = valorTablero;
        }
    }

    public void setEdificios(ArrayList<Edificio> edificio) {
        edificios = edificio;
    }

    public void anhadirPropiedades(Propiedad casilla) {
        if (casilla != null) {
            propiedades.add(casilla);
        }

    }

    public void anhadirListaBlanca(Propiedad casilla) {
        if (casilla != null) {
            listablanca.add(casilla);
        }

    }

    public void anhadirEdificios(Edificio edificacion) {
        if (edificacion != null) {
            edificios.add(edificacion);
        }

    }

    public void anhadirHipotecas(Propiedad casilla) {
        hipotecas.add(casilla);

    }

    public boolean getCompras() {
        return compras;
    }

    public void setCompras(boolean compras) {
        this.compras = compras;
    }

    public void setPenalizacion(int penalizacion) {
        if (penalizacion >= 0) {
            this.penalizacion = penalizacion;
        }
    }

    public int getPenalizacion() {
        return penalizacion;
    }

    public int getTiradasDados() {
        return tiradasDados;
    }

    public void setTiradasDados(int tiradasDados) {
        if (tiradasDados >= 0) {
            this.tiradasDados = tiradasDados;
        }
    }

    public ArrayList<Trato> getTratos() {
        return tratos;
    }

    public void anhadirTratos(Trato trato) {
        if (trato != null) {
            tratos.add(trato);
        }
    }

    public void lanzarDados(Dado n) throws Exceptionacciondados {
        int tiro;
        String nombrecasillaaux = "";

        if (tirada == true) {
            avatar.setRepeticiones(avatar.getRepeticiones() + 1);
            throw new Exceptionacciondados("No puede lanzar dados, debe acabar el turno");
        }

        if (turnosCarcel == 0 && tirada == false && penalizacion == 0) {
            tiradasDados++;
            tiro = n.Tirada();
            if (n.getValor1() == n.getValor2() && dobles < 2) {
                nombrecasillaaux = avatar.getUbicacion().getNombre();
                if (avatar.getModo().equals("normal")) {
                    avatar.moverEnBasico(tiro);
                } else {
                    avatar.moverEnAvanzado(tiro);
                }
                dobles++;
                consola.imprimir("El avatar " + avatar.getId() + " se mueve " + tiro + " posiciones, desde " + nombrecasillaaux + " hasta " + avatar.getUbicacion().getNombre() + ".Ha sacado dobles, vuelva a tirar");

            } else if (n.getValor1() == n.getValor2() && dobles == 2) {
                nombrecasillaaux = avatar.getUbicacion().getNombre();
                avatar.setUbicacion(partida.casillaPosicion(10));
                partida.casillaPosicion(10).setFrecuencia(partida.casillaPosicion(10).getFrecuencia() + 1);
                turnosCarcel = 1;
                tirada = true;
                vecesEnLaCarcel = vecesEnLaCarcel + 1;
                consola.imprimir("El avatar " + avatar.getId() + " se mueve" + tiro + " posiciones, desde " + nombrecasillaaux + " hasta " + avatar.getUbicacion().getNombre() + ".El jugador ha sacado tres veces dobles. El avatar se coloca en la casilla de " + avatar.getUbicacion().getNombre());

            } else {
                nombrecasillaaux = avatar.getUbicacion().getNombre();
                tirada = true;
                if (avatar.getModo().equals("normal")) {
                    avatar.moverEnBasico(tiro);
                } else {
                    avatar.moverEnAvanzado(tiro);
                }
                dobles = 0;
                consola.imprimir("El avatar " + avatar.getId() + " se mueve " + tiro + " posiciones, desde " + nombrecasillaaux + " hasta " + avatar.getUbicacion().getNombre());

            }
        }
        if (turnosCarcel > 0 && turnosCarcel <= 4 && tirada == false && penalizacion == 0) {
            n.Tirada();
            tiradasDados++;
            tirada = true;
            if (n.getValor1() == n.getValor2()) {
                consola.imprimir("Se han lanzado los dados y ha sacado dobles. Saldrá de la cárcel");
                tirada = false;
                turnosCarcel = 0;
            } else {
                consola.imprimir("Se han lanzado los dados y no ha sacado dobles");
            }
        }

    }

    public void lanzarDados(int n1, int n2) throws Exceptionacciondados {
        int tiro;
        String nombrecasillaaux = "";

        if (tirada == true) {
            avatar.setRepeticiones(avatar.getRepeticiones() + 1);
            throw new Exceptionacciondados("No puede lanzar dados, debe acabar el turno");
        }

        if (turnosCarcel == 0 && tirada == false && penalizacion == 0) {
            if (n1 == n2 && dobles < 3) {
                nombrecasillaaux = avatar.getUbicacion().getNombre();
                if (avatar.getModo().equals("normal")) {
                    avatar.moverEnBasico(n1);
                } else {
                    avatar.moverEnAvanzado(n1);
                }
                dobles++;
                consola.imprimir("El avatar " + avatar.getId() + " se mueve " + n1 + " posiciones, desde " + nombrecasillaaux + " hasta " + avatar.getUbicacion().getNombre() + ".Ha sacado dobles, vuelva a tirar");
            } else if (n1 == n2 && dobles == 3) {
                nombrecasillaaux = avatar.getUbicacion().getNombre();
                avatar.setUbicacion(partida.casillaPosicion(10));
                turnosCarcel = 1;
                tirada = true;
                consola.imprimir("El avatar " + avatar.getId() + " se mueve" + n1 + " posiciones, desde " + nombrecasillaaux + " hasta " + avatar.getUbicacion().getNombre() + ".El jugador ha sacado tres veces dobles. El avatar se coloca en la casilla de " + avatar.getUbicacion().getNombre());

            } else {
                nombrecasillaaux = avatar.getUbicacion().getNombre();
                tirada = true;
                if (avatar.getModo().equals("normal")) {
                    avatar.moverEnBasico(n1);
                } else {
                    avatar.moverEnAvanzado(n1);
                }
                dobles = 0;
                consola.imprimir("El avatar " + avatar.getId() + " se mueve " + n1 + " posiciones, desde " + nombrecasillaaux + " hasta " + avatar.getUbicacion().getNombre());
            }
        }
        if (turnosCarcel > 0 && turnosCarcel <= 4 && tirada == false && penalizacion == 0) {
            tirada = true;
            if (n1 == n2) {
                consola.imprimir("Se han lanzado los dados y ha sacado dobles. Saldrá de la cárcel");
                tirada = false;
                turnosCarcel = 0;
            } else {
                consola.imprimir("Se han lanzado los dados y no ha sacado dobles");
            }
        }
        if (penalizacion > 0) {
            consola.imprimir("Le quedan aun " + penalizacion + " para poder tirar");
        }

    }

    //en tablero,metodo que recupere los jugadores que esten jugando
    //lanzar dados:jugador
    //edificio, solo los nombres
    public void imprimirJugador() {
        consola.imprimir("{");
        consola.imprimir("  Nombre:" + nombre);
        consola.imprimir("  Avatar:" + avatar.getId());
        consola.imprimir("  Fortuna:" + fortuna);
        String propiedad = "  Propiedades: ";
        if (propiedades.size() > 0) {
            propiedad += String.format("[");
            for (int i = 0; i < propiedades.size() - 1; i++) {
                propiedad += String.format(propiedades.get(i).getNombre() + ", ");
            }
            propiedad += String.format(propiedades.get(propiedades.size() - 1).getNombre() + "]");
        } else {
            propiedad += String.format(" -");
        }
        consola.imprimir(propiedad);
        String hipoteca = "  Hipotecas:";
        if (hipotecas.size() > 0) {
            hipoteca += String.format("[");
            for (int i = 0; i < hipotecas.size() - 1; i++) {
                hipoteca += String.format(hipotecas.get(i).getNombre() + ", ");
            }
            hipoteca += String.format(hipotecas.get(hipotecas.size() - 1).getNombre() + "]");
        } else {
            hipoteca += String.format(" -");
        }
        consola.imprimir(hipoteca);
        String edificio = "  Edificios: ";
        if (edificios.size() > 0) {
            edificio += String.format("[");
            for (int i = 0; i < edificios.size() - 1; i++) {
                edificio += String.format(edificios.get(i).getId() + ", ");
            }
            edificio += String.format(edificios.get(edificios.size() - 1).getId() + "]");

        } else {
            edificio += String.format("-");
        }
        consola.imprimir(edificio);
        consola.imprimir("}");
    }

    public long calcularFortuna() {
        long valor = fortuna;
        for (Propiedad casilla : propiedades) {
            valor = valor + casilla.getValorAct();
        }
        for (Edificio edificio : edificios) {
            if (edificio instanceof Casa) {
                valor = valor + Math.round(((Propiedad) edificio.getUbicacion()).getValorIn() * 0.60);
            } else if (edificio instanceof Hotel) {
                valor = valor + Math.round(((Propiedad) edificio.getUbicacion()).getValorIn() * 0.60);
            } else if (edificio instanceof PistaDeporte) {
                valor = valor + Math.round(((Propiedad) edificio.getUbicacion()).getValorIn() * 1.25);
            } else {
                valor = valor + Math.round(((Propiedad) edificio.getUbicacion()).getValorIn() * 1.25);
            }
        }

        return valor;
    }
}
