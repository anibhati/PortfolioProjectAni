/**
 * JUnit test fixture for {@code DialogTree}'s constructor and kernel methods.
 *
 * @author Aniruddha Singh Bhati
 */
public abstract class DialogTreeTest {

    /**
     * Invokes the appropriate {@code DialogTree} constructor and returns the
     * result.
     *
     * @return the new tree
     * @ensures constructorTest = ("", [], cursor = root)
     */
    protected abstract DialogTree constructorTest();

    /**
     * Invokes the appropriate {@code DialogTree} constructor with a string and
     * returns the result.
     *
     * @param opening
     *            the opening dialogue
     * @return the new tree
     * @ensures constructorTest = (opening, [], cursor = root)
     */
    protected abstract DialogTree constructorTest(String opening);

    /**
     * Invokes the appropriate {@code DialogTree} reference constructor and
     * returns the result.
     *
     * @return the new reference tree
     */
    protected abstract DialogTree constructorRef();

    /**
     * Invokes the appropriate {@code DialogTree} reference constructor with a
     * string and returns the result.
     *
     * @param opening
     *            the opening dialogue
     * @return the new reference tree
     */
    protected abstract DialogTree constructorRef(String opening);

    // ------------------------------------------------------------------
    // Constructor tests
    // ------------------------------------------------------------------

    /**
     * Test default constructor: dialogue is empty, no responses, at root.
     */
    @Test
    public void testDefaultConstructor() {
        DialogTree t = this.constructorTest();
        DialogTree tExpected = this.constructorRef();

        assertEquals(tExpected, t);
    }

    /**
     * Test string constructor: root dialogue equals opening string.
     */
    @Test
    public void testStringConstructor() {
        DialogTree t = this.constructorTest("Hello!");
        DialogTree tExpected = this.constructorRef("Hello!");

        assertEquals(tExpected, t);
    }

    // ------------------------------------------------------------------
    // addResponse tests
    // ------------------------------------------------------------------

    /**
     * Test addResponse: add one response to empty node.
     */
    @Test
    public void testAddResponseOne() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Option A");
        tExpected.addResponse("Option A");

        assertEquals(tExpected, t);
    }

    /**
     * Test addResponse: add multiple responses to same node.
     */
    @Test
    public void testAddResponseMultiple() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Option A");
        t.addResponse("Option B");
        tExpected.addResponse("Option A");
        tExpected.addResponse("Option B");

        assertEquals(tExpected, t);
    }

    // ------------------------------------------------------------------
    // numberOfResponses tests
    // ------------------------------------------------------------------

    /**
     * Test numberOfResponses: zero at leaf node.
     */
    @Test
    public void testNumberOfResponsesZero() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        int result = t.numberOfResponses();

        assertEquals(tExpected.numberOfResponses(), result);
        assertEquals(tExpected, t);
    }

    /**
     * Test numberOfResponses: one response.
     */
    @Test
    public void testNumberOfResponsesOne() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("A");
        tExpected.addResponse("A");

        int result = t.numberOfResponses();

        assertEquals(tExpected.numberOfResponses(), result);
        assertEquals(tExpected, t);
    }

    /**
     * Test numberOfResponses: three responses.
     */
    @Test
    public void testNumberOfResponsesThree() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("A");
        t.addResponse("B");
        t.addResponse("C");
        tExpected.addResponse("A");
        tExpected.addResponse("B");
        tExpected.addResponse("C");

        int result = t.numberOfResponses();

        assertEquals(tExpected.numberOfResponses(), result);
        assertEquals(tExpected, t);
    }

    // ------------------------------------------------------------------
    // moveToResponse tests
    // ------------------------------------------------------------------

    /**
     * Test moveToResponse: move to first (only) child.
     */
    @Test
    public void testMoveToResponseFirst() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Child");
        t.moveToResponse(0);

        tExpected.addResponse("Child");
        tExpected.moveToResponse(0);

        assertEquals(tExpected, t);
        assertEquals("Child", t.getCurrentDialogue());
    }

    /**
     * Test moveToResponse: move to second of two children.
     */
    @Test
    public void testMoveToResponseSecond() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Child0");
        t.addResponse("Child1");
        t.moveToResponse(1);

        tExpected.addResponse("Child0");
        tExpected.addResponse("Child1");
        tExpected.moveToResponse(1);

        assertEquals("Child1", t.getCurrentDialogue());
        assertEquals(tExpected, t);
    }

    // ------------------------------------------------------------------
    // moveToParent tests
    // ------------------------------------------------------------------

    /**
     * Test moveToParent: move to parent after moving to child.
     */
    @Test
    public void testMoveToParent() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Child");
        t.moveToResponse(0);
        t.moveToParent();

        tExpected.addResponse("Child");

        assertEquals(tExpected, t);
        assertEquals("Root", t.getCurrentDialogue());
    }

    // ------------------------------------------------------------------
    // isAtRoot tests
    // ------------------------------------------------------------------

    /**
     * Test isAtRoot: true at root.
     */
    @Test
    public void testIsAtRootTrue() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        boolean result = t.isAtRoot();

        assertTrue(result);
        assertEquals(tExpected, t);
    }

    /**
     * Test isAtRoot: false after moving to child.
     */
    @Test
    public void testIsAtRootFalse() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Child");
        t.moveToResponse(0);
        tExpected.addResponse("Child");
        tExpected.moveToResponse(0);

        boolean result = t.isAtRoot();

        assertFalse(result);
        assertEquals(tExpected, t);
    }

    // ------------------------------------------------------------------
    // getCurrentDialogue tests
    // ------------------------------------------------------------------

    /**
     * Test getCurrentDialogue: at root.
     */
    @Test
    public void testGetCurrentDialogueRoot() {
        DialogTree t = this.constructorTest("Hello!");
        DialogTree tExpected = this.constructorRef("Hello!");

        String result = t.getCurrentDialogue();

        assertEquals("Hello!", result);
        assertEquals(tExpected, t);
    }

    /**
     * Test getCurrentDialogue: at child node.
     */
    @Test
    public void testGetCurrentDialogueChild() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Response A");
        t.moveToResponse(0);
        tExpected.addResponse("Response A");
        tExpected.moveToResponse(0);

        String result = t.getCurrentDialogue();

        assertEquals("Response A", result);
        assertEquals(tExpected, t);
    }

    // ------------------------------------------------------------------
    // setCurrentDialogue tests
    // ------------------------------------------------------------------

    /**
     * Test setCurrentDialogue: update root dialogue.
     */
    @Test
    public void testSetCurrentDialogueRoot() {
        DialogTree t = this.constructorTest("Old");
        DialogTree tExpected = this.constructorRef("New");

        t.setCurrentDialogue("New");

        assertEquals(tExpected, t);
    }

    /**
     * Test setCurrentDialogue: update child dialogue.
     */
    @Test
    public void testSetCurrentDialogueChild() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Old Child");
        t.moveToResponse(0);
        t.setCurrentDialogue("New Child");
        t.moveToParent();

        tExpected.addResponse("New Child");

        assertEquals(tExpected, t);
    }

    // ------------------------------------------------------------------
    // removeResponseAt tests
    // ------------------------------------------------------------------

    /**
     * Test removeResponseAt: remove only response.
     */
    @Test
    public void testRemoveResponseAtOnly() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("A");
        t.removeResponseAt(0);

        assertEquals(tExpected, t);
        assertEquals(tExpected.numberOfResponses(), t.numberOfResponses());
    }

    /**
     * Test removeResponseAt: remove first of two responses.
     */
    @Test
    public void testRemoveResponseAtFirst() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("A");
        t.addResponse("B");
        t.removeResponseAt(0);

        tExpected.addResponse("B");

        assertEquals(tExpected, t);
        assertEquals(tExpected.numberOfResponses(), t.numberOfResponses());
    }

    // ------------------------------------------------------------------
    // indexInParent tests
    // ------------------------------------------------------------------

    /**
     * Test indexInParent: first child returns 0.
     */
    @Test
    public void testIndexInParentFirst() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Child0");
        t.addResponse("Child1");
        t.moveToResponse(0);
        tExpected.addResponse("Child0");
        tExpected.addResponse("Child1");
        tExpected.moveToResponse(0);

        int result = t.indexInParent();

        assertEquals(tExpected.indexInParent(), result);
        assertEquals(tExpected, t);
    }

    /**
     * Test indexInParent: second child returns 1.
     */
    @Test
    public void testIndexInParentSecond() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Child0");
        t.addResponse("Child1");
        t.moveToResponse(1);
        tExpected.addResponse("Child0");
        tExpected.addResponse("Child1");
        tExpected.moveToResponse(1);

        int result = t.indexInParent();

        assertEquals(tExpected.indexInParent(), result);
        assertEquals(tExpected, t);
    }

    // ------------------------------------------------------------------
    // Secondary method tests
    // ------------------------------------------------------------------

    /**
     * Test reset: cursor returns to root from child.
     */
    @Test
    public void testReset() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Child");
        t.moveToResponse(0);
        t.reset();

        tExpected.addResponse("Child");

        assertEquals(tExpected, t);
        assertTrue(t.isAtRoot());
    }

    /**
     * Test isLeaf: true for node with no children.
     */
    @Test
    public void testIsLeafTrue() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        boolean result = t.isLeaf();

        assertTrue(result);
        assertEquals(tExpected, t);
    }

    /**
     * Test isLeaf: false for node with one child.
     */
    @Test
    public void testIsLeafFalse() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Child");
        tExpected.addResponse("Child");

        boolean result = t.isLeaf();

        assertFalse(result);
        assertEquals(tExpected, t);
    }

    /**
     * Test getAvailableResponses: returns correct list, cursor unchanged.
     */
    @Test
    public void testGetAvailableResponses() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("A");
        t.addResponse("B");
        tExpected.addResponse("A");
        tExpected.addResponse("B");

        java.util.List<String> responses = t.getAvailableResponses();
        java.util.List<String> responsesExpected = tExpected
                .getAvailableResponses();

        assertEquals(responsesExpected.size(), responses.size());
        assertEquals(responsesExpected.get(0), responses.get(0));
        assertEquals(responsesExpected.get(1), responses.get(1));
        assertEquals(tExpected, t);
    }

    /**
     * Test depthOfCursor: zero at root.
     */
    @Test
    public void testDepthOfCursorRoot() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        int depth = t.depthOfCursor();

        assertEquals(tExpected.depthOfCursor(), depth);
        assertEquals(tExpected, t);
    }

    /**
     * Test depthOfCursor: one after moving to child.
     */
    @Test
    public void testDepthOfCursorOne() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Child");
        t.moveToResponse(0);
        tExpected.addResponse("Child");
        tExpected.moveToResponse(0);

        int depth = t.depthOfCursor();

        assertEquals(tExpected.depthOfCursor(), depth);
        assertEquals(tExpected, t);
    }

    /**
     * Test depthOfCursor: two after moving two levels deep.
     */
    @Test
    public void testDepthOfCursorTwo() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("Child");
        t.moveToResponse(0);
        t.addResponse("Grandchild");
        t.moveToResponse(0);

        tExpected.addResponse("Child");
        tExpected.moveToResponse(0);
        tExpected.addResponse("Grandchild");
        tExpected.moveToResponse(0);

        int depth = t.depthOfCursor();

        assertEquals(tExpected.depthOfCursor(), depth);
        assertEquals(tExpected, t);
    }

    /**
     * Test editCurrentDialogue: same as setCurrentDialogue via secondary.
     */
    @Test
    public void testEditCurrentDialogue() {
        DialogTree t = this.constructorTest("Old");
        DialogTree tExpected = this.constructorRef("New");

        t.editCurrentDialogue("New");

        assertEquals(tExpected, t);
    }

    /**
     * Test removeResponse: remove by index via secondary method.
     */
    @Test
    public void testRemoveResponse() {
        DialogTree t = this.constructorTest("Root");
        DialogTree tExpected = this.constructorRef("Root");

        t.addResponse("A");
        t.addResponse("B");
        t.removeResponse(0);

        tExpected.addResponse("B");

        assertEquals(tExpected, t);
    }
}