package org.adempiere.ad.migration.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.Services;
import org.compiere.util.CLogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLWriter
{
	public static final String NODENAME_Migrations = "Migrations";

	private final transient CLogger logger = CLogger.getCLogger(getClass());

	private final String fileName;

	private OutputStream outputStream = null;

	public XMLWriter(String fileName)
	{
		this.fileName = fileName;
	}

	public XMLWriter(OutputStream is)
	{
		this.fileName = null;
		this.outputStream = is;
	}

	public void write(I_AD_Migration migration)
	{
		try
		{
			write0(migration);
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
	}

	private void write0(I_AD_Migration migration) throws ParserConfigurationException, TransformerException, IOException
	{
		if (migration == null || migration.getAD_Migration_ID() <= 0)
		{
			throw new AdempiereException("No migration to export. Migration is null or new.");
		}

		logger.log(Level.FINE, "Creating xml document for migration: " + migration);

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final Document document = builder.newDocument();
		final Element root = document.createElement(NODENAME_Migrations);
		document.appendChild(root);

		final IXMLHandlerFactory converterFactory = Services.get(IXMLHandlerFactory.class);
		final IXMLHandler<I_AD_Migration> converter = converterFactory.getHandler(I_AD_Migration.class);
		final Node migrationNode = converter.toXmlNode(document, migration);
		root.appendChild(migrationNode);

		// set up a transformer
		final TransformerFactory transFactory = TransformerFactory.newInstance();
		transFactory.setAttribute("indent-number", 2);
		final Transformer trans = transFactory.newTransformer();

		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		trans.setOutputProperty(OutputKeys.STANDALONE, "yes");

		logger.log(Level.FINE, "Writing xml to file.");
		Writer writer = null;
		try
		{
			writer = getWriter();
			final StreamResult result = new StreamResult(writer);
			final DOMSource source = new DOMSource(document);
			trans.transform(source, result);
		}
		finally
		{
			if (writer != null)
			{
				try
				{
					writer.close();
				}
				catch (IOException e)
				{
				}
				writer = null;
			}
		}

	}

	private Writer getWriter() throws IOException
	{
		if (fileName != null)
		{
			return new FileWriter(fileName);
		}
		else if (outputStream != null)
		{
			return new OutputStreamWriter(outputStream);
		}
		else
		{
			throw new AdempiereException("Cannot identify target stream");
		}
	}
}
