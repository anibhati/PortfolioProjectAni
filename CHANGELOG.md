# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## [Unreleased]

- Disabled an aspect of the coderunner extension
- Fixed broken links
- Disabled AI features globally

# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## 2026.04.23

### Added

- Added moveToParent(), indexInParent(), setCurrentDialogue(String),
  and removeResponseAt(int) to DialogTreeKernel
- Added parent field to Node in DialogTree1L to support upward traversal

### Updated

- Moved isAtRoot() to DialogTreeKernel
- Rewrote DialogTreeSecondary to be fully stateless using only kernel methods
- Implemented editCurrentDialogue and removeResponse using new kernel methods
- Removed path field from DialogTree1L and DialogTreeSecondary

## 2025.04.01

### Added

- Designed abstract class for DialogTree component

### Updated

- Identified missing kernel methods needed for full implementation
  (setCurrentDialogue, deleteChild)

## 2025.03.10

### Added

- Designed kernel and enhanced interfaces for the `DialogTree` component
- Created `DialogTreeKernel.java` with four minimal kernel methods:
  `addResponse`, `moveToResponse`, `getCurrentDialogue`, and `numberOfResponses`
- Created `DialogTree.java` (enhanced interface) with seven secondary methods:
  `reset`, `isLeaf`, `getAvailableResponses`, `editCurrentDialogue`,
  `removeResponse`, `depthOfCursor`, and `isAtRoot`
- Added component hierarchy diagram showing where both interfaces fit in the
  OSU software sequence discipline

### Updated

- Changed kernel method name from `setRootDialogue(Node root)` to
  `addResponse(String dialogue)` — the original design exposed internal `Node`
  types to the client
- Changed `getCurrentResponse()` to `getCurrentDialogue()` to better describe
  that the method returns a `String` of text, not a `Node` object
- Moved `getAvailableResponses()` from the kernel to the enhanced interface,
  since it can be implemented using kernel methods alone
- Renamed `isAChild()` to `isLeaf()` because the original name was misleading;
  `isLeaf` correctly describes a node with no children

## [2024.12.30]

- Added table-based rubrics to all 6 parts of the project
- Updated gitignore to exclude more files
- Fixed image markdown in the interfaces document

## [2024.08.07]

### Added

- Added `/bin` to `.gitignore`, so binaries are no longer committed
- Added the TODO tree extensions to `extensions.json`
- Added the `todo-tree.general.showActivityBarBadge` setting to `settings.json`
- Added the `todo-tree.tree.showCountsInTree` setting to `settings.json`
- Added the VSCode PDF extension to `extensions.json`
- Added `java.debug.settings.vmArgs` setting to enable assertions (i.e., `-ea`)
- Added information about making branches to all parts of the project
- Added information about how to update the CHANGELOG to every part of the
  project
- Added information about how to make a pull request to every part of the
  project

### Changed

- Updated `settings.json` to format document on save using `editor.formatOnSave`
  setting
- Updated `settings.json` to exclude certain files from markdown to PDF
  generation using `markdown-pdf.convertOnSaveExclude` setting
- Updated `settings.json` to use latest `java.cleanup.actions` setting
- Updated `settings.json` to automatically choose line endings using `files.eol`
  setting
- Updated `settings.json` to organize imports automatically on save using the
  `editor.codeActionsOnSave` and `source.organizeImports` settings
- Changed the component brainstorming assignment to ask a few clarifying
  questions
- Changed the component brainstorming example from `Point3D` to `NaturalNumber`
  to avoid the getter/setter trend
- Updated assignment feedback sections to include a link to a survey that
  I'll actually review
- Updated README to include step about using template repo
- Updated part 3 rubric to include a hierarchy diagram
- Updated part 6 rubric to account for overall polish

### Fixed

- Fixed issue where checkstyle paths would not work on MacOS

### Removed

- Removed `java.saveActions.organizeImports` setting from `settings.json`
- Removed references to `Point3D` completely

## [2024.01.07]

### Added

- Added a list of extensions to capture the ideal student experience
- Added PDFs to the `.gitignore`
- Added the OSU checkstyle config file
- Added the OSU formatter config file
- Added a `settings.json` file to customize the student experience
- Created a README at the root to explain how to use the template repo
- Created initial drafts of the six portfolio assessments
- Added READMEs to key folders like `test` and `lib` to explain their purpose

[unreleased]: https://github.com/jrg94/portfolio-project/compare/v2024.08.07...HEAD
[2024.08.07]: https://github.com/jrg94/portfolio-project/compare/v2024.01.07...v2024.08.07
[2024.01.07]: https://github.com/jrg94/portfolio-project/releases/tag/v2024.01.07
