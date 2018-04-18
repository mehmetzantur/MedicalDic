package com.mehmetzantur.medicaldic;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by mehme on 11.05.2016.
 */
public class Get_Record extends Activity {

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_record);

        final ListView listAll = (ListView)findViewById(R.id.listAll);

        Database db = new Database(Get_Record.this);

        try{
            db.connOpen();
            adapter = db.GetAllList(getApplicationContext());
            listAll.setAdapter(adapter);
            listAll.setTextFilterEnabled(true);
            db.connClose();

        }

        catch (Exception e){
            Dialog ex = new Dialog(Get_Record.this);
            ex.setTitle("Listeleme İşlemi");
            TextView tvError = new TextView(Get_Record.this);
            tvError.setText(e.toString());
            ex.setContentView(tvError);
            ex.show();
        }

        finally {
            Dialog dialog = new Dialog(Get_Record.this);
            dialog.setTitle("Listeleme İşlemi");
            TextView tvInfo = new TextView(Get_Record.this);
            tvInfo.setText("Başarılı");
            dialog.setContentView(tvInfo);
            dialog.show();
        }


    }
}
