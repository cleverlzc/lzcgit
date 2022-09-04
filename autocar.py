from typing import List


class Solution:
    '''
    第一题：无人驾驶汽车减速
    车子进隧道，每个车子以一定车速进入，车速比前一辆大的需要减速。从列表倒数开始进入隧道，值代表车速。求不减速的车辆序号，并以升序输出
    输入：[4, 5, 5, 3, 2, 8, 7]
    输出：[4, 6]
    '''

    def get_affected_ids2(self, speed: List[int]) -> List[int]:
        slow_count_list = list()
        for i in range(len(speed)):
            now_speed = speed[i]
            slow_count_list.append([now_speed, 0])

            for j in range(len(slow_count_list)):
                if slow_count_list[j][0] > now_speed:
                    slow_count_list[j][1] += 1

        result_list = list()
        for i in range(len(slow_count_list)):
            if slow_count_list[i][1] == 0:
                result_list.append(i)
        return result_list

    def get_affected_ids(self, speed: [int]) -> [int]:
        n = len(speed)
        right = n - 2
        min_speed = speed[-1]
        res = [n - 1]
        if n == 1:
            return speed
        while right >= 0:
            min_speed = min(min_speed, speed[right])
            if speed[right] <= min_speed:
                res.append(right)
            right -= 1
        res.sort() # sort() return None, need return res(in-place sort)
        return res

if __name__ == '__main__':
    sol = Solution()
    print(sol.get_affected_ids([4, 5, 5, 3, 2, 8, 7]))
    print(sol.get_affected_ids([60, 90, 80, 110, 120, 90, 100, 90, 100, 100]))
    print(sol.get_affected_ids([110, 110, 110]))

# 这类简单的算法题，关键是读懂题目，分析，然后coding，一遍过，还有重要的一点，保持题感
# 只要前面所有的没有比它本身小的（正序遍历）或者大的（逆序遍历），就把索引记录下来，下面的解法用了一个小技巧，不断的取min，一遍循环
