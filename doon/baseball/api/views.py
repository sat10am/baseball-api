from django.http import JsonResponse
import attr

# Create your views here.


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
    response = get_response("1B 3S", "1234", 10)
    return JsonResponse(attr.asdict(response))
