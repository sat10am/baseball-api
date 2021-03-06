# Generated by Django 2.2.6 on 2019-10-05 08:23

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='BaseballGame',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('session', models.TextField()),
                ('number', models.CharField(max_length=4)),
                ('real_number', models.CharField(max_length=4)),
                ('try_count', models.IntegerField(default=1)),
                ('created_at', models.DateTimeField(auto_now_add=True)),
            ],
            options={
                'unique_together': {('session', 'real_number', 'created_at')},
            },
        ),
        migrations.DeleteModel(
            name='RequestLog',
        ),
    ]
