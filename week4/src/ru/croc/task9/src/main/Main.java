package main;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Log {
        long time;
        String message;
        Log(String log) {
            String[] strings = log.split(" ");
            this.time = Long.parseLong(strings[0]);
            this.message = strings[1];
        }
    }

    static class LogsSearcher extends SimpleFileVisitor<Path> {
        List<Path> paths = new ArrayList<>();
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            String filePath = file.toString().toLowerCase();
            if (filePath.endsWith(".log") || (filePath.endsWith(".trace"))) {
                this.paths.add(file);
            }
            return FileVisitResult.CONTINUE;
        }
    }

    static boolean isAllItemsNull(Object[] objects) {
        for (Object object : objects) {
            if (object != null) {
                return false;
            }
        }
        return true;
    }

    static int getEarliestIndex(Log[] logs) {
        int index;
        for (index = 0; index < logs.length; index++) {
            if (logs[index] != null) {
                break;
            }
        }

        for (int i = index; i < logs.length; i++) {
            index = (logs[i] != null) && (logs[i].time < logs[index].time) ? i : index;
        }
        return index;
    }

    public static void main(String[] args) {
        LogsSearcher seacher = new LogsSearcher();
        try {
            Files.walkFileTree(Paths.get(args[0]), seacher);
        } catch (IOException e) {
            System.out.printf("Ошибка при поиске: %s\n", e.getMessage());
        }

        if (seacher.paths.size() == 0) {
            System.out.println("В папке отсутствуют логи.\n");
            return;
        }

        Scanner[] scanners = new Scanner[seacher.paths.size()];
        Log[] logs = new Log[scanners.length];
        for (int i = 0; i < seacher.paths.size(); i++) {
            try {
                scanners[i] = new Scanner(seacher.paths.get(i));
            } catch (IOException e) {
                System.out.printf("Не удалось открыть файл, ошибка: %s\n", e.getMessage());
                return;
            }

            logs[i] = new Log(scanners[i].nextLine());
        }

        while (!isAllItemsNull(logs)) {
            int index = getEarliestIndex(logs);
            System.out.printf("%d %s\n", logs[index].time, logs[index].message);
            logs[index] = scanners[index].hasNextLine() ? new Log(scanners[index].nextLine()) : null;
        }
    }
}