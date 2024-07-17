package programmers.hash;

import java.util.HashSet;

/**
 * 제목: 폰켓몬
 * 문제 url: <a href="https://school.programmers.co.kr/learn/courses/30/lessons/1845">...</a>
 */
public class PoneKeMone {
    public static void main(String[] args) {
        int[] inputCase1 = {3, 1, 2, 3};
        int[] inputCase2 = {3, 3, 3, 2, 2, 2 };
        int case1 = solution(inputCase1);
        int case2 = solution(inputCase2);

        System.out.println("case1 result =" + case1);
        System.out.println("case1 result =" + case2);
    }

    /**
     * @param nums : 폰켓몬 종류
     * @return : 폰켓몬을 선택할 수 있는 최대 경우의 수
     * TC[O(N)], SC[O(N)]
     */
    public static int solution(int[] nums) {
        int max = nums.length / 2; // TC: O(1), SC: O(1)
        HashSet<Integer> set = new HashSet<>();

        for (int value: nums) { //TC[O(N)], SC[O(N)]
            set.add(value); //TC[O(1)]
        }

        return Math.min(set.size(), max); //TC[O(1)]
    }
}