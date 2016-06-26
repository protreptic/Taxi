#!/bin/bash

SERVER_KEY=AIzaSyBdC8vPcitKCHG4Mr_S0cxCHsPwcq6eEn0
CLIENT_TOKEN=df3W6UkSpT4:APA91bEZg_voVT9zxebIQuENev-2hMjztyw1W59yTyn98xZGfIYZ-LJSNKDk219fX0_eUYi4BoLkCo_0186y31QPZrKDLl6vUyFfJbDWY4JxDgd3bTzW2YNkNN-QbZ46lIBdO_LvPQBh
# Идентификатор уведомления
EVENT_ID=$1

# Идентификатор заказа
ORDER_ID=$2

# Текст сообщения
BODY=$3

curl -i \
	-H "Content-Type:application/json" \
	-H "Authorization:key=$SERVER_KEY" \
	-X POST -d "{\"data\":{\"event_id\":$EVENT_ID, \"order_id\":$ORDER_ID, \"body\":\"$BODY\"},\"to\":\"$CLIENT_TOKEN\"}" \
	https://gcm-http.googleapis.com/gcm/send
