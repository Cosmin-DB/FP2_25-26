# Compilacion del enunciado

Compilar desde esta carpeta:

```bash
latexmk -pdf -interaction=nonstopmode -halt-on-error -auxdir=build memoria_s4.tex
```

Resultado:

- El PDF final queda junto al `.tex` (`memoria_s4.pdf`).
- Los archivos auxiliares se generan en `build/`.
