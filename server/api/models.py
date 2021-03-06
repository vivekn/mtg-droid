from django.db import models
from itertools import chain

# Create your models here.

class User(models.Model):
    userid = models.CharField()
    pass_hash = models.CharField()
    name = models.CharField()
    signup_provider = models.CharField() # Eg Facebook, Twitter etc
    fb_uid = models.CharField()

    def get_friend_feed(self):
        feed = chain([x.update_set for x in self.friends.all()]).order_by('-timestamp')
        return feed
    
class Update(models.Model):
    user = models.ForeignKey('User')
    latitude = models.CharField()
    longitude = models.CharField()
    message =  models.CharField(max_length = 300, blank = True)
    reverse_geocode = models.CharField(max_length = 300, blank = True)
    tag = models.ForeignKey('Tag',null = True)
    timestamp = models.DateTimeField(auto_now = True)

class Tag(models.Model):
    name = models.CharField()
    picture_url = models.CharField()

class Connection(models.Model):
    '''
    Note: Create two of these objects for each connection
    '''
    from_friend = models.ForeignKey(User, related_name = 'friend_set')
    to_friend = models.ForeignKey(User, related_name = 'friends')
