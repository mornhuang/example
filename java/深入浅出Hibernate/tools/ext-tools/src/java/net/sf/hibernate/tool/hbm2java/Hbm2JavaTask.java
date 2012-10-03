package net.sf.hibernate.tool.hbm2java;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/**
 * Task for hbm2java (Hibernates codegenerator)
 * 
 *
 * @author GBegley and max
 *
 */
public class Hbm2JavaTask extends Task {

	private File configurationFile;
	private Path compileClasspath;
	private File outputDir;
	private List filesets = new ArrayList();

	/**
	 * Set a hbm2java <literal>config.xml</literal> configuration file 
	 * @param the file name
	 */
	public void setConfig(File configurationFile) {
		this.configurationFile = configurationFile;
	}

	/**
	   * Set the classpath to be used for this compilation.
	   *
	   * @param classpath an Ant Path object containing the compilation classpath.
	   */
	public void setClasspath(Path classpath) {
		if (compileClasspath == null) {
			compileClasspath = classpath;
		} else {
			compileClasspath.append(classpath);
		}
	}
	/** Gets the classpath to be used for this compilation. */
	public Path getClasspath() {
		return compileClasspath;
	}
	/**
	 * Adds a path to the classpath.
	 */
	public Path createClasspath() {
		if (compileClasspath == null) {
			compileClasspath = new Path(getProject());
		}
		return compileClasspath.createPath();
	}
	/**
	 * Adds a reference to a classpath defined elsewhere.
	 */
	public void setClasspathRef(Reference r) {
		createClasspath().setRefid(r);
	}

	/**
	   * Adds a set of files to translate.
	   */
	public void addFileset(FileSet set) {
		filesets.add(set);
	}

	/**
	   * Sets the output directory.
	   *
	   * @param binDirectory directory
	   */
	public void setOutput(File outDirectory) {
		this.outputDir = outDirectory;
	}

	public void execute() throws BuildException {
		
		List fileList = getTargetFiles();
		if (fileList.size() == 0)
			return;
		log("Processing " + fileList.size() + " mapping files.");
		try {			
			processFile(outputDir, (File[]) fileList.toArray(new File[fileList.size()]));			
		} catch (Throwable t) {
			StringWriter sw = new StringWriter();
			t.printStackTrace(new PrintWriter(sw));
			throw new BuildException("Caused by:\n" + sw.toString());
		}
	}
	/**
     * 
     * 
     * Comment:
     * The initial ant task had some initial filtering on the hbm.xml/java names to only process the needed files.
     * That is not possible to decide in the ant task without implementing the same logic present in hbm2java.
     * Thus I've removed it and let it be something that hbm2java should do.
     * 
     * 
	 * @return
	 */
	private List getTargetFiles() {
        List l = new java.util.ArrayList();
              // deal with the filesets
              for (int i = 0; i < filesets.size(); i++) {
                 FileSet fs = (FileSet) filesets.get(i);
                 File parent = fs.getDir( getProject() );
                 DirectoryScanner ds = fs.getDirectoryScanner(getProject());
                 String[] files = ds.getIncludedFiles();
                 for (int j = 0; j < files.length; j++) {
                    File srcFile = new File( parent, files[j] );
                    l.add( srcFile );                    
                 }
              }
              return l;
	}

	private void processFile(File outputDir, File[] targets) {
		List args = new ArrayList();
        
		if (outputDir != null) {
			args.add("--output=" + outputDir.getAbsolutePath());
		}

		if (configurationFile != null) {
			args.add("--config=" + configurationFile);

		}
        
		for (int i = 0; i < targets.length; i++) {
			args.add(targets[i].getAbsoluteFile().toString());	
		}
        

		try {
			net.sf.hibernate.tool.hbm2java.CodeGenerator.main((String[]) args.toArray(new String[args.size()]));
		} catch (Throwable t) {
			StringWriter sw = new StringWriter();
			t.printStackTrace(new PrintWriter(sw));
			throw new BuildException("Caused by:\n" + sw.toString());
		}
	}
}
