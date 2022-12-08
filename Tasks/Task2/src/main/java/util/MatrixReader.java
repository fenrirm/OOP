package util;

import model.TridiagonalMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixReader {

    public static TridiagonalMatrix read(File inputFile){
        List<Double> lowerDiag = new ArrayList<>();
        List<Double> mainDiag = new ArrayList<>();
        List<Double> aboveDiag = new ArrayList<>();
        List<Double> freeVars = new ArrayList<>();

        try{
            Scanner scanner = new Scanner(inputFile);
            int i = 0;
            while (scanner.hasNextLine()){
                String[] vals = scanner.nextLine().split("\\s+");
                mainDiag.add(Double.parseDouble(vals[i]));
                freeVars.add(Double.parseDouble(vals[vals.length-1]));
                if(i > 0)
                    lowerDiag.add(Double.parseDouble(vals[i-1]));
                if(i < vals.length - 2)
                    aboveDiag.add(Double.parseDouble(vals[i+1]));
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception: "+ e.getMessage());
        }

        TridiagonalMatrix result = new TridiagonalMatrix(lowerDiag, mainDiag, aboveDiag, freeVars);
        if(!isTridiagonal(result))
            throw new IllegalArgumentException("Matrix is not tridiagonal");

        return result;
    }

    public static boolean isTridiagonal(TridiagonalMatrix matrix){
        List<Double> lower = matrix.getAbsLowerDiag();
        List<Double> above = matrix.getAbsUpperDiag();
        List<Double> main = matrix.getAbsMainDiag();

        if(main.get(0) < above.get(0) ||
                main.get(main.size() - 1) < lower.get(lower.size() - 1))
            return false;

        for (int i = 1; i < main.size() - 2; i++)
            if(main.get(i) < lower.get(i - 1) + above.get(i + 1))
                return false;

        return true;
    }
}
