# Create your views here.

import itertools
from api.models import *
from django.utils import simplejson
from django.http import HttpResponse

def serialize_update(queryset):
    FIELDS = ['user.name', 'latitude', 'longitude', 'message', 'reverse_geocode'
              'tag.name']
    string = ''
    for item in queryset:
        values = []
        for field in FIELDS:
            if field.find('.') != -1:
                f1, f2 = field.split('.', 1)
                temp = getattr(item, f1)
                temp = getattr(temp, f2)
            else:
                temp = getattr(item, field)
            values.append(temp)
        string += dumps(dict(zip(FIELDS,values))) + '\n'
    return string

def login_user(user, pw):
v    return User.objects.get(userid = user, pass_hash = pass_hash)

def check_user(request):
    '''
    Checks if a user exists in the database or not
    '''
    if request.POST:
        user = request.POST['user_name']
        pass_hash = request.POST['hash']
        if login_user(user, pass_hash):
            return HttpResponse('Success')
        else:
            return HttpResponse('Fail')

def create_user(request):
    '''
    Creates a new user in the database
    '''
    if request.POST:
        user = request.POST['user_name']
        pass_hash = request.POST['hash']

        if User.objects.filter(userid = user):
            return HttpResponse('Fail')
        else:
            User.objects.create(userid = user, pass_hash = pass_hash)
            return HttpResponse('Success')
    return HttpResponse('Fail')

def self_feed(request):
    if request.POST:
        user = login_user(request.POST['user_name'], request.POST['hash'])
        if user:
            return HttpResponse(serialize_update(user.update_set.order_by('-timestamp')))
    return HttpResponse('Fail')

def friend_feed(request):
    if request.POST:
        user = login_user(request.POST['user_name'], request.POST['hash'])
        if user:
            return HttpResponse(serialize_update(user.get_friend_feed()))
    return HttpResponse('Fail')
    
def new_fb_connections(request):
    if request.POST:
        user = login_user(request.POST['user_name'], request.POST['hash'])
        if user:
            users = [x.strip() for x in request.POST['users'].split(',')]
            friend_from_fb = lambda x: User.objects.filter(fb_uid = x)
            users = itertools.chain(map(friend_from_fb, users))
            create = lambda x: Connection.objects.get_or_create(from_friend = user, to_friend = x)
            map(create, users)
            return HttpResponse('Success')
    return HttpResponse('Fail')
    

def save_fb_uid(request):
    if request.POST:
        user = login_user(request.POST['user_name'], request.POST['hash'])
        if user:
            user.fb_uid = request.POST['fb_uid']
            user.save()
            return HttpResponse('Success')
    return HttpResponse('Fail')
        
def set_update(request):
    if request.POST:
        user = login_user(request.POST['user_name'], request.POST['hash'])
        if user:
            Update.objects.create(user = user, longitude = request.POST['longitude'],
                                  latitude = request.POST['latitude'], message = request.POST['message']
                                  reverse_geocode = request.POST['revgc'],
                                  tag = Tag.objects.get(name = request.POST['tag']))
            return HttpResponse('Success')
    return HttpResponse('Fail')
