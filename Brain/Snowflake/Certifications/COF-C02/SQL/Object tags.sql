CREATE TAG TAG1;
CREATE TAG TAG2;
CREATE TAG TAG3;
CREATE TAG TAG4;
CREATE TAG TAG5;
CREATE TAG TAG6;
CREATE TAG TAG7;
CREATE TAG TAG8;
CREATE TAG TAG9;
CREATE TAG TAG10;

CREATE TAG TAG11;
CREATE TAG TAG12;
CREATE TAG TAG13;
CREATE TAG TAG14;
CREATE TAG TAG15;
CREATE TAG TAG16;
CREATE TAG TAG17;
CREATE TAG TAG18;
CREATE TAG TAG19;
CREATE TAG TAG20;

CREATE TAG TAG21;
CREATE TAG TAG22;
CREATE TAG TAG23;
CREATE TAG TAG24;
CREATE TAG TAG25;
CREATE TAG TAG26;
CREATE TAG TAG27;
CREATE TAG TAG28;
CREATE TAG TAG29;
CREATE TAG TAG30;

CREATE TAG TAG31;
CREATE TAG TAG32;
CREATE TAG TAG33;
CREATE TAG TAG34;
CREATE TAG TAG35;
CREATE TAG TAG36;
CREATE TAG TAG37;
CREATE TAG TAG38;
CREATE TAG TAG39;
CREATE TAG TAG40;

CREATE TAG TAG41;
CREATE TAG TAG42;
CREATE TAG TAG43;
CREATE TAG TAG44;
CREATE TAG TAG45;
CREATE TAG TAG46;
CREATE TAG TAG47;
CREATE TAG TAG48;
CREATE TAG TAG49;
CREATE TAG TAG50;
CREATE TAG TAG51;
CREATE TAG TAG52;
CREATE TAG TAG53;
CREATE TAG TAG54;
CREATE TAG TAG55;
CREATE TAG TAG56;
CREATE TAG TAG57;
CREATE TAG TAG58;
CREATE TAG TAG59;
CREATE TAG TAG60;
CREATE TAG TAG61;
CREATE TAG TAG62;
CREATE TAG TAG63;
CREATE TAG TAG64;
CREATE TAG TAG65;
CREATE TAG TAG66;
CREATE TAG TAG67;
CREATE TAG TAG68;
CREATE TAG TAG69;
CREATE TAG TAG70;
CREATE TAG TAG71;
CREATE TAG TAG72;
CREATE TAG TAG73;
CREATE TAG TAG74;
CREATE TAG TAG75;
CREATE TAG TAG76;
CREATE TAG TAG77;
CREATE TAG TAG78;
CREATE TAG TAG79;
CREATE TAG TAG80;
CREATE TAG TAG81;
CREATE TAG TAG82;
CREATE TAG TAG83;
CREATE TAG TAG84;
CREATE TAG TAG85;
CREATE TAG TAG86;
CREATE TAG TAG87;
CREATE TAG TAG88;
CREATE TAG TAG89;
CREATE TAG TAG90;
CREATE TAG TAG91;
CREATE TAG TAG92;
CREATE TAG TAG93;
CREATE TAG TAG94;
CREATE TAG TAG95;
CREATE TAG TAG96;
CREATE TAG TAG97;
CREATE TAG TAG98;
CREATE TAG TAG99;
CREATE TAG TAG100;
CREATE TAG TAG101;
CREATE TAG TAG102;
CREATE TAG TAG103;
CREATE TAG TAG104;
CREATE TAG TAG105;


CREATE OR REPLACE TABLE t1 (c1 INT)
  WITH TAG (
    tag1 = 'val1',
    tag2 = 'val2',
    tag3 = 'val3',
    tag4 = 'val4',
    tag5 = 'val5',
    tag6 = 'val6',
    tag7 = 'val7',
    tag8 = 'val8',
    tag9 = 'val9',
    tag10 = 'val10',
    tag11 = 'val11',
    tag12 = 'val12',
    tag13 = 'val13',
    tag14 = 'val14',
    tag15 = 'val15',
    tag16 = 'val16',
    tag17 = 'val17',
    tag18 = 'val18',
    tag19 = 'val19',
    tag20 = 'val20',
    tag21 = 'val21',
    tag22 = 'val22',
    tag23 = 'val23',
    tag24 = 'val24',
    tag25 = 'val25',
    tag26 = 'val26',
    tag27 = 'val27',
    tag28 = 'val28',
    tag29 = 'val29',
    tag30 = 'val30',
    tag31 = 'val31',
    tag32 = 'val32',
    tag33 = 'val33',
    tag34 = 'val34',
    tag35 = 'val35',
    tag36 = 'val36',
    tag37 = 'val37',
    tag38 = 'val38',
    tag39 = 'val39',
    tag40 = 'val40',
    tag41 = 'val41',
    tag42 = 'val42',
    tag43 = 'val43',
    tag44 = 'val44', 
    tag45 = 'val45',
    tag46 = 'val46',
    tag47 = 'val47',
    tag48 = 'val48',
    tag49 = 'val49',
    tag50 = 'val50'
  );
CREATE OR REPLACE TABLE t2 (c1 INT)
  TAG (
    tag52 = 'val52',
    tag53 = 'val53',
    tag54 = 'val54',
    tag55 = 'val55',
    tag56 = 'val56',
    tag57 = 'val57',
    tag58 = 'val58',
    tag59 = 'val59',
    tag60 = 'val60',
    tag61 = 'val61',
    tag62 = 'val62',
    tag63 = 'val63',
    tag64 = 'val64',
    tag65 = 'val65',
    tag66 = 'val66',
    tag67 = 'val67',
    tag68 = 'val68',
    tag69 = 'val69',
    tag70 = 'val70',
    tag71 = 'val71',
    tag72 = 'val72',
    tag73 = 'val73',
    tag74 = 'val74',
    tag75 = 'val75',
    tag76 = 'val76',
    tag77 = 'val77',
    tag78 = 'val78',
    tag79 = 'val79',
    tag80 = 'val80',
    tag81 = 'val81',
    tag82 = 'val82',
    tag83 = 'val83',
    tag84 = 'val84',
    tag85 = 'val85',
    tag86 = 'val86',
    tag87 = 'val87',
    tag88 = 'val88',
    tag89 = 'val89',
    tag90 = 'val90',
    tag91 = 'val91',
    tag92 = 'val92',
    tag93 = 'val93',
    tag94 = 'val94',
    tag95 = 'val95',
    tag96 = 'val96',
    tag97 = 'val97',
    tag98 = 'val98',
    tag99 = 'val99',
    tag100 = 'val100'
);

CREATE OR REPLACE TABLE t3 (c1 INT)
  WITH TAG (
    tag101 = 'val101',
    tag102 = 'val102',
    tag103 = 'val103',
    tag104 = 'val104',
    tag105 = 'val105'
);

alter table t3 set tag tag7 = 'val7';
ALTER TABLE t3 UNSET TAG tag7, tag8;
select * from table(information_schema.tag_references('t3', 'table'));
select * from table(information_schema.tag_references_all_columns('t3', 'table'));


select * from table(information_schema.tag_references('snowflake.core', 'schema'));

CREATE TAG cost_center ALLOWED_VALUES 'finance', 'engineering', 'marketing';

SHOW TAGS;



-- 20






