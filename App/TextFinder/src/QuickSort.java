public class QuickSort {

    public static void quickSort(Mi_Lista dl, int beginning, int end){
        if (dl.isEmpty() || dl.getLength() == 0)
            AlertBoxes.displayAlertBox("Exception", "Empty library");
        if (beginning >= end)
            return;

        Documents pivot = dl.get(beginning + (end - beginning) / 2);

        int i = beginning, j = end;
        while (i <= j) {
            while (dl.get(i).getName().compareTo(pivot.getName()) < 0) {
                i++;
            }

            while (dl.get(j).getName().compareTo(pivot.getName()) > 0) {
                j--;
            }

            if (i <= j) {
                swap(dl.get(i), dl.get(j), dl);
                i++;
                j--;
            }
        }


        if (beginning < j)
            quickSort(dl, beginning, j);

        if (end > i)
            quickSort(dl, i, end);
    }

    public static void swap(Documents a, Documents b, Mi_Lista dl){
        if (dl.isEmpty() || dl.first == dl.last){
            return;
        }
        else{
            String aText = a.getText();
            String aName = a.getName();
            String aSize = a.getSize();
            String aDate = a.getDate();

            a.setText(b.getText());
            a.setName(b.getName());
            a.setSize(b.getSize());
            a.setDate(b.getDate());

            b.setText(aText);
            b.setName(aName);
            b.setSize(aSize);
            b.setDate(aDate);
        }
    }


}

