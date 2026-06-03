# Dialogue Tree Component

> A custom Java software component built as part of the OSU Software Sequence portfolio project. This component provides a framework for building branching dialogue trees — useful for games, interactive fiction, and NPC conversation systems.

---

## What Is This?

This repo contains a from-scratch implementation of a **Dialogue Tree** component, designed and built following the OSU software discipline. A dialogue tree lets you model branching conversations where each node holds a line of dialogue and a set of possible responses, each leading to a new node. It's the backbone of NPC interactions in games and choose-your-own-adventure style narratives.

---

## Getting Started

### Step 1: Create a Repo From This Template

Click the **Use this template** button at the top-right of this page, or use [this direct link](https://github.com/new?template_name=portfolio-project&template_owner=jrg94). Choose **Create a new repository**, name it after your component, and make sure it's set to **public** (required for Carmen submissions). Then clone it locally — [GitHub Desktop](https://desktop.github.com/) works great and lets you open the repo directly in VSCode from the `Repository` menu.

### Step 2: Install Recommended Plugins

Open the project in VSCode and click **Install All** when prompted about recommended extensions. If you missed the prompt, press `CTRL+SHIFT+P` and search for **"Show Recommended Extensions"**, then install everything listed.

### Step 3: Install the Latest JDK

If you don't have a JDK on your system, VSCode may prompt you to install one. Skip the Red Hat option (no Mac support, requires account registration) and install the latest JDK directly from [Oracle's site](https://www.oracle.com/java/technologies/downloads/).

### Step 4: Add Key Libraries

**Components JAR** — Download `components.jar` from [here](https://cse22x1.engineering.osu.edu/common/components.jar) and drop it into the `lib/` folder. Git ignores this folder by default, so no need to commit it.

**JUnit** — Click the beaker icon in the VSCode sidebar (below the Extensions icon). If it's not visible, try creating a Java file in `src/` first. Then click **Enable Java Tests → JUnit**. The two JUnit libraries will appear in `lib/` automatically.

> **Note:** If you're used to the monorepo template where libraries were already included — that's intentional here. Committing binaries is generally bad practice, and this setup shows you how to manage dependencies from scratch. If you get stuck, copy the `lib/` folder from the monorepo template.

---

## Project Structure

```
.
├── src/                  # Component source files
├── test/                 # JUnit test files
├── lib/                  # Local dependencies (gitignored)
├── doc/                  # Assignment documentation and specs
└── README.md
```

---

## Assignment Checkpoints

All assignment files and specs live in the [`doc/`](./doc) directory. There are deadlines for each step on Carmen, but you're free to work ahead. Each step has its own branch in this repo:

| Step | Branch | Description |
|------|--------|-------------|
| 1 | [`Component-Interface`](../../tree/Component-Interface) | Define the abstract model and kernel methods |
| 2 | [`proof-of-concept`](../../tree/proof-of-concept) | Sketch a rough implementation to validate your design |
| 3 | [`abstract-class`](../../tree/abstract-class) | Implement secondary methods on top of the kernel |
| 4 | [`kernel-implementation`](../../tree/kernel-implementation) | Build the concrete data representation |
| 5 | [`finishing-touches`](../../tree/finishing-touches) | Polish, testing, and final cleanup |

---

## Usage Example

```java
// Build a simple dialogue tree
DialogueTree tree = new DialogueTree1();
tree.addNode("start", "Hello, traveler. What brings you here?");
tree.addChoice("start", "I seek the ancient artifact.", "quest");
tree.addChoice("start", "Just passing through.", "goodbye");

tree.addNode("quest", "Brave soul! The artifact lies in the eastern dungeon.");
tree.addNode("goodbye", "Safe travels then.");

// Traverse
String current = "start";
System.out.println(tree.getDialogue(current));
// → "Hello, traveler. What brings you here?"
```

> **Note:** API details may vary depending on your final kernel design. Update this example once your interface is finalized.

---

## Resources

- [OSU Components Javadoc](https://cse22x1.engineering.osu.edu/common/doc/)
- [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Project Template](https://github.com/jrg94/portfolio-project)
