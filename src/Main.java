import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entre o número de processos: ");
        int numProcesses = scanner.nextInt();

        int[] burstTimes = new int[numProcesses];
        int[] processNumbers = new int[numProcesses];
        int[] waitingTimes = new int[numProcesses];
        int[] turnaroundTimes = new int[numProcesses];

        System.out.println("\nInforme o tempo de execução estimado:");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("P[" + (i + 1) + "]: ");
            burstTimes[i] = scanner.nextInt();
            processNumbers[i] = i + 1; // Número do processo
        }

        sjfScheduler(processNumbers, burstTimes, waitingTimes, turnaroundTimes);

        printResults(processNumbers, burstTimes, waitingTimes, turnaroundTimes);

        scanner.close();
    }

    public static void sjfScheduler(int[] processNumbers, int[] burstTimes, int[] waitingTimes, int[] turnaroundTimes) {
        int n = processNumbers.length;

        // Ordena os processos com base no tempo de execução
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (burstTimes[i] > burstTimes[j]) {
                    // Troca os tempos de execução
                    int temp = burstTimes[i];
                    burstTimes[i] = burstTimes[j];
                    burstTimes[j] = temp;

                    // Troca os números dos processos correspondentes
                    temp = processNumbers[i];
                    processNumbers[i] = processNumbers[j];
                    processNumbers[j] = temp;
                }
            }
        }

        // Calcula os tempos de espera
        waitingTimes[0] = 0;
        for (int i = 1; i < n; i++) {
            waitingTimes[i] = burstTimes[i - 1] + waitingTimes[i - 1];
        }

        // Calcula os tempos de retorno (Turnaround Time)
        for (int i = 0; i < n; i++) {
            turnaroundTimes[i] = burstTimes[i] + waitingTimes[i];
        }
    }

    public static void printResults(int[] processNumbers, int[] burstTimes, int[] waitingTimes, int[] turnaroundTimes) {
        System.out.println("\nPROC.\t Execução\t Espera\t Saída");
        for (int i = 0; i < processNumbers.length; i++) {
            System.out.println("P" + processNumbers[i] + "\t\t\t " + burstTimes[i] + "\t\t " + waitingTimes[i] + "\t\t " + turnaroundTimes[i]);
        }
    }
} 