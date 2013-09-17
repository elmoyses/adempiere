﻿--DROP VIEW RV_PP_Product_BOMLine_Storage;
CREATE OR REPLACE VIEW RV_PP_Product_BOMLine_Storage AS 
SELECT  
t.ad_client_id,
t.ad_org_id, 
t.createdby, 
t.updatedby, 
t.updated,
t.created, 
t.ad_pinstance_id,
t.seqno, 
t.levelno, 
t.levels, 
t.m_product_id as tm_product_id,
t.sel_product_id as m_product_id,
t.QtyBOM,
t.DateTrx,
bl.isactive, 
bl.pp_product_bom_id, 
bl.pp_product_bomline_id, 
bl.description, 
bl.iscritical, 
bl.componenttype,  
bl.c_uom_id,
bl.issuemethod, 
bl.line, 
bl.m_attributesetinstance_id, 
bl.scrap,
bl.validfrom, 
bl.validto, 
bl.isqtypercentage,
bl.BackflushGroup,
bl.LeadTimeOffset,
s.qtyonhand,
round(t.qtyrequired, 4) AS qtyrequired, 
round(bomqtyreserved(t.m_product_id,t.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(t.m_product_id, t.m_warehouse_id,0), 4) AS qtyavailable,
t.m_warehouse_id,
l.m_locator_id,
l.x,
l.y,
l.z
FROM t_bomline t 
LEFT JOIN PP_Product_BOMLine bl ON (t.PP_Product_BOMLine_ID = bl.PP_Product_BOMLine_ID)
LEFT JOIN M_Storage s ON (s.M_Product_ID = t.m_product_id AND s.qtyonhand <> 0 AND EXISTS ( SELECT 1 FROM M_Locator ld WHERE s.M_Locator_ID = ld.M_Locator_ID AND ld.M_Warehouse_ID=t.M_Warehouse_ID))
LEFT JOIN M_Locator l ON (l.M_Locator_ID = s.M_Locator_ID)