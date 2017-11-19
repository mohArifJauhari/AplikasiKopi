package com.mahesaiqbal.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int jumlahKopi = 0;
    private int hargaKopi = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tambah(View view) {
        jumlahKopi += 1;
        tampilkan(jumlahKopi);
    }

    public void kurang(View view) {
        if(jumlahKopi == 0) {
            Toast.makeText(MainActivity.this, "Tidak bisa kurang dari 0",
                    Toast.LENGTH_SHORT).show();
        } else {
            jumlahKopi -= 1;
            tampilkan(jumlahKopi);
        }
    }

    public void totalHarga(View view) {
        TextView totalHarga = (TextView) findViewById(R.id.harga);
        totalHarga.setText("Rp " + hargaKopi * jumlahKopi);
    }

    public void tampilkan(int angka) {
        TextView txtJumlahKopi = (TextView) findViewById(R.id.jumlah_kopi);
        txtJumlahKopi.setText("" + angka);
    }

}
