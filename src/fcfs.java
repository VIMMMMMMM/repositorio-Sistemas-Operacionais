import java.util.Scanner;

public class fcfs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entre o número de processos: ");
        int numberOfProcesses = scanner.nextInt(); //numero de processos (p) que serão executados

        int[] burstTimes = new int[numberOfProcesses]; //variavel do tempo de execução
        int[] turnaroundTimes = new int[numberOfProcesses]; //variavel do tempo de retorno
        int[] waitingTimes = new int[numberOfProcesses]; //variavel para guardar os tempos de esperas
        waitingTimes[0] = 0; // pre-definição (primeira variavel SEMPRE vai ter o tempo de espera 0)

        System.out.println("Informe a duração estimada de cada processo:");
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.print("\tP" + (i + 1) + ": "); //P1, P2, P3 (Nomenclatura)
            burstTimes[i] = scanner.nextInt(); //Vai guardar o tempo de execução na posição "i" do burstTimes)
        }

        // Calcula tempos de espera e médias
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        for (int i = 1; i < numberOfProcesses; i++) {
            waitingTimes[i] = waitingTimes[i - 1] + burstTimes[i - 1];
            totalWaitingTime += waitingTimes[i];
        }
        float averageWaitingTime = (float) totalWaitingTime / numberOfProcesses;

        for (int i = 0; i < numberOfProcesses; i++) {
            turnaroundTimes[i] = waitingTimes[i] + burstTimes[i];
            totalTurnaroundTime += turnaroundTimes[i];
        }
        float averageTurnaroundTime = (float) totalTurnaroundTime / numberOfProcesses;

        // Saída formatada
        System.out.println("\n****************************************************************");
        System.out.println("\tProcessos:");
        System.out.println("****************************************************************");
        System.out.println(" Processo\tTempo Estimado\tTempo de Espera\tTempo de Execução");
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println("\tP" + (i + 1) + "\t " + burstTimes[i] + "\t\t " + waitingTimes[i] + "\t\t " + turnaroundTimes[i]);
        }
        System.out.println("\n----------------------------------------------------------------");
        System.out.println("\nTempo de Espera Médio : " + averageWaitingTime);
        System.out.println("\nTempo Médio de Saída : " + averageTurnaroundTime + "\n");

        scanner.close();
    }
}