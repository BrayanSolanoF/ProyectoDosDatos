
public class Tree {

    private Node root= null;

    private static Tree instance;

    public void clear(){
        this.root=null;
    }

    public void add(String word,File document, int lineNumber, int linePos){
        root=this.add(word, document,  lineNumber,  linePos, this.root);
    }


}
