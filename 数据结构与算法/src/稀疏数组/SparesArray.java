package 稀疏数组;

public class SparesArray {

    public static void main(String args[]) {
        // 创建原始二维数组11*11
        // 0:表示没有有效数值
        int chessArray[][] = new int[11][11];
        // 增添两个有效数字
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[2][5] = 3;
        printArray(chessArray);
        System.out.println("稀疏数组打印结果:");
        int[][] sparesArray = changeSparesArray(chessArray);    // 原始数组转稀疏数组
        printArray(sparesArray);
        // 稀疏数组转原始数组
        int[][] initArray = changeInitArray(sparesArray);
        System.out.println("原始数组打印结果:");
        printArray(initArray);


    }

    /* 将原始数组转变为稀疏数组:
     表头: (行   列   有效值)
        总行数  总列数  有效值总数
        有效值1行数  有效值1列数  有效值1
        有效值2行数  有效值2列数  有效值2
        ..............
        固定列数为为3,行数为有效值总数+1
     */
    private static int[][] changeSparesArray(int[][] arr) {

        // 记录有效值总数
        int sum = 0;
        for (int[] datas : arr) {
            for (int data : datas) {
                if (data != 0) {
                    // 只要出现有效值,sum++
                    sum++;
                }
            }
        }

        // 创建对应的稀疏数组
        int sparesArr[][] = new int[sum + 1][3];
        // 给稀疏数组赋值
        // 第一行
        sparesArr[0][0] = 11;
        sparesArr[0][1] = 11;
        sparesArr[0][2] = sum;

        // 遍历原始二维数组,将!=0的值存放到稀疏数组中
        int count = 0;    // 用于记录当前是第几个有效数值
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (arr[i][j] != 0) {
                    count++;    // 从稀疏数组第二行开始存入
                    // 将有效值的行号,列号,值存进稀疏数组
                    sparesArr[count][0] = i;
                    sparesArr[count][1] = j;
                    sparesArr[count][2] = arr[i][j];
                }
            }
        }

        return sparesArr;
    }

    /*
    将稀疏数组转为原始数组
     */
    private static int[][] changeInitArray(int[][] sparesArr) {
        // 读取稀疏数组第一行,获取原数数组的行和列
        int[][] init = new int[sparesArr[0][0]][sparesArr[0][1]];
        // 将有效值恢复给原始数组
        for (int i = 1; i < sparesArr.length; i++) {
            // 从稀疏数组的第二行开始,将有效值的行,列,数值恢复给原始数组
            init[sparesArr[i][0]][sparesArr[i][1]] = sparesArr[i][2];
        }
        return init;
    }

    private static void printArray(int[][] arr) {
        for (int[] datas : arr) {
            for (int data : datas) {
                System.out.print(data + "   ");
            }
            System.out.println();
        }
    }
}
