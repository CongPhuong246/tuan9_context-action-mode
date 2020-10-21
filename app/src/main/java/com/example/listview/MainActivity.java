package com.example.listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.listview.adapter.CustomAdapter;
import com.example.listview.mode.Nhac;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int[] color_lv = new int[]{Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE};
    String[] baihat_lv = new String[]{"Ánh Nắng Của Anh","Lạc Trôi","Sóng Gió","Nàng Thơ","Em Của Quá Khứ"};
    String[] ten_lv = new String[]{"Đức Phúc","Sơn Tùng","Jack","Công Phương","Nguyễn Đình Vũ"};
    Button bt;
    ListView lvnhac;
    int vitri = -1;
    EditText editnhac,editcasi;
    ArrayList<Nhac> arrayList;
    CustomAdapter customAdapter;
    private ArrayList<Nhac> UserSelection = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvnhac = (ListView) findViewById(R.id.lv);
        editnhac = (EditText) findViewById(R.id.edt_baihat);
        editcasi = (EditText) findViewById(R.id.edt_nghesi);
        arrayList = new ArrayList<>();
        for(int i =0;i<= color_lv.length-1;i++)
        {
            Nhac nhac = new Nhac(color_lv[i],baihat_lv[i],ten_lv[i]);
            arrayList.add(nhac);
        }

        customAdapter = new CustomAdapter(this,R.layout.listview,arrayList);
        lvnhac.setAdapter(customAdapter);

        lvnhac.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvnhac.setMultiChoiceModeListener(modeListener);
        registerForContextMenu(bt);

        lvnhac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editnhac.setText(arrayList.get(position).getmNhac());
                editcasi.setText(arrayList.get(position).getmTen());
                vitri=position;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.menu_contex, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.them:
                String bhat= editnhac.getText().toString();
                String name = editcasi.getText().toString();
                Nhac nhac = new Nhac(Color.BLUE,bhat,name);
                arrayList.add(nhac);
                customAdapter.notifyDataSetChanged();
                break;
            case R.id.sua:
                Nhac nhac2 = new Nhac(Color.BLUE,editnhac.getText().toString(),editcasi.getText().toString());
                arrayList.set(vitri,nhac2);
                customAdapter.notifyDataSetChanged();
                break;
            case R.id.xoa:
                arrayList.remove(vitri);
                customAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
    AbsListView.MultiChoiceModeListener modeListener = new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
            if(UserSelection.contains(arrayList.get(position)))
            {
                UserSelection.remove(arrayList.get(position));
            }
            else
            {
                UserSelection.add(arrayList.get(position));
            }
            mode.setTitle(UserSelection.size() + " item selected...");
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.menu_context2,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.delete:
                    customAdapter.removeItem(UserSelection);
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            UserSelection.clear();
        }
    };
}