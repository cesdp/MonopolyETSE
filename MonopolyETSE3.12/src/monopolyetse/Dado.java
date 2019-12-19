/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

import java.util.Random;

/**
 *
 * @author cesar
 */
public class Dado {
    
    private int valor1;
    private int valor2;
    
    public Dado(){
        valor1=0;
        valor2=0;
    }
    public int getValor1(){
        return valor1;
    }
    public int getValor2(){
        return valor2;
    }
    public void setValor1(int valor){
        if(valor>0 && valor<7)
            valor1=valor;
    }
    public void setValor2(int valor){
        if(valor>0 && valor<7)
            valor2=valor;
    }
    
    public int Tirada(){
        
        Random num=new Random();
        
        valor1=num.nextInt(6-1+1)+1;
        valor2=num.nextInt(6-1+1)+1;
        return valor1+valor2;
    }
}
