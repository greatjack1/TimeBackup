/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timebackup;

/**
 *
 * @author yaakov
 */
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class TimeBackUp extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("recompile");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Time Backup");

        Button button = new Button("Button");
        VBox vertLayout = new VBox(10);
        HBox horLayout1 = new HBox(20);
        MenuBar mb = new MenuBar();
        vertLayout.getChildren().add(mb);
        final Menu backupMenu = new Menu("Backups");
        final MenuItem backupNow = new MenuItem("Backup Now");
        final MenuItem backupSett = new MenuItem("Schedule Backups");
        backupMenu.getItems().add(backupNow);
        backupMenu.getItems().add(backupSett);
        backupNow.setOnAction(actionEvent -> {

        });
        mb.getMenus().add(backupMenu);
        mb.getMenus().add(backupMenu);
        horLayout1.setPadding(new Insets(25.0));
        ComboBox<String> DateCb = new ComboBox<>();
        DateCb.setMinWidth(100);
        DateCb.getItems().add("Date 1");
        vertLayout.getChildren().add(horLayout1);
        Label lbl = new Label("Select A Backup to View the Files:");
        lbl.setPadding(new Insets(5, 0, 0, 0));
        horLayout1.getChildren().add(lbl);
        horLayout1.getChildren().add(DateCb);
        TreeItem<String> root = new TreeItem<>("Sample Backup");
        TreeView<String> tree = new TreeView<>(root);
        
        for (int i = 0; i <1000;i++){
            TreeItemPath tip = new TreeItemPath(new PathNode(false,"file" + i,"num" +i ));
            root.getChildren().add(tip);
        }
        vertLayout.getChildren().add(tree);

        DateCb.setOnAction(actionEvent -> {
            System.out.println(DateCb.getValue());
        });

        Scene scene = new Scene(vertLayout, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
