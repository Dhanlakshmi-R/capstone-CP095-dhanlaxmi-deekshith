class SampleData {
    static void load() {
        BugStore.insert("Login button does not respond", "Auth", "High", "", "New",
                "Open the login page, enter valid credentials and click Sign in.", 0, 0);

        BugStore.insert("Payment fails for saved cards", "Payments", "Critical", "Priya", "Assigned",
                "Add a saved card, start checkout and submit the payment.", 2, 0);

        int search = BugStore.insert("Search results show stale items", "Search", "Medium", "Kiran", "Fixed",
                "Search for a bug, change its title, then search for the old title.", 5, 0);
        BugStore.addHistory(search, "Moved from Assigned to Fixed");

        int header = BugStore.insert("Profile picture overlaps the header", "UI", "Low", "Asha", "Verified",
                "Open the profile page in a narrow window and scroll down.", 8, 0);
        BugStore.addHistory(header, "Moved from Assigned to Fixed");
        BugStore.addHistory(header, "Moved from Fixed to Verified");

        int report = BugStore.insert("Export report totals are wrong", "Data", "High", "Ravi", "Closed",
                "Export the monthly report and compare the totals with the dashboard.", 12, 1);
        BugStore.addHistory(report, "Moved from Assigned to Fixed");
        BugStore.addHistory(report, "Moved from Fixed to Reopened");
        BugStore.addHistory(report, "Moved from Reopened to Assigned");
        BugStore.addHistory(report, "Moved from Assigned to Fixed");
        BugStore.addHistory(report, "Moved from Fixed to Verified");
        BugStore.addHistory(report, "Moved from Verified to Closed");

        int session = BugStore.insert("Session expires after five minutes", "Auth", "High", "Kiran", "Reopened",
                "Sign in, leave the tab idle for five minutes and click any link.", 3, 2);
        BugStore.addHistory(session, "Moved from Assigned to Fixed");
        BugStore.addHistory(session, "Moved from Fixed to Verified");
        BugStore.addHistory(session, "Moved from Verified to Closed");
        BugStore.addHistory(session, "Moved from Closed to Reopened");
        BugStore.addHistory(session, "Moved from Reopened to Assigned");
        BugStore.addHistory(session, "Moved from Assigned to Fixed");
        BugStore.addHistory(session, "Moved from Fixed to Verified");
        BugStore.addHistory(session, "Moved from Verified to Closed");
        BugStore.addHistory(session, "Moved from Closed to Reopened");
    }
}
