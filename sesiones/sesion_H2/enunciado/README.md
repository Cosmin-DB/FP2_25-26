# Compilacion del enunciado

Compilar desde esta carpeta:

```bash
latexmk -pdf -interaction=nonstopmode -halt-on-error -auxdir=build memoria_H2.tex
```

Resultado:

- El PDF final queda junto al `.tex` (`memoria_H2.pdf`).
- Los archivos auxiliares se generan en `build/`.
