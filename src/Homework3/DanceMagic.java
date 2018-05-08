package Homework3;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class DanceMagic {

    /**
     * @return the captured moves from the user
     */
    public String getUserMoves(final String[] expected) {
        final AtomicReference<CountDownLatch> next = new AtomicReference<CountDownLatch>();
        final CountDownLatch latch = new CountDownLatch(1);
        final StringBuilder buffer = new StringBuilder();
        final Thread captureThread = new Thread(new Runnable() {
            @Override public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        int read = System.in.read();
                        if (read == -1) {
                            latch.countDown();
                            return;
                        } else if (read > 0) {
                            if ((read == '\n') || (read == '\r')) {
                                continue;
                            } else {
                                buffer.append((char) read);
                                next.get().countDown();
                            }
                        }
                    } catch (IOException ioe) {
                        latch.countDown();
                        return;
                    }
                }
            }
        });
        captureThread.setDaemon(true);
        final Thread outputThread = new Thread(new Runnable() {
            @Override public void run() {
                for (String move : expected) {
                    if (latch.getCount() > 0) {
                        next.set(new CountDownLatch(1));
                        System.out.printf("Move - %s%n", move);
                        System.out.printf("Copy - ");
                    } else {
                        return;
                    }
                    try {
                        next.get().await();
                    } catch (InterruptedException ie) {
                        return; // done
                    }
                }
                latch.countDown();
            }
        });
        outputThread.setDaemon(true);
        Thread waitThread = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(12000L);
                    latch.countDown();
                    captureThread.interrupt();
                } catch (InterruptedException ie) {
                    // do nothing, thread is dying
                }
            }
        });
        waitThread.setDaemon(true);
        outputThread.start();
        captureThread.start();
        waitThread.start();
        try {
            latch.await();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
        outputThread.interrupt();
        return buffer.toString();
    }

    public void dance(DanceCat challenger, DanceCat incumbent) {
        if ((challenger == null) || (incumbent == null) || (challenger.getDanceMoves() == null)
                || (incumbent.getDanceMoves() == null) || (challenger.getDanceMoves().length != 18)
                || (challenger.getDanceMoves().length != incumbent.getDanceMoves().length)) {
            printError();
            return;
        }
        DanceMove[] challengerMoves = challenger.getDanceMoves();
        DanceMove[] incumbentMoves = incumbent.getDanceMoves();
        int score = 0; // see return of performMove(DanceMove, DanceMove)
        for (int i = 0; i < incumbentMoves.length; i++) {
            score += performMove(i, challengerMoves[i], incumbentMoves[i]);
        }
        if (score > 0) {
            printWin();
        } else if (score < 0) {
            printLose();
        } else {
            printTie();
        }
        int challengerCorrectMoves = challenger.getNumberOfCorrectMoves();
        int incumbentCorrectMoves = incumbent.getNumberOfCorrectMoves();
        printCorrectMoves(challenger.getName(), challengerCorrectMoves, incumbent.getName(), incumbentCorrectMoves);
    }

    private int performMove(int sequence, DanceMove challenger, DanceMove incumbent) {
        int score = (challenger.correctMove() ? incumbent.correctMove() ? 0 : 1 : incumbent.correctMove() ? -1 : 0);
        print(getMoveLocation(sequence), challenger.correctMove(), incumbent.correctMove());
        try {
            Thread.sleep(90L);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
        return score;
    }

    private String getMoveLocation(int sequence) {
        return String.format("cat-0%s%d.txt", (sequence < 10 ? "0" : ""), sequence);
    }

    private void printCorrectMoves(String challenger, int challengerCorrectMoves, String incumbent, int incumbentCorrectMoves) {
        String terminal = System.getenv("TERM");
        boolean withinTerminal = (terminal != null);
        boolean challengerBetter = (challengerCorrectMoves > incumbentCorrectMoves);
        boolean incumbentBetter = (incumbentCorrectMoves > challengerCorrectMoves);
        boolean tied = (challengerCorrectMoves == incumbentCorrectMoves);
        String decorationEnd = (withinTerminal ? "\u001B[0m" : "");
        String challengerDecorated = (withinTerminal ? String.format("\u001B[1;3%sm", (challengerBetter ? "2" : tied ? "7" : "1")) : "");
        String incumbentDecorated = (withinTerminal ? String.format("\u001B[1;3%sm", (incumbentBetter ? "2" : tied ? "7" : "1")) : "");
        String bold = (withinTerminal ? "\u001B[1m" : "");
        System.out.printf("%s%s%s (%syou%s) performed %s%d%s moves correctly. %s%s%s performed %s%d%s moves %scorrectly%s.%n",
                challengerDecorated, challenger, decorationEnd, bold, decorationEnd, challengerDecorated, challengerCorrectMoves, decorationEnd,
                incumbentDecorated, incumbent, decorationEnd, incumbentDecorated, incumbentCorrectMoves, decorationEnd, bold, decorationEnd);
    }

    private void print(String location, boolean leftGreen, boolean rightGreen) {
        String terminal = System.getenv("TERM");
        boolean withinTerminal = (terminal != null);
        String separator = "          ";
        String good = "2", bad = "1";
        String left = (leftGreen ? good : bad);
        String right = (rightGreen ? good : bad);
        String leftDecoration = (withinTerminal ? String.format("\u001B[1;3%sm", left) : "");
        String rightDecoration = (withinTerminal ? String.format("\u001B[1;3%sm", right) : "");
        String decorationEnd = (withinTerminal ? "\u001B[0m": "");
        try {
            List<String> lines = Files.readAllLines(Paths.get(location), Charset.forName("ASCII"));
            for (String line : lines) {
                System.out.printf("%s%s%s%s%s%s%s%s%n", separator, leftDecoration, pad(line), decorationEnd, separator,
                        rightDecoration, pad(line), decorationEnd);
            }
        } catch (IOException ioe) {
            System.err.printf("Error - %s%n", ioe.getMessage());
        }
    }

    private void printWin() {
        printMessage("./cat-win.txt", "6");
    }

    private void printLose() {
        printMessage("./cat-lose.txt", "3");
    }

    private void printTie() {
        printMessage("./cat-tie.txt", "7");
    }

    private void printError() {
        printMessage("./cat-error.txt", "1");
    }

    private void printMessage(String location, String color) {
        String terminal = System.getenv("TERM");
        boolean withinTerminal = (terminal != null);
        String separator = "          ";
        String decoration = (withinTerminal ? String.format("\u001B[1;3%sm", color) : "");
        String decorationEnd = (withinTerminal ? "\u001B[0m" : "");
        try {
            List<String> lines = Files.readAllLines(Paths.get(location), Charset.forName("ASCII"));
            for (String line : lines) {
                System.out.printf("%s%s%s%s%n", separator, decoration, pad(line), decorationEnd);
            }
        } catch (IOException ioe) {
            System.err.printf("Error - %s%n", ioe.getMessage());
        }
    }

    private String pad(String line) {
        StringBuilder buffer = new StringBuilder(line);
        while (buffer.length() < 40) {
            buffer.append(' ');
        }
        return buffer.toString();
    }
}
