import java.util.*;

public class Main {
    static Scanner in;
    static Scanner inLine;

    public static void main(String[] args) {
        Main.in = new Scanner(System.in);
        Main.inLine = new Scanner(System.in);


        System.out.println("Input different records quantity:");
        int n = in.nextInt();
        List<Record> recordList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            recordList.add(new Record());
        }

        System.out.println("\nInput document quantity: ");
        int quantity = in.nextInt();
        Queue<Doc> docProcessingQueue = new Queue<>(quantity);
        for (int i = 0; i < quantity; i++) {
            docProcessingQueue.enqueue(new Doc(recordList));
        }

        System.out.println("----------------------------------\nInput clerk quantity:");
        ClerkCircle circle = new ClerkCircle(in.nextInt());
        circle.distributeTheWork();

        for (Clerk i : circle.clerkList
        ) {
            i.introduceYourself();
        }

        boolean mognoPeredat;
        int StartDocProcessingQueueSize = docProcessingQueue.size;

        while (StartDocProcessingQueueSize != circle.finishedDocs.size) {
            if (!docProcessingQueue.isEmpty()) {
                docProcessingQueue.peek().processingTime--;//У пакета документов, находящегося в начале очереди, на 1 уменьшается время поступления на обработку.
                if (docProcessingQueue.peek().processingTime == 0) {
                    circle.chooseClerk().table.push(docProcessingQueue.dequeue());
                }//Если у пакета документов, находящегося в начале очереди, время поступления на обработку равно 0, то он помещается на верх стопки документов на столе некоторого клерка. У каждого клерка на столе своя стопка документов, которые он должен обработать. При выборе клерка, которому необходимо передать пакет документов из очереди, необходимо выбирать клерка с наименьшей стопкой документов. Если таких клерков несколько, то выбирается клерк с минимальным уникальным номером.

            }

            for (Clerk clerk : circle.clerkList) {

                clerk.workingTime++;//общее время работы клерка

                if (clerk.workingDoc == null) {
                    if (!clerk.table.isEmpty()) {
                        //-------------------------------------------------
                        if (clerk.table.size > clerk.maxDocsOnTable) {
                            clerk.maxDocsOnTable = clerk.table.size;//максимальное колво доков на столе
                        }//------------------------------------------------

                        clerk.workingDoc = clerk.table.pop();//Для каждого клерка проверяется, есть ли у него документ в работе. Если нет, он забирает себе в работу очередной пакет документов из своей стопки. При этом если стопка пустая, то клерк ничего в это время не делает.
                    } else {
                        clerk.freeTime++;
                        continue;
                    }
                }
//==============================документ в работе есть\

                clerk.workingDoc.totalProcessingTime++;//общее время обработки документа

                if (clerk.workingDoc.isDocFinished()) {
                    circle.finishedDocs.push(clerk.workingDoc);
                    clerk.workingDoc = null;//Для каждого клерка проверяется, если документы, находящиеся в работе, уже обработаны (в пакет документов уже внесены все необходимые виды записей), то пакет перемещается в общую для всей канцелярии стопку готовых документов.
                } else {
                    mognoPeredat = true;
                    for (Integer record : clerk.workingDoc.mapRecords.keySet()) {

                        if (clerk.workingDoc.mapRecords.get(record)[1] == null && clerk.doesClerkCanMakeRecord(record)) {

                            //клерк обрабатывает документ
                            if (clerk.workingDoc.mapRecords.get(record)[0] == 0) {
                                clerk.workingDoc.mapRecords.get(record)[1] = clerk.clerkId;
                                //-------------------------------------------------------
                                clerk.processedDocs.add(clerk.workingDoc);
                                //----------------------------------------------------

                            } else {
                                mognoPeredat = false;
                                clerk.workingDoc.mapRecords.get(record)[0]--;

                            }
                        }

                    }
                    if (mognoPeredat) {
                        if (circle.clerkList.indexOf(clerk) == circle.clerkList.size() - 1) {
                            circle.clerkList.get(0).table.push(clerk.workingDoc);
                        } else {
                            circle.clerkList.get(circle.clerkList.indexOf(clerk) + 1).table.push(clerk.workingDoc);
                        }
                        clerk.workingDoc = null;//передает док след
                    } else {
                        clerk.freeTime++;
                    }
                }
            }
        }

        int finishedDocsQuantity = circle.finishedDocs.size;
        int fullWorkTimeOnDocs = 0;
        for (int i = 0; i < finishedDocsQuantity; i++) {
            System.out.println("----------------------------------");
            fullWorkTimeOnDocs += circle.finishedDocs.peek().totalProcessingTime;
            circle.finishedDocs.pop().show();
        }

        System.out.println("\nAvg doc processing time in the chancellery " + fullWorkTimeOnDocs / finishedDocsQuantity);
        for (Clerk clerk : circle.clerkList
        ) {
            clerk.tellAboutYourWork();
        }

        Main.in.close();
        Main.inLine.close();
    }
}