import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for the DialogTree component. Implements all secondary
 * methods using only kernel methods. This class has no instance fields —
 * all state is derived entirely from kernel calls.
 *
 * @author Aniruddha Singh Bhati
 */
public abstract class DialogTreeSecondary implements DialogTree {

    /**
     * Prime multiplier used in hashCode computation.
     */
    private static final int HASH_MULTIPLIER = 31;

    /*
     * Secondary methods implemented using only kernel methods.
     * No instance fields are used.
     */

    @Override
    public void reset() {
        /*
         * Walk up to the root using moveToParent(). isAtRoot() tells us when
         * to stop. No precondition needed — isAtRoot() is always callable.
         */
        while (!this.isAtRoot()) {
            this.moveToParent();
        }
    }

    @Override
    public boolean isLeaf() {
        /*
         * A node is a leaf if it has no children. numberOfResponses() has no
         * precondition so it is safe to call directly.
         */
        return this.numberOfResponses() == 0;
    }

    @Override
    public List<String> getAvailableResponses() {
        /*
         * For each child index, move down, record the dialogue, then move back
         * up. The cursor returns to its original position after the loop.
         */
        int n = this.numberOfResponses();
        List<String> responses = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            assert 0 <= i && i < this.numberOfResponses() : "Violation of: 0 <= i < this.numberOfResponses()";
            this.moveToResponse(i);
            responses.add(this.getCurrentDialogue());
            this.moveToParent();
        }

        return responses;
    }

    @Override
    public void editCurrentDialogue(String newDialogue) {
        assert newDialogue != null : "Violation of: newDialogue /= null";

        /*
         * Delegate directly to the kernel method setCurrentDialogue, which
         * was added to the kernel specifically to support this operation.
         */
        this.setCurrentDialogue(newDialogue);
    }

    @Override
    public void removeResponse(int choice) {
        assert 0 <= choice && choice < this
                .numberOfResponses() : "Violation of: 0 <= choice < this.numberOfResponses()";

        /*
         * Delegate directly to the kernel method removeResponseAt, which was
         * added to the kernel specifically to support this operation.
         */
        this.removeResponseAt(choice);
    }

    @Override
    public int depthOfCursor() {
        /*
         * Walk up to the root counting steps, then walk back down to restore
         * the cursor. We record the indices taken while going up so we can
         * retrace them in reverse order.
         */
        List<Integer> indices = new ArrayList<>();

        while (!this.isAtRoot()) {
            indices.add(this.indexInParent());
            this.moveToParent();
        }

        // Restore cursor to original position
        for (int i = indices.size() - 1; i >= 0; i--) {
            int idx = indices.get(i);
            assert 0 <= idx && idx < this.numberOfResponses() : "Violation of: 0 <= idx < this.numberOfResponses()";
            this.moveToResponse(idx);
        }

        return indices.size();
    }

    /*
     * Object methods implemented using only kernel methods.
     */

    /**
     * Returns a string representation of the dialog tree rooted at the current
     * cursor position. Each node is indented by its depth relative to the
     * starting node and shows its dialogue text. The cursor is restored to its
     * original position after the call.
     *
     * @return string representation of this dialog tree from the cursor
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.toStringHelper(sb, 0);
        return sb.toString();
    }

    /**
     * Recursive helper for toString. A separate method is required because
     * toString cannot call itself with parameters; this helper carries the
     * StringBuilder and depth across recursive calls.
     *
     * @param sb
     *            the StringBuilder being built
     * @param depth
     *            depth of the current node relative to the toString root
     */
    private void toStringHelper(StringBuilder sb, int depth) {
        // Indent based on depth
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }

        if (depth == 0) {
            sb.append("[cursor] ");
        } else {
            sb.append("[" + depth + "] ");
        }
        sb.append(this.getCurrentDialogue()).append("\n");

        int n = this.numberOfResponses();
        for (int i = 0; i < n; i++) {
            assert 0 <= i && i < this.numberOfResponses() : "Violation of: 0 <= i < this.numberOfResponses()";
            this.moveToResponse(i);
            this.toStringHelper(sb, depth + 1);
            this.moveToParent();
        }
    }

    /**
     * Returns true if {@code obj} is a DialogTree with the same structure and
     * dialogue strings as this one, compared from both cursors. Two trees are
     * equal if their current dialogue matches, they have the same number of
     * responses, and each child subtree is recursively equal.
     *
     * The cursor of this tree is restored to its original position after the
     * call. The cursor of {@code obj} is also restored.
     *
     * @param obj
     *            the object to compare with
     * @return true if obj is a structurally equal DialogTree
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DialogTree)) {
            return false;
        }
        DialogTree other = (DialogTree) obj;
        return this.equalsHelper(other);
    }

    /**
     * Recursive helper for equals. A separate method is required because
     * equals takes an Object parameter and cannot recurse directly with a
     * DialogTree; this helper carries the correctly typed reference across
     * recursive calls. Both cursors are restored to their original positions
     * after the call.
     *
     * @param other
     *            the other DialogTree to compare
     * @return true if both subtrees are structurally identical
     */
    private boolean equalsHelper(DialogTree other) {
        // Dialogues at current positions must match
        if (!this.getCurrentDialogue().equals(other.getCurrentDialogue())) {
            return false;
        }

        int n = this.numberOfResponses();
        if (n != other.numberOfResponses()) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            assert 0 <= i && i < this.numberOfResponses() : "Violation of: 0 <= i < this.numberOfResponses()";
            assert 0 <= i && i < other.numberOfResponses() : "Violation of: 0 <= i < other.numberOfResponses()";

            this.moveToResponse(i);
            other.moveToResponse(i);

            boolean childEqual = this.equalsHelper(other);

            // Restore both cursors to this level
            this.moveToParent();
            other.moveToParent();

            if (!childEqual) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns a hash code consistent with equals, based on the current node's
     * dialogue and number of responses.
     *
     * @return hash code for this dialog tree node
     */
    @Override
    public int hashCode() {
        int result = this.getCurrentDialogue().hashCode();
        result = HASH_MULTIPLIER * result + this.numberOfResponses();
        return result;
    }

}