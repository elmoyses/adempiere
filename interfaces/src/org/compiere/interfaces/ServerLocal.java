/*
 * Generated by XDoclet - Do not edit!
 */
package org.compiere.interfaces;

/**
 * Local interface for adempiere/Server.
 */
public interface ServerLocal
   extends javax.ejb.EJBLocalObject
{
   /**
    * Get and create Window Model Value Object
    * @param ctx Environment Properties
    * @param WindowNo number of this window
    * @param AD_Window_ID the internal number of the window, if not 0, AD_Menu_ID is ignored
    * @param AD_Menu_ID ine internal menu number, used when AD_Window_ID is 0
    * @return initialized Window Model    */
   public org.compiere.model.GridWindowVO getWindowVO( java.util.Properties ctx,int WindowNo,int AD_Window_ID,int AD_Menu_ID ) ;

   /**
    * Post Immediate
    * @param ctx Client Context
    * @param AD_Client_ID Client ID of Document
    * @param AD_Table_ID Table ID of Document
    * @param Record_ID Record ID of this document
    * @param force force posting
    * @param trxName transaction
    * @return null, if success or error message    */
   public java.lang.String postImmediate( java.util.Properties ctx,int AD_Client_ID,int AD_Table_ID,int Record_ID,boolean force,java.lang.String trxName ) ;

   /**
    * Get Prepared Statement ResultSet
    * @param info Result info
    * @return RowSet
    * @throws NotSerializableException    */
   public javax.sql.RowSet pstmt_getRowSet( org.compiere.util.CStatementVO info ) throws java.io.NotSerializableException;

   /**
    * Get Statement ResultSet
    * @param info Result info
    * @return RowSet    */
   public javax.sql.RowSet stmt_getRowSet( org.compiere.util.CStatementVO info ) ;

   /**
    * Execute Update
    * @param info Result info
    * @return row count    */
   public int stmt_executeUpdate( org.compiere.util.CStatementVO info ) ;

   /**
    * Get next number for Key column = 0 is Error.
    * @param AD_Client_ID client
    * @param TableName table name
    * @param trxName optional Transaction Name
    * @return next no    */
   public int getNextID( int AD_Client_ID,java.lang.String TableName,java.lang.String trxName ) ;

   /**
    * Get Document No from table
    * @param AD_Client_ID client
    * @param TableName table name
    * @param trxName optional Transaction Name
    * @return document no or null    */
   public java.lang.String getDocumentNo( int AD_Client_ID,java.lang.String TableName,java.lang.String trxName ) ;

   /**
    * Get Document No based on Document Type
    * @param C_DocType_ID document type
    * @param trxName optional Transaction Name
    * @return document no or null    */
   public java.lang.String getDocumentNo( int C_DocType_ID,java.lang.String trxName ) ;

   /**
    * Process Remote
    * @param ctx Context
    * @param pi Process Info
    * @return resulting Process Info    */
   public org.compiere.process.ProcessInfo process( java.util.Properties ctx,org.compiere.process.ProcessInfo pi ) ;

   /**
    * Run Workflow (and wait) on Server
    * @param ctx Context
    * @param pi Process Info
    * @param AD_Workflow_ID id
    * @return process info    */
   public org.compiere.process.ProcessInfo workflow( java.util.Properties ctx,org.compiere.process.ProcessInfo pi,int AD_Workflow_ID ) ;

   /**
    * Online Payment from Server
    * @param ctx Context
    * @param C_Payment_ID payment
    * @param C_PaymentProcessor_ID processor
    * @param trxName transaction
    * @return true if approved    */
   public boolean paymentOnline( java.util.Properties ctx,int C_Payment_ID,int C_PaymentProcessor_ID,java.lang.String trxName ) ;

   /**
    * Create EMail from Server (Request User)
    * @param ctx Context
    * @param AD_Client_ID client
    * @param to recipient email address
    * @param subject subject
    * @param message message
    * @return EMail    */
   public org.compiere.util.EMail createEMail( java.util.Properties ctx,int AD_Client_ID,java.lang.String to,java.lang.String subject,java.lang.String message ) ;

   /**
    * Create EMail from Server (Request User)
    * @param ctx Context
    * @param AD_Client_ID client
    * @param AD_User_ID user to send email from
    * @param to recipient email address
    * @param subject subject
    * @param message message
    * @return EMail    */
   public org.compiere.util.EMail createEMail( java.util.Properties ctx,int AD_Client_ID,int AD_User_ID,java.lang.String to,java.lang.String subject,java.lang.String message ) ;

   /**
    * Create EMail from Server (Request User)
    * @param AD_Task_ID task
    * @return execution trace    */
   public java.lang.String executeTask( int AD_Task_ID ) ;

   /**
    * Cash Reset
    * @param tableName table name
    * @param Record_ID record or 0 for all
    * @return number of records reset    */
   public int cacheReset( java.lang.String tableName,int Record_ID ) ;

   /**
    * LOB update
    * @param sql table name
    * @param displayType display type (i.e. BLOB/CLOB)
    * @param value the data
    * @return true if updated    */
   public boolean updateLOB( java.lang.String sql,int displayType,java.lang.Object value ) ;

   /**
    * Describes the instance and its content for debugging purpose
    * @return Debugging information about the instance and its content    */
   public java.lang.String getStatus(  ) ;

   /**
    * Commit the named transaction on server
    * @param trxName
    * @return true if success, false otherwise    */
   public boolean commit( java.lang.String trxName ) ;

   /**
    * Rollback the named transaction on server
    * @param trxName
    * @return true if success, false otherwise    */
   public boolean rollback( java.lang.String trxName ) ;

}
