package Homework3;
import java.util.Random;

public class DanceOff {

    private static final String[] POTENTIAL_MOVES = new String[] {
        "L", "R", "U", "D"
    };

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        DanceMagic danceMagic = new DanceMagic();
        String[] generatedMoves = generateMoves();
        System.out.printf("Repeat moves after me...%n");
        String moves = danceMagic.getUserMoves(generatedMoves);
        System.out.printf("%nUser moves - %s%n", moves);
        String[] computerMoves = generateComputerMoves(generatedMoves);
        DanceCat challenger = new DanceCat(moves, generatedMoves);
        DanceCat incumbent = new DanceCat(computerMoves, generatedMoves);
        danceMagic.dance(challenger, incumbent);
    }

    private static String[] generateComputerMoves(String[] moves) {
        String[] generated = new String[moves.length];
        for (int i = 0; i < moves.length; i++) {
            String move = moves[i];
            int value = RANDOM.nextInt(100);
            if (value < DanceCat.getComputerLevel()) {
                generated[i] = move;
            } else {
                generated[i] = "";
            }
        }
        return generated;
    }

    private static String[] generateMoves() {
        String[] moves = new String[18];
        for (int i = 0; i < 18; i++) {
            int index = RANDOM.nextInt(POTENTIAL_MOVES.length);
            moves[i] = POTENTIAL_MOVES[index];
        }
        return moves;
    }

}
