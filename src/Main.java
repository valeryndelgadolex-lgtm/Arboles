import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Ejemplo: arbol del enunciado
        //            A
        //          /   \
        //         B     C
        //        / \   / \
        //       D   E F   G
        //      / \
        //     H   I
        TreeNode a = new TreeNode("A");
        BinaryTree tree = new BinaryTree(a);

        a.left = new TreeNode("B");
        a.right = new TreeNode("C");
        a.left.left = new TreeNode("D");
        a.left.right = new TreeNode("E");
        a.right.left = new TreeNode("F");
        a.right.right = new TreeNode("G");
        a.left.left.left = new TreeNode("H");
        a.left.left.right = new TreeNode("I");

        /*
        Implemente aqui el menu (cada opcion llama al metodo de BinaryTree
        indicado entre parentesis):
        ========== MENU ==========
        1. Construir arbol                  (buildTree(new Scanner(System.in)))
        2. Mostrar arbol                    (printTree())
        3. Recorrido Preorden               (preorder())
        4. Recorrido Inorden                (inorder())
        5. Recorrido Posorden               (postorder())
        6. Mostrar factores de equilibrio   (printBalanceFactors())
        7. Balancear arbol (AVL)            (balance())
        8. Salir
        ==========================
        Seleccione una opcion:
        */

        Scanner scanner = new Scanner(System.in);
        int opcion;

        // usamos un do-while para que el menu se repita hasta que elijamos salir (opcion 8)
        do {
            System.out.println("========== MENU ==========");
            System.out.println("1. Construir arbol");
            System.out.println("2. Mostrar arbol");
            System.out.println("3. Recorrido Preorden");
            System.out.println("4. Recorrido Inorden");
            System.out.println("5. Recorrido Posorden");
            System.out.println("6. Mostrar factores de equilibrio");
            System.out.println("7. Balancear arbol (AVL)");
            System.out.println("8. Salir");
            System.out.println("==========================");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiamos el buffer para que no brinque lineas despuess, el buffer significa menu

            // el switch para elegir cual y cual colocar :333
            switch (opcion) {
                case 1:
                    tree.buildTree(new Scanner(System.in)); // (buildTree(new Scanner(System.in))), para leer
                    break;
                case 2:
                    tree.printTree(); // (printTree())
                    break;
                case 3:
                    System.out.println("Resultado Preorden: " + tree.preorder()); // (preorder())
                    break;
                case 4:
                    System.out.println("Resultado Inorden: " + tree.inorder()); // (inorder())
                    break;
                case 5:
                    System.out.println("Resultado Posorden: " + tree.postorder()); // (postorder())
                    break;
                case 6:
                    tree.printBalanceFactors(); // (printBalanceFactors())
                    break;
                case 7:
                    tree.balance(); // (balance())
                    break;
                case 8:
                    System.out.println("Saliendo del programa, adios!");
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo.");
            }
            System.out.println(); // eso namas para qs e vea mas ordenado

        } while (opcion != 8); // se sigue repitiendo mientras la opcion sea diferente de 8

        scanner.close(); // cerrando el scannercito
    }
}





