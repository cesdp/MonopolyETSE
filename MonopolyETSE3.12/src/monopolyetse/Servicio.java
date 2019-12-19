/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import datosjugador.Jugador;
import static monopolyetse.Juego.consola;
import static monopolyetse.Valores.PASOSALIDA;

/**
 *
 * @author cesdp
 */
public final class Servicio extends Propiedad{

    public Servicio(String name, Tablero valTab, long valCas, Jugador Banca) {
        super(name, valTab, valCas, Banca);
    }

    
    @Override
    public long alquiler(Dado dado) {
        long valor=0;
        int numerocasillas = 0;
        for (int i = 0; i < this.getPropietario().getPropiedades().size(); i++) {
            if ((this.getPropietario().getPropiedades().get(i)) instanceof Servicio) {
                numerocasillas = numerocasillas + 1;
            }
        }
        if (numerocasillas == 1) {
            valor = 4 * (dado.getValor1() + dado.getValor2()) * (PASOSALIDA / 200);
        }
        if (numerocasillas == 2) {
            valor = 10 * (dado.getValor1() + dado.getValor2()) * (PASOSALIDA / 200);
        }
        return valor;
    }
    
    @Override
    public void ImprimeCasilla() {
        consola.imprimir("{");
        consola.imprimir("  tipo: servicio");
        consola.imprimir("  propietario: " + this.getPropietario().getNombre());
        consola.imprimir("  valor: " + this.getValorIn());
        consola.imprimir("  factor servicio: " + Valores.PASOSALIDA / 200);
        consola.imprimir("}");
    }
    
}
