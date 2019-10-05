from django.http import JsonResponse
import attr

from api.models import BaseballGame
from api.utils import judge


@attr.s
class BaseballAPIResponse:

    JUDGEMENT: str = attr.ib()
    TRIED_NUMBER: str = attr.ib()
    CORRECT: bool = attr.ib()
    TRY_COUNT: int = attr.ib()


def get_response(game, tried_number) -> JsonResponse:
    judgement, correct = judge(tried_number, game.real_number)
    api_response = BaseballAPIResponse(
        JUDGEMENT=judgement,
        TRIED_NUMBER=tried_number,
        TRY_COUNT=game.try_count + 1,
        CORRECT=correct,
    )
    json_response = JsonResponse(attr.asdict(api_response))
    return json_response


def deny_anonymous_user():
    return JsonResponse({"message": "Please specify your name in query string"}, 401)


def get(request):
    username = request.GET.get("username")
    tried_number = request.GET.get("number")
    if not username:
        return deny_anonymous_user

    current_game = BaseballGame.get_current_game(username)
    if not current_game.exists():
        game = BaseballGame.new_game(username)
    else:
        game = current_game.first()

    response = get_response(game, tried_number)
    game.increment_try_count()
    return response
