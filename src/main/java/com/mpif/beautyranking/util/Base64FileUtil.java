package com.mpif.beautyranking.util;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;
import org.apache.commons.io.FileUtils;

import java.io.*;

//import com.sun.mail.util.BASE64DecoderStream;
//import com.sun.mail.util.BASE64EncoderStream;

public class Base64FileUtil {

	private static String rootDir = System.getProperty("user.dir") + "/src/main/resources/";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		Base64FileUtil decoder = new Base64FileUtil();

		// File srcFile = new
		// File("C:\\Users\\Administrator\\Desktop\\abc.docx");
		// File destFile = new
		// File("C:\\Users\\Administrator\\Desktop\\GetAttachmentResponse_Content.txt");
		// decoder.encodeBase64File(srcFile, destFile);

		File srcFile = new File("C:\\Users\\Administrator\\Desktop\\GetAttachmentResponse_Content.txt");
		File destFile = new File("C:\\Users\\Administrator\\Desktop\\abc_decoded.docx");
		// decoder.decoderBase64File(srcFile, destFile);

		File file = new File(rootDir + "pornBase64Str.txt");
        String str = FileUtils.readFileToString(file);

		File decode = new File("/githubForSourcetree/pic_beauty_ranking/src/main/resources/imgs/porn.jpg");
		decoder.str2file(str, decode);
		
//		decoder.file2str(System.getProperty("user.dir") + "/src/main/resources/imgs/aaaaaa.jpg");

	}

	public void encodeBase64File(File sourceFile, File destFile) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(sourceFile));

			bos = new BufferedOutputStream(new BASE64EncoderStream(new FileOutputStream(destFile)));

			byte[] buffer = new byte[4096];
			int len;
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
				if (bis != null) {
					bis.close();
					bis = null;
				}
			} catch (IOException ex3) {
				ex3.printStackTrace();
			}
		}
	}

	public void decoderBase64File(File source, File dest) {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(new BASE64DecoderStream(new FileInputStream(source)));

			bos = new BufferedOutputStream(new FileOutputStream(dest));

			byte[] buffer = new byte[4096];
			int len;
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
				if (bis != null) {
					bis.close();
					bis = null;
				}
			} catch (IOException ex3) {
				ex3.printStackTrace();
			}
		}
	}

	/*
	 * UmFyIRoHAM+QcwAADQAAAAAAAAARUHQgkC8AFgMAAGYEAAACsff4nE14+
	 * kAdMwoAIAAAAHNvdXJj
	 * ZS50eHQA8OboSRHdUQyM082BV79qt8HfZs8ynTK3ycFRGNjcghIGkCAYCgvixJCSMRprcZoyVu7C
	 * pCSEYDcHKlMZBizEsum4idU8CXQqpcy+Zd85nMXjl/HL5fLtXar9X/p/+E2eedTXmfv+
	 * 2tFkoInC
	 * fzIfUzg2XD08ybjzO+LhkGdvNTevIb7VdVINMZ9UJuzo1RtRdS1wIdS3X2tX0vZRZ5JiRplUN
	 * /Py
	 * V7XeyC+HSTKOvXeHPc8QBaTtjjP05bmX5Zcr+fKZvBoq4KXynMP5Tlp6dHsfPXOZxx1kL/
	 * c80s8K KPZXYjHRT/ggR3kgWGSA7mCphHtzAwlDFNtwC+
	 * 7ss5t7QBU7KrKhwrfOJSB4pAXMezxWge474pxM
	 * LVutsmRcwZh6qStuV75bjPH9FZFHXrXIwDamk9wl98Gp+
	 * SlS9kDak2pGA1fAISFKVsMLNHzqzClf
	 * cfjrMTuUn1B7vbu6XN6VfrjLWdA4NyFuqr67OiMSizpa826HCm4UNzEyn4spn48Ysj/
	 * qBekbXUax fpYMDi4Ybwffa+
	 * sCxcPXiDhz9FENIV568G48MGY0MTyesuWFAVgQPuTpLA5pbrIzs5JxxT6CLPdK
	 * fLcwW+pI2yrPugxGondMq+KEMlwYu+vMdsBEyhnHweOanRtF4Aeaq7mKKgjOTQ0mxT+
	 * UiQzwaQSz
	 * h26836IqK4NJD/daDljKFA33NCrMP5rnPQeAlq+T+BQog+gO07it/5Q+MIBm8XC9ataZy+
	 * GLHsQn
	 * H1XHiPNxQJri4pcR4vQW947byhzTwOa9JcwWMx29sbPrdRoX0nZ30XtdwVJvE7eQym/
	 * A9ephrvQO
	 * v/1qX2w4De+8OJLiNqsKyLO8RGx7wRxFfuUWK7udQmV5aKpRLDat1b2zk1LYz4Dre8ceSozvo
	 * /2I s3JE02oQn8detvXmQbQ+pC4BU3Zv9J+
	 * 3jPctnLDmPNiLKbTwm8tjADE9DilORJH8GIWNids3Df/d
	 * sARFVC6CyS8pf4ibrbKRWYj0RxriGR7FUuamH4v7mBykrcOMaE1kLlHRxSfCXSd5tMihvp4ucJKa
	 * 37LEPXsAQAcA
	 * 
	 * the string come from the base64 encode stream of file 'source.rar'
	 */
	public void str2file(String str, File file) {
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;

		bis = new BufferedInputStream(new BASE64DecoderStream(new ByteArrayInputStream(str.getBytes())));
		byte[] data = new byte[4096];

		try {
			
			if(!file.exists()) {
				boolean flag = file.createNewFile();
				if(!flag) {
					throw new RuntimeException("file:[" + file.getAbsolutePath() + "] create fail.");
				}
			}
			
			bos = new BufferedOutputStream(new FileOutputStream(file));
			int len = 0;
			while ((len = bis.read(data)) != -1) {
				bos.write(data, 0, len);
			}
			bos.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
				
				if(bis != null) {
					bis.close();
					bis = null;
				}
			} catch (IOException ex3) {
				ex3.printStackTrace();
			}
		}
	}

	public String file2str(String filePath) {

		BufferedReader br = null;
		BufferedWriter bw = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		String base64Str = "";
		StringBuffer sb = null;
		OutputStream os = null;
		byte[] data = new byte[40960];

		try {
			
			File file = new File(filePath);
			File out = null;
			
			String fileName = file.getName();
			String opt = "";
			if(file.length() >= FileUtils.ONE_MB * 10) {
				opt = "file";
				System.out.println(fileName + ":");
				out = new File(filePath + "_out.txt");
				if(!out.exists()) {
					boolean flag = out.createNewFile();
					System.out.println("createNewFile:" + flag);
				}
				os = new FileOutputStream(out);
			} else {
				opt = "string";
				sb = new StringBuffer();
				sb.append(fileName).append(":");
				os = new ByteArrayOutputStream();
			}
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new BASE64EncoderStream(os));

			int len = 0;
			while ((len = bis.read(data)) != -1) {
				bos.write(data, 0, len);
			}
			bos.flush();

//			br = new BufferedReader(new InputStreamReader(bis));
//			bw = new BufferedWriter(new OutputStreamWriter(bos));
//
//			String line = "";
//			while((line = br.readLine()) != null) {
//				bw.write(line);
//			}
//			bw.flush();

			if(opt.equals("string")) {
				sb.append(os.toString());
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
				
				if(bis != null) {
					bis.close();
					bis = null;
				}
				if (bw != null) {
					bw.flush();
					bw.close();
					bw = null;
				}

				if(br != null) {
					br.close();
					br = null;
				}
			} catch (IOException ex3) {
				ex3.printStackTrace();
			}
		}
		base64Str = sb.toString();
		System.out.println("文件的base64字符串为:");
		base64Str = base64Str.replaceAll("\r\n", "");
		System.out.println(base64Str);


		return base64Str;
		
	}
}
