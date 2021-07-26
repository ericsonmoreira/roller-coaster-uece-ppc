import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * @author Ericson Rogerio Moreira
 * @version 1.0.0
 */
public class MontanhaRussa {
    private final int N; // Número de passageiros
    private final int M; // Número de carros
    private final int C; // Número bancos em um carro
    private final int TE; // Tempo de embarque e desembarque em um carro
    private final int TM; // Tempo que um carro leva para dar uma volta
    private final int TP_MIN; // Tempo mínimo de chegada dos passageiros à montanha russa
    private final int TP_MAX; // Tempo máximo de chegada dos passageiros à montanha russa

    private final Semaphore ridingSemaphore; // Semaphore para bloquear enquanto um carro está dirigindo

    private final Semaphore headPassengerQueueSemaphore; // Semaphore que controla a saída de passageiros na fila

    private final Queue<Passenger> passengerQueue;

    private int happyPassengersCount; // contador de passageiros que brincaram na Montanha Russa

    private MontanhaRussa(int N, int M, int C, int TE, int TM, int TP_MIN, int TP_MAX) {
        this.N = N;
        this.M = M;
        this.C = C;
        this.TE = TE;
        this.TM = TM;
        this.TP_MIN = TP_MIN;
        this.TP_MAX = TP_MAX;
        this.ridingSemaphore = new Semaphore(1);
        this.headPassengerQueueSemaphore = new Semaphore(1);
        this.passengerQueue = new LinkedList<>();
        this.happyPassengersCount = 0;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public int getC() {
        return C;
    }

    public int getTE() {
        return TE;
    }

    public int getTM() {
        return TM;
    }

    public long getTP() {
        int range = this.TP_MAX - this.TP_MIN;
        return (long) (Math.random() * range + this.TP_MIN) * 1000;
    }

    public Queue<Passenger> getPassengerQueue() {
        return passengerQueue;
    }

    public Semaphore getRidingSemaphore() {
        return ridingSemaphore;
    }

    public Semaphore getHeadPassengerQueueSemaphore() {
        return headPassengerQueueSemaphore;
    }

    public void byeBye() {
        this.happyPassengersCount += this.C;
        Printer.printlnColor("Total de passageiros felizes: " + this.happyPassengersCount, PrinterColors.BLUE);
    }

    public boolean isEnd() {
        return this.happyPassengersCount >= this.N;
    }

    public static void main(String[] args) {
        String params = lerArquivo(new File(args[0]));

        MontanhaRussa montanhaRussa = generateMontanhaRussa(params);

        ArrayList<Passenger> passageiros = new ArrayList<>();
        ArrayList<Car> carros = new ArrayList<>();

        long initialTime = System.currentTimeMillis();

        Printer.printlnColor("Iniciando em: " + initialTime, PrinterColors.WHITE);

        Printer.printlnColor(montanhaRussa.toString(), PrinterColors.PURPLE);

        Printer.printlnColor("Criando Threads dos Passageiros", PrinterColors.WHITE);
        for (int i = 0; i < montanhaRussa.getN(); i++) {
            passageiros.add(new Passenger(i, montanhaRussa));
        }

        Printer.printlnColor("Criando Threads dos Carros em: " + System.currentTimeMillis(), PrinterColors.WHITE);
        for (int i = 0; i < montanhaRussa.getM(); i++) {
            carros.add(new Car(i, montanhaRussa));
        }

        Printer.printlnColor("Start Threads dos Passageiros em: " + System.currentTimeMillis(), PrinterColors.WHITE);
        passageiros.forEach(Thread::start);

        Printer.printlnColor("Start Threads dos Carros em: " + System.currentTimeMillis(), PrinterColors.WHITE);
        carros.forEach(Thread::start);

        try {
            for (Thread passageirosThread : passageiros) passageirosThread.join();
            for (Thread carrosThread : carros) carrosThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finalTime = System.currentTimeMillis();

        generateReport(passageiros, carros, initialTime, finalTime);

        Printer.printlnColor("FIM em: " + finalTime, PrinterColors.WHITE);
    }

    private static void generateReport(
            ArrayList<Passenger> passageiros,
            ArrayList<Car> carros,
            long initalTime,
            long finaTime
    ) {
        long[] passageirosTimes = passageiros.stream().mapToLong(
                passenger -> passenger.getOutQueueTimestamp() - passenger.getInQueueTimestamp()
        ).toArray();

        long movingTime = carros.stream().mapToLong(Car::getTotalTimeInRoad).sum();

        long totalAppTime = finaTime - initalTime;

        double usageTime = (double) movingTime / (double) totalAppTime;

        long minTime = Arrays.stream(passageirosTimes).min().getAsLong();
        long maxTime = Arrays.stream(passageirosTimes).max().getAsLong();
        double avaregeTime = Arrays.stream(passageirosTimes).average().getAsDouble();

        System.out.println();
        Printer.printlnColor("=============== Relatório ===============", PrinterColors.PURPLE);
        Printer.printlnColor("Tempo Total App: " + totalAppTime + " ms", PrinterColors.PURPLE);
        Printer.printlnColor("Menor tempo de um passageiro na fila: " + minTime + " ms", PrinterColors.PURPLE);
        Printer.printlnColor("Maior tempo de um passageiro na fila: " + maxTime + " ms", PrinterColors.PURPLE);
        Printer.printlnColor("Média tempo de um passageiro na fila: " + avaregeTime + " ms", PrinterColors.PURPLE);
        Printer.printlnColor("Tempo de movimentação do(s) carro(s): " + movingTime + " ms", PrinterColors.PURPLE);
        Printer.printlnColor("Utilização do(s) carro(s): " + usageTime, PrinterColors.PURPLE);
        Printer.printlnColor("============== Fim Relaório ==============", PrinterColors.PURPLE);
    }

    private static String lerArquivo(File file) {
        String texto = "";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            texto = bufferedReader.readLine();
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texto;
    }

    private static MontanhaRussa generateMontanhaRussa(String s) {
        String[] params = s.split(" ");
        int N = Integer.parseInt(params[0]);
        int M = Integer.parseInt(params[1]);
        int C = Integer.parseInt(params[2]);
        int TE = Integer.parseInt(params[3]);
        int TM = Integer.parseInt(params[4]);
        int TP_MIN = Integer.parseInt(params[5]);
        int TP_MAX = Integer.parseInt(params[6]);
        return new MontanhaRussa(N, M, C, TE, TM, TP_MIN, TP_MAX);
    }

    @Override
    public String toString() {
        return "MontanhaRussa{" +
                "N=" + N +
                ", M=" + M +
                ", C=" + C +
                ", TE=" + TE +
                ", TM=" + TM +
                ", TP_MIN=" + TP_MIN +
                ", TP_MAX=" + TP_MAX +
                '}';
    }
}
