package douglasspgyn.com.github.fragmentstackmanager.`interface`

import android.support.v4.app.Fragment

/**
 * Created by Douglas on 3/9/18.
 */

/**
 * Fragment can reach the Activity to push and pop a Fragment
 * Alert the View that the Fragment change
 * Request the Fragment when change the Root
 */
internal interface FragmentNavigation {
    fun pushFragment(fragment: Fragment)
    fun popFragment()
    fun fragmentChanged()
    fun getRootFragment(position: Int): Fragment
}