import lombok.NonNull;
import model.TridiagonalMatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TridiagonalMatrixSolver {
    private final TridiagonalMatrix matrix;

    private final ArrayList<Double> leftAlpha;
    private final ArrayList<Double> rightAlpha;
    private final ArrayList<Double> leftBeta;
    private final ArrayList<Double> rightBeta;

    private final int size;

    private final List<Double> result;

    TridiagonalMatrixSolver(@NonNull TridiagonalMatrix matrix){
        this.matrix = matrix;
        leftAlpha = new ArrayList<>(Collections.nCopies(matrix.getMainDiag().size(), 0D));
        leftBeta = new ArrayList<>(Collections.nCopies(matrix.getMainDiag().size(), 0D));
        rightAlpha = new ArrayList<>(Collections.nCopies(matrix.getMainDiag().size(), 0D));
        rightBeta = new ArrayList<>(Collections.nCopies(matrix.getMainDiag().size(), 0D));

        result = new ArrayList<>(Collections.nCopies(matrix.getMainDiag().size(), 0D));

        size = (int) Math.floor(matrix.getMainDiag().size());
    }

    public List<Double> execute() throws InterruptedException {
        calculateCoefficients();
        findMiddleResult();
        findAllResults();
        return result;
    }

    private void calculateCoefficients() throws InterruptedException {
        Thread left = new Thread(() -> {
            for(int i = 1; i <= Math.floor(matrix.getMainDiag().size())/2; i++){
                leftAlpha.set(i, calculateLeftAlpha(i));
                leftBeta.set(i, calculateLeftBeta(i));
            }
        });

        Thread right = new Thread(() -> {
            for (int i = size - 1; i >= size/2 - 1; i--) {
                rightAlpha.set(i, calculateRightAlpha(i));
                rightBeta.set(i, calculateRightBeta(i));
            }
        });

        left.start();
        right.start();

        left.join();
        right.join();
    }

    private Double calculateLeftAlpha(int i) {
        if(i == 0)
            return matrix.getMainDiag().get(0);
        else if(i == 1)
            return -matrix.getUpperDiag().get(0) / matrix.getMainDiag().get(0);

        return -matrix.getUpperDiag().get(i - 1) / (matrix.getMainDiag().get(i - 1) + matrix.getLowerDiag().get(i - 2) * leftAlpha.get(i - 1));
    }

    private Double calculateLeftBeta(int i) {
        if(i == 0)
            return matrix.getUpperDiag().get(0);
        else if(i == 1)
            return matrix.getFreeVars().get(0) / matrix.getMainDiag().get(0);

        return (matrix.getFreeVars().get(i - 1) - matrix.getLowerDiag().get(i - 2) * leftBeta.get(i - 1))
                / (matrix.getMainDiag().get(i - 1) + matrix.getLowerDiag().get(i - 2) * leftAlpha.get(i - 1));
    }

    private Double calculateRightAlpha(int i) {
        if(i == size - 1 )
            return -matrix.getLowerDiag().get(matrix.getLowerDiag().size() - 1) / matrix.getMainDiag().get(matrix.getMainDiag().size() - 1);

        return  -matrix.getLowerDiag().get(i - 1) / (matrix.getMainDiag().get(i) + matrix.getUpperDiag().get(i) * rightAlpha.get(i + 1));
    }

    private Double calculateRightBeta(int i) {
        if(i == size - 1 )
               return matrix.getFreeVars().get(matrix.getFreeVars().size() - 1) / matrix.getMainDiag().get(matrix.getMainDiag().size() - 1);

        return (matrix.getFreeVars().get(i) - matrix.getUpperDiag().get(i) * rightBeta.get(i + 1))
                / (matrix.getMainDiag().get(i) + matrix.getUpperDiag().get(i) * rightAlpha.get(i + 1));
    }

    private void findMiddleResult() {
        int middle = size/2;
        Double middleX = (leftAlpha.get(middle) * rightBeta.get(middle)
                + leftBeta.get(middle)) / (1 - leftAlpha.get(middle) * rightAlpha.get(middle));
        result.set(size/2-1, middleX);
    }

    public void findAllResults() throws InterruptedException {
        Thread left = new Thread(() -> {
            for (int i = size/2 - 2 ; i >= 0; i--) {
                result.set(i,
                        result.get(i + 1) * leftAlpha.get(i + 1) + leftBeta.get(i + 1));
            }
        });
        Thread right = new Thread(() -> {
            for (int i = size/2; i <= size - 1; i++) {
                result.set(i,
                        result.get(i - 1) * rightAlpha.get(i) + rightBeta.get(i));
            }
        });
        left.start();
        right.start();
        left.join();
        right.join();
    }
}