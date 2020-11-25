import java.util.Arrays;
public class rank {
    static int[] array = {2, 1, 4, 8, 9, 0, 3, 5, 6, 13};

    public static void main(String[] args) {
        rank rank = new rank();
        int[] maopaorankarray = rank.maopaoRank(array);
        System.out.println(Arrays.toString(maopaorankarray));
        int[] xuanzheRankArray = rank.xuanzheRank(array);
        System.out.println("选择排序：");
        //数组：引用,地址
        System.out.println(Arrays.toString(xuanzheRankArray));
    }

    public int[] maopaoRank(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int n = array[j];
                    int m = array[j + 1];
                    array[j] = m;
                    array[j + 1] = n;
                }
            }
        }
        return array;
    }

    public int[] xuanzheRank(int[] array) {
        int max = 0;
        for (int i = 0; i <= array.length-1; i++) {
            max = array[i];//默认数组的第i个元素是最大的
            for (int j = 0; j < array.length; j++) {

                if (max < array[j]) {
                    int m = array[i];
                    array[i] = array[j];
                    array[j] = m;
                    max = array[j];
                }
            }
        }
        return array;
    }
}
