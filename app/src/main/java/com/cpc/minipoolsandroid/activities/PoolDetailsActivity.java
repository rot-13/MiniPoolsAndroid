package com.cpc.minipoolsandroid.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cpc.minipoolsandroid.R;
import com.cpc.minipoolsandroid.adapters.ContributionsListAdapter;
import com.cpc.minipoolsandroid.fragments.AddContributionFragment;
import com.cpc.minipoolsandroid.fragments.AddContributionNetworkFragment;
import com.cpc.minipoolsandroid.loaders.PoolDetailsLoader;
import com.cpc.minipoolsandroid.loaders.UsersLoader;
import com.cpc.minipoolsandroid.models.Contribution;
import com.cpc.minipoolsandroid.models.Pool;
import com.cpc.minipoolsandroid.models.User;
import com.cpc.minipoolsandroid.models.UsersCache;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PoolDetailsActivity extends AppCompatActivity implements
        AddContributionFragment.Listener,
        AddContributionNetworkFragment.Listener {

    private static final String EXTRA_POOL = "extra_pool";
    private static final String STATE_POOL = "state_pool";

    private Pool mPool;
    private TextView mCreatedByTextView;
    private RecyclerView mRecyclerView;
    private ContributionsListAdapter mAdapter;
    private AddContributionFragment mDialogFragment;
    private AddContributionNetworkFragment mNetworkFragment;
    private View mProgressContainer;

    public static Intent createIntent(Context context, Pool pool) {
        Intent intent = new Intent(context, PoolDetailsActivity.class);
        intent.putExtra(EXTRA_POOL, pool);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_details);

        if (savedInstanceState != null) {
            mPool = savedInstanceState.getParcelable(STATE_POOL);
        } else {
            mPool = getIntent().getParcelableExtra("extra_pool");
        }

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

        mProgressContainer = findViewById(R.id.progress_container);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogFragment = AddContributionFragment.newInstance(
                        UsersCache.getInstance().getUsers());
                mDialogFragment.show(getSupportFragmentManager(), AddContributionFragment.class.getSimpleName());
            }
        });

        mNetworkFragment = AddContributionNetworkFragment.getInstance(getSupportFragmentManager());

        getSupportLoaderManager().initLoader(0, null, new UsersLoaderCallback());
    }

    //<editor-fold desc="dialog listener">
    @Override
    public void onContribute(User contributor, String note, int amountValue, String amountCurrency) {
        Contribution contribution = new Contribution.Builder()
                .withContributor(contributor)
                .withAmount(amountValue, amountCurrency)
                .withNote(note)
                .build();
        mNetworkFragment.addContribution(mPool.id, contribution);
        mProgressContainer.setVisibility(View.VISIBLE);
    }

    //</editor-fold>

    //<editor-fold desc="network fragment listener">
    @Override
    public void onContributionSucceeded(Contribution contribution) {
        getSupportLoaderManager().restartLoader(1, null, new PoolDetailsLoaderCallback());
        mProgressContainer.setVisibility(View.INVISIBLE);
        Snackbar.make(
                findViewById(android.R.id.content),
                "Successfully added contribution",
                Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onContributionFailed(String errorMessage) {
        mProgressContainer.setVisibility(View.INVISIBLE);
        Snackbar.make(
                findViewById(android.R.id.content),
                "Could not add contribution: " + errorMessage,
                Snackbar.LENGTH_LONG).show();
    }
    //</editor-fold>


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_POOL, mPool);
    }

    private class UsersLoaderCallback implements LoaderManager.LoaderCallbacks<List<User>> {

        @Override
        public Loader<List<User>> onCreateLoader(int id, Bundle args) {
            return new UsersLoader(PoolDetailsActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<List<User>> loader, List<User> data) {
            UsersCache.getInstance().setUsers((ArrayList<User>) data);
        }

        @Override
        public void onLoaderReset(Loader<List<User>> loader) {
        }
    }

    private class PoolDetailsLoaderCallback implements LoaderManager.LoaderCallbacks<Pool> {

        @Override
        public Loader<Pool> onCreateLoader(int id, Bundle args) {
            return new PoolDetailsLoader(PoolDetailsActivity.this, mPool.id);
        }

        @Override
        public void onLoadFinished(Loader<Pool> loader, Pool data) {
            if (data != null) {
                mPool = data;
                mAdapter.setContributions(mPool.contributions);
            }
        }

        @Override
        public void onLoaderReset(Loader<Pool> loader) {
        }
    }
}
