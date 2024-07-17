package programmers.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 제목: 완주하지 못한 선수
 * 문제 url: <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42576">...</a>
 * #입력데이터가 클 경우 : solution2 > solution3
 * #메모리가 제한된 경우 : solution2 < solution3
 * #메모리가 제한된 경우를 제외하고는 일반적으로는 solution2가 더 최적의 알고리즘
 */
public class NotCompleteRunner {
    public static void main(String[] args) {
        String[][] inputCase1 = {{"leo", "kiki", "eden"}, {"eden", "kiki"}};
        String[][] inputCase2 = {{"mislav", "stanko", "mislav", "ana"}, {"stanko", "ana", "mislav"}};

        String sol1Case1 = solution1(inputCase1[0], inputCase1[1]);
        String sol1Case2 = solution1(inputCase2[0], inputCase2[1]);
        System.out.println("solution1 case1 result =" + sol1Case1);
        System.out.println("solution1 case2 result =" + sol1Case2);

        String sol2Case1 = solution2(inputCase1[0], inputCase1[1]);
        String sol2Case2 = solution2(inputCase2[0], inputCase2[1]);
        System.out.println("solution2 case1 result =" + sol2Case1);
        System.out.println("solution2 case2 result =" + sol2Case2);

        String sol3Case1 = solution3(inputCase1[0], inputCase1[1]);
        String sol3Case2 = solution3(inputCase2[0], inputCase2[1]);
        System.out.println("solution3 case1 result =" + sol3Case1);
        System.out.println("solution3 case2 result =" + sol3Case2);
    }


    /**
     * @param participant : 참석자 명단
     * @param completion : 완료자 명단
     * @return : 완료하지 못한 선수 이름
     * Timeout Error
     * TC: O(N^2), SC: O(N)
     */
    public static String solution1(String[] participant, String[] completion) {
        // TC: O(1), SC: O(1) - List는 배열을 복사하지 않고 단순 배열을 참조만하는 리스트를 만들기 때문에 O(1)
        List<String> participantList = Arrays.asList(participant);
        // TC: O(N), SC: O(N) - ArrayList는 새로운 List를 만들어 복사하기 때문에 O(N)
        ArrayList<String> completionList = new ArrayList<>(Arrays.asList(completion));

        for (String runner : participantList) { // TC: O(N)
            if (!completionList.contains(runner)) { // TC: O(N)
                return runner; // TC: O(1)
            }else {
                completionList.remove(runner); // TC: O(N)
            }
        }

        return null; // TC: O(1)
    }

    /**
     * @param participant : 참석자 명단
     * @param completion : 완료자 명단
     * @return : 완료하지 못한 선수 이름
     * solution1 개선
     * TC: O(N), SC: O(N)
     */
    public static String solution2(String[] participant, String[] completion) {
        HashMap<String, Integer> participantMap = new HashMap<>(); // SC: O(N)

        // 참가자 명단을 HashMap에 저장
        for (String p : participant) { // TC: O(N)
            participantMap.put(p, participantMap.getOrDefault(p, 0) + 1); // TC: O(1)
        }

        // 완주자 명단을 HashMap에서 차감
        for (String c : completion) { // TC: O(N)
            if (participantMap.get(c) == 1) {
                participantMap.remove(c); // TC: O(1)
            } else {
                participantMap.put(c, participantMap.get(c) - 1); // TC: O(1)
            }
        }

        // 남아 있는 참가자가 완주하지 못한 사람
        for (String key : participantMap.keySet()) {
            return key; // TC: O(1)
        }

        return null;
    }

    /**
     * @param participant : 참석자 명단
     * @param completion : 완료자 명단
     * @return : 완료하지 못한 선수 이름
     * solution1 개선 (하지만 solution2 보다는 효율이 좋지 못하다.)
     * TC: O(N log N), SC: O(1) - 시간복잡도를 계산할때는 평균적인 시간복잡도를 사용
     */
    public static String solution3(String[] participant, String[] completion) {
        // 1. 두 배열을 정렬한다 (Dual-Pivot Quicksort 알고리즘)
        Arrays.sort(participant); // TC: 평균-O(N log N) 최악-O(N^2)
        Arrays.sort(completion); // TC: 평균-O(N log N) 최악-O(N^2)

        int i;
        for (i = 0; i < completion.length; i++) { // TC: O(N)
            if (!participant[i].equals(completion[i])) { // TC: O(1)
                return participant[i]; // TC: O(1)
            }
        }

        return participant[i]; // TC: O(1)
    }
}
