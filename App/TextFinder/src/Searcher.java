
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;


public class Searcher {

    /**
     * Arbol de palabras
     */

    Tree tree;

    /**
     * Controlador de la interfaz
     */

    Controller controller;

    /**
     * Crea una instancia del buscador de palabras
     * @param controller
     */

    public Searcher(Controller controller){
        this.tree= Tree.getInstance();
        this.controller=controller;
    }

    /**
     * Busca la frase ingresada por el usuario y actualiza la interfaz con los archivos
     * que contienen dicha frase
     * @param phrase
     */

    public void search(String phrase){
        String[] splitPhrase = this.decomposePhrase(phrase);
        if(splitPhrase.length>=1){

            WordOcurrences wordOcurrences= this.searchWord(splitPhrase[0]);
            if( wordOcurrences!= null) {
                wordOcurrences = this.getRealOcurrences(wordOcurrences, splitPhrase);

                String[] context = this.getContext(wordOcurrences);
                String[] names = this.getNames(wordOcurrences.getDocuments());

                String[] dates;
                try {
                    dates = this.getDates(wordOcurrences.getDocuments());
                } catch (IOException e) {
                    dates = null;
                }

                String[] sizes;
                try {
                    sizes = this.getSizes(wordOcurrences.getDocuments());
                } catch (IOException e) {
                    sizes = null;
                }
                controller.updateSearchPane(wordOcurrences.getDocuments(), context, names, dates, sizes);
            }else{
                AlertBoxes.displayAlertBox("Error", "No encontrado");
            }
        }else{
            AlertBoxes.displayAlertBox("Error", "No se ingreso palabras");
        }
    }

    /**
     * Retorna un arreglo de Strings con los nombres de los documentos encontrados
     * @param documents
     * @return String[] Documentos
     */

    private String[] getNames(ArrayList<File> documents){
        String[] names = new String[documents.size()];
        for (int i = 0; i<documents.size();i++){
            names[i]=documents.get(i).getName();
        }
        return names;
    }

    /**
     * Retorna un arreglo de Strings con los tamaños de los documentos encontrados
     * @param documents
     * @return
     * @throws IOException
     */

    private String[] getSizes(ArrayList<File> documents) throws IOException {
        String[] sizes = new String[documents.size()];
        for (int i = 0; i<documents.size();i++){
            sizes[i]= documents.get(i).length() +" bytes";
        }
        return sizes;
    }

    /**
     * Retorna un arreglo de Strings con las fechas de lso documentos encontrados
     * @param documents
     * @return
     * @throws IOException
     */

    private String[] getDates(ArrayList<File> documents) throws IOException {
        String[] dates = new String[documents.size()];
        for (int i = 0; i<documents.size();i++){
            dates[i]= Files.readAttributes(documents.get(i).toPath(), BasicFileAttributes.class).creationTime().toString();
        }
        return dates;
    }

    /**
     * Retorna las instancias de una palabra del arbol
     * @param word
     * @return WordOcurrences instancias
     */

    private WordOcurrences searchWord(String word){
        WordOcurrences ocurrenceList= this.tree.getOcurrences(word);
        return ocurrenceList;
    }

    /**
     * Retorna las instacias con los documentos en donde se encontro la frase ingresada
     * @param wordOcurrences
     * @param phrase
     * @return
     */

    public WordOcurrences getRealOcurrences(WordOcurrences wordOcurrences, String[] phrase){
        int size = wordOcurrences.getDocuments().size();
        File document;
        int lineNumber;
        int linePos;
        boolean error=false;

        ArrayList<File> finalDocs = new ArrayList<>();
        ArrayList<Integer> finalLineNumber = new ArrayList<>();
        ArrayList<Integer>  finalLinePos = new ArrayList<>();

        for(int i = 0; i < size; i++){
            document = wordOcurrences.getDocuments().get(i);
            String[][] content =this.getContent(document);
            lineNumber=wordOcurrences.getLineNumber().get(i);
            linePos=wordOcurrences.getLinePos().get(i);

            int index1= lineNumber;
            int index2= linePos;
            for(int j=0;j<phrase.length;j++){
                try {
                    error= !content[index1][index2].equals(phrase[j]);
                    if (error){
                        break;
                    }
                    index2++;
                }catch (IndexOutOfBoundsException e){
                    index1++;
                    if(index1<content.length) {

                        index2 = 0;
                        j--;
                    }else{
                        error=true;
                        break;
                    }
                }
            }

            if(!error){
                finalDocs.add(document);
                finalLineNumber.add(lineNumber);
                finalLinePos.add(linePos);
            }
        }

        WordOcurrences result=new WordOcurrences();
        result.setDocuments(finalDocs);
        result.setLineNumber(finalLineNumber);
        result.setLinePos(finalLinePos);
        return result;
    }

    /**
     * Retorna el contexto en el documento de la frase que se buscó
     * @param wordOcurrences
     * @return String[] Contexto
     */

    protected String[] getContext(WordOcurrences wordOcurrences){
        int size = wordOcurrences.getDocuments().size();
        String[] contexts= new String[size];
        for(int i=0;i<size;i++){
            contexts[i]=this.getContext(wordOcurrences.getDocuments().get(i),wordOcurrences.getLineNumber().get(i),wordOcurrences.getLinePos().get(i));
        }
        return contexts;
    }

    /**
     * Retorna el texto contenido en un documento
     * @param document
     * @return String[] Texto
     */

    private String[][] getContent(File document){
        int index= controller.getDocuments().indexOf(document);
        String[][] content = controller.getContents().get(index);
        return content;
    }

    /**
     * Retorna el contexto en el documento de la frase que se buscó
     * @param document
     * @param lineNumber
     * @param linePos
     * @return String Contexto
     */


    private String getContext(File document, int lineNumber, int linePos){
        String[][] content =this.getContent(document);
        String context="...";

        for(int j=linePos-5;j<linePos+5;j++){
            try {


                if (content[lineNumber][j] != null) {
                    context += content[lineNumber][j]+" ";
                }
            }catch (IndexOutOfBoundsException e){

            }
        }

        return context+"...";
    }

    /**
     * Descompone la frase ingresada por el usuario para facilitar la busca
     * @param phrase
     * @return String[] Frase
     */

    private String[] decomposePhrase(String phrase){
        String[] split=phrase.split(" ");
        ArrayList<String> noSpacesSplit=new ArrayList<>();
        for(String word: split){
            if(!word.equals("")){
                noSpacesSplit.add(word);
            }
        }
        split= noSpacesSplit.toArray(split);
        return split;
    }





}