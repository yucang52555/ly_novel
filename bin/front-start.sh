# 拉取最新的源码
git pull

# 执行打包
mvn clean package -Pprod

java -jar /app/ly/server/docker/ly_novel/novel-front/target/novel-front-1.1.0.jar &

echo "novel-front部署完毕，Enjoy！"