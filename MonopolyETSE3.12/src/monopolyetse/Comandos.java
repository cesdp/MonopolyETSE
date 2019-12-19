/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolyetse;

/**
 *
 * @author Usuario
 */
public interface Comandos {
    public void crear(String[] partes)throws Exceptioncreacion;
    public void jugador(String[] partes);
    public void listar(String[] partes);
    public void edificar(String[] partes)throws Exceptionaccionpropiedadedificios;
    public void vender(String[] partes);
    public void lanzar(String[] partes)throws Exceptionaccion,Exceptionacciondados;
    public void acabar(String[] partes)throws Exceptionaccion;
    public void salir(String[] partes);
    public void describir(String[] partes);
    public void comprar(String[] partes)throws Exceptionaccionpropiedad;
    public void hipotecar(String[] partes)throws Exceptionaccionpropiedadhipotecar;
    public void deshipotecar(String[] partes);
    public void cambiar(String[] partes);
    public void estadistica(String[] partes);
    public void trato(String[] partes);
    public void aceptar(String[] partes);
    public void eliminar(String[] partes);
    public void ver(String[] partes);
    public void set(String[] partes);
    
}
