from django.http import JsonResponse

# Create your views here.


def get(request):
    print(request)
    return JsonResponse({"JUDGEMENT": "4B"})
