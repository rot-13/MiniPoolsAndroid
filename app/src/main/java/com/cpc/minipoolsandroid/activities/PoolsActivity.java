package com.cpc.minipoolsandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cpc.minipoolsandroid.R;
import com.cpc.minipoolsandroid.adapters.PoolsListAdapter;
import com.cpc.minipoolsandroid.models.Pool;

import java.util.Arrays;
import java.util.List;

public class PoolsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PoolsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pools);

        mAdapter = new PoolsListAdapter();

        mRecyclerView = (RecyclerView) findViewById(R.id.list_pools);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        List<Pool> pools = Arrays.asList(
                new Pool.Builder().withName("Red Wedding").build(),
                new Pool.Builder().withName("Seven Kingdoms").build()
        );
        mAdapter.setPools(pools);
    }

}
