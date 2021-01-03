package com.example.magspace.model;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.magspace.MainActivity;
import com.example.magspace.R;

public class BluechoiceActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private Button control_car;
    private Button program_maze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_choice);
        init();
    }
    private void init()
    {
        back = (Button) this.findViewById(R.id.back);
        back.setOnClickListener(this);
        control_car = (Button) this.findViewById(R.id.control_car);
        program_maze = (Button) this.findViewById(R.id.program_maze);
        control_car.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("blue-text", "用户选择了遥控小车");
                Intent intent = new Intent(BluechoiceActivity.this, BlueMainActivity.class);
                intent.putExtra("state","control_car");
                startActivity(intent);

            }
        });
        program_maze.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("blue-text", "用户选择了编程迷宫");
                Intent intent = new Intent(BluechoiceActivity.this, BlueMainActivity.class);
                intent.putExtra("state","program_maze");
                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View view) {
//        Intent intent = new Intent(BluechoiceActivity.this, MainActivity.class);
//        startActivity(intent);
            finish();

    }
}
