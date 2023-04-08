import java.util.*;

public class Doc {
    int docId;
    static int lastId = 0;
    String description;
    Map<Integer, Integer[]> mapRecords;//0-вр обработки записи, 1-подпись
    int processingTime;
    int totalProcessingTime;

    public Doc(List<Record> recordList) {
        docId = ++lastId;
        System.out.println("\nDocId " + docId + "\nInput doc description ");
        description = Main.inLine.nextLine();
        System.out.println("\nInput time to processing the doc  ");
        processingTime = Main.in.nextInt();
        mapRecords = new HashMap<>();
        fillNecessaryRecordsList(recordList);
        for (Integer recId : mapRecords.keySet()
        ) {
            System.out.println(recId);
        }

    }


    private void fillNecessaryRecordsList(List<Record> records) {
        int rand;

        while (mapRecords.isEmpty()) {
            for (Record record : records) {
                rand = (int) (Math.random() * 3);
                if (rand == 0) {
                    mapRecords.put(record.recordId, new Integer[2]);
                    mapRecords.get(record.recordId)[0] = record.recordTime;
                    mapRecords.get(record.recordId)[1] = null;
                }

            }
        }
    }

    public void show() {
        System.out.println("docId " + docId + "\ndescription the doc " + description + "\nrecords to be made in the document\n");
        for (Integer recordId : mapRecords.keySet()) {
            System.out.println(recordId);
        }
        System.out.println("\nfinished records ");
        for (Integer recordId : mapRecords.keySet()) {
            System.out.println("record №" + recordId + " was made by " + mapRecords.get(recordId)[1]);
        }
        System.out.println("\ntotal processing time " + totalProcessingTime);

    }

    public boolean isDocFinished() {
        for (Integer recordId : mapRecords.keySet()) {
            if (mapRecords.get(recordId)[1] == null) {
                return false;
            }
        }
        return true;
    }

}
