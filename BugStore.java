class BugStore {
    static final int[] ids = new int[Config.MAX_BUGS];
    static final String[] titles = new String[Config.MAX_BUGS];
    static final String[] modules = new String[Config.MAX_BUGS];
    static final String[] severities = new String[Config.MAX_BUGS];
    static final String[] developers = new String[Config.MAX_BUGS];
    static final String[] statuses = new String[Config.MAX_BUGS];
    static final String[] steps = new String[Config.MAX_BUGS];
    static final String[] histories = new String[Config.MAX_BUGS];
    static final int[] reopenCounts = new int[Config.MAX_BUGS];
    static final long[] reportedAt = new long[Config.MAX_BUGS];

    static int count = 0;
    static int nextId = 1;

    static int insert(String title, String module, String severity, String developer,
                      String status, String reproductions, int daysOld, int reopenCount) {
        String history = "Reported";
        if (!developer.isEmpty()) {
            history = "Assigned to " + developer + "\n" + history;
        }
        long when = System.currentTimeMillis() - daysOld * Config.DAY_MILLIS;
        return insertRaw(nextId, title, module, severity, developer, status,
                reproductions, reopenCount, when, history);
    }

    static int insertRaw(int id, String title, String module, String severity, String developer,
                         String status, String reproductions, int reopenCount,
                         long when, String history) {
        if (count >= Config.MAX_BUGS) return -1;

        int index = count;
        ids[index] = id;
        if (id >= nextId) nextId = id + 1;
        titles[index] = title;
        modules[index] = module;
        severities[index] = severity;
        developers[index] = developer;
        statuses[index] = status;
        steps[index] = reproductions;
        reopenCounts[index] = reopenCount;
        reportedAt[index] = when;
        histories[index] = history;
        count++;
        return index;
    }

    static void clear() {
        count = 0;
        nextId = 1;
    }

    static int findIndexById(int id) {
        for (int i = 0; i < count; i++) {
            if (ids[i] == id) return i;
        }
        return -1;
    }

    static void addHistory(int index, String text) {
        histories[index] = text + "\n" + histories[index];
    }
}
