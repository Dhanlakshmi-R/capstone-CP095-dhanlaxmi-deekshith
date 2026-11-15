public class Main {
    public static void main(String[] args) {
        boolean loaded = Storage.load();
        if (loaded && BugStore.count > 0) {
            System.out.println("Loaded " + BugStore.count + " bug(s) from " + Storage.FILE + ".");
        } else if (Config.LOAD_SAMPLES) {
            SampleData.load();
            Storage.save();
            System.out.println("Loaded sample data.");
        } else {
            System.out.println("Starting with an empty board.");
        }

        System.out.println("Welcome to BugBoard - Defect Lifecycle Tracking System");

        int choice;
        do {
            Menu.print();
            choice = Input.readInt("Choose an option: ");

            if (choice == 1) {
                BugActions.add();
            } else if (choice == 2) {
                BugActions.list();
            } else if (choice == 3) {
                BugActions.view();
            } else if (choice == 4) {
                BugActions.assign();
            } else if (choice == 5) {
                BugActions.changeStatus();
            } else if (choice == 6) {
                Search.run();
            } else if (choice == 7) {
                Reports.show();
            } else if (choice != 8) {
                System.out.println("Please choose a number from 1 to 8.");
            }
        } while (choice != 8);

        if (Storage.save()) {
            System.out.println("Saved " + BugStore.count + " bug(s) to " + Storage.FILE + ".");
        } else {
            System.out.println("Could not save data to " + Storage.FILE + ".");
        }
        System.out.println("Goodbye.");
    }
}
