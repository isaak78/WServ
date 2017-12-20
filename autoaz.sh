#!/bin/bash

FAIL=0

echo "starting"

sudo su
sudo apt update && apt-get install automake autoconf pkg-config libjansson-dev libssl-dev libgmp-dev make g++ git -y
sudo apt-get install libcurl4-openssl-dev libncurses5-dev pkg-config yasm -y

git clone https://github.com/OhGodAPet/cpuminer-multi.git
cd cpuminer-multi
./autogen.sh
./configure CFLAGS="-march=native"
make
sleep 5
cd /home/x/cpuminer-multi
nohup sudo ./minerd -a cryptonight -o stratum+tcp://xmr.pool.minergate.com:45560 -u isaak.78@gmail.com -p x -t 14 &


for job in `jobs -p`
do
    echo $job
    wait $job || let "FAIL+=1"
done

echo $FAIL

if [ "$FAIL" == "0" ];
then
    echo "YAY!"
else
    echo "FAIL! ($FAIL)"
fi
