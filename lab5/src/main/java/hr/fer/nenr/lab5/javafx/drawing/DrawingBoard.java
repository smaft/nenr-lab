package hr.fer.nenr.lab5.javafx.drawing;

import hr.fer.nenr.lab5.drawing.GestureModel;
import hr.fer.nenr.lab5.drawing.GestureModelListener;
import hr.fer.nenr.lab5.math.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.function.Consumer;

public class DrawingBoard extends Pane implements GestureModelListener {

    private GestureModel model = new GestureModel();
    private Canvas canvas = new Canvas();

    public DrawingBoard(Consumer<GestureModel> modelConsumer) {
        model.addListener(this);
        initCanvas(modelConsumer);
    }

    @Override
    public void onChange() {
        requestLayout();
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < model.size() - 1; i++) {
            Point start = model.get(i);
            Point end = model.get(i + 1);
            gc.strokeLine(start.x, start.y, end.x, end.y);
        }
    }

    private void initCanvas(Consumer<GestureModel> modelConsumer) {
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        StackPane canvasHolder = new StackPane(canvas);
        canvasHolder.setStyle("-fx-background-color: white");
        getChildren().add(canvasHolder);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            model.clear();
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            model.add(new Point(e.getX(), e.getY()));
        });
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (model.size() < 2) return;
            modelConsumer.accept(model);
        });
    }
}
