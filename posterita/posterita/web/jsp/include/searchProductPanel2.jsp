<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * @author Praveen
--%>


<% 
	String minChars = "1";
%>					


<logic:present cookie="preference.minChars">
	<bean:cookie id="minCharsCookie" name="preference.minChars"/>
	<% 
		if (minCharsCookie.getValue() != null || !minCharsCookie.getValue().equals(""))
			minChars = minCharsCookie.getValue();
	%>					
</logic:present>

						<select class="text searchBox" style="width:50%" id="productQuery">
						
						</select>
						<input type="text" class="text searchBox" style="width:100%" id="productQuery" accesskey="p"/>
						<div id="productSearchResult" class="autocomplete"></div>
						
						<script>
							var productAutocompleter = new Ajax.Autocompleter('productQuery','productSearchResult','SearchProductsAction.do',{
								paramName:'productName',
								minChars:<%=minChars%>,
								frequency:1.0,
								afterUpdateElement:function(e1,e2){													
												
													var barcode = e2.getAttribute('barcode');
													if(barcode=='null')
													{
														barcode = "";
													}
													var productId = e2.getAttribute('productId');													
													document.getElementsByName('barCode')[0].value = barcode;
													document.getElementsByName('productId')[0].value= productId;
													
													addToCart();													
																																									
													}												
								});
								
							$('productQuery').Autocompleter = productAutocompleter;
						</script>					