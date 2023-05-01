<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.text.Font?>

<AnchorPane maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="750.0" prefWidth="1200.0" xmlns="http://javafx.com/javafx/8.0.171" xmlns:fx="http://javafx.com/fxml/1" fx:controller="application.SceneController">
   <children>
      <Button fx:id="back" layoutX="954.0" layoutY="658.0" mnemonicParsing="false" onAction="#switchToNewLORScene" prefHeight="39.0" prefWidth="79.0" text="Done">
         <font>
            <Font size="18.0" />
         </font>
      </Button>
      <Label layoutX="411.0" prefHeight="173.0" prefWidth="378.0" text="Compiled Letter of Recommendation" textAlignment="CENTER" wrapText="true">
         <font>
            <Font size="40.0" />
         </font>
      </Label>
      <TextField fx:id="lorText" layoutX="220.0" layoutY="173.0" prefHeight="453.0" prefWidth="761.0" />
   </children>
</AnchorPane>
