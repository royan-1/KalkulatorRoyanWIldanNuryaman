package com.informatika.kalkulatorroyan;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etNumber1,etNumber2;
    Spinner spPilihan;
    TextView tvHasil;
    Button btnHitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.etAngka1);
        etNumber2 = findViewById(R.id.etAngka2);
        tvHasil = findViewById(R.id.tvHasil);
        btnHitung = findViewById(R.id.btnHitung);
        spPilihan = findViewById(R.id.spPilihan);

        // ✅ tambahan: isi spinner agar tidak null
        String[] operatorList = {"+", "-", "x", ":"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operatorList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPilihan.setAdapter(adapter);

        btnHitung.setOnClickListener(v -> hitung());
    }

    private void hitung(){

        String nilai1 = etNumber1.getText().toString();
        String nilai2 = etNumber2.getText().toString();

        if(nilai1.isEmpty() || nilai2.isEmpty()){
            Toast.makeText(this,"Isikan Nilai 1 dan 2",Toast.LENGTH_LONG).show(); // ✅ tambahkan .show()
            return;
        }

        // ✅ tambahan: pastikan spinner tidak null
        if (spPilihan.getSelectedItem() == null) {
            Toast.makeText(this, "Pilih operator dulu", Toast.LENGTH_SHORT).show();
            return;
        }

        double angka1 = Double.parseDouble(nilai1);
        double angka2 = Double.parseDouble(nilai2);
        String operator = spPilihan.getSelectedItem().toString();
        double hasil = 0;

        switch (operator) {
            case "+":
                hasil = angka1 + angka2;
                break;
            case "-":
                hasil = angka1 - angka2;
                break;
            case "x":
                hasil = angka1 * angka2;
                break;
            case ":":
                if (angka2 == 0) {
                    Toast.makeText(this, "Tidak bisa dibagi 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                hasil = angka1 / angka2;
                break;
        }

        tvHasil.setText("Hasil " + hasil);

    }
}