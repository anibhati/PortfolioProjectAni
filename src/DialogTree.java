import java.util.ArrayList;

/**
 * Proof-of-concept for the DialogTree component.
 *
 * A DialogTree models a branched conversation structure where each node holds a
 * line of dialogue lists possible child notes that contain responses to that
 * node. The "cursor" points to which node you are at in the specific
 * conversation.
 */

public class DialogTree {
    /**
     * Represents a node in the dialog tree. Each node stores the dialogue text
     * spoken in the conversation and a list of child nodes representing the
     * possible responses that could follow.
     */
    private static class Node {
        String dialogue;
        ArrayList<Node> children;

        Node(String dialogue) {
            this.dialogue = dialogue;
            this.children = new ArrayList<>();
        }
    }

    /**
     * Starting point of the conversation.
     */
    private Node root;

    /**
     * Tracks the current position of the tree in the conversation.
     */
    private Node cursor;

    /**
     * Creates a new DialogTree with the opening line of dialogue.
     *
     * @param opening
     *            the text displayed at the start of the conversation
     */
    public DialogTree(String opening) {
        this.root = new Node(opening);
        this.cursor = this.root;
    }

    //Kernel Methods

    /**
     * Adds a new response option to the current node by creating a child node
     * with the given dialogue text.
     *
     * @param dialogue
     *            the text of the new response to add
     */
    public final void addResponse(String dialogue) {
        this.cursor.children.add(new Node(dialogue));
    }

    /**
     * Moves the cursor to the child node at the given index.
     *
     * @param choice
     *            the index of the response to move to
     */
    public final void moveToResponse(int choice) {
        this.cursor = this.cursor.children.get(choice);
    }

    /**
     * Returns the dialogue text at the current cursor position.
     *
     * @return the dialogue string of the current node
     */
    public final String getCurrentDialogue() {
        return this.cursor.dialogue;
    }

    /**
     * Returns the number of responses available at the current node.
     *
     * @return the number of children of the current node
     */
    public final int numberOfResponses() {
        return this.cursor.children.size();
    }

    // Secondary methods

    /**
     * Resets the cursor back to the root node so the conversation can be
     * replayed from the beginning.
     */
    public final void reset() {
        this.cursor = this.root;
    }

    /**
     * Returns true if the current node has no children, meaning the
     * conversation has reached the end.
     *
     * @return true if the current node is a leaf, false otherwise
     */
    public final boolean isLeaf() {
        return this.numberOfResponses() == 0;
    }

    /**
     * Demonstrates the DialogTree component by building a short conversation
     * and navigating through it.
     *
     * @param args
     *            command line arguments, not used
     */
    public static void main(String[] args) {
        DialogTree tree = new DialogTree(
                "Server: Hello sir, what's your name?");

        // Build the conversation tree
        tree.addResponse("Me: My name is Ani.");
        tree.addResponse("Me: My name is Aniruddha Sinh Bhat.");

        tree.moveToResponse(0);
        tree.addResponse("Server: What would you like to eat today?");

        tree.moveToResponse(0);
        tree.addResponse("Me: A burrito.");
        tree.addResponse("Me: I want a taco.");

        tree.moveToResponse(0);
        tree.addResponse("Server: Great choice! Coming right up.");

        // Reset back to root before the demo
        tree.reset();

        // Walk through the conversation
        System.out.println(tree.getCurrentDialogue());
        System.out.println("Options: " + tree.numberOfResponses());

        tree.moveToResponse(0);
        System.out.println(tree.getCurrentDialogue());

        tree.moveToResponse(0);
        System.out.println(tree.getCurrentDialogue());
        System.out.println("Options: " + tree.numberOfResponses());

        tree.moveToResponse(0);
        System.out.println(tree.getCurrentDialogue());

        tree.moveToResponse(0);
        System.out.println(tree.getCurrentDialogue());
        System.out.println("Is end of conversation: " + tree.isLeaf());

        tree.reset();
        System.out.println("Back to start: " + tree.getCurrentDialogue());
    }
}
