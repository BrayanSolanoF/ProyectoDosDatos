

public class WordOcurrences {

    private ArrayList<File> documents;

    private ArrayList<Integer> lineNumber;


    private ArrayList<Integer> linePos;

    public WordOcurrences(){
        this.documents = new ArrayList<>();
        this.lineNumber = new ArrayList<>();
        this.linePos = new ArrayList<>();
    }

    public ArrayList<File> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<File> documents) {
        this.documents = documents;
    }


}



