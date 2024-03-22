# 启动项目
nohup java -jar auth-1.0-SNAPSHOT.jar &> auth.log &

jps -l
ps -ef| grep java
kill -9 3600520
