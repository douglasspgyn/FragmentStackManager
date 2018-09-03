package douglasspgyn.com.github.fragmentstackmanager.controller

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import douglasspgyn.com.github.fragmentstackmanager.`interface`.FragmentNavigation
import java.util.*

/**
 * Created by Douglas on 3/9/18.
 */

/**
 * Here control the Stack of Fragments of each Root
 */
internal class FragmentNavigationController(private val fragmentNavigation: FragmentNavigation,
                                            private val fragmentManager: FragmentManager,
                                            private val container: Int) {

    private val baseTag: String = "douglasspgyn.com.github.fragmentstackmanager"
    private val fragmentStack: HashMap<Int, Stack<Fragment>> = hashMapOf()
    private var currentRootFragment: Int = -1
    private var previousFragment: Fragment? = null

    /**
     * Called when select a position on the Bottom Navigation
     *
     * @param entry The position on the Bottom Navigation
     */
    fun updateRootFragment(entry: Int) {
        if (currentRootFragment == entry) {
            clearStack()
        }

        currentRootFragment = entry
        updateView()
    }

    /**
     * Add a Fragment to the Stack and, if the Stack doesn't exist, create one
     * Each Root have one Stack
     *
     * @param fragment The Fragment that gonna be added to the stack
     */
    fun pushFragment(fragment: Fragment) {
        if (rootFragmentAlreadyExist(currentRootFragment)) {
            fragmentStack[currentRootFragment]?.push(fragment)
        } else {
            val stack: Stack<Fragment> = Stack()
            stack.push(fragment)

            fragmentStack[currentRootFragment] = stack
        }

        updateView()
    }

    /**
     * Remove the Peek from the Stack and his Tag
     */
    fun popFragment() {
        fragmentManager.findFragmentByTag(getTag())?.let {
            fragmentManager.beginTransaction().remove(it).commit()
        }

        fragmentStack[currentRootFragment]?.pop()

        updateView()
    }

    /**
     * Change the fragment on the view
     * If the fragment was already created get the existing instance, otherwise, get the Root or the Peek of the Stack
     */
    private fun updateView() {
        previousFragment?.let {
            fragmentManager.beginTransaction().hide(it).commit()
        }

        previousFragment = fragmentManager.findFragmentByTag(getTag())?.let {
            fragmentManager.beginTransaction().show(it).commit()
            it
        } ?: let {
            if (isRootFragment()) {
                fragmentNavigation.getRootFragment(currentRootFragment)
            } else {
                getPeekFragment()
            }?.let { fragment ->
                fragmentManager.beginTransaction().add(container, fragment, getTag()).commit()
                fragment
            }
        }

        fragmentNavigation.fragmentChanged()
    }

    /**
     * Check if the current Fragment is the Root (one of the positions on Bottom Navigation)
     */
    fun isRootFragment(): Boolean = getCurrentStack() == null || getCurrentStack()!!.size == 0

    /**
     * Get the Stack of the current Root
     */
    private fun getCurrentStack(): Stack<Fragment>? = fragmentStack[currentRootFragment]

    /**
     * Get the Peek of current Stack
     */
    fun getPeekFragment(): Fragment? = getCurrentStack()?.peek()

    /**
     * Remove all Fragments and Tags from the current Root
     */
    private fun clearStack() {
        fragmentStack[currentRootFragment]?.let { fragmentStack ->
            fragmentStack.forEachIndexed { index, _ ->
                fragmentManager.findFragmentByTag(getTag(index + 1))?.let { fragment ->
                    fragmentManager.beginTransaction().remove(fragment).commit()
                }
            }
        }

        fragmentStack[currentRootFragment]?.clear()
    }

    /**
     * Check if the Stack for the current Root was already created
     */
    private fun rootFragmentAlreadyExist(entry: Int): Boolean = fragmentStack.contains(entry)

    /**
     * Get the Tag for a Fragment
     */
    private fun getTag(): String {
        var pos = 0

        getCurrentStack()?.let {
            pos = it.size
        }

        return getTag(pos)
    }

    /**
     * Get the Tag for a Fragment
     *
     * @param pos The position on the stack
     */
    private fun getTag(pos: Int): String = "$baseTag-$currentRootFragment-$pos"
}