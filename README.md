# RPG de Consola (FP2) — Inventario, Equipo y Tienda con Arrays

## Objetivo del ejercicio

Que el alumno deje de *picar código* y empiece a **modelar**:

- Herencia + polimorfismo
- Interfaces para desacoplar decisiones
- Arrays fijos y huecos (`null`) como "gestión de memoria" básica
- Reglas de negocio entre clases (Personaje ↔ Inventario ↔ Tienda)
- Lectura de ficheros de texto y creación de objetos (parseo)

El proyecto está pensado para que el foco sea **POO y diseño**, no el menú ni la UX.

## Reglas de programación (restricciones)

- **No usar** `break` ni `continue` en bucles.
- Evitar **múltiples `return`** por método (idealmente uno).
- **No usar colecciones** dinámicas (`ArrayList`, `HashMap`, etc.). Solo **arrays**.
- Se permite `instanceof` únicamente para:
  - listar/imprimir tipos
  - validar si un `Objeto` es `IEquipable` al equipar
- La entrada del menú no es objetivo: se asume **input semi-confiable**.
  - Si el usuario mete basura, se informa y se vuelve al menú (sin reintentos infinitos).

---

## Mecánica general

El programa arranca, carga datos desde ficheros y permite navegar por un menú:

1. Se carga la **tienda** (`tienda.txt`) con objetos vendibles.
2. Se cargan **personajes** (`personajes.txt`) con inventario y equipo.
3. La tienda tiene **stock infinito**: comprar crea **copias** independientes.
4. El usuario navega: selecciona personaje, consulta, compra/vende, equipa/desequipa.

---

## Formato de ficheros (AUTOCONTENIDOS)

> **Diseño clave**: Los ficheros son autocontenidos. No hay IDs ni referencias cruzadas.  
> Cada línea tiene todos los datos necesarios para crear el objeto directamente.

### `tienda.txt`

Cada línea define un objeto vendible completo:

```
ARMA;Nombre;Precio;Ataque
ARMADURA;Nombre;Precio;TipoArmadura;Defensa
MATERIAL;Nombre;Precio;Calidad
```

Ejemplo:

```
ARMA;EspadaCorta;50;12
ARMADURA;CascoCuero;35;CABEZA;4
MATERIAL;PiedraAfilado;15;1
```

---

### `personajes.txt`

Bloques por personaje, terminados con `FIN` obligatorio:

```
PERSONAJE;Nombre;Dinero;CapacidadInventario
EQUIPO;VACIO;SlotEquipo
EQUIPO;ARMA;Nombre;Precio;Ataque
EQUIPO;ARMADURA;Nombre;Precio;TipoArmadura;Defensa
INV;ARMA;Nombre;Precio;Ataque
INV;ARMADURA;Nombre;Precio;TipoArmadura;Defensa
INV;MATERIAL;Nombre;Precio;Calidad
FIN
```

Ejemplo:

```
PERSONAJE;Alba;120;8
EQUIPO;ARMA;EspadaCorta;50;12
EQUIPO;ARMADURA;CascoCuero;35;CABEZA;4
EQUIPO;VACIO;CUERPO
EQUIPO;VACIO;PIES
INV;MATERIAL;PiedraAfilado;15;1
FIN
```

Reglas:

- `FIN` es obligatorio. Si falta → `FormatoFicheroException`.
- Los objetos del personaje pueden NO existir en tienda (son independientes).
- `EQUIPO;VACIO;SLOT` indica slot vacío.

---

## Modelo de clases

### Jerarquía de objetos (sin IDs)

- `Objeto` (abstracta): `nombre`, `precio`, `copia()`
- `Arma`, `Armadura`, `Material` heredan de `Objeto`

### Interfaz de equipamiento

- `IEquipable`: `getBonoAtaque()`, `getBonoDefensa()`, `getSlot()`
- `Arma` y `Armadura` implementan `IEquipable`. `Material` no.

---

## Asociaciones

- `Personaje` posee un `Inventario` (composición, array fijo con huecos `null`)
- `Personaje` tiene `IEquipable[4]` para equipo (ARMA, CABEZA, CUERPO, PIES)
- `Tienda` mantiene un array de objetos vendibles. **Stock infinito**: comprar clona.

---

## Reglas de negocio

### Equipar desde inventario

1. Seleccionar índice del inventario.
2. Si no es `IEquipable` → `NoEquipableException`.
3. Si hay algo equipado en ese slot → mover al inventario.
4. Si inventario lleno → `InventarioLlenoException`.
5. Recalcular stats.

### Comprar / Vender

- **Comprar**: valida índice, valida dinero, añade `copia()` al inventario.
- **Vender**: quita objeto del inventario, suma dinero × `% reventa`.

---

## Excepciones

| Excepción | Cuándo se lanza |
|-----------|-----------------|
| `IndiceInvalidoException` | Índice fuera de rango o hueco vacío |
| `InventarioLlenoException` | Al añadir sin huecos disponibles |
| `DineroInsuficienteException` | Al comprar sin dinero suficiente |
| `NoEquipableException` | Al intentar equipar un `Material` |
| `FormatoFicheroException` | Línea mal formada o falta `FIN` |

---

## Configuración

La clase `Config` centraliza las constantes:

- `CAPACIDAD_INVENTARIO`
- `PORCENTAJE_REVENTA`
- `RUTA_TIENDA`
- `RUTA_PERSONAJES`

---

## Conceptos POO y dónde aparecen

| Concepto | Dónde se aplica |
|----------|-----------------|
| **Clase abstracta** | `Objeto` - no se instancia, define estructura común |
| **Método abstracto** | `Objeto.copia()`, `Objeto.toString()` |
| **Herencia** | `Arma`, `Armadura`, `Material` extienden `Objeto` |
| **Interface** | `IEquipable` - define contrato sin implementación |
| **Polimorfismo** | `Tienda.comprar()` llama `obj.copia()` sin saber el tipo concreto |
| **instanceof** | `Personaje.equiparDesdeInventario()` verifica si es `IEquipable` |
| **Composición** | `Personaje` crea y posee `Inventario` (ciclo de vida ligado) |
| **Agregación** | `Tienda` recibe array de `Objeto[]` externo (no lo crea) |
| **Constantes (final)** | `Config` - clase final con constructor privado |
| **Enum** | `SlotEquipo`, `TipoArmadura` |
| **Arrays con huecos** | `Inventario.items[]`, `Personaje.equipo[]` - gestión de `null` |
| **Excepciones propias** | 5 excepciones de dominio (`InventarioLlenoException`, etc.) |
| **Encapsulación** | Todos los campos privados, acceso por getters/setters |
| **Override toString()** | `Arma`, `Armadura`, `Material`, `Personaje`, `Inventario`, `Tienda` |

---

## Ejecución

1. Asegura que existen `tienda.txt` y `personajes.txt` en el directorio de ejecución.
2. Ejecuta `Main`.
3. Navega por el menú.

---

