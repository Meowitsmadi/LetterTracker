package application;

import org.controlsfx.control.CheckListView;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import sql.sqliteDemo;

public class ManageDataLORController{

// retrieves the data in the database and populates the combo boxes. 
    public static ComboBox<String> setAddData(ComboBox<String> combo, String name) {
    	combo.getItems().addAll(FXCollections.observableArrayList(sqliteDemo.getAllData(name)));
    	combo.getItems().addAll("");
        combo.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        if (item.isEmpty()) {
                            setText("Add item...");
                        } else {
                            setText(item);
                        }
                    }
                }
            };

            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
                if (cell.getItem().isEmpty() && ! cell.isEmpty()) {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setContentText("Enter item");
                    dialog.showAndWait().ifPresent(text -> {
                        int index = combo.getItems().size()-1;
                        combo.getItems().add(index, text);
                        combo.getSelectionModel().select(index);
                    });
                    evt.consume();
                }
            });

            return cell ;
        });
        
		return combo;
        
    }

	// retrieves the data in the database and populates the checklist view. 
	public static CheckListView<String> setAddData(CheckListView<String> combo, String name) {
		ObservableList<String> listOfItems = FXCollections.observableArrayList(sqliteDemo.getAllData(name));
		combo.getItems().addAll(listOfItems);
    	// Select the first checkListView element
        combo.getItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                c.next();
                if (c.wasAdded()) {
                    combo.getSelectionModel().select(c.getAddedSubList().get(0));
                }
            }
        });
		return combo;
	}

}
