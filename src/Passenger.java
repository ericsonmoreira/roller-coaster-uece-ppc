public class Passenger implements Runnable {
    private final int id;

    private final MontanhaRussa montanhaRussa;

    public Passenger(int id, MontanhaRussa montanhaRussa) {
        this.id = id;
        this.montanhaRussa = montanhaRussa;
    }

    public void run() {
        try {
            this.montanhaRussa.getDoorPassengerQueueSemaphore().acquire();
            Thread.sleep(montanhaRussa.getTP());
            this.montanhaRussa.getPassengerQueue().add(this);
            Printer.printlnColor(this + "=> Entrou na fila: " + System.currentTimeMillis(), PrinterColors.YELLOW);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.montanhaRussa.getDoorPassengerQueueSemaphore().release();
        }
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                '}';
    }
}
