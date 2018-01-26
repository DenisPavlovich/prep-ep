package epam.pre.romanenko.task8.sequence;

import epam.pre.romanenko.task8.sequence.service.SequenceFinder;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner in = new Scanner(System.in);
        String path = "D:\\preprod\\preprod\\part2\\task8\\src\\main\\resources\\";

        SequenceFinder sequenceFinder = new SequenceFinder(4);
        while (true) {
            if (sequenceFinder.isReady()) {
                System.out.println(sequenceFinder.getResult());
                System.out.println("input new path");
                String fileName = in.nextLine();
                sequenceFinder.setPath(path.concat(fileName));
                sequenceFinder.startFinding();
            } else {
                Thread.sleep(150);
                if (!sequenceFinder.isReady()) {
                    System.out.printf("current length:%d...\n", sequenceFinder.getLength());
                }
            }
        }
    }
}
