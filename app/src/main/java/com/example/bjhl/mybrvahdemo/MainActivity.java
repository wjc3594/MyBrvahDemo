package com.example.bjhl.mybrvahdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView iv_back;
    private Spinner s_animainStyle;
    private CheckBox c_isFirstOnly;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private List<String> animationList;
    private List<DataModel> dataModels;
    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initToolbar();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(R.layout.item_recycler_view, dataModels);
        //设置Adapter的监听点击事件
        setAdapterClickListener();
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        dataModels = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataModels.add(new DataModel("练习", "测试"));
        }
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        iv_back = findViewById(R.id.back);
        s_animainStyle = findViewById(R.id.animation_style);
        c_isFirstOnly = findViewById(R.id.is_first_only);
        c_isFirstOnly.setChecked(true);

        animationList = new ArrayList<>();
        animationList.add("Alphaln");
        animationList.add("Scaleln");
        animationList.add("SlideBottom");
        animationList.add("SlideLeft");
        animationList.add("SlideRight");
        animationList.add("Custom");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, animationList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_animainStyle.setAdapter(arrayAdapter);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        s_animainStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setAdapterAnimation(BaseQuickAdapter.ALPHAIN);
                        break;
                    case 1:
                        setAdapterAnimation(BaseQuickAdapter.SCALEIN);
                        break;
                    case 2:
                        setAdapterAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                        break;
                    case 3:
                        setAdapterAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        break;
                    case 4:
                        setAdapterAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
                        break;
                    default:
                        setAdapterAnimation(BaseQuickAdapter.ALPHAIN);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        c_isFirstOnly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adapter.isFirstOnly(true);
                } else {
                    adapter.isFirstOnly(false);
                }
            }
        });
    }

    private void setAdapterClickListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, "点击了view" + position, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, "长按了view" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);
                Toast.makeText(MainActivity.this, "点击了" + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, "长按了" + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void setAdapterAnimation(int animationStyle) {
        if (animationStyle == 0)
            adapter.openLoadAnimation();
        else
            adapter.openLoadAnimation(animationStyle);
    }
}
