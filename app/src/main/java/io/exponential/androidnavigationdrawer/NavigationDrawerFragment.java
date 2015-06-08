package io.exponential.androidnavigationdrawer;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class NavigationDrawerFragment extends Fragment {
    private static final String ARG_BASE = "NavigationDrawerFragment.";
    private static final String ARG_USERNAME = ARG_BASE + "USERNAME";
    private static final String ARG_EMAIL = ARG_BASE + "EMAIL";
    private static final String ARG_AVATAR = ARG_BASE + "AVATAR";
    private String username;
    private String email;
    private int avatar;
    private Callbacks callbacks;

    /**
     * Factory method to create a new instance of NavigationDrawerFragment.
     *
     * @param username Username.
     * @param email User's email address.
     * @param avatar Id of the user's avatar image.
     * @return A new instance of fragment NavigationDrawerFragment.
     */
    public static NavigationDrawerFragment newInstance(String username, String email, int avatar) {
        NavigationDrawerFragment fragment = new NavigationDrawerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        args.putString(ARG_EMAIL, email);
        args.putInt(ARG_AVATAR, avatar);
        fragment.setArguments(args);
        return fragment;
    }

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            username = arguments.getString(ARG_USERNAME);
            email = arguments.getString(ARG_EMAIL);
            avatar = arguments.getInt(ARG_AVATAR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        // Programmatically change the NavigationDrawer's header background image
        //RelativeLayout navigationHeader = (RelativeLayout) view.findViewById(R.id.navigation_header);
        //navigationHeader.setBackgroundResource(R.drawable.sf);

        // Username
        TextView usernameTextView = (TextView) view.findViewById(R.id.username);
        usernameTextView.setText(username);

        // Email
        TextView emailTextView = (TextView) view.findViewById(R.id.email);
        emailTextView.setText(email);

        // Avatar
        // Note: The rounded image code below does not current work correctly.
        Resources res = getResources();
        ImageView avatarImageView = (ImageView) view.findViewById(R.id.avatar);
        Bitmap avatarImage = BitmapFactory.decodeResource(res, avatar);
        RoundedBitmapDrawable roundedAvatarImage = RoundedBitmapDrawableFactory
            .create(res, avatarImage);
        roundedAvatarImage.setCornerRadius(Math.max(avatarImage.getWidth(), avatarImage.getHeight()) / 2.0f);
        avatarImageView.setImageDrawable(roundedAvatarImage);
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
