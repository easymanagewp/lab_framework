package cn.core.framework.utils.importx;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.springframework.web.multipart.MultipartFile;

import cn.core.framework.exception.GlobalException;
import cn.core.framework.log.Logger;
import cn.core.framework.utils.ApplicationUtils;

public class ImportUtils {
	
	public static final String SERVER_BASE = ApplicationUtils.getValue("config.server.base").toString().replace("\\", File.separator);
	public static final String TEMPLATE_PATH = SERVER_BASE+ApplicationUtils.getValue("config.import.template").toString().replace("\\", File.separator);
	public static final String TEMP_PATH = SERVER_BASE+ApplicationUtils.getValue("config.import.temp").toString().replace("\\", File.separator);
	

	/**
	 * <strong>创建信息:2015年7月10日 上午9:34:34 </strong> <br>
	 * <strong>概要 : 获取excel数据</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param file excel文件对象
	 *@return
	 *@throws GlobalException
	 */
	public static String[][] importData(MultipartFile file) throws GlobalException {
		return readExcel(uploadTemp(file), 0);
	}

	/**
	 * <strong>创建信息:2015年7月10日 上午9:35:19 </strong> <br>
	 * <strong>概要 : 存储导入模板文件</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param file 模板文件对象
	 *@param tempName 模板文件名
	 *@return 文件存储路径
	 */
	public static String uploadTemplate(MultipartFile file,String templateName) {
		File targetFile = template(templateName);
		saveFile(file, targetFile);
		return targetFile.getAbsolutePath();
	}
	
	/**
	 * <strong>创建信息:2015年7月10日 下午2:24:51 </strong> <br>
	 * <strong>概要 : 模板文件对象</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param templateName 模板名称
	 *@return File 对象
	 */
	public static File template(String templateName) {
		return new File(TEMPLATE_PATH, templateName);
	}
	
	/**
	 * <strong>创建信息:2015年7月10日 下午2:23:01 </strong> <br>
	 * <strong>概要 : 根据模板名称获取模板路径</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param templateName
	 *@return
	 */
	public static String getTemplatePath(String templateName) {
		return template(templateName).getAbsolutePath();
	}
	/**
	 * <strong>创建信息: 2015年7月10日 上午9:36:11 </strong> <br>
	 * <strong>概要 : 文件存储</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param file 原始文件对象
	 *@param targetFile 文件存储对象
	 */
	private static void saveFile(MultipartFile file, File targetFile) {
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			Logger.getLogger(ImportUtils.class).error("文件存储出错", e);
		}
	}
	
	/**
	 * <strong>创建信息:2015年7月10日 上午9:36:46 </strong> <br>
	 * <strong>概要 : 文件存储</strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param file 临时文件对象
	 *@return
	 */
	public static String uploadTemp(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		File targetFile = new File(TEMP_PATH, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			Logger.getLogger(ImportUtils.class).error("文件导出，临时文件存储出错"+fileName, e);
		}
		
		return targetFile.getAbsolutePath();
	}
	
	/**
	 * <strong>创建信息:2015年7月10日 上午9:37:17 </strong> <br>
	 * <strong>概要 : </strong> <br>
	 * <strong>修改记录 : (修改人： ;时间： ;目的: ;)</strong> <br>
	 *@param filePath 文件绝对路径
	 *@param sheetNum 要解析的数据所在sheet页
	 *@return
	 *@throws GlobalException
	 */
	private static String[][] readExcel(String filePath, int sheetNum) throws GlobalException {
		try {
			InputStream is = new FileInputStream(filePath);
			// 创建一个工作薄Workbook
			Workbook rwb = Workbook.getWorkbook(is);
			// 检查工作表的有效性
			int sheets = rwb.getNumberOfSheets();
			if (sheetNum < 0 || sheetNum >= sheets || sheets == 0)
				throw new Exception("没此工作表");
			// 获取一个工作表Sheet
			Sheet sht = rwb.getSheet(sheetNum);
			// System.err.println("工作表: "+sht.getName()+sht.getRows());
			// 获取行列值
			Cell cell[] = null;
			String value[][] = null;
			value = new String[sht.getRows()][];
			for (int r = 0; r < sht.getRows(); r++) {
				cell = sht.getRow(r);
				value[r] = new String[cell.length];
				for (int c = 0; c < cell.length; c++) {
					value[r][c] = cell[c].getContents();
				}
			}
			if(null!=is){
				is.close();
			}
			return value;
		} catch (Exception e) {
			Logger.getLogger(ImportUtils.class).error("解析excel文件出错"+filePath, e);
			throw new GlobalException("解析excel文件出错"+filePath, e);
		}
	}

	public static void main(String[] args) {
		String path = "E:\\git\\res1503\\src\\main\\webapp\\static\\export\\temp\\1436488200844-222.xls";
		String[][] dateArrays = null;
		try {
			dateArrays = ImportUtils.readExcel(path, 0);
		} catch (GlobalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0, k1=dateArrays.length; i<k1; i++){
			for(int j=0, k2=dateArrays[i].length; j<k2; j++){
				System.out.print(i+"-"+j+":");
				System.out.print(dateArrays[i][j]);
				System.out.print("   ");
			}
			System.out.println();
		}
	}
}
