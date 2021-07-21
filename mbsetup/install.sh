#!~/../usr/bin/bash
echo MiraiBot 安装程序

read -p "是否安装MiraiBot(y/n)> " sel
if (($sel == y))                                                      
then                                                                          
mkdir miraibot
cd miraibot
rm ~/../usr/etc/apt/source.list.d/games.list
rm ~/../usr/etc/apt/source.list.d/game.list
pkg update
pkg install wget tar p7zip
echo 正在下载java 11...
wget "https://ghproxy.com/https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk-11.0.11%2B9/OpenJDK11U-jre_aarch64_linux_hotspot_11.0.11_9.tar.gz" -O jre11.tar.gz
tar -zxvf jre11.tar.gz
mv jdk-11.* jre11
echo 正在下载MiraiBot最新版本..
wget "ghproxy.com/https://raw.githubusercontent.com/1689295608/MiraiBot/main/LatestVersion" -O ver.tmp
mbver=$(cat ver.tmp)
wget "ghproxy.com/https://github.com/1689295608/MiraiBot/releases/download/$mbver/MiraiBot_$mbver.7z" -O MiraiBot.7z
mkdir miraibot
cd miraibot
7z x MiralBot.7z
cd ..
echo 正在释放启动脚本...
echo "#!~/../usr/bin/bash">mirai.sh
echo "export PATH=\$(pwd)/jre11/bin:\$PATH">>mirai.sh
echo "miraibot/Start.sh">>mirai.sh
echo 清理文件中..
rm jre11.tar.gz
rm MiraiBot.7z
rm ver.tmp
else                                                
exit
fi
