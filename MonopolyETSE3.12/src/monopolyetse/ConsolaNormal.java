/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import java.util.Scanner;

/**
 *
 * @author cesdp
 */
public class ConsolaNormal implements Consola  {

    @Override
    public void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public String leer(String descripcion) {
        System.out.print(descripcion);
        Scanner scanner = new Scanner(System.in);
        String lectura = scanner.nextLine();
        return lectura;
    }
    
}
