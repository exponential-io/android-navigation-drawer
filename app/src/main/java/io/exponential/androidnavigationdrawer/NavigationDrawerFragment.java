package io.exponential.androidnavigationdrawer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NavigationDrawerFragment extends Fragment {
    private static final String ARG_BASE = "NavigationDrawerFragment.";
    private static final String ARG_PLACEHOLDER = ARG_BASE + "PLACEHOLDER";
    private String placeholder;
    private Callbacks callbacks;

    /**
     * Factory method to create a new instance of NavigationDrawerFragment.
     *
     * @param placeholder Parameter 1.
     * @return A new instance of fragment NavigationDrawerFragment.
     */
    public static NavigationDrawerFragment newInstance(String placeholder) {
        NavigationDrawerFragment fragment = new NavigationDrawerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PLACEHOLDER, placeholder);
        fragment.setArguments(args);
        return fragment;
    }

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            placeholder = getArguments().getString(ARG_PLACEHOLDER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callbacks = (Callbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Callbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    public interface Callbacks {
        public void placeholderCallback(String placeholderArg);
    }

}
