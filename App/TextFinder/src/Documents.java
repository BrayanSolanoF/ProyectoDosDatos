
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

    public Documents(String text, String name, String size, String date) {
        this.text = text;
        this.name = name;
        this.size = size;
        this.date = date;
        this.realSize = toInt(this.size);
    }

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


}
