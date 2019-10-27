

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


}



