import model.TridiagonalMatrix;
import org.junit.Test;
import util.MatrixReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MatrixReaderTest {
    @Test
    public void read_EverythingIsOk(){
        TridiagonalMatrix matrix = MatrixReader.read(new File("src/test/resources/testMatrix.txt"));

        assertEquals(List.of(2.0, 5.0, 4.0, 66.0), matrix.getMainDiag());
        assertEquals(List.of(1.0, 3.0, 1.0), matrix.getLowerDiag());
        assertEquals(List.of(1.0, 1.0, 1.0), matrix.getUpperDiag());
        assertEquals(List.of(6.0, 2.0, 1.0, 1.0), matrix.getFreeVars());
    }

    @Test
    public void isTridiagonal_EverythingIsOkay(){
        List<Double> validA = new ArrayList<>(Arrays.asList(1.0, 3.0, 1.0, 1.0));
        List<Double> validC = new ArrayList<>(Arrays.asList(2.0, 2.0, 4.0, 2.0, 3.0));
        List<Double> validB = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
        List<Double> validF = new ArrayList<>(Arrays.asList(1.0, 2.0, 1.0, 1.0, 1.0));

        TridiagonalMatrix validMatrix = new TridiagonalMatrix(validA, validC, validB, validF);
        assertTrue(MatrixReader.isTridiagonal(validMatrix));

        List<Double> notValidB = new ArrayList<>(Arrays.asList(10.0, 10.0, 10.0, 10.0));
        TridiagonalMatrix notValidMatrix = new TridiagonalMatrix(validA, validC, notValidB, validF);
        assertFalse(MatrixReader.isTridiagonal(notValidMatrix));
    }
}