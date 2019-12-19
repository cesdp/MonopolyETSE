
package monopolyetse;

import datosjugador.Avatar;
import java.util.ArrayList;


public abstract class Casilla {

    private String nombre;
    private Tablero tablero;
    private long frecuencia;

      public Casilla(String name, Tablero valTab) {
        if (name == null || valTab == null ) {
            System.exit(1);
        }
        nombre = name;
        tablero = valTab;
    }

    
    public String getNombre() {
        return nombre;
    }
    public Tablero getTablero() {
        return tablero;
    }

    public long getFrecuencia() {
        return frecuencia;
    }
    
    public void setNombre(String name) {
        if (name != null) {
            nombre = name;
        }
    }

    public void setTablero(Tablero valorTablero) {
        if (valorTablero != null) {
            tablero = valorTablero;
        }
    }

    public void setFrecuencia(long frecuencia) {
        if(frecuencia>=0)
            this.frecuencia = frecuencia;
    }
    
    public abstract void ImprimeCasilla() ;
    
    public boolean estaAvatar(){//Pendiente de revisión
        boolean a=false;
        for(int i=1; i<tablero.getJugadores().size();i++){
            a= a || tablero.PosicionCasilla(tablero.getJugadores().get(i).getAvatar().getUbicacion())==(tablero.PosicionCasilla(this));
        }
        return a;
    }
    
    public long frecuenciaVisita(){
        return frecuencia;
    }
    @Override
    public String toString() {
        ArrayList<Avatar> avt;
        String texto = "";

        if (this instanceof Solar) {
            texto += ((Solar)this).getGrupo().getColor();
        }

        texto += nombre + " ";
        if (tablero.getAvatares().size() > 0) {
            avt = tablero.casillaJugador(this);
            if (avt.size() > 0) {
                texto += "&";
                for (int i = 0; i < avt.size(); i++) {
                    texto += avt.get(i).getId();
                }
            }
        }
        texto += Valores.NEGRO;
        return texto;
    }
}
