import json


def dict_to_str(mydict):
    return json.dumps(mydict)


def str_to_dict(mystr):
    return json.loads(mystr)
