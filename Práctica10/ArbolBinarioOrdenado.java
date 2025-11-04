/**
 * Clase que implementa un Árbol Binario de Búsqueda (Ordenado).
 * Los elementos se insertan manteniendo la propiedad:
 *  - Todo elemento en el subárbol izquierdo es menor o igual.
 *  - Todo elemento en el subárbol derecho es mayor.
 * @param <T> Tipo de los elementos, debe ser comparable.
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>> extends ArbolBinario<T> {

    /**
     * Constructor por defecto. Crea un árbol vacío.
     */
    public ArbolBinarioOrdenado() {
        super();
    }

    /**
     * Inserta un elemento en el árbol según las reglas del Árbol Binario de Búsqueda.
     * @param elemento Elemento a insertar.
     * @throws IllegalArgumentException si el elemento es null.
     */
    @Override
    public void insertar(T elemento) throws IllegalArgumentException {
        if (elemento == null)
            throw new IllegalArgumentException("Error: elemento nulo.");

        Vertice v = new Vertice(elemento);
        if (raiz == null) {
            raiz = v;
            tamanio = 1;
            return;
        }
        insertar(raiz, elemento);
    }

    /**
     * Método recursivo auxiliar para insertar un elemento en el lugar correcto.
     * @param v Nodo actual durante la búsqueda.
     * @param elemento Elemento a insertar.
     */
    private void insertar(Vertice v, T elemento) {
        if (elemento.compareTo(v.elemento) <= 0) {
            if (v.izquierdo == null) {
                Vertice aux = new Vertice(elemento);
                v.izquierdo = aux;
                aux.padre = v;
                tamanio++;
            } else {
                insertar(v.izquierdo, elemento);
            }
        } else {
            if (v.derecho == null) {
                Vertice aux = new Vertice(elemento);
                v.derecho = aux;
                aux.padre = v;
                tamanio++;
            } else {
                insertar(v.derecho, elemento);
            }
        }
    }

    /**
     * Elimina un elemento del árbol si está contenido.
     * @param elemento Elemento a eliminar.
     * @throws IllegalArgumentException si el elemento es null.
     */
    @Override
    public void eliminar(T elemento) {
        if (elemento == null)
            throw new IllegalArgumentException("Error: elemento nulo.");
        raiz = eliminar(raiz, elemento);
    }

    /**
     * Método recursivo auxiliar para eliminar un nodo.
     * Casos:
     * - Nodo hoja.
     * - Nodo con un hijo.
     * - Nodo con dos hijos → se sustituye por su sucesor in-order.
     * @param v Nodo actual en el recorrido.
     * @param elemento Elemento a eliminar.
     * @return Nodo raíz del subárbol después de eliminar.
     */
    private Vertice eliminar(Vertice v, T elemento) {
        if (v == null)
            return null;

        if (elemento.compareTo(v.elemento) < 0)
            v.izquierdo = eliminar(v.izquierdo, elemento);
        else if (elemento.compareTo(v.elemento) > 0)
            v.derecho = eliminar(v.derecho, elemento);
        else {
            tamanio--;
            if (v.izquierdo == null && v.derecho == null)
                return null;
            if (v.izquierdo == null) {
                v.derecho.padre = v.padre;
                return v.derecho;
            }
            if (v.derecho == null) {
                v.izquierdo.padre = v.padre;
                return v.izquierdo;
            }
            Vertice sucesor = devolverMinimo(v.derecho);
            v.elemento = sucesor.elemento;
            v.derecho = eliminar(v.derecho, sucesor.elemento);
        }
        return v;
    }

    /**
     * Devuelve el nodo con el valor mínimo en el subárbol.
     * @param v Nodo desde el que se busca el mínimo.
     * @return Nodo con el elemento mínimo.
     */
    private Vertice devolverMinimo(Vertice v) {
        while (v.izquierdo != null)
            v = v.izquierdo;
        return v;
    }

    /**
     * Busca un elemento en el árbol.
     * @param elemento Elemento a buscar.
     * @return true si el elemento está en el árbol, false en otro caso.
     */
    @Override
    public boolean buscar(T elemento) {
        return buscar(elemento, raiz);
    }

    /**
     * Método recursivo auxiliar para buscar un elemento.
     * @param elemento Elemento a buscar.
     * @param v Nodo actual.
     * @return true si se encontró el elemento, false en otro caso.
     */
    private boolean buscar(T elemento, Vertice v) {
        if (v == null)
            return false;
        if (v.elemento.equals(elemento))
            return true;
        if (elemento.compareTo(v.elemento) <= 0)
            return buscar(elemento, v.izquierdo);
        else
            return buscar(elemento, v.derecho);
    }

    /**
     * Devuelve una lista con los elementos del árbol
     * en recorrido in-order (ordenados de menor a mayor).
     * @return ListaDoblementeLigada con los elementos en orden.
     */
    public ListaDoblementeLigada<T> devolverRecorrido() {
        ListaDoblementeLigada<T> recorrido = new ListaDoblementeLigada<>();
        if (raiz == null) return recorrido;

        Pila<Vertice> pila = new Pila<>();
        Vertice actual = raiz;

        while (actual != null || !pila.estaVacia()) {
            while (actual != null) {
                pila.meter(actual);
                actual = actual.izquierdo;
            }
            actual = pila.sacar();
            recorrido.insertarFinal(actual.elemento);
            actual = actual.derecho;
        }
        return recorrido;
    }
}
