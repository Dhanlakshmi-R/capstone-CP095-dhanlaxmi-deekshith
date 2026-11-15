class BugActions {
    static void add() {
        if (BugStore.count >= Config.MAX_BUGS) {
            System.out.println("The board is full. Delete some bugs first.");
            return;
        }

        System.out.println("\n--- Report a bug ---");
        String title = Input.readRequired("Title: ");
        int moduleChoice = Input.chooseOption("Which part of the app?", Config.MODULES);
        int severityChoice = Input.chooseOption("How serious is it?", Config.SEVERITIES);
        String reproductions = Input.readLine("Steps to reproduce (optional): ");
        if (reproductions.isEmpty()) reproductions = "No reproduction steps were added yet.";

        int index = BugStore.insert(title, Config.MODULES[moduleChoice], Config.SEVERITIES[severityChoice],
                "", "New", reproductions, 0, 0);
        Storage.save();
        System.out.println("Bug #" + BugStore.ids[index] + " reported.");
    }

    static void list() {
        System.out.println("\n--- All bugs (" + BugStore.count + ") ---");
        if (BugStore.count == 0) {
            System.out.println("No bugs have been reported yet.");
            return;
        }

        for (int i = 0; i < BugStore.count; i++) {
            Output.printSummary(i);
            System.out.println("     " + Output.ageText(i) + ", reopened " +
                    BugStore.reopenCounts[i] + " time(s)");
        }
    }

    static void view() {
        int index = askBugIndex();
        if (index == -1) return;
        Output.printDetails(index);
    }

    static void assign() {
        int index = askBugIndex();
        if (index == -1) return;

        if (BugStore.statuses[index].equals("Closed")) {
            System.out.println("Bug #" + BugStore.ids[index] + " is closed. Reopen it before assigning.");
            return;
        }

        System.out.println("Current developer: " +
                (BugStore.developers[index].isEmpty() ? "Unassigned" : BugStore.developers[index]));
        int developerChoice = Input.chooseOption("Assign to:", Config.DEVELOPERS);
        String developer = Config.DEVELOPERS[developerChoice];

        boolean changesStatus = BugStore.statuses[index].equals("New") ||
                BugStore.statuses[index].equals("Reopened");
        if (changesStatus) {
            if (!Input.confirm("Assign bug #" + BugStore.ids[index] + " to " + developer +
                    " and move it from " + BugStore.statuses[index] + " to Assigned?")) return;
            BugStore.developers[index] = developer;
            BugStore.addHistory(index, "Assigned to " + developer);
            applyStatus(index, "Assigned");
        } else {
            BugStore.developers[index] = developer;
            BugStore.addHistory(index, "Reassigned to " + developer);
            Storage.save();
            System.out.println("Bug #" + BugStore.ids[index] + " assigned to " + developer + ".");
        }
    }

    static void changeStatus() {
        int index = askBugIndex();
        if (index == -1) return;

        System.out.println("Bug #" + BugStore.ids[index] + " is currently " + BugStore.statuses[index] + ".");
        String[] next = Lifecycle.allowedNext(BugStore.statuses[index]);
        if (next.length == 0) {
            System.out.println("No further status changes are allowed.");
            return;
        }

        int choice = Input.chooseOption("Move to:", next);
        String target = next[choice];
        String developer = BugStore.developers[index];

        if (target.equals("Assigned") && developer.isEmpty()) {
            int developerChoice = Input.chooseOption("Choose a developer first:", Config.DEVELOPERS);
            developer = Config.DEVELOPERS[developerChoice];
        }

        if (!Input.confirm("Move bug #" + BugStore.ids[index] + " from " +
                BugStore.statuses[index] + " to " + target + "?")) {
            return;
        }

        if (!developer.equals(BugStore.developers[index])) {
            BugStore.developers[index] = developer;
            BugStore.addHistory(index, "Assigned to " + developer);
        }
        applyStatus(index, target);
    }

    static void applyStatus(int index, String target) {
        String old = BugStore.statuses[index];
        BugStore.statuses[index] = target;
        if (target.equals("Reopened")) BugStore.reopenCounts[index]++;
        BugStore.addHistory(index, "Moved from " + old + " to " + target);
        Storage.save();
        System.out.println("Bug #" + BugStore.ids[index] + " is now " + target + ".");
    }

    static int askBugIndex() {
        int id = Input.readInt("Bug ID: ");
        int index = BugStore.findIndexById(id);
        if (index == -1) System.out.println("No bug with ID #" + id + ".");
        return index;
    }
}
