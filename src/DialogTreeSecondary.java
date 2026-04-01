import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for the DialogTree component. Implements all secondary methods
 * using only kernel methods.
 *
 * @author Aniruddha Singh Bhati
 */
public abstract class DialogTreeSecondary implements DialogTree {

    /**
     * Tracks the path of child indices taken from the root to the cursor.
     * Updated whenever moveToResponse is called. Not part of the tree
     * representation used to track cursor position.
     */
    protected List<Integer> path = new ArrayList<>();

    /**
     * Navigates the cursor to the node described by the given path, starting
     * from the root. Uses Standard methods newInstance and transferFrom to
     * reset to root first.
     *
     * @param targetPath
     *            list of child indices from root to target
     */
    private void navigateToPath(List<Integer> targetPath) {
        DialogTree fresh = this.newInstance();
        fresh.transferFrom(this);
        for (int step : targetPath) {
            fresh.moveToResponse(step);
        }
        this.transferFrom(fresh);
        this.path = new ArrayList<>(targetPath);
    }

    @Override
    public void reset() {
        /*
         * Navigate back to the root by renavigating to an empty path, which
         * corresponds to the root node.
         */
        this.navigateToPath(new ArrayList<>());
    }

    @Override
    public boolean isLeaf() {
        return this.numberOfResponses() == 0;
    }

    @Override
    public List<String> getAvailableResponses() {
        int n = this.numberOfResponses();
        List<String> responses = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            this.moveToResponse(i);
            responses.add(this.getCurrentDialogue());

            // Go back to the parent node
            List<Integer> parentPath = new ArrayList<>(
                    this.path.subList(0, this.path.size() - 1));
            this.path.add(i);
            this.navigateToPath(parentPath);
        }

        return responses;
    }

    @Override
    public void editCurrentDialogue(String newDialogue) {
        assert newDialogue != null : "Violation of: newDialogue /= null";

        /*
         * This method cannot be fully implemented using only kernel methods.
         * The kernel has no setCurrentDialogue method, so there is no way to
         * change the dialogue of a node without rebuilding the entire subtree.
         * A setCurrentDialogue(String) method should be added to the kernel.
         *
         * For now this throws UnsupportedOperationException to signal the gap.
         */
        throw new UnsupportedOperationException(
                "editCurrentDialogue requires a kernel method "
                        + "setCurrentDialogue(String) which does not exist yet.");
    }

    @Override
    public void removeResponse(int choice) {
        assert 0 <= choice && choice < this
                .numberOfResponses() : "Violation of: 0 <= choice < this.numberOfResponses()";

        /*
         * This method cannot be fully implemented using only kernel methods.
         * The kernel has no way to delete a child node. A deleteChild(int) or
         * clearChildren() method should be added to the kernel.
         *
         * For now this throws UnsupportedOperationException to signal the gap.
         */
        throw new UnsupportedOperationException(
                "removeResponse requires a kernel method deleteChild(int) "
                        + "which does not exist yet.");
    }

    @Override
    public int depthOfCursor() {
        /*
         * The path list holds one entry per edge from root to cursor, so its
         * size is the depth.
         */
        return this.path.size();
    }

    @Override
    public boolean isAtRoot() {
        return this.path.isEmpty();
    }

    /*
     * Common Object methods implemented using only kernel methods.
     */

    /**
     * Returns a string representation of the dialog tree from the current
     * cursor position. Each node is indented based on its depth and shows its
     * dialogue text.
     *
     * @return string representation of this dialog tree
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.toStringHelper(sb, 0);
        return sb.toString();
    }

    /**
     * Helper for toString. Recursively visits each child and appends to sb.
     *
     * @param sb
     *            the StringBuilder being built
     * @param depth
     *            current depth from the starting node
     */
    private void toStringHelper(StringBuilder sb, int depth) {
        // Add indentation
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
            this.moveToResponse(i);
            this.path.add(i);

            this.toStringHelper(sb, depth + 1);

            // Go back to this node
            List<Integer> here = new ArrayList<>(
                    this.path.subList(0, this.path.size() - 1));
            this.path.remove(this.path.size() - 1);
            this.navigateToPath(here);
        }
    }

    /**
     * Returns true if {@code obj} is a DialogTree with the same structure and
     * dialogue strings as this one, compared from both cursors. Two trees are
     * equal if their current dialogue matches, they have the same number of
     * responses, and each child subtree is equal recursively.
     *
     * @param obj
     *            the object to compare with
     * @return true if obj is an equal DialogTree
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
     * Recursive helper for equals. Compares this tree and other from their
     * current cursor positions using only kernel methods.
     *
     * @param other
     *            the other DialogTree to compare
     * @return true if both subtrees are identical
     */
    private boolean equalsHelper(DialogTree other) {
        // Check dialogue at current node
        if (!this.getCurrentDialogue().equals(other.getCurrentDialogue())) {
            return false;
        }

        int n = this.numberOfResponses();
        if (n != other.numberOfResponses()) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            this.moveToResponse(i);
            this.path.add(i);
            other.moveToResponse(i);

            boolean childEqual = this.equalsHelper(other);

            // Return this cursor to parent
            List<Integer> parentPath = new ArrayList<>(
                    this.path.subList(0, this.path.size() - 1));
            this.path.remove(this.path.size() - 1);
            this.navigateToPath(parentPath);

            // Return other cursor to parent
            other.reset();
            for (int step : parentPath) {
                other.moveToResponse(step);
            }

            if (!childEqual) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns a hash code consistent with equals, derived from the current
     * node's dialogue and number of responses.
     *
     * @return hash code for this dialog tree
     */
    @Override
    public int hashCode() {
        int result = this.getCurrentDialogue().hashCode();
        result = 31 * result + this.numberOfResponses();
        return result;
    }
}
