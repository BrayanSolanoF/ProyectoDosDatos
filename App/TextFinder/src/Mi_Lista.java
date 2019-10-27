import java.net.MalformedURLException;

/**
 * Esta clase corresponde a una lista doblemente enlazada que almacena los documentos agregados
 *
 */
public class Mi_Lista{

    protected Documents first;

    protected Documents last;

    private int length;

    public Mi_Lista() {
        this.first = null;
    }

    public void clearList(){
        this.first = null;
        this.last = null;
        this.length = 0;
    }

    public boolean isEmpty(){
        return this.first == null;
    }



}