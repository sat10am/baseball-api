import random


def judge(number: str, real_number: str) -> (str, bool):
    strikes = 0
    balls = 0
    result = ""

    for i, real_i in zip(number, real_number):
        if i == real_i:
            strikes += 1
            continue
        if i in real_number:
            balls += 1
            continue

    if strikes == 4:
        return "4S", True

    if not (strikes or balls):
        return "1O", False

    if strikes and balls:
        return f"{strikes}S {balls}B", False

    if strikes:
        return f"{strikes}S", False

    return f"{balls}B", False


def generate_baseball_number():
    numbers = [str(i) for i in range(10)]
    random.shuffle(numbers)
    return "".join(numbers[:4])
