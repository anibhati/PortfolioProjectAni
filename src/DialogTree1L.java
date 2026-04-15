import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

/**
 * Kernel implementation of {@code DialogTree} using a linked structure of
 * nodes.
 *
 * <p>
 * <b>Representation Choice:</b> I used a custom Node class because it mirrors
 * the recursive nature of a tree. Each node keeps track of its own dialogue and
 * a list of branches (children). This makes the kernel methods like
 * {@code addResponse} and {@code numberOfResponses} very efficient (O(1)) since
 * they just interact with the cursor's internal list.
 * </p>
 *
 * <p>
 * <b>Convention (Representation Invariant):</b>
 * </p>
 * <ul>
 * <li>{@code root != null}</li>
 * <li>{@code cursor != null}</li>
 * <li>{@code cursor} is reachable from {@code root}</li>
 * <li>{@code dialogue} in any Node is never null</li>
 * <li>{@code children} list in any Node is never null</li>
 * <li>There are no cycles in the structure</li>
 * <li>{@code path} correctly reflects the sequence of indices to get from
 * {@code root} to {@code cursor}</li>
 * </ul>
 *
 * <p>
 * <b>Correspondence (Abstraction Function):</b>
 * </p>
 * <ul>
 * <li>this = [the tree rooted at this.root, with the current position defined
 * by this.cursor and tracked by this.path]</li>
 * <li>the dialogue at a node = node.dialogue</li>
 * <li>the available responses = node.children</li>
 * </ul>
 *
 * @author Aniruddha Singh Bhati
 */
public class DialogTree1L extends DialogTreeSecondary {

    /**
     * Inner class representing a single node in the dialogue tree.
     */
    private static final class Node {
        /**
         * The dialogue text for this node.
         */
        private String dialogue;

        /**
         * The list of child nodes (responses).
         */
        private List<Node> children;

        /**
         * Constructor for a new Node. @param dialogue the text for this node
         */
        private Node(String dialogue) {
            this.dialogue = dialogue;
            this.children = new ArrayList<>();
        }
    }

    /**
     * The root of the entire dialogue tree.
     */
    private Node root;

    /**
     * The current position in the tree.
     */
    private Node cursor;

    /**
     * Creates the initial representation of an empty tree.
     */
    private void createNewRep() {
        this.root = new Node("");
        this.cursor = this.root;
        this.path = new ArrayList<>();
    }

    /**
     * Default constructor.
     */
    public DialogTree1L() {
        this.createNewRep();
    }

    /**
     * Constructor with an initial opening line. @param opening the dialogue for
     * the root node
     */
    public DialogTree1L(String opening) {
        assert opening != null : "Violation of: opening is not null";
        this.createNewRep();
        this.root.dialogue = opening;
    }

    @Override
    public void addResponse(String dialogue) {
        assert dialogue != null : "Violation of: dialogue is not null";

        Node newNode = new Node(dialogue);
        this.cursor.children.add(newNode);
    }

    @Override
    public void moveToResponse(int choice) {
        assert 0 <= choice && choice < this.cursor.children
                .size() : "Violation of: 0 <= choice < numberOfResponses()";

        this.cursor = this.cursor.children.get(choice);
        this.path.add(choice);
    }

    @Override
    public String getCurrentDialogue() {
        return this.cursor.dialogue;
    }

    @Override
    public int numberOfResponses() {
        return this.cursor.children.size();
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public DialogTree newInstance() {
        return new DialogTree1L();
    }

    @Override
    public void transferFrom(DialogTree source) {
        assert source != null : "Violation of: source is not null";
        assert source instanceof DialogTree1L : "Violation of: source is DialogTree1L";

        DialogTree1L localSource = (DialogTree1L) source;

        this.root = localSource.root;
        this.cursor = localSource.cursor;
        this.path = localSource.path;

        localSource.createNewRep();
    }
}
