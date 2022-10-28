package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = getResult(user, friends, visitors);
        return answer;
    }

    static List<String> getResult(String user, List<List<String>> friends, List<String> visitors) {
        Map<String, List<String>> friendsMap = getFriendsMap(friends);
        Map<String, Integer> scoreMap = new HashMap<>();
        List<String> userFriends = friendsMap.remove(user);
        List<String> result;

        setFriendsPoint(friendsMap, userFriends, scoreMap);
        getVisitorsPoint(scoreMap, visitors);
        result = sortScoreMap(scoreMap, userFriends);

        return result;
    }

    static Map<String, List<String>> getFriendsMap(List<List<String>> friends) {
        Map<String, List<String>> friendsMap = new HashMap<>();
        friends.stream()
            .forEach(list -> inputFriendsToMap(friendsMap, list));

        return friendsMap;
    }

    static void inputFriendsToMap(Map<String, List<String>> friendsMap, List<String> friend) {
        String friend1 = friend.get(0);
        String friend2 = friend.get(1);

        inputFriendToMap(friendsMap, friend1, friend2);
        inputFriendToMap(friendsMap, friend2, friend1);
    }

    static void inputFriendToMap(Map<String, List<String>> friendsMap, String friend1, String friend2) {
        List<String> defaultList = new ArrayList<>();
        List<String> friend1Value = friendsMap.getOrDefault(friend1, defaultList);
        friend1Value.add(friend2);
        friendsMap.put(friend1, friend1Value);
    }

    static void setFriendsPoint(Map<String, List<String>> friendsMap, List<String> userFriends, Map<String, Integer> scoreMap) {
        friendsMap.entrySet().stream()
                .forEach(e -> processScore(e.getKey(), e.getValue(), userFriends, scoreMap));
    }

    static void processScore(String name, List<String> friendsList, List<String> userFriends, Map<String, Integer> scoreMap) {
        int count = (int) friendsList.stream()
                .filter(friend -> userFriends.contains(friend))
                .count();

        scoreMap.put(name, count * 10);
    }

    static List<String> getFriendList(List<List<String>> friends, String user) {
        Set<String> friendSet = new HashSet<>();
        List<String> friendList = new ArrayList<>();
        friends.stream()
            .forEach(list -> inputFriendSet(friendSet, list));

        friendList.addAll(friendSet);
        friendList.remove(user);
        return friendList;
    }

    static void inputFriendSet(Set<String> set, List<String> list) {
        set.add(list.get(0));
        set.add(list.get(1));
    }




    
    static void getVisitorsPoint(Map<String, Integer> scoreMap, List<String> visitors) {
        visitors.stream()
            .forEach(v -> scoreMap.put(v, scoreMap.getOrDefault(v,0) + 1));
    }

    public static Comparator<Map.Entry<String, Integer>> mapComparator = new
        Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() >  o2.getValue()){
                    return -1;
                }
                else if (o1.getValue() ==  o2.getValue()) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return 1;
            }
        };

    static List<String> sortScoreMap(Map<String, Integer> scoreMap, List<String> userFriends) {

        List<Map.Entry<String, Integer>> entries = new LinkedList<>(scoreMap.entrySet());
        Collections.sort(entries, mapComparator);
        List<String> result = new ArrayList<>();

        entries.stream()
            .filter(e -> !(userFriends.contains(e.getKey())))
            .forEach(e -> result.add(e.getKey()));

        if (result.size() > 5) {
            List<String> subList = new ArrayList<>(result.subList(0,5));
            return subList;
        }
        return result;
    }

    public static void main(String[] args) {
        String user = "mrko";
        List<List<String>> friends = List.of(
                List.of("donut", "andole"),
                List.of("donut", "jun"),
                List.of("donut", "mrko"),
                List.of("shakevan", "andole"),
                List.of("shakevan", "jun"),
                List.of("shakevan", "mrko")
        );
        Map<String, List<String>> friendsMap = getFriendsMap(friends);
        Map<String, Integer> scoreMap = new HashMap<>();
        List<String> userFriends = friendsMap.remove(user);

        System.out.println("friendsMap: \n" + friendsMap);
        System.out.println("userFriends: \n" + userFriends);

        setFriendsPoint(friendsMap, userFriends, scoreMap);

        System.out.println(scoreMap);
    }
}
