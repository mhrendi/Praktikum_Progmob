package com.example.modul2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HasilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        Button button = findViewById(R.id.buttonmenu);
        Button button1 = findViewById(R.id.buttonupdate);
        Button button2 = findViewById(R.id.buttondelete);

        Intent intent = getIntent();
        String mid = intent.getStringExtra("ID");
        String mjudul = intent.getStringExtra("JUDUL");
        String mtahun = intent.getStringExtra("TAHUN");
        String mradio = intent.getStringExtra("RADIO");
        String mrating = intent.getStringExtra("RATING");
        String mgenre = intent.getStringExtra("GENRE");

        TextView Judul = findViewById(R.id.judul);
        TextView Tahun = findViewById(R.id.tahun);
        TextView Radio = findViewById(R.id.radio);
        TextView Rating = findViewById(R.id.rating);
        TextView Genre = findViewById(R.id.genre);

        Judul.setText("Judul : " + mjudul);
        Tahun.setText("tahun : " + mtahun);
        Radio.setText("Rating Umur : " + mradio);
        Rating.setText("Judul : " + mrating);
        Genre.setText("Judul : " + mgenre);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilActivity.this, UpdateActivity.class);
                intent.putExtra("ID", mid);
                intent.putExtra("JUDUL", mjudul);
                intent.putExtra("TAHUN", mtahun);
                intent.putExtra("RADIO", mradio);
                intent.putExtra("RATING", mrating);
                intent.putExtra("GENRE", mgenre);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogConfirm(mid);
            }
        });


    }
    void dialogConfirm(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Data");
        builder.setMessage("Apakah anda yakin ingin menghapus data ini?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper myDB = new DBHelper(HasilActivity.this);
                myDB.deleteData(id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.create().show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(HasilActivity.this, "Data Film", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(HasilActivity.this, "Telah Keluar Dari Preview Data", Toast.LENGTH_SHORT).show();
    }
}