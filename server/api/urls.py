from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('api.views',
    (r'^check/',  'check_user'),
    (r'^create/', 'create_user'),
    (r'^self/', 'self_feed'),
    (r'^feed/', 'friend_feed'),
    (r'^update/', 'set_update'),
    (r'^fbconnect/', 'new_fb_connections'),
    (r'^fbuid/', 'save_fb_uid'),
)
