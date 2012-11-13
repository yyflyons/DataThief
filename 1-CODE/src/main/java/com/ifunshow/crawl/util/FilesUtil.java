package com.ifunshow.crawl.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesUtil {
	public static List getFiles(String dirname) {
		File dir = new File(dirname);
		File[] files = dir.listFiles();
		List file_names = new ArrayList();
		if(files.length > 0){
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile() && (!files[i].isHidden())) {
					String fileInfo[] = {files[i].getAbsolutePath(),files[i].getName()};
					file_names.add(fileInfo);
				}
			}
		}
		return file_names;
	}
}