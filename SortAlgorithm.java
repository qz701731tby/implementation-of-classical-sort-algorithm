import java.lang.reflect.Method;
import java.lang.reflect.Array;
import java.util.*;

public class SortAlgorithm<T extends Comparable<T>>{
    String[] sort_type = {"bubble", "select", "insert", "shell", "quick", "localMerge", "heap"};
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
        int flag = seq.equals("Asc")?1:-1;
        switch(type){
            case "bubble":
                bubbleSort(array, flag);
                break;
            case "select":
                selectSort(array, flag);
                break;
            case "insert":
                insertSort(array, flag);
                break;
            case "shell":
                shellSort(array, flag);
                break;
            case "quick":
                quickSort(array, 0, array.length-1, flag);
                break;
            case "localMerge":
                localMergeSort(array, 0, array.length-1, flag);
                break;
            case "heap":
                heapSort(array, flag);
                break;
        }

        System.out.print(type+": ");
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

    private T[] bubbleSort(T[] array, int flag){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<i;j++){
                if((array[j].compareTo(array[j+1]))*flag>0){
                    T tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }

        return array;
    }

    private T[] selectSort(T[] array, int flag){
        for(int i=0;i<array.length;i++){
            int max_index=0;
            for(int j=0;j<array.length-i;j++){
                if((array[j].compareTo(array[max_index]))*flag>0){
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

    private T[] insertSort(T[] array, int flag){
        for(int i=0;i<array.length;i++){
            T tmp = array[i];
            int j = i-1;
            while((j>-1)&&((tmp.compareTo(array[j]))*flag<0)){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = tmp;
        }
        return array;
    }

    private T[] shellSort(T[] array, int flag){
        int increase = (array.length/2);
        while(increase>0){
            for(int i=0;i<increase;i++){
                for(int j=i;j<array.length;j+=increase){
                    T tmp = array[j];
                    int k = j-increase;
                    while((k>-1)&&((tmp.compareTo(array[k]))*flag<0)){
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

    public int[] mergeAscSort(int[] array, int left, int right){
        if(left==right){
            return new int[]{array[left]};
        }
        int mid = (left + right) / 2;
        int[] leftArr = mergeAscSort(array, left, mid);
        int[] rightArr = mergeAscSort(array, mid+1, right);
        int[] newArr = new int[leftArr.length+rightArr.length];

        int m = 0, i = 0, j = 0; 
        while (i < leftArr.length && j < rightArr.length) {
            newArr[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length)
            newArr[m++] = leftArr[i++];
        while (j < rightArr.length)
            newArr[m++] = rightArr[j++];

        return newArr;
    }

    private void localMergeSort(T[] array, int left, int right, int flag){
        if(left>=right) return;
        int mid = (left+right)/2;
        localMergeSort(array, left, mid, flag);
        localMergeSort(array, mid+1, right, flag);

        int i=left, j=mid+1, step;
        while(i<j && j<=right){
            System.out.println("run");
            step=0;
            while(i<j && ((array[i].compareTo(array[j]))*flag<=0)) i++;
            while(j<=right && ((array[j].compareTo(array[i]))*flag<=0)){
                j++;
                step++;
            }
            reverse(array, i, j-1);
            reverse(array, i, i+step-1);
            reverse(array, i+step, j-1);
            i+=step;
        }
    }

    private void reverse(T[] array, int left, int right){
        for(int i=left,j=right;i<j;i++,j--){
            T tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    private void quickSort(T[] array, int left, int right, int flag){
        if(left>=right){
            return;
        }
        T pivotKey = array[left];
        int l=left, r=right, p=left;
        while(l<=r){
            while((r>=p)&&((array[r].compareTo(pivotKey)*flag)>=0)) r--;
            if(r>=p){
                array[p] = array[r];
                p = r;
            }
            while((l<=p)&&((array[l].compareTo(pivotKey)*flag)<=0)) l++;
            if(l<=p){
                array[p] = array[l];
                p = l;
            }
        }
        array[p] = pivotKey;
        if(p+1<right){
            quickSort(array, p+1, right, flag);
        }
        if(p-1>left){
            quickSort(array, left, p-1, flag);
        }  
    }

    //the feature of complete binary tree in array: lchild_loc = father_loc * 2 + 1, rchild_loc = father_loc * 2 + 2
    private void heapify(T[] array, int currentRootNode, int size, int flag){
        if(currentRootNode<size){
            int left = currentRootNode*2+1, right=currentRootNode*2+2;
            int max=currentRootNode;

            if(left<size){
                if((array[left].compareTo(array[max]))*flag>0){
                    max = left;
                }
            }
            if(right<size){
                if((array[right].compareTo(array[max]))*flag>0){
                    max = right;
                }
            }

            if(max!=currentRootNode){
                T tmp = array[max];
                array[max] = array[currentRootNode];
                array[currentRootNode] = tmp;

                heapify(array, max, size, flag);
            }
        }
    }

    private void heapSort(T[] array, int flag){
        for(int i=0;i<array.length;i++){
            if(i==0){
                //first time, you can create a heap, O(n)
                for(int j=array.length-1-i;j>=0;j--){
                    heapify(array, j, array.length, flag);
                }
            }
            else{
                //you only have to adjust from the top of heap, O(log(n))
                heapify(array, 0, array.length-i, flag);
            }

            T tmp = array[0];
            array[0] = array[array.length-1-i];
            array[array.length-1-i] = tmp;
        }
    }

    public void bucketSort(int[] array, int bucket_num, String flag){
        int n=array.length;
        int[][] buckets = new int[bucket_num][n];
        int[] index = new int[bucket_num];
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            min = Math.min(array[i], min);
            max = Math.max(array[i], max);
        }
        int gap = (max-min)/bucket_num + 1;
        //System.out.println(gap);
        for(int i=0;i<n;i++){
            int tmp_index = (int)Math.floor((array[i]-min)/gap);
            buckets[tmp_index][index[tmp_index]++] = array[i];
        }

        // use quickSort for each buckets
        for(int i=0;i<bucket_num;i++){
            quickSortForBucket(buckets[i], 0, index[i]-1, flag);
        }

        int idx=0;
        for(int i=0;i<bucket_num;i++){
            for(int j=0;j<index[i];j++){
                array[idx++] = buckets[i][j];
            }
        }
        
        System.out.print("bucket: ");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]);
            System.out.print(" ");
        }
        System.out.println();

    }

    private void quickSortForBucket(int[] array, int left, int right, String flag){
        if(left>=right){
            return;
        }
        int pivotKey = array[left];
        int l=left, r=right, p=left;
        //boolean b_flag = (flag=="Asc")?true:false;
        while(l<=r){
            while((r>=p)&&(array[r]>=pivotKey)) r--;
            if(r>=p){
                array[p] = array[r];
                p = r;
            }
            while((l<=p)&&(array[l]<=pivotKey)) l++;
            if(l<=p){
                array[p] = array[l];
                p = l;
            }
        }
        array[p] = pivotKey;
        if(p+1<right){
            quickSortForBucket(array, p+1, right, flag);
        }
        if(p-1>left){
            quickSortForBucket(array, left, p-1, flag);
        }  
    }
}