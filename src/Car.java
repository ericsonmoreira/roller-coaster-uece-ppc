import java.util.ArrayList;

class Car extends Thread {
    private final int id;
    private final ArrayList<Passenger> passengers;
    private final MontanhaRussa montanhaRussa;

    public Car(int id, MontanhaRussa montanhaRussa) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.montanhaRussa = montanhaRussa;
    }

    public void run() {
        while (true) {
            this.boardPassengers();
            if (this.passengers.size() == this.montanhaRussa.getC()) {
                ride();
            }
            // condição de parada
            if (this.montanhaRussa.isEnd()) {
                return;
            }
        }
    }

    private void boardPassengers() {
        try {
            this.montanhaRussa.getHeadPassengerQueueSemaphore().acquire();
            if (!montanhaRussa.getPassengerQueue().isEmpty()
                    && montanhaRussa.getPassengerQueue().size() >= montanhaRussa.getC()
            ) {
                Thread.sleep((long) montanhaRussa.getTE() * 1000);
                long boardPassengerCurrentTimeMillis = System.currentTimeMillis();
                for (int i = 0; i < montanhaRussa.getC(); i++) {
                    Passenger passenger = montanhaRussa.getPassengerQueue().poll();
                    passenger.setOutQueueTimestamp(boardPassengerCurrentTimeMillis);
                    Printer.printlnColor(
                            passenger + "=> Embarcou no carro: " + this + " em: " + boardPassengerCurrentTimeMillis,
                            PrinterColors.GREEN
                    );
                    this.passengers.add(passenger);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.montanhaRussa.getHeadPassengerQueueSemaphore().release();
        }
    }

    private void ride() {
        try {
            this.montanhaRussa.getRidingSemaphore().acquire();
            Printer.printlnColor(this + "=> Dirigindo em: " + System.currentTimeMillis(), PrinterColors.RED);
            Thread.sleep((long) montanhaRussa.getTM() * 1000);
            Printer.printlnColor(this + "=> Voltou em: " + System.currentTimeMillis(), PrinterColors.RED);
            unBoardPassengers();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.montanhaRussa.getRidingSemaphore().release();
        }
    }

    private void unBoardPassengers() {
        try {
            Thread.sleep((long) montanhaRussa.getTE() * 1000);
            long unBoardPassengerCurrentTimeMillis = System.currentTimeMillis();
            for (Passenger passenger : this.passengers) {
                Printer.printlnColor(
                        passenger + "=> Desembarcou no carro: " + this + " em: " + unBoardPassengerCurrentTimeMillis,
                        PrinterColors.GREEN
                );
            }
            this.passengers.clear();
            this.montanhaRussa.byeBye();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                '}';
    }
}