
public class Node {

    private Node left;

    private Node rigth;

    private WordOcurrences wordOcurrences;

    private String word;

    public Node(String word){
        this.word=word;
        wordOcurrences=new WordOcurrences();
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }


}

