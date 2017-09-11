package com.cpc.minipoolsandroid.activities;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cpc.minipoolsandroid.R;
import com.cpc.minipoolsandroid.adapters.PoolsListAdapter;
import com.cpc.minipoolsandroid.loaders.PoolsLoader;
import com.cpc.minipoolsandroid.models.Pool;

import java.util.List;

public class PoolsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Pool>>,
        PoolsListAdapter.Listener {

    private static final int POOLS_LOADER_ID = 0;

    private RecyclerView mRecyclerView;
    private PoolsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pools);

        mAdapter = new PoolsListAdapter();
        mAdapter.setListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.list_pools);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        getSupportLoaderManager().initLoader(POOLS_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Pool>> onCreateLoader(int id, Bundle args) {
        if (id == POOLS_LOADER_ID) {
            return new PoolsLoader(this);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Pool>> loader, List<Pool> data) {
        mAdapter.setPools(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Pool>> loader) {
    }

    @Override
    public void onPoolTapped(Pool pool) {
        startActivity(PoolDetailsActivity.createIntent(this, pool));
    }
}
