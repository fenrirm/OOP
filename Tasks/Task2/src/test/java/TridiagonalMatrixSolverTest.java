import model.TridiagonalMatrix;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TridiagonalMatrixSolverTest {

    @Test
    public void solve() throws InterruptedException {
        List<Double> a = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
        List<Double> c = new ArrayList<>(Arrays.asList(2.0, 2.0, 2.0, 2.0, 2.0));
        List<Double> b = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
        List<Double> f = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0));
        TridiagonalMatrix matrix = new TridiagonalMatrix(a, c, b, f);

        TridiagonalMatrixSolver solver = new TridiagonalMatrixSolver(matrix);

        List<Double> expectedResult = new ArrayList<>(Arrays.asList(0.5, 0.0, 0.5, 0.0, 0.5));
        List<Double> result = solver.execute();
        assertEquals(expectedResult, result);
    }
}