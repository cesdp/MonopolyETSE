/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import datosjugador.Jugador;
import static monopolyetse.Juego.consola;

/**
 *
 * @author cesdp
 */
public class Transporte extends Propiedad{

    public Transporte(String name, Tablero valTab, long valCas, Jugador Banca) {
        super(name, valTab, valCas, Banca);
    }
    
    @Override
    public long alquiler(Dado dado) {
        long valor;
        int numerocasillas = 0;
        for (int i = 0; i < this.getPropietario().getPropiedades().size(); i++) {
            if ((this.getPropietario().getPropiedades().get(i)) instanceof Transporte) {
                        numerocasillas = numerocasillas + 1;
                    }
            }
            valor = this.getValorIn() / (5 - numerocasillas);
        return valor;        
    }
    
   
    @Override
    public void ImprimeCasilla() {
        consola.imprimir("{");
        consola.imprimir("  tipo: transporte" );
        consola.imprimir("  propietario: " + super.getPropietario().getNombre());
        consola.imprimir("  valor: " + super.getValorIn());
        consola.imprimir("  operacion transporte: " + super.getValorIn());
        consola.imprimir("}");
    }

    
    
    
    
}
