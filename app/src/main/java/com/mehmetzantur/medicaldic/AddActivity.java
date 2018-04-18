package com.mehmetzantur.medicaldic;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by mehme on 11.05.2016.
 */
public class AddActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtTitle = (EditText) findViewById(R.id.txtTitle);
                EditText txtDefi = (EditText) findViewById(R.id.txtDefi);
                String title = txtTitle.getText().toString();
                String defi = txtDefi.getText().toString();

                try {
                    Database db = new Database(AddActivity.this);
                    db.connOpen();
                    db.SaveWord(title, defi);
                    db.connClose();
                } catch (Exception e) {
                    Dialog ex = new Dialog(AddActivity.this);
                    ex.setTitle("Ekleme İşlemi");
                    TextView tvError = new TextView(AddActivity.this);
                    tvError.setText(e.toString());
                    ex.setContentView(tvError);
                    ex.show();
                } finally {
                    Dialog dialog = new Dialog(AddActivity.this);
                    dialog.setTitle("Ekleme İşlemi");
                    TextView tvInfo = new TextView(AddActivity.this);
                    tvInfo.setText("Başarılı");
                    dialog.setContentView(tvInfo);
                    dialog.show();
                }
            }
        });

        Button btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(getApplicationContext(),Get_Record.class);
                startActivity(act);
            }
        });

    }
}
