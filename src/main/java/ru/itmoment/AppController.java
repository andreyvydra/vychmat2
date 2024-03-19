package ru.itmoment;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.tuple.Pair;
import ru.itmoment.math.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class AppController {
    @FXML
    private ChoiceBox<FunctionOneVariable> choicesFunction;

    @FXML
    private ChoiceBox<Method> choicesMethod;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private TextField aField;

    @FXML
    private TextField bField;

    @FXML
    private TextField eField;

    @FXML
    private Button solveButton;

    public ArrayList<FunctionOneVariable> functions = new ArrayList<>();

    public void initialize() throws NoSuchMethodException {
        CubicFunction cb = new CubicFunction();
        functions.add(cb);

        choicesFunction.setItems(FXCollections.observableArrayList(functions));
        choicesMethod.setItems(FXCollections.observableArrayList(
                Methods.class.getDeclaredMethod("methodSimpleIteration", FunctionOneVariable.class, BigDecimal.class, BigDecimal.class, BigDecimal.class),
                Methods.class.getDeclaredMethod("methodHalfDiv", FunctionOneVariable.class, BigDecimal.class, BigDecimal.class, BigDecimal.class)
        ));
        choicesFunction.setOnAction(this::changeFunction);

        solveButton.setOnAction(this::solve);
    }

    public void changeFunction(ActionEvent event) {
        FunctionOneVariable f = choicesFunction.getValue();
        BigDecimal a = f.getX1();
        BigDecimal b = f.getX2();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Функция");

        while (b.compareTo(a) >= 0) {
            series.getData().add(new XYChart.Data<>(a.doubleValue(), f.compute(a).doubleValue()));
            a = a.add(f.getStep());
        }

        lineChart.getData().add(series);
    }

    public void solve(ActionEvent event) {
        FunctionOneVariable f = choicesFunction.getValue();
        BigDecimal a = new BigDecimal(aField.getText());
        BigDecimal b = new BigDecimal(bField.getText());
        BigDecimal e = new BigDecimal(eField.getText());

        try {
            Pair<BigDecimal, Integer> solution = (Pair<BigDecimal, Integer>) choicesMethod.getValue().invoke(new Methods(), f, a, b, e);
            BigDecimal x = solution.getLeft();
            Integer count = solution.getRight();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Найдено решение");
            alert.setContentText("X = " + x + ", f(X) = " + f.compute(x) + ", число итераций - " + count);
            alert.showAndWait();
        } catch (InvocationTargetException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText(ex.getCause().getMessage());
            alert.showAndWait();
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

}
