package leetcode.p3.q141_150;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 149.直线上最多的点数
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 *
 * 示例 1:
 *
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |        o
 * |     o
 * |  o  
 * +------------->
 * 0  1  2  3  4
 * 示例 2:
 *
 * 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出: 4
 * 解释:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 *
 * @author ilongli
 * @date 2020/7/8 12:00
 */
public class MaxPoints {

    public static void main(String[] args) {
//        int[][] points = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
//        int[][] points = {{4,6}, {2,3}, {0,0}, {1,1}, {1,1}, {2,3}, {4,6}, {6,9}};
//        int[][] points = {{1,1},{3,4},{4,5},{5,6},{1,1},{0,0},{7,8},{8,9}};
        int[][] points = {{1,1}};
        MaxPoints maxPoints = new MaxPoints();
        System.out.println(maxPoints.maxPoints(points));
    }

    public int maxPoints(int[][] points) {

        if (points.length == 0) return 0;

        // 存放斜率的数组
        Map<Slope, Integer> slopeMap = new HashMap<>();
        
        int max = 1;

        for (int i = 0; i < points.length; i++) {

            // 基点
            int[] basePoint = points[i];
            // 该次遍历的新斜率
            Map<Slope, Integer> _slopeMap = new HashMap<>();
            // 同点数量
            int samePointCount = 0;
            
            for (int j = i + 1; j < points.length; j++) {
                // 目标点
                int[] targetPoint = points[j];
                // 如果是同一个点，统计
                if (basePoint[0] == targetPoint[0] && basePoint[1] == targetPoint[1]) {
                    samePointCount++;
                    continue;
                }

                // 算出两点上的线的斜率
                Slope slope = new Slope(basePoint, targetPoint);

                // 如果之前已经统计过这个斜率，直接跳过
//                if (slopeMap.containsKey(slope)) continue;

                Integer count = _slopeMap.get(slope);
                if (count == null) {
                    // 该斜率第一次计算
                    _slopeMap.put(slope, 2);
                } else {
                    // 将该斜率的数值加1
                    _slopeMap.put(slope, count + 1);
                }
            }

            // putAll
//            for (Map.Entry<Slope, Integer> entry : _slopeMap.entrySet()) {
//                slopeMap.put(entry.getKey(), entry.getValue() + samePointCount);
//            }
            for (int count : _slopeMap.values()) {
                max = Math.max(max, count + samePointCount);
            }

            max = Math.max(max, samePointCount + 1);
        }

        // 统计出最大值
//        for (int count : slopeMap.values()) {
//            max = Math.max(max, count);
//        }
        
        return max;
    }


    /**
     * 斜率
     */
    class Slope {
        // 分子
        int numerator;
        // 分母
        int denominator;

        public Slope(int[] p1, int[] p2) {
            int deltaX = p2[0] - p1[0];

            // 垂直
            if (deltaX == 0) {
                this.numerator = 0;
                this.denominator = p1[0];
                return;
            }

            int deltaY = p2[1] - p1[1];

            // 水平
            if (deltaY == 0) {
                this.numerator = p1[1];
                this.denominator = 0;
                return;
            }

            // 正负修正
            if ((deltaX < 0 && deltaY > 0) || (deltaX < 0 && deltaY < 0)) {
                deltaX = deltaX * -1;
                deltaY = deltaY * -1;
            }

            // 计算最大公约数
            int gcd;
            if (deltaY > deltaX) {
                gcd = getGCD(deltaY, deltaX);
            } else {
                gcd = getGCD(deltaX, deltaY);
            }
            // 保存相约后的数
            this.numerator = deltaY / gcd;
            this.denominator = deltaX /gcd;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Slope slope = (Slope) o;
            return numerator == slope.numerator &&
                    denominator == slope.denominator;
        }

        @Override
        public int hashCode() {
            return Objects.hash(numerator, denominator);
        }
    }

    /**
     * 计算最大公约数
     * @param a     较大数
     * @param b     较小数
     * @return
     */
    public int getGCD(int a, int b) {
        return a % b == 0 ? b : getGCD(b, a % b);
    }

}
