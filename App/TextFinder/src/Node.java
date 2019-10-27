
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

    public Node getRigth() {
        return rigth;
    }

    public void setRigth(Node rigth) {
        this.rigth = rigth;
    }

    public WordOcurrences getWordOcurrences() {
        return wordOcurrences;
    }


    public String getWord() {
        return word;
    }


}

