/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.panel;

import org.adempiere.webui.component.Panel;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 2, 2007
 * @version $Revision: 0.10 $
 */
public class SideUserPanel extends Panel
{
    private static final long serialVersionUID = 1L;

    //private Hbox hBox;
    
    private UserPanel pnlUser;
    //private LogoutPanel pnlLogout;
    
    public SideUserPanel()
    {
        super();
        init();
    }
    
    private void init()
    {
        //hBox = new Hbox();
        pnlUser = new UserPanel();
        //pnlLogout = new LogoutPanel();
        
        //hBox.appendChild(pnlUser);
        //hBox.appendChild(pnlLogout);

        this.setWidth("100%");
        this.appendChild(new Separator());
        this.appendChild(pnlUser);
        this.appendChild(new Separator());
    }
}
