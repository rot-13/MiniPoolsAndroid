package com.cpc.minipoolsandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cpc.minipoolsandroid.R;
import com.cpc.minipoolsandroid.models.User;

import java.util.ArrayList;
import java.util.List;

public class AddContributionFragment extends DialogFragment {

    public interface Listener {
        void onContribute(User contributor, String note, int amountValue, String amountCurrency);
    }

    private static final String ARG_USERS = "arg_users";

    private List<User> mUsers;
    private Spinner mSpinnerView;
    private Button mCancelButton;
    private Button mContributeButton;
    private EditText mNoteView;
    private EditText mAmountView;
    private Listener mListener;

    public AddContributionFragment() {
        // Required empty public constructor
    }

    public static AddContributionFragment newInstance(ArrayList<User> users) {
        AddContributionFragment fragment = new AddContributionFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_USERS, users);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);

        if (getArguments() != null) {
            mUsers = getArguments().getParcelableArrayList(ARG_USERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_contribution, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSpinnerView = (Spinner) view.findViewById(R.id.spinner_users);
        ArrayAdapter<User> adapter = new ArrayAdapter<>(
                this.getContext(), android.R.layout.simple_spinner_item, mUsers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerView.setAdapter(adapter);

        mNoteView = (EditText) view.findViewById(R.id.edit_text);
        mAmountView = (EditText) view.findViewById(R.id.edit_amount);

        mCancelButton = (Button) view.findViewById(R.id.btn_cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddContributionFragment.this.dismiss();
            }
        });

        mContributeButton = (Button) view.findViewById(R.id.btn_contribute);
        mContributeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
    }

    private void onSubmit() {
        if (mListener == null) return;

        User contributor = (User) mSpinnerView.getSelectedItem();
        String note = mNoteView.getText().toString();
        int amount = Integer.parseInt(mAmountView.getText().toString());
        AddContributionFragment.this.dismiss();
        mListener.onContribute(contributor, note, amount, "USD");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (Listener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
