package org.rebecalang.afra.ideplugin.editors;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.swt.custom.StyleRange;

public class NonRuleBasedDamagerRepairer implements IPresentationDamager, IPresentationRepairer
{
	private IDocument document;
	private TextAttribute defaultTextAttribute;

	public NonRuleBasedDamagerRepairer(TextAttribute defaultTextAttribute)
	{
		this.defaultTextAttribute = defaultTextAttribute;
	}

	/**
	 * @see IPresentationRepairer#setDocument(IDocument)
	 */
	public void setDocument(IDocument document)
	{
		this.document = document;
	}

	/**
	 * Returns the end offset of the line that contains the specified offset or
	 * if the offset is inside a line delimiter, the end offset of the next line.
	 *
	 * @param offset the offset whose line end offset must be computed
	 * @return the line end offset for the given offset
	 * @exception BadLocationException if offset is invalid in the current document
	 */
	protected int endOfLineOf(int offset) throws BadLocationException
	{
		IRegion info = document.getLineInformationOfOffset(offset);
		if (offset <= info.getOffset() + info.getLength())
			return info.getOffset() + info.getLength();

		int line = document.getLineOfOffset(offset);
		try
		{
			info = document.getLineInformation(line + 1);
			return info.getOffset() + info.getLength();
		}
		catch (BadLocationException x)
		{
			return document.getLength();
		}
	}

	/**
	 * @see IPresentationDamager#getDamageRegion(ITypedRegion, DocumentEvent, boolean)
	 */
	public IRegion getDamageRegion(
		ITypedRegion partition,
		DocumentEvent event,
		boolean documentPartitioningChanged)
	{
		if (!documentPartitioningChanged)
		{
			try
			{

				IRegion info = document.getLineInformationOfOffset(event.getOffset());
				int start = Math.max(partition.getOffset(), info.getOffset());

				int len;
				if (event.getText() == null)
					len = event.getLength();
				else
					len = event.getText().length();
				int end = event.getOffset() + len;

				if (info.getOffset() <= end && end <= info.getOffset() + info.getLength())
				{
					// optimize the case of the same line
					end = info.getOffset() + info.getLength();
				}
				else
					end = endOfLineOf(end);

				end = Math.min(partition.getOffset() + partition.getLength(), end);
				return new Region(start, end - start);

			}
			catch (BadLocationException x)
			{
			}
		}

		return partition;
	}

	/**
	 * @see IPresentationRepairer#createPresentation(TextPresentation, ITypedRegion)
	 */
	public void createPresentation(TextPresentation presentation, ITypedRegion region)
	{
		addRange(presentation, region.getOffset(), region.getLength(), defaultTextAttribute);
	}

	/**
	 * Adds style information to the given text presentation.
	 *
	 * @param presentation the text presentation to be extended
	 * @param offset the offset of the range to be styled
	 * @param length the length of the range to be styled
	 * @param attr the attribute describing the style of the range to be styled
	 */
	protected void addRange(
		TextPresentation presentation,
		int offset,
		int length,
		TextAttribute attr)
	{
		if (attr != null)
		{
			presentation.addStyleRange(
				new StyleRange(
					offset,
					length,
					attr.getForeground(),
					attr.getBackground(),
					attr.getStyle()));
		}
	}
}