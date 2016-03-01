#!/bin/sh

curl -H "Content-Type: application/json" -X POST -d '{"name":"Choco", "color":"brown"}' localhost:8080/cats; echo;
curl -H "Content-Type: application/json" -X POST -d '{"name":"Muffie", "color":"blue"}' localhost:8080/cats; echo;
curl -H "Content-Type: application/json" -X POST -d '{"name":"Ray", "color":"red"}' localhost:8080/cats; echo;
curl -H "Content-Type: application/json" -X POST -d '{"name":"Monica", "color":"black"}' localhost:8080/cats; echo;
echo;
curl -H "Content-Type: application/json" -X POST -d '{"description":"Cabinet Opener", "rate":"JEDI"}' localhost:8080/cats/Ray/special-power; echo;
curl -H "Content-Type: application/json" -X POST -d '{"description":"Meow Alarm", "rate":"JEDI"}' localhost:8080/cats/Muffie/special-power; echo;
curl -H "Content-Type: application/json" -X POST -d '{"description":"Tightrope Walker", "rate":"APPRENTICE"}' localhost:8080/cats/Ray/special-power; echo;
curl -H "Content-Type: application/json" -X POST -d '{"description":"Tightrope Walker", "rate":"JEDI"}' localhost:8080/cats/Choco/special-power; echo;
curl -H "Content-Type: application/json" -X POST -d '{"description":"Baby Maker", "rate":"JEDI_MASTER"}' localhost:8080/cats/Monica/special-power; echo;
echo;
curl -X GET localhost:8080/cats; echo;
echo;