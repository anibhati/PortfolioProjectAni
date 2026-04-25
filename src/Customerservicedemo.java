/**
 * Demo 2: Customer Service Chatbot
 *
 * This program demonstrates how a DialogTree can be used to power a simple
 * customer service decision tree. The user is guided through a series of
 * options to resolve their issue, and the program simulates navigating through
 * the tree automatically.
 *
 * @author Aniruddha Singh Bhati
 */
public final class CustomerServiceDemo {

    /**
     * Private constructor to prevent instantiation.
     */
    private CustomerServiceDemo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            command-line arguments (unused)
     */
    public static void main(String[] args) {
        // Build the customer service tree
        DialogTree tree = new DialogTree1L(
                "Bot: Hello! Welcome to TechSupport. What issue are you having?");

        // Top-level options
        tree.addResponse("User: My internet is not working.");
        tree.addResponse("User: My device won't turn on.");
        tree.addResponse("User: I have a billing question.");

        // --- Internet branch ---
        tree.moveToResponse(0);
        tree.addResponse("User: My router lights are all red.");
        tree.addResponse("User: The connection keeps dropping.");

        // Router red lights
        tree.moveToResponse(0);
        tree.addResponse("User: I already tried that, still red.");
        tree.addResponse("User: That worked, thanks!");
        tree.moveToParent();

        // Router red: tried restart, still red
        tree.moveToResponse(0);
        tree.addResponse("User: Okay, I'll do that.");
        tree.moveToParent();
        // Router red: it worked
        tree.moveToResponse(1);
        tree.addResponse("User: Great, goodbye!");
        tree.moveToParent();

        tree.moveToParent(); // back to router red lights

        // Connection keeps dropping
        tree.moveToResponse(1);
        tree.addResponse("User: Yes, I updated already.");
        tree.addResponse("User: I haven't updated, let me try.");
        tree.moveToParent();

        tree.moveToParent(); // back to internet branch

        // --- Device won't turn on ---
        tree.moveToParent(); // back to root
        tree.moveToResponse(1);
        tree.addResponse("User: It's plugged in but nothing happens.");
        tree.addResponse("User: The battery is dead.");
        tree.moveToParent();

        // --- Billing question ---
        tree.moveToResponse(2);
        tree.addResponse("User: I was charged twice this month.");
        tree.addResponse("User: I want to cancel my subscription.");
        tree.moveToParent();

        // Reset to root for the demo simulation
        tree.reset();

        // --- Simulate a customer service session ---
        System.out.println("=== Customer Service Chatbot Demo ===");
        System.out.println();

        printNode(tree);

        // User chooses: internet not working
        System.out.println("\n> User selects: My internet is not working.");
        tree.moveToResponse(0);
        printNode(tree);

        // User chooses: router lights are red
        System.out.println("\n> User selects: Router lights are all red.");
        tree.moveToResponse(0);
        System.out.println("Bot: Have you tried restarting your router?");
        printNode(tree);

        // User tried it and it worked
        System.out.println("\n> User selects: That worked, thanks!");
        tree.moveToResponse(1);
        System.out.println(tree.getCurrentDialogue());
        System.out.println("Bot: Great! Glad we could help. Have a nice day!");

        // Show depth and leaf status
        System.out
                .println("\nCurrent depth from root: " + tree.depthOfCursor());
        System.out.println("Is leaf node: " + tree.isLeaf());

        // Edit a node to show editCurrentDialogue
        tree.reset();
        tree.editCurrentDialogue(
                "Bot: Hello! Welcome to TechSupport v2. How can I assist you today?");
        System.out.println("\n> Root updated via editCurrentDialogue:");
        System.out.println(tree.getCurrentDialogue());

        // Show available responses at root
        System.out.println("\nAvailable top-level options:");
        java.util.List<String> options = tree.getAvailableResponses();
        for (int i = 0; i < options.size(); i++) {
            System.out.println("  [" + i + "] " + options.get(i));
        }

        System.out.println("\n=== Demo complete ===");
    }

    /**
     * Prints the current node's dialogue and available responses.
     *
     * @param tree
     *            the DialogTree to print from
     */
    private static void printNode(DialogTree tree) {
        System.out.println(tree.getCurrentDialogue());
        if (!tree.isLeaf()) {
            System.out.println("Options:");
            java.util.List<String> responses = tree.getAvailableResponses();
            for (int i = 0; i < responses.size(); i++) {
                System.out.println("  [" + i + "] " + responses.get(i));
            }
        }
    }
}
