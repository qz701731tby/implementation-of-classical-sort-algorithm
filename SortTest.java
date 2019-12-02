public class SortTest{
    public static void main(String[] args) {
        Integer[] int_array = {2,1,4,3,8,7,5,20,16,13,14,11,16,14};
        String[] str_array = {"a","b","z","k","e","d","f","m","g","o","q","n"};
        SortAlgorithm<Integer> sortAlgorithm_int = new SortAlgorithm<Integer>();
        sortAlgorithm_int.print_sort_type();
        sortAlgorithm_int.sort(int_array, "insert", "Desc");

        SortAlgorithm<String> sortAlgorithm_str = new SortAlgorithm<String>();
        sortAlgorithm_str.sort(str_array, "insert", "Asc");
        
        int[] array = {2,1,4,3,8,7,5,20,16,13,14,11,16,14};
        sortAlgorithm_int.bucketSort(array, 5, "Asc");
    }
}