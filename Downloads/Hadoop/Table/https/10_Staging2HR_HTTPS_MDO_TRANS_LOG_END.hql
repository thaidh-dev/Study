alter VIEW 10_Staging2HR_HTTPS_MDO_TRANS_LOG_END 
AS 
select /*+ BROADCASTJOIN(b) */ a.*, b.cell_node_id from 10_Staging2HR_HTTPS_MDO_TRANS_LOG_END_SUB_1 a 
inner join 11_dim_cell_info_cells_current_4G b on a.cell_id = b.cell_id 
where a.cell_type='4G'

union all

select /*+ BROADCASTJOIN(b) */ a.*, b.cell_node_id from 10_Staging2HR_HTTPS_MDO_TRANS_LOG_END_SUB_1 a 
inner join 11_dim_cell_info_cells_current_2G_3G b on a.cell_id = b.cell_id and a.lac = b.lac
where a.cell_type<>'4G';