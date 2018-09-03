package douglasspgyn.com.github.fragmentstackmanager.extension

import android.content.Context
import android.support.v4.app.Fragment
import douglasspgyn.com.github.fragmentstackmanager.base.BaseStackActivity
import douglasspgyn.com.github.fragmentstackmanager.exception.InvalidStackActivityException

/**
 * Created by Douglas on 3/9/18.
 */

/**
 * Kotlin Extension to facilitate the Push Fragment from somewhere you only have Context, like an Adapter
 */
fun Context.pushFragment(fragment: Fragment) {
    when (this) {
        is BaseStackActivity -> pushFragment(fragment)
        else -> throw InvalidStackActivityException()
    }
}

/**
 * Kotlin Extension to facilitate the Pop Fragment from somewhere you only have Context, like an Adapter
 */
fun Context.popFragment(fragment: Fragment) {
    when (this) {
        is BaseStackActivity -> popFragment()
        else -> throw InvalidStackActivityException()
    }
}