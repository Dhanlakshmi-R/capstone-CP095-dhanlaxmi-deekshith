class Reports {
    static void show() {
        System.out.println("\n--- Reports ---");
        System.out.println("Total bugs:         " + BugStore.count);
        System.out.println("Open bugs:          " + countOpen());
        System.out.println("Critical open:      " + countCriticalOpen());
        System.out.println("Fixed, waiting:     " + countStatus("Fixed"));
        System.out.println("Reopened right now: " + countStatus("Reopened"));
        System.out.println();
        moduleReport();
        System.out.println();
        ageReport();
    }

    static int countOpen() {
        int total = 0;
        for (int i = 0; i < BugStore.count; i++) {
            if (!BugStore.statuses[i].equals("Closed")) total++;
        }
        return total;
    }

    static int countCriticalOpen() {
        int total = 0;
        for (int i = 0; i < BugStore.count; i++) {
            if (!BugStore.statuses[i].equals("Closed") &&
                    BugStore.severities[i].equals("Critical")) total++;
        }
        return total;
    }

    static int countStatus(String status) {
        int total = 0;
        for (int i = 0; i < BugStore.count; i++) {
            if (BugStore.statuses[i].equals(status)) total++;
        }
        return total;
    }

    static int countModule(String module) {
        int total = 0;
        for (int i = 0; i < BugStore.count; i++) {
            if (BugStore.modules[i].equals(module)) total++;
        }
        return total;
    }

    static void moduleReport() {
        System.out.println("Bugs per module:");
        int max = 1;
        for (int m = 0; m < Config.MODULES.length; m++) {
            int amount = countModule(Config.MODULES[m]);
            if (amount > max) max = amount;
        }
        for (int m = 0; m < Config.MODULES.length; m++) {
            int amount = countModule(Config.MODULES[m]);
            System.out.printf("  %-10s %-20s %d%n", Config.MODULES[m], Output.bar(amount, max), amount);
        }
    }

    static void ageReport() {
        System.out.println("Open bugs by age (oldest first):");
        int open = countOpen();
        if (open == 0) {
            System.out.println("  No open bugs.");
            return;
        }

        int[] order = new int[open];
        int at = 0;
        for (int i = 0; i < BugStore.count; i++) {
            if (!BugStore.statuses[i].equals("Closed")) {
                order[at] = i;
                at++;
            }
        }

        // Selection sort: oldest first.
        for (int i = 0; i < order.length - 1; i++) {
            int oldest = i;
            for (int j = i + 1; j < order.length; j++) {
                if (Output.daysOld(order[j]) > Output.daysOld(order[oldest])) oldest = j;
            }
            int temp = order[i];
            order[i] = order[oldest];
            order[oldest] = temp;
        }

        for (int i = 0; i < order.length; i++) {
            int index = order[i];
            System.out.printf("  #%-3d %-32s %s%n",
                    BugStore.ids[index], Output.shorten(BugStore.titles[index], 32),
                    Output.ageText(index));
        }
    }
}
