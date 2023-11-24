lion = [0 for _ in range(11)]
max_diff = -1
answer = []


def solution(n, info):
    find(0, n, info)
    if len(answer) == 0:
        return [-1]
    return answer


def find(depth, arrow_left, apeach):
    global lion
    global max_diff
    global answer

    if arrow_left < 0:
        return
    if depth == 11:
        apeach_score, lion_score = calculate_score(apeach, lion)
        if apeach_score >= lion_score:
            return
        lion[10] += arrow_left
        tmp_diff = lion_score - apeach_score
        if tmp_diff > max_diff:
            # print("갱신 - ", lion)
            max_diff = tmp_diff
            answer = list(lion)
        elif tmp_diff == max_diff and is_faster(lion, answer):
            # print("갱신 - ", lion)
            answer = list(lion)
        return

    arrow_req = apeach[depth] + 1
    lion[depth] = arrow_req
    find(depth + 1, arrow_left - arrow_req, apeach)
    lion[depth] = 0
    find(depth + 1, arrow_left, apeach)


def calculate_score(apeach, lion):
    lion_score = 0
    apeach_score = 0
    for i in range(11):
        if apeach[i] < lion[i]:
            lion_score += 10 - i
        elif apeach[i] != 0:
            apeach_score += 10 - i
    return apeach_score, lion_score


def is_faster(arr1, arr2):
    for i in range(len(arr1) - 1, -1, -1):
        if arr1[i] > arr2[i]:
            return True
        if arr1[i] < arr2[i]:
            return False
    return False


if __name__ == '__main__':
    print(solution(3, [0,0,1,0,0,0,0,0,0,1,0]))
