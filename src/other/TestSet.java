package other;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class TestSet {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item4");
        list.add("item5");
        list.add("item6");
        List<String> historyList = new ArrayList<>();
        historyList.add("history1");
        historyList.add("history2");
        historyList.add("history3");
        historyList.add("history4");
        historyList.add("history5");
        historyList.add("history6");
        historyList.add("item1");

        TreeSet<String> resultList = new TreeSet<>();
        System.out.println("开始前：" + resultList);


        resultList.addAll(historyList);
        int totalSize = resultList.size() + historyList.size();
        if (totalSize < 1000) {
            for (String item : list) {
                resultList.add(item);
            }
        } else {
            for (int i = 0; i < totalSize; i++) {
                if (resultList.size() >= 8) {
                    resultList.remove(resultList.iterator().next());
                }
                if (i < historyList.size()) {
                    resultList.add(historyList.get(i));
                } else {
                    resultList.add(list.get(i - historyList.size()));
                }
            }
        }
        System.out.println(resultList);
    }
}
