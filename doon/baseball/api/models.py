from django.db import models

# Create your models here.
from api.utils import generate_baseball_number


class BaseballGame(models.Model):

    username = models.TextField()
    number = models.CharField(max_length=4)
    real_number = models.CharField(max_length=4)
    try_count = models.IntegerField(default=0)
    is_over = models.BooleanField(default=False)
    created_at = models.DateTimeField(auto_now_add=True)

    class Meta:
        unique_together = ("username", "real_number", "created_at")

    @classmethod
    def new_game(cls, username):
        while True:
            real_number = generate_baseball_number()
            already_played_number = cls.objects.filter(
                username=username, real_number=real_number
            ).exists()
            if not already_played_number:
                return cls.objects.create(username=username, real_number=real_number)

    @classmethod
    def get_current_game(cls, username) -> models.query.QuerySet:
        return cls.objects.filter(username=username, try_count__lt=20, is_over=False)

    def increment_try_count(self):
        self.try_count += 1
        self.save()
