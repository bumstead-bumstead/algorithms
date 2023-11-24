'''
1. number의 이진수를 포화이진트리 형태로 만든다. (1, 3, 7, ... 크기가 되도록 앞에 0 더함)
2. 이진 트리 내의 모든 서브트리에 대해서 다음을 반복
    a. 루트 노드가 더미 노드이면서 자식 노드중에 정상 노드가 있는지 확인
-> 이거를 재귀로 맨밑에서부터 확인해야겠네..

- 포화 이진트리로 표현할 수 없는 경우 -> 더미노드 밑에 정상 노드가 있는 경우
'''

binary_form = ""
is_correct = True


def solution(numbers):
    global binary_form
    global is_correct

    result = []
    for number in numbers:
        print("---------------------")
        is_correct = True
        binary_form = to_full_binary_form(bin(number)[2:])
        print(binary_form)
        is_empty(0, len(binary_form) - 1)
        if is_correct:
            result.append(1)
        else:
            result.append(0)

    return result

# 해당 서브트리가 0으로만 되어있는 지 반환
def is_empty(start_idx, end_idx):
    global is_correct
    print("check : ", start_idx, end_idx)

    if start_idx == end_idx:
        return binary_form[start_idx] == "0"

    root_idx = (end_idx + start_idx) // 2
    empty_left = is_empty(start_idx, root_idx - 1)
    empty_right = is_empty(root_idx + 1, end_idx)

    if binary_form[root_idx] == "0" and not (empty_left and empty_right):
        is_correct = False

    return empty_left and empty_right and binary_form[root_idx] == "0"


def to_full_binary_form(binary_form):
    n = 1
    cnt = 1
    while n < len(binary_form):
        n += 2**cnt
        cnt += 1

    return '0' * (n-len(binary_form)) + binary_form


if __name__ == '__main__':
    # print(to_full_binary_form(bin(96)[2:]))
    # print(len(to_full_binary_form(bin(96)[2:])))
    print(solution([547]))
    # [0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1]