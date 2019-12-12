import datetime, urllib3, ssl, os, uuid
from http import HTTPStatus, client
from locust import HttpLocust, TaskSet, task

# This test can be run after installing locust through the cli as "locust --host=http://<deployed_host>:<port>"
# Then url http://localhost:8089/ should be access to start the test.
# Can also be run using no UI mode as "locust --no-web -c <number_of_clients> -r <clients_per_second> --run-time <time e.g. 1h30m> --host=http://<deployed_host>:<port>"

# this will disable ssl disabled warnings
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)


class UserBehavior(TaskSet):

    # disable SSL verification for every request
    def on_start(self):
        self.client.verify = False

    def teardown(self):
        r = self.client.get("/greeting/")
        assert r.status_code == HTTPStatus.OK

    @task(1)
    def createGreeting(self):
        r = self.client.get("/greeting/" + uuid.uuid4())
        assert r.status_code == HTTPStatus.CREATED

class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 1000
    max_wait = 1000
