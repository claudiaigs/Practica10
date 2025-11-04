public class MainOrdenado {
    public static void main(String[] args) {
        // Crear un árbol binario ordenado de enteros
        ArbolBinarioOrdenado<Integer> arbol = new ArbolBinarioOrdenado<>();

        // Insertar elementos del 1 al 10
        System.out.println("Insertando elementos del 1 al 10");
        for (int i = 1; i <= 10; i++) {
            arbol.insertar(i);
        }

        // Mostrar recorrido in-order con devolverRecorrido()
        System.out.println("\nRecorrido");
        System.out.println(arbol.devolverRecorrido());

        // Probar buscar
        System.out.println("\n Buscando elementos");
        System.out.println("¿Está el 5?: " + arbol.buscar(5));
        System.out.println("¿Está el 20?: " + arbol.buscar(20));

        // Eliminar un elemento intermedio
        System.out.println("\nEliminando el elemento 5");
        arbol.eliminar(5);
        System.out.println("Recorrido después de eliminar 5: " + arbol.devolverRecorrido());

        // Eliminar raíz original (1)
        System.out.println("\nEliminando el elemento 1 (raíz original)");
        arbol.eliminar(1);
        System.out.println("Recorrido después de eliminar 1: " + arbol.devolverRecorrido());

        // Eliminar un nodo con dos hijos (por ejemplo 7)
        System.out.println("\nEliminando el elemento 7");
        arbol.eliminar(7);
        System.out.println("Recorrido después de eliminar 7: " + arbol.devolverRecorrido());

        // Tamaño final del árbol
        System.out.println("\nTamaño del árbol final");
        System.out.println("Tamaño: " + arbol.devolverTamanio());
    }
}
