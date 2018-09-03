package douglasspgyn.com.github.fragmentstackmanager.base

import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import douglasspgyn.com.github.fragmentstackmanager.`interface`.FragmentNavigation
import douglasspgyn.com.github.fragmentstackmanager.`interface`.FragmentStackManager
import douglasspgyn.com.github.fragmentstackmanager.controller.FragmentNavigationController
import douglasspgyn.com.github.fragmentstackmanager.controller.RootFragmentHistory

/**
 * Created by Douglas on 3/9/18.
 */

/**
 * @param frameLayout The resource id of the Frame Layout that will be inflated
 * @param bottomNavigationStartPosition The start position on the Bottom Navigation View
 */
abstract class BaseStackActivity(@IdRes private val frameLayout: Int, private val bottomNavigationStartPosition: Int) : AppCompatActivity(), FragmentNavigation {

    private val rootFragmentHistory: RootFragmentHistory = RootFragmentHistory(bottomNavigationStartPosition)
    private lateinit var fragmentNavigationController: FragmentNavigationController
    private var currentItem: Int = -1
    private var stackBackPressedListener: FragmentStackManager.OnBackPressed? = null
    abstract fun bottomNavigationView(): BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentNavigationController = FragmentNavigationController(this, supportFragmentManager, frameLayout)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        updateStack(bottomNavigationStartPosition)
    }

    fun updateStack(itemPosition: Int) {
        currentItem = itemPosition
        rootFragmentHistory.push(itemPosition)
        fragmentNavigationController.updateRootFragment(itemPosition)
        bottomNavigationView().menu.getItem(itemPosition).isChecked = true
    }

    override fun pushFragment(fragment: Fragment) {
        fragmentNavigationController.pushFragment(fragment)
    }

    override fun popFragment() {
        fragmentNavigationController.popFragment()
    }

    override fun fragmentChanged() {
        supportActionBar?.setDisplayHomeAsUpEnabled(!fragmentNavigationController.isRootFragment())
    }

    fun updateToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun setOnStackBackPressedListener(listener: FragmentStackManager.OnBackPressed) {
        stackBackPressedListener = listener
    }

    override fun onBackPressed() {
        if (!fragmentNavigationController.isRootFragment()) {
            fragmentNavigationController.popFragment()
        } else {
            if (rootFragmentHistory.size() > 1) {
                rootFragmentHistory.pop()

                val peek = rootFragmentHistory.peek()
                updateStack(peek)
            } else {
                stackBackPressedListener?.onStackEnded() ?: super.onBackPressed()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        fragmentNavigationController.getPeekFragment()?.onActivityResult(requestCode, resultCode, data)
    }
}