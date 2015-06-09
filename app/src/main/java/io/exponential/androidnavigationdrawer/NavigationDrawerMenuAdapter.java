package io.exponential.androidnavigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


public class NavigationDrawerMenuAdapter
    extends RecyclerView.Adapter<NavigationDrawerMenuAdapter.MenuViewHolder> {

    // LayoutInflater parses the layout xml and instantiates the corresponding View object
    // instances in an object hierarchy.
    private LayoutInflater inflater;

    // menu contains a List of NavigationDrawerMenuItem instances.
    // By default, initialize menu as an empty list.
    private List<NavigationDrawerMenuItem> menu = Collections.emptyList();

    /**
     * Constructor to create a new instance of NavigationDrawerMenuAdapter. The new instance of
     * NavigationDrawerMenuAdapter is created in NavigationDrawerFragment.
     *
     * @param context The containing Activity's context.
     * @param menu    The list that contains all NavigationDrawerMenuItem instances.
     */
    public NavigationDrawerMenuAdapter(Context context, List<NavigationDrawerMenuItem> menu) {
        inflater = LayoutInflater.from(context);
        this.menu = menu;
    }

    /**
     * Inflate the `navigation_drawer_menu_item` layout and return it as an instance of
     * MenuViewHolder. MenuViewHolder is an inner class defined near the bottom of this file as a
     * subclass of RecyclerView.ViewHolder.
     *
     * @param viewGroup The parent ViewGroup that contains all instances of
     *                  navigation_drawer_menu_item.
     * @param i         The view type of the new View.
     * @return An instance of MenuViewHolder that contains private members that point to each view
     * in navigation_drawer_menu_item that will be populated with data from the members in
     * NavigationDrawerMenuItem.
     */
    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // navigationDrawerMenuItemView holds an inflated instance of the
        // navigation_drawer_menu_item layout.
        View navigationDrawerMenuItemView = inflater
            .inflate(R.layout.navigation_drawer_menu_item, viewGroup, false);

        // The menuViewHolder instance has 3 private members each of which hold the id of each view
        // within the navigation_drawer_menu_item layout that we will later insert data into.
        MenuViewHolder menuViewHolder = new MenuViewHolder(navigationDrawerMenuItemView);

        return menuViewHolder;
    }

    /**
     * Bind each field in NavigationDrawerMenuItem to a View in navigation_drawer_menu_item.
     * <p/>
     * Method called by the RecyclerView when it needs to display each data item in the layout for
     * each item. Specifically, this method binds a single NavigationDrawerMenuItem to a single
     * navigation_drawer_menu_item layout.
     *
     * @param viewHolder The instance of ViewHolder that holds the navigation_drawer_menu_item.
     * @param i          The index of the current NavigationDrawerMenuItem.
     */
    @Override
    public void onBindViewHolder(MenuViewHolder viewHolder, int i) {
        // Get the current NavigationDrawerMenuItem
        NavigationDrawerMenuItem item = menu.get(i);

        String count = (item.getCount() > 99) ? "99+" : Integer.toString(item.getCount());

        // Bind each field in NavigationDrawerMenuItem to a view in
        viewHolder.iconImageView.setImageResource(item.getImageId());
        viewHolder.titleTextView.setText(item.getTitle());
        viewHolder.countTextView.setText(count);
    }

    /**
     * Return the number of elements in the adapter's data set.
     *
     * @return Count of elements.
     */
    @Override
    public int getItemCount() {
        return menu.size();
    }

    /**
     * Store the id of each view within the item layout.
     */
    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconImageView;
        private TextView titleTextView;
        private TextView countTextView;

        public MenuViewHolder(View itemView) {
            super(itemView);
            iconImageView = (ImageView) itemView.findViewById(R.id.icon);
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            countTextView = (TextView) itemView.findViewById(R.id.count);
        }
    }
}
