package douglasspgyn.com.github.fragmentstackmanager.controller

/**
 * Created by Douglas on 3/9/18.
 */

/**
 * Control the Stack of the Root and the order
 * A Root can be added and reordered on the Stack but only appears once
 *
 * @param defaultEntry The default position on the Bottom Navigation
 */
internal class RootFragmentHistory(private val defaultEntry: Int) {

    private val rootFragmentStack: MutableList<Int> = mutableListOf()

    /**
     * Get the position on the Bottom Navigation and place on the top of the list
     *
     * @param entry The position on the Bottom Navigation
     */
    fun push(entry: Int) {
        if (!alreadyExist(entry)) {
            rootFragmentStack.add(entry)
        } else {
            rearrangeStack(entry)
        }
    }

    /**
     * Remove the Peek of the Stack
     */
    fun pop() {
        if (!isEmpty()) {
            rootFragmentStack.removeAt(rootFragmentStack.size - 1)
        }
    }

    /**
     * Get the current Peek of the Stack and, if don't have any, return the default
     */
    fun peek(): Int = if (!isEmpty()) rootFragmentStack.last() else defaultEntry

    /**
     * Get the size of the Stack
     */
    fun size(): Int = rootFragmentStack.size

    /**
     * Check if the Stack is empty
     */
    private fun isEmpty(): Boolean = rootFragmentStack.isEmpty()

    /**
     * Check if the Root is already on the Stack
     */
    private fun alreadyExist(entry: Int): Boolean = rootFragmentStack.contains(entry)

    /**
     * Rearrange the Stack to the entry stay on the peek
     *
     * @param entry The position on the Bottom Navigation
     */
    private fun rearrangeStack(entry: Int) {
        val position = rootFragmentStack.indexOf(entry)
        rootFragmentStack.removeAt(position)
        rootFragmentStack.add(entry)
    }
}