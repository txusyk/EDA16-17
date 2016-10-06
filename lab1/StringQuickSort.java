package lab1;

import java.util.Arrays;
import java.util.Random;

/**
 * This class implements a Quicksort for strings.
 *
 * @author Rodion "rodde" Efremov
 * @version (Dec 17, 2015)
 */
public class StringQuickSort {

    private static final int ALPHABET_SIZE = 26;

    public static void sort(String[] array) {
        sort(array, 0, array.length);
    }

    public static void sort(String[] array, int fromIndex, int toIndex) {
        if (toIndex - fromIndex < 2) {
            return;
        }

        sortImpl(array, fromIndex, toIndex, 0);
    }

    private static void sortImpl(String[] array,
                                 int fromIndex,
                                 int toIndex,
                                 int stringLength) {
        int rangeLength = toIndex - fromIndex;

        if (rangeLength < 2) {
            return;
        }

        int finger = fromIndex;

        // Put all strings of length 'stringLength' to the beginning of the
        // requested sort range.
        for (int index = fromIndex; index < toIndex; ++index) {
            String current = array[index];

            try {
                if (current.length() == stringLength) {
                    String tmp = array[finger];
                    array[finger] = current;
                    array[index] = tmp;
                    ++finger;
                }
            } catch (NullPointerException e1) {

            }
        }

        fromIndex = finger;

        // Choose a pivot string by median.
        String probeLeft = array[fromIndex];
        String probeRight = array[toIndex - 1];
        String probeMiddle = array[fromIndex + rangeLength >> 1];

        String pivot = median(probeLeft, probeMiddle, probeRight);

        // Process strings S for which S[stringLength] < X[stringLength].
        for (int index = fromIndex; index < toIndex; ++index) {
            String current = array[index];

            if (current.charAt(stringLength) < pivot.charAt(stringLength)) {
                String tmp = array[finger];
                array[finger] = current;
                array[index] = tmp;
                ++finger;
            }
        }

        sortImpl(array, fromIndex, finger, stringLength);

        fromIndex = finger;
        int processed = 0;

        for (int index = fromIndex; index < toIndex; ++index) {
            String current = array[index];

            if (current.charAt(stringLength) == pivot.charAt(stringLength)) {
                String tmp = array[finger];
                array[finger] = current;
                array[index] = tmp;
                ++finger;
            }
        }

        sortImpl(array, fromIndex, finger, stringLength + 1);
        sortImpl(array, finger, toIndex, stringLength);
    }

    private static String median(String a, String b, String c) {
        if (a.compareTo(b) <= 0) {
            if (c.compareTo(a) <= 0) {
                return a;
            }

            return b.compareTo(c) <= 0 ? b : c;
        }

        if (c.compareTo(b) <= 0) {
            return b;
        }

        return a.compareTo(c) <= 0 ? a : c;
    }

}