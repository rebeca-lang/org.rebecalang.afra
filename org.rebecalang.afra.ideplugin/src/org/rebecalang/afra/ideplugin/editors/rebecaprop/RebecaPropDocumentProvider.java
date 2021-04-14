package org.rebecalang.afra.ideplugin.editors.rebecaprop;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.rebecalang.afra.ideplugin.editors.GeneralDocument;
import org.rebecalang.afra.ideplugin.editors.GeneralFileDocumentProvider;

public class RebecaPropDocumentProvider extends GeneralFileDocumentProvider {

	@Override
	protected GeneralDocument getDocument(IProject project, IFile file) {
		return new RebecaPropDocument(project, file);
	}
	
}
