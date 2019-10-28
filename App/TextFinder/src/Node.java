/**
 * Esta clase corresponde a los nodos del arbol binario de busqueda Tree
 *
 */
public class Node {

    /**
     * Hijo izquierdo
     */

    private Node left;

    /**
     * Hijo derecho
     */

    private Node rigth;

    /**
     * Instancias de la palabra
     */

    private WordOcurrences wordOcurrences;

    /**
     * La palabra
     */

    private String word;

    /**
     * Crea un nodo con la palabra
     * @param word
     */

    public Node(String word){
        this.word=word;
        wordOcurrences=new WordOcurrences();
    }

    /**
     * Retorna el hijo izquierdo del nodo
     * @return Node Izquierdo
     */

    public Node getLeft() {
        return left;
    }

    /**
     * Establece el hijo izquierdo del nodo
     * @param left
     */

    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Retorna el hijo derecho del nodo
     * @return Node Derecho
     */

    public Node getRigth() {
        return rigth;
    }

    /**
     * Establece el hijo derecho del nodo
     * @param rigth
     */

    public void setRigth(Node rigth) {
        this.rigth = rigth;
    }

    /**
     * Retorna las instancias de la palabra
     * @return WordOcurrences Instancias
     */

    public WordOcurrences getWordOcurrences() {
        return wordOcurrences;
    }

    /**
     * Retorna la palabra
     * @return String Palabra
     */


    public String getWord() {
        return word;
    }


}

