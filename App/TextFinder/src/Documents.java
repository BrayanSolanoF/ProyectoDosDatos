
/**
 * Esta clase corresponde a los nodos de la clase Mi_Lista, aqu� se almacenan 
 * los datos extra�dos de los documentos.
 *
 */
public class Documents {

    private String text;
    private String name;
    private String size;
    private String date;
    private int realSize;

    protected Documents next = null;
    protected Documents prev = null;

    /**
     * Crea un nodo Documents con el texto, nombre, tamaño y fecha ingresados
     * @param text
     * @param name
     * @param size
     * @param date
     */

    public Documents(String text, String name, String size, String date) {
        this.text = text;
        this.name = name;
        this.size = size;
        this.date = date;
        this.realSize = toInt(this.size);
    }

    /**
     * Con este método se calcula el peso del documento a partir del String ingresado
     * @param size
     * @return int
     */

    public static int toInt(String size){
        String subSize[] = size.split(" ");
        int result = 0;
        int exp = 0;
        for(int i = subSize.length-1; i == 0; i--, exp++){
            switch (subSize[i]){
                case "1": {result += Math.pow(1, exp);}
                case "2": {result += Math.pow(2, exp);}
                case "3": {result += Math.pow(3, exp);}
                case "4": {result += Math.pow(4, exp);}
                case "5": {result += Math.pow(5, exp);}
                case "6": {result += Math.pow(6, exp);}
                case "7": {result += Math.pow(7, exp);}
                case "8": {result += Math.pow(8, exp);}
                case "9": {result += Math.pow(9, exp);}
            }
        }
        return result;
    }

    /**
     * Retorna el texto del documento
     * @return text
     */


    public String getText() { return text; }

    /**
     * Establece el texto del documento
     * @param text
     */

    public void setText(String text) { this.text = text; }

    /**
     * Retorna el nombre del documento
     * @return Nombre
     */

    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del documento
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna el tamaño del documento
     * @return Tamaño
     */

    public String getSize() {
        return size;
    }

    /**
     * Establece el tamaño del documento
     * @param size
     */

    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Retorna la fecha del documento
     * @return Fecha
     */

    public String getDate() {
        return date;
    }

    /**
     * Establece la fecha del documento
     * @param date
     */

    public void setDate(String date) {
        this.date = date;
    }
    /**
     * Retorna el tamaño del documento como un número entero
     * @return int Tamaño
     */

    public int getRealSize() { return realSize; }

    /**
     * Establece el tamaño del documento como un número entero
     * @param realSize
     */

    public void setRealSize(int realSize) { this.realSize = realSize; }

}
