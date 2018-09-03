package douglasspgyn.com.github.fragmentstackmanager.`interface`

/**
 * Created by Douglas on 3/9/18.
 */

/**
 * Used to warn the activity that the back reach the end of the stack
 */
class FragmentStackManager {

    interface OnBackPressed {
        fun onStackEnded()
    }
}