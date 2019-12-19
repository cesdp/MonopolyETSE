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
public class Impuesto extends Casilla {

    private long valorIn;
    
    public Impuesto(String name, Tablero valTab,long valorIn) {
        super(name, valTab);
        this.valorIn=valorIn;
    }
    
    public long getValorIn() {
        return valorIn;
    }
    
    @Override
    public void ImprimeCasilla() {
        consola.imprimir("{");
        consola.imprimir("  tipo: impuesto");
        consola.imprimir("  apagar: " + valorIn);
        consola.imprimir("}");
    }

    
}
