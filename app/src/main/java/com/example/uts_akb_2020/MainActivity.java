package com.example.uts_akb_2020;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper myDb;
    EditText ednim, ednama, edkelas, edprodi;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DBHelper(this);
        ednim = (EditText) findViewById(R.id.editnim);
        ednama = (EditText) findViewById(R.id.editnama);
        edkelas = (EditText) findViewById(R.id.editkelas);
        edprodi = (EditText) findViewById(R.id.editprodi);
        btnAddData = (Button) findViewById(R.id.btnsimpan);
        btnViewAll = (Button) findViewById(R.id.btntampil);
        btnUpdate = (Button) findViewById(R.id.btnedit);
        btnDelete = (Button) findViewById(R.id.btnhapus);
        addData();
        viewAll();
        updateData();
        deleteData();
    }

    //Hapus
    public void deleteData() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(ednim.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(MainActivity.this, "Data Dihapus",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Gagal Dihapus",
                            Toast.LENGTH_LONG).show();
            }
        });
    }

    //Update
    public void updateData() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(ednim.getText().toString(),
                        ednama.getText().toString(), edkelas.getText().toString(), edprodi.getText().toString());
                if (isUpdate == true)
                    Toast.makeText(MainActivity.this, "Data Berhasil Dirubah",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data gagal Dirubah",
                            Toast.LENGTH_LONG).show();
            }
        });
    }

    //Simpan
    public void addData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(ednim.getText().toString(),ednama.getText().toString(),edkelas.getText().toString(), edprodi.getText().toString());
                if (isInserted == true)
                    Toast.makeText(MainActivity.this, "Data Berhasil Disimpan",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Gagal Disimpan",
                            Toast.LENGTH_LONG).show();
            }
        });
    }

    //Tampil
    public void viewAll() {
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("NIM : " + res.getString(0) + "\n");
                    buffer.append("NAMA : " + res.getString(1) + "\n");
                    buffer.append("KELAS : " + res.getString(2) + "\n");
                    buffer.append("Program Studi : " + res.getString(3) + "\n");
                }
                showMessage("Data", buffer.toString());
            }
        });
    }

    //Alert Dialog
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}