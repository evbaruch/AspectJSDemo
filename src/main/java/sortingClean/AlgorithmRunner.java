package sortingClean;
import java.util.Random;

// TODO: Add dependency injection and annotations to this file
public class AlgorithmRunner {
    SortingAlgorithm<Integer> quadraticAlgorithm = new BubbleSort();
    SortingAlgorithm<Integer> nlognAlgorithm = new QuickSort();
    SortingAlgorithm<Integer> randomAlgorithm1 = makeRandomSortingAlgorithm();
    SortingAlgorithm<Integer> randomAlgorithm2 = makeRandomSortingAlgorithm();
    int numberOfElements = 10000;
    public void runAlgorithms(){
        Random rand = new Random();
        Integer[] ints = rand.ints(1,Integer.MAX_VALUE)
                .distinct()
                .limit(numberOfElements)
                .boxed()
                .toArray(Integer[]::new);
        Integer[] intsClone = ints.clone();
        quadraticAlgorithm.sort(intsClone);
        intsClone = ints.clone();
        nlognAlgorithm.sort(intsClone);
        intsClone = ints.clone();
        randomAlgorithm1.sort(intsClone);
        intsClone = ints.clone();
        randomAlgorithm2.sort(intsClone);
    }

    private static SortingAlgorithm<Integer> makeRandomSortingAlgorithm(){
        Random random = new Random(System.currentTimeMillis());
        SortingAlgorithm<Integer> sortAlg= null;
        switch (random.nextInt(4)){
            case 0: sortAlg = new QuickSort();
                break;
            case 1: sortAlg = new MergeSort();
                break;
            case 2: sortAlg = new BubbleSort();
                break;
            case 3: sortAlg = new InsertionSort();
        }
        return sortAlg;
    }
}
