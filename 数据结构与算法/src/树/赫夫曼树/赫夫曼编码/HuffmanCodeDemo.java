package 树.赫夫曼树.赫夫曼编码;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class HuffmanCodeDemo {
    public static void main(String args[]) {
        String str = "i like like like java do you like a java";
        byte[] zip = huffmanZip(str.toCharArray());
        System.out.println("字符串通过赫夫曼压缩后:" + Arrays.toString(zip));
        System.out.println("长度:" + zip.length);

        System.out.println("--------解压缩--------");
        char[] chars = unZip(zip);
        System.out.println("还原:" + new String(chars));
    }

    // 解压
    // 1.将字符串通过赫夫曼压缩后:[50, -124, 101, 8,先转回拼接后的编码字符串:1001100
    // 2.拼接后的编码字符串:1001100转为"i like like like java d...
    public static char[] unZip(byte[] zip) {
        return decode(huffmanCodeMap, zip);
    }

    // 1.将字符串通过赫夫曼压缩后:[50, -124, 101, 8,先转回拼接后的编码字符串:1001100
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        // 如果是正数,补高位
        if (flag) {
            temp |= 256;
        }
        String s = Integer.toBinaryString(temp);    // 返回的是二进制的补码
        if (flag) {
            return s.substring(s.length() - 8);
        } else {
            return s;
        }
    }

    // 2.拼接后的编码字符串:1001100转为"i like like like java d...
    private static char[] decode(Map<Character, String> huffmanCodes, byte[] huffmanBytes) {
        // 先得到二进制字符串;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1");
        // 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        // System.out.println(stringBuilder.length());
        System.out.println("赫夫曼字节数相对应:" + stringBuilder.toString());
        // 把字符串按照指定的赫夫曼编码进行解码
        Map<String, Character> map = new HashMap<>();
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //System.out.println(map);
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length() - 1; ) {
            int count = 1;
            boolean flag = true;
            Character c = null;
            while (flag) {
                // i不动,count动,直到匹配了字符
                String key = stringBuilder.substring(i, i + count);
                c = map.get(key);
                if (c == null) { // 没匹配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(c);
            i += count;
        }
        // 当for循环结束后,list中存放了所有的字符
        char[] chars = new char[list.size()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = list.get(i);
        }
        return chars;
    }

    // 封装
    public static byte[] huffmanZip(char[] chars) {
        // 1.统计字符及其对应数量,创建对应的节点集合
        List<CodeNode> nodes = getNodes(chars);
        // 2.创建赫夫曼树
        CodeNode root = createHuffmanCodeTree(nodes);
        System.out.println("前序遍历赫夫曼树:");
        root.preOrder();
        System.out.println();
        // 3.获取叶子节点(有效字符)的赫夫曼编码
        getHuffmanCodes(root, "", huffmanString);
        System.out.println("字符串的赫夫曼编码集合:" + huffmanCodeMap);
        // 4.压缩
        return zip(chars, huffmanCodeMap);
    }

    // 1.统计字符及其对应数量,创建对应的节点,使用集合保存
    private static List<CodeNode> getNodes(char[] chars) {
        ArrayList<CodeNode> nodes = new ArrayList<>();
        // 统计每一个字符出现的次数
        HashMap<Character, Integer> counts = new HashMap<>();
        for (char c : chars) {
            // 先判断map中是否已经有该字符
            Integer count = counts.get(c);
            if (count == null) {
                // 没有就第一次存储
                counts.put(c, 1);
            } else {
                // 有就+1
                counts.put(c, count + 1);
            }
        }
        // 把每个键值对转成Node放入集合
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            nodes.add(new CodeNode(entry.getKey(), entry.getValue()));
        }
        // Collections.sort(nodes);
        return nodes;
    }

    // 2.创建赫夫曼树
    private static CodeNode createHuffmanCodeTree(List<CodeNode> nodes) {
        while (nodes.size() > 1) {
            // 1.取出根节点权值最小的二叉树
            // 单个节点视为小型二叉树
            CodeNode leftNode = nodes.get(0);
            // 2.取出第二小的节点
            CodeNode rightNode = nodes.get(1);
            // 3.构建新的二叉树
            // 新二叉树权值 = 左 + 右
            // 新二叉树没有data,只有权值(data只在叶子结点)
            CodeNode parent = new CodeNode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // 4.构建新二叉树后,删除掉用过的左右小树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 5.将新树加入进集合
            nodes.add(parent);
            // 6.根据权值重新排序
            Collections.sort(nodes);
        }
        // 返回赫夫曼树的头结点
        return nodes.get(0);
    }

    // 3.生成所有叶子节点对应的赫夫曼编码
    // 左路径为0,右路径为1
    // String leftOrRight = 0 ? 1;
    // 遍历到叶子结点的过程中,不断拼接路径码号("1+0+1")
    static StringBuilder huffmanString = new StringBuilder();
    // 将赫夫曼编码存放在map中<Character,String>
    static Map<Character, String> huffmanCodeMap = new HashMap<>();

    // 比如 a:100
    private static void getHuffmanCodes(CodeNode node, String leftOrRight, StringBuilder huffmanString) {
        StringBuilder sb = new StringBuilder(huffmanString);
        sb.append(leftOrRight);
        if (node != null) {
            if (node.data == null) {
                // 非叶子节点
                // 向左递归
                getHuffmanCodes(node.left, "0", sb);
                // 向右递归
                getHuffmanCodes(node.right, "1", sb);
            } else {
                // 如果是叶子节点
                // 表示找到了某个叶子节点的最后
                huffmanCodeMap.put(node.data, sb.toString());
            }
        }
    }

    // 4.将生成的各叶子节点的赫夫曼编码,拼成总字符串
    private static byte[] zip(char[] chars, Map<Character, String> map) {
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            // 根据遍历到的字符获取map中对应的value,拼接
            String s = map.get(c);
            sb.append(s);
        }
        System.out.println("拼接后的编码字符串:" + sb.toString());
        // 压缩赫夫曼字节数组
        // 八位为一个
        /*
        对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
	    huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000(源码)= -88 ]
	    huffmanCodeBytes[1] = -88
         */
        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        byte[] bytes = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String string;
            if (i + 8 > sb.length()) {
                string = sb.substring(i);
            } else {
                string = sb.substring(i, i + 8 + 1);
            }
            bytes[index++] = (byte) Integer.parseInt(string, 2);
        }
        return bytes;
    }

    // 压缩文件
    public static void zipFile(String src, String dst) throws IOException {
        // 创建输入流
        FileInputStream inputStream = new FileInputStream(src);
        // 创建和原文件一样大小的byte[];
        byte[] b = new byte[inputStream.available()];
        // 读取文件
        inputStream.read(b);
        inputStream.close();
        //
    }
}

