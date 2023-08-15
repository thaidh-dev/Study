ssh root@192.168.56.102 "
    git clone https://github.com/opensourcecobol/Open-COBOL-ESQL.git
    apt update
    apt-get upgrade
    apt-get install -y gnucobol
    apt-get install -y autoconf
    apt-get install -y libtool
    apt-get install -y pkg-config
    apt-get install -y yacc
    apt-get install -y bison
    apt-get install -y flex
    export CPPFLAGS="-I/usr/include/postgresql"
    ./autogen.sh
    ./configure
    cd ocesql/
    flex -o scanner.c scanner.l 
    make
    make install
    echo 'DONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE' ;
"
exec $SHELL