# Sesión 1 — Lectura de ficheros y arrays (1h20)

## Objetivo

Leer un fichero de texto, guardarlo en un array (sin ArrayList), partir cada línea por `;` y mostrarlo formateado por consola.

---

## Contenido (1h10)

| Tiempo | Actividad |
|--------|-----------|
| 15 min | Lectura básica con `BufferedReader + FileReader` |
| 20 min | Dos pasadas: contar líneas y cargar en `String[]` |
| 25 min | `split(";")` y salida formateada por tipo |
| 10 min | Debugger: breakpoints, ver variables, Step Over |
| 5 min  | Cierre |

---

## Fichero de datos

```
ARMA;EspadaCorta;50;12
ARMADURA;CascoCuero;35;CABEZA;4
MATERIAL;PiedraAfilado;15;1
```

---

## Salida esperada

```
[0] ARMA
    nombre  = EspadaCorta
    precio  = 50
    ataque  = 12

[1] ARMADURA
    nombre  = CascoCuero
    precio  = 35
    slot    = CABEZA
    defensa = 4

[2] MATERIAL
    nombre  = PiedraAfilado
    precio  = 15
    calidad = 1
```

---

## Entregable

1. `Main.java` que:
   - Lee `datos.txt`
   - Crea `String[] lineas` con dos pasadas (sin ArrayList)
   - Imprime listado formateado según tipo

2. `datos.txt` usado

## Criterios de corrección

- ✅ Usa `BufferedReader`
- ✅ Hace 2 pasadas para array exacto
- ✅ Usa `split(";")`
- ✅ Imprime formateado por consola
- ✅ `throws Exception` (sin try/catch)
