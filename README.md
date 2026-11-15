# Problem Title: BugBoard: Defect Lifecycle Tracking System

**Problem ID: CP095**

## Team Members

**1. Name: Dhanlaxmi   | USN: 1AT24CS052**

**2. Name: Deekshith S | USN: 1AT24CS050** 

**JAVA TRACK**

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

```text
Welcome to BugBoard - Defect Lifecycle Tracking System

===== BugBoard =====
1. Report a bug
2. View all bugs
3. View one bug
4. Assign a bug
5. Change bug status
6. Search bugs
7. Reports
8. Exit
Choose an option: 1

--- Report a bug ---
Title: login button not working
Which part of the app?
  1. Auth
  2. Payments
  3. UI
  4. Search
  5. Data
Enter 1-5: 1
How serious is it?
  1. Low
  2. Medium
  3. High
  4. Critical
Enter 1-4: 3
Steps to reproduce (optional): the button does nothing whne clicked
Bug #8 reported.

===== BugBoard =====
1. Report a bug
2. View all bugs
3. View one bug
4. Assign a bug
5. Change bug status
6. Search bugs
7. Reports
8. Exit
Choose an option: 2

--- All bugs (8) ---
#1   Login button does not respond    Auth       High      New        Unassigned
     11 days old, reopened 0 time(s)
#2   Payment fails for saved cards    Payments   Critical  Assigned   Priya
     13 days old, reopened 0 time(s)
#3   Search results show stale items  Search     Medium    Fixed      Kiran
     16 days old, reopened 0 time(s)
#4   Profile picture overlaps the ... UI         Low       Verified   Asha
     19 days old, reopened 0 time(s)
#5   Export report totals are wrong   Data       High      Closed     Ravi
     23 days old, reopened 1 time(s)
#6   Session expires after five mi... Auth       High      Reopened   Kiran
     14 days old, reopened 2 time(s)
#7   Root test bug                    Auth       Low       New        Unassigned
     11 days old, reopened 0 time(s)
#8   login button not working         Auth       High      New        Unassigned
     today, reopened 0 time(s)

===== BugBoard =====
1. Report a bug
2. View all bugs
3. View one bug
4. Assign a bug
5. Change bug status
6. Search bugs
7. Reports
8. Exit
Choose an option: 8
Saved 8 bug(s) to bugs.txt.
Goodbye.
```

