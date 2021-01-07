package com.example.magspace.model;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    private void init() {
        back = (Button) this.findViewById(R.id.back);
        control_car = (Button) this.findViewById(R.id.control_car);
        program_maze = (Button) this.findViewById(R.id.program_maze);

        control_car.setOnClickListener(view -> {
            // 手机硬件是否支持蓝牙
            if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                Toast.makeText(this, "不支持BLE", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.d("blue-text", "用户选择了遥控小车");

            Intent intent = new Intent(BluechoiceActivity.this, BlueMainActivity.class);
            intent.putExtra("state", "control_car");
            startActivity(intent);
        });

        program_maze.setOnClickListener(view -> {
            // 手机硬件是否支持蓝牙
            if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                Toast.makeText(this, "不支持BLE", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.d("blue-text", "用户选择了编程迷宫");
            Intent intent = new Intent(BluechoiceActivity.this, BlueMainActivity.class);
            intent.putExtra("state", "program_maze");
            startActivity(intent);
        });

        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
