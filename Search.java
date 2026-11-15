class Search {
    static void run() {
        String query = Input.readLine("Search by ID, title, module, severity or developer: ").toLowerCase();
        if (query.isEmpty()) {
            System.out.println("Enter something to search for.");
            return;
        }

        System.out.println("\n--- Search results ---");
        int found = 0;
        for (int i = 0; i < BugStore.count; i++) {
            if (matches(i, query)) {
                Output.printSummary(i);
                found++;
            }
        }

        if (found == 0) System.out.println("No bugs matched.");
        else System.out.println(found + " bug(s) found.");
    }

    static boolean matches(int index, String query) {
        return ("" + BugStore.ids[index]).contains(query)
                || BugStore.titles[index].toLowerCase().contains(query)
                || BugStore.modules[index].toLowerCase().contains(query)
                || BugStore.severities[index].toLowerCase().contains(query)
                || BugStore.developers[index].toLowerCase().contains(query);
    }
}
