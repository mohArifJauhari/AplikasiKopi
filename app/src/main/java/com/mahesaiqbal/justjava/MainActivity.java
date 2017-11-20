package com.mahesaiqbal.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int jumlahKopi = 0;
    private final int hargaKopi = 3000;
    private int totalHarga = 0;
    CheckBox checkKrim, checkCoklat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkKrim = findViewById(R.id.check_krim);
        checkCoklat = findViewById(R.id.check_coklat);
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

    public void order(View view) {
        String toppingKrim = "";
        String toppingCoklat = "";
        Boolean ckKrim = checkKrim.isChecked();
        Boolean ckCoklat = checkCoklat.isChecked();

        if(ckKrim) {
            //Menghasilkan nilai true or false
            toppingKrim = "Krim, ";
        }
        if(ckCoklat) {
            //Menghasilkan nilai true or false
            toppingCoklat = "Coklat,  ";
        }
        int totalHarga = tampilTotalHarga(ckKrim, ckCoklat);
        String tampilkanHarga = tampilkanHarga(totalHarga, toppingKrim, toppingCoklat);

        //Mengirim laporan pemesanan ke email
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Laporan pemesanan");
        intent.putExtra(Intent.EXTRA_TEXT, tampilkanHarga);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public String tampilkanHarga(int harga, String ckKrim, String ckCoklat) {
        EditText edtNama = findViewById(R.id.input_nama);
        String nama = edtNama.getText().toString();
        String pesannya = "Terima kasih, " + nama +  " telah membeli kopi \n";
        pesannya += "\nTopping = " + ckKrim + ckCoklat;
        pesannya += "\nHarga = " + harga;
        return pesannya;
    }

    public int tampilTotalHarga(Boolean ckKrim, Boolean ckCoklat) {

        if(ckKrim) {
            totalHarga += 2000;
        }
        if(ckCoklat) {
            totalHarga += 3000;
        }

        //hargaAkhir = totalHarga;
        totalHarga = totalHarga + hargaKopi * jumlahKopi;
        return totalHarga;
    }
}
