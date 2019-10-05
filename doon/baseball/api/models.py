from django.db import models

# Create your models here.


class RequestLog(models.Model):

    session = models.TextField()
    number = models.CharField(max_length=4)
    real_number = models.CharField(max_length=4)
    created_at = models.DateTimeField(auto_now_add=True)
