# Android NavigationDrawer

Example Android project for learning how to implement a `NavigationDrawer` that uses Material
Design.

Developing this project consists of several discrete steps:

- Create a `Toolbar` and set as a Material Design compliant `ActionBar` (aka an appbar)
- Create a `NavigationDrawer` that conforms to Material Design
- Add a `RecyclerView` to the `NavigationDrawer` to display a menu
- Display a different `Fragment` on click of each menu item in the `NavigationDrawer`


## Process overview

- Create a new project with that uses **API 14: Android 4.0 (IceCreamSandwich)**. The new project
  should have a blank Activity.
- Create the ActionBar
- Add menu items to the ActionBar
- Create the CreateCompanyActivity
- Add the ActionBar to the CreateCompanyActivity
- Add the up button to the ActionBar
- Create the basic NavigationBar
- Apply a theme to the NavigationBar
- Add listeners for drawer events via `setDrawerListener()` in `MainActivity`
- Make NavigationDrawer appear under a transparent Status Bar
- Add RecyclerView menu to NavigationDrawer
- Handle RecyclerView click events
- Display different Fragments for each menu item
- Display NavigationDrawer on first run
- Prevent click through on NavigationDrawer

## Architecture

- MainActivity.java
    - inflates: menu_main.xml
    - inflates: activity_main.xml
        - references: dimens.xml
        - references: themes.xml
            - references: colors.xml
    - implements: MainFragmentCallbacks.java
    - adds: AMainFragment.java
        - inflates: fragment_amain.xml
        - uses instance of: MainFragmentCallbacks.java
    - adds: BMainFragment.java
        - inflates: fragment_bmain.xml
        - uses instance of: MainFragmentCallbacks.java
    - adds: CMainFragment.java
        - inflates: fragment_cmain.xml
        - uses instance of: MainFragmentCallbacks.java


# Section 1: Project setup

## Update MainActivity

- Change `ActionBarActivity` to `AppCompatActivity`

## Create 3 new fragments

- Create a new blank Fragment named `AMainFragment`
- Create a new blank Fragment named `BMainFragment`
- Create a new blank Fragment named `CMainFragment`

### Update the layouts of all 3 blank fragments

- Set the TextView to display 'A Main Fragment', 'B Main Fragment', or 'C Main Fragment' for each of
  the fragments.
- Change `FrameLayout` to `RelativeLayout`.
- Change the `TextView` height to `wrap_content`.
- Add a `Button` named `display_bactivity`.

### Update the Java class of all 3 blank fragments

- Change `import android.app.Fragment;` to `import android.support.v4.app.Fragment;`
- Change `OnFragmentInteractionListener` to `Callbacks`
- Change `mListener` to `callbacks`
- Change `ARG_PARAM1` to `ARG_MESSAGE` and set the value to `AMainFragment.MESSAGE`,
  `BMainFragment.MESSAGE` or `CMainFragment.MESSAGE` as appropriate.
- Change `mParam1` to `message`
- Change `param1` to `message` in the `newInstance` parameters
- In `onCreateView`, split the return statement into assignment and return.
- Delete the `onButtonPressed` method.
- Change the Callbacks Interface method name to `displayBActivity` with no parameters.
- Delete `param2` / `ARG_PARAM2` / `mParam2` and associated constants, local variables, everywhere
  in the Fragment.
- Delete all of the `TODO` comments
- Set the click listener for the `Button`. Call the `displayBActivity` Interface method when the
  button is clicked.

### Update `activity_main.xml`.

- Delete the `TextView`
- Insert a `FrameLayout` in the `RelativeLayout`
- Delete the "Hello world" string(s) from `strings.xml`.

### Update `MainActivity` to use `AMainFragment`

`MainActivity` will use `AMainFragment` as its default Fragment. When the app starts, the user will
see `MainActivity` / `AMainFragment`. The user will be unable to change fragments until we
implement the `NavigationDrawer` (which comes later in this tutorial).

- Create an `Interface` named `MainFragmentCallbacks`.
    - Create one interface method: `public void displayBActivity();`
- Make the following updates in `AMainFragment`, `BMainFragment` and `CMainFragment`:
    - Delete the `Callbacks` interface.
    - Change `private Callbacks callbacks;` to `private MainFragmentCallbacks callbacks;`.
    - In `onAttach`, change `callbacks = (Callbacks) activity;` to
      `callbacks = (MainFragmentCallbacks) activity;`.
    - In `onAttach`, change **Callbacks** to **MainFragmentCallbacks** in the exception.
- Make the following updates to `MainActivity`:
    - Add `implements MainFragmentCallbacks` to the `MainActivity` class declaration.
    - Override the `displayBActivity` Interface method, but do not implement any functionality yet.
    - Create an instance of `AMainFragment` in the `onCreate` method and insert it into the
      `FrameLayout`.


## Create `BActivity`

- Create a new blank Activity named `BActivity`. Set the hierarchical parent to `MainActivity`.
- Change `ActionBarActivity` to `AppCompatActivity`.


## Create `BFragment`

- Create a new blank Fragment named `BFragment`


### Update `fragment_b.xml`

- Set the TextView to display 'B Fragment'.
- Change `FrameLayout` to `RelativeLayout`.


### Update `BFragment.java`

- Change `import android.app.Fragment;` to `import android.support.v4.app.Fragment;`.
- Delete the `OnFragmentInteractionListener` Interface as this Fragment will not communicate with
  its parent Activity.
- Delete `mListener`.
- Delete `ARG_PARAM1` and `ARG_PARAM2`.
- Delete `mParam1` and `mParam1` and `param1` and `param2` from all parts of the code.
- Delete the `Bundle` related code from `newInstance`.
- Delete the `getArguments` related code from `onCreate`.
- Delete the Interface related code from `onAttach`.
- Delete the Interface related code from `onDetach`.
- In `onCreateView`, split the return statement into assignment and return.
- Delete the `onButtonPressed` method.
- Delete all of the `TODO` comments

### Update `activity_b.xml`.

- Delete the `TextView`.
- Insert a `FrameLayout` with an id of `container` in the `RelativeLayout`.

### Update `BActivity` to use `BFragment`

- Create an instance of `BFragment` in the `onCreate` method and insert it into the Activity.


## Start `BActivity` in `MainActivity.displayBActivity()`

- In `MainActivity.displayBActivity()`, start `BActivity` via an `Intent` and `startActivity()`.


# Section 2: Toolbar / ActionBar / app bar

## Update the theme

We are now going to update the theme to support Material Design. Also, we must disable the default
`ActionBar` in our theme before we can create a `Toolbar` and set it as the `ActionBar`.

### Colors

- Create `res/values/colors.xml`

### Dimensions

- Update `res/values/dimens.xml`
- Create `res/values-land/dimens.xml`

### Styles

- Update `res/values/styles.xml`
- Create `res/values-v21/styles.xml`

### Set style in `AndroidManifest.xml`

- In `AndroidManifest.xml` change `android:theme="@style/AppTheme"` to
  `android:theme="@style/Exponential.Theme.Light"`.


## Create the Toolbar (app bar)

Next, we will create a Toolbar and set it as the ActionBar. In terms of naming, Google has started
to refer to the ActionBar as the "app bar". Therefore, we will name our Toolbar ActionBar as
"appbar".

- Create layout/app_bar.xml
    - Set the root element to `android.support.v7.widget.Toolbar`.
    - Add `xmlns:app`
    - Set `android:layout_height="@dimen/app_bar_height"`
    - Add `app:theme`
    - Add `app:popupTheme`

## Set the `Toolbar` as the `ActionBar`

Get a reference to the `app_bar` in the `MainActivity` layout, then set the `Toolbar` as the
`ActionBar`.

- In `layout/activity_main.xml`, include the `actionbar` layout.
- In `layout/activity_main.xml`, remove all of the padding from `RelativeLayout`.
- In `MainActivity.onCreate()` find the Toolbar and call `setSupportActionBar()`


## Set the `app_bar` `Toolbar` as the `ActionBar` in `BActivity`

Currently we have two activities, `MainActivity` and `BActivity`. The `BActivity` layout and class
both need to be updated to use the `app_bar` `Toolbar` as the `ActionBar`.

- In `layout/activity_b.xml`, include the `actionbar` layout.
- In `layout/activity_b.xml`, remove all of the padding from `RelativeLayout`.
- In `BActivity.onCreate()` find the `app_bar` `Toolbar` and call `setSupportActionBar()`


## Bug fix: Add missing `layout_below`

If you try running the project at this point you'll notice two bugs:

1. Incorrect layout
2. Missing event handlers


- Add `android:layout_below="@id/app_bar"` to `FrameLayout` in both `activity_main.xml` and
  `activity_b.xml`.


## Bug fix: Add missing event handlers

- Make the following bug fixes in `AMainFragment`, `BMainFragment` and `CMainFragment`:
    - Add an event handler for the `Button` in `onCreateView`. Define the `View.OnClickListener`
      inline.


## Add the up button to the ActionBar

- In `BActivity.onCreate()`, set `getSupportActionBar().setDisplayHomeAsUpEnabled(true);`

ref: https://developer.android.com/training/basics/actionbar/adding-buttons.html
ref: http://developer.android.com/training/implementing-navigation/ancestral.html


## Add option menu items to `MainActivity`

Adding option menu items to an `ActionBar` created via a `Toolbar` is the same as how you would add
items to the default `ActionBar`.

Each Activity can customize the "Actions" displayed in the `ActionBar` by adding `&lt;item>`
elements in it's `menu/menu_activity_name.xml` file.

> Buttons added to the menu should have global applicability, such as **Search**.

There are 3 key steps in defining `ActionBar` actions (aka option menu items):

1. Create an `&lt;item>` element in the Activity's menu xml file `menu_main.xml`.
2. Inflate `menu_main` in the Activity's `onCreateOptionsMenu` method (Note: This code is
   generated by Android Studio...so there's nothing to do here)
3. Handle click events for the menu item in the Activity's `onOptionsItemSelected` method

In this example, we will add an action that displays a `Toast` message.

- Add the search icon image asset to the `drawable` directory. The search icons is in the Android
  Design Icons collection. Use the HOLO_DARK theme when importing the icon.
- Add an `&lt;item>` to `res/menu/menu_main.xml`.
- Create a new private method named `handleSearch` that displays a `Toast`.
- Update the Activity's `onOptionsItemSelected` method to handle the click event by calling
  `handleSearch` then the search icon is clicked.

> If your icon is not visible, then it's likely that you selected the wrong theme (i.e. HOLO_LIGHT
> instead of HOLO_DARK).


## Add option menu items to `BActivity`

Repeat the same steps to add an option menu item to BActivity's menu. In a production app, you can
support different ActionBar Actions per Activity.


# Section 3: NavigationDrawer

## Update activity layouts

We need to update the layout of both `MainActivity` and `BActivity` before we create the
`NavigationDrawer`.

- Make the following updates to `activity_layout.xml`:
    - Change `RelativeLayout` to `LinearLayout`.
    - In `LinearLayout`, use `android:orientation="vertical"`
    - In `FrameLayout`, delete `android:layout_below="@id/app_bar"`.
- Make the following updates to `activity_b.xml`:
    - Change `RelativeLayout` to `LinearLayout`.
    - In `LinearLayout`, use `android:orientation="vertical"`
    - In `FrameLayout`, delete `android:layout_below="@id/app_bar"`.


## Create the `NavigationDrawerFragment` Fragment

In this step we'll create a new blank Fragment. This step is separate from the setup to make it easy
to see what has changed in the source file via the git log.

- Create a new blank Fragment named `NavigationDrawerFragment`.


### Setup the `NavigationDrawerFragment` Fragment

In this step we'll do basic updates to `NavigationDrawerFragment.java`. The updates in this step are
similar to those that are done on any new Fragment.

- Change `import android.app.Fragment;` to `import android.support.v4.app.Fragment;`.
- Delete all unneeded comments.
- Create a new constant named `ARG_BASE`.
- Change `ARG_PARAM1` to `ARG_PLACEHOLDER` and set the value to `ARG_BASE + "PLACEHOLDER"`.
- Delete the `onButtonPressed` method.
- Rename the `OnFragmentInteractionListener` interface to `Callbacks`.
- Rename `mParam1` to `placeholder`.
- Rename `mListener` to `callbacks`.
- Delete `ARG_PARAM2`.
- Delete `mParam2`.
- Delete `param2` from `newInstance`.
- In `newInstance`, rename the `param1` parameter to `placeholder`.
- In `onCreateView`, split the return statement into assignment and return.
- Rename the `onFragmentInteraction` interface method to `placeholderCallback` and change the method
  signature to `public void placeholderCallback(String placeholderArg);`.

### Update `fragment_navigation_drawer.xml`

- Change `FrameLayout` to `RelativeLayout`
- Add the following attributes to the `RelativeLayout`
    - `android:id="@+id/navigation_drawer"`
    - `xmlns:app="http://schemas.android.com/apk/res-auto"`
    - `app:theme="@style/Exponential.Theme.Light.NavigationDrawer"`

### Update the theme

- Add the `ic_drawer.png` icon.
- Add `drawer_open` and `drawer_close` to `strings.xml`.
- Review the `NavigationDrawer` styles in the following files:
    - `values/styles.xml`
    - `values-v21/styles.xml`
    - `values/dimens.xml`
    - `values-land/dimens.xml`

### Add `DrawerLayout` to `activity_main.xml`

- Add a new root element of `android.support.v4.widget.DrawerLayout`.
- Move the following attributes from `LinearLayout` to `DrawerLayout`:
    - `xmlns:android`
    - `xmlns:tools`
    - `tools:context`
- Add the following attributes to `DrawerLayout`:
    - `android:id="@+id/navigation_drawer_layout"`
    - `xmlns:app="http://schemas.android.com/apk/res-auto"`
    - `android:fitsSystemWindows="true"`
- Add a `FrameLayout` for the `NavigationDrawer` below `LinearLayout`.
- Add the following attributes to `FrameLayout`:
    - `android:layout_gravity="start"`
    - `android:layout_width="@dimen/navigation_drawer_width"`
    - `android:layout_height="match_parent"`

### Wrap `fragment_amain.xml`, `fragment_bmain.xml`, `fragment_cmain.xml` in `ScrollView`

- Update `fragment_amain.xml` as follows:
    - Add a new root element of `ScrollView`
    - Move the following attributes from `RelativeLayout` to `ScrollView`:
        - `xmlns:android`
        - `xmlns:tools`
        - `tools:context`
    - Add the following attributes to `ScrollView`:
        - `android:layout_width="match_parent"`
        - `android:layout_height="match_parent"`
        - `android:paddingBottom="@dimen/activity_vertical_margin"`
        - `android:paddingLeft="@dimen/activity_horizontal_margin"`
        - `android:paddingRight="@dimen/activity_horizontal_margin"`
        - `android:paddingTop="@dimen/activity_vertical_margin"`
- Update `fragment_bmain.xml` using the same steps as above.
- Update `fragment_cmain.xml` using the same steps as above.

### Update `MainActivity` to support `NavigationDrawerFragment` Fragment

- Add `implements NavigationDrawerFragment.Callbacks` to the `MainActivity` class declaration.
- Override the `placeholderCallback` method required by `NavigationDrawerFragment.Callbacks`.
- Create two new member variables:
    - `private DrawerLayout drawerLayout;`
    - `private ActionBarDrawerToggle actionBarDrawerToggle;`
- Create an instance of `NavigationDrawerFragment` and insert it into `navigation_drawer`.
- Get a reference to the `DrawerLayout`.
- Create a new `ActionBarDrawerToggle` and set it as the drawer listener via
  `drawerLayout.setDrawerListener(actionBarDrawerToggle);`.
- Update the `ActionBar` by calling both `setDisplayHomeAsUpEnabled()` and `setHomeButtonEnabled()`
  with an argument of `true`.
- Create the `onPostCreate` method.
- Create the `onPrepareOptionsMenu` method.




## Download `ScrimInsetsFrameLayout.java` to your project


## Create the NavigationDrawer

- in activity_main.xml, change the root element to `android.support.v4.widget.DrawerLayout`. Push
  `LinearLayout` down one level in the layout's View hierarchy.
- Set the width for the drawer in both `values/dimens.xml` and `values-land/dimens.xml`.
- Create NavigationDrawerFragment
    - Change 'import android.app.Fragment;' to `import android.support.v4.app.Fragment;`
    - Make all other standard updates to a Fragment
        - Change `OnFragmentInteractionListener` to `Callbacks`
        - Change `mListener` to `callbacks`
        - Delete the `public void onButtonPressed` method
        - Rename the static args, the args members, and the newInstance() method as necessary
        - In `onCreateView()`, get an instance of `View`, then return it.
- Update fragment_navigation_drawer
    - Change the root element from `FrameLayout` to `RelativeLayout`
- Create an instance of `NavigationDrawerFragment` in `MainActivity` and insert it into
  `activity_main_navigation_drawer` in `activity_main.xml`


## NavigationDrawer theme

- Create a style for navigation drawer
- Set the theme in `fragment_navigation_drawer.xml` via `app:theme`
- Add default 16dp padding to `fragment_navigation_drawer.xml`


#### Add listeners for drawer events via `setDrawerListener()` in Activity

Setting a listener for the NavigationDrawer is similar to setting a listener for a button or other
clickable element.

- Import hamburger image
- update Activity onCreate
    - drawerLayout
    - drawerFragmentLayout
    - drawerToggle
    - setDrawerListener
    - setDisplayHomeAsUpEnabled
    - setHomeButtonEnabled
- update Activity onPrepareOptionsMenu
- override onPostCreate()
- override onConfigurationChanged()
- update onPrepareOptionsMenu()
- Add app icon to toggle NavigationDrawer


ref: https://developer.android.com/training/implementing-navigation/nav-drawer.html
ref: https://medium.com/sebs-top-tips/material-navigation-drawer-sizing-558aea1ad266

### Make NavigationDrawer appear under a transparent Status Bar

- Save ScrimInsetsFrameLayout.java into my project
    - https://github.com/google/iosched/blob/master/android/src/main/java/com/google/samples/apps/iosched/ui/widget/ScrimInsetsFrameLayout.java
- Create res/values/attrs.xml
- Update `activity_main.xml` by setting `android:fitsSystemWindows="true"` on both `DrawerLayout`
  and `ScrimInsetsFrameLayout`.
    - add ScrimInsetsFrameLayout
- Update `fragment_navigation_drawer.xml`. (Major design update)
- Add username, email, avatar to `NavigationDrawerFragment.java`
- Update `MainActivity.java` to point drawerFragmentLayout to ScrimInsetsFrameLayout


# Section 4: RecyclerView menu in NavigationDrawer
