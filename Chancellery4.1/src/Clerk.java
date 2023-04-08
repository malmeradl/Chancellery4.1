import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Clerk {
    int clerkId;
    String name;
    List<Integer> possibleRecords;
    static int lastId = 0;
    Stack<Doc> table;
    Doc workingDoc = null;
    int workingTime;//общее время работы
    int freeTime;//время отдыха
    Set<Doc> processedDocs;//колво обработанных документов
    int maxDocsOnTable;//максимальное колво документов на столе

    public Clerk() {
        clerkId = ++lastId;
        System.out.println("ClerkId " + clerkId + "\nInput clerks name ");
        name = Main.in.next();
        possibleRecords = new ArrayList<>();
        table = new Stack<>();
        processedDocs = new HashSet<>();

    }

    public void fillPossibleRecordsList() {
        int rand;
        for (int i = 1; i <= Record.lastId; i++) {
            rand = (int) (Math.random() * 10);
            if (rand == 0) {
                possibleRecords.add(i);
            }
        }
    }

    public void introduceYourself() {
        System.out.println("Hello, iam clerk №" + clerkId + ". My name is " + name + ". I can make records ");
        for (Integer i : possibleRecords) {
            System.out.println(i);
        }
    }

    public boolean doesClerkCanMakeRecord(int recId) {
        return possibleRecords.contains(recId);
    }

    public void tellAboutYourWork() {
        System.out.println("\nMy name is " + name + ". I worked for " + workingTime + " minutes, rested for " + freeTime + " minutes. I have been working on " + processedDocs.size() + " documents. The maximum quantity of documents on my table is " + maxDocsOnTable + ".");
    }
}
