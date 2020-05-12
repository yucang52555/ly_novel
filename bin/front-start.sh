# 拉取最新的源码
git pull

# 执行打包
mvn clean package -Pprod

java -jar ../novel-front/target/novel-front-1.1.0.jar --spring.profiles.active=prod  &

echo "novel-front部署完毕，Enjoy！"