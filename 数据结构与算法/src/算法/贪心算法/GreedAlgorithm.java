package 算法.贪心算法;

import java.util.*;

public class GreedAlgorithm {
    public static void main(String args[]) {
        // 创建电台集合,放入一个Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        // 创建多个电台单体
        HashSet<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");

        HashSet<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");

        HashSet<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");

        HashSet<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");

        HashSet<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        // 将各个电台放入电台集合broadcast
        broadcasts.put("k1", set1);
        broadcasts.put("k2", set2);
        broadcasts.put("k3", set3);
        broadcasts.put("k4", set4);
        broadcasts.put("k5", set5);

        // 存放所有覆盖地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 创建集合,存放我们选择的电台
        ArrayList<String> selects = new ArrayList<>();
        // 定义临时集合,在遍历过程中存放电台覆盖的地区和没有覆盖的地区的交集
        HashSet<String> temp = new HashSet<>();

        // 不为0,表示还未覆盖全部地区
        while (allAreas.size() != 0) {
            // 定义maxKey,保存在一次遍历过程中,能够覆盖最多未覆盖的地区对应的电台的key
            // 如果maxKey不为空,则加入selects
            String maxKey = null;
            for (String key : broadcasts.keySet()) {
                // 每进行一次for,需要清空temp
                temp.clear();
                HashSet<String> areas = broadcasts.get(key);
                temp.addAll(areas);
                // 求出两个集合的交集,交集会覆盖原先的temp
                temp.retainAll(allAreas);
                // 如果当前集合包含的未覆盖地区的数量,比maxKey指向的集合地区还多,重置maxKey
                // 体现贪心算法,每次都选最优的
                if (temp.size() > 0 &&
                        (maxKey == null || temp.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            // maxKey!=null,就应该加入到selects中
            if (maxKey != null) {
                selects.add(maxKey);
                // 将maxKey指向的电台覆盖地区,从allAreas中remove
                allAreas.removeAll(broadcasts.get(maxKey));
            }

        }
        System.out.println("筛选后的结果:" + selects);

    }
}
