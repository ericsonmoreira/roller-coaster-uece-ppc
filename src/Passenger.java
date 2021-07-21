public class Passenger extends Thread {
    private final int id;
    private final MontanhaRussa montanhaRussa;
    private long inQueueTimestamp;
    private long outQueueTimestamp;

    public Passenger(int id, MontanhaRussa montanhaRussa) {
        this.id = id;
        this.montanhaRussa = montanhaRussa;
        this.inQueueTimestamp = 0;
        this.outQueueTimestamp = 0;
    }

    public void run() {
        try {
            this.montanhaRussa.getDoorPassengerQueueSemaphore().acquire();
            Thread.sleep(montanhaRussa.getTP());
            this.inQueueTimestamp = System.currentTimeMillis();
            this.montanhaRussa.getPassengerQueue().add(this);
            Printer.printlnColor(this + "=> Entrou na fila: " + this.inQueueTimestamp, PrinterColors.YELLOW);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.montanhaRussa.getDoorPassengerQueueSemaphore().release();
        }
    }

    public long getInQueueTimestamp() {
        return inQueueTimestamp;
    }

    public void setInQueueTimestamp(long inQueueTimestamp) {
        this.inQueueTimestamp = inQueueTimestamp;
    }

    public long getOutQueueTimestamp() {
        return outQueueTimestamp;
    }

    public void setOutQueueTimestamp(long outQueueTimestamp) {
        this.outQueueTimestamp = outQueueTimestamp;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                '}';
    }
}
