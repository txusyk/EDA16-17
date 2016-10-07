package lab1;

/**
 * Created by Josu on 25/09/2016.
 */
public class StringQuickSort {

    public static void sort(String[] array) {
        sort(array, 0, array.length);
    }

    private static void sort(String[] array, int fromIndex, int toIndex) {
        if (toIndex - fromIndex < 2) {
            return;
        }
        long timeStart = System.currentTimeMillis();
        sortImpl(array, fromIndex, toIndex, 0);
        long timeTotal = (System.currentTimeMillis() - timeStart);
        System.out.println("\t\t --- Elapsed time to order the actor list --- : " + (int) timeTotal / 1000 + "sec, " + timeTotal * 1000 + "ms\n");
    }

    private static void sortImpl(String[] array, int fromIndex, int toIndex, int stringLength) throws NullPointerException {



        int rangeLength = toIndex - fromIndex;

        if (rangeLength < 2) {
            return;
        }

        int finger = fromIndex;

        // Put all strings of length 'stringLength' to the beginning of the
        // requested sort range.
        for (int index = fromIndex; index < toIndex; ++index) {
            String current = array[index];
            if (current.length() == stringLength) {
                String tmp = array[finger];
                array[finger] = current;
                array[index] = tmp;
                ++finger;
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