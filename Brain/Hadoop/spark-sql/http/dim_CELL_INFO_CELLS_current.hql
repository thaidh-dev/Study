CREATE TABLE dim_CELL_INFO_CELLS_current
using orc
partitioned by (CELL_TYPE)
as select * from 11_dim_cell_info_cells_current;


