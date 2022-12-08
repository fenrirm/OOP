package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class TridiagonalMatrix {

    private List<Double> lowerDiag;
    private List<Double> mainDiag;
    private List<Double> upperDiag;
    private List<Double> freeVars;

    public List<Double> getAbsLowerDiag(){
        return lowerDiag.stream().map(Math::abs).collect(Collectors.toList());
    }

    public List<Double> getAbsMainDiag(){
        return mainDiag.stream().map(Math::abs).collect(Collectors.toList());
    }

    public List<Double> getAbsUpperDiag(){
        return upperDiag.stream().map(Math::abs).collect(Collectors.toList());
    }
}
