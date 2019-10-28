import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Esta clase permite leer el contenido de archivos de formato .txt
 *
 */

public class TxtReader {

    /**
     * Retorna un String[] con el texto extraído del documento .txt
     * @param path
     * @return String[] texto
     */

    public static String[] txtReader(String path) {
        BufferedReader reader;
        ArrayList<String> lines= new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line.replaceAll("\n",""));
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.toArray(new String[lines.size()]);
    }


}