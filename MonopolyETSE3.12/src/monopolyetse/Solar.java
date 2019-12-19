
package monopolyetse;

import datosjugador.Jugador;
import java.util.ArrayList;
import static monopolyetse.Juego.consola;

/**
 *
 * @author cesdp
 */
public final class Solar extends Propiedad{
    
    private Grupo grupo;
    private boolean edificar;
    private int paso;
    private ArrayList<Edificio> edificios;
    
    public Solar(String name, Tablero valTab, long valCas, Jugador Banca,Grupo grp) {
        super(name, valTab, valCas, Banca);
        grupo=grp;
        edificar=false;
        paso=0;
        edificios=new ArrayList<>();
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public boolean isEdificar() {
        return edificar;
    }

    public int getPaso() {
        return paso;
    }

    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void setEdificar(boolean edificar) {
        this.edificar = edificar;
    }

    public void setPaso(int paso) {
        this.paso = paso;
    }

    public void setEdificios(ArrayList<Edificio> edificios) {
        this.edificios = edificios;
    }
    
    @Override
     public long alquiler(Dado dado){
        int contador=0; 
        long valorbase;
        long valor;
        if (grupo.getJugador().getNombre().equals(this.getPropietario().getNombre())) {
                valor = this.getValorIn() / 5;
                valorbase = this.getValorIn()/ 5;
        } else {
                valor = this.getValorIn() / 10;
                valorbase = this.getValorIn() / 10;
        }    
        if (edificios.size() > 0) {
            valor = 0;
            valorbase = this.getValorIn()/10;
            for (int i = 0; i < edificios.size(); i++) {
                if(edificios.get(i) instanceof Casa){
                        contador++;
                }
                else if(edificios.get(i) instanceof Hotel){
                        valor = valor + (valorbase * 70);
                }
                else if(edificios.get(i) instanceof Piscina){
                        valor = valor + (valorbase * 25);
                }
                else{
                        valor = valor + (valorbase * 25);
                }

            }
            switch (contador) {
                case 1:
                    valor = valor + (valorbase * 5);
                    break;
                case 2:
                    valor = valor + (valorbase * 15);
                    break;
                case 3:
                    valor = valor + (valorbase * 35);
                    break;
                case 4:
                    valor = valor + (valorbase * 50);
                    break;
                    }
        }
        return valor;
     }

    @Override
    public void anhadirPropietario(Jugador jugador) {
        super.anhadirPropietario(jugador);
        int i, grupoigual = 0;
            for (i = 0; i <super.getPropietario().getPropiedades().size(); i++) {
                if (super.getPropietario().getPropiedades().get(i) instanceof Solar) {
                    if (((Solar)(super.getPropietario().getPropiedades().get(i))).getGrupo().getId().equals(grupo.getId())) {
                        grupoigual = grupoigual + 1;
                    }
                }
            }
            if (grupo.getId().equals("marron") || grupo.getId().equals("azul")) {

                if (grupoigual == 2) {
                    grupo.setJugador(super.getPropietario());
                    for (i = 0; i < grupo.getCasillas().size(); i++) {
                        ((Solar)grupo.getCasillas().get(i)).setEdificar(true);
                    }
                }
            } else {
                if (grupoigual == 3) {
                    grupo.setJugador(super.getPropietario());
                    for (i = 0; i < grupo.getCasillas().size(); i++) {
                        ((Solar)grupo.getCasillas().get(i)).setEdificar(true);
                    }
                }
            }
        

    }
    public void EliminarEdificios(String nombre,int cantidad) {
        int contador = 0;
        switch (nombre){
            case "casa":
                for (int i = 1; i <= edificios.size(); i++) {       
                if (edificios.get(edificios.size() - i) instanceof Casa && contador < cantidad) {
                    for (int j = 1; j <= super.getPropietario().getEdificios().size(); j++) {
                        if (super.getPropietario().getEdificios().get(super.getPropietario().getEdificios().size() - j).getId().equals(edificios.get(edificios.size() - i).getId())) {
                            super.getPropietario().getEdificios().remove(super.getPropietario().getEdificios().size() - j);
                            j--;
                        }
                    }
                    for (int j = 1; j <= grupo.getEdificios().size(); j++) {
                        if (grupo.getEdificios().get(grupo.getEdificios().size() - j).getId().equals(edificios.get(edificios.size() - i).getId())) {
                            grupo.getEdificios().remove(grupo.getEdificios().size() - j);
                            j--;
                        }
                    }
                    edificios.remove(edificios.size() - i);
                    i--;
                    contador++;
                    }
                }
                break;
            case "hotel":
                for (int i = 1; i <= edificios.size(); i++) {       
                if (edificios.get(edificios.size() - i) instanceof Hotel && contador < cantidad) {
                    for (int j = 1; j <= super.getPropietario().getEdificios().size(); j++) {
                        if (super.getPropietario().getEdificios().get(super.getPropietario().getEdificios().size() - j).getId().equals(edificios.get(edificios.size() - i).getId())) {
                            super.getPropietario().getEdificios().remove(super.getPropietario().getEdificios().size() - j);
                            j--;
                        }
                    }
                    for (int j = 1; j <= grupo.getEdificios().size(); j++) {
                        if (grupo.getEdificios().get(grupo.getEdificios().size() - j).getId().equals(edificios.get(edificios.size() - i).getId())) {
                            grupo.getEdificios().remove(grupo.getEdificios().size() - j);
                            j--;
                        }
                    }
                    edificios.remove(edificios.size() - i);
                    i--;
                    contador++;
                    }
                }
                break;
            case "piscina":
                for (int i = 1; i <= edificios.size(); i++) {       
                if (edificios.get(edificios.size() - i) instanceof Piscina && contador < cantidad) {
                    for (int j = 1; j <= super.getPropietario().getEdificios().size(); j++) {
                        if (super.getPropietario().getEdificios().get(super.getPropietario().getEdificios().size() - j).getId().equals(edificios.get(edificios.size() - i).getId())) {
                            super.getPropietario().getEdificios().remove(super.getPropietario().getEdificios().size() - j);
                            j--;
                        }
                    }
                    for (int j = 1; j <= grupo.getEdificios().size(); j++) {
                        if (grupo.getEdificios().get(grupo.getEdificios().size() - j).getId().equals(edificios.get(edificios.size() - i).getId())) {
                            grupo.getEdificios().remove(grupo.getEdificios().size() - j);
                            j--;
                        }
                    }
                    edificios.remove(edificios.size() - i);
                    i--;
                    contador++;
                    }
                }
                break;
            case "pistaDeporte":
                for (int i = 1; i <= edificios.size(); i++) {       
                if (edificios.get(edificios.size() - i) instanceof PistaDeporte && contador < cantidad) {
                    for (int j = 1; j <= super.getPropietario().getEdificios().size(); j++) {
                        if (super.getPropietario().getEdificios().get(super.getPropietario().getEdificios().size() - j).getId().equals(edificios.get(edificios.size() - i).getId())) {
                            super.getPropietario().getEdificios().remove(super.getPropietario().getEdificios().size() - j);
                            j--;
                        }
                    }
                    for (int j = 1; j <= grupo.getEdificios().size(); j++) {
                        if (grupo.getEdificios().get(grupo.getEdificios().size() - j).getId().equals(edificios.get(edificios.size() - i).getId())) {
                            grupo.getEdificios().remove(grupo.getEdificios().size() - j);
                            j--;
                        }
                    }
                    edificios.remove(edificios.size() - i);
                    i--;
                    contador++;
                    }
                }
                break;
        }    
        
        

    }
    public void Edificar(String nombre) {
        Edificio edificacion=null;
        switch (nombre){
            case "casa":
                edificacion = new Casa(super.getTablero(), this);
                break;
            case "hotel":
                edificacion = new Hotel(super.getTablero(), this);
                break;
            case "piscina":
                edificacion = new Piscina(super.getTablero(), this);
                break;
            case "pistaDeporte":
                edificacion = new PistaDeporte(super.getTablero(), this);
                break;
        } 
        if (nombre.equals("hotel")) {
            EliminarEdificios("casa",4);

        }
        edificios.add(edificacion);
        super.getPropietario().anhadirEdificios(edificacion);
        grupo.anhadirEdificios(edificacion);

    }

    @Override
    public void ImprimeCasilla() {
        long valorbase;
        if (grupo.getJugador().getNombre().equals(this.getPropietario().getNombre())) {
                valorbase = this.getValorIn()/ 5;
        } else {
                valorbase = this.getValorIn() / 10;
        }    
        String edificio;
        consola.imprimir("{");
        consola.imprimir("  tipo: solar");
        consola.imprimir("  grupo: " + grupo.getId());
        consola.imprimir("  valor: " + this.getValorAct());
        consola.imprimir("  propietario: " + this.getPropietario().getNombre());
        consola.imprimir("  alquiler: " + this.alquiler(null));
        edificio="  Edificios: ";
        if (edificios.size() > 0) {
            edificio+=String.format("[");
            for (int i = 0; i < edificios.size() - 1; i++) {
                edificio+=String.format(edificios.get(i).getId() + ", ");
            }
            edificio+=String.format(edificios.get(edificios.size() - 1).getId() + "]\n");

        } else {
            edificio+=String.format("-");
        }
        consola.imprimir(edificio);
        consola.imprimir("  valor hotel: " + Math.round(0.6 * this.getValorIn()) + "\n"
            + "  valor casa: " + Math.round(0.6 * this.getValorIn()) + "\n"
            + "  valor piscina: " + Math.round(0.4 * this.getValorIn()) + "\n"
            + "  valor pista de deporte: " + Math.round(1.25 * this.getValorIn()) + "\n"
            + "  alquiler una casa: " + Math.round(5 * valorbase) + "\n"
            + "  alquiler dos casas: " + Math.round(15 * valorbase) + "\n"
            + "  alquiler tres casas: " + Math.round(35 *  valorbase) + "\n"
            + "  alquiler cuatro casas: " + Math.round(50 * valorbase) + "\n"
            + "  alquiler hotel: " + Math.round(70 * valorbase) + "\n"
            + "  alquiler piscina: " + Math.round(25 * this.getValorIn()) + "\n"
            + "  alquiler pista de deporte: " + Math.round(25 * this.getValorIn()));
        consola.imprimir("}");
    }
    
}
