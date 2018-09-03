package douglasspgyn.com.github.fragmentstackmanager.exception

/**
 * Created by Douglas on 3/9/18.
 */

/**
 * Exception generated when the parent activity don't extend the BaseStackActivity and try to use some reserved function
 */
class InvalidStackActivityException : RuntimeException("The Activity that owns the Fragments needs to extend BaseStackActivity")