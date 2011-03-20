"""
This file demonstrates two different styles of tests (one doctest and one
unittest). These will both pass when you run "manage.py test".

Replace these with more appropriate tests for your application.
"""

from django.test import TestCase
from api.views import *
from api.models import *

class SimpleTest(TestCase):
    def test_basic_addition(self):
        """
        Tests that 1 + 1 always equals 2.
        """
        self.failUnlessEqual(1 + 1, 2)

__test__ = {"doctest": """
Another way to test that 1 + 1 is equal to 2.

>>> 1 + 1 == 2
True
"""}

class GettersTest(TestCase):
    def setUp(self):
        vivek = User.objects.create(userid = "vivek", pass_hash = "h2hiu")
        foo = User.objects.create(userid = "foo", pass_hash = "h2hiu")
        Update.objects.create(user = vivek, latitude = '78.9111', longitude = '17.7878',
                              message = 'Howdy!', reverse_geocode = 'The Middle of Nowhere')

    def tearDown(self):
        

    def test_login_user(self):
        response = self.client.post('/check_user/', {'user_name': 'vivek', 'hash': 'h2hiu'})
        self.assertEquals(response.contents, 'Success')
        response = self.client.post('/check_user/', {'user_name': 'vvek', 'hash': 'h2hiu'})
        self.assertEquals(response.contents, 'Fail')
        response = self.client.post('/check_user/', {'user_name': 'vivek', 'hash': 'h22hiu'})
        self.assertEquals(response.contents, 'Fail')
