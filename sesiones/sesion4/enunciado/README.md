# Compilacion del enunciado

Compilar desde esta carpeta (`sesiones/sesion4/enunciado`):

```bash
latexmk -pdf -interaction=nonstopmode -halt-on-error -auxdir=build memoria_s4.tex
```

Resultado:

- El PDF final queda junto al `.tex` (`memoria_s4.pdf`).
- Los archivos auxiliares se generan en `build/`.

