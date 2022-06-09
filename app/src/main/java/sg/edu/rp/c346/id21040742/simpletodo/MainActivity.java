package sg.edu.rp.c346.id21040742.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    ListView lvTask;
    Button clear;
    Button delete;
    Button add;
    Spinner spnTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etTask = findViewById(R.id.etTask);
        add = findViewById(R.id.btnAdd);
        clear = findViewById(R.id.btnClear);
        delete = findViewById(R.id.btnDelete);
        lvTask = findViewById(R.id.listViewTask);
        spnTask = findViewById(R.id.spinner);

        ArrayList<String> alTasks;
        alTasks = new ArrayList<String>();
        ArrayAdapter aaTasks = new ArrayAdapter<> (this,android.R.layout.simple_list_item_1, alTasks);
        lvTask.setAdapter(aaTasks);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String colour = (etTask.getText().toString());
                alTasks.add(colour);
                aaTasks.notifyDataSetChanged();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvTask.setAdapter(null);
            }


        });

        spnTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etTask.setHint("Type in a new task here");
                        delete.setEnabled(false);
                        add.setEnabled(true);
                        etTask.setText(null);
                        break;
                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        add.setEnabled(false);
                        delete.setEnabled(true);
                        etTask.setText(null);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(etTask.getText().toString()) - 1;

                alTasks.remove(pos);
                aaTasks.notifyDataSetChanged();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alTasks.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
                else {
                    alTasks.clear();
                    aaTasks.notifyDataSetChanged();
                }
                if (alTasks.contains(etTask)) {
                    alTasks.clear();
                    aaTasks.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }
            }
        });











    }
}