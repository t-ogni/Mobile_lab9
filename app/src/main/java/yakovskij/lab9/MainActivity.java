package yakovskij.lab9;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import Plotter.interp;
import Plotter.arr;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MySurface s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = findViewById(R.id.mySurface);


    }
    public void plot(View v){
        showFloatingDialog();
    }

    public void zoomin(View v){
        s.start_y = s.start_y / 2;
        s.end_y = s.end_y / 2;
        s.start_x = s.start_x / 2;
        s.end_x = s.end_x / 2;
        s.calcValuesMap();
        s.invalidate();
    }
    public void zoomout(View v){
        s.start_y = s.start_y * 2;
        s.end_y = s.end_y * 2;
        s.start_x = s.start_x * 2;
        s.end_x = s.end_x * 2;
        s.calcValuesMap();
        s.invalidate();
    }

    private void showFloatingDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_dialog);

        ListView listView = dialog.findViewById(R.id.listView);
        EditText editText = dialog.findViewById(R.id.editText);
        Button sendButton = dialog.findViewById(R.id.sendButton);

        List<String> items = Arrays.asList("sin(x)", "cos(x)", "x*x+(2*x)-3");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selected = items.get(position);
            s.formula = selected;
            s.calcValuesMap();
            dialog.dismiss();
            s.invalidate();
        });

        sendButton.setOnClickListener(v -> {
            String input = editText.getText().toString();
            s.formula = input;
            s.calcValuesMap();
            dialog.dismiss();
            s.invalidate();
        });

        // Set dialog properties for a floating window
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.show();
    }

    public void setup(View view) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.setup);

        SeekBar dot = dialog.findViewById(R.id.dotsseek);
        EditText xmin = dialog.findViewById(R.id.Xmin);
        EditText xmax = dialog.findViewById(R.id.Xmax);
        EditText ymin = dialog.findViewById(R.id.Ymin);
        EditText ymax = dialog.findViewById(R.id.Ymax);
        Button sendButton = dialog.findViewById(R.id.button);

        xmin.setText(String.valueOf(s.start_x));
        ymin.setText(String.valueOf(s.start_y));
        xmax.setText(String.valueOf(s.end_x));
        ymax.setText(String.valueOf(s.end_y));



        dot.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                s.dots = dot.getProgress();
                s.calcValuesMap();
                s.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                s.dots = dot.getProgress();
                s.calcValuesMap();
                s.invalidate();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                s.dots = dot.getProgress();
                s.calcValuesMap();
                s.invalidate();
            }
        });

        sendButton.setOnClickListener(v -> {
            s.start_x=Float.parseFloat(xmin.getText().toString());
            s.start_y=Float.parseFloat(ymin.getText().toString());
            s.end_x=Float.parseFloat(xmax.getText().toString());
            s.end_y=Float.parseFloat(ymax.getText().toString());
            s.calcValuesMap();
            dialog.dismiss();
            s.invalidate();
        });

        // Set dialog properties for a floating window
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.show();
    }
}