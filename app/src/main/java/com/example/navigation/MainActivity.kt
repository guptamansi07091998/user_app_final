package com.example.navigation


import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

lateinit var toggle:ActionBarDrawerToggle

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar2)
        val nav_view=findViewById<NavigationView>(R.id.nav)
        toggle= ActionBarDrawerToggle(this, drawer,toolbar2,R.string.open,R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        nav.setNavigationItemSelectedListener (this)
        setToolbarTitle("Login")
        val fragment = supportFragmentManager.beginTransaction()
        fragment.addToBackStack("login")
        fragment.replace(R.id.fragment_container,login(),"login").commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer.closeDrawers()
        if(item.itemId==R.id.home){
            setToolbarTitle("Home")
            changeFragment(home(),"home")
        }

        if(item.itemId==R.id.edit){
            setToolbarTitle("Edit Profile")
            changeFragment(edit(),"edit")
        }
        if(item.itemId==R.id.contact){
            setToolbarTitle("Contact Details")
            changeFragment(contacts(),"contacts")
        }

        return true
    }

    private fun setToolbarTitle(title: String){
        supportActionBar?.title = title
    }


    fun changeFragment(fragment: Fragment, tag: String?) {
        //Get current fragment placed in container
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        //Prevent adding same fragment on top
        if (currentFragment!!.javaClass == fragment.javaClass) {
            return
        }

        //If fragment is already on stack, we can pop back stack to prevent stack infinite growth
        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            supportFragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        //Otherwise, just replace fragment
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(tag)
            .replace(R.id.fragment_container, fragment, tag)
            .commit()
    }


    override fun onBackPressed() {
        val fragmentsInStack = supportFragmentManager.backStackEntryCount
        if (fragmentsInStack > 1) { // If we have more than one fragment, pop back stack
            supportFragmentManager.popBackStack()
        } else if (fragmentsInStack == 1) { // Finish activity, if only one fragment left, to prevent leaving empty screen
            finish()
        } else {
            super.onBackPressed()
        }
    }

}