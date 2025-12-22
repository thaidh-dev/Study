SELECT GET_PRESIGNED_URL(@test.public.MY_INT_STAGE, 'tomcat.png', 3600);
SELECT GET_PRESIGNED_URL(@test.public.MY_INT_STAGE, '49.csv', 3600);

ls @test.public.MY_INT_STAGE

desc stage test.public.MY_INT_STAGE