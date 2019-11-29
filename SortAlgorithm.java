import java.lang.reflect.Method;
import java.lang.reflect.Array;
import java.util.*;

public class SortAlgorithm<T extends Comparable<T>>{
    String[] sort_type = {"bubble", "select", "insert", "shell", "quick"};
    String[] seq_type = {"Asc", "Desc"};

    public boolean sort(T[] array, String type, String seq){
        if(!Arrays.asList(sort_type).contains(type)){
            System.out.println("this type of sort doesn't exist!!!");
            return false;
        }
        if(!Arrays.asList(seq_type).contains(seq)){
            System.out.println("this type of seq doesn't exist!!!");
            return false;
        }
        if(seq.equals("Asc")){
            switch(type){
                case "bubble":
                    bubbleAscSort(array);
                    break;
                case "select":
                    selectAscSort(array);
                    break;
                case "insert":
                    insertAscSort(array);
                    break;
                case "shell":
                    shellAscSort(array);
                    break;
                case "quick":
                    quickAscSort(array, 0, array.length-1);
                    break;
            }

        }
        print(array);
        return true;
    }

    public void print_seq_type(){
        for(int i=0;i<seq_type.length;i++){
            System.out.print(seq_type[i]+' ');
        }
        System.out.println();
    }

    public void print_sort_type(){
        for(int i=0;i<sort_type.length;i++){
            System.out.print(sort_type[i]+' ');
        }
        System.out.println();
    }

    private void print(T[] array){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    private T[] bubbleAscSort(T[] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<i;j++){
                if(array[j].compareTo(array[j+1])>0){
                    T tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }

        return array;
    }

    private T[] selectAscSort(T[] array){
        for(int i=0;i<array.length;i++){
            int max_index=0;
            for(int j=0;j<array.length-i;j++){
                if(array[j].compareTo(array[max_index])>0){
                    max_index = j;
                }
            }
            System.out.println(max_index);
            T tmp = array[max_index];
            array[max_index] = array[array.length-i-1];
            array[array.length-i-1] = tmp;
        }

        return array;
    }

    private T[] insertAscSort(T[] array){
        for(int i=0;i<array.length;i++){
            T tmp = array[i];
            int j = i-1;
            while((j>-1)&&(tmp.compareTo(array[j])<0)){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = tmp;
        }
        return array;
    }

    private T[] shellAscSort(T[] array){
        int increase = (array.length/2);
        while(increase>0){
            for(int i=0;i<increase;i++){
                for(int j=i;j<array.length;j+=increase){
                    T tmp = array[j];
                    int k = j-increase;
                    while((k>-1)&&(tmp.compareTo(array[k])<0)){
                        array[k+increase] = array[k];
                        k -= increase;
                    }
                    array[k+increase] = tmp;
                }
            }

            increase /= 2;
        }

        return array;
    }

    private void quickAscSort(T[] array, int left, int right){
        if(left>=right){
            return;
        }
        T pivotKey = array[left];
        int l=left, r=right, p=left;
        while(l<=r){
            while((r>=p)&&(array[r].compareTo(pivotKey)>=0)) r--;
            if(r>=p){
                array[p] = array[r];
                p = r;
            }
            while((l<=p)&&(array[l].compareTo(pivotKey)<=0)) l++;
            if(l<=p){
                array[p] = array[l];
                p = l;
            }
        }
        array[p] = pivotKey;
        if(p+1<right){
            quickAscSort(array, p+1, right);
        }
        if(p-1>left){
            quickAscSort(array, left, p-1);
        }
        
    }
}