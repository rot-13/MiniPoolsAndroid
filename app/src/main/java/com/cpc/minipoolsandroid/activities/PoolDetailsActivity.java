package com.cpc.minipoolsandroid.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cpc.minipoolsandroid.R;
import com.cpc.minipoolsandroid.adapters.ContributionsListAdapter;
import com.cpc.minipoolsandroid.models.Pool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PoolDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_POOL = "extra_pool";

    private Pool mPool;
    private TextView mCreatedByTextView;
    private RecyclerView mRecyclerView;
    private ContributionsListAdapter mAdapter;

    public static Intent createIntent(Context context, Pool pool) {
        Intent intent = new Intent(context, PoolDetailsActivity.class);
        intent.putExtra(EXTRA_POOL, pool);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_details);

        mPool = getIntent().getParcelableExtra("extra_pool");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mPool.name);

        String createdAtDesc = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault()).format(mPool.createdAt);
        mCreatedByTextView = (TextView) findViewById(R.id.text_pool_created_by);
        mCreatedByTextView.setText(getString(R.string.pool_creation, createdAtDesc, mPool.creator.name));

        mAdapter = new ContributionsListAdapter(mPool.contributions);

        mRecyclerView = (RecyclerView) findViewById(R.id.list_contributions);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
