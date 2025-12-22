select null is null

select null = null

SELECT CONCAT_WS(',', null, 'two', 'three');

SELECT CONCAT(',', null, 'two', 'three');

select 'one' || null || 'two'

select concat_ws('one', null, 'two');  

select 15 / null
select 15 / 0








select LISTAGG('one', null, 'two');
.

















-- Create the table
CREATE TABLE sample_addresses (
    address_line_1 VARCHAR(255),  -- street address
    city           VARCHAR(100),  -- city name
    state          VARCHAR(100),  -- state or province
    postal_code    VARCHAR(20)    -- postal/zip code
);

-- Insert sample rows (some with NULLs)
INSERT INTO sample_addresses (address_line_1, city, state, postal_code) VALUES
('123 Main St', 'Seattle', 'WA', '98101'),
(NULL, 'Portland', 'OR', '97201'),
('456 Elm St', NULL, 'CA', '90001'),
('789 Oak Ave', 'Denver', NULL, '80202'),
('101 Pine Rd', 'Boston', 'MA', NULL);




select
    concat(
        nvl('one ', ''),
        nvl('two ', ''),
        nvl(null, ''),
        nvl('three', '')
    )
from sample_addresses





select 



select * from sample_addresses
