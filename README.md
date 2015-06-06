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
- When creating `BActivity`, set the parent to `MainActivity`

### Update `fragment_b.xml`

- Set the TextView to display 'B Fragment'.
- Change `FrameLayout` to `RelativeLayout`.


### Update `BFragment.java`

- Change `ActionBarActivity` to `AppCompatActivity`
- Change `import android.app.Fragment;` to `import android.support.v4.app.Fragment;`.
- Delete the `OnFragmentInteractionListener` Interface.
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

- Delete the `TextView`
- Insert a `FrameLayout` in the `RelativeLayout`

### Update `BActivity` to use `BFragment`

- Create an instance of `BFragment` in the `onCreate` method and insert it into the Activity


## Start `BActivity` in `MainActivity.displayBActivity()`

- Call `startActivity()` in `MainActivity.displayBActivity()`


## Update the theme

We must disable the default `ActionBar` before we can create a `Toolbar` and set it as the
`ActionBar`.

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
  `android:theme="@style/Exponential.Theme.Colors"`.


## Create the ActionBar

Next, we will create a Toolbar and set it as the ActionBar.

- Create layout/actionbar.xml
    - Set the root element to `android.support.v7.widget.Toolbar`.
    - Add `xmlns:app`
    - Set `android:layout_height="@dimen/actionbar_height"`
    - Add `app:theme`
    - Add `app:popupTheme`

## Set the `Toolbar` as the `ActionBar`

- In `layout/activity_main.xml`, include the `actionbar` layout.
- In `layout/activity_main.xml`, remove all of the padding from `RelativeLayout`.
- In `MainActivity.onCreate()` find the Toolbar and call `setSupportActionBar()`


## Use the ActionBar in `BActivity`

- In `layout/activity_b.xml`, include the `actionbar` layout.
- In `layout/activity_b.xml`, remove all of the padding from `RelativeLayout`.
- In `BActivity.onCreate()` find the Tool   bar and call `setSupportActionBar()`


## Add the up button to the ActionBar

- In `BActivity.onCreate()`, set `getSupportActionBar().setDisplayHomeAsUpEnabled(true);`

ref: https://developer.android.com/training/basics/actionbar/adding-buttons.html
ref: http://developer.android.com/training/implementing-navigation/ancestral.html


## Add option menu items for `MainActivity`

Adding option menu items to an `ActionBar` created via a `Toolbar` is the same as how you would add
items to the default `ActionBar`.

Each Activity can customize the "Actions" displayed in the `ActionBar` by adding `&lt;item>`
elements in it's `menu/menu_activity_name.xml` file.

> Buttons added to the menu should have global applicability, such as **Search**.

There are 3 key steps in defining `ActionBar` actions (aka option menu items):

1. Create an `&lt;item>` element in the Activity's menu xml file
2. Inflate the menu xml file in the Activity's `onCreateOptionsMenu` method (Note: This code is
   generated by Android Studio...so there's nothing to do here)
3. Handle click events for the menu item in the Activity's `onOptionsItemSelected` method

In this example, we will add an action that displays a `Toast` message.

- Add the search icon image asset from the Android Design Icons collection. Use the HOLO_DARK theme
  icons.
- Add an `&lt;item>` to `res/menu/menu_main.xml`.
- Update the Activity's `onOptionsItemSelected` method to handle the click event.

> If your icon is not visible, then it's likely that you selected the wrong theme (i.e. HOLO_LIGHT
> instead of HOLO_DARK).


## Add menu items for `BActivity`

Repeat the same steps to add an option menu item to BActivity's menu. In a production app, you can
support different ActionBar Actions per Activity.


## Additional reading

- ActionBar: http://developer.android.com/guide/topics/ui/actionbar.html
- Toolbar: http://developer.android.com/reference/android/support/v7/widget/Toolbar.html
- Menus: http://developer.android.com/guide/topics/ui/menus.html
- Material Design:
    - http://developer.android.com/training/material/index.html
    - http://www.google.com/design/spec/material-design/introduction.html