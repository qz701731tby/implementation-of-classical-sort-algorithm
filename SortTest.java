public class SortTest{
    public static void main(String[] args) {
        Integer[] array = {2,1,4,3,8,7,5,20,16,13,14,11,16,14};
        SortAlgorithm<Integer> sortAlgorithm = new SortAlgorithm<Integer>();
        sortAlgorithm.print_sort_type();
        sortAlgorithm.sort(array, "quick", "Asc");
    }
}