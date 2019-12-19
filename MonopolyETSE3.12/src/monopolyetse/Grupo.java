/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import datosjugador.Jugador;
import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public class Grupo {

    private String id;
    private ArrayList<Solar> casillas;
    private ArrayList<Edificio> edificios;
    private String color;
    private Jugador jugador;
    private Boolean hipotecada; 

    public Grupo(String ident, String colores) {
        if(ident==null ||colores==null){
            System.exit(1);
        }
            id = ident;
            casillas = new ArrayList<>();
            edificios = new ArrayList<>();
            color = colores;
            jugador = new Jugador();
            hipotecada = false;
            
    }

    public String getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public ArrayList<Solar> getCasillas() {
        return casillas;
    }
    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }
    public long calcularValor(){
        long valor=0;
        for(Casilla k:casillas){
            valor=valor+((Propiedad)k).getRentabilidad();
        }
        return valor;
    }
    public void anhadirEdificios(Edificio edificacion) {
        edificios.add(edificacion);
    }
    public void anhadirCasillas(Solar casilla) {
        casillas.add(casilla);
    }
    public void setJugador(Jugador jugadoractual) {
        if(jugadoractual!=null && jugadoractual.getAvatar()!=null && jugadoractual.getAvatar().getTablero()!=null)
            jugador = jugadoractual;
    }
    public void setId(String Id) {
        if(Id!=null)
            id=Id;
    }
    public void setColor(String colour) {
        if(colour!=null)
            color = colour;
    }
    public void setCasilla(ArrayList<Solar> listacasillas) {
        if(listacasillas!=null)
            casillas = listacasillas;
    }
    public Boolean isHipotecada() {
        return hipotecada;
    }
    public void setHipotecada(Boolean hip) {
            hipotecada=hip;
    }
}
