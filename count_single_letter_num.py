class Solution:
    '''
    第二题：统计只含单一字母/数字(只含一类，题目最简单变种)的子串
    给一个字符串，求连续子串数量，单个字符也算
    输入：“11121”
    输出：8
    解释："1"有4个，"11"有2个，"111"有1个，"2"有1个
    '''

    def count_single_digital_num(self, digit_str: str) -> int:
        result = 0
        str_len = len(digit_str)
        for i in range(str_len - 1):
            result += 1
            digit = digit_str[i]
            for j in range(i + 1, str_len):
                if digit_str[j] == digit:
                    result += 1
                else:
                    break
        result += 1
        return result


if __name__ == '__main__':
    sol = Solution()
    print(sol.count_single_digital_num("11121"))
    print(sol.count_single_digital_num("6666666666"))

# leetcode 算法题1180 (简单305) 统计只含单一字母的子串
# https://blog.csdn.net/FYuu95100/article/details/103026017
# https://leetcode.cn/problems/count-substrings-with-only-one-distinct-letter/
