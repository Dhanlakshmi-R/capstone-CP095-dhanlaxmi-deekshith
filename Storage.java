import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Stretch feature: saves bugs to a plain text file so they are remembered
 * between runs. Each bug is stored as key=value lines and ended with "---".
 */
class Storage {
    static final String FILE = "bugs.txt";

    static boolean load() {
        BugStore.clear();
        File file = new File(FILE);
        if (!file.exists()) return false;

        try {
            Scanner reader = new Scanner(file);

            int id = 0;
            String title = "";
            String module = "";
            String severity = "";
            String developer = "";
            String status = "";
            String steps = "";
            int reopenCount = 0;
            long reportedAt = System.currentTimeMillis();
            String history = "";
            boolean hasBug = false;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                if (line.equals("---")) {
                    if (hasBug) {
                        BugStore.insertRaw(id, title, module, severity, developer, status,
                                steps, reopenCount, reportedAt, history);
                    }
                    id = 0;
                    title = "";
                    module = "";
                    severity = "";
                    developer = "";
                    status = "";
                    steps = "";
                    reopenCount = 0;
                    reportedAt = System.currentTimeMillis();
                    history = "";
                    hasBug = false;
                    continue;
                }

                int equals = line.indexOf('=');
                if (equals == -1) continue;
                String key = line.substring(0, equals);
                String value = line.substring(equals + 1);

                if (key.equals("id")) {
                    id = toInt(value);
                    hasBug = true;
                } else if (key.equals("title")) {
                    title = value;
                } else if (key.equals("module")) {
                    module = value;
                } else if (key.equals("severity")) {
                    severity = value;
                } else if (key.equals("developer")) {
                    developer = value;
                } else if (key.equals("status")) {
                    status = value;
                } else if (key.equals("steps")) {
                    steps = value;
                } else if (key.equals("reopenCount")) {
                    reopenCount = toInt(value);
                } else if (key.equals("reportedAt")) {
                    reportedAt = toLong(value);
                } else if (key.equals("history")) {
                    if (history.isEmpty()) history = value;
                    else history = history + "\n" + value;
                }
            }

            if (hasBug) {
                BugStore.insertRaw(id, title, module, severity, developer, status,
                        steps, reopenCount, reportedAt, history);
            }

            reader.close();
            return true;
        } catch (Exception error) {
            BugStore.clear();
            return false;
        }
    }

    static boolean save() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE));

            for (int i = 0; i < BugStore.count; i++) {
                writer.println("id=" + BugStore.ids[i]);
                writer.println("title=" + BugStore.titles[i]);
                writer.println("module=" + BugStore.modules[i]);
                writer.println("severity=" + BugStore.severities[i]);
                writer.println("developer=" + BugStore.developers[i]);
                writer.println("status=" + BugStore.statuses[i]);
                writer.println("steps=" + BugStore.steps[i]);
                writer.println("reopenCount=" + BugStore.reopenCounts[i]);
                writer.println("reportedAt=" + BugStore.reportedAt[i]);

                String history = BugStore.histories[i];
                int start = 0;
                while (start < history.length()) {
                    int end = history.indexOf('\n', start);
                    if (end == -1) end = history.length();
                    writer.println("history=" + history.substring(start, end));
                    start = end + 1;
                }

                writer.println("---");
            }

            writer.close();
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    static int toInt(String value) {
        return (int) toLong(value);
    }

    static long toLong(String value) {
        long result = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c >= '0' && c <= '9') result = result * 10 + (c - '0');
        }
        return result;
    }
}
