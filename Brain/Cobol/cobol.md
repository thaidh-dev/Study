các câu lệnh để cài cobol và Open-COBOL-ESQL (để connect postgres)
cài trên linux or ubuntu
có thể sẽ thiếu nhiều thư viện khác, thiếu thì cài thêm
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

trong Open-COBOL-ESQL có folder sample, có chứa code mẫu connect db
đó là file FETCHTBL.cbl, chỉ cần mở file này ra chỉnh lại vài thông tin đăng nhập db là đc
    cd sample/
    export COBCPY=/var/opt/geminiot/cobol/Open-COBOL-ESQL/copy
    ocesql FETCHTBL.cbl FETCHTBL.cob
    cobc -x -locesql -static FETCHTBL.cob
    ./FETCHTBL

Lưu ý ko chỉ đinh host và port thì nó sẽ tự hiểu đang muốn connect vào db local
còn muốn chỉ định thì như hình