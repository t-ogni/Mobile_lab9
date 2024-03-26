package yakovskij.lab9;
import Plotter.arr;
import Plotter.interp;
import Solver.ExpressionParser;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.io.Console;
import java.util.Map;

import Solver.Expr.Expression;
import Solver.ExpressionParser;

public class MySurface extends SurfaceView {
    boolean active = true;
    protected float[] x, y;
    String formula = "1";
    int dots = 100;
    float cellWidth = 1;
    float start_x=-10, end_x=10;
    float start_y=-20, end_y=20;
    protected Paint p;
    protected ExpressionParser parser = new ExpressionParser();
    protected Expression expression = parser.parse(formula);


    public void calcValuesMap(){
        expression = parser.parse(formula);

        x = new float[dots];
        y = new float[dots];

        float step_x = (end_x - start_x) / dots;

        for(int i = 0; i < dots; i++) {
            x[i] = start_x + step_x * i;
            //y[i] = (float) Math.sin(x[i]);
            y[i] = (float) expression.execute(Map.of("x", (double) x[i]));

        }
    }

    public MySurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        p = new Paint();
        p.setColor(Color.RED);
        calcValuesMap();
        p.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        int width = getWidth();
        int height = getHeight();

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);

        // Отрисовка вертикальной шкалы
        float x_location = (width / (end_x - start_x)) * -start_x;
        if(start_x < 0 && end_x > 0) {
            canvas.drawLine(x_location, 0, x_location, height, paint);
        }

        // Отрисовка горизонтальной шкалы
        float y_location = height - ((height / (end_y - start_y)) * -start_y);
        if(start_y < 0 && end_y > 0) {
            canvas.drawLine(0, y_location, width, y_location, paint);
        }

        // Отрисовка клеток

        int numCellsX = Math.round((end_x - start_x) / cellWidth);
        int numCellsY = Math.round((end_y - start_y) / cellWidth);
        int cellLeft = Math.round(start_x / cellWidth);
        int cellUp = Math.round(start_y / cellWidth);

        paint.setStrokeWidth(1);
        paint.setColor(Color.GRAY);

        for (int i = cellLeft; i < numCellsX-cellLeft; i++) {
            // Горизонтальные линии клеток
            float x = (i * cellWidth - start_x) * (width / (end_x - start_x));
            canvas.drawLine(x, 0, x, height, paint);
        }

        for (int i = cellUp; i < numCellsY-cellUp; i++) {
            // Вертикальные линии клеток
            float y = (i * cellWidth - start_y) * (height / (end_y - start_y));
            canvas.drawLine(0, y, width, y, paint);
        }


        for (int i = 0; i < dots - 1; i++) {
            float x_coord1 = (x[i] - start_x) * (width / (end_x - start_x));
            float y_coord1 = height - (y[i] - start_y) * (height / (end_y - start_y));
            float x_coord2 = (x[i+1] - start_x) * (width / (end_x - start_x));
            float y_coord2 = height - (y[i+1] - start_y) * (height / (end_y - start_y));

            canvas.drawLine(x_coord1,y_coord1,x_coord2,y_coord2, p);
        }

//        float x0 = 0.0f, y0 = 0.0f;
//        float w = width / dots; // pixel per step
//        float h = height / dots;
//        float sw = (end_x - start_x) / dots; // interval per step
//        float sh = (end_y - start_y) / dots;
//
//        for (int i = 0; i < dots; i++)
//        {
////            float x1 = interp.map(x[i], start_x, end_x, 0, width - 1);
////            float y1 = interp.map(y[i], start_y, end_y, height - 1, 0);
//            float x1 = x[i]-start_x * w;
//            float y1 = y[i]-start_y * h;
//
//            canvas.drawPoint(x1, y1, p);
//            x0 = x1;
//            y0 = y1;
//        }
//        super.draw(canvas);
    }
}
