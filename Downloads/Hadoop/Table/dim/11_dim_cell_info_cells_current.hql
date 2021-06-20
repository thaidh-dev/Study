CREATE VIEW 11_dim_cell_info_cells_current 
AS 
select c.* from dim_cell_info_cells c 
inner join (select max(d.cell_node_id) as cell_node_id, d.cell_id FROM dim_CELL_INFO_CELLS d
where d.cell_type = '4G'
group by d.cell_id) a on a.cell_node_id = c.cell_node_id

union all

select c.* from dim_cell_info_cells c 
inner join (select max(d.cell_node_id) as cell_node_id, d.cell_id, d.lac FROM dim_CELL_INFO_CELLS d
where d.cell_type <> '4G'
group by d.cell_id, d.lac) a on a.cell_node_id = c.cell_node_id;