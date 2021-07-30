import java.util.ArrayList;

class Car extends Thread {
    private final int id; // id do carro
    private final ArrayList<Passenger> passengers; // lista de passageiros que estão no carro
    private final MontanhaRussa montanhaRussa; // instância da Montanha Russa
    private long totalTimeInRoad; // tempo total do carro em movimento

    public Car(int id, MontanhaRussa montanhaRussa) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.montanhaRussa = montanhaRussa;
        this.totalTimeInRoad = 0;
    }

    public void plusTotalTimeInRoad(long time) {
        this.totalTimeInRoad += time;
    }

    public void run() {
        while (true) {
            this.tryBoardPassengers();
            if (this.passengers.size() == this.montanhaRussa.getC()) {
                toDrive();
            }
            if (this.montanhaRussa.isEnd()) {
                return;
            }
        }
    }

    private void tryBoardPassengers() {
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
                            passenger + " => Embarcou no carro: " + this + " em: " + boardPassengerCurrentTimeMillis,
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

    private void toDrive() {
        long initalRideTime, finalRidetime;
        try {
            this.montanhaRussa.getRidingSemaphore().acquire();
            initalRideTime = System.currentTimeMillis();
            Printer.printlnColor(this + " => Dirigindo em: " + initalRideTime, PrinterColors.RED);
            Thread.sleep((long) montanhaRussa.getTM() * 1000);
            finalRidetime = System.currentTimeMillis();
            Printer.printlnColor(this + " => Voltou em: " + finalRidetime, PrinterColors.RED);
            plusTotalTimeInRoad(finalRidetime - initalRideTime);
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
                        passenger + " => Desembarcou no carro: " + this + " em: " + unBoardPassengerCurrentTimeMillis,
                        PrinterColors.GREEN
                );
            }
            this.passengers.clear();
            this.montanhaRussa.byeBye();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getTotalTimeInRoad() {
        return totalTimeInRoad;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                '}';
    }
}