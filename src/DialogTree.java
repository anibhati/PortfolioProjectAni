import java.util.List;

/**
 * Enhanced interface for the DialogTree component. Provides additional methods
 * for navigating and modifying a DialogTree that are built on top of the kernel
 * methods.
 *
 * @author Aniruddha Singh Bhati
 */
public interface DialogTree extends DialogTreeKernel {

    /**
     * Resets the cursor back to the root node.
     *
     * @updates this.cursor
     * @ensures this.cursor = this.root
     */
    void reset();

    /**
     * Returns true if the current node has no children, meaning the
     * conversation has reached an endpoint.
     *
     * @return true if there are no responses available, false otherwise
     * @ensures isLeaf = (this.numberOfResponses() = 0)
     */
    boolean isLeaf();

    /**
     * Returns a list of all response dialogue strings available at the current
     * node without moving the cursor.
     *
     * @return list of dialogue strings for each child of the current node
     * @ensures |getAvailableResponses| = this.numberOfResponses()
     */
    List<String> getAvailableResponses();

    /**
     * Replaces the dialogue text at the current node with the given string.
     *
     * @param newDialogue
     *            the new dialogue text to set
     * @updates this.cursor.dialogue
     * @requires newDialogue /= null
     * @ensures this.cursor.dialogue = newDialogue
     */
    void editCurrentDialogue(String newDialogue);

    /**
     * Removes the response at the given index from the current node.
     *
     * @param choice
     *            the index of the response to remove
     * @updates this.cursor.children
     * @requires 0 <= choice < this.numberOfResponses()
     * @ensures |this.cursor.children| = |#this.cursor.children| - 1
     */
    void removeResponse(int choice);

    /**
     * Returns the depth of the current node, where the root has depth 0.
     *
     * @return number of edges from the root to the current node
     * @ensures depthOfCursor >= 0
     */
    int depthOfCursor();

    /**
     * Returns true if the cursor is at the root node.
     *
     * @return true if cursor is at root, false otherwise
     * @ensures isAtRoot = (this.cursor = this.root)
     */
    boolean isAtRoot();

}
