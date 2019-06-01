import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MatrixRotation {

    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int r) {

        int totalLines = matrix.size();
        int totalColumns = matrix.get(0).size();

        //1. All numbers of matrix are equal or rotation number is multiple of sum of sides -> no need for rotation
        if (matrix.stream().distinct().count() <= 1) {
            matrix.stream().map(s -> s.toString().replaceAll("\\[|\\,|\\]", "")).forEach(System.out::println);
            return;
        }

        Integer[][] neoMatrix = new Integer[totalLines][totalColumns];

        Integer[][] arrayMatrix = matrix.stream()
                .map(arr -> arr.toArray(new Integer[0]  ))
                .toArray(Integer[][]::new);

        //Rotation by external matrix
        int numberOfExternalMatrix = totalLines < totalColumns ? totalLines / 2 : totalColumns / 2;

        int firstLine = 0;
        int firstColumn = 0;
        int lastLine = totalLines - 1;
        int lastColumn = totalColumns - 1;
        int numLines = totalLines;
        int numColumns = totalColumns;

        for (int i = 0; i < arrayMatrix.length; i++) {
            System.arraycopy(arrayMatrix[i], 0, neoMatrix[i], 0, arrayMatrix[i].length);
        }

        for (int countMatrix = numberOfExternalMatrix; countMatrix > 0; countMatrix--) {

            int sideSum = (numLines * 2) + ((numColumns - 2) * 2);

            for (int rotNumber = (r % sideSum); rotNumber > 0; rotNumber--) {

                for (int j = lastColumn; j >= firstColumn; j--) {
                    // <- 1st line and more columns available
                    if (j - 1 >= firstColumn) {
                        neoMatrix[firstLine][j - 1] = arrayMatrix[firstLine][j];
                    } else {
                        neoMatrix[firstLine + 1][j] = arrayMatrix[firstLine][j];
                    }

                    // -> Last line and more columns available
                    if (j + 1 <= lastColumn) {
                        neoMatrix[lastLine][j + 1] = arrayMatrix[lastLine][j];
                    } else {
                        neoMatrix[lastLine - 1][j] = arrayMatrix[lastLine][j];
                    }
                }

                for (int i = lastLine - 1; i > firstLine; i--) {
                    // \/ 1st column and more lines available
                    if (i + 1 <= lastLine) {
                        neoMatrix[i + 1][firstColumn] = arrayMatrix[i][firstColumn];
                    }

                    // /\ Last column and more lines available
                    if (i - 1 >= firstLine) {
                        neoMatrix[i - 1][lastColumn] = arrayMatrix[i][lastColumn];
                    }

                }

                for (int i = 0; i < neoMatrix.length; i++) {
                    System.arraycopy(neoMatrix[i], 0, arrayMatrix[i], 0, neoMatrix[i].length);
                }

            }

            firstLine++;
            firstColumn++;
            lastLine--;
            lastColumn--;
            numLines = numLines-2;
            numColumns = numColumns-2;
        }

        Arrays.stream(neoMatrix).map(Arrays::asList).map(s -> s.toString().replaceAll("\\[|\\,|\\]", "")).forEach(System.out::println);

        return;

    }

    public static void main(String[] args) {

        List<Integer> lines = new ArrayList<>();
        List<List<Integer>> matrix = new ArrayList<>();

        lines.add(1);
        lines.add(1);
        lines.add(1);
        lines.add(1);
        matrix.add(lines);
        matrix.add(lines);
        matrix.add(lines);
        matrix.add(lines);
        //matrixRotation(matrix, 11);

        lines = new ArrayList<>();
        matrix = new ArrayList<>();

        lines.add(1);
        lines.add(2);
        lines.add(3);
        lines.add(4);
        matrix.add(lines);
        lines = new ArrayList<>();
        lines.add(12);
        lines.add(20);
        lines.add(30);
        lines.add(5);
        matrix.add(lines);
        lines = new ArrayList<>();
        lines.add(11);
        lines.add(50);
        lines.add(40);
        lines.add(6);
        matrix.add(lines);
        lines = new ArrayList<>();
        lines.add(10);
        lines.add(9);
        lines.add(8);
        lines.add(7);
        matrix.add(lines);

        System.out.println();
        System.out.println();

        //matrixRotation(matrix, 13);

        try {
            InputStream inputStream = new FileInputStream("C:\\dev\\projects\\hackerRank\\test7");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String[] mnr = new String[0];

            mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");


            int m = Integer.parseInt(mnr[0]);

            int n = Integer.parseInt(mnr[1]);

            int r = Integer.parseInt(mnr[2]);

            List<List<Integer>> matrix2 = new ArrayList<>();

            IntStream.range(0, m).forEach(i -> {
                try {
                    matrix2.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            matrixRotation(matrix2, r);

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
