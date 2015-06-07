package io.exponential.androidnavigationdrawer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AMainFragment extends Fragment {
    private static final String ARG_MESSAGE = "AMainFragment.MESSAGE";
    private String message;
    private MainFragmentCallbacks callbacks;

    /**
     * Factory method to create a new instance of AMainFragment.
     *
     * @param message Message passed from Activity during creation of a new Fragment instance.
     * @return A new instance of fragment AMainFragment.
     */
    public static AMainFragment newInstance(String message) {
        AMainFragment fragment = new AMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    public AMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString(ARG_MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_amain, container, false);

        // Event handlers
        // Note: Define the onClickListener inline.
        ((Button) view.findViewById(R.id.display_bactivity))
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callbacks.displayBActivity();
                }
            });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callbacks = (MainFragmentCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement MainFragmentCallbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

}
