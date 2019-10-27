

public class ParserFacade {

    public static String[][] parse(File file) throws  IOException {
        String extension = getExtension(file);
        String[] lines;
        String[][] content;
        switch (extension){
            case "pdf":
                lines= PdfReader.pdfReader(file.getPath());
                content= toBidemensionalArray(lines);
                updateTree(file,content);
                return content;
            case "txt":
                lines= TxtReader.txtReader(file.getPath());
                content= toBidemensionalArray(lines);
                updateTree(file,content);
                return content;
            case "docx":
                lines= DocxReader.docxReader(file.getPath());
                content= toBidemensionalArray(lines);
                updateTree(file,content);
                return content;
            default:
                return null;
        }

    }

    private static String[][] toBidemensionalArray(String[] lines){
        String[][] content = new String[lines.length][1];
        for(int i =0;i<lines.length;i++) {
            String phrase= lines[i];
            String[] split = phrase.split(" ");
            ArrayList<String> noSpacesSplit = new ArrayList<>();

            for (String word : split) {
                word=word.replaceAll("\n","");
                if (!word.equals("")) {
                    noSpacesSplit.add(word);
                }
            }

            split = noSpacesSplit.toArray(split);
            content[i]=split;
        }

        return content;
    }



}



