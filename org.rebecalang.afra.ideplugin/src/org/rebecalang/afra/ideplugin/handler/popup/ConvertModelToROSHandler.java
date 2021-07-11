package org.rebecalang.afra.ideplugin.handler.popup;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Shell;

public class ConvertModelToROSHandler extends AbstractPopupOnProjectExplorerHandler {

	public final static String ID = ConvertModelToROSHandler.class.getName();

	@CanExecute
	public boolean canExecute(MPart part) {
		return isSelectedItemIsFileAndHasExtension("rebeca");
	}

	@Execute
	public void execute(Shell shell) {
//		IFile selectedFile = (IFile)getSelectedItem();
//		IProject project = selectedFile.getProject();
//		File resultFolder = new File(selectedFile.getRawLocation().toString().substring(0,
//				selectedFile.getRawLocation().toString().lastIndexOf(".")) + "ROS");
//		Set<CompilerFeature> compilerFeatures = new HashSet<CompilerFeature>();
//
//		CompilerFeature version = CoreRebecaProjectPropertyPage.getProjectLanguageVersion(project);
//		compilerFeatures.add(version);
//		String type = CoreRebecaProjectPropertyPage.getProjectType(project);
//		if (type.equals("TimedRebeca")) {
//			compilerFeatures.add(CompilerFeature.TIMED_REBECA);
//		} else if (type.equals("ProbabilisitcTimedRebeca")) {
//			compilerFeatures.add(CompilerFeature.TIMED_REBECA);
//			compilerFeatures.add(CompilerFeature.PROBABILISTIC_REBECA);
//		}
//		Rebeca2ROSModelTransformer.getInstance().transformModel(
//				selectedFile.getRawLocation().toFile(), resultFolder, compilerFeatures, 
//				null);
//		ExceptionContainer container = Rebeca2ROSModelTransformer.getInstance().getExceptionContainer();
//		if (!container.getExceptions().isEmpty()) {
//			MessageDialog.openInformation(shell, "Transformation Report",
//					"Transforming " + selectedFile.getName() + " to ROS is failed. The model has compile errors.");			
//		}
//		try {
//			project.refreshLocal(IResource.DEPTH_INFINITE, null);
//		} catch (CoreException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
