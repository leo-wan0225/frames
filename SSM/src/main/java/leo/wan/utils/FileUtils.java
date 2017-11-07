package leo.wan.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 
 * @Title 文件操作工具类,随着经验的增长需要不断完善和更新
 * @Description 封装了对文件的一系列操作
 * @author leo
 * @date 2017年10月2日 上午11:29:58
 */
public class FileUtils {

	/**
	 * 一次读取文件的缓存大小
	 */
	private final static int BUFFER_SIZE = 1024;

	private FileUtils() {

	}

	/**
	 * 文件剪切，允许剪切后重命名
	 * 
	 * @param from
	 *            源文件路径
	 * @param to
	 *            目标文件路径
	 * @param overwrite
	 *            是否覆盖
	 * @throws IOException
	 */
	public static void move(String from, String to, boolean overwrite)
			throws IOException {
		File fromFile = new File(from);
		File toFile = new File(to);
		move(fromFile, toFile, overwrite);
	}

	/**
	 * 文件剪切，允许剪切后重命名
	 * 
	 * @param from
	 *            源文件路径
	 * @param to
	 *            目标文件路径
	 * @param overwrite
	 *            是否覆盖
	 * @throws IOException
	 */
	public static void move(File fromFile, File toFile, boolean overwrite)
			throws IOException {
		// 对目标路径进行判断
		if (toFile.exists()) {
			if (overwrite) {
				if (!toFile.delete()) {
					throw new IOException("delete error");
				}
			} else {
				throw new IOException("already exists error");
			}
		}
		if (fromFile.renameTo(toFile))
			return;
		copy(fromFile, toFile, true);
		// 复制完成后删除源文件
		if (!fromFile.delete()) {
			throw new IOException("delete original error");
		}
	}

	/**
	 * 复制文件，允许重命名
	 * 
	 * @param from
	 *            源文件
	 * @param to
	 *            目标文件
	 * @param overwrite
	 *            是否覆盖
	 * @throws IOException
	 */
	public static void copy(String from, String to, boolean overwrite)
			throws IOException {
		File fromFile = new File(from);
		File toFile = new File(to);
		copy(fromFile, toFile, overwrite);
	}

	/**
	 * 复制文件，允许重命名
	 * 
	 * @param from
	 *            源文件
	 * @param to
	 *            目标文件
	 * @param overwrite
	 *            是否覆盖
	 * @throws IOException
	 */
	public static void copy(File fromFile, File toFile, boolean overwrite)
			throws IOException {

		// 对目标路径进行判断
		if (toFile.exists()) {
			if (overwrite) {
				if (!toFile.delete()) {
					throw new IOException("delete error");
				}
			} else {
				throw new IOException("already exists error");
			}
		}
		// 复制文件操作，默认是覆盖文件
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(fromFile);
			out = new FileOutputStream(toFile);
			byte[] buffer = new byte[BUFFER_SIZE];
			int read;
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
		} finally {
			if (in != null) {
				in.close();
				in = null;
			}
			if (out != null) {
				out.flush();
				out.close();
				out = null;
			}
		}

	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 *            要删除的文件路径
	 * @return
	 */
	public static boolean delete(String path) {
		File localFile = new File(path);
		if (localFile.exists())
			return localFile.delete();
		return true;
	}

	/**
	 * 判断目标文件是否存在
	 * 
	 * @param path
	 *            目标文件路径
	 * @return
	 */
	public static boolean exist(String path) {
		if (path == null)
			return false;
		return new File(path).exists();
	}

	/**
	 * 判断文件是否是目录
	 * 
	 * @param path
	 *            目标文件
	 * @return
	 */
	public static boolean isDirectory(String path) {
		return new File(path).isDirectory();
	}

	/**
	 * 将文本文件中的内容读取出来
	 * 
	 * @param file
	 *            目标文件
	 * @return 文本内容
	 * @throws IOException
	 */
	public static String getStrFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		if (file.length() > 2147483647L) {
			fis.close();
			throw new IOException("File is to large " + file.getName());
		}
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,
				"utf-8"));
		while (true) {
			String str = br.readLine();
			if (str == null) {
				fis.close();
				return sb.toString();
			}
			sb.append(str);
			sb.append("\n");
		}
	}

	/**
	 * 创建指定的目录。 如果指定的目录的父目录不存在则创建其目录书上所有需要的父目录以及目标目录。
	 * 
	 * @param file
	 *            要创建的目录
	 * @return 创建成功或者存在返回true，否则返回false
	 */
	public static boolean makeDirectory(File file) {
		if (!file.exists()) {
			return file.mkdirs();
		}
		return true;
	}

	/**
	 * 文件存在，返回存在文件的大小；文件不存在，创建文件返回0
	 * 
	 * @param path
	 * @return
	 */
	public static int makeSureFileExistAndGetSize(String path) {
		File file = new File(path);
		if (!file.exists())
			try {
				if (file.createNewFile())
					return 0;
			} catch (IOException ex) {
				return -1;
			}
		return (int) file.length();
	}

	/**
	 * 根据uuid生成唯一的文件文件名
	 * 
	 * @param suffix
	 *            文件后缀名,不用带.
	 * @return 文件名
	 */
	public static String getUniqueFileName(String suffix) {
		return UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
	}

	/**
	 * 清空指定目录下的所有为空的目录和文件
	 * 
	 * @param directory
	 *            要清空的目录
	 * @return 目录下的所有内容都被成功删除时返回true，否则返回false.
	 */
	public static boolean emptyDirectory(File directory) {
		boolean result = true;
		File[] entries = directory.listFiles();
		for (int i = 0; i < entries.length; i++) {
			if (!entries[i].delete()) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 删除指定目录及递归删除其中的所有内容。
	 * 
	 * @param dir
	 *            要删除的目录
	 * @return 删除成功时返回true，否则返回false。
	 */
	public static boolean removeDirectory(File dir) {
		if ((dir == null) || !dir.isDirectory()) {
			throw new IllegalArgumentException("Argument " + dir
					+ " is not a directory. ");
		}
		File[] entries = dir.listFiles();
		for (int i = 0; i < entries.length; i++) {
			if (entries[i].isDirectory()) {
				if (!removeDirectory(entries[i])) {
					return false;
				}
			} else {
				if (!entries[i].delete()) {
					return false;
				}
			}
		}
		if (!dir.delete()) {
			return false;
		}
		return true;
	}

	/**
	 * 列出目录中的所有内容，包括其子目录中的内容。
	 * 
	 * @param file
	 *            要列出的目录
	 * @param filter
	 *            过滤器
	 * @return 目录内容的文件数组。
	 * @since 1.0
	 */
	public static File[] listAll(File file, FileFilter filter) {
		ArrayList list = new ArrayList();
		File[] files;
		if (!file.exists() || file.isFile()) {
			return null;
		}
		list(list, file, filter);
		files = new File[list.size()];
		list.toArray(files);
		return files;
	}

	/**
	 * 将目录中的内容添加到列表。
	 * 
	 * @param list
	 *            文件列表
	 * @param file
	 *            目录
	 * @param filter
	 *            过滤器
	 */
	private static void list(ArrayList list, File file, FileFilter filter) {
		if (filter.accept(file)) {
			list.add(file);
			if (file.isFile()) {
				return;
			}
		}
		if (file.isDirectory()) {
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				list(list, files[i], filter);
			}
		}

	}

	/**
	 * 返回文件的URL地址。
	 * 
	 * @param file
	 *            目标文件
	 * @return 文件对应的的URL地址
	 * @throws MalformedURLException
	 */
	public static URL getURL(File file) throws MalformedURLException {

		return file.toURI().toURL();
	}

	/**
	 * 将DOS/Windows格式的路径转换为UNIX/Linux格式的路径。
	 * 其实就是将路径中的"\"全部换为"/"，因为在某些情况下我们转换为这种方式比较方便，
	 * 某中程度上说"/"比"\"更适合作为路径分隔符，而且DOS/Windows也将它当作路径分隔符。
	 * 
	 * @param filePath
	 *            转换前的路径
	 * @return 转换后的路径
	 */
	public static String toUNIXpath(String filePath) {
		return filePath.replace('\\', '/');
	}

	/**
	 * 从文件名得到UNIX风格的文件绝对路径。
	 * 
	 * @param fileName
	 *            文件名
	 * @return 对应的UNIX风格的文件路径
	 */
	public static String getUNIXfilePath(String fileName) {
		File file = new File(fileName);
		return toUNIXpath(file.getAbsolutePath());
	}

	/**
	 * 得到父路径
	 * 
	 * @param fileName
	 *            目标文件
	 * @return 目标文件的父路径
	 */
	public static String getParentPath(String fileName) {
		File file = new File(fileName);
		return file.getParent();
	}

	/**
	 * 得到文件扩展名
	 * 
	 * @return 文件的扩展名
	 */
	public static String getExtension(File file) {
		String extension = "";
		if (file.isFile()) {
			String fileName = file.getName();
			extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return extension;
	}

	/**
	 * 将文件名中的类型部分去掉。
	 * 
	 * @param filename
	 *            文件名
	 * @return 去掉类型部分的结果
	 */
	public static String trimExtension(String filename) {
		int index = filename.lastIndexOf(".");
		if (index != -1) {
			return filename.substring(0, index);
		} else {
			return filename;
		}
	}
	/**
	 * 根据内容生成文件
	 * 
	 * @param path
	 *            要生成文件的绝对路径
	 * @param 文件的内容
	 * @return
	 */
	public static boolean createFileByContext(String path, String content)
			throws IOException {

		path = getUNIXfilePath(path);
		String[] patharray = path.split("\\/");
		String modulepath = "";
		for (int i = 0; i < patharray.length - 1; i++) {
			modulepath += "/" + patharray[i];
		}
		File d = new File(modulepath.substring(1));
		if (!d.exists()) {
			d.mkdirs();
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter(path);
			// 将字符串写入文件
			fw.write(content);
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
		return true;
	}
}
