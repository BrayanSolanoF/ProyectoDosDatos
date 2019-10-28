import java.io.File;
import java.util.ArrayList;

/**
 * Esta clase almacena los documentos, la linea y la posición en la linea de cada palabra en el
 * arbol de palabras.
 *
 */

public class WordOcurrences {

    /**
     * Lista de documentos
     */

    private ArrayList<File> documents;

    /**
     * Lista de numeros de linea
     */

    private ArrayList<Integer> lineNumber;

    /**
     * Lista de posicion en la linea
     */


    private ArrayList<Integer> linePos;

    /**
     * Crea una instancia con listas vacías
     */

    public WordOcurrences(){
        this.documents = new ArrayList<>();
        this.lineNumber = new ArrayList<>();
        this.linePos = new ArrayList<>();
    }

    /**
     * Retorna la lista de documentos de la palabra
     * @return ArrayList
     */

    public ArrayList<File> getDocuments() {
        return documents;
    }

    /**
     * Establece la lista de documentos de la palabra
     * @param documents
     */

    public void setDocuments(ArrayList<File> documents) {
        this.documents = documents;
    }

    /**
     * Retorna la lista de numeros de linea de la palabra
     * @return ArrayList
     */

    public ArrayList<Integer> getLineNumber() {
        return lineNumber;
    }

    /**
     * Establece la lista de numeros de linea de la palabra
     * @param lineNumber
     */

    public void setLineNumber(ArrayList<Integer> lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Retorna la lista de posicion en linea de la palabra
     * @return
     */

    public ArrayList<Integer> getLinePos() {
        return linePos;
    }

    /**
     * Establece la lista de posicion en linea de la palabra
     */

    public void setLinePos(ArrayList<Integer> linePos) {
        this.linePos = linePos;
    }



}



