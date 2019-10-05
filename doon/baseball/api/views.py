from django.http import JsonResponse
import attr

# Create your views here.
def judge(number, real_number):
    strikes = 0
    balls = 0
    out = 0

    for i, real_i in zip(number, real_number):
        if i == real_i:
            strikes += 1
            continue
        if i in real_number:
            balls += 1
            continue

    # Both strikes and balls are zero
    if not (strikes or balls):
        return "1O"

    # Neither balls and out
    if strikes and not balls:
        return "4S"
    # Neither strikes and out

    if not strikes:
        return "4B"

    # Either strikes and balls are not zero
    return f"{strikes}S {balls}B"


@attr.s
class BaseballAPIResponse:

    JUDGEMENT: str = attr.ib()
    TRIED_NUMBER: str = attr.ib()
    CORRECT: bool = attr.ib()
    TRY_COUNT: int = attr.ib()


def get_response(
    judgement: str, tried_number: str, try_count: int, correct: bool = False
):
    return BaseballAPIResponse(
        JUDGEMENT=judgement,
        TRIED_NUMBER=tried_number,
        TRY_COUNT=try_count,
        CORRECT=correct,
    )


def get(request):
    tried_number = request.GET.get("number")
    real_number = "1234"
    judgement = judge(tried_number, real_number)
    if judgement == "4S":
        response = get_response(judgement, tried_number, 10, True)
        return JsonResponse(attr.asdict(response), status=201)
    response = get_response(judgement, tried_number, 10)
    return JsonResponse(attr.asdict(response))
