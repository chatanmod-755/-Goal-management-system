#!/usr/bin/env python
# -*- coding: utf-8 -*-

import subprocess
import time
import requests
from requests.auth import HTTPBasicAuth

def reboot_tomcat():
    #cmd -> tomcatを再起動するコマンド
    print("＝＝＝＝＝tomcat再起動＝＝＝＝＝＝")
    cmd = 'systemctl restart tomcat'
    subprocess.check_call(cmd, shell=True)
    while True:
        try:
            response = session.post(login_url[0], json={'user_id':'Yoshiki','user_password':'ok'}, timeout=10, auth=HTTPBasicAuth("yoshikings_higa58", "WBA_WBC_WBO_IBF_CHAMPION"))
        except requests.exceptions.ConnectionError:
            return
        time.sleep(120)

# ログインしたいURl一覧
login_url = ['https://uunionn.duckdns.org:8080/UNION/login/Login']
# 死活監視したいURl一覧
check_url_list = ['https://uunionn.duckdns.org:8080/UNION/login/login.jsp']

session = requests.Session()
while True:
    try:
        print("foo")
        response = session.post(login_url[0], json={'user_id':'Yoshiki','user_password':'ok'}, timeout=10, auth=HTTPBasicAuth("yoshikings_higa58", "WBA_WBC_WBO_IBF_CHAMPION"))
    except (requests.exceptions.ConnectionError,requests.exceptions.ReadTimeout):
        print("-----------")
        print("まじかーーーー")
        print("-----------")
        # ConnectionErrorだったらtomcatを再起動
        reboot_tomcat()
    if response.status_code != 200:
        reboot_tomcat()

    response = session.get(check_url_list[0], timeout=10, auth=HTTPBasicAuth("yoshikings_higa58", "WBA_WBC_WBO_IBF_CHAMPION"))
    print(response.status_code)
    if response.status_code != 200:
        reboot_tomcat()
    print('status is OK!')
    # 何秒感覚で確認するか
    time.sleep(60)