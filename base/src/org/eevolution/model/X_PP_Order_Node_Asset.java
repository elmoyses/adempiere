/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.I_A_Asset;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for PP_Order_Node_Asset
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_PP_Order_Node_Asset extends PO implements I_PP_Order_Node_Asset, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PP_Order_Node_Asset (Properties ctx, int PP_Order_Node_Asset_ID, String trxName)
    {
      super (ctx, PP_Order_Node_Asset_ID, trxName);
      /** if (PP_Order_Node_Asset_ID == 0)
        {
			setA_Asset_ID (0);
			setPP_Order_ID (0);
			setPP_Order_Node_Asset_ID (0);
			setPP_Order_Node_ID (0);
			setPP_Order_Workflow_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PP_Order_Node_Asset (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_PP_Order_Node_Asset[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_A_Asset getA_Asset() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_A_Asset.Table_Name);
        I_A_Asset result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_A_Asset)constructor.newInstance(new Object[] {getCtx(), new Integer(getA_Asset_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_ID is mandatory.");
		set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Asset.
		@return Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Order getPP_Order() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_PP_Order.Table_Name);
        org.eevolution.model.I_PP_Order result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_PP_Order)constructor.newInstance(new Object[] {getCtx(), new Integer(getPP_Order_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Manufacturing Order.
		@param PP_Order_ID Manufacturing Order	  */
	public void setPP_Order_ID (int PP_Order_ID)
	{
		if (PP_Order_ID < 1)
			 throw new IllegalArgumentException ("PP_Order_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PP_Order_ID, Integer.valueOf(PP_Order_ID));
	}

	/** Get Manufacturing Order.
		@return Manufacturing Order	  */
	public int getPP_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Manufacturing Order Activity Asset.
		@param PP_Order_Node_Asset_ID Manufacturing Order Activity Asset	  */
	public void setPP_Order_Node_Asset_ID (int PP_Order_Node_Asset_ID)
	{
		if (PP_Order_Node_Asset_ID < 1)
			 throw new IllegalArgumentException ("PP_Order_Node_Asset_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PP_Order_Node_Asset_ID, Integer.valueOf(PP_Order_Node_Asset_ID));
	}

	/** Get Manufacturing Order Activity Asset.
		@return Manufacturing Order Activity Asset	  */
	public int getPP_Order_Node_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_Node_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Order_Node getPP_Order_Node() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_PP_Order_Node.Table_Name);
        org.eevolution.model.I_PP_Order_Node result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_PP_Order_Node)constructor.newInstance(new Object[] {getCtx(), new Integer(getPP_Order_Node_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Manufacturing Order Activity.
		@param PP_Order_Node_ID Manufacturing Order Activity	  */
	public void setPP_Order_Node_ID (int PP_Order_Node_ID)
	{
		if (PP_Order_Node_ID < 1)
			 throw new IllegalArgumentException ("PP_Order_Node_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PP_Order_Node_ID, Integer.valueOf(PP_Order_Node_ID));
	}

	/** Get Manufacturing Order Activity.
		@return Manufacturing Order Activity	  */
	public int getPP_Order_Node_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_Node_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Order_Workflow getPP_Order_Workflow() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_PP_Order_Workflow.Table_Name);
        org.eevolution.model.I_PP_Order_Workflow result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_PP_Order_Workflow)constructor.newInstance(new Object[] {getCtx(), new Integer(getPP_Order_Workflow_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Manufacturing Order Workflow.
		@param PP_Order_Workflow_ID Manufacturing Order Workflow	  */
	public void setPP_Order_Workflow_ID (int PP_Order_Workflow_ID)
	{
		if (PP_Order_Workflow_ID < 1)
			 throw new IllegalArgumentException ("PP_Order_Workflow_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PP_Order_Workflow_ID, Integer.valueOf(PP_Order_Workflow_ID));
	}

	/** Get Manufacturing Order Workflow.
		@return Manufacturing Order Workflow	  */
	public int getPP_Order_Workflow_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_Workflow_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}