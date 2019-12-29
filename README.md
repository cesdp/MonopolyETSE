# MonopolyETSE
## Introduccion
Esta aplicación ha sido realizada en la asignatura de Programación Orientada a Objetos, con la base de conocer el paradigma con ese nombre, como primera toma de contacto con el lenguaje Java y este paradigma.

Como su nombre indica es una implementación del clásico juego del Monopoly. Este se habrá realizado integramente por línea de comandos. La aplicación presentará al igual que el clásico: visualización del tablero, elección de figuras, compra de casillas y edificaciones, y realización de tratos, entre otras.

## Comandos a usar
### Crear jugador
Para iniciar el juego será necesaria la creación de dos o más jugadores, no permitiendo iniciar el juego, para lo cual habrá que ejecutar el siguiente comando:

  ```
 crear jugador nombre avatar
  ```
Para iniciar el juego habrá que lanzar los dados con el comando:
  ```
 lanzar dados
  ```
  
### Consultar tablero
Los jugadores puede ver el tablero en cualquier momento de la partida

  ```
 ver tablero
  ```
### Comprar propiedades
Los jugadores pueden comprar la casilla sobre la que se encuentran si está no pertenece a ningún otro uusario y es una propiedad para ello habrá que usar el comando siguiente, donde *casilla* será el nombre de la casilla

 ```
 comprar casilla
  ```
### Construir edificio
Una vez que se tiene el grupo de propiedades del mismo color se puede edificar en ella , para eso se ha de estar en esa casilla e indicar el tipo de edificio como *edificio* que podrá ser: **casa,hotel,piscina y pista de Deporte**

 ```
 edificar edificio
  ```
### Hacer trato
Un jugador tendrá la posibilidad de realizar un trato con otro, siendo objeto de este dinero o propiedades, o la omisión del pago de alquiler durante unos turnos. Este trato será recibido por el otro jugador, siendo este el que tiene la potestad de aceptarlo o rechazarlo. Para aceptar el trato se ha de estar en el turno del receptor.


 ```
 trato Jugador Propiedad Propiedad
 
 trato Jugador Propiedad Dinero
 
 acpetar trato 
 
 eliminar trato 
  ```
El valor de *trato* será el número del mismo, denotado en la pantalla de inicio del receptor.

### Funcionalidades añadidas
A mayores de las funcionalidades clásicas aportadas por el juego se habrán incoporado otras indicadas por los requerimientos de la práctica.
#### Cambiar de modo
El jugador podrá elegir un **modo avanzado** para el movimiento de los diferentes avatares, el cual dependerá de la figura elegida previamente por el usuario. Los tipos serán: *esfinge*,*sombrero*,*coche* y *pelota*.

Este **modo avanzado** puede ser activado o desactivado a petición del usuario durante la ejecución del juego. Dichas acciones especiales son:

* **Sombrero**: movimiento en zig-zag del norte al sur del tablero.

* **Coche**: permite que el jugador tire el dado varias veces en el mismo turno, y si es un valor inferior a uno especificado retrocederá.

* **Pelota**: permite que el uusario interaccione con las casillas que se mueve, este movimiento puede ser hacia delante o hacia atrás en función de los datos, botando en varias vcasillas hasta llegar al resultado de la tirada.

* **Esfinge**: movimiento en zig-zag del norte al sur del talero.


 ```
 cambiar modo
  ```

#### Estadísticas
Se habrá incorporado la posibilidad de obtener estadísticas del juego para informar a las usuarios sobre determinados parámetros de interés, como serán estadisticas del *usuario*, siendo este indicado por el nombre, sin especificar nada, se denotarán unas estadísticas generales del juego.


 ```
 estadisticas usuario
 
 estadisticas
  ```

## Dinámica y objetivo del juego

El juego, como el clásico Mnopoly, consistirá en ir comprando propiedades para ir incrementando los bienes del usuario, e ir intentando arruinar a los otros.

El **ganador** del juego será el último que posea dinero o propiedades sin tener deudas.

## ¿Cómo ejecutar el juego?
El juego fue desarrollado con el IDE **Netbeans** en **Java** en su versión 8. Se puede ejecutar cargando la carpeta en el IDE. Otra forma es ejecutando el .jar adjunto en la carpeta *dist* cuyo nombre será *MonopolyETSE3.10.jar*.

  ```
java -jar "MonopolyETSE3.10.jar" 
  ```
## Autores
* [César Díaz Parga](https://github.com/cesdp)
* Fernando Arrojo Alonso
