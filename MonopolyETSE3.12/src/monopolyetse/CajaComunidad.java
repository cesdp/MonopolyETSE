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
public class CajaComunidad extends Accion{

    
    public CajaComunidad(String name, Tablero valTab) {
        super(name, valTab);
    }
    
   @Override
    public void ImprimeCasilla() {
        super.ImprimeCasilla();
        consola.imprimir(" tipo: caja de comunidad");
        consola.imprimir("}");
    }
    
}
