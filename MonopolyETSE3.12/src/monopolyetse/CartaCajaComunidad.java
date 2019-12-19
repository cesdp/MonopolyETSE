/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import static monopolyetse.Juego.consola;

/**
 *
 * @author cesdp
 */
public class CartaCajaComunidad extends Carta {
    private int id;
    
    public CartaCajaComunidad(int id,Tablero tablero) {
        super(tablero);
        if((id<=6 && id>0)){
            this.id=id;
        }
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        if(id>0 && id<=6)
            this.id = id;
    }

    @Override
    public void accion() {
       switch (id) {
            case 1:
                consola.imprimir("Te investigan por fraude de identidad. Ve a la Cárcel.Ve directamente sin pasar por la casilla de Salida y sin cobrar los " + Valores.PASOSALIDA + "€.");
                this.getReceptor().getAvatar().setUbicacion(this.getTablero().casillaPosicion(10));
                this.getTablero().casillaPosicion(10).setFrecuencia(this.getTablero().casillaPosicion(10).getFrecuencia() + 1);
                this.getReceptor().setTurnosCarcel(1);
                this.getReceptor().setVecesEnLaCarcel(this.getReceptor().getVecesEnLaCarcel() + 1);
                break;
            case 2:
                consola.imprimir("Colócate en la casilla de Salida.Cobra" + Valores.PASOSALIDA + "€.");
                this.getReceptor().getAvatar().setUbicacion(this.getTablero().casillaPosicion(0));
                this.getTablero().casillaPosicion(0).setFrecuencia(this.getTablero().casillaPosicion(0).getFrecuencia() + 1);
                this.getReceptor().setFortuna(this.getReceptor().getFortuna() + Valores.PASOSALIDA);
                this.getReceptor().setPasarPorCasillaDeSalida(this.getReceptor().getPasarPorCasillaDeSalida() + Valores.PASOSALIDA);
                break;
            case 3:
                consola.imprimir("Devolución de Hacienda. Cobra 325€");
                this.getReceptor().setFortuna(this.getReceptor().getFortuna() + 325);
                this.getReceptor().setPremiosInversionesOBote(this.getReceptor().getPremiosInversionesOBote() + 325);
                break;
            case 4:
                consola.imprimir("Alquilas a tus compañeros una casa de Turismo Rural en Entrimo durante una seman.Paga 130€ a cada jugador");
                for (int i = 1; i < this.getTablero().getJugadores().size(); i++) {
                     if (!this.getReceptor().equals(this.getTablero().getJugadores().get(i))) {
                        this.getReceptor().setFortuna(this.getReceptor().getFortuna() - 130);
                        this.getTablero().getJugadores().get(i).setFortuna(this.getTablero().getJugadores().get(i).getFortuna() + 130);
                        this.getReceptor().setPagoTasasEImpuestos(this.getReceptor().getPagoTasasEImpuestos() + 130);
                    }
                }
                break;
            case 5:
                consola.imprimir("Recibe 650€ de beneficios por alquilar los servicios de tu jet privado. ");
                this.getReceptor().setFortuna(this.getReceptor().getFortuna() + 650);
                this.getReceptor().setPremiosInversionesOBote(this.getReceptor().getPremiosInversionesOBote() + 650);
                break;
            case 6:
                consola.imprimir("Tu compañía de Internet obtiene beneficios. Recibe " + Valores.PASOSALIDA + "€.");
                this.getReceptor().setFortuna(this.getReceptor().getFortuna() + Valores.PASOSALIDA);
                this.getReceptor().setPremiosInversionesOBote(this.getReceptor().getPremiosInversionesOBote() + Valores.PASOSALIDA);
                break;
        }
    }
}
