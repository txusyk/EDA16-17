package lab1;

/**
 * Created by Josu on 03/10/2016.
 */
public class NormalizeStrings extends Thread {

    private static NormalizeStrings myNormalizeString;

    public static NormalizeStrings getMyNormalizeString() {
        if (myNormalizeString == null) {
            myNormalizeString = new NormalizeStrings();
        }
        return myNormalizeString;
    }

    public void run(String[] pLine) {

        for (int i = 0; i < pLine.length; i++) {
            this.waitXseconds(0);
            if (pLine[i].contains("Ã¡")) {
                pLine[i].replaceAll("Ã¡", "a");
            } else if (pLine[i].contains("Ã©")) {
                pLine[i].replaceAll("Ã©", "e");
            } else if (pLine[i].contains("Ã­")) {
                pLine[i].replaceAll("Ã­", "i");
            } else if (pLine[i].contains("Ã³")) {
                pLine[i].replaceAll("Ã³", "o");
            } else if (pLine[i].contains("Ãº")) {
                pLine[i].replaceAll("Ãº", "u");
            } else if (pLine[i].contains("Ã�")) {
                pLine[i].replaceAll("Ã�", "A");
            } else if (pLine[i].contains("Ã‰")) {
                pLine[i].replaceAll("Ã‰", "E");
            } else if (pLine[i].contains("Ã�")) {
                pLine[i].replaceAll("Ã�", "I");
            } else if (pLine[i].contains("Ã“")) {
                pLine[i].replaceAll("Ã“", "O");
            } else if (pLine[i].contains("Ãš")) {
                pLine[i].replaceAll("Ãš", "U");
            } else if (pLine[i].contains("Ã§")) {
                pLine[i].replaceAll("Ã§", "c");
            } else if (pLine[i].contains("Ã")) {
                pLine[i].replaceAll("Ã", "a");
            } else if (pLine[i].contains("Ã¨")) {
                pLine[i].replaceAll("Ã¨", "e");
            } else if (pLine[i].contains("Ã¬")) {
                pLine[i].replaceAll("Ã¬", "i");
            } else if (pLine[i].contains("Ã²")) {
                pLine[i].replaceAll("Ã²", "o");
            } else if (pLine[i].contains("Ã¹")) {
                pLine[i].replaceAll("Ã¹", "u");
            } else if (pLine[i].contains("Ã€")) {
                pLine[i].replaceAll("Ã€", "A");
            } else if (pLine[i].contains("Ãˆ")) {
                pLine[i].replaceAll("Ãˆ", "E");
            } else if (pLine[i].contains("ÃŒ")) {
                pLine[i].replaceAll("ÃŒ", "I");
            } else if (pLine[i].contains("Ã’")) {
                pLine[i].replaceAll("Ã’", "O");
            } else if (pLine[i].contains("Ã™")) {
                pLine[i].replaceAll("Ã™", "U");
            } else if (pLine[i].contains("Ã‡")) {
                pLine[i].replaceAll("Ã‡", "C");
            } else if (pLine[i].contains("�")) {
                pLine[i].replaceAll("�", "A");
            } else if (pLine[i].contains("�s")) {
                pLine[i].replaceAll("�s", "Os");
            }
        }
    }

    private void waitXseconds(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}