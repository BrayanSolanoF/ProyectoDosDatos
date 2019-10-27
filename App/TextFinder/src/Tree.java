
public class Tree {

    private Node root= null;

    private static Tree instance;

    public void clear(){
        this.root=null;
    }

    public void add(String word,File document, int lineNumber, int linePos){
        root=this.add(word, document,  lineNumber,  linePos, this.root);
    }

    private Node add(String word, File document, int lineNumber, int linePos, Node current){
        if(current==null){
            Node node= new Node(word);
            this.addOcurrence(node.getWordOcurrences(),document,lineNumber,linePos);
            return node;
        }else if(current.getWord().equals(word)){
            this.addOcurrence(current.getWordOcurrences(),document,lineNumber,linePos);
        }else if(current.getWord().compareTo(word)>0){
            current.setLeft(this.add(word, document,  lineNumber,  linePos, current.getLeft()));
        }else{
            current.setRigth(this.add(word, document,  lineNumber,  linePos, current.getRigth()));
        }
        return current;

    }

    private void addOcurrence(WordOcurrences wordOcurrences, File document, int lineNumber, int linePos){
        wordOcurrences.getDocuments().add(document);
        wordOcurrences.getLineNumber().add(lineNumber);
        wordOcurrences.getLinePos().add(linePos);
    }



}
