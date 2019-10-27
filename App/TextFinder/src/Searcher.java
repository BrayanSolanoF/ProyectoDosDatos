

public class Searcher {

    Tree tree;

    Controller controller;

    public Searcher(Controller controller){
        this.tree= Tree.getInstance();
        this.controller=controller;
    }

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

    private String[] getNames(ArrayList<File> documents){
        String[] names = new String[documents.size()];
        for (int i = 0; i<documents.size();i++){
            names[i]=documents.get(i).getName();
        }
        return names;
    }

    private String[] getSizes(ArrayList<File> documents) throws IOException {
        String[] sizes = new String[documents.size()];
        for (int i = 0; i<documents.size();i++){
            sizes[i]= documents.get(i).length() +" bytes";
        }
        return sizes;
    }





}