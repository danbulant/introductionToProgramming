package week14;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
This could have been implemented with a possibly more readable state machine instead of this messy distributed state..

Value => result of operations, or the first number pressed in before selecting an operation
Buffer => currently typed number, right side of operations when activated
Number pressed => buffer is !empty / was interacted with
Buferred operation => operation selected but not acted on yet
Prev buffer => previously used buffer, in case the user wants to repeat operations (by pressing = multiple times)
Prev operation => last used operation

Display => text output (reactive string property)
*/
public class Calculator extends Application {
  enum Operation {
    ADD,
    SUBSTRACT,
    MULTIPLY,
    DIVIDE;

    public String toString() {
      return switch(this) {
        case ADD -> "+";
        case SUBSTRACT -> "-";
        case MULTIPLY -> "*";
        case DIVIDE -> "/";
        default -> "";
      };
    }

    public static Operation fromString(String op) {
      return switch(op) {
        case "+" -> ADD;
        case "-" -> SUBSTRACT;
        case "*" -> MULTIPLY;
        case "/" -> DIVIDE;
        default -> null;
      };
    }

    public double apply(double left, double right) {
      return switch(this) {
        case ADD -> left + right;
        case SUBSTRACT -> left - right;
        case MULTIPLY -> left * right;
        case DIVIDE -> left / right;
      };
    }
  }

  double value = 0;
  double buffer = 0;
  boolean numberPressed = false;

  Operation bufferredOperation = null;

  double prevBuffer = 0;
  Operation prevOperation = null;

  StringProperty display = new SimpleStringProperty();

  /**
   * Event handler after a number was pressed
   */
  void processNumberInput(int num) {
    assert num >= 0 && num <= 9;
    resetNumberIfNeeded();
    numberPressed = true;
    buffer *= 10;
    buffer += num;
    
    updateDisplay();
  }

  /**
   * Event handler after backspace was pressed
   */
  void backspace() {
    resetNumberIfNeeded();
    var num = buffer % 10;
    buffer -= num;
    buffer /= 10;

    updateDisplay();
  }

  /**
   * Applies operations, used as event handler after = / enter
   */
  void processBuffered() {
    if(numberPressed && bufferredOperation != null) {
      value = bufferredOperation.apply(value, buffer);
      prevOperation = bufferredOperation;
      bufferredOperation = null;
      resetBuffer();
    } else if(prevOperation != null) {
      value = prevOperation.apply(value, prevBuffer);
    } else if(bufferredOperation != null) {
      prevBuffer = value;
      value = bufferredOperation.apply(value, value);
      prevOperation = bufferredOperation;
      bufferredOperation = null;
    }
    updateDisplay();
  }

  /**
   * Selects an operation, computes previous operation if still buffered
   */
  void processOperationInput(Operation op) {
    assert op != null;
    if(numberPressed && bufferredOperation != null) {
      value = bufferredOperation.apply(value, buffer);
      prevOperation = bufferredOperation;
      bufferredOperation = null;
    } else if(numberPressed) {
      value = buffer;
    }
    bufferredOperation = op;
    resetBuffer();
    updateDisplay();
  }

  /**
   * Resets all relevant state
   */
  void reset() {
    resetBuffer();
    value = 0;
    bufferredOperation = null;
    prevBuffer = 0;
    prevOperation = null;
    updateDisplay();
  }

  /**
   * Makes sure value is safe to interact with (enter numbers over)
   * resets to 0 if NaN or infinite
   */
  void resetNumberIfNeeded() {
    if(!Double.isFinite(value)) value = 0;
  }

  void updateDisplay() {
    if(numberPressed) display.set(Double.toString(buffer));
    else if(Double.isNaN(value)) display.set("ERROR");
    else display.set(Double.toString(value));

    // System.out.format("value %s buffer %s numberPressed %s buferredOperation %s prevBuffer %s prevOperation %s\n", value, buffer, numberPressed, buferredOperation, prevBuffer, prevOperation);
  }

  void resetBuffer() {
    prevBuffer = buffer;
    buffer = 0;
    numberPressed = false;
  }

  /**
   * Builds the ui
   * 
   * VALUE/BUFFER/ERROR
   * 
   * 7 8 9 + ⌫
   * 4 5 6 -
   * 1 2 3 *
   *   0   / =
   */
  public void start(Stage primaryStage) {
    updateDisplay();
    var output = new Label();
    output.setStyle("-fx-font-size: 30px; -fx-text-alignment: right;");
    output.textProperty().bind(display);

    var numberPanel = new GridPane();

    for(var i = 0; i < 9; i++) {
      var button = new Button(Integer.toString(i + 1));
      var index = i;
      button.setOnMouseClicked(e -> {
        processNumberInput(index + 1);
      });
      numberPanel.add(button, i % 3, 2-(i / 3));
    }
    var zero = new Button("0");
    zero.setOnMouseClicked(e -> {
      processNumberInput(0);
    });
    numberPanel.add(zero, 1, 3);

    var controlPanel = new GridPane();
    
    var values = Operation.values();
    for(var i = 0; i < values.length; i++) {
      var op = values[i];
      var button = new Button(op.toString());
      button.setOnMouseClicked(e -> {
        processOperationInput(op);
      });
      controlPanel.add(button, 0, i);
    }

    var backspace = new Button("⌫");
    backspace.setOnMouseClicked(e -> backspace());
    controlPanel.add(backspace, 1, 0);
    var equals = new Button("=");
    equals.setOnMouseClicked(e -> processBuffered());
    controlPanel.add(equals, 1, 3);

    var hbox = new HBox();
    hbox.setAlignment(Pos.CENTER);
    hbox.getChildren().add(numberPanel);
    hbox.getChildren().add(controlPanel);
    
    var vbox = new VBox();
    vbox.setAlignment(Pos.TOP_CENTER);
    vbox.getChildren().add(output);
    vbox.getChildren().add(hbox);
    VBox.setVgrow(hbox, Priority.SOMETIMES);

    Scene scene = new Scene(vbox, 300, 250);
    scene.setOnKeyPressed(ev -> {
      final var ch = ev.getText();
      try {
        var num = Integer.parseInt(ch);
        processNumberInput(num);
        return;
      } catch(NumberFormatException e) {}
      var op = Operation.fromString(ch);
      if(op != null) {
        processOperationInput(op);
        return;
      }
      if(ev.getCode() == KeyCode.BACK_SPACE) {
        backspace();
        return;
      }
      if(ev.getCode() == KeyCode.ESCAPE) {
        reset();
        return;
      }
      if(ev.getCode() == KeyCode.ENTER || ev.getCode() == KeyCode.EQUALS) {
        processBuffered();
        return;
      }
    });
    primaryStage.setTitle("Calculator");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
