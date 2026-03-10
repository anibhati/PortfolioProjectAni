import components.standard.Standard;

/**
 * Kernel interface for the DialogTree component.
 *
 * A DialogTree models a branched conversation where each node holds a line of
 * dialogue and a list of possible responses. A cursor tracks the current
 * position in the conversation.
 *
 * @author Aniruddha Singh Bhati
 * @mathmodel a rooted tree (root, cursor) where root is the starting node,
 *            cursor is the current position, and each node has a String
 *            dialogue and an ordered list of child nodes
 * @initially <pre>
 * ():
 *   ensures
 *     this.root.dialogue = "" and
 *     this.root.children = [] and
 *     this.cursor = this.root
 * (String opening):
 *   requires
 *     opening /= null
 *   ensures
 *     this.root.dialogue = opening and
 *     this.root.children = [] and
 *     this.cursor = this.root
 * </pre>
 */
public interface DialogTreeKernel extends Standard<DialogTree> {

    /**
     * Adds a new response to the current node with the given dialogue text.
     *
     * @param dialogue
     *            the text of the response to add
     * @updates this.cursor.children
     * @requires dialogue /= null
     * @ensures this.cursor.children = #this.cursor.children * [dialogue]
     */
    void addResponse(String dialogue);

    /**
     * Moves the cursor to the response at the given index.
     *
     * @param choice
     *            the index of the response to move to
     * @updates this.cursor
     * @requires 0 <= choice < this.numberOfResponses()
     * @ensures this.cursor = #this.cursor.children[choice]
     */
    void moveToResponse(int choice);

    /**
     * Returns the dialogue text at the current cursor position.
     *
     * @return the dialogue string of the current node
     * @ensures getCurrentDialogue = this.cursor.dialogue
     */
    String getCurrentDialogue();

    /**
     * Returns the number of responses available at the current node.
     *
     * @return the number of children of the current node
     * @ensures numberOfResponses = |this.cursor.children|
     */
    int numberOfResponses();

}
