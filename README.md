# Problem Title: BugBoard: Defect Lifecycle Tracking System

**Problem ID: CP095**

## Team Members

1. Name: Dhanlaxmi   | USN: 1AT24CS052
2. Name: Deekshith S | USN: 1AT24CS050


## How to Run the Program

**Prerequisites:**
- Java JDK 8 or newer.

**Steps:**
1. Clone or download this repository.
2. Open the project folder in your terminal.
3. Compile the program:

   ```bash
   javac *.java
   ```

4. Execute the program:

   ```bash
   java Main
   ```

   For Windows, use:

   ```bash
   java Main
   ```

## Features Implemented

- Menu-driven console interface
- Report a bug with title, module, severity, and reproduction steps
- View all bugs or view a single bug in detail
- Assign or reassign a developer
- Enforced lifecycle: New → Assigned → Fixed → Verified → Closed, with Reopened support
- Search by bug ID, title, module, severity, or developer
- Reports: totals, open bugs, critical bugs, fixed bugs, reopened bugs, bugs per module, and open bugs by age
- Saves and loads bugs from a text file (`bugs.txt`) — stretch feature
- Sample data loaded when `bugs.txt` does not exist (set `LOAD_SAMPLES = false` in `Config.java` to start empty)

> Note: bugs are saved to `bugs.txt` after every change and on exit. Delete `bugs.txt` to start fresh.

## Sample Run

**Input:**
```text
1
Login button not working
1
3
The button does nothing when clicked.
2
8
```

**Output:**
```text
===== BugBoard =====
1. Report a bug
2. View all bugs
3. View one bug
4. Assign a bug
5. Change bug status
6. Search bugs
7. Reports
8. Exit
Choose an option: 
--- All bugs (7) ---
#1   Login button does not respond    Auth       High      New        Unassigned
...
#7   Login button not working         Auth       High      New        Unassigned
```