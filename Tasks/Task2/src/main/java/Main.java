import model.TridiagonalMatrix;
import util.MatrixReader;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TridiagonalMatrix tridiagonalMatrix = MatrixReader.read( new File("src/main/resources/data.txt"));
        TridiagonalMatrixSolver solver = new TridiagonalMatrixSolver(tridiagonalMatrix);

        List<Double> result = solver.execute();
        result.forEach(System.out::println);
    }
}
