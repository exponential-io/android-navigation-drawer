package io.exponential.androidnavigationdrawer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NavigationDrawerFragment extends Fragment {
    private static final String ARG_BASE = "NavigationDrawerFragment.";
    private static final String ARG_USERNAME = ARG_BASE + "USERNAME";
    private static final String ARG_EMAIL = ARG_BASE + "EMAIL";
    private static final String ARG_AVATAR = ARG_BASE + "AVATAR";
    private String username;
    private String email;
    private int avatar;
    private Callbacks callbacks;
    private RecyclerView navigationDrawerMenuRecyclerView;
    private NavigationDrawerMenuAdapter navigationDrawerMenuAdapter;

    /**
     * Factory method to create a new instance of NavigationDrawerFragment.
     *
     * @param username Username.
     * @param email    User's email address.
     * @param avatar   Id of the user's avatar image.
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

        // NavigationDrawer RecyclerView menu
        // Get a reference to the navigation_menu RecyclerView in the layout
        navigationDrawerMenuRecyclerView = (RecyclerView) view.findViewById(R.id.navigation_menu);

        // Create an instance of the adapter by passing in the context (getActivity) and the list
        // of menu items (getNavigationDrawerMenu). getNavigationDrawerMenu() is a method that we
        // defined below to return a list of NavigationDrawerMenuItem instances.
        navigationDrawerMenuAdapter = new NavigationDrawerMenuAdapter(getActivity(), getNavigationDrawerMenu());

        // Set the RecyclerView's adapter to the NavigationDrawer's menu adapter
        navigationDrawerMenuRecyclerView.setAdapter(navigationDrawerMenuAdapter);

        // Set the RecyclerView's layout manager to a layout manager that is provided by Android
        navigationDrawerMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Add a new touch listener in a 3 step process
        // 1. Create a ClickListener instance
        // 2. Create a RecyclerViewTouchListener instance by passing in the ClickListener instance
        // 3. Add the RecyclerViewTouchListener instance to the navigationDrawerMenuRecyclerView's
        //    list of available event handlers / touch listeners.

        // Implement the ClickListener interface defined in this file.
        ClickListener clickListener = new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                NavigationDrawerMenuItem item = getNavigationDrawerMenuItem(position);
                callbacks.setHomeScreen(position);
            }

            @Override
            public void onLongClick(View view, int position) {
                NavigationDrawerMenuItem item = getNavigationDrawerMenuItem(position);
                callbacks.setHomeScreen(position);
            }
        };

        // Create an instance of the RecyclerViewTouchListener inner class defined in this file.
        RecyclerViewTouchListener recyclerViewTouchListener = new RecyclerViewTouchListener(
            getActivity(), navigationDrawerMenuRecyclerView, clickListener);

        // Bind recyclerViewTouchListener to navigationDrawerMenuRecyclerView's event handlers
        navigationDrawerMenuRecyclerView.addOnItemTouchListener(recyclerViewTouchListener);

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
        public void setHomeScreen(int menuPosition);
    }

    /**
     * Extract and return one NavigationDrawerMenuItem from the list of menu items.
     *
     * @param position Position of item
     * @return The NavigationDrawerMenuItem located at position.
     */
    public static NavigationDrawerMenuItem getNavigationDrawerMenuItem(int position) {
        // Get the entire menu
        List<NavigationDrawerMenuItem> menu = getNavigationDrawerMenu();

        // Extract and return the item located at position
        NavigationDrawerMenuItem item = menu.get(position);
        return item;
    }

    private static List<NavigationDrawerMenuItem> getNavigationDrawerMenu() {
        List<NavigationDrawerMenuItem> menu = new ArrayList<>();

        int[] icons = {
            R.drawable.ic_assignment_black,
            R.drawable.ic_book_black,
            R.drawable.ic_dashboard_black,
            R.drawable.ic_question_answer_black
        };

        String[] titles = {"Assignments", "Books", "Dashboard", "Q&A"};

        int[] count = {15, 4, 12, 54};

        for (int i = 0; i < icons.length && i < titles.length && i < count.length; i++) {
            NavigationDrawerMenuItem item = new NavigationDrawerMenuItem(icons[i], titles[i], count[i]);
            menu.add(item);
        }

        return menu;
    }

    public interface ClickListener {
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }

    class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerViewTouchListener(Context context, final RecyclerView recyclerView,
                                         final ClickListener clickListener) {

            this.clickListener = clickListener;

            // Create a new instance of SimpleOnGestureListener
            // SimpleOnGestureListener is from GestureDetector.SimpleOnGestureListener
            SimpleOnGestureListener simpleOnGestureListener = new SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    //return super.onSingleTapUp(e);
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    //super.onLongPress(e);
                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (childView != null && clickListener != null) {
                        clickListener.onLongClick(childView, recyclerView.getChildPosition(childView));
                    }
                }
            };

            // Create a new GestureDetector by passing in the current context and the
            // simpleOnGestureListener that is created above.
            gestureDetector = new GestureDetector(context, simpleOnGestureListener);
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            // return false to allow the event to propagate down to the children so that a child
            // has a chance to precess the event
            View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

            if (childView != null && clickListener != null && gestureDetector.onTouchEvent(motionEvent)) {
                clickListener.onClick(childView, recyclerView.getChildPosition(childView));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

        }
    }

}
