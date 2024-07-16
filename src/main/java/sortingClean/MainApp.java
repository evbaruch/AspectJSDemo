//package sortingClean;
//
//import jakarta.enterprise.inject.Produces;
//import org.jboss.weld.environment.se.Weld;
//import org.jboss.weld.environment.se.WeldContainer;
//
//import java.util.Random;
//
//// TODO: Add java classes (in separate files for annotations and aspects)
//public class MainApp {
//
//    public static void main(String[] args) {
//        // TODO: Change this line to initialize an injected AlgorithmRunner
//        AlgorithmRunner algorithmRunner = new AlgorithmRunner();
//
//        algorithmRunner.runAlgorithms();
//    }
//    // TODO: Add producers
//}

package sortingClean;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.Random;

// TODO: Add java classes (in separate files for annotations and aspects) - v
public class MainApp {

    static WeldContainer container = new Weld().initialize(); //for injection

    public static void main(String[] args) {
        // TODO: Change this line to initialize an injected AlgorithmRunner - v
        AlgorithmRunner algorithmRunner = container.select(AlgorithmRunner.class).get();
        // AlgorithmRunner algorithmRunner = new AlgorithmRunner();
        algorithmRunner.runAlgorithms();
    }
    // TODO: Add producers - v

    @Produces
    public  @Named("quadraticAlgorithm")SortingAlgorithm<Integer> getQuadraticAlgorithm(){
        return container.select(InsertionSort.class).get();
    }

    @Produces
    public @Named("nlognAlgorithm")SortingAlgorithm<Integer> getNlognAlgorithm(){
        return container.select(MergeSort.class).get();
    }

    @Produces
    public @Named("randomAlgorithm1")SortingAlgorithm<Integer> getRandomAlgorithm1(){
        return makeRandomSortingAlgorithm();
    }

    @Produces
    public @Named("randomAlgorithm2")SortingAlgorithm<Integer> getRandomAlgorithm2(){
        return makeRandomSortingAlgorithm();
    }

    @Produces
    public @Named("int")int getNumberOfElements() {
        return 1000;
    }

    private static SortingAlgorithm<Integer> makeRandomSortingAlgorithm() {
        Random random = new Random(System.currentTimeMillis());
        switch (random.nextInt(4)) {
            case 0: return container.select(QuickSort.class).get();
            case 1: return container.select(MergeSort.class).get();
            case 2: return container.select(BubbleSort.class).get();
            case 3: return container.select(InsertionSort.class).get();
            default: return null; // Fallback, should not happen
        }
    }
}
