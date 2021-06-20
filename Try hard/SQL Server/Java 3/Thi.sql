create database thi

use thi

create table sinh_vien (
ma_sv nvarchar(50) not null primary key,
ten nvarchar(50) not null,
email nvarchar(50) not null,
tuoi int not null
)

insert into sinh_vien values
('sv01', 'duong hong thai', 'duonghongthai@gmail.com', '22'),
('sv02', 'nguyen van a', 'duonghongthai@gmail.com', '22'),
('sv03', 'nguyen van b', 'duonghongthai@gmail.com', '22'),
('sv04', 'nguyen van c', 'duonghongthai@gmail.com', '22'),
('sv05', 'nguyen van d', 'duonghongthai@gmail.com', '22'),
('sv06', 'nguyen van e', 'duonghongthai@gmail.com', '22'),
('sv07', 'nguyen van f', 'duonghongthai@gmail.com', '22')

delete from sinh_vien where ma_sv = 'sv09'
update sinh_vien set tuoi = 45 where ma_sv = 'dsds'

select * from sinh_vien
