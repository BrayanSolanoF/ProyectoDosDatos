import java.net.MalformedURLException;

/**
 * Esta clase corresponde a una lista doblemente enlazada que almacena los documentos agregados
 *
 */
public class Mi_Lista{

    /**
     * El primer elemento de la lista
     */

    protected Documents first;

    /**
     * El ultimo elemento de la lista
     */

    protected Documents last;

    /**
     * El tamaño de la lista
     */

    private int length;

    /**
     * Crea una lista vacia
     */

    public Mi_Lista() {
        this.first = null;
    }

    /**
     * Elimina los contenidos de la lista
     */

    public void clearList(){
        this.first = null;
        this.last = null;
        this.length = 0;
    }

    /**
     * Determina si la lista está vacía
     * @return boolean Vacia
     */

    public boolean isEmpty(){
        return this.first == null;
    }

    /**
     * Retorna el documento en el indice determinado
     * @param index
     * @return Documents Documento
     */

    public Documents get(int index){
        if (index > this.length-1 || this.first == null || index < 0){
            System.out.println("Index out of range");
            return null;
        }
        else{
            int counter = 0;
            Documents current = this.first;
            while(counter < index){
                current = current.next;
                counter ++;
            }
            return current;
        }
    }

    /**
     * Elimina el documento en el indice determinado
     * @param index
     */


    public void deleteAt(int index){
        if (index > this.length-1 || this.first == null || index < 0){
            System.out.println("Index out of range");
        }
        else{
            int counter = 0;
            Documents current = this.first;
            while(counter < index){
                current = current.next;
                counter ++;
            }
            if (current == this.first){
                Documents after = current.next;
                after.prev = null;
                this.first = after;
            }
            else if (current == this.last){
                Documents before = current.prev;
                before.next = null;
                this.last = before;
            } else {
                Documents before = current.prev;
                Documents after = current.next;
                before.next = after;
                after.prev = before;

            }
        }
    }



    public void printList(){
        if (this.first == null){
            System.out.println("[]");
        }
        else {
            Documents current = this.first;
            System.out.print("[");
            while (current.next != null) {
                System.out.print(current.getName());
                System.out.print(", ");
                current = current.next;
            }
            System.out.print(current.getName());
            System.out.println("]");
        }
    }

    /**
     * Agrega un elemento al final de la lista
     * @param addedDoc
     * @throws MalformedURLException
     */

    public void addLast(Documents addedDoc) throws MalformedURLException {
        this.length ++;
        if (this.first == null){
            this.first = addedDoc;
            this.last = this.first;
        }
        else {
            Documents newDoc = addedDoc;
            this.last.next = newDoc;
            newDoc.prev = this.last;
            this.last = newDoc;
        }
    }

    /**
     * Invierte la lista
     */

    public void reverseList(){
        Documents previous = null;
        //change reference head becomes tail in reversal
        this.last = this.first;
        Documents current = this.first;
        while(current != null){
            // swap prev and next for the current node
            previous = current.prev;
            current.prev = current.next;
            current.next = previous;
            // to move to next node current.prev has to be called
            // as that has reference to next node now
            current = current.prev;
        }
        if(previous != null){
            this.first = previous.prev;
        }
    }
    
    /**
     * Retorna el tamaño de la lista
     * @return int Tamaño
     */

    public int getLength() {
        return length;
    }



}