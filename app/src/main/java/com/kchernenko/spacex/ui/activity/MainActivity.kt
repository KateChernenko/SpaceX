package com.kchernenko.spacex.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kchernenko.spacex.R
import com.kchernenko.spacex.ui.fragment.*

class MainActivity : AppCompatActivity(), LaunchesListFragment.LaunchesListFragmentContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
        initPagerTabsFragment()
    }

    private fun initPagerTabsFragment(){
        transactionFragmentWithStack(PagerTabsFragment.newInstance(), PagerTabsFragment.TAG)
    }

    private fun transactionFragmentWithStack(fragment: BaseFragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(tag)
            .commit()
    }

    override fun navigateToDetailsScreen(id: Int) {
        transactionFragmentWithStack(DetailsFragment.newInstance(id), DetailsFragment.TAG)
    }

    override fun onBackPressed() {
        when {
            supportFragmentManager.backStackEntryCount > 1 -> supportFragmentManager.popBackStackImmediate()
            else -> finish()
        }
    }

}