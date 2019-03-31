package methodreference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ExecuteAroundDemo 环绕执行
 */
public class ExecuteAroundDemo {

	public static void main(String[] args) throws IOException {
		System.out.println(processFile());
		System.out.println(processFile((BufferedReader br)->{return br.readLine()+br.readLine();}));
	}

	/**
	 * 将行为抽出后的方法
	 * @param fp
	 * @return
	 * @throws IOException
	 */
	public static String processFile(FileProcessor fp) throws IOException{
		InputStream dataIn = ExecuteAroundDemo.class.getResourceAsStream("/data.txt");
		try(BufferedReader br = new BufferedReader(new InputStreamReader(dataIn,"UTF-8"))){
			return fp.process(br);
		}
	}	
	/**
	 * 一般读取文件方法
	 * @return
	 * @throws IOException
	 */
	public static String processFile() throws IOException {
		InputStream dataIn = ExecuteAroundDemo.class.getResourceAsStream("/data.txt");
		try(BufferedReader br = new BufferedReader(new InputStreamReader(dataIn,"UTF-8"))){
			return br.readLine();
		}
	}


}

