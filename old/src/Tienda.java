public class Tienda {
    private String nombre;
    private Objeto[] catalogo;
    private int porcentajeReventa;

    public Tienda(String nombre, Objeto[] catalogo, int porcentajeReventa) {
        this.nombre = nombre;
        this.catalogo = catalogo;
        this.porcentajeReventa = porcentajeReventa;
    }

    public String getNombre() {
        return nombre;
    }

    public int size() {
        return catalogo.length;
    }

    public Objeto getItem(int indice) throws IndiceInvalidoException {
        if (indice < 0 || indice >= catalogo.length) {
            throw new IndiceInvalidoException("Indice invalido");
        }
        return catalogo[indice];
    }

    public void comprar(Personaje p, int indiceCatalogo)
            throws IndiceInvalidoException, DineroInsuficienteException, InventarioLlenoException {
        if (indiceCatalogo < 0 || indiceCatalogo >= catalogo.length) {
            throw new IndiceInvalidoException("Indice invalido");
        }

        Objeto obj = catalogo[indiceCatalogo];

        if (p.getDinero() < obj.getPrecio()) {
            throw new DineroInsuficienteException("Dinero insuficiente");
        }

        p.setDinero(p.getDinero() - obj.getPrecio());
        p.getInventario().add(obj.copia());
    }

    public void vender(Personaje p, int indiceInventario) throws IndiceInvalidoException {
        Objeto vendido = p.getInventario().remove(indiceInventario);
        int pago = vendido.getPrecio() * porcentajeReventa / 100;
        p.setDinero(p.getDinero() + pago);
    }

    @Override
    public String toString() {
        return "Tienda: " + nombre + " | reventa=" + porcentajeReventa + "% | items=" + catalogo.length;
    }
}
