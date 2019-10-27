import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    Button indexBtn;


    @FXML
    Button refreshBtn;


    @FXML
    Button addBtn;

    @FXML
    Button deleteBtn;

    @FXML
    Button searchBtn;

    @FXML
    TextField inputField;

    @FXML
    ListView textPane;

    @FXML
    ListView namePane;

    @FXML
    ListView sizePane;

    @FXML
    ListView datePane;

    @FXML
    ListView libraryListView;

    @FXML
    TableView<Documents> resultsTable;

    //tabla
    @FXML
    TableColumn textColumn;
    @FXML
    TableColumn nameColumn;
    @FXML
    TableColumn sizeColumn;
    @FXML
    TableColumn dateColumn;

    //ordenamiento
    @FXML
    Button textUpBtn;
    @FXML
    Button textDownBtn;
    @FXML
    Button nameUpBtn;

    @FXML
    Button sizeUpBtn;
    @FXML
    Button sizeDownBtn;
    @FXML
    Button dateUpBtn;
    @FXML
    Button dateDownBtn;

    @FXML
    AnchorPane searchPane;

    @FXML
    VBox libraryPane;

    Searcher searcher;

    File[] documentsOnSearchPane;

    ArrayList<File> documents;

    ArrayList<String[][]> contents;

    Mi_Lista dl = new Mi_Lista();

    public Controller(){}

    /**
     * Este método se llama al inciar el programa, crea las acciones y eventos necesarios.
     */

    @FXML
    public void initialize(){
        documents = new ArrayList<>();
        addBtn.setOnMouseClicked(this::ButtonPlusAction);
        deleteBtn.setOnMouseClicked(this::ButtonMinus);
        indexBtn.setOnMouseClicked(this::ButtonIndex);
        searchBtn.setOnMouseClicked(this::ButtonSearch);
        namePane.setOnMouseClicked(this::ListViewClic);

        nameUpBtn.setOnMouseClicked(this::buttonNameUp);
        //nameDownBtn.setOnMouseClicked(this::buttonNameDown);

        dateUpBtn.setOnMouseClicked(this::buttonDateUp);
        dateDownBtn.setOnMouseClicked(this::buttonDateDown);

        searcher=new Searcher(this);
        inputField.setPromptText("Insert a word or phrase");
        //deleteBtn.setGraphic(new ImageView(new Image("imgs/icon1.png")));
        textColumn.setResizable(false);
        nameColumn.setResizable(false);
        sizeColumn.setResizable(false);
        dateColumn.setResizable(false);
    }

    /**
     * Actualiza la tabla de resultados con los archivos encontrados
     * @param documents
     * @param text
     * @param names
     * @param dates
     * @param sizes
     */

    public void updateSearchPane(ArrayList<File> documents,String[] text, String[] names, String[] dates, String [] sizes) {
        this.clearSearchPane();
        this.documentsOnSearchPane = documents.toArray(new File[documents.size()]);
        for (int i = 0; i < text.length; i++) {
            try {
                dl.addLast(new Documents(text[i], names[i], sizes[i], dates[i].substring(0, 10)));
            } catch (MalformedURLException e) {
                AlertBoxes.displayAlertBox("Exception", "An error occurred with the file");
            }
            this.updateResultTable();
        }
    }
    /**
     * Reinicia el panel
     */

    private void clearSearchPane(){
        textPane.getItems().clear();
        namePane.getItems().clear();
        datePane.getItems().clear();
        sizePane.getItems().clear();
    }

    /**
     *  Permite añadir documentos
     */

    public void ButtonPlusAction(MouseEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("pdf files", "*.pdf"),
                new FileChooser.ExtensionFilter("docx files", "*.docx"),
                new FileChooser.ExtensionFilter("txt files", "*.txt"));
        List<File> selectedFiles = fc.showOpenMultipleDialog(null);

        if (selectedFiles != null){
            for(int i = 0; i < selectedFiles.size(); i++){
                libraryListView.getItems().add(selectedFiles.get(i).getName());
                this.documents.addAll(selectedFiles);
            }
        } else {
            AlertBoxes.displayResultAlertBox("Exception", "Invalid file");
        }
    }

    /**
     * Permite eliminar documentos
     * @param event
     */

    private void ButtonMinus(MouseEvent event) {
        int index = libraryListView.getSelectionModel().getSelectedIndex();
        if (index >= 0){
            libraryListView.getItems().remove(index);
            this.documents.remove(index);
        }
    }

    /**
     * Obtiene la lista de documentos
     * @return
     */

    public List<File> getDocuments() {
        return documents;
    }

    /**
     * Retorna los contenidos de los textos en una lista
     * @return
     */

    public ArrayList<String[][]> getContents() {
        return contents;
    }

    /**
     * Indiza los documetos agregados
     * @param event
     */


    private void ButtonIndex(MouseEvent event){
        contents=new ArrayList<>();
        Tree tree= Tree.getInstance();
        tree.clear();
        for(File doc:this.documents){
            try {
                contents.add(ParserFacade.parse(doc));
            } catch ( IOException e) {
                AlertBoxes.displayAlertBox("Error", "An error has ocurred while reading "+doc.getName());
                String[][] result={{""}};
                contents.add(result);
            }
        }
    }

    /**
     * Inicia una búsqueda
     * @param e
     */

    private void ButtonSearch(MouseEvent e){
        searcher.search(inputField.getText());
    }

    /**
     * Abre el archivo seleccionado
     * @param e
     */

    private void ListViewClic(MouseEvent e) {
        int index = namePane.getSelectionModel().getSelectedIndex();
        try {
            Desktop.getDesktop().open(documentsOnSearchPane[index]);
            //RandomAccessFile raFile = new RandomAccessFile(documentsOnSearchPane[index], "r");
            //raFile.seek(500);
        } catch (IOException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
            AlertBoxes.displayAlertBox("Error", "File not found");
        }
    }

    /**
     * Retorna una lista observable de los documentos indizados
     * @param dl
     * @return
     */

    public ObservableList<Documents> getIndexedDocuments(Mi_Lista dl){
        ObservableList<Documents> files = FXCollections.observableArrayList();
        for(int i = 0; i < dl.getLength(); i++){
            files.add(dl.get(i));
        }
        System.out.println(files);
        return files;
    }

    /**
     * Actualiza la tabla de resultados con los archivos encontrados
     */

    private void updateResultTable(){
        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        textColumn.setSortable(false);
        nameColumn.setSortable(false);
        sizeColumn.setSortable(false);
        dateColumn.setSortable(false);

        resultsTable.setItems(getIndexedDocuments(dl));

    }

    /**
     * Ordena según los nombres de los archivos
     * @param e
     */

    private void buttonNameUp(MouseEvent e){
        resultsTable.getItems().clear();
        QuickSort.quickSort(dl, 0, dl.getLength()-1);
        this.updateResultTable();
    }

    /**
     * Ordena según los nombres de los archivos
     * @param e
     */

    private void buttonNameDown(MouseEvent e){
        resultsTable.getItems().clear();
        QuickSort.quickSort(dl, 0, dl.getLength()-1);
        dl.reverseList();
        this.updateResultTable();
    }

    /**
     * Ordena según la fecha de los archivos
     * @param e
     */

    private void buttonDateUp(MouseEvent e){
        resultsTable.getItems().clear();
        BubbleSort.bubbleSort(dl);
        this.updateResultTable();
    }

    /**
     * Ordena según la fecha de los archivos
     * @param e
     */

    private void buttonDateDown(MouseEvent e) {
        resultsTable.getItems().clear();
        BubbleSort.bubbleSort(dl);
        dl.reverseList();
        this.updateResultTable();
    }




}


