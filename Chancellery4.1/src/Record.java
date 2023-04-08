public class Record {
    int recordId;
    String title;
    int recordTime;
    static int lastId = 0;

    public Record() {
        recordId = ++lastId;
        System.out.println("RecordId " + recordId + "\nInput records title ");
        title = Main.in.next();
        System.out.println("Input time to make a record in the document ");
        recordTime = Main.in.nextInt();

    }

    public void show() {
        System.out.println("recordId " + recordId + "\ntypeName " + title + "\ntime " + recordTime);
    }
}
