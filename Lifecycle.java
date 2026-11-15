class Lifecycle {
    static String[] allowedNext(String status) {
        if (status.equals("New")) return new String[]{"Assigned"};
        if (status.equals("Assigned")) return new String[]{"Fixed"};
        if (status.equals("Fixed")) return new String[]{"Verified", "Reopened"};
        if (status.equals("Verified")) return new String[]{"Closed", "Reopened"};
        if (status.equals("Closed")) return new String[]{"Reopened"};
        if (status.equals("Reopened")) return new String[]{"Assigned"};
        return new String[0];
    }

    static String allowedText(String status) {
        String[] next = allowedNext(status);
        if (next.length == 0) return "none";
        if (next.length == 1) return next[0];
        return next[0] + " or " + next[1];
    }
}
