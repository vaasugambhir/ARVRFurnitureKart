package com.vaasugambhir.furniturekart.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.vaasugambhir.furniturekart.R
import com.vaasugambhir.furniturekart.databinding.ActivityMainBinding
import com.vaasugambhir.furniturekart.ui.fragments.OrdersFragment
import com.vaasugambhir.furniturekart.ui.fragments.SearchFragment
import com.vaasugambhir.furniturekart.ui.fragments.ShopFragment
import com.vaasugambhir.furniturekart.ui.fragments.UserFragment

class MainActivity : AppCompatActivity() {

    //private lateinit var arFragment: ArFragment
    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var ordersFragment: OrdersFragment
    private lateinit var shopFragment: ShopFragment
    private lateinit var userFragment: UserFragment
    private lateinit var searchFragment: SearchFragment

    private var isShop = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragments()
        init()
        initNav()
        setCurrentFragment(shopFragment)
    }

    private fun initNav() {
        binding.sideNav.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainNav_search -> {
                    isShop = false
                    setCurrentFragment(searchFragment)
                    closeDrawer()
                    println("Search")
                    return@setNavigationItemSelectedListener true
                }
                R.id.mainNav_orders -> {
                    isShop = false
                    setCurrentFragment(ordersFragment)
                    closeDrawer()
                    println("Orders")
                    return@setNavigationItemSelectedListener true
                }
                R.id.mainNav_shop -> {
                    isShop = true
                    setCurrentFragment(shopFragment)
                    closeDrawer()
                    println("Shop")
                    return@setNavigationItemSelectedListener true
                }
                R.id.mainNav_user -> {
                    isShop = false
                    setCurrentFragment(userFragment)
                    closeDrawer()
                    println("User")
                    return@setNavigationItemSelectedListener true
                }
                else -> {return@setNavigationItemSelectedListener false}
            }
        }
    }

    private fun init() {
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.mainDrawerLayout,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.mainDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun initFragments() {
        ordersFragment = OrdersFragment()
        shopFragment = ShopFragment()
        userFragment = UserFragment()
        searchFragment = SearchFragment()
    }

    private fun setCurrentFragment(fragment: Fragment) {
        val frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.fragmentView, fragment)
        frag.commit()
    }

    private fun closeDrawer() {
        if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START))
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
        if (isShop) super.onBackPressed()
        else setCurrentFragment(shopFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }
}