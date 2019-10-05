from django.urls import path
from api.views import get

urlpatterns = [path("", get, name="baseball_api_get")]
