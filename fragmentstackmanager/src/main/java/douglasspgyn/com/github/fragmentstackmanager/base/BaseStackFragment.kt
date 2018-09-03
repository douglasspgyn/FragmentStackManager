package douglasspgyn.com.github.fragmentstackmanager.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import douglasspgyn.com.github.fragmentstackmanager.`interface`.FragmentNavigation
import douglasspgyn.com.github.fragmentstackmanager.exception.InvalidStackActivityException

/**
 * Created by Douglas on 3/9/18.
 */

abstract class BaseStackFragment : Fragment() {

    private var title: String = ""
    private lateinit var fragmentNavigation: FragmentNavigation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        context.let {
            if (it is FragmentNavigation) {
                fragmentNavigation = it
            } else {
                throw InvalidStackActivityException()
            }
        }

        toolbarTitle(title)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (hidden) {
            onPause()
        } else {
            onResume()
        }
    }

    override fun onResume() {
        super.onResume()
        toolbarTitle(title)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
    }

    fun toolbarTitle(title: String) {
        this.title = title
        activity.let {
            if (it is BaseStackActivity) {
                it.updateToolbarTitle(this.title)
            } else {
                throw InvalidStackActivityException()
            }
        }
    }

    fun pushFragment(fragment: Fragment) {
        fragmentNavigation.pushFragment(fragment)
    }

    fun popFragment() {
        fragmentNavigation.popFragment()
    }
}