# Reflexion H4 de recuperacion

Fecha: 2026-06-08

Estado: primer borrador de enunciado redactado en
`enunciado/memoria_H4.tex`. Pendiente de revision final.

## Contexto

El H4 se plantea para alumnado que no ha superado la practica. La idea no es
crear una practica nueva, sino pedir que rehagan/corrijan H1, H2 y H3, y anadir
un hito adicional pequeno pero integrador.

La dificultad global del H4 no debe superar claramente al hito mas dificil ya
realizado. Si lo supera, debe hacerlo solo ligeramente. El reto debe estar en
entender y extender la practica existente, no en introducir tecnologias o
estructuras nuevas.

## Acuerdo provisional

La linea que parece mejor equilibrada es:

- limite de peso en el inventario del personaje;
- nueva excepcion `PesoMaximoSuperadoException`;
- transferencia segura de objetos entre contenedores;
- venta de objetos del personaje a la tienda;
- posible transferencia o venta de objetos entre personajes.

La idea general es trabajar el patron:

```text
origen -> objeto -> destino
```

El objeto puede moverse desde un personaje hacia la tienda o desde un personaje
hacia otro personaje. La operacion debe respetar huecos libres, peso maximo,
dinero si corresponde, y consistencia del estado.

## Decisiones acordadas

- No tocar el formato de ficheros en esta fase.
- Usar el campo `peso` que ya existe en los ficheros. Se descarta recalcular
  pesos con formulas, densidades o constantes porque el peso ya esta definido en
  la entrada.
- No usar colecciones (`ArrayList`, `HashMap`, etc.). La ampliacion debe seguir
  trabajando con arrays fijos y huecos `null`.
- No introducir una practica nueva ni sistemas externos.
- La dificultad conceptual debe venir de la consistencia de las operaciones:
  si una operacion falla, no debe perderse ni duplicarse ningun objeto, ni
  cambiarse dinero de forma incorrecta.

## Comportamientos esperados

- Si el inventario destino no tiene hueco, la transferencia no se realiza.
- Si el inventario destino supera el peso maximo, se lanza o gestiona
  `PesoMaximoSuperadoException` y la transferencia no se realiza.
- Si la posicion origen esta vacia, se usa la excepcion correspondiente
  (`PosicionVaciaException` o equivalente del proyecto).
- Si se vende a la tienda, el personaje recibe dinero solo si el objeto se ha
  transferido correctamente.
- Si se transfiere entre personajes, el objeto sale del origen solo si ha podido
  entrar correctamente en el destino.

## Decisiones abiertas

- Revisar si la entrega extraordinaria debe mantenerse por parejas, pasar a
  individual o quedar vinculada a la configuracion de Campus Virtual.
- Revisar si el nombre de ZIP `LX_XX_H4_EXTRA.zip` encaja con la operativa real
  de Campus Virtual.
- Redactar la rubrica definitiva despues de cerrar el alcance.

## Decisiones cerradas en el primer borrador

- Transferencia entre personajes: gratuita, sin intercambio de dinero.
- Peso maximo del inventario de personaje: 20 unidades.
- Falta de hueco en personaje o tienda: se reutiliza
  `InventarioLlenoException`.
- Precio de venta a tienda: el personaje recibe el precio del objeto vendido.

## Estado de la carpeta

La carpeta `recuperacion_H4` se creo copiando `sesion_H3` como base. El codigo
de referencia no se ha modificado. El archivo `memoria_H4.tex` ya contiene un
primer borrador del enunciado de recuperacion extraordinaria; todavia no debe
tratarse como version definitiva hasta revision de alcance, rubrica y entrega.
