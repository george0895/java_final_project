package com.example.user.afinal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by tomoya on 4/5/17.
 */

public class fragment3 extends Fragment {

    private EditText ed_scoreA, ed_scoreB, ed_TeamA,ed_TeamB, ed_number;
    private Button btn_query, btn_insert, btn_update, btn_delete;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items = new ArrayList<>();
    private SQLiteDatabase dbrw;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment3, container, false);

        super.onCreate(savedInstanceState);

        ed_number = rootView.findViewById(R.id.editText2);
        ed_scoreA = rootView.findViewById(R.id.ed_book);
        ed_scoreB = rootView.findViewById(R.id.editText);
        ed_TeamA = rootView.findViewById(R.id.ed_price);
        ed_TeamB = rootView.findViewById(R.id.editText3);
        btn_query = rootView.findViewById(R.id.btn_query);
        btn_insert = rootView.findViewById(R.id.btn_insert);
        btn_update = rootView.findViewById(R.id.btn_update);
        btn_delete = rootView.findViewById(R.id.btn_delete);
        listView = rootView.findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        dbrw = new MyDBHelper(rootView.getContext()).getWritableDatabase();

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c;
                if(ed_number.length()<1)
                    c = dbrw.rawQuery("SELECT * FROM myTable",null);
                else
                    c = dbrw.rawQuery("SELECT * FROM myTable WHERE number LIKE '"+ ed_number.getText().toString() + ed_TeamA.getText().toString() + ed_TeamB.getText().toString() + ed_scoreA.getText().toString() + ed_scoreB.getText().toString() +"'",null);
                c.moveToFirst();
                items.clear();
                Toast.makeText(getActivity(),"����" + c.getCount() +
                        "蝑���",Toast.LENGTH_SHORT).show();
                for(int i = 0; i < c.getCount(); i++){
                    items.add("蝺刻��:" + c.getString(0) + "\t\t\t ����:" + c.getString(1)+" VS "+c.getString(2)+"\t\t\t ��:"+c.getString(3)+" : "+c.getString(4));
                    c.moveToNext();
                }

                
                adapter.notifyDataSetChanged();
                c.close();
            }
        });
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_number.length()<1 || ed_scoreA.length()<1|| ed_scoreB.length()<1|| ed_TeamA.length()<1|| ed_TeamB.length()<1)
                    Toast.makeText(getActivity(),"甈����征", Toast.LENGTH_SHORT).show();
                else{
                    try{
                        dbrw.execSQL("INSERT INTO myTable( number, TeamA, TeamB, scoreA, scoreB) VALUES(?,?,?,?,?)", new Object[]{ed_number.getText().toString(),
                                ed_TeamA.getText().toString(),ed_TeamB.getText().toString(),ed_scoreA.getText().toString(),ed_scoreB.getText().toString()});
                        Toast.makeText(getActivity(),
                                "�憓楊���"+ ed_number.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                        ed_number.setText("");
                        ed_TeamA.setText("");
                        ed_TeamB.setText("");
                        ed_scoreA.setText("");
                        ed_scoreB.setText("");
                    }catch (Exception e){
                        Toast.makeText(getActivity(),"�憓仃���:"+
                                e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
  
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_number.length()<1 || ed_scoreA.length()<1|| ed_scoreB.length()<1|| ed_TeamA.length()<1|| ed_TeamB.length()<1)
                    Toast.makeText(getActivity(),"甈����征", Toast.LENGTH_SHORT).show();
                else{
                    try{
                        dbrw.execSQL("UPDATE myTable SET TeamA = '" +
                                ed_TeamA.getText().toString()  + "', TeamB = '" +ed_TeamB.getText().toString() + "', scoreA = '" +ed_scoreA.getText().toString()+ "', scoreB = '"+ed_scoreB.getText().toString() +
                                "' WHERE number LIKE '" + ed_number.getText().toString() + "'");
                        Toast.makeText(getActivity(),
                                "��蝺刻��:"+ ed_number.getText().toString(), Toast.LENGTH_SHORT).show();
                        ed_number.setText("");
                        ed_TeamA.setText("");
                        ed_TeamB.setText("");
                        ed_scoreA.setText("");
                        ed_scoreB.setText("");
                    }catch (Exception e){
                        Toast.makeText(getActivity(),"��憭望��:"+
                                e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_number.length()<1)
                    Toast.makeText(getActivity(),"蝺刻����征",
                            Toast.LENGTH_SHORT).show();
                else{
                    try{
                        dbrw.execSQL("DELETE FROM myTable WHERE number LIKE '" +ed_number.getText().toString() + "'");
                        Toast.makeText(getActivity(), "��蝺刻��:"+ ed_number.getText().toString() , Toast.LENGTH_SHORT).show();
                        ed_number.setText("");
                        ed_TeamA.setText("");
                        ed_TeamB.setText("");
                        ed_scoreA.setText("");
                        ed_scoreB.setText("");
                    }catch (Exception e){
                        Toast.makeText(getActivity(),"��憭望��:"+
                                e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        return rootView;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        dbrw.close();
    }
}
