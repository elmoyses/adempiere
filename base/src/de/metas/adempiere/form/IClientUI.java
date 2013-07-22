/**
 * 
 */
package de.metas.adempiere.form;

import org.adempiere.util.ISingletonService;

/**
 * Client's User Interface
 * 
 * @author cg
 * 
 */
public interface IClientUI extends IClientUIInstance, ISingletonService
{
	/**
	 * Create an UI Instance in current running user session/desktop
	 * 
	 * @return
	 */
	IClientUIInstance createInstance();
}
