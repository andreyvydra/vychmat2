package ru.itmoment;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import org.apache.commons.lang3.tuple.Pair;
import ru.itmoment.math.*;
import ru.itmoment.math.system.CosXFunctionTwoVariable;
import ru.itmoment.math.system.CosYFunctionTwoVariable;
import ru.itmoment.math.system.LnFunctionTwoVariable;
import ru.itmoment.math.system.SystemOfTwoFunctions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

import static ru.itmoment.math.Constants.MATH_CONTEXT;

public class AppController {
    @FXML
    private ChoiceBox<FunctionOneVariable> choicesFunction;

    @FXML
    private ChoiceBox<String> choicesMethod;


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

    @FXML
    private ChoiceBox<SystemOfTwoFunctions> systemChoicesFunction;

    @FXML
    private LineChart<Number, Number> systemLineChart;

    @FXML
    private TextField systemAField;

    @FXML
    private TextField systemBField;

    @FXML
    private TextField systemEField;

    @FXML
    private Button systemSolveButton;

    @FXML
    private Label solutionLabel;

    @FXML
    private Label systemSolutionLabel;

    private HashMap<String, Method> systemMethods = new HashMap<>();
    private HashMap<String, Method> methods = new HashMap<>();

    public ArrayList<SystemOfTwoFunctions> systems = new ArrayList<>();
    public ArrayList<FunctionOneVariable> functions = new ArrayList<>();

    public void initialize() throws NoSuchMethodException {
        initEquations();
        initSystems();
    }


    public void initSystems() {
        systems.add(new SystemOfTwoFunctions(new CosXFunctionTwoVariable(), new CosYFunctionTwoVariable(),
                BigDecimal.valueOf(-5), BigDecimal.valueOf(5), BigDecimal.valueOf(-5), BigDecimal.valueOf(5)));
        systems.add(new SystemOfTwoFunctions(new LnFunctionTwoVariable(), new CosYFunctionTwoVariable(),
                BigDecimal.valueOf(0.5), BigDecimal.valueOf(5), BigDecimal.valueOf(-5), BigDecimal.valueOf(5)));
        systemChoicesFunction.setItems(FXCollections.observableArrayList(systems));

        systemChoicesFunction.setOnAction(this::changeSystemFunction);
        systemSolveButton.setOnAction(this::solveSystem);
    }

    public void solveSystem(ActionEvent event) {
        String as = systemAField.getText();
        String bs = systemBField.getText();
        String es = systemEField.getText();

        if (Objects.isNull(as) || Objects.isNull(bs) || Objects.isNull(es)) {
            systemSolutionLabel.setText("Ошибка: не все значения выставлены");
        }

        BigDecimal a = new BigDecimal(as.replace(",", "."));
        BigDecimal b = new BigDecimal(bs.replace(",", "."));
        BigDecimal e = new BigDecimal(es.replace(",", "."));

        try {
            Pair<Integer, Pair<BigDecimal, BigDecimal>> s = systemChoicesFunction.getValue().solveByNewton(a, b, e);
            systemSolutionLabel.setText("X = " + s.getRight().getLeft() + ", Y = " + s.getRight().getRight() + ", итерация " + s.getLeft());
        } catch (NumberFormatException ex) {
            systemSolutionLabel.setText("Ошибка: деление на ноль. Уберите особую точку из промежутка");
        } catch (Exception ex) {
            systemSolutionLabel.setText("Ошибка: " + ex.getMessage());
        }

    }

    public void changeSystemFunction(ActionEvent event) {
        SystemOfTwoFunctions system = systemChoicesFunction.getValue();
        BigDecimal a1 = system.getA1();
        BigDecimal a2 = system.getA2();
        BigDecimal b1 = system.getB1();
        BigDecimal b2 = system.getB2();
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Функция 1");
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Функция 2");
        systemLineChart.getData().clear();
        for (BigDecimal x = a1; x.compareTo(a2) <= 0; x = x.add(BigDecimal.valueOf(0.1))) {
            series1.getData().add(new XYChart.Data<>(x.doubleValue(), system.getFunction1().computeByX(x)));
        }

        for (BigDecimal y = b1; y.compareTo(b2) <= 0; y = y.add(BigDecimal.valueOf(0.1))) {
            series2.getData().add(new XYChart.Data<>(system.getFunction2().computeByY(y), y.doubleValue()));
        }

        systemLineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        systemLineChart.getData().add(series1);
        systemLineChart.getData().add(series2);
    }

    public void changeFunction(ActionEvent event) {
        FunctionOneVariable f = choicesFunction.getValue();
        BigDecimal a = f.getX1();
        BigDecimal b = f.getX2();
        System.out.println(f);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Функция");
        lineChart.getData().clear();
        while (b.compareTo(a) >= 0) {
            series.getData().add(new XYChart.Data<>(a.doubleValue(), f.compute(a).doubleValue()));
            a = a.add(f.getStep());
        }

        lineChart.getData().add(series);
    }

    public void initEquations() throws NoSuchMethodException {
        functions.add(new CubicFunction());
        functions.add(new FourFunction());
        functions.add(new SinCosFunction());
        functions.add(new LnFunction());

        methods.put("Метод простой итерации",
                Methods.class.getDeclaredMethod("methodSimpleIteration", FunctionOneVariable.class, BigDecimal.class, BigDecimal.class, BigDecimal.class));
        methods.put("Метод половинного деление",
                Methods.class.getDeclaredMethod("methodHalfDiv", FunctionOneVariable.class, BigDecimal.class, BigDecimal.class, BigDecimal.class));
        methods.put("Метод секущих",
                Methods.class.getDeclaredMethod("methodSimpleNewton", FunctionOneVariable.class, BigDecimal.class, BigDecimal.class, BigDecimal.class));

        choicesFunction.setItems(FXCollections.observableArrayList(functions));
        choicesMethod.setItems(FXCollections.observableArrayList(methods.keySet()));

        choicesFunction.setOnAction(this::changeFunction);

        solveButton.setOnAction(this::solve);
    }


    public void solve(ActionEvent event) {
        FunctionOneVariable f = choicesFunction.getValue();
        Method m = methods.get(choicesMethod.getValue());
        String as = aField.getText();
        String bs = bField.getText();
        String es = eField.getText();

        if (Objects.isNull(f) || Objects.isNull(m) || Objects.isNull(as) ||
                Objects.isNull(bs) || Objects.isNull(es)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Не все значения выставлены");
            alert.showAndWait();
        }

        BigDecimal a = new BigDecimal(as.replace(",", "."));
        BigDecimal b = new BigDecimal(bs.replace(",", "."));
        BigDecimal e = new BigDecimal(es.replace(",", "."));
        try {
            Pair<BigDecimal, Integer> solution = (Pair<BigDecimal, Integer>) m.invoke(new Methods(), f, a, b, e);
            BigDecimal x = solution.getLeft();
            Integer count = solution.getRight();

            solutionLabel.setText("X = " + x.round(MATH_CONTEXT) + ", f(X) = " + f.compute(x).round(MATH_CONTEXT) + ", число итераций - " + count);
        } catch (InvocationTargetException ex) {
            if (ex.getCause().getClass().equals(NumberFormatException.class)) {
                solutionLabel.setText("Ошибка: деление на ноль. Уберите особую точку из промежутка");
            } else {
                solutionLabel.setText("Ошибка: " + ex.getCause().getMessage());
            }
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

}
