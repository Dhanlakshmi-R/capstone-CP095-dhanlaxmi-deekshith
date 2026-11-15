class Output {
    static void printSummary(int index) {
        System.out.printf("#%-3d %-32s %-10s %-9s %-10s %s%n",
                BugStore.ids[index],
                shorten(BugStore.titles[index], 32),
                BugStore.modules[index],
                BugStore.severities[index],
                BugStore.statuses[index],
                BugStore.developers[index].isEmpty() ? "Unassigned" : BugStore.developers[index]);
    }

    static void printDetails(int index) {
        System.out.println("\n--- Bug #" + BugStore.ids[index] + " ---");
        System.out.println("Title:        " + BugStore.titles[index]);
        System.out.println("Module:       " + BugStore.modules[index]);
        System.out.println("Severity:     " + BugStore.severities[index]);
        System.out.println("Status:       " + BugStore.statuses[index]);
        System.out.println("Developer:    " +
                (BugStore.developers[index].isEmpty() ? "Unassigned" : BugStore.developers[index]));
        System.out.println("Reported:     " + ageText(index));
        System.out.println("Reopened:     " + BugStore.reopenCounts[index] + " time(s)");
        System.out.println("Steps:        " + BugStore.steps[index]);
        System.out.println("Allowed next: " + Lifecycle.allowedText(BugStore.statuses[index]));
        System.out.println("History:");
        printHistory(index);
    }

    static void printHistory(int index) {
        String text = BugStore.histories[index];
        int start = 0;
        while (start < text.length()) {
            int end = text.indexOf('\n', start);
            if (end == -1) end = text.length();
            System.out.println("  - " + text.substring(start, end));
            start = end + 1;
        }
    }

    static String shorten(String text, int max) {
        if (text.length() <= max) return text;
        return text.substring(0, max - 3) + "...";
    }

    static long daysOld(int index) {
        long days = (System.currentTimeMillis() - BugStore.reportedAt[index]) / Config.DAY_MILLIS;
        return days < 0 ? 0 : days;
    }

    static String ageText(int index) {
        long days = daysOld(index);
        if (days == 0) return "today";
        if (days == 1) return "1 day old";
        return days + " days old";
    }

    static String bar(int value, int max) {
        int width = (value * 20) / max;
        String result = "";
        for (int i = 0; i < width; i++) result += "#";
        return result;
    }
}
