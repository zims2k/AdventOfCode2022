package tasks;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * <pre>
 * --- Day 5: Supply Stacks ---
 * The expedition can depart as soon as the final supplies have been unloaded from the ships. Supplies are stored in stacks of marked crates, but because the needed supplies are buried under many other crates, the crates need to be rearranged.
 *
 * The ship has a giant cargo crane capable of moving crates between stacks. To ensure none of the crates get crushed or fall over, the crane operator will rearrange them in a series of carefully-planned steps. After the crates are rearranged, the desired crates will be at the top of each stack.
 *
 * The Elves don't want to interrupt the crane operator during this delicate procedure, but they forgot to ask her which crate will end up where, and they want to be ready to unload them as soon as possible so they can embark.
 *
 * They do, however, have a drawing of the starting stacks of crates and the rearrangement procedure (your puzzle input). For example:
 *
 *     [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 *
 * move 1 from 2 to 1
 * move 3 from 1 to 3
 * move 2 from 2 to 1
 * move 1 from 1 to 2
 * In this example, there are three stacks of crates. Stack 1 contains two crates: crate Z is on the bottom, and crate N is on top. Stack 2 contains three crates; from bottom to top, they are crates M, C, and D. Finally, stack 3 contains a single crate, P.
 *
 * Then, the rearrangement procedure is given. In each step of the procedure, a quantity of crates is moved from one stack to a different stack. In the first step of the above rearrangement procedure, one crate is moved from stack 2 to stack 1, resulting in this configuration:
 *
 * [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 * In the second step, three crates are moved from stack 1 to stack 3. Crates are moved one at a time, so the first crate to be moved (D) ends up below the second and third crates:
 *
 *         [Z]
 *         [N]
 *     [C] [D]
 *     [M] [P]
 *  1   2   3
 * Then, both crates are moved from stack 2 to stack 1. Again, because crates are moved one at a time, crate C ends up below crate M:
 *
 *         [Z]
 *         [N]
 * [M]     [D]
 * [C]     [P]
 *  1   2   3
 * Finally, one crate is moved from stack 1 to stack 2:
 *
 *         [Z]
 *         [N]
 *         [D]
 * [C] [M] [P]
 *  1   2   3
 * The Elves just need to know which crate will end up on top of each stack; in this example, the top crates are C in stack 1, M in stack 2, and Z in stack 3, so you should combine these together and give the Elves the message CMZ.
 *
 * After the rearrangement procedure completes, what crate ends up on top of each stack?
 * </pre>
 */
@SuppressWarnings("rawtypes")
public class Day5 {

    private static final String KW_MOVE = "move";
    private static final String KW_FROM = "from";
    private static final String KW_TO = "to";

    private static final int CRATE_MOVER_9000 = 9000;
    private static final int CRATE_MOVER_9001 = 9001;

    public static void run() throws IOException {
        String fileName = "C:\\dev\\AdventOfCode\\resources\\input_day5_reorder_operations";

        Stack[] stacks = initStacks();
        LineNumberReader lnr = getLnr(fileName);
        StringBuilder sb = getMessage(stacks, lnr, CRATE_MOVER_9000);
        System.out.printf("The resulting message of all top crates with CrateMover 9000 is: %s%n", sb);

        stacks = initStacks();
        lnr = getLnr(fileName);
        sb = getMessage(stacks, lnr, CRATE_MOVER_9001);
        System.out.printf("The resulting message of all top crates with CrateMover 9001 is: %s%n", sb);
    }

    private static StringBuilder getMessage(Stack[] stacks, LineNumberReader lnr, int crateMover) throws IOException {
        doRearrangement(stacks, lnr, crateMover);

        StringBuilder sb = new StringBuilder();
        for (Stack stack : stacks) {
            Character peek = (Character) stack.peek();
            sb.append(peek);
        }
        return sb;
    }

    private static LineNumberReader getLnr(String fileName) throws FileNotFoundException {
        return new LineNumberReader(new FileReader(fileName));
    }

    private static void doRearrangement(Stack[] stacks, LineNumberReader lnr, int crateMover) throws IOException {
        String line;
        //noinspection ForLoopReplaceableByWhile (I like the go style of loops ;))
        for (; ((line = lnr.readLine()) != null);){
            int moveCnt = Integer.parseInt(line.substring(
                    line.indexOf(KW_MOVE) + KW_MOVE.length(),
                    line.indexOf(KW_FROM)
            ).trim());
            int fromIdx = Integer.parseInt(line.substring(
                    line.indexOf(KW_FROM) + KW_FROM.length(),
                    line.indexOf(KW_TO)
            ).trim());
            int toIdx = Integer.parseInt(line.substring(
                    line.indexOf(KW_TO) + KW_TO.length()
            ).trim());

            if (crateMover == CRATE_MOVER_9000) {
                doMoveOperation9000(stacks, moveCnt, fromIdx - 1, toIdx - 1);
            }
            else if (crateMover == CRATE_MOVER_9001){
                doMoveOperation9001(stacks, moveCnt, fromIdx - 1, toIdx - 1);
            }
            else{
                throw new IllegalArgumentException("Invalid crane operator");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void doMoveOperation9000(Stack[] stacks, int moveCnt, int fromIdx, int toIdx){
        for (int i = 0; i < moveCnt; i++) {
            stacks[toIdx].push(stacks[fromIdx].pop());
        }
    }

    @SuppressWarnings("unchecked")
    private static void doMoveOperation9001(Stack[] stacks, int moveCnt, int fromIdx, int toIdx){
        Stack tmpStack = new Stack();

        for (int i = 0; i < moveCnt; i++) {
            tmpStack.push(stacks[fromIdx].pop());
        }

        while (!tmpStack.isEmpty()){
            stacks[toIdx].push(tmpStack.pop());
        }
    }

    private static Stack[] initStacks() throws IOException {
        final LineNumberReader lnr = getLnr("C:\\dev\\AdventOfCode\\resources\\input_day5_stack");

        String line;

        final ArrayList<Stack<Character>> stacks = new ArrayList<>();
        //noinspection ForLoopReplaceableByWhile (I like the go style of loops ;))
        for (;((line = lnr.readLine()) != null);){
            int length = line.length();
            int numStacks = (length + 1) / 4;

            for (int i = 0; i < numStacks; i++) {
                if (numStacks > stacks.size()){
                    for (int j = 0; j < (numStacks - stacks.size()); j++) {
                        stacks.add(new Stack<>());
                    }
                }
            }

            for (int i = 0; i < stacks.size(); i++) {
                char crate = line.charAt(4 * i + 1);
                if (crate != ' '){
                    stacks.get(i).push(crate);
                }
            }
        }

        for (int i = 0; i < stacks.size(); i++) {
            Stack<Character> stack = stacks.get(i);
            Stack<Character> newStack = new Stack<>();
            while (!stack.isEmpty()){
                newStack.push(stack.pop());
            }

            stacks.set(i, newStack);
        }

        return stacks.toArray(new Stack[0]);
    }
}
