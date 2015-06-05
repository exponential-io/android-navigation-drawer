# android-navigation-drawer

Example Android project for learning how to implement a NavigationDrawer that uses Material Design.

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

## Create the ActionBar

We're going to create a Toolbar and set it as the ActionBar.

- Create theme files.
- Create layout/actionbar.xml
- in Activity.onCreate() find toolbar and call setSupportActionBar()
- Create strings, dimens, etc. resources
- update the layout structure of layout/activity_main.xml
    - include app bar in main_activity.xml