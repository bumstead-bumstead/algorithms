def solution(today, terms, privacies):
    today = date.fromString(today)
    duration_hash = {}
    answer = []

    for string in terms:
        term, duration = string.split()
        duration_hash[term] = int(duration)

    for i in range(len(privacies)):
        start, term = privacies[i].split()
        start_date = date.fromString(start)
        if start_date.is_expired(today, duration_hash[term]):
            answer.append(i+1)

    return answer


class date:
    def __init__(self, year, month, day):
        self.year = year
        self.month = month
        self.day = day

    def addMonth(self, month):
        month += self.month
        year = self.year + month // 12
        month %= 12
        if month == 0:
            month = 12
            year -= 1

        return date(year, month, self.day)

    def is_expired(self, today, duration):
        # self에 duration 더하고, today와 크거나 같은지 확인
        date_d = self.addMonth(duration)
        return date_d <= today

    def __le__(self, other):
        if self.year < other.year:
            return True
        elif self.year > other.year:
            return False
        elif self.month < other.month:
            return True
        elif self.month > other.month:
            return False
        return self.day <= other.day

    @classmethod
    def fromString(cls, string):
        a = string.split(".")
        return date(int(a[0]), int(a[1]), int(a[2]))

if __name__ == '__main__':

    print(solution("2009.12.31", ["A 13"], ["2008.11.03 A"]))