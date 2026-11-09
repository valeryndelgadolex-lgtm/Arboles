import java.util.Scanner;
import java.util.*;
class BinaryTree {

    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }


    //public void insertRoot(Object data)
    //        Si el arbol esta vacio, crea la raiz con 'data'.


    public void insertRoot(Object data) {
        if (this.root == null) {
            this.root = new TreeNode(data);
        }

    }

//public TreeNode search(Object data)
//        Devuelve el nodo cuyo dato es igual (equals) a 'data', o null si no
//        existe.


    //creamos una busqueda ayudante que nos ayude a recorrer el arbolito en el search(object data)
    //va a recibir el node que le damos a la hora de iniciar el agoritmo que es el this.root
    // y ademas el dato que estamos bucando
    private TreeNode busquedaAyudante(TreeNode node, Object data) {
        if (node == null) {
            return null; // por si no lo encontramos
        }
        if (node.data.equals(data)) {
            return node; //
        }

        //busca en el de la derecha
        TreeNode found = busquedaAyudante(node.left, data);
        if (found != null) {
            return found;
        }
        //busca ala izquierda
        return busquedaAyudante(node.right, data);

    }


    public TreeNode search(Object data) {
        // Llama al ayudante privado q hicimos
        return busquedaAyudante(this.root, data);
    }

    public BinaryTree(TreeNode root) {
        this.root = root;
    }


    //public void buildTree(Scanner scanner)
    //        Construye el arbol leyendo los datos con 'scanner' (en el main se le
    //        pasa new Scanner(System.in)). Primero se lee la raiz y luego, por
    //        niveles, el hijo izquierdo y el hijo derecho de cada nodo
    //        ("Hijo izquierdo de A: ", "Hijo derecho de A: ", ...). Una linea
    //        vacia o "-" significa que ese hijo no existe.
    //    */

    Scanner Nodo = new Scanner(System.in);

    public void buildTree(Scanner scanner) {
        System.out.print("Ingrese el dato ");
        String datoRaiz = scanner.nextLine();
        if (datoRaiz.isEmpty() || datoRaiz.equals("-")) {
            this.root = null;
            return;
        }
        this.root = new TreeNode(datoRaiz);

        //se tiene que crear unqueue para mantener un orden, quese crea asi;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this.root);

        //se necesita un ciclo pq el algoirmto continua siempre y cuando queue no este vacio, es decir.
        // mientras hayan datos que colocar en el arbol y ordenar, continuara
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll(); // .poll pq vamos a agararr el primero que esta en la cola

            // primero se mira en la parte izquierda
            System.out.print("Hijo izquierdo de " + current.data + " (o '-' si no tiene): ");
            if (scanner.hasNextLine()) {
                String leftVal = scanner.nextLine().trim();
                if (!leftVal.isEmpty() && !leftVal.equals("-")) {
                    current.left = new TreeNode(leftVal); // se crea el nodo para la parte izquierda
                    queue.add(current.left); // lo metemos a la cola para luego buscarle sus hijos
                }
            }

            // despues se mira la parte derecha
            System.out.print("Hijo derecho de " + current.data + " (o '-' si no tiene): ");
            if (scanner.hasNextLine()) {
                String rightVal = scanner.nextLine().trim();
                if (!rightVal.isEmpty() && !rightVal.equals("-")) {
                    current.right = new TreeNode(rightVal); // creamos el nodo pal derecho
                    queue.add(current.right); // lo metemos a la cola tambien
                }
            }
        }

        System.out.println("el arbol ya esta construido");




    }

    // public boolean insertLeft(Object parent, Object data)
    // Agrega 'data' como hijo izquierdo del nodo cuyo dato es 'parent'.
    // Devuelve false (y no modifica nada) si 'parent' no existe o si ya
    // tiene hijo izquierdo; true en otro caso.
    public boolean insertLeft(Object parent, Object data) {
        // buscamos al papa primero con el metodo search q ya hicimos
        TreeNode parentNode = search(parent);

        // si el papa no existe, o si ya tiene hijo izquierdo ocupado, devolvemos false
        if (parentNode == null || parentNode.left != null) {
            return false;
        }

        // sitodo bien, creamos el hijo a la izquierda y devolvemos true
        parentNode.left = new TreeNode(data);
        return true;
    }

    // public boolean insertRight(Object parent, Object data)
    // Igual que insertLeft, pero con el hijo derecho.

    public boolean insertRight(Object parent, Object data) {
        // buscamos al papa primero
        TreeNode parentNode = search(parent);

        // si no existe o ya tiene hijo derecho, false
        if (parentNode == null || parentNode.right != null) {
            return false;
        }

        // pq sisi se puede, lo metemos a la derecha y true
        parentNode.right = new TreeNode(data);
        return true;
    }

    // public void printTree()
    // Muestra el arbol en consola de forma grafica, indicando claramente
    // la relacion entre padres e hijos.
    public void printTree() {
        if (this.root == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        printTreeRecursive(this.root, "", true);
    }

    // ayudante privado para dibujar el arbol con ramitas
    private void printTreeRecursive(TreeNode nodo, String sangria, boolean esElUltimo) {
        if (nodo != null) {
            // Imprimimos el nodo con ramitas dependiendo si es el ultimo o no
            System.out.println(sangria + (esElUltimo ? "└── " : "├── ") + nodo.data);

            // Guardamos los hijos en una lista normalitapp
            List<TreeNode> hijos = new ArrayList<>();
            if (nodo.left != null || nodo.right != null) {
                hijos.add(nodo.left);
                hijos.add(nodo.right);
            }

            // Recorremos los hijos para qsesiga dibujando hacia abajo
            for (int i = 0; i < hijos.size(); i++) {
                TreeNode hijoActual = hijos.get(i);

                if (hijoActual != null || (i == 0 && nodo.right != null)) {
                    // Si es el ultimo hijo, le sumamos mas espacios vacios a la sangria
                    String nuevaSangria = sangria + (esElUltimo ? "    " : "│   ");

                    // Llamamos de nuevo al metodo pasando las variables simplificadas
                    printTreeRecursive(hijoActual, nuevaSangria, i == hijos.size() - 1);
                }
            }
        }
    }

    // public String preorder()
    // Recorrido Preorden: Raiz - Izquierda - Derecha
    public String preorder() {
        StringBuilder sb = new StringBuilder(); // para acumular el texto hacemos el stringbuilder
        preorderRecursive(this.root, sb);
        return sb.toString().trim(); // Convertimos a String y con trim limpiamos los espacios eso
    }

    private void preorderRecursive(TreeNode nodo, StringBuilder sb) {
        if (nodo == null) return;
        sb.append(nodo.data).append(" "); // para chechaer la raiz
        preorderRecursive(nodo.left, sb);   // para cehcar la izquierda
        preorderRecursive(nodo.right, sb);  // pa la derecha
    }

    public String inorder() {
        StringBuilder sb = new StringBuilder();
        inorderRecursive(this.root, sb);
        return sb.toString().trim();
    }


    private void inorderRecursive(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        inorderRecursive(node.left, sb);   // primero izquierda aca ya q es inorder
        sb.append(node.data).append(" "); // dps raiz
        inorderRecursive(node.right, sb);  // dsps se checa derecha
    }

    // public String postorder()
    // Recorrido Posorden: Izquierda - Derecha - Raiz
    public String postorder() {
        StringBuilder sb = new StringBuilder();
        postorderRecursive(this.root, sb);
        return sb.toString().trim();
    }

    private void postorderRecursive(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        postorderRecursive(node.left, sb);  // izquierda
        postorderRecursive(node.right, sb); // derecha
        sb.append(node.data).append(" "); // raiz
    }

    // public int height(TreeNode node)
    // Altura del subarbol que empieza en 'node' (null tiene altura 0 y una hoja altura 1).
    public int height(TreeNode node) {
        if (node == null) return 0;
        // sumamos 1 y asi miramos si se va a izquierda o a derecha cual es el mazimo
        return 1 + Math.max(height(node.left), height(node.right)); //math.max es una herramienta q nos sirve para buscar el amximo
    }

    // public int balanceFactor(TreeNode node)
    // Factor de equilibrio de 'node': height(node.right) - height(node.left).
    public int balanceFactor(TreeNode node) {
        if (node == null) return 0;
        // la resta q se tiene q hacer
        return height(node.right) - height(node.left);
    }

    // public boolean isBalanced()
    // true si TODOS los nodos tienen factor de equilibrio -1, 0 o 1.
    public boolean isBalanced() {
        return isBalancedRecursive(this.root);
    }

    private boolean isBalancedRecursive(TreeNode node) {
        if (node == null){
            return true;
        }
        int bf = balanceFactor(node);  //usando la funcion k se creo
        if (bf < -1 || bf > 1) {
            return false; // pq si se pasan no estan balanceados
        }
        return isBalancedRecursive(node.left) && isBalancedRecursive(node.right);
    }

    // public List<Object> unbalancedNodes()
    // Lista con los datos de los nodos cuyo factor de equilibrio es menor que -1 o mayor que 1
    public List<Object> unbalancedNodes() {
        List<Object> lista = new ArrayList<>();
        unbalancedNodesRecursive(this.root, lista);
        return lista;
    }

    private void unbalancedNodesRecursive(TreeNode node, List<Object> lista) {
        if (node == null) return;
        int bf = balanceFactor(node);
        if (bf < -1 || bf > 1) {
            lista.add(node.data); // se guarda quien desbalancea apra ya aplicar cambiso
        }
        unbalancedNodesRecursive(node.left, lista);
        unbalancedNodesRecursive(node.right, lista);
    }

    // public void printBalanceFactors()
    // Muestra el arbol en consola indicando en cada nodo su factor de equilibrio.
    public void printBalanceFactors() {
        if (this.root == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        printBalanceFactorsRecursive(this.root);
    }

    private void printBalanceFactorsRecursive(TreeNode node) {
        if (node == null) return;
        System.out.println("Nodo: " + node.data + " | Factor de equilibrio: " + balanceFactor(node));
        printBalanceFactorsRecursive(node.left);
        printBalanceFactorsRecursive(node.right);
    }

    // public TreeNode rotateRight(TreeNode node)
    // rotacion simple a la derecha
    public TreeNode rotateRight(TreeNode node) {
        TreeNode y = node.left;
        TreeNode T3 = y.right;

        // sehace el cambio
        y.right = node;
        node.left = T3;

        return y; // devolvemos la nueva raiz de este subarbol
    }

    // public TreeNode rotateLeft(TreeNode node)
    // rotacion simple a la izquierda
    public TreeNode rotateLeft(TreeNode node) {
        TreeNode y = node.right;
        TreeNode T2 = y.left;

        // se hace ekl cambio
        y.left = node;
        node.right = T2;

        return y;
    }

    // public TreeNode rotateLeftRight(TreeNode node)
    // rotacion doble izquierda-derecha
    public TreeNode rotateLeftRight(TreeNode node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    // public TreeNode rotateRightLeft(TreeNode node)
    // rotacion doble derecha-izquierda
    public TreeNode rotateRightLeft(TreeNode node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    // public void balance()
    // Identifica los nodos desbalanceados y aplica las rotaciones necesarias
    public void balance() {
       System.out.println("\n-arbol cuando no se ha balanceado");
        printTree();
        System.out.println("\n-los facores de equilibrio");
        printBalanceFactors();

        this.root = balanceRecursive(this.root);

        System.out.println("\n-arbol balanceado");
        printTree();
        System.out.println("\n-factores de equilibrio");
        printBalanceFactors();
    }

    private TreeNode balanceRecursive(TreeNode node) {
        if (node == null) return null;

        // balanceamos los hijos primero
        node.left = balanceRecursive(node.left);
        node.right = balanceRecursive(node.right);

        int bf = balanceFactor(node);

        // Rotacion simple a la derecha
        if (bf < -1 && balanceFactor(node.left) <= 0) {
            return rotateRight(node);
        }

        // Rotacion doble izq-der
        if (bf < -1 && balanceFactor(node.left) > 0) {
            return rotateLeftRight(node);
        }

        // Rotacion simple a la izquierda
        if (bf > 1 && balanceFactor(node.right) >= 0) {
            return rotateLeft(node);
        }

        // Rotacion doble der-izq
        if (bf > 1 && balanceFactor(node.right) < 0) {
            return rotateRightLeft(node);
        }

        return node; // xsi ya ta balanceado
    }
}

    /*
    IMPORTANTE: respete EXACTAMENTE los nombres y firmas de los metodos que se
    piden abajo; la revision automatica los busca por nombre. Puede agregar
    todos los metodos auxiliares (privados) que necesite.
    */

    /*
    1. Construccion del arbol: implemente aqui




    public boolean insertLeft(Object parent, Object data)
        Agrega 'data' como hijo izquierdo del nodo cuyo dato es 'parent'.
        Devuelve false (y no modifica nada) si 'parent' no existe o si ya
        tiene hijo izquierdo; true en otro caso.

    public boolean insertRight(Object parent, Object data)
        Igual que insertLeft, pero con el hijo derecho.



    /*
    2. Visualizacion: implemente aqui

    public void printTree()
        Muestra el arbol en consola de forma grafica, indicando claramente
        la relacion entre padres e hijos.
    */

    /*
    3. Recorridos: implemente aqui. Cada recorrido debe ser una funcion
    RECURSIVA independiente (por ejemplo, un metodo privado que reciba un
    TreeNode). Los metodos publicos devuelven la secuencia separada por
    espacios, p.ej. "A B D H I E C F G".

    public String preorder()
    public String inorder()
    public String postorder()
    */

    /*
    4. Factores de equilibrio: implemente aqui

    public int height(TreeNode node)
        Altura del subarbol que empieza en 'node' (null tiene altura 0 y una
        hoja altura 1).

    public int balanceFactor(TreeNode node)
        Factor de equilibrio de 'node': height(node.right) - height(node.left).

    public boolean isBalanced()
        true si TODOS los nodos tienen factor de equilibrio -1, 0 o 1.

    public List<Object> unbalancedNodes()
        Lista con los datos de los nodos cuyo factor de equilibrio es menor
        que -1 o mayor que 1 (los que requieren balanceo).

    public void printBalanceFactors()
        Muestra el arbol en consola indicando en cada nodo su factor de
        equilibrio.
    */

    /*
    5. Balanceo AVL: implemente aqui. Cada rotacion recibe la raiz del
    subarbol desbalanceado y devuelve la NUEVA raiz de ese subarbol.

    public TreeNode rotateRight(TreeNode node)       rotacion simple a la derecha
    public TreeNode rotateLeft(TreeNode node)        rotacion simple a la izquierda
    public TreeNode rotateLeftRight(TreeNode node)   rotacion doble izquierda-derecha
    public TreeNode rotateRightLeft(TreeNode node)   rotacion doble derecha-izquierda

    public void balance()
        Identifica los nodos desbalanceados y aplica las rotaciones necesarias
        hasta que el arbol sea AVL (actualiza 'root' si cambia). Debe mostrar
        el arbol con sus factores de equilibrio antes y despues de balancear.
    */

