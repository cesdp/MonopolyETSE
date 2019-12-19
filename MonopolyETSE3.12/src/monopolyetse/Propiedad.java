/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import datosjugador.Coche;
import datosjugador.Jugador;
import static monopolyetse.Juego.consola;

/**
 *
 * @author cesdp
 */
public abstract class Propiedad extends Casilla {

    private Jugador propietario;
    private boolean hipotecada;
    private long rentabilidad;
    private long valorIn;
    private long valorAct;

    public Propiedad(String name, Tablero valTab, long valCas, Jugador Banca) {
        super(name, valTab);
        propietario = Banca;
        propietario.anhadirPropiedades(this);
        hipotecada = false;
        rentabilidad = 0;
        valorIn = valCas;
        valorAct = valorIn;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public boolean isHipotecada() {
        return hipotecada;
    }

    public long getRentabilidad() {
        return rentabilidad;
    }

    public long getValorIn() {
        return valorIn;
    }

    public long getValorAct() {
        return valorAct;
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }

    public void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }

    public void setRentabilidad(long rentabilidad) {
        this.rentabilidad = rentabilidad;
    }

    public void setValorIn(long valorIn) {
        this.valorIn = valorIn;
    }

    public void setValorAct(long valorAct) {
        this.valorAct = valorAct;
    }

    public abstract long alquiler(Dado dado);

    public long valor() {
        return valorAct;
    }

    public void comprar(Jugador jugador) {
        jugador.setFortuna(jugador.getFortuna() - ((Propiedad) jugador.getAvatar().getUbicacion()).getValorAct());
        ((Propiedad) jugador.getAvatar().getUbicacion()).anhadirPropietario(jugador);
        jugador.setCompras(true);
        jugador.setCasillaPasada(null);
        jugador.setCasillaPasada((Propiedad) jugador.getAvatar().getUbicacion());
        jugador.setDineroInvertido(jugador.getDineroInvertido() + ((Propiedad) jugador.getAvatar().getUbicacion()).getValorAct());

    }

    public boolean perteneceAJugador(Jugador jugador) {
        return this.propietario.getNombre().equals(jugador.getNombre());
    }

    public void anhadirPropietario(Jugador jugador) {
        propietario = jugador;
        jugador.anhadirPropiedades(this);
    }

}
