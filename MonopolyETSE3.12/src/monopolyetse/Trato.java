package monopolyetse;

import datosjugador.Jugador;
import static monopolyetse.Juego.consola;

public class Trato {

    private int tipo;
    private String id;
    private Jugador emisor;
    private Jugador receptor;
    private Propiedad propiedad1;
    private Propiedad propiedad2;
    private long cantidad;
    private Propiedad propiedad3;
    private int numeroturnos;

    public Trato(Jugador emisor, Jugador receptor, Propiedad propiedad1, Propiedad propiedad2) {
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.emisor = emisor;
        this.receptor = receptor;
        tipo = 1;
        cantidad = 0;
        propiedad3 = null;
        numeroturnos = 0;
        id = "trato" + receptor.getAvatar().getTablero().getContadorTrato();
        receptor.getAvatar().getTablero().setContadorTrato((receptor.getAvatar().getTablero().getContadorTrato() + 1));
    }

    public Trato(Jugador emisor, Jugador receptor, Propiedad propiedad1, long cantidad) {
        this.propiedad1 = propiedad1;
        this.cantidad = cantidad;
        this.emisor = emisor;
        this.receptor = receptor;
        tipo = 2;
        propiedad2 = null;
        propiedad3 = null;
        numeroturnos = 0;
        id = "trato" + receptor.getAvatar().getTablero().getContadorTrato();
        receptor.getAvatar().getTablero().setContadorTrato((receptor.getAvatar().getTablero().getContadorTrato() + 1));
    }

    public Trato(Jugador emisor, Jugador receptor, long cantidad, Propiedad propiedad1) {
        this.propiedad1 = propiedad1;
        this.cantidad = cantidad;
        this.emisor = emisor;
        this.receptor = receptor;
        tipo = 3;
        propiedad2 = null;
        propiedad3 = null;
        numeroturnos = 0;
        id = "trato" + receptor.getAvatar().getTablero().getContadorTrato();
        receptor.getAvatar().getTablero().setContadorTrato((receptor.getAvatar().getTablero().getContadorTrato() + 1));
    }

    public Trato(Jugador emisor, Jugador receptor, Propiedad propiedad1, Propiedad propiedad2, long cantidad) {
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.cantidad = cantidad;
        this.emisor = emisor;
        this.receptor = receptor;
        tipo = 4;
        propiedad3 = null;
        numeroturnos = 0;
        id = "trato" + receptor.getAvatar().getTablero().getContadorTrato();
        receptor.getAvatar().getTablero().setContadorTrato((receptor.getAvatar().getTablero().getContadorTrato() + 1));
    }

    public Trato(Jugador emisor, Jugador receptor, Propiedad propiedad1, long cantidad, Propiedad propiedad2) {
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.cantidad = cantidad;
        this.emisor = emisor;
        this.receptor = receptor;
        tipo = 5;
        propiedad3 = null;
        numeroturnos = 0;
        id = "trato" + receptor.getAvatar().getTablero().getContadorTrato();
        receptor.getAvatar().getTablero().setContadorTrato((receptor.getAvatar().getTablero().getContadorTrato() + 1));
    }

    public Trato(Jugador emisor, Jugador receptor, Propiedad propiedad1, Propiedad propiedad2, Propiedad propiedad3, int numeroturnos) {
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.propiedad3 = propiedad3;
        this.emisor = emisor;
        this.receptor = receptor;
        this.numeroturnos = numeroturnos;
        tipo = 6;
        cantidad = 0;
        id = "trato" + receptor.getAvatar().getTablero().getContadorTrato();
        receptor.getAvatar().getTablero().setContadorTrato((receptor.getAvatar().getTablero().getContadorTrato() + 1));
    }

    public String getId() {
        return id;
    }

    public Jugador getReceptor() {
        return receptor;
    }

    public Jugador getEmisor() {
        return emisor;
    }

    public boolean ejecutarTrato() {
        boolean condicion1 = false, condicion2 = false, condicion3 = false;
        String trato="Se ha aceptado el siguiente trato con " + receptor.getNombre() + ": ";
        switch (tipo) {
            case 1:
                for (int i = 0; i < emisor.getPropiedades().size(); i++) {
                    if (emisor.getPropiedades().get(i).getNombre().equals(propiedad1.getNombre())) {
                        condicion1 = true;
                    }
                }
                for (int i = 0; i < receptor.getPropiedades().size(); i++) {
                    if (receptor.getPropiedades().get(i).getNombre().equals(propiedad2.getNombre())) {
                        condicion2 = true;
                    }

                }
                if (condicion1 && condicion2) {
                    emisor.getPropiedades().get(emisor.getPropiedades().indexOf(propiedad1)).anhadirPropietario(receptor);
                    emisor.getPropiedades().remove(emisor.getPropiedades().indexOf(propiedad1));
                    receptor.getPropiedades().get(receptor.getPropiedades().indexOf(propiedad2)).anhadirPropietario(emisor);
                    receptor.getPropiedades().remove(receptor.getPropiedades().indexOf(propiedad2));
                    trato+=String.format("cambiar(" + propiedad1.getNombre() + "," + propiedad2.getNombre() + ")");
                    consola.imprimir(trato);
                    return true;
                }
                break;
            case 2:
                for (int i = 0; i < emisor.getPropiedades().size(); i++) {
                    if (emisor.getPropiedades().get(i).getNombre().equals(propiedad1.getNombre())) {
                        condicion1 = true;
                    }
                }

                if (condicion1 && receptor.getFortuna() > cantidad) {
                    emisor.getPropiedades().get(emisor.getPropiedades().indexOf(propiedad1)).anhadirPropietario(receptor);
                    emisor.getPropiedades().remove(emisor.getPropiedades().indexOf(propiedad1));
                    emisor.setFortuna(emisor.getFortuna() + cantidad);
                    receptor.setFortuna(receptor.getFortuna() - cantidad);
                    trato+=String.format("cambiar(" + propiedad1.getNombre() + "," + cantidad + ")");
                    consola.imprimir(trato);
                    return true;
                }
                break;
            case 3:
                for (int i = 0; i < receptor.getPropiedades().size(); i++) {
                    if (receptor.getPropiedades().get(i).getNombre().equals(propiedad1.getNombre())) {
                        condicion1 = true;
                    }

                }
                if (condicion1 && emisor.getFortuna() > cantidad) {
                    receptor.getPropiedades().get(receptor.getPropiedades().indexOf(propiedad1)).anhadirPropietario(emisor);
                    receptor.getPropiedades().remove(receptor.getPropiedades().indexOf(propiedad1));
                    receptor.setFortuna(receptor.getFortuna() + cantidad);
                    emisor.setFortuna(emisor.getFortuna() - cantidad);
                    trato+=String.format("cambiar(" + cantidad + "," + propiedad1.getNombre() + ")");
                    consola.imprimir(trato);
                    return true;
                }
                break;
            case 4:
                for (int i = 0; i < emisor.getPropiedades().size(); i++) {
                    if (emisor.getPropiedades().get(i).getNombre().equals(propiedad1.getNombre())) {
                        condicion1 = true;
                    }
                }
                for (int i = 0; i < receptor.getPropiedades().size(); i++) {
                    if (receptor.getPropiedades().get(i).getNombre().equals(propiedad2.getNombre())) {
                        condicion2 = true;
                    }

                }
                if (condicion1 && condicion2 && receptor.getFortuna() > cantidad) {
                    emisor.getPropiedades().get(emisor.getPropiedades().indexOf(propiedad1)).anhadirPropietario(receptor);
                    emisor.getPropiedades().remove(emisor.getPropiedades().indexOf(propiedad1));
                    receptor.getPropiedades().get(receptor.getPropiedades().indexOf(propiedad2)).anhadirPropietario(emisor);
                    receptor.getPropiedades().remove(receptor.getPropiedades().indexOf(propiedad2));
                    emisor.setFortuna(emisor.getFortuna() + cantidad);
                    receptor.setFortuna(receptor.getFortuna() - cantidad);
                    trato+=String.format("cambiar(" + propiedad1.getNombre() + "," + propiedad2.getNombre() + " y " + cantidad + ")");
                    consola.imprimir(trato);
                    return true;
                }
                break;
            case 5:
                for (int i = 0; i < emisor.getPropiedades().size(); i++) {
                    if (emisor.getPropiedades().get(i).getNombre().equals(propiedad1.getNombre())) {
                        condicion1 = true;
                    }
                }
                for (int i = 0; i < receptor.getPropiedades().size(); i++) {
                    if (receptor.getPropiedades().get(i).getNombre().equals(propiedad2.getNombre())) {
                        condicion2 = true;
                    }

                }
                if (condicion1 && condicion2 && emisor.getFortuna() > cantidad) {

                    emisor.getPropiedades().get(emisor.getPropiedades().indexOf(propiedad1)).anhadirPropietario(receptor);
                    emisor.getPropiedades().remove(emisor.getPropiedades().indexOf(propiedad1));
                    receptor.getPropiedades().get(receptor.getPropiedades().indexOf(propiedad2)).anhadirPropietario(emisor);
                    receptor.getPropiedades().remove(receptor.getPropiedades().indexOf(propiedad2));
                    receptor.setFortuna(receptor.getFortuna() + cantidad);
                    emisor.setFortuna(emisor.getFortuna() - cantidad);
                    trato+=String.format("cambiar(" + propiedad1.getNombre() + " y " + cantidad + "," + propiedad2.getNombre() + ")");
                    consola.imprimir(trato);
                    return true;
                }
                break;
            case 6:
                for (int i = 0; i < emisor.getPropiedades().size(); i++) {
                    if (emisor.getPropiedades().get(i).getNombre().equals(propiedad1.getNombre())) {
                        condicion1 = true;
                    }
                }
                for (int i = 0; i < receptor.getPropiedades().size(); i++) {
                    if (receptor.getPropiedades().get(i).getNombre().equals(propiedad2.getNombre())) {
                        condicion2 = true;
                    }
                    if (receptor.getPropiedades().get(i).getNombre().equals(propiedad3.getNombre())) {
                        condicion3 = true;

                    }

                }
                if (condicion1 && condicion2 && condicion3) {
                    emisor.getPropiedades().get(emisor.getPropiedades().indexOf(propiedad1)).anhadirPropietario(receptor);
                    emisor.getPropiedades().remove(emisor.getPropiedades().indexOf(propiedad1));
                    receptor.getPropiedades().get(receptor.getPropiedades().indexOf(propiedad2)).anhadirPropietario(emisor);
                    receptor.getPropiedades().remove(receptor.getPropiedades().indexOf(propiedad2));
                    emisor.anhadirInmunidadAlquiler(numeroturnos);
                    emisor.anhadirListaBlanca(propiedad3);
                    trato+=String.format("cambiar(" + propiedad1.getNombre() + "," + propiedad2.getNombre() + " y no pagar alquiler de" + propiedad3.getNombre() + " durante:" + numeroturnos + ")");
                    consola.imprimir(trato);
                    return true;
                }
                break;
        }
        return false;
    }

    public void imprimirTrato() {
        consola.imprimir("JugadorPropone: " + emisor.getNombre());
        consola.imprimir("NombreTrato: " + id);
        String trato="Trato: ";
        switch (tipo) {
            case 1:
                trato+=String.format("cambiar(" + propiedad1.getNombre() + "," + propiedad2.getNombre() + ")");
                break;
            case 2:
                trato+=String.format("cambiar(" + propiedad1.getNombre() + "," + cantidad + ")");
                break;
            case 3:
                trato+=String.format("cambiar(" + cantidad + "," + propiedad1.getNombre() + ")");
                break;
            case 4:
                trato+=String.format("cambiar(" + propiedad1.getNombre() + "," + propiedad2.getNombre() + " y " + cantidad + ")");
                break;
            case 5:
                trato+=String.format("cambiar(" + propiedad1.getNombre() + " y " + cantidad + "," + propiedad2.getNombre() + ")");
                break;
            case 6:
                trato+=String.format("cambiar(" + propiedad1.getNombre() + "," + propiedad2.getNombre() + " y no pagar alquiler de" + propiedad3.getNombre() + " durante:" + numeroturnos + ")");
                break;
        }
        consola.imprimir(trato);

    }

}
