public class Passenger extends Thread {
    private final int id; // id do passageiro
    private final MontanhaRussa montanhaRussa; // instância da Montanha Russa
    private long inQueueTimestamp; // momento em que o passageiro entrou na fila
    private long outQueueTimestamp; // momento em que o passageiro saiu da fila

    public Passenger(int id, MontanhaRussa montanhaRussa) {
        this.id = id;
        this.montanhaRussa = montanhaRussa;
        this.inQueueTimestamp = 0;
        this.outQueueTimestamp = 0;
    }

    public void run() {
        try {
            getInPassagersQueue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getInPassagersQueue() throws InterruptedException {
        synchronized (this.montanhaRussa.getPassengerQueue()) {
            Thread.sleep(montanhaRussa.getTP());
            this.inQueueTimestamp = System.currentTimeMillis();
            this.setInQueueTimestamp(inQueueTimestamp);
            this.montanhaRussa.getPassengerQueue().add(this);
            Printer.printlnColor(this + " => Entrou na fila: " + this.inQueueTimestamp, PrinterColors.YELLOW);
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
