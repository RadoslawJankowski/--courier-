package routs.currentRout;

import routs.Routs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinishedRoutsList {

    private static FinishedRoutsList instance;

    public static synchronized FinishedRoutsList getInstanceOfFinishedRoutsList(){
        if (instance == null){
            instance = new FinishedRoutsList();
        }
        return instance;
    }

    private final List<Routs> finishedRoutsList = new ArrayList<>();

    public List<Routs> getFinishedRoutsList() {
        return finishedRoutsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinishedRoutsList that = (FinishedRoutsList) o;
        return Objects.equals(finishedRoutsList, that.finishedRoutsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finishedRoutsList);
    }
}
