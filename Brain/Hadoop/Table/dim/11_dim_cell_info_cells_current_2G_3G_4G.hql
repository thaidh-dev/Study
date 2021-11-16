CREATE VIEW 11_dim_cell_info_cells_current_4G
AS select * from dim_cell_info_cells_current where cell_type='4G';

CREATE VIEW 11_dim_cell_info_cells_current_2G_3G
AS select * from dim_cell_info_cells_current where cell_type<>'4G';