import java.util.LinkedList;
import java.util.List;

public class ClerkCircle {//замкнутая очередь клерков
    List<Clerk> clerkList;
    int clerkQuantity;
    Stack<Doc> finishedDocs;

    public ClerkCircle(int quantity) {
        clerkQuantity = quantity;
        clerkList = new LinkedList<>();
        for (int i = 0; i < clerkQuantity; i++) {
            clerkList.add(new Clerk());
        }
        finishedDocs = new Stack<>();
    }

    public void distributeTheWork() {
        for (Clerk clerk : clerkList) {
            while (clerk.possibleRecords.isEmpty()) {
                clerk.fillPossibleRecordsList();
            }
        }

        for (int i = 1; i <= Record.lastId; i++) {
            if (!isRecordContainsInList(i)) {
                clerkList.get((int) (Math.random() * clerkQuantity)).possibleRecords.add(i);
            }
        }

    }

    public boolean isRecordContainsInList(int i) {
        for (Clerk clerk : clerkList) {
            if (clerk.possibleRecords.contains(i)) {
                return true;
            }
        }
        return false;
    }

    public Clerk chooseClerk() {
        Clerk tempClerk = clerkList.get(0);
        for (Clerk clerk : clerkList) {
            if (tempClerk.table.size > clerk.table.size) {
                tempClerk = clerk;
            }
            if (tempClerk.table.size == clerk.table.size) {
                if (tempClerk.clerkId > clerk.clerkId) {
                    tempClerk = clerk;
                }
            }
        }
        return tempClerk;
    }


}
