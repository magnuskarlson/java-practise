import java.util.Arrays;
import java.util.Random;

public class optic {
    public static void main(String[] args){
        // Sorting
        for(String method : new String[]{"BubbleSort", "MergeSort"}){
            int[] nums = randomNums(10000, 0, 10000);
            printNumArray(nums);
            long start = System.currentTimeMillis();
            if(method.equals("BubbleSort")){
                bubbleSort(nums);
            }else if(method.equals("MergeSort")){
                mergeSort(nums);
            }
            long end = System.currentTimeMillis();
            printNumArray(nums);
            System.out.println(String.format("%s %dms\n",method, end - start));
        }

        // Tree
        System.out.println("Tree");
        Node n = new Node(100, new Node(200, new Node(400), null), new Node(300));
        int total = recTree(n, "Main");
        System.out.println("Total " + total);

    }

    // Recursive tree
    static int recTree(Node n, String type){
        System.out.println(type + " " + n.v);
        int l = n.l != null ? recTree(n.l, "Left") : 0;
        int r = n.r != null ? recTree(n.r, "Right") : 0;
        return n.v + l + r;
    }

    // BubbleSort
    static void bubbleSort(int[] nums){
        for(int i = 0; i < nums.length; i++){
            for(int j = nums.length - 1; j > i; j--){
                if(nums[j] < nums[j - 1]){
                    swap(nums, j, j - 1);
                }
            }
        }
    }

    // MergeSort
    static void mergeSort(int[] m){
        mergeSort(m, 0, m.length);
    }

    static void mergeSort(int[] m, int i, int j){
        int k = (i + j) / 2;
        if(j - i > 2){
            mergeSort(m, i, k);
            mergeSort(m, k, j);
        }
        merge(m, i, k, j);
    }

    static void merge(int[] m, int i, int k, int j){
        int[] m1 = Arrays.copyOfRange(m, i, k), m2 = Arrays.copyOfRange(m, k, j);
        int mn1 = 0, mn2 = 0;
        for(int n = i; n < j; n++){
            if(mn1 < m1.length && mn2 < m2.length){
                if(m1[mn1] < m2[mn2]){
                    m[n] = m1[mn1];
                    mn1 += 1;
                }else{
                    m[n] = m2[mn2];
                    mn2 += 1;
                }
            }else if(mn1 < m1.length){
                m[n] = m1[mn1];
                mn1 += 1;
            }else{
                m[n] = m2[mn2];
                mn2 += 1;
            }
        }
    }
    static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Helpful
    static void printNumArray(int[] nums){
        if(nums.length > 50){
            System.out.println(String.format("First %d, Last %d", nums[0], nums[nums.length - 1]));
        }else{
            System.out.println(Arrays.toString(nums));
        }
    }

    static int[] randomNums(int n, int min, int max){
        return new Random().ints(n, min, max).toArray();
    }
}

class Node{
    int v;
    Node l;
    Node r;
    Node(int v, Node l, Node r){
        this.v = v;
        this.l = l;
        this.r = r;
    }
    Node(int v){
        this.v = v;
        this.l = null;
        this.r = null;
    }
}