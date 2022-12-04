package tasks;

import structs.day4.SectionPair;
import structs.day4.SectionRange;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

/**
 * <pre>
 * --- Day 4: Camp Cleanup ---
 * Space needs to be cleared before the last supplies can be unloaded from the ships, and so several Elves have been assigned the job of cleaning up sections of the camp. Every section has a unique ID number, and each Elf is assigned a range of section IDs.
 *
 * However, as some of the Elves compare their section assignments with each other, they've noticed that many of the assignments overlap. To try to quickly find overlaps and reduce duplicated effort, the Elves pair up and make a big list of the section assignments for each pair (your puzzle input).
 *
 * For example, consider the following list of section assignment pairs:
 *
 * 2-4,6-8
 * 2-3,4-5
 * 5-7,7-9
 * 2-8,3-7
 * 6-6,4-6
 * 2-6,4-8
 * For the first few pairs, this list means:
 *
 * Within the first pair of Elves, the first Elf was assigned sections 2-4 (sections 2, 3, and 4), while the second Elf was assigned sections 6-8 (sections 6, 7, 8).
 * The Elves in the second pair were each assigned two sections.
 * The Elves in the third pair were each assigned three sections: one got sections 5, 6, and 7, while the other also got 7, plus 8 and 9.
 * This example list uses single-digit section IDs to make it easier to draw; your actual list might contain larger numbers. Visually, these pairs of section assignments look like this:
 *
 * .234.....  2-4
 * .....678.  6-8
 *
 * .23......  2-3
 * ...45....  4-5
 *
 * ....567..  5-7
 * ......789  7-9
 *
 * .2345678.  2-8
 * ..34567..  3-7
 *
 * .....6...  6-6
 * ...456...  4-6
 *
 * .23456...  2-6
 * ...45678.  4-8
 * Some of the pairs have noticed that one of their assignments fully contains the other. For example, 2-8 fully contains 3-7, and 6-6 is fully contained by 4-6. In pairs where one assignment fully contains the other, one Elf in the pair would be exclusively cleaning sections their partner will already be cleaning, so these seem like the most in need of reconsideration. In this example, there are 2 such pairs.
 *
 * In how many assignment pairs does one range fully contain the other?
 * </pre>
 */
public class Day4 {
    public static void run() throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader("C:\\dev\\AdventOfCode\\resources\\input_day4"));

        String line;

        ArrayList<SectionPair> sectionPairs = new ArrayList<>();
        //noinspection ForLoopReplaceableByWhile (I like the go style of loops ;))
        for (;((line = lnr.readLine()) != null);){
            String[] sectionPairStrArr = line.split(",");
            String[] section1StrArr = sectionPairStrArr[0].split("-");
            String[] section2StrArr = sectionPairStrArr[1].split("-");

            SectionPair sectionPair = new SectionPair(
                    new SectionRange(Integer.parseInt(section1StrArr[0]), Integer.parseInt(section1StrArr[1])),
                    new SectionRange(Integer.parseInt(section2StrArr[0]), Integer.parseInt(section2StrArr[1]))
            );
            sectionPairs.add(sectionPair);
        }

        int fullyContainsCnt = 0;
        for (SectionPair sectionPair : sectionPairs) {
            if (fullyContains(sectionPair)){
                fullyContainsCnt++;
            }
        }

        int overlapsCnt = 0;
        for (SectionPair sectionPair : sectionPairs) {
            if (overlaps(sectionPair)){
                overlapsCnt++;
            }
        }

        System.out.printf("The fully contains count is: %d%n", fullyContainsCnt);
        System.out.printf("The overlap count is: %d%n", overlapsCnt);
    }

    private static boolean fullyContains(SectionPair pair){
        SectionRange sectionRange1 = pair.range1();
        SectionRange sectionRange2 = pair.range2();

        return fullyContainsHelper(
                sectionRange1.lower(),
                sectionRange1.upper(),
                sectionRange2.lower(),
                sectionRange2.upper()
        );
    }

    private static boolean fullyContainsHelper(int lower1, int upper1, int lower2, int upper2){
        return lower2 >= lower1 && upper2 <= upper1 || lower1 >= lower2 && upper1 <= upper2;
    }

    private static boolean overlaps(SectionPair pair) {
        SectionRange sectionRange1 = pair.range1();
        SectionRange sectionRange2 = pair.range2();

        return overlapsHelper(
                sectionRange1.lower(),
                sectionRange1.upper(),
                sectionRange2.lower(),
                sectionRange2.upper()
        );
    }

    private static boolean overlapsHelper(int lower1, int upper1, int lower2, int upper2) {
        int maxesOfMins = Math.max(lower1, lower2);
        int minsOfMaxes = Math.min(upper1, upper2);

        return maxesOfMins <= minsOfMaxes;
    }
}
