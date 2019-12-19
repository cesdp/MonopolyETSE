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
public class CartaSuerte extends Carta {
    
    private int id;
    
    public CartaSuerte(int id,Tablero tablero) {
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
        long coste=0;
        switch (id) {
            case (1):
                consola.imprimir("Decides hacer un viaje de placer. Avanza hasta Vigo\n");
                this.getReceptor().getAvatar().setUbicacion(this.getTablero().casillaPosicion(39));
                this.getTablero().casillaPosicion(39).setFrecuencia(this.getTablero().casillaPosicion(39).getFrecuencia() + 1);
                Ejecucion nueva=new Ejecucion(this.getTablero());
                nueva.realizarAccion(this.getReceptor().getAvatar().getUbicacion(), this.getReceptor(),this.getTablero(), new Dado());
                break;
            case (2):
                consola.imprimir("Has sido elegido presidente de la junta directiva. Paga a cada jugador 163€.");
                for (int i = 1; i < this.getTablero().getJugadores().size(); i++) {
                    if (!this.getReceptor().equals(this.getTablero().getJugadores().get(i))) {
                        this.getReceptor().setFortuna(this.getReceptor().getFortuna() - 163);
                        this.getTablero().getJugadores().get(i).setFortuna(this.getTablero().getJugadores().get(i).getFortuna() + 163);
                        this.getReceptor().setPagoTasasEImpuestos(this.getReceptor().getPagoTasasEImpuestos() + 163);
                    }
                }
                break;
            case (3):
                consola.imprimir("Te multan por usar el móvil mientras conduces. Paga 98€. ");
                if ((this.getReceptor().getFortuna() - 98) <= 0) {
                    consola.imprimir("No dispone de dinero.Debe hipotecar");
                } else {
                    this.getReceptor().setFortuna(this.getReceptor().getFortuna() - 98);
                    this.getReceptor().setPagoTasasEImpuestos(this.getReceptor().getPagoTasasEImpuestos() + 98);
                    this.getTablero().SetBote(this.getTablero().getBote()+98);
                }
                break;
            case (4):
                consola.imprimir("El aumento del impuesto sobre bienes inmuebles afecta a todas tus propiedades. Paga 26€ por casa, 748€ por hotel, 130€ por piscina y 488€ por pista de deportes.");
                coste=0;
                for (Edificio edificio : this.getReceptor().getEdificios()) {
                    if (edificio instanceof Casa) {
                        coste = coste + 26;
                    } else if (edificio instanceof Hotel) {
                        coste = coste + 748;
                    } else if (edificio instanceof PistaDeporte) {
                        coste = coste + 488;
                    } else {
                        coste = coste + 130;
                    }
                }
                if (this.getReceptor().getFortuna() - coste < 0) {
                    consola.imprimir("No tiene dinero suficiente,debe vender o hipotecar");

                } else {
                    this.getReceptor().setFortuna(this.getReceptor().getFortuna() - coste);
                    this.getTablero().SetBote(this.getTablero().getBote()+coste);
                }
                    break;
            case (5):
                consola.imprimir("¡Has ganado el bote de la lotería!Recibe 650€");
                this.getReceptor().setFortuna(this.getReceptor().getFortuna() + 650);
                this.getReceptor().setPremiosInversionesOBote(this.getReceptor().getPremiosInversionesOBote() + 650);
                break;
            case (6):
                consola.imprimir("Vendes tu billete de avión a A Coruña en una subasta por internet.Cobras 325€ .");
                this.getReceptor().setFortuna(this.getReceptor().getFortuna() + 325);
                this.getReceptor().setPremiosInversionesOBote(this.getReceptor().getPremiosInversionesOBote() + 325);
                break;
        }
    }
    
    
    
}
