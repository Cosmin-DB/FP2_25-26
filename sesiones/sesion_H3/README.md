# Hito 3 (pendiente)

Objetivo principal respecto a H2: introducir excepciones y pulir el flujo de error.

## Alcance propuesto

1. Mantener la misma mecanica y el mismo modelo de clases de H2.
2. No anadir nuevas funcionalidades de juego.
3. Centrar el trabajo en manejo de errores con excepciones.

## Pendiente de implementar

1. Definir excepciones de dominio necesarias (sin exceso):
   - indice invalido
   - inventario lleno
   - dinero insuficiente
   - objeto no equipable (si se mantiene este caso)
2. Sustituir retornos booleanos por excepciones donde tenga sentido en operaciones de dominio.
3. Decidir politica final:
   - errores de contrato -> excepcion
   - errores de juego -> excepcion o boolean (cerrar criterio unico)
4. Adaptar `Menu` y `Main` al nuevo flujo de errores (captura y mensaje coherente).
5. Introducir clase `Config` para centralizar constantes:
   - rutas de ficheros
   - capacidades (inventario personaje y tienda)
   - cualquier otro valor fijo repetido

## Criterio de cierre de H3

1. El sistema mantiene el comportamiento funcional de H2.
2. Los errores relevantes ya no se gestionan "a mano" con ifs dispersos.
3. Existe una estrategia unica y consistente de excepciones.
4. Las constantes principales ya no estan repartidas por clases.
