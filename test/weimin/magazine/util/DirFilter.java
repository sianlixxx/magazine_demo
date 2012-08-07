package weimin.magazine.util;

import java.io.File;
import java.io.FilenameFilter;



/**
 * 
 * 
 */


public class DirFilter implements FilenameFilter {
	
	

	private String prefix = "", suffix = ""; //文件名的前缀、后缀

	public DirFilter(String filterstr) {
		filterstr = filterstr.toLowerCase();
		int i = filterstr.indexOf('*');
		int j = filterstr.indexOf('.');
		if (i > 0)
			prefix = filterstr.substring(0, i);
		if (j > 0)
			suffix = filterstr.substring(j + 1);
	}

	public boolean accept(File dir, String filename) {
		boolean yes = true;
		try {
			filename = filename.toLowerCase();
			yes = (filename.startsWith(prefix)) & (filename.endsWith(suffix));
		} catch (NullPointerException e) {
			
		}
		return yes;
	}

}
