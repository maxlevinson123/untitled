import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/brick_layout");
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }


        System.out.println(partOne(bricks));
        System.out.println(partTwo2DArray(bricks));
    }

    private static int partOne(ArrayList<Brick> bricks) {
        int total = 0;
        for (Brick b : bricks) {
            total += b.getEnd() - b.getStart();
        }
        return total;
    }

    private static int partTwo(ArrayList<Brick> bricks) {
        int max = 0;
        ArrayList<Integer> layout = new ArrayList<Integer>();
        int lastEnd = 0;
        for (Brick b : bricks) {
            if (b.getEnd() > lastEnd) lastEnd = b.getEnd();
        }
        for (int i = 0; i <= lastEnd; i++) {
            layout.add(0);
        }
        for (Brick b: bricks) {
            int height = 0;
            for (int i = b.getStart(); i <= b.getEnd(); i++) {
                if (layout.get(i) > height) height = layout.get(i);
            }
            for (int i = b.getStart(); i <= b.getEnd(); i++) {
                layout.set(i, height + 1);
            }
        }
        for (Integer i : layout) {
            if (i > max) max = i;
        }
        return max;
    }

    private static int partTwo2DArray(ArrayList<Brick> bricks) {
        int lastEnd = 0;
        for (Brick b : bricks) {
            if (b.getEnd() > lastEnd) lastEnd = b.getEnd();
        }
        int[][] layout = new int[bricks.size() + 1][lastEnd + 1];
        int max = 0;
        for (Brick b : bricks) {
            int height = 0;
            for (int i = b.getStart(); i <= b.getEnd(); i++) {
                for (int j = layout.length - 1; j >= 0; j--) {
                    if (layout[j][i] == 1) {
                        if (j > height) height = j;
                        break;
                    }
                }
            }
                for (int k = b.getStart(); k <= b.getEnd(); k ++) {
                    layout[height + 1][k] = 1;
                }
                if (height + 1 > max) max = height + 1;

        }
        return max;
    }

    public static ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        return fileData;
    }
}