import requests

reply = requests.get("https://api.github.com/user", auth=("user", "pass"))

# print(requests.codes.__dict__) # all the status codes
print(requests.codes.ok) # 200
print(reply.status_code) # 404
print(reply.json()) # convert the response to json
print(type(reply.json())) # <type 'dict'>
print(reply.text) # convert the response to text
print(reply.content) # convert the response to bytes

# requests.Timeout # Catching this error will catch both ~requests.exceptions.ConnectTimeout and ~requests.exceptions.ReadTimeout errors.